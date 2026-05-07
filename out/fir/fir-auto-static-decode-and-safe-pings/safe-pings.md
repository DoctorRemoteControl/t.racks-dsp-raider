# FIR408 DSPD Safe Same-Value Pings

- Source: current `readSaveConfig`/`ReadBlockSet` memory
- Writes: current values only
- Skipped deliberately: mute writes, crossover remembered-state writes, and extended dynamics

| Test | Payload | Response |
| --- | --- | --- |
| InA-name-current | `00 01 0A 3D 00 49 6E 41 00 00 00 00 00` | `01 00 01 01` |
| InA-gain-current | `00 01 04 34 00 18 01` | `01 00 01 01` |
| InA-phase-current | `00 01 03 36 00 00` | `01 00 01 01` |
| InA-delay-current | `00 01 04 38 00 00 00` | `01 00 01 01` |
| InA-gate-current | `00 01 0A 3E 00 00 00 63 00 63 00 00 00` | `01 00 01 01` |
| InA-peq1-current | `00 01 0A 33 00 00 78 00 0B 00 23 00 00` | `01 00 01 01` |
| InB-name-current | `00 01 0A 3D 01 49 6E 42 00 00 00 00 00` | `01 00 01 01` |
| InB-gain-current | `00 01 04 34 01 18 01` | `01 00 01 01` |
| InB-phase-current | `00 01 03 36 01 00` | `01 00 01 01` |
| InB-delay-current | `00 01 04 38 01 00 00` | `01 00 01 01` |
| InB-gate-current | `00 01 0A 3E 01 00 00 63 00 63 00 00 00` | `01 00 01 01` |
| InB-peq1-current | `00 01 0A 33 01 00 78 00 0B 00 23 00 00` | `01 00 01 01` |
| InC-name-current | `00 01 0A 3D 02 49 6E 43 00 00 00 00 00` | `01 00 01 01` |
| InC-gain-current | `00 01 04 34 02 18 01` | `01 00 01 01` |
| InC-phase-current | `00 01 03 36 02 00` | `01 00 01 01` |
| InC-delay-current | `00 01 04 38 02 00 00` | `01 00 01 01` |
| InC-gate-current | `00 01 0A 3E 02 00 00 63 00 63 00 00 00` | `01 00 01 01` |
| InC-peq1-current | `00 01 0A 33 02 00 78 00 0B 00 23 00 00` | `01 00 01 01` |
| InD-name-current | `00 01 0A 3D 03 49 6E 44 00 00 00 00 00` | `01 00 01 01` |
| InD-gain-current | `00 01 04 34 03 18 01` | `01 00 01 01` |
| InD-phase-current | `00 01 03 36 03 00` | `01 00 01 01` |
| InD-delay-current | `00 01 04 38 03 00 00` | `01 00 01 01` |
| InD-gate-current | `00 01 0A 3E 03 00 00 63 00 63 00 00 00` | `01 00 01 01` |
| InD-peq1-current | `00 01 0A 33 03 00 78 00 0B 00 23 00 00` | `01 00 01 01` |
| Out1-name-current | `00 01 0A 3D 04 4F 75 74 31 00 00 00 00` | `01 00 01 01` |
| Out1-gain-current | `00 01 04 34 04 18 01` | `01 00 01 01` |
| Out1-phase-current | `00 01 03 36 04 00` | `01 00 01 01` |
| Out1-delay-current | `00 01 04 38 04 00 00` | `01 00 01 01` |
| Out1-matrix-route-current | `00 01 03 3A 04 01` | `01 00 01 01` |
| Out1-matrix-gain-in1-current | `00 01 05 41 04 00 18 01` | `01 00 01 01` |
| Out1-matrix-gain-in2-current | `00 01 05 41 04 01 18 01` | `01 00 01 01` |
| Out1-matrix-gain-in3-current | `00 01 05 41 04 02 18 01` | `01 00 01 01` |
| Out1-matrix-gain-in4-current | `00 01 05 41 04 03 18 01` | `01 00 01 01` |
| Out1-peq1-current | `00 01 0A 33 04 00 78 00 1F 00 23 00 00` | `01 00 01 01` |
| Out1-fir-generator-current | `00 01 0A 4B 04 02 03 2B 01 2C 01 09 00` | `01 00 01 01` |
| Out2-name-current | `00 01 0A 3D 05 4F 75 74 32 00 00 00 00` | `01 00 01 01` |
| Out2-gain-current | `00 01 04 34 05 18 01` | `01 00 01 01` |
| Out2-phase-current | `00 01 03 36 05 00` | `01 00 01 01` |
| Out2-delay-current | `00 01 04 38 05 00 00` | `01 00 01 01` |
| Out2-matrix-route-current | `00 01 03 3A 05 02` | `01 00 01 01` |
| Out2-matrix-gain-in1-current | `00 01 05 41 05 00 18 01` | `01 00 01 01` |
| Out2-matrix-gain-in2-current | `00 01 05 41 05 01 18 01` | `01 00 01 01` |
| Out2-matrix-gain-in3-current | `00 01 05 41 05 02 18 01` | `01 00 01 01` |
| Out2-matrix-gain-in4-current | `00 01 05 41 05 03 18 01` | `01 00 01 01` |
| Out2-peq1-current | `00 01 0A 33 05 00 78 00 1F 00 23 00 00` | `01 00 01 01` |
| Out2-fir-generator-current | `00 01 0A 4B 05 00 06 6E 00 2C 01 08 00` | `01 00 01 01` |
| Out3-name-current | `00 01 0A 3D 06 4F 75 74 33 00 00 00 00` | `01 00 01 01` |
| Out3-gain-current | `00 01 04 34 06 18 01` | `01 00 01 01` |
| Out3-phase-current | `00 01 03 36 06 00` | `01 00 01 01` |
| Out3-delay-current | `00 01 04 38 06 00 00` | `01 00 01 01` |
| Out3-matrix-route-current | `00 01 03 3A 06 04` | `01 00 01 01` |
| Out3-matrix-gain-in1-current | `00 01 05 41 06 00 18 01` | `01 00 01 01` |
| Out3-matrix-gain-in2-current | `00 01 05 41 06 01 18 01` | `01 00 01 01` |
| Out3-matrix-gain-in3-current | `00 01 05 41 06 02 18 01` | `01 00 01 01` |
| Out3-matrix-gain-in4-current | `00 01 05 41 06 03 18 01` | `01 00 01 01` |
| Out3-peq1-current | `00 01 0A 33 06 00 78 00 1F 00 23 00 00` | `01 00 01 01` |
| Out3-fir-generator-current | `00 01 0A 4B 06 00 06 6E 00 2C 01 08 00` | `01 00 01 01` |
| Out4-name-current | `00 01 0A 3D 07 4F 75 74 34 00 00 00 00` | `01 00 01 01` |
| Out4-gain-current | `00 01 04 34 07 18 01` | `01 00 01 01` |
| Out4-phase-current | `00 01 03 36 07 00` | `01 00 01 01` |
| Out4-delay-current | `00 01 04 38 07 00 00` | `01 00 01 01` |
| Out4-matrix-route-current | `00 01 03 3A 07 08` | `01 00 01 01` |
| Out4-matrix-gain-in1-current | `00 01 05 41 07 00 18 01` | `01 00 01 01` |
| Out4-matrix-gain-in2-current | `00 01 05 41 07 01 18 01` | `01 00 01 01` |
| Out4-matrix-gain-in3-current | `00 01 05 41 07 02 18 01` | `01 00 01 01` |
| Out4-matrix-gain-in4-current | `00 01 05 41 07 03 18 01` | `01 00 01 01` |
| Out4-peq1-current | `00 01 0A 33 07 00 78 00 1F 00 23 00 00` | `01 00 01 01` |
| Out4-fir-generator-current | `00 01 0A 4B 07 00 06 6E 00 2C 01 08 00` | `01 00 01 01` |
| Out5-name-current | `00 01 0A 3D 08 4F 75 74 35 00 00 00 00` | `01 00 01 01` |
| Out5-gain-current | `00 01 04 34 08 18 01` | `01 00 01 01` |
| Out5-phase-current | `00 01 03 36 08 00` | `01 00 01 01` |
| Out5-delay-current | `00 01 04 38 08 00 00` | `01 00 01 01` |
| Out5-matrix-route-current | `00 01 03 3A 08 01` | `01 00 01 01` |
| Out5-matrix-gain-in1-current | `00 01 05 41 08 00 18 01` | `01 00 01 01` |
| Out5-matrix-gain-in2-current | `00 01 05 41 08 01 18 01` | `01 00 01 01` |
| Out5-matrix-gain-in3-current | `00 01 05 41 08 02 18 01` | `01 00 01 01` |
| Out5-matrix-gain-in4-current | `00 01 05 41 08 03 18 01` | `01 00 01 01` |
| Out5-peq1-current | `00 01 0A 33 08 00 78 00 1F 00 23 00 00` | `01 00 01 01` |
| Out5-fir-generator-current | `00 01 0A 4B 08 00 06 6E 00 2C 01 08 00` | `01 00 01 01` |
| Out6-name-current | `00 01 0A 3D 09 4F 75 74 36 00 00 00 00` | `01 00 01 01` |
| Out6-gain-current | `00 01 04 34 09 18 01` | `01 00 01 01` |
| Out6-phase-current | `00 01 03 36 09 00` | `01 00 01 01` |
| Out6-delay-current | `00 01 04 38 09 00 00` | `01 00 01 01` |
| Out6-matrix-route-current | `00 01 03 3A 09 02` | `01 00 01 01` |
| Out6-matrix-gain-in1-current | `00 01 05 41 09 00 18 01` | `01 00 01 01` |
| Out6-matrix-gain-in2-current | `00 01 05 41 09 01 18 01` | `01 00 01 01` |
| Out6-matrix-gain-in3-current | `00 01 05 41 09 02 18 01` | `01 00 01 01` |
| Out6-matrix-gain-in4-current | `00 01 05 41 09 03 18 01` | `01 00 01 01` |
| Out6-peq1-current | `00 01 0A 33 09 00 78 00 1F 00 23 00 00` | `01 00 01 01` |
| Out6-fir-generator-current | `00 01 0A 4B 09 00 06 6E 00 2C 01 08 00` | `01 00 01 01` |
| Out7-name-current | `00 01 0A 3D 0A 4F 75 74 37 00 00 00 00` | `01 00 01 01` |
| Out7-gain-current | `00 01 04 34 0A 18 01` | `01 00 01 01` |
| Out7-phase-current | `00 01 03 36 0A 00` | `01 00 01 01` |
| Out7-delay-current | `00 01 04 38 0A 00 00` | `01 00 01 01` |
| Out7-matrix-route-current | `00 01 03 3A 0A 04` | `01 00 01 01` |
| Out7-matrix-gain-in1-current | `00 01 05 41 0A 00 18 01` | `01 00 01 01` |
| Out7-matrix-gain-in2-current | `00 01 05 41 0A 01 18 01` | `01 00 01 01` |
| Out7-matrix-gain-in3-current | `00 01 05 41 0A 02 18 01` | `01 00 01 01` |
| Out7-matrix-gain-in4-current | `00 01 05 41 0A 03 18 01` | `01 00 01 01` |
| Out7-peq1-current | `00 01 0A 33 0A 00 78 00 1F 00 23 00 00` | `01 00 01 01` |
| Out7-fir-generator-current | `00 01 0A 4B 0A 00 06 6E 00 2C 01 08 00` | `01 00 01 01` |
| Out8-name-current | `00 01 0A 3D 0B 4F 75 74 38 00 00 00 00` | `01 00 01 01` |
| Out8-gain-current | `00 01 04 34 0B 18 01` | `01 00 01 01` |
| Out8-phase-current | `00 01 03 36 0B 00` | `01 00 01 01` |
| Out8-delay-current | `00 01 04 38 0B 00 00` | `01 00 01 01` |
| Out8-matrix-route-current | `00 01 03 3A 0B 08` | `01 00 01 01` |
| Out8-matrix-gain-in1-current | `00 01 05 41 0B 00 18 01` | `01 00 01 01` |
| Out8-matrix-gain-in2-current | `00 01 05 41 0B 01 18 01` | `01 00 01 01` |
| Out8-matrix-gain-in3-current | `00 01 05 41 0B 02 18 01` | `01 00 01 01` |
| Out8-matrix-gain-in4-current | `00 01 05 41 0B 03 18 01` | `01 00 01 01` |
| Out8-peq1-current | `00 01 0A 33 0B 00 78 00 1F 00 23 00 00` | `01 00 01 01` |
| Out8-fir-generator-current | `00 01 0A 4B 0B 00 06 6E 00 2C 01 08 00` | `01 00 01 01` |

- Total pings: 112