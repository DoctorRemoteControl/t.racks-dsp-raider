# FIR408 0x56 Read-only Offset Probe

- Read-only probe. No DSP setting write commands are sent.
- Selector: 0x00
- Offsets: `0,1,2,13,14,15,38,39,40,41,150,157,158,177,195,196,197`
- Attempts per offset: 1
- Requests: 17
- Data rows: 6, zero rows: 2, partial rows: 5, FF rows: 4, no response rows: 0

| Selector | Offset | Bytes | Non-FF bytes | Non-FF slots | Prefix | Float[0] | Float[1] | Class |
| ---: | ---: | ---: | ---: | ---: | --- | ---: | ---: | --- |
| 0x00 | 0 | 52 | 52 | 13 | `AC C5 27 37 AC C5 A7 37 82 A8 FB 37 AC C5 27 38 17 B7 51 38 ...` | 1.0E-5 | 2.0E-5 | data |
| 0x00 | 1 | 52 | 52 | 13 | `F7 CC 12 39 52 49 1D 39 AC C5 27 39 07 42 32 39 62 BE 3C 39 ...` | 1.4E-4 | 1.5E-4 | data |
| 0x00 | 2 | 52 | 52 | 13 | `C9 8E 8D 39 F7 CC 92 39 24 0B 98 39 52 49 9D 39 7F 87 A2 39 ...` | 2.7E-4 | 2.8E-4 | data |
| 0x00 | 13 | 52 | 51 | 13 | `89 D2 DE 3A 14 22 E0 3A 9F 71 E1 3A 2B C1 E2 3A B6 10 E4 3A ...` | 0.0017 | 0.00171 | partial_data |
| 0x00 | 14 | 52 | 51 | 13 | `9C DC EF 3A 28 2C F1 3A B3 7B F2 3A 3E CB F3 3A CA 1A F5 3A ...` | 0.00183 | 0.00184 | partial_data |
| 0x00 | 15 | 52 | 52 | 13 | `58 73 00 3B 1E 1B 01 3B E3 C2 01 3B A9 6A 02 3B 6F 12 03 3B ...` | 0.00196 | 0.00197 | data |
| 0x00 | 38 | 52 | 52 | 13 | `9C 33 A2 3B 7F 87 A2 3B 62 DB A2 3B 45 2F A3 3B 27 83 A3 3B ...` | 0.00495 | 0.00496 | data |
| 0x00 | 39 | 52 | 20 | 5 | `21 76 A6 3B 04 CA A6 3B E7 1D A7 3B C9 71 A7 3B AC C5 A7 3B ...` | 0.00508 | 0.00509 | partial_data |
| 0x00 | 40 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 41 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 150 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 157 | 52 | 24 | 6 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | partial_data |
| 0x00 | 158 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 177 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 80 3F 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | data |
| 0x00 | 195 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 196 | 52 | 48 | 12 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | partial_data |
| 0x00 | 197 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |