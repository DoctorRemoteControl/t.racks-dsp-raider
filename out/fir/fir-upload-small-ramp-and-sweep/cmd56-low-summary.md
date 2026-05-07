# FIR408 0x56 Read-only Sweep

- Read-only probe. No DSP setting write commands are sent.
- Selectors: 0x00..0x00
- Offsets: 0..60 step 1
- Requests: 61
- Data rows: 2, zero rows: 37, partial rows: 1, FF rows: 21, no response rows: 0

| Selector | Offset | Bytes | Non-FF bytes | Non-FF slots | Prefix | Float[0] | Float[1] | Class |
| ---: | ---: | ---: | ---: | ---: | --- | ---: | ---: | --- |
| 0x00 | 0 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 1 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 2 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 3 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 4 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 5 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 6 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 7 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 8 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 9 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 10 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 11 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 12 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 13 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 14 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 15 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 16 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 17 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 18 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 19 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | data |
| 0x00 | 20 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 21 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 22 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 23 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 24 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 25 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 26 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 27 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 28 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 29 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 30 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 31 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 32 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 33 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 34 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 35 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 36 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 37 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 38 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | data |
| 0x00 | 39 | 52 | 20 | 5 | `4E 2B 05 3A 69 6E 05 3A 85 B1 05 3A A1 F4 05 3A BD 37 06 3A ...` | 5.08E-4 | 5.09E-4 | partial_data |
| 0x00 | 40 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 41 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 42 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 43 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 44 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 45 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 46 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 47 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 48 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 49 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 50 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 51 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 52 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 53 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 54 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 55 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 56 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 57 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 58 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 59 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 60 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |