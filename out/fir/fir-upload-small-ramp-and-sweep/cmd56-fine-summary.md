# FIR408 0x56 Read-only Sweep

- Read-only probe. No DSP setting write commands are sent.
- Selectors: 0x00..0x00
- Offsets: 150..210 step 1
- Requests: 61
- Data rows: 1, zero rows: 37, partial rows: 2, FF rows: 21, no response rows: 0

| Selector | Offset | Bytes | Non-FF bytes | Non-FF slots | Prefix | Float[0] | Float[1] | Class |
| ---: | ---: | ---: | ---: | ---: | --- | ---: | ---: | --- |
| 0x00 | 150 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 151 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 152 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 153 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 154 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 155 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 156 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 157 | 52 | 24 | 6 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | partial_data |
| 0x00 | 158 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 159 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 160 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 161 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 162 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 163 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 164 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 165 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 166 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 167 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 168 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 169 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 170 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 171 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 172 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 173 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 174 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 175 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 176 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 177 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 80 3F 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | data |
| 0x00 | 178 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 179 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 180 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 181 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 182 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 183 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 184 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 185 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 186 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 187 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 188 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 189 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 190 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 191 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 192 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 193 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 194 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 195 | 52 | 52 | 13 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | zero_filled_data |
| 0x00 | 196 | 52 | 48 | 12 | `00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ...` | 0.0 | 0.0 | partial_data |
| 0x00 | 197 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 198 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 199 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 200 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 201 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 202 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 203 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 204 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 205 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 206 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 207 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 208 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 209 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |
| 0x00 | 210 | 52 | 0 | 0 | `FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF ...` |  |  | ff_empty_or_unavailable |