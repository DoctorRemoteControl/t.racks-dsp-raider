# FIR408 DSPD Static Decode

- Assembled data bytes: 1556
- Method: DSPD `ReadBlockSet` static decode
- No GUI capture required

## Inputs

| Input | Label | Gain | Phase | Delay | Gate A/R/H/T | PEQ1 |
| --- | --- | ---: | ---: | ---: | --- | --- |
| InA | InA | +0.0dB | 0 | 0.00ms | 0/99/99/0 | 25.4Hz q=3.01 g=+0.0dB type=Peak |
| InB | InB | +0.0dB | 0 | 0.00ms | 0/99/99/0 | 25.4Hz q=3.01 g=+0.0dB type=Peak |
| InC | InC | +0.0dB | 0 | 0.00ms | 0/99/99/0 | 25.4Hz q=3.01 g=+0.0dB type=Peak |
| InD | InD | +0.0dB | 0 | 0.00ms | 0/99/99/0 | 25.4Hz q=3.01 g=+0.0dB type=Peak |

## Outputs

| Output | Label | Gain | Phase | Delay | Matrix | FIR | PEQ1 |
| --- | --- | ---: | ---: | ---: | --- | --- | --- |
| Out1 | Out1 | +0.0dB | 0 | 0.00ms | route=0x01 gains=280/280/280/280 | HIGH PASS/HAMMING hp=19.70KHz lp=20.16KHz taps=544 | 40.3Hz q=3.01 g=+0.0dB type=Peak |
| Out2 | Out2 | +0.0dB | 0 | 0.00ms | route=0x02 gains=280/280/280/280 | BYPASS/SINC hp=250.1Hz lp=20.16KHz taps=512 | 40.3Hz q=3.01 g=+0.0dB type=Peak |
| Out3 | Out3 | +0.0dB | 0 | 0.00ms | route=0x04 gains=280/280/280/280 | BYPASS/SINC hp=250.1Hz lp=20.16KHz taps=512 | 40.3Hz q=3.01 g=+0.0dB type=Peak |
| Out4 | Out4 | +0.0dB | 0 | 0.00ms | route=0x08 gains=280/280/280/280 | BYPASS/SINC hp=250.1Hz lp=20.16KHz taps=512 | 40.3Hz q=3.01 g=+0.0dB type=Peak |
| Out5 | Out5 | +4.0dB | 0 | 0.00ms | route=0x01 gains=280/280/280/280 | BYPASS/SINC hp=250.1Hz lp=20.16KHz taps=512 | 24.8Hz q=0.71 g=-3.0dB type=Peak |
| Out6 | Out6 | +0.0dB | 0 | 0.00ms | route=0x02 gains=280/280/280/280 | BYPASS/SINC hp=250.1Hz lp=20.16KHz taps=512 | 40.3Hz q=3.01 g=+0.0dB type=Peak |
| Out7 | Out7 | +0.0dB | 0 | 0.00ms | route=0x04 gains=280/280/280/280 | BYPASS/SINC hp=250.1Hz lp=20.16KHz taps=512 | 40.3Hz q=3.01 g=+0.0dB type=Peak |
| Out8 | Out8 | +0.0dB | 0 | 0.00ms | route=0x08 gains=280/280/280/280 | BYPASS/SINC hp=250.1Hz lp=20.16KHz taps=512 | 40.3Hz q=3.01 g=+0.0dB type=Peak |

## Notes

- Crossover active/remembered mode bytes are decoded statically but not written by the safe ping suite.
- Mute bytes are not decoded here yet; previous mask guesses were intentionally removed.
- Compressor/Limiter extended fields are intentionally excluded from the first DSPD safe ping pass.