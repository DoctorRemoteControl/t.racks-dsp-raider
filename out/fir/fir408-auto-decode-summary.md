# FIR408 Automatic Config Decode

- Blocks: out\fir\fir-read-config-allblocks
- Reference library: DspLib-408.json
- Assembled data bytes: 1556
- Method: static config-read decode. No GUI capture required.

## Channels

| Channel | Label | Record offset |
| --- | --- | ---: |
| InA | InA | 16 |
| InB | InB | 154 |
| InC | InC | 292 |
| InD | InD | 430 |
| Out1 | Out1 | 568 |
| Out2 | Out2 | 676 |
| Out3 | Out3 | 784 |
| Out4 | Out4 | 892 |
| Out5 | Out5 | 1000 |
| Out6 | Out6 | 1108 |
| Out7 | Out7 | 1216 |
| Out8 | Out8 | 1324 |

## Output Summary

| Output | Gain | IIR HP | IIR LP | FIR Type | FIR Win | FIR HP | FIR LP | Taps |
| --- | ---: | ---: | ---: | --- | --- | ---: | ---: | ---: |
| Out1 | +0.0dB | 20.16KHz | 19.7Hz | BYPASS | SINC | 250.1Hz | 20.16KHz | 512 |
| Out2 | +0.0dB | 20.16KHz | 19.7Hz | BYPASS | SINC | 250.1Hz | 20.16KHz | 512 |
| Out3 | +0.0dB | 20.16KHz | 19.7Hz | BYPASS | SINC | 250.1Hz | 20.16KHz | 512 |
| Out4 | +0.0dB | 20.16KHz | 19.7Hz | BYPASS | SINC | 250.1Hz | 20.16KHz | 512 |
| Out5 | +0.0dB | 20.16KHz | 19.7Hz | BYPASS | SINC | 250.1Hz | 20.16KHz | 512 |
| Out6 | +0.0dB | 20.16KHz | 19.7Hz | BYPASS | SINC | 250.1Hz | 20.16KHz | 512 |
| Out7 | +0.0dB | 20.16KHz | 19.7Hz | BYPASS | SINC | 250.1Hz | 20.16KHz | 512 |
| Out8 | +0.0dB | 20.16KHz | 19.7Hz | BYPASS | SINC | 250.1Hz | 20.16KHz | 512 |

## Out1 Detail

- Label: Out1
- Output gain: +0.0dB raw 280
- FIR generator: Type BYPASS raw 0, Win SINC raw 6, HP 250.1Hz raw 110, LP 20.16KHz raw 300, Taps 512 raw 8

| PEQ | Frequency | Q | Gain | Type | Raw |
| --- | ---: | ---: | ---: | --- | --- |
| PEQ1 | 40.3Hz | 3.01 | +0.0dB | Peak | g=120 f=31 q=35 t=0 |
| PEQ2 | 84.4Hz | 3.01 | +0.0dB | Peak | g=120 f=63 q=35 t=0 |
| PEQ3 | 176.9Hz | 3.01 | +0.0dB | Peak | g=120 f=95 q=35 t=0 |
| PEQ4 | 370.4Hz | 3.01 | +0.0dB | Peak | g=120 f=127 q=35 t=0 |
| PEQ5 | 758.1Hz | 3.01 | +0.0dB | Peak | g=120 f=158 q=35 t=0 |
| PEQ6 | 1.59KHz | 3.01 | +0.0dB | Peak | g=120 f=190 q=35 t=0 |
| PEQ7 | 3.33KHz | 3.01 | +0.0dB | Peak | g=120 f=222 q=35 t=0 |
| PEQ8 | 6.81KHz | 3.01 | +0.0dB | Peak | g=120 f=253 q=35 t=0 |
| PEQ9 | 14.26KHz | 3.01 | +0.0dB | Peak | g=120 f=285 q=35 t=0 |

## FIR File Name Table

| Channel | Offset | Name bytes as ASCII |
| --- | ---: | --- |
| InA | 1460 | InAFile |
| InB | 1468 | InBFile |
| InC | 1476 | InCFile |
| InD | 1484 | InDFile |
| Out1 | 1492 | Out1File |
| Out2 | 1500 | Out2File |
| Out3 | 1508 | Out3File |
| Out4 | 1516 | Out4File |
| Out5 | 1524 | Out5File |
| Out6 | 1532 | Out6File |
| Out7 | 1540 | Out7File |
| Out8 | 1548 | Out8File |

## Limits

- This decodes values that are present in the FIR408 0x27/0x24 config blocks.
- Live/write-only state still needs targeted writes or GUI captures.
- FIR 0x4C IIR/FIR processing mode was not observed in the config dump; it remains decoded from live command captures.
