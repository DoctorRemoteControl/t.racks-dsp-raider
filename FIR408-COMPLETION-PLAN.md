# FIR408 Completion Plan

Goal: make `DspLib-408-fir.json` complete while using as few GUI captures as
possible.

## Rule

Every field moves through this path:

1. `predicted_from_dsp408` or `predicted_from_ui_order`
2. automatic check if possible
3. active scripted write/read check if needed
4. GUI capture only when the value cannot be proven any other way
5. promote to `observed` or `inherited_confirmed`

The normal `DspLib-408.json` is the base library. FIR408-specific additions are
an overlay, not a full rediscovery from zero.

## Evidence Levels

- `observed`: captured or read directly on FIR408.
- `inherited_confirmed`: copied from `DspLib-408.json` and verified by one or
  more FIR408 endpoint tests.
- `predicted_from_dsp408`: copied from `DspLib-408.json` but not yet verified on
  FIR408.
- `predicted_from_ui_order`: enum prediction from the FIR408 GUI list order.
- `candidate_static_storage`: found in the FIR408 config dump, but still needs a
  before/after check to prove the field changes there.

## Minimal-Capture Workflow

### Phase 1: Static Decode, No GUI

Run a full FIR408 config read:

```powershell
java -jar .\tracks-dsp-script-all.jar --file .\script-example\195-fir-read-config-allblocks.dspd
```

Then decode the dump with the DSP408 reference:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\tools\decode-fir408-config.ps1 `
  -BlocksDir out\fir\fir-read-config-allblocks `
  -Lib DspLib-408.json `
  -OutFile out\fir\fir408-auto-decode-summary.md `
  -FocusOutput Out1
```

This proves values that are present in the `0x27`/`0x24` config dump. If the GUI
currently shows Out1 FIR `HIGH PASS / HAMMING / 19.70KHz / 544`, the auto decode
should show the same. If it does, promote the FIR output-record storage fields.

### Phase 2: Inherit Normal DSP408 Functions

For normal DSP408 features, do endpoint tests only:

- Inputs: `InA = 0x00` and `InD = 0x03`
- Outputs: `Out1 = 0x04` and `Out8 = 0x0B`

If endpoint payloads and readback diffs match `DspLib-408.json`, mark the whole
family as `inherited_confirmed`:

- gain, mute, phase, delay
- PEQ input/output
- crossover
- matrix routing and matrix gain
- gate
- compressor, limiter
- test tone
- channel names and preset names

FIR408 correction: this model has no GEQ. The inherited DSP408 GEQ section is
kept only as base-library reference and must be marked absent/unsupported for
FIR408. User screenshot of the top navigation shows `Gain`, `Gate`, `Comp`,
`Limit`, `Delay`, `Matrix`, `InA..InD`, and `Out1..Out8`, with no `GEQ` tab;
`0x48` returned status `01 00 01 02` with no retained config diff.

Use active scripted writes with readback diffs, not GUI captures. Always read a
baseline first and restore the value when possible.

### Phase 3: FIR Output Generator

Use direct `0x4B` writes and config readback before using GUI captures:

- Type enum:
  - observed: `0x02 = HIGH PASS`
  - observed/candidate: `0x00 = BYPASS`, `0x01 = LOW PASS`, `0x03 = BAND PASS`
  - predicted: `0x04 = External FIR`
- Window enum:
  - observed: `0x03 = HAMMING`, `0x06 = SINC`
  - predicted: `0x04 = BLACKMAN`, `0x05 = SINE`, `0x07 = NUTTALL`, `0x08 = KAISER`
- Taps model:
  - `taps = 256 + raw * 32`
  - verify endpoints with raw `0` and `24`
- Frequencies:
  - same `19.7Hz..20.16KHz` logarithmic model as DSP408

GUI capture is only needed if a direct write is rejected, not persisted, or the
GUI sends extra hidden commands.

### Phase 4: FIR Processing Mode

`0x4C` is live-write decoded:

- `00 01 03 4C 04 01` = Out1 FIR
- `00 01 03 4C 04 00` = Out1 IIR
- `00 01 03 4C 0B 01` = Out8 FIR

The normal config dump did not change after Out1 mode. Treat `0x4C` as live or
separate storage until proven otherwise. Only one optional GUI capture remains:
Out8 `FIR -> IIR`, if both directions are required.

### Phase 5: External FIR

Avoid real/unknown FIR files. Use deterministic test files from `fir-test-files`.

Already known:

- `0x4F`: transfer control
- `0x4E`: coefficient chunks, float32 big-endian upload
- `0x5B`: 8-byte FIR file name prefix
- `0x56`: low-offset readback is float32 little-endian where data exists
- observed selectors: `InA = 0x00`, `InB = 0x01`
- predicted selectors: `InC = 0x02`, `InD = 0x03`
- read-only direct `0x56` selector probe accepted selectors `0x00..0x03`;
  in the current DSP state only selector `0x00` exposed non-`FF` data at the
  sampled offsets. Selector `0x00` offset `0` returned the two-tap file as
  little-endian float32 `1.0, 0.5`.

Next with minimal GUI:

1. Only upload to `InC` or `InD` after explicit approval, because it overwrites
   that input slot's current external FIR coefficients.
2. Run `0x56` selector probes for `0x00..0x03` after any approved upload.
3. Load or upload center impulse and check whether `0x56` exposes tap 255.

GUI capture is only needed if direct upload and GUI import behave differently.

## Current Best Next Step

Updated result:

- The proxy control status was `active=false, ready=false`, so proxy injection
  was not available.
- Direct raw socket access to `192.168.0.166:9761` worked.
- `tools/read-fir408-config-direct.ps1` read all blocks `0x00..0x1F` without a
  GUI capture.
- `tools/decode-fir408-config.ps1` decoded Out1 and matched the GUI screenshot:
  FIR `HIGH PASS`, Win `HAMMING`, HighPass `19.70KHz`, LowPass `20.16KHz`,
  Taps `544`, and PEQ1..PEQ9 values/types.
- `tools/verify-fir408-safe-write-pings.ps1` sent same-value writes for Out1
  gain, mute, phase, delay, PEQ1, FIR generator, channel name, IIR crossover
  HP/LP active values, matrix route, and matrix gain. All returned ACK
  `01 00 01 01`.
- The IIR crossover pings exposed a FIR408-specific static record correction:
  output IIR HP/LP live frequency and mode fields are at record offsets
  `20/22/24/25`, not `22/24/26/27`. The active HP/LP values were restored and
  a follow-up dump was unchanged after the corrected same-value ping script.
- Record offsets `26/27` behave like remembered/secondary IIR slope state. They
  are now marked separately instead of being used as the active IIR mode fields.
- The direct raw reader is now length-based. FIR408 block payloads can contain a
  literal `10 03` byte sequence, so delimiter-only parsing can truncate a valid
  block.
- Additional same-value probes confirmed Gate InA and Test Tone. The user
  confirmed FIR408 has no GEQ; the earlier `0x48` probe returned
  `01 00 01 02` and had no retained config diff, so GEQ is marked absent on
  FIR408. Compressor/`0x30` needs a FIR408 extended payload; the inherited
  `00 01 0C 30 ...` form is unsafe for FIR408 because it is too short.
- Limiter/`0x3F` has the same FIR408 caveat: the inherited `00 01 0A 3F ...`
  form is too short. The restored/current FIR408-safe form is
  `00 01 0C 3F ...`.
- FIR generator/`0x4B` was verified on Out2 by direct write/readback/restore:
  type raw `0..4`, window raw `3..8`, taps raw `0/8/9/24`, and HP/LP frequency
  endpoint samples all read back exactly as written. A full config dump after
  restore matched the previous baseline byte-for-byte.
- `tools/probe-fir408-cmd56-selectors.ps1` read selectors `0x00..0x03` without
  changing the DSP. Current result: selector `0x00` offset `0` exposes the
  two-tap file as little-endian float32 `1.0/0.5`; selectors `0x01..0x03`
  returned `FF` at the sampled offsets in the current device state.

Next:

1. Use direct raw socket scripts whenever the proxy has no inject-ready session.
2. Do not decode GEQ for FIR408. It is not present on this model; `0x48` is
   treated as unsupported here.
3. For any family that needs read-storage proof, do direct write/readback with
   restore rather than GUI capture.
4. Keep GUI captures only for GUI-only behavior or hidden multi-command actions.
