package de.drremote.dsp408.emu;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

final class MeterGuiFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final String[] CHANNEL_NAMES = {
            "InA", "InB", "InC", "InD",
            "Out1", "Out2", "Out3", "Out4", "Out5", "Out6", "Out7", "Out8"
    };

    private static final String[] OUTPUT_NAMES = {
            "Out1", "Out2", "Out3", "Out4", "Out5", "Out6", "Out7", "Out8"
    };

    private final MeterEngine meterEngine;
    private final JSlider[] sliders = new JSlider[12];
    private final JCheckBox[] outputLimitChecks = new JCheckBox[8];

    MeterGuiFrame(MeterEngine meterEngine) {
        super("DSP408 Meter Emulator");
        this.meterEngine = meterEngine;

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setContentPane(buildUi());
        pack();
        setLocationByPlatform(true);

        refreshLimiterUiFromEngine();
    }

    private JPanel buildUi() {
        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(buildActionBar());
        north.add(Box.createVerticalStrut(8));
        north.add(buildLimiterPanel());

        JPanel channels = new JPanel(new GridLayout(1, 12, 6, 0));
        for (int i = 0; i < CHANNEL_NAMES.length; i++) {
            channels.add(buildChannelStrip(i, CHANNEL_NAMES[i]));
        }

        JLabel hint = new JLabel(
                "Known-good controls only: sliders drive slot bytes 0/1 for meter display; output limiter checkboxes drive payload[41].",
                SwingConstants.LEFT
        );

        root.add(north, BorderLayout.NORTH);
        root.add(channels, BorderLayout.CENTER);
        root.add(hint, BorderLayout.SOUTH);
        return root;
    }

    private JPanel buildActionBar() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));

        JButton zeroAll = new JButton("Zero all");
        zeroAll.addActionListener(e -> applyAll(0));

        JButton halfAll = new JButton("50%");
        halfAll.addActionListener(e -> applyAll(50));

        JButton maxAll = new JButton("Max");
        maxAll.addActionListener(e -> applyAll(100));

        JButton clearLimits = new JButton("All limits off");
        clearLimits.addActionListener(e -> {
            meterEngine.setAllGuiOutputLimits(false);
            refreshLimiterUiFromEngine();
        });

        JButton setLimits = new JButton("All limits on");
        setLimits.addActionListener(e -> {
            meterEngine.setAllGuiOutputLimits(true);
            refreshLimiterUiFromEngine();
        });

        top.add(zeroAll);
        top.add(halfAll);
        top.add(maxAll);
        top.add(clearLimits);
        top.add(setLimits);
        return top;
    }

    private JPanel buildLimiterPanel() {
        JPanel outer = new JPanel();
        outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
        outer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Output limiter indicators (payload[41])"),
                new EmptyBorder(6, 6, 6, 6)
        ));

        JPanel checks = new JPanel(new GridLayout(2, 4, 8, 6));
        for (int i = 0; i < OUTPUT_NAMES.length; i++) {
            final int outputIndex = i;
            JCheckBox cb = new JCheckBox(OUTPUT_NAMES[i] + " limit");
            cb.addActionListener(e -> meterEngine.setGuiOutputLimit(outputIndex, cb.isSelected()));
            outputLimitChecks[i] = cb;
            checks.add(cb);
        }

        JLabel note = new JLabel(
                "This panel controls the proven limiter status bitmask in 0x40 payload byte 41.",
                SwingConstants.LEFT
        );

        outer.add(checks);
        outer.add(Box.createVerticalStrut(6));
        outer.add(note);
        return outer;
    }

    private void refreshLimiterUiFromEngine() {
        for (int i = 0; i < outputLimitChecks.length; i++) {
            if (outputLimitChecks[i] != null) {
                outputLimitChecks[i].setSelected(meterEngine.isGuiOutputLimitActive(i));
            }
        }
    }

    private JPanel buildChannelStrip(int slot, String channelName) {
        JPanel strip = new JPanel();
        strip.setLayout(new BoxLayout(strip, BoxLayout.Y_AXIS));
        strip.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                new EmptyBorder(6, 6, 6, 6)
        ));

        JLabel title = new JLabel(channelName, SwingConstants.CENTER);
        title.setAlignmentX(CENTER_ALIGNMENT);

        JSlider slider = new JSlider(SwingConstants.VERTICAL, 0, 100, 0);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setAlignmentX(CENTER_ALIGNMENT);
        slider.setPreferredSize(new Dimension(48, 220));
        slider.setMaximumSize(new Dimension(60, 240));

        JLabel percentLabel = new JLabel("0%", SwingConstants.CENTER);
        percentLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel rawLabel = new JLabel("00 00", SwingConstants.CENTER);
        rawLabel.setAlignmentX(CENTER_ALIGNMENT);

        sliders[slot] = slider;

        Runnable update = () -> {
            int percent = slider.getValue();
            meterEngine.setGuiSliderPercent(slot, percent);

            int[] pair = previewMeterPair(percent);
            percentLabel.setText(percent + "%");
            rawLabel.setText(String.format("%02X %02X", pair[0], pair[1]));
        };

        slider.addChangeListener(e -> update.run());

        strip.add(title);
        strip.add(Box.createVerticalStrut(6));
        strip.add(slider);
        strip.add(Box.createVerticalStrut(6));
        strip.add(percentLabel);
        strip.add(Box.createVerticalStrut(2));
        strip.add(rawLabel);

        update.run();
        return strip;
    }

    private void applyAll(int percent) {
        for (JSlider slider : sliders) {
            if (slider != null) {
                slider.setValue(percent);
            }
        }
    }

    private static int[] previewMeterPair(int percent) {
        int raw = mapPercentToObservedFloat16Raw(percent);
        int low = raw & 0xFF;
        int high = (raw >> 8) & 0xFF;
        return new int[]{low, high};
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

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
