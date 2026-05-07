# FIR408 Decode Strategy

Goal: decode the t.racks/Thomann DSP408 FIR variant as a mostly compatible
DSP408 superset. Do not re-discover every normal DSP function from scratch.
Use `DspLib-408.json` as the known DSP408 reference and keep
`DspLib-408-fir.json` as the FIR408 overlay/evidence library.

## Evidence Rules

Use three evidence levels:

- `observed`: directly captured on the FIR408.
- `inherited_confirmed`: same command and payload layout as DSP408, verified by
  at least one controlled FIR408 capture.
- `predicted_from_dsp408`: copied from DSP408 because the protocol family and
  channel map match, but not yet directly tested on FIR408.

Only mark a field stable when one of these is true:

- controlled GUI write captured, with only one non-meter write;
- active readback confirms the changed storage bytes;
- both directions were captured for a toggle or reversible setting;
- repeated after reconnect and still identical.

## Current FIR408 Facts

- Device info is `FIR408 V0113P` from command `0x13`.
- Channel map is the standard 4-in/8-out DSP408 map:
  `InA..InD = 0x00..0x03`, `Out1..Out8 = 0x04..0x0B`.
- FIR408 config readback uses command `0x27` with response `0x24`.
- FIR408 has 32 config blocks `0x00..0x1F`.
- Full assembled config data is 1556 bytes.
- Blocks `0x00..0x1E` carry 50 data bytes each; block `0x1F` carries 6.
- FIR file-name table is at absolute offset `1460`, 12 entries, 8 bytes each.
- Command `0x56` returns 52 data bytes per response. Empty slots are `0xFF`;
  after External FIR load, low offsets contain little-endian float32 coefficient
  data or coefficient-preview data.
- Out1 IIR/FIR mode write is decoded:
  - `00 01 03 4C 04 01` = Out1 FIR
  - `00 01 03 4C 04 00` = Out1 IIR
- Out8 FIR mode sample confirmed the endpoint prediction:
  - `00 01 03 4C 0B 01` = Out8 FIR
- Out1 IIR/FIR mode did not change any byte in the `0x27/0x24` config dump.

## External FIR Test Files

Created deterministic import files in `fir-test-files/`:

- `fir_512_impulse_first.txt`: tap 0 = `1.0`, all others `0.0`
- `fir_512_impulse_center.txt`: tap 255 = `1.0`, all others `0.0`
- `fir_512_two_tap_1_05.txt`: tap 0 = `1.0`, tap 1 = `0.5`
- `fir_512_small_ramp.txt`: tiny increasing values from `0.000001` to `0.000512`

Use `fir_512_impulse_first.txt` first. It is the easiest file for discovering
coefficient byte order and fixed-point scale.

First External FIR result:

- `0x4F` starts or clears the transfer: `00 01 03 4F 00 00`.
- `0x4E` uploads coefficient chunks.
- `0x5B` writes the 8-byte imported name prefix.
- The 512-tap impulse upload produced 43 x `0x4E` chunks.
- Total coefficient payload is `2048` bytes = `512 x 4`.
- Upload coefficient encoding is IEEE-754 float32 big-endian.
- The first impulse coefficient was sent as `3F 80 00 00`.
- `0x56` readback at offset 0 returned `00 00 80 3F`, so where present the
  readback encoding is IEEE-754 float32 little-endian.
- Important correction from the live GUI: txt/csv External FIR import is
  available only for `InA..InD`. Therefore the captured selector `0x00` is now
  treated as `InA` / input FIR slot 0, not Out1.
- PDF note: `c_472928_v2_en_online.pdf` page 24 describes FIR import in the
  input-channel section. Page 26 also mentions External FIR in the output
  section, but the current live software behavior should drive the capture plan.

Prediction sample round:

- InB External FIR used selector `0x01` in `0x4F`, every `0x4E` chunk, and
  `0x5B`. This confirms the input selector map at least for `InA=0x00` and
  `InB=0x01`; predict `InC=0x02`, `InD=0x03`.
- The two-tap file produced upload bytes `3F 80 00 00 3F 00 00 00` and `0x56`
  readback bytes `00 00 80 3F 00 00 00 3F`. So upload is float32 big-endian;
  readback is float32 little-endian at low offsets.
- `Type=HIGH PASS` produced `0x4B` type byte `0x02`.
- `Win=HAMMING` produced `0x4B` window byte `0x03`; a repeat capture produced
  the same payload.
- Out8 mode produced `0x4C` channel byte `0x0B`, confirming the output endpoint
  of the standard channel map.

## FIR-Specific GUI Inventory

The user screenshots show this FIR-only control group:

| UI control | Visible values / behavior | Decode status |
| --- | --- | --- |
| Mode | `IIR`, `FIR` | `0x4C`; Out1 and Out8 endpoints confirmed |
| HighPass | fixed while `Type=BYPASS`, editable with an active filter type | decoded as `0x4B` u16le raw |
| LowPass | vertical fader, numeric display such as `20.16KHz` | decoded as `0x4B` u16le raw |
| Type | `BYPASS`, `LOW PASS`, `HIGH PASS`, `BAND PASS`, `External FIR` | `0x4B` u8; `LOW PASS`, `HIGH PASS`, `BAND PASS` observed, endpoints predicted by UI order |
| Win | `HAMMING`, `BLACKMAN`, `SINE`, `SINC`, `NUTTALL`, `KAISER`, plus one partially visible `BLACK-N...` | `0x4B` u8; `HAMMING`, `SINC` observed, others predicted by UI order |
| Taps | visible range appears to be `256..1024` in steps of `32` | decoded as `taps = 256 + raw * 32` |
| OK | apply button | no separate command observed; capture produced duplicated `0x4B` only |

Treat screenshot-only values as UI evidence. Protocol bytes become stable only
after a clean capture for each control/value.

## Strategy Overview

### 0. Decode Static Config Automatically First

Use the finished `DspLib-408.json` as the base map and decode FIR408 config
blocks before spending GUI captures.

Automatic pass:

1. Read FIR408 blocks `0x00..0x1F` with `script-example/195-fir-read-config-allblocks.dspd`.
2. Run:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\tools\decode-fir408-config.ps1 `
  -BlocksDir out\fir\fir-read-config-allblocks `
  -Lib DspLib-408.json `
  -OutFile out\fir\fir408-auto-decode-summary.md `
  -FocusOutput Out1
```

This needs no GUI action. It decodes the static preset/config values that are
actually present in the `0x27`/`0x24` block dump: channel names, output gain,
normal DSP408 output PEQ/crossover-style fields, FIR generator fields, and FIR
file-name table.

Current output-record FIR storage candidate:

- output record base: `568 + output_index * 108`
- FIR Type: record offset `28`
- FIR Win: record offset `29`
- FIR HighPass raw u16le: record offsets `30..31`
- FIR LowPass raw u16le: record offsets `32..33`
- FIR Taps raw u16le: record offsets `34..35`
- Output PEQ1 starts at record offset `36`, then 9 records x 6 bytes.

Use GUI captures only for fields that are not visible in this static dump or
whose write command/storage relationship is still uncertain. Examples:

- live-only `0x4C` IIR/FIR processing mode;
- unknown FIR apply/bypass behavior;
- External FIR upload/readback edge cases;
- proof samples when a predicted enum endpoint needs promotion to observed.

### 1. Keep Infrastructure Stable

Use project-local proxy logs and active scripts that start a fresh proxy-side
session:

```dspd
connect();
resetSession();
ensureSession();
handshake();
```

For passive GUI captures, keep Track DSP connected to `127.0.0.1:9761`.
Every capture should save:

- raw capture summary;
- last non-`0x40` write;
- recent non-`0x40` writes;
- read-block responses, if any;
- before/after all-block readback when storage matters.

### 2. Confirm Common DSP408 Commands Fast

The FIR408 likely keeps almost all normal DSP408 commands. Confirm by family,
not by every single value first.

High-priority common command checks:

| Function | DSP408 command | FIR408 test |
| --- | --- | --- |
| Channel name | `0x3D` | Rename one channel, read back all blocks |
| Gain | `0x34` | Move InA gain or Out1 gain one step |
| Mute | `0x35` | Toggle one input and one output |
| Phase | `0x36` | Toggle one input and one output |
| Delay | `0x38` | Change one output delay |
| PEQ | `0x33` | Change Out1 PEQ1 gain/frequency/Q/type |
| Crossover | `0x31`/`0x32` | Change Out1 HP/LP frequency and mode |
| Matrix route | `0x3A` | Toggle one input route to Out1 |
| Matrix gain | `0x41` | Change one matrix crosspoint gain |
| GEQ input | `0x48` | Change one InA GEQ band |
| Gate | `0x3E` | Change one InA gate field |
| Limiter | `0x3F` | Change one Out1 limiter field |
| Compressor | `0x30` | Change one Out1 compressor field |
| Test tone | `0x39` | Change source or sine frequency |

If the captured payload layout matches `DspLib-408.json`, copy the model into
`DspLib-408-fir.json` as `inherited_confirmed`.

Do minimal channel coverage first:

- Inputs: verify `InA = 0x00` and `InD = 0x03`.
- Outputs: verify `Out1 = 0x04` and `Out8 = 0x0B`.
- If both ends match, fill intermediate channels as predicted from the standard
  channel map.

### 3. Separate Write Decode From Read Storage

The normal DSP408 live writes may be the same, but FIR408 read storage is
different enough to map separately.

For each important parameter family:

1. Read all FIR408 blocks into `out/fir/<tag>/before`.
2. Change exactly one GUI value.
3. Save the GUI write capture.
4. Read all blocks again into `out/fir/<tag>/after`.
5. Diff all blocks and record changed absolute offsets.
6. Store write mapping and read-storage mapping separately.

This avoids mixing up command decode with preset/config memory layout.

### 4. Decode FIR-Specific Controls

FIR-specific controls have priority because the normal DSP408 library cannot
cover them.

Immediate FIR-specific captures:

1. Optional endpoint check: capture Out8 `FIR -> IIR` if both directions are
   needed. Out8 `IIR -> FIR` is already confirmed as `00 01 03 4C 0B 01`.
2. Capture only high-value enum endpoints now:
   - `Type=BYPASS` or `External FIR` if the GUI produces a clean write.
   - `Win=KAISER` if we want to confirm the top end of the predicted window map.
3. Taps are decoded as `taps = 256 + raw * 32`; verify endpoints `256` and
   `1024` once.
4. HighPass and LowPass raw fields are decoded; capture more labels only if the
   frequency curve needs extra proof.
5. OK after a HighPass change produced no separate command; only duplicated
   `0x4B` was captured. Retest only if another UI state behaves differently.
6. For External FIR, capture `InD` once to confirm the far end of the input
   selector map.
7. Load `fir_512_impulse_center.txt` and run the `0x56` probe to learn whether
   `0x56` exposes the full coefficient array or a short preview window.

Known scripts:

- `script-example/195-fir-read-config-allblocks.dspd`
- `script-example/196-fir-auto-capture-file-assignment-clean.dspd`
- `script-example/197-fir-probe-cmd56-nonempty-file.dspd`
- `script-example/198-fir-auto-capture-enable-toggle-clean.dspd`
- `script-example/199-fir-auto-capture-load-nonempty-file-clean.dspd`
- `script-example/200-fir-auto-capture-out1-mode-iir-to-fir-clean.dspd`
- `script-example/201-fir-read-config-after-out1-mode-fir-allblocks.dspd`
- `script-example/202-fir-auto-capture-out1-mode-fir-to-iir-clean.dspd`
- `script-example/203-fir-auto-capture-out1-type-clean.dspd`
- `script-example/204-fir-auto-capture-out1-window-clean.dspd`
- `script-example/205-fir-auto-capture-out1-taps-clean.dspd`
- `script-example/206-fir-auto-capture-out1-highpass-frequency-clean.dspd`
- `script-example/207-fir-auto-capture-out1-lowpass-frequency-clean.dspd`
- `script-example/208-fir-auto-capture-out1-ok-apply-clean.dspd`
- `script-example/209-fir-auto-capture-out8-mode-iir-to-fir-clean.dspd`
- `script-example/210-fir-auto-capture-out1-highpass-19700hz-ok-apply-clean.dspd`
- `script-example/211-fir-auto-capture-out1-external-fir-impulse-load-clean.dspd` (legacy misnamed; captured selector 0)
- `script-example/213-fir-auto-capture-ina-external-fir-impulse-load-clean.dspd`
- `script-example/214-fir-auto-capture-inb-external-fir-impulse-load-clean.dspd`
- `script-example/215-fir-auto-capture-inc-external-fir-impulse-load-clean.dspd`
- `script-example/216-fir-auto-capture-ind-external-fir-impulse-load-clean.dspd`
- `script-example/217-fir-auto-capture-ina-external-fir-two-tap-load-clean.dspd`
- `script-example/218-fir-auto-capture-out1-type-high-pass-clean.dspd`
- `script-example/219-fir-auto-capture-out1-window-hamming-clean.dspd`

Recommended FIR UI capture order:

1. Put Out1 in FIR mode.
2. Start with dropdowns because they usually create one compact write:
   `Type`, `Win`, `Taps`.
3. Then capture faders because they may send multiple writes while dragged:
   HighPass and LowPass should be moved one small step and released.
4. Finally capture `OK`, because it may trigger generated-coefficient upload or
   a burst of writes.
5. Only after the GUI control writes are understood, decode `External FIR` file
   loading and command `0x56` coefficient chunks.

For the next External FIR selector-mapping endpoint capture, use InD:

```powershell
java -jar .\tracks-dsp-script-all.jar --file .\script-example\216-fir-auto-capture-ind-external-fir-impulse-load-clean.dspd
```

Then in the GUI load:

```text
C:\Users\XLA\DSP408\tracks-dsp-raider\fir-test-files\fir_512_impulse_first.txt
```

`InA=0x00` and `InB=0x01` are already observed. If `InD=0x03` matches, fill
`InC=0x02` as predicted from the contiguous input map.

### 5. Make Coefficient Decoding Deterministic

Do not try to decode coefficients from an unknown real-world FIR file first.
Use synthetic test files where the expected coefficient pattern is known.

Recommended FIR test sequence:

1. Impulse:
   - coefficient 0 = `1.0`
   - all others = `0.0`
2. Shifted impulse:
   - coefficient 1 or 16 = `1.0`
   - all others = `0.0`
3. Two impulses:
   - coefficient 0 = `1.0`
   - coefficient 1 = `0.5`
4. Alternating signs:
   - `+0.5, -0.5, +0.5, -0.5`
5. Small ramp:
   - `0.001, 0.002, 0.003, ...`

For each file:

1. Load exactly one file into exactly one channel.
2. Capture the GUI upload/write traffic.
3. Reconnect Track DSP if needed and capture startup reads.
4. Run `script-example/197-fir-probe-cmd56-nonempty-file.dspd`.
5. Compare `0x56` chunks against the known coefficient positions.

Decode hypotheses to test:

- 52 bytes may be 13 x 32-bit samples.
- 52 bytes may be 26 x 16-bit samples.
- uploaded coefficient values are float32 big-endian.
- low-offset `0x56` readback values are float32 little-endian.
- `0x56` offsets may be coefficient positions, not raw byte offsets.
- selector byte is the input channel or input FIR slot for observed `InA=0x00`
  and `InB=0x01`; confirm `InD=0x03` as the far endpoint.

### 6. Promote Results Into Tools

After each stable discovery:

1. Update `DspLib-408-fir.json`.
2. Add or update live decoder support in `WriteDecodeService`.
3. Run JSON validation.
4. Run Maven build.
5. Copy fresh `target/tracks-dsp-*-all.jar` files into the project root.
6. Smoke-test the new decoder path with the captured payloads.

### 7. Suggested Next Session Order

Best next steps:

1. Capture one normal DSP408 function, for example Out1 gain or mute, to prove
   common command compatibility on FIR408.
2. Capture `InD` External FIR import to confirm the input selector endpoint.
3. Load `fir_512_impulse_center.txt` on InA and rerun `0x56`.
4. Capture taps endpoints `256` and `1024`.
5. Capture one predicted window endpoint, preferably `KAISER`.

This order keeps captures sparse: confirm endpoints, then fill the middle from
the already-matching contiguous maps.
