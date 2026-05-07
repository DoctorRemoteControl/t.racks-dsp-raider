# FIR408 Common Endpoint Pings

- Direct same-value endpoint probes for common DSP408 feature families.
- Baseline and after-action config dumps are compared byte-for-byte.

| Test | Payload | Meaning | Response |
| --- | --- | --- | --- |
| InA-gain-current-0db | `00 01 04 34 00 18 01` | InA gain raw 0x0118 = +0.0dB | `01 00 01 01` |
| InA-mute-current-normal | `00 01 03 35 00 00` | InA mute current Normal/unmuted | `01 00 01 01` |
| InA-phase-current-normal | `00 01 03 36 00 00` | InA phase current 0 degrees | `01 00 01 01` |
| InA-delay-current-zero | `00 01 04 38 00 00 00` | InA delay raw 0 = 0.0ms | `01 00 01 01` |
| InD-gain-current-0db | `00 01 04 34 03 18 01` | InD gain raw 0x0118 = +0.0dB | `01 00 01 01` |
| InD-mute-current-normal | `00 01 03 35 03 00` | InD mute current Normal/unmuted | `01 00 01 01` |
| InD-phase-current-normal | `00 01 03 36 03 00` | InD phase current 0 degrees | `01 00 01 01` |
| InD-delay-current-zero | `00 01 04 38 03 00 00` | InD delay raw 0 = 0.0ms | `01 00 01 01` |
| Out1-gain-current-0db | `00 01 04 34 04 18 01` | Out1 gain raw 0x0118 = +0.0dB | `01 00 01 01` |
| Out1-mute-current-normal | `00 01 03 35 04 00` | Out1 mute current Normal/unmuted | `01 00 01 01` |
| Out1-phase-current-normal | `00 01 03 36 04 00` | Out1 phase current 0 degrees | `01 00 01 01` |
| Out1-delay-current-zero | `00 01 04 38 04 00 00` | Out1 delay raw 0 = 0.0ms | `01 00 01 01` |
| Out8-gain-current-0db | `00 01 04 34 0B 18 01` | Out8 gain raw 0x0118 = +0.0dB | `01 00 01 01` |
| Out8-mute-current-normal | `00 01 03 35 0B 00` | Out8 mute current Normal/unmuted | `01 00 01 01` |
| Out8-phase-current-normal | `00 01 03 36 0B 00` | Out8 phase current 0 degrees | `01 00 01 01` |
| Out8-delay-current-zero | `00 01 04 38 0B 00 00` | Out8 delay raw 0 = 0.0ms | `01 00 01 01` |
| InA-name-current | `00 01 0A 3D 00 49 6E 41 00 00 00 00 00` | InA channel name | `01 00 01 01` |
| InD-name-current | `00 01 0A 3D 03 49 6E 44 00 00 00 00 00` | InD channel name | `01 00 01 01` |
| Out1-name-current | `00 01 0A 3D 04 4F 75 74 31 00 00 00 00` | Out1 channel name | `01 00 01 01` |
| Out8-name-current | `00 01 0A 3D 0B 4F 75 74 38 00 00 00 00` | Out8 channel name | `01 00 01 01` |
| InA-gate-current | `00 01 0A 3E 00 00 00 63 00 63 00 00 00` | InA gate current attack/release/hold/threshold | `01 00 01 01` |
| InD-gate-current | `00 01 0A 3E 03 00 00 63 00 63 00 00 00` | InD gate current attack/release/hold/threshold | `01 00 01 01` |
| Out1-matrix-route-current-InA | `00 01 03 3A 04 01` | Out1 matrix route mask InA | `01 00 01 01` |
| Out8-matrix-route-current-InD | `00 01 03 3A 0B 08` | Out8 matrix route current mask InD | `01 00 01 01` |
| Out1-matrix-gain-InA-current-0db | `00 01 05 41 04 00 18 01` | Out1 <- InA matrix gain +0.0dB | `01 00 01 01` |
| Out8-matrix-gain-InD-current-0db | `00 01 05 41 0B 03 18 01` | Out8 <- InD matrix gain +0.0dB | `01 00 01 01` |
| Out1-fir-generator-current | `00 01 0A 4B 04 02 03 2B 01 2C 01 09 00` | Out1 FIR HIGH PASS/HAMMING/19.70kHz/20.16kHz/544 | `01 00 01 01` |
| Out8-fir-generator-current | `00 01 0A 4B 0B 00 06 6E 00 2C 01 08 00` | Out8 FIR BYPASS/SINC/250Hz/20.16kHz/512 | `01 00 01 01` |

## Config Compare

- Baseline: `out\fir\fir408-common-endpoint-pings-v2\baseline`
- After: `out\fir\fir408-common-endpoint-pings-v2\after`
- Byte-for-byte match: True
