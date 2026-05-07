# FIR408 Automatic Config Decode

- Blocks: out\fir\fir-read-config-direct-after-extended-pings
- Reference library: DspLib-408-fir.json
- Assembled data bytes: 1544
- Method: static config-read decode. No GUI capture required.

## Channels

| Channel | Label | Record offset |
| --- | --- | ---: |
| InA | InA | 16 |
| InB | c1 | 154 |
| InC | c1 | 292 |
| InD | c1 | 430 |
| Out1 |  | 568 |
| Out2 |  | 676 |
| Out3 |  | 784 |
| Out4 |  | 892 |
| Out5 |  | 1000 |
| Out6 |  | 1108 |
| Out7 |  | 1216 |
| Out8 |  | 1324 |

## Output Summary

| Output | Gain | IIR HP | IIR LP | FIR Type | FIR Win | FIR HP | FIR LP | Taps |
| --- | ---: | ---: | ---: | --- | --- | ---: | ---: | ---: |
| Out1 | +2.0dB | 20.16KHz | 24.3Hz | type#35 | window#0 | 397.0Hz | 84.4Hz | 8768 |
| Out2 | +2.0dB | 20.16KHz | 23.7Hz | type#35 | window#0 | 315.1Hz | 84.4Hz | 1376 |
| Out3 | +2.0dB | 20.16KHz | 23.7Hz | type#35 | window#0 | 315.1Hz | 84.4Hz | 1376 |
| Out4 | +2.0dB | 20.16KHz | 23.7Hz | type#35 | window#0 | 315.1Hz | 84.4Hz | 1376 |
| Out5 | +2.0dB | 20.16KHz | 23.7Hz | type#35 | window#0 | 315.1Hz | 84.4Hz | 1376 |
| Out6 | +2.0dB | 20.16KHz | 23.7Hz | type#35 | window#0 | 315.1Hz | 84.4Hz | 1376 |
| Out7 | +2.0dB | 20.16KHz | 23.7Hz | type#35 | window#0 | 315.1Hz | 84.4Hz | 1376 |
| Out8 | +2.0dB | 20.16KHz | 23.7Hz | type#35 | window#0 | 315.1Hz | 84.4Hz | 1376 |

## Out1 Detail

- Label: 
- Output gain: +2.0dB raw 300
- FIR generator: Type type#35 raw 35, Win window#0 raw 0, HP 397.0Hz raw 130, LP 84.4Hz raw 63, Taps 8768 raw 266

| PEQ | Frequency | Q | Gain | Type | Raw |
| --- | ---: | ---: | ---: | --- | --- |
| PEQ1 | 176.9Hz | 0.71 | +2.0dB | High Shelf | g=140 f=95 q=10 t=2 |
| PEQ2 | 370.4Hz | 0.71 | +3.0dB | LP -6dB | g=150 f=127 q=10 t=3 |
| PEQ3 | 758.1Hz | 0.71 | +4.0dB | LP -12dB | g=160 f=158 q=10 t=4 |
| PEQ4 | 1.59KHz | 0.71 | +5.0dB | HP -6dB | g=170 f=190 q=10 t=5 |
| PEQ5 | 3.33KHz | 0.71 | +6.0dB | HP -12dB | g=180 f=222 q=10 t=6 |
| PEQ6 | 6.81KHz | 3.01 | +7.0dB | All Pass 1 | g=190 f=253 q=35 t=7 |
| PEQ7 | 14.26KHz | 3.01 | +8.0dB | All Pass 2 | g=200 f=285 q=35 t=8 |
| PEQ8 | 61.1Hz | 489299.12 | -10.5dB | Low Shelf | g=15 f=49 q=243 t=1 |
| PEQ9 | 3.18KHz | 1.60 | -12.0dB | Low Shelf | g=0 f=220 q=24 t=1 |

## FIR File Name Table

| Channel | Offset | Name bytes as ASCII |
| --- | ---: | --- |
| InA | 1460 | 512_InCF |
| InB | 1468 | ileInDF |
| InC | 1476 | ileOut1 |
| InD | 1484 | FileOut2 |
| Out1 | 1492 | FileOut3 |
| Out2 | 1500 | FileOut4 |
| Out3 | 1508 | FileOut5 |
| Out4 | 1516 | FileOut6 |
| Out5 | 1524 | FileOut7 |
| Out6 | 1532 | FileOut8 |
| Out7 | 1540 | File |
| Out8 | 1548 |  |

## Limits

- This decodes values that are present in the FIR408 0x27/0x24 config blocks.
- Live/write-only state still needs targeted writes or GUI captures.
- FIR 0x4C IIR/FIR processing mode was not observed in the config dump; it remains decoded from live command captures.
