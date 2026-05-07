# FIR408 0x56 Boundary Probe

- Selector 0x00 only.
- Read-only probe. No DSP settings are written.
- The offset appears to address float32 coefficient/display slots; response payload contains up to 13 float32-sized slots.

| Offset | Payload bytes | Non-FF bytes | Non-FF float slots | Prefix | Float[0] | Float[1] | Class |
| ---: | ---: | ---: | ---: | --- | ---: | ---: | --- |
| 0 | 52 | 52 | 13 | `00 00 80 3F 00 00 00 3F 00 00 00 00 00 00 00 00 00 00 00 00` | 1 | 0.5 | data_or_partial_data |
| 13 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00` | 0 | 0 | data_or_partial_data |
| 26 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00` | 0 | 0 | data_or_partial_data |
| 33 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00` | 0 | 0 | data_or_partial_data |
| 34 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00` | 0 | 0 | data_or_partial_data |
| 35 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00` | 0 | 0 | data_or_partial_data |
| 36 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00` | 0 | 0 | data_or_partial_data |
| 37 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00` | 0 | 0 | data_or_partial_data |
| 38 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00` | 0 | 0 | data_or_partial_data |
| 39 | 52 | 20 | 5 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00` | 0 | 0 | data_or_partial_data |
| 40 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 41 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 42 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 43 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 44 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 45 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 46 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 47 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 48 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 49 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 50 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 51 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 52 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 64 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 128 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 255 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 256 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
| 511 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF` |  |  | ff_empty_or_unavailable |
