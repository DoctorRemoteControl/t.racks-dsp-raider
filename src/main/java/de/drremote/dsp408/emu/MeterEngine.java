package de.drremote.dsp408.emu;

import java.util.Arrays;

final class MeterEngine {
    private static final int CHANNEL_COUNT = 12;
    private static final int BYTES_PER_CHANNEL = 3;

    private MeterMode mode;
    private int configuredSlot;
    private int focusByte;
    private int rampStep;
    private int slotHoldPolls;

    private int meterLowByte;
    private int meterHighByte;
    private int meterPeakByte;
    private int statusByte40;
    private int statusByte41;

    private int floatRampStartHigh;
    private int floatRampEndHigh;

    private final int[][] manualTriplets = new int[CHANNEL_COUNT][BYTES_PER_CHANNEL];

    private int tick;
    private int rampValue;
    private boolean rampUp = true;

    private long configVersion;
    private MeterDebugState lastDebugState = new MeterDebugState("INIT", -1, 0, 0, 0, 0);

    MeterEngine(EmulatorConfig config) {
        this.mode = config.meterMode();
        this.configuredSlot = config.activeSlot();
        this.focusByte = config.focusByte();
        this.rampStep = config.rampStep();
        this.slotHoldPolls = config.slotHoldPolls();
        this.meterLowByte = config.meterLowByte();
        this.meterHighByte = config.meterHighByte();
        this.meterPeakByte = config.meterPeakByte();

        this.floatRampStartHigh = this.meterHighByte;
        this.floatRampEndHigh = this.meterHighByte;

        refreshPreviewDebugState();
    }

    synchronized byte[] nextMeterPayload() {
        byte[] payload = new byte[42];
        payload[0] = 0x01;
        payload[1] = 0x00;
        payload[2] = 0x27;
        payload[3] = 0x40;

        byte[] meterRegion = new byte[36];
        int debugSlot = -1;

        switch (mode) {
            case ZERO -> Arrays.fill(meterRegion, (byte) 0x00);

            case SINGLE_SLOT_RAMP -> {
                debugSlot = configuredSlot;
                applySingleSlotRamp(meterRegion, configuredSlot);
            }

            case SLOT_SWEEP -> {
                debugSlot = currentSweepSlot();
                applySingleSlotRamp(meterRegion, debugSlot);
            }

            case FLOAT16_SLOT_CONSTANT -> {
                debugSlot = configuredSlot;
                applyTriplet(meterRegion, configuredSlot, meterLowByte, meterHighByte, meterPeakByte);
            }

            case FLOAT16_ENDIAN_TOGGLE -> {
                debugSlot = configuredSlot;
                applyEndianToggle(meterRegion);
            }

            case FLOAT16_SLOT_SCAN -> {
                debugSlot = currentSweepSlot();
                applyTriplet(meterRegion, debugSlot, meterLowByte, meterHighByte, meterPeakByte);
            }

            case FLOAT16_HIGH_RAMP -> {
                debugSlot = configuredSlot;
                applyTriplet(
                        meterRegion,
                        configuredSlot,
                        meterLowByte,
                        currentFloatRampHigh(),
                        meterPeakByte
                );
            }

            case PEAK_RAMP -> {
                debugSlot = configuredSlot;
                applyTriplet(
                        meterRegion,
                        configuredSlot,
                        meterLowByte,
                        meterHighByte,
                        nextRampValue()
                );
            }

            case NAN_SLOT -> {
                debugSlot = configuredSlot;
                applyTriplet(meterRegion, configuredSlot, 0xFF, 0xFF, meterPeakByte);
            }

            case MANUAL_GUI -> {
                copyManualTripletsTo(meterRegion);
                debugSlot = firstActiveManualSlot();
            }
        }

        System.arraycopy(meterRegion, 0, payload, 4, meterRegion.length);
        payload[40] = (byte) (statusByte40 & 0xFF);
        payload[41] = (byte) (statusByte41 & 0xFF);

        lastDebugState = createDebugState(meterRegion, debugSlot, tick);
        tick++;

        return payload;
    }

    synchronized MeterDebugState debugState() {
        return lastDebugState;
    }

    synchronized long configVersion() {
        return configVersion;
    }

    synchronized String currentSummary() {
        return configSummary();
    }

    synchronized String configSummary() {
        if (mode == MeterMode.FLOAT16_HIGH_RAMP) {
            return String.format(
                    "[TEST] current mode=%s slot=%d low=0x%02X startHigh=0x%02X endHigh=0x%02X peak=0x%02X hold=%d status40=0x%02X status41=0x%02X",
                    mode,
                    configuredSlot,
                    meterLowByte,
                    floatRampStartHigh,
                    floatRampEndHigh,
                    meterPeakByte,
                    slotHoldPolls,
                    statusByte40,
                    statusByte41
            );
        }

        if (mode == MeterMode.MANUAL_GUI) {
            return String.format(
                    "[TEST] current mode=MANUAL_GUI status40=0x%02X status41=0x%02X",
                    statusByte40,
                    statusByte41
            );
        }

        return String.format(
                "[TEST] current mode=%s slot=%d focusByte=%d step=%d hold=%d low=0x%02X high=0x%02X peak=0x%02X status40=0x%02X status41=0x%02X",
                mode,
                configuredSlot,
                focusByte,
                rampStep,
                slotHoldPolls,
                meterLowByte,
                meterHighByte,
                meterPeakByte,
                statusByte40,
                statusByte41
        );
    }

    synchronized void setConstantTriplet(int slot, int b0, int b1, int b2) {
        mode = MeterMode.FLOAT16_SLOT_CONSTANT;
        configuredSlot = clamp(slot, 0, 11);
        meterLowByte = clampByte(b0);
        meterHighByte = clampByte(b1);
        meterPeakByte = clampByte(b2);
        tick = 0;
        touchConfig();
    }

    synchronized void setConstant(int slot, int b0, int b1, int b2) {
        setConstantTriplet(slot, b0, b1, b2);
    }

    synchronized void setZero() {
        mode = MeterMode.ZERO;
        tick = 0;
        touchConfig();
    }

    synchronized void setNanSlot(int slot, int peak) {
        mode = MeterMode.NAN_SLOT;
        configuredSlot = clamp(slot, 0, 11);
        meterPeakByte = clampByte(peak);
        tick = 0;
        touchConfig();
    }

    synchronized void setNan(int slot, int peak) {
        setNanSlot(slot, peak);
    }

    synchronized void setSingleRamp(int slot, int byteIndex, int step) {
        mode = MeterMode.SINGLE_SLOT_RAMP;
        configuredSlot = clamp(slot, 0, 11);
        focusByte = clamp(byteIndex, 0, 2);
        rampStep = clamp(step, 1, 255);
        resetRamp();
        touchConfig();
    }

    synchronized void setRamp(int slot, int byteIndex, int step) {
        setSingleRamp(slot, byteIndex, step);
    }

    synchronized void setSweep(int byteIndex, int step, int hold) {
        mode = MeterMode.SLOT_SWEEP;
        focusByte = clamp(byteIndex, 0, 2);
        rampStep = clamp(step, 1, 255);
        slotHoldPolls = clamp(hold, 1, 1000);
        resetRamp();
        touchConfig();
    }

    synchronized void setSlotScan(int low, int high, int peak, int hold) {
        mode = MeterMode.FLOAT16_SLOT_SCAN;
        meterLowByte = clampByte(low);
        meterHighByte = clampByte(high);
        meterPeakByte = clampByte(peak);
        slotHoldPolls = clamp(hold, 1, 1000);
        tick = 0;
        touchConfig();
    }

    synchronized void setScan(int b0, int b1, int b2, int hold) {
        setSlotScan(b0, b1, b2, hold);
    }

    synchronized void setEndianToggle(int slot, int low, int high, int peak, int hold) {
        mode = MeterMode.FLOAT16_ENDIAN_TOGGLE;
        configuredSlot = clamp(slot, 0, 11);
        meterLowByte = clampByte(low);
        meterHighByte = clampByte(high);
        meterPeakByte = clampByte(peak);
        slotHoldPolls = clamp(hold, 1, 1000);
        tick = 0;
        touchConfig();
    }

    synchronized void setPeakRamp(int slot, int low, int high, int step) {
        mode = MeterMode.PEAK_RAMP;
        configuredSlot = clamp(slot, 0, 11);
        meterLowByte = clampByte(low);
        meterHighByte = clampByte(high);
        rampStep = clamp(step, 1, 255);
        resetRamp();
        touchConfig();
    }

    synchronized void setFloatHighRamp(int slot, int low, int startHigh, int endHigh, int peak, int hold) {
        mode = MeterMode.FLOAT16_HIGH_RAMP;
        configuredSlot = clamp(slot, 0, 11);
        meterLowByte = clampByte(low);
        meterPeakByte = clampByte(peak);
        floatRampStartHigh = clampByte(startHigh);
        floatRampEndHigh = clampByte(endHigh);
        meterHighByte = floatRampStartHigh;
        slotHoldPolls = clamp(hold, 1, 1000);
        tick = 0;
        touchConfig();
    }

    synchronized void setGuiSliderPercent(int slot, int percent) {
        int s = clamp(slot, 0, CHANNEL_COUNT - 1);
        int p = clamp(percent, 0, 100);

        mode = MeterMode.MANUAL_GUI;

        int raw = s < 4 ? mapInputPercentToObservedRaw(p) : mapOutputPercentToObservedRaw(p);
        manualTriplets[s][0] = raw & 0xFF;
        manualTriplets[s][1] = (raw >> 8) & 0xFF;

        if (s < 4) {
            manualTriplets[s][2] = 0x00;
        }

        touchConfig();
    }

    synchronized void setGuiOutputByte2(int outputIndex, int value) {
        int idx = clamp(outputIndex, 0, 7);
        int slot = 4 + idx;

        mode = MeterMode.MANUAL_GUI;
        manualTriplets[slot][2] = clampByte(value);

        touchConfig();
    }

    synchronized void setGuiManualTriplet(int slot, int b0, int b1, int b2) {
        int s = clamp(slot, 0, CHANNEL_COUNT - 1);

        mode = MeterMode.MANUAL_GUI;
        manualTriplets[s][0] = clampByte(b0);
        manualTriplets[s][1] = clampByte(b1);
        manualTriplets[s][2] = clampByte(b2);

        touchConfig();
    }

    synchronized void setGuiOutputLimit(int outputIndex, boolean limited) {
        int idx = clamp(outputIndex, 0, 7);
        int bit = 1 << idx;

        mode = MeterMode.MANUAL_GUI;

        if (limited) {
            statusByte41 |= bit;
        } else {
            statusByte41 &= ~bit;
        }

        touchConfig();
    }

    synchronized void setStatusBytes(int b40, int b41) {
        statusByte40 = clampByte(b40);
        statusByte41 = clampByte(b41);
        touchConfig();
    }
    
    synchronized int statusByte40() {
        return statusByte40;
    }

    synchronized int statusByte41() {
        return statusByte41;
    }

    synchronized boolean isGuiOutputLimitActive(int outputIndex) {
        int idx = clamp(outputIndex, 0, 7);
        return (statusByte41 & (1 << idx)) != 0;
    }

    synchronized void setAllGuiOutputLimits(boolean limited) {
        mode = MeterMode.MANUAL_GUI;
        statusByte41 = limited ? 0xFF : 0x00;
        touchConfig();
    }

    synchronized void clearUnknownStatusBytes() {
        statusByte40 = 0x00;
        touchConfig();
    }

    private void touchConfig() {
        configVersion++;
        refreshPreviewDebugState();
    }

    private void refreshPreviewDebugState() {
        byte[] meterRegion = new byte[36];
        int previewSlot = -1;

        switch (mode) {
            case ZERO -> Arrays.fill(meterRegion, (byte) 0x00);

            case SINGLE_SLOT_RAMP -> {
                previewSlot = configuredSlot;
                int base = configuredSlot * BYTES_PER_CHANNEL;
                meterRegion[base + focusByte] = (byte) (rampValue & 0xFF);
            }

            case SLOT_SWEEP -> {
                previewSlot = currentSweepSlot();
                int base = previewSlot * BYTES_PER_CHANNEL;
                meterRegion[base + focusByte] = (byte) (rampValue & 0xFF);
            }

            case FLOAT16_SLOT_CONSTANT -> {
                previewSlot = configuredSlot;
                applyTriplet(meterRegion, configuredSlot, meterLowByte, meterHighByte, meterPeakByte);
            }

            case FLOAT16_ENDIAN_TOGGLE -> {
                previewSlot = configuredSlot;
                applyTriplet(meterRegion, configuredSlot, meterLowByte, meterHighByte, meterPeakByte);
            }

            case FLOAT16_SLOT_SCAN -> {
                previewSlot = currentSweepSlot();
                applyTriplet(meterRegion, previewSlot, meterLowByte, meterHighByte, meterPeakByte);
            }

            case FLOAT16_HIGH_RAMP -> {
                previewSlot = configuredSlot;
                applyTriplet(meterRegion, configuredSlot, meterLowByte, currentFloatRampHigh(), meterPeakByte);
            }

            case PEAK_RAMP -> {
                previewSlot = configuredSlot;
                applyTriplet(meterRegion, configuredSlot, meterLowByte, meterHighByte, rampValue);
            }

            case NAN_SLOT -> {
                previewSlot = configuredSlot;
                applyTriplet(meterRegion, configuredSlot, 0xFF, 0xFF, meterPeakByte);
            }

            case MANUAL_GUI -> {
                copyManualTripletsTo(meterRegion);
                previewSlot = firstActiveManualSlot();
            }
        }

        lastDebugState = createDebugState(meterRegion, previewSlot, tick);
    }

    private void copyManualTripletsTo(byte[] meterRegion) {
        for (int slot = 0; slot < CHANNEL_COUNT; slot++) {
            int base = slot * BYTES_PER_CHANNEL;
            meterRegion[base] = (byte) (manualTriplets[slot][0] & 0xFF);
            meterRegion[base + 1] = (byte) (manualTriplets[slot][1] & 0xFF);
            meterRegion[base + 2] = (byte) (manualTriplets[slot][2] & 0xFF);
        }
    }

    private int firstActiveManualSlot() {
        for (int slot = 0; slot < CHANNEL_COUNT; slot++) {
            if (manualTriplets[slot][0] != 0 || manualTriplets[slot][1] != 0 || manualTriplets[slot][2] != 0) {
                return slot;
            }
        }
        return -1;
    }

    private static int mapPercentToObservedFloat16Raw(int percent) {
        int p = clamp(percent, 0, 100);
        if (p == 0) {
            return 0x0000;
        }

        int min = 0x3800;
        int max = 0x4000;
        return min + (int) Math.round((p / 100.0) * (max - min));
    }

    private static int mapInputPercentToObservedRaw(int percent) {
        return mapPercentToObservedFloat16Raw(percent);
    }

    private static int mapOutputPercentToObservedRaw(int percent) {
        return mapPercentToObservedFloat16Raw(percent);
    }

    private int currentFloatRampHigh() {
        int start = floatRampStartHigh;
        int end = floatRampEndHigh;

        if (start == end) {
            return start;
        }

        int dir = Integer.compare(end, start);
        int distance = Math.abs(end - start);
        int stepIndex = tick / slotHoldPolls;
        if (stepIndex > distance) {
            stepIndex = distance;
        }

        return clampByte(start + dir * stepIndex);
    }

    private MeterDebugState createDebugState(byte[] meterRegion, int slot, long poll) {
        if (slot < 0 || slot >= CHANNEL_COUNT) {
            return new MeterDebugState(mode.name(), -1, 0, 0, 0, poll);
        }

        int base = slot * BYTES_PER_CHANNEL;
        int b0 = meterRegion[base] & 0xFF;
        int b1 = meterRegion[base + 1] & 0xFF;
        int b2 = meterRegion[base + 2] & 0xFF;

        return new MeterDebugState(mode.name(), slot, b0, b1, b2, poll);
    }

    private void applySingleSlotRamp(byte[] meterRegion, int slot) {
        int value = nextRampValue();
        int base = slot * BYTES_PER_CHANNEL;
        meterRegion[base + focusByte] = (byte) value;
    }

    private void applyEndianToggle(byte[] meterRegion) {
        boolean swapped = ((tick / slotHoldPolls) & 1) == 1;

        if (swapped) {
            applyTriplet(meterRegion, configuredSlot, meterHighByte, meterLowByte, meterPeakByte);
        } else {
            applyTriplet(meterRegion, configuredSlot, meterLowByte, meterHighByte, meterPeakByte);
        }
    }

    private void applyTriplet(byte[] meterRegion, int slot, int b0, int b1, int b2) {
        if (slot < 0 || slot >= CHANNEL_COUNT) {
            return;
        }

        int base = slot * BYTES_PER_CHANNEL;
        meterRegion[base] = (byte) (b0 & 0xFF);
        meterRegion[base + 1] = (byte) (b1 & 0xFF);
        meterRegion[base + 2] = (byte) (b2 & 0xFF);
    }

    private int currentSweepSlot() {
        return (tick / slotHoldPolls) % CHANNEL_COUNT;
    }

    private int nextRampValue() {
        int out = rampValue;

        if (rampUp) {
            rampValue += rampStep;
            if (rampValue >= 255) {
                rampValue = 255;
                rampUp = false;
            }
        } else {
            rampValue -= rampStep;
            if (rampValue <= 0) {
                rampValue = 0;
                rampUp = true;
            }
        }

        return out;
    }

    private void resetRamp() {
        tick = 0;
        rampValue = 0;
        rampUp = true;
    }

    private static int clampByte(int value) {
        return clamp(value, 0, 255);
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
