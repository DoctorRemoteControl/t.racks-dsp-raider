# FIR408 0x56 Read-only Sweep

- Read-only probe. No DSP setting write commands are sent.
- Selectors: 0x00..0x00
- Offsets: 0..60 step 1
- Requests: 61
- Data rows: 35, zero rows: 0, partial rows: 5, FF rows: 21, no response rows: 0

| Selector | Offset | Bytes | Non-FF bytes | Non-FF slots | Prefix | Float[0] | Float[1] | Class |
| ---: | ---: | ---: | ---: | ---: | --- | ---: | ---: | --- |
| 0x00 | 0 | 52 | 52 | 13 | `BE AE B5 38 28 F2 74 38 72 D8 65 B8 7A FB BB B8 E6 F1 B2 36 ...` | 8.663301E-5 | 5.839966E-5 | data |
| 0x00 | 1 | 52 | 52 | 13 | `8F F4 AC B8 48 E1 25 38 C0 8B E0 38 FA 08 A5 37 BA 1C D0 B8 ...` | -8.247152E-5 | 3.9548875E-5 | data |
| 0x00 | 2 | 52 | 52 | 13 | `6B C4 9D B7 C7 EA 09 B9 9F B2 66 B8 81 DE DD 38 08 18 F6 38 ...` | -1.8807323E-5 | -1.31528E-4 | data |
| 0x00 | 3 | 52 | 52 | 13 | `C6 E5 27 39 5C C1 E0 38 6D AB DF B8 5F 39 35 B9 61 B7 60 37 ...` | 1.6011958E-4 | 1.0717168E-4 | data |
| 0x00 | 4 | 52 | 52 | 13 | `93 85 3D B9 10 DC C0 38 31 61 7D 39 C2 5C 2C 38 D1 E5 70 B9 ...` | -1.8074205E-4 | 9.196263E-5 | data |
| 0x00 | 5 | 52 | 52 | 13 | `77 B3 51 B8 F3 B5 A6 B9 82 A6 06 B9 97 1D 88 39 A2 C1 93 39 ...` | -4.9996623E-5 | -3.179755E-4 | data |
| 0x00 | 6 | 52 | 52 | 13 | `99 F1 CC 39 F3 77 85 39 C1 26 8A B9 99 B5 D9 B9 F7 A3 24 38 ...` | 3.9089916E-4 | 2.5457106E-4 | data |
| 0x00 | 7 | 52 | 52 | 13 | `50 C6 DC B9 C7 15 6B 39 56 45 14 3A E8 02 B7 38 E2 5E 0D BA ...` | -4.2109424E-4 | 2.2419459E-4 | data |
| 0x00 | 8 | 52 | 52 | 13 | `1A CA 01 B9 2A 35 3C BA 93 C5 91 B9 8F 3C 1A 3A 36 0C 23 3A ...` | -1.2377687E-4 | -7.179553E-4 | data |
| 0x00 | 9 | 52 | 52 | 13 | `03 04 5F 3A 28 F9 0C 3A 45 80 17 BA FD E4 67 BA 28 F0 CE 38 ...` | 8.5073727E-4 | 5.3777033E-4 | data |
| 0x00 | 10 | 52 | 52 | 13 | `11 8D 62 BA E4 30 FC 39 05 F1 98 3A 3B 93 29 39 98 59 92 BA ...` | -8.6422364E-4 | 4.8101612E-4 | data |
| 0x00 | 11 | 52 | 52 | 13 | `B6 BA 8D B9 A8 11 BD BA 8C 66 0C BA 26 E2 9B 3A F6 B1 A0 3A ...` | -2.7032726E-4 | -0.0014424818 | data |
| 0x00 | 12 | 52 | 52 | 13 | `4D C3 DB 3A 8B 02 87 3A C0 E9 96 BA 00 D6 E0 BA C9 D9 67 39 ...` | 0.0016766578 | 0.001030044 | data |
| 0x00 | 13 | 52 | 52 | 13 | `63 A4 D7 BA 5E 38 7B 3A CE F5 12 3B 65 D0 90 39 E9 B8 0D BB ...` | -0.0016452189 | 9.5832895E-4 | data |
| 0x00 | 14 | 52 | 52 | 13 | `38 D1 12 BA 99 C0 35 BB 9C 84 81 BA 75 7B 17 3B BB A9 18 3B ...` | -5.600634E-4 | -0.0027733205 | data |
| 0x00 | 15 | 52 | 51 | 13 | `AD EE 55 3B 28 0D 00 3B 4D 58 15 BB 87 4D 59 BB 61 5B FF 39 ...` | 0.0032643483 | 0.0019539092 | partial_data |
| 0x00 | 16 | 52 | 51 | 13 | `84 C8 53 BB 3A 80 01 3B 3D E4 92 3B F9 2A FE 39 F2 F4 8F BB ...` | -0.0032315562 | 0.0019760267 | partial_data |
| 0x00 | 17 | 52 | 52 | 13 | `D9 E1 A5 BA 0E B3 C0 BB AF 56 04 BB B9 50 A4 3B 7C ED A2 3B ...` | -0.0012655809 | -0.00588072 | data |
| 0x00 | 18 | 52 | 52 | 13 | `8A 08 FB 3B 32 BD 93 3B E3 A1 B5 BB 17 76 02 BC B0 51 AE 3A ...` | 0.00766093 | 0.004508638 | data |
| 0x00 | 19 | 52 | 52 | 13 | `EB F7 14 BC 18 D9 C2 3B 76 F9 5A 3C E6 6F A6 3A 5C 5A 64 BC ...` | -0.009092311 | 0.0059462897 | data |
| 0x00 | 20 | 52 | 52 | 13 | `AA 21 CB BB B8 F0 EB BC 1A 94 26 BC 73 45 ED 3C C7 A1 FC 3C ...` | -0.0061990814 | -0.028801307 | data |
| 0x00 | 21 | 52 | 52 | 13 | `99 8D 9C BE 8C F8 AD BD 7F 3F 90 3D AD 0E 93 3D 13 35 29 BC ...` | -0.3057678 | -0.08494672 | data |
| 0x00 | 22 | 52 | 52 | 13 | `B9 D0 84 3C CF 42 22 BC 53 39 9E BC 96 89 B3 BA 90 77 86 3C ...` | 0.016212808 | -0.009903624 | data |
| 0x00 | 23 | 52 | 52 | 13 | `9E 91 3C 3B C3 BC 35 3C C3 60 56 3B 42 47 0C BC 18 7D FA BB ...` | 0.0028773318 | 0.011092368 | data |
| 0x00 | 24 | 52 | 52 | 13 | `F6 70 EF BB 77 8F 7B BB 2D D3 9E 3B 8A 47 CD 3B A8 7A 9E BA ...` | -0.007307167 | -0.0038385072 | data |
| 0x00 | 25 | 52 | 51 | 13 | `F9 E4 7E 3B FF 91 2A BB F6 59 A8 BB 20 30 9E B9 5D EC 9B 3B ...` | 0.0038893803 | -0.0026026962 | partial_data |
| 0x00 | 26 | 52 | 51 | 13 | `DF DD 91 3A 63 45 87 3B 64 9A 9A 3A 0B 48 5A BB 03 77 41 BB ...` | 0.0011128745 | 0.0041281446 | partial_data |
| 0x00 | 27 | 52 | 52 | 13 | `C8 1D 52 BB 2A 5A D8 BA C4 C0 0F 3B 3C EC 36 3B 84 AA 1B BA ...` | -0.0032061208 | -0.0016506363 | data |
| 0x00 | 28 | 52 | 52 | 13 | `BC FC F0 3A 0E 30 A9 BA 27 B5 22 BB 0C AF E3 B8 EB 6E 19 3B ...` | 0.0018385868 | -0.0012907998 | data |
| 0x00 | 29 | 52 | 52 | 13 | `DB 8E 1B 3A FA 03 09 3B 6E 0E 15 3A 0A 59 E0 BA 29 B5 C2 BA ...` | 5.934068E-4 | 0.0020906911 | data |
| 0x00 | 30 | 52 | 52 | 13 | `74 4C DA BA 72 4E 5A BA 8B 7C 97 3A 20 5A BC 3A 4B 50 AE B9 ...` | -0.0016654865 | -8.3277293E-4 | data |
| 0x00 | 31 | 52 | 52 | 13 | `06 0F 77 3A EE F0 34 BA A4 D0 A8 BA 8A 13 19 B8 E7 85 A0 3A ...` | 9.424541E-4 | -6.9023564E-4 | data |
| 0x00 | 32 | 52 | 52 | 13 | `80 6F A9 39 86 9F 8D 3A BA B6 91 39 B4 A0 69 BA 2D DA 45 BA ...` | 3.2317266E-4 | 0.0010804988 | data |
| 0x00 | 33 | 52 | 52 | 13 | `68 C4 5E BA 3F 9A D7 B9 63 E4 1B 3A 5D EA 3C 3A 07 A7 3C B9 ...` | -8.497895E-4 | -4.1122918E-4 | data |
| 0x00 | 34 | 52 | 52 | 13 | `49 97 F0 39 12 51 B7 B9 A2 A7 25 BA 07 B3 07 B7 F4 1F 1E 3A ...` | 4.5889083E-4 | -3.4964882E-4 | data |
| 0x00 | 35 | 52 | 52 | 13 | `9F 1B 2A 39 0C 00 07 3A B8 B1 02 39 BD 7C DF B9 33 50 B8 B9 ...` | 1.6222753E-4 | 5.1498483E-4 | data |
| 0x00 | 36 | 52 | 52 | 13 | `52 2F CD B9 B3 BC 3F B9 6D 4F 90 39 89 36 AA 39 BC 52 B6 B8 ...` | -3.9135903E-4 | -1.8285475E-4 | data |
| 0x00 | 37 | 52 | 52 | 13 | `0F BA 4F 39 F0 71 24 B9 30 E2 8F B9 D5 74 4D 35 35 A5 89 39 ...` | 1.981037E-4 | -1.5682704E-4 | data |
| 0x00 | 38 | 52 | 52 | 13 | `60 25 96 38 C1 D5 62 39 5E F5 4D 38 54 93 3C B9 3B 89 17 B9 ...` | 7.159519E-5 | 2.1632669E-4 | data |
| 0x00 | 39 | 52 | 20 | 5 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | partial_data |
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