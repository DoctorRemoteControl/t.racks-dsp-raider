# FIR408 Safe Write Pings

| Test | Payload | Meaning | Response |
| --- | --- | --- | --- |
| out1-gain-current-0db | `00 01 04 34 04 18 01` | Out1 gain raw 0x0118 = +0.0dB | `01 00 01 01` |
| out1-mute-current-normal | `00 01 03 35 04 00` | Out1 mute state Normal/unmuted | `01 00 01 01` |
| out1-phase-current-normal | `00 01 03 36 04 00` | Out1 phase 0 degrees | `01 00 01 01` |
| out1-delay-current-zero | `00 01 04 38 04 00 00` | Out1 delay raw 0 = 0.0ms | `01 00 01 01` |
| out1-peq1-current | `00 01 0A 33 04 00 78 00 1F 00 23 00 00` | Out1 PEQ1 Peak, 40.3Hz, Q 3.00, +0.0dB | `01 00 01 01` |
| out1-fir-generator-current | `00 01 0A 4B 04 02 03 2B 01 2C 01 09 00` | Out1 FIR generator HIGH PASS/HAMMING/19.70KHz/20.16KHz/544 | `01 00 01 01` |
| out1-channel-name-current | `00 01 0A 3D 04 4F 75 74 31 00 00 00 00` | Out1 channel name bytes for 'Out1' | `01 00 01 01` |
| out1-iir-highpass-current | `00 01 05 32 04 00 00 00` | Out1 IIR HighPass current raw 0 / bypass mode 0x00 | `01 00 01 01` |
| out1-iir-lowpass-current | `00 01 05 31 04 2C 01 00` | Out1 IIR LowPass current raw 300 / bypass mode 0x00 | `01 00 01 01` |
| out1-matrix-route-current-ina | `00 01 03 3A 04 01` | Out1 matrix route current InA mask 0x01 | `01 00 01 01` |
| out1-matrix-gain-ina-current-0db | `00 01 05 41 04 00 18 01` | Out1 <- InA matrix gain current raw 0x0118 = +0.0dB | `01 00 01 01` |
| ina-gate-current | `00 01 0A 3E 00 00 00 63 00 63 00 00 00` | InA gate current attack 0, release 99, hold 99, threshold 0 | `01 00 01 01` |
| out1-compressor-current-fir408-extended | `00 01 10 30 04 00 00 31 00 F3 01 00 00 DC 00 AA 00 23 00` | Out1 compressor/dynamics FIR408 extended current bytes restored with no config diff | `01 00 01 01` |
| out1-limiter-current-fir408-extended | `00 01 0C 3F 04 0F 00 31 00 F3 01 00 00 DC 00` | Out1 limiter/dynamics FIR408 extended current bytes restored with no config diff | `01 00 01 01` |
| test-tone-current-analog | `00 01 03 39 00 00` | Test tone current ANALOG INPUT, sine selector raw 0 | `01 00 01 01` |
