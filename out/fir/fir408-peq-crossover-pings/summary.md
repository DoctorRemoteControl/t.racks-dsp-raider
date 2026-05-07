# FIR408 PEQ/Crossover Same-Value Pings

- Direct same-value probes generated from the current 0x27/0x24 config dump.
- Baseline and after-action config dumps are compared byte-for-byte.

| Test | Payload | Meaning | Response |
| --- | --- | --- | --- |
| InA-peq1-current | `00 01 0A 33 00 00 78 00 0B 00 23 00 00` | InA PEQ1 current gain/frequency/q/type with bypass off | `01 00 01 01` |
| InD-peq1-current | `00 01 0A 33 03 00 78 00 0B 00 23 00 00` | InD PEQ1 current gain/frequency/q/type with bypass off | `01 00 01 01` |
| Out1-peq1-current | `00 01 0A 33 04 00 78 00 1F 00 23 00 00` | Out1 PEQ1 current gain/frequency/q/type | `01 00 01 01` |
| Out8-peq1-current | `00 01 0A 33 0B 00 78 00 1F 00 23 00 00` | Out8 PEQ1 current gain/frequency/q/type | `01 00 01 01` |
| Out1-iir-highpass-current | `00 01 05 32 04 00 00 00` | Out1 IIR HighPass current raw/mode | `01 00 01 01` |
| Out1-iir-lowpass-current | `00 01 05 31 04 2C 01 00` | Out1 IIR LowPass current raw/mode | `01 00 01 01` |
| Out8-iir-highpass-current | `00 01 05 32 0B 00 00 00` | Out8 IIR HighPass current raw/mode | `01 00 01 01` |
| Out8-iir-lowpass-current | `00 01 05 31 0B 2C 01 00` | Out8 IIR LowPass current raw/mode | `01 00 01 01` |

## Config Compare

- Baseline: `.\out\fir\fir408-peq-crossover-pings\baseline`
- After: `.\out\fir\fir408-peq-crossover-pings\after`
- Byte-for-byte match: False
