# FIR408 0x56 Read-only Offset Probe

- Read-only probe. No DSP setting write commands are sent.
- Selector: 0x00
- Offsets: `16,19,20,21,22`
- Attempts per offset: 1
- Requests: 5
- Data rows: 5, zero rows: 0, partial rows: 0, FF rows: 0, no response rows: 0

| Selector | Offset | Bytes | Non-FF bytes | Non-FF slots | Prefix | Float[0] | Float[1] | Class |
| ---: | ---: | ---: | ---: | ---: | --- | ---: | ---: | --- |
| 0x00 | 16 | 52 | 52 | 13 | `62 F8 08 3B 27 A0 09 3B ED 47 0A 3B B3 EF 0A 3B 78 97 0B 3B ...` | 0.00209 | 0.0021 | data |
| 0x00 | 19 | 52 | 52 | 13 | `7F 87 22 3B 45 2F 23 3B 0A D7 23 3B D0 7E 24 3B 96 26 25 3B ...` | 0.00248 | 0.00249 | data |
| 0x00 | 20 | 52 | 52 | 13 | `89 0C 2B 3B 4E B4 2B 3B 14 5C 2C 3B DA 03 2D 3B 9F AB 2D 3B ...` | 0.00261 | 0.00262 | data |
| 0x00 | 21 | 52 | 52 | 13 | `92 91 33 3B 58 39 34 3B 1E E1 34 3B E3 88 35 3B A9 30 36 3B ...` | 0.00274 | 0.00275 | data |
| 0x00 | 22 | 52 | 52 | 13 | `9C 16 3C 3B 62 BE 3C 3B 27 66 3D 3B ED 0D 3E 3B B3 B5 3E 3B ...` | 0.00287 | 0.00288 | data |