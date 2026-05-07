# FIR408 0x56 Read-only Offset Probe

- Read-only probe. No DSP setting write commands are sent.
- Selector: 0x00
- Offsets: `0,13,16,19,20,21,22,38,39,40,41,150,157,158,177,195,196,197`
- Attempts per offset: 1
- Requests: 18
- Data rows: 2, zero rows: 0, partial rows: 0, FF rows: 0, no response rows: 16

| Selector | Offset | Bytes | Non-FF bytes | Non-FF slots | Prefix | Float[0] | Float[1] | Class |
| ---: | ---: | ---: | ---: | ---: | --- | ---: | ---: | --- |
| 0x00 | 0 | 52 | 52 | 13 | `BD 37 86 35 BD 37 06 36 9C 53 49 36 BD 37 86 36 AC C5 A7 36 ...` | 1.0E-6 | 2.0E-6 | data |
| 0x00 | 13 | 52 | 52 | 13 | `07 42 32 39 77 4E 33 39 E6 5A 34 39 55 67 35 39 C5 73 36 39 ...` | 1.7E-4 | 1.71E-4 | data |
| 0x00 | 16 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 19 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 20 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 21 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 22 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 38 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 39 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 40 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 41 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 150 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 157 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 158 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 177 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 195 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 196 | 0 | 0 | 0 | `` |  |  | no_response |
| 0x00 | 197 | 0 | 0 | 0 | `` |  |  | no_response |