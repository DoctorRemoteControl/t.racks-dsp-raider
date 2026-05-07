# FIR408 0x4B Probe

- Output: Out2
- Baseline: type=0 window=6 hp=110 lp=300 taps=8
- Final: type=0 window=6 hp=110 lp=300 taps=8
- Restored: True

| Test | Payload | Response | Readback | Matches | Restore |
| --- | --- | --- | --- | ---: | --- |
| type-raw-0 | `00 01 0A 4B 05 00 06 6E 00 2C 01 08 00` | `01 00 01 01` | type=0 window=6 hp=110 lp=300 taps=8 | True | `01 00 01 01` |
| type-raw-1 | `00 01 0A 4B 05 01 06 6E 00 2C 01 08 00` | `01 00 01 01` | type=1 window=6 hp=110 lp=300 taps=8 | True | `01 00 01 01` |
| type-raw-2 | `00 01 0A 4B 05 02 06 6E 00 2C 01 08 00` | `01 00 01 01` | type=2 window=6 hp=110 lp=300 taps=8 | True | `01 00 01 01` |
| type-raw-3 | `00 01 0A 4B 05 03 06 6E 00 2C 01 08 00` | `01 00 01 01` | type=3 window=6 hp=110 lp=300 taps=8 | True | `01 00 01 01` |
| type-raw-4 | `00 01 0A 4B 05 04 06 6E 00 2C 01 08 00` | `01 00 01 01` | type=4 window=6 hp=110 lp=300 taps=8 | True | `01 00 01 01` |
| window-raw-3 | `00 01 0A 4B 05 02 03 6E 00 2C 01 08 00` | `01 00 01 01` | type=2 window=3 hp=110 lp=300 taps=8 | True | `01 00 01 01` |
| window-raw-4 | `00 01 0A 4B 05 02 04 6E 00 2C 01 08 00` | `01 00 01 01` | type=2 window=4 hp=110 lp=300 taps=8 | True | `01 00 01 01` |
| window-raw-5 | `00 01 0A 4B 05 02 05 6E 00 2C 01 08 00` | `01 00 01 01` | type=2 window=5 hp=110 lp=300 taps=8 | True | `01 00 01 01` |
| window-raw-6 | `00 01 0A 4B 05 02 06 6E 00 2C 01 08 00` | `01 00 01 01` | type=2 window=6 hp=110 lp=300 taps=8 | True | `01 00 01 01` |
| window-raw-7 | `00 01 0A 4B 05 02 07 6E 00 2C 01 08 00` | `01 00 01 01` | type=2 window=7 hp=110 lp=300 taps=8 | True | `01 00 01 01` |
| window-raw-8 | `00 01 0A 4B 05 02 08 6E 00 2C 01 08 00` | `01 00 01 01` | type=2 window=8 hp=110 lp=300 taps=8 | True | `01 00 01 01` |
| taps-raw-0 | `00 01 0A 4B 05 02 06 6E 00 2C 01 00 00` | `01 00 01 01` | type=2 window=6 hp=110 lp=300 taps=0 | True | `01 00 01 01` |
| taps-raw-8 | `00 01 0A 4B 05 02 06 6E 00 2C 01 08 00` | `01 00 01 01` | type=2 window=6 hp=110 lp=300 taps=8 | True | `01 00 01 01` |
| taps-raw-9 | `00 01 0A 4B 05 02 06 6E 00 2C 01 09 00` | `01 00 01 01` | type=2 window=6 hp=110 lp=300 taps=9 | True | `01 00 01 01` |
| taps-raw-24 | `00 01 0A 4B 05 02 06 6E 00 2C 01 18 00` | `01 00 01 01` | type=2 window=6 hp=110 lp=300 taps=24 | True | `01 00 01 01` |
| highpass-raw-0 | `00 01 0A 4B 05 02 06 00 00 2C 01 08 00` | `01 00 01 01` | type=2 window=6 hp=0 lp=300 taps=8 | True | `01 00 01 01` |
| highpass-raw-150 | `00 01 0A 4B 05 02 06 96 00 2C 01 08 00` | `01 00 01 01` | type=2 window=6 hp=150 lp=300 taps=8 | True | `01 00 01 01` |
| highpass-raw-300 | `00 01 0A 4B 05 02 06 2C 01 2C 01 08 00` | `01 00 01 01` | type=2 window=6 hp=300 lp=300 taps=8 | True | `01 00 01 01` |
| lowpass-raw-0 | `00 01 0A 4B 05 01 06 6E 00 00 00 08 00` | `01 00 01 01` | type=1 window=6 hp=110 lp=0 taps=8 | True | `01 00 01 01` |
| lowpass-raw-150 | `00 01 0A 4B 05 01 06 6E 00 96 00 08 00` | `01 00 01 01` | type=1 window=6 hp=110 lp=150 taps=8 | True | `01 00 01 01` |
| lowpass-raw-300 | `00 01 0A 4B 05 01 06 6E 00 2C 01 08 00` | `01 00 01 01` | type=1 window=6 hp=110 lp=300 taps=8 | True | `01 00 01 01` |
