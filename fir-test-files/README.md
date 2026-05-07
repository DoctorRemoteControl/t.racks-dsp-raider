FIR408 import test files

Use these as external FIR files in the Track DSP FIR408 GUI.
They are deliberately simple so captured bytes can be mapped back to known coefficients.

- fir_512_impulse_first.txt: coefficient 0 = 1.0, all others 0.0
- fir_512_impulse_center.txt: coefficient 255 = 1.0, all others 0.0
- fir_512_two_tap_1_05.txt: coefficient 0 = 1.0, coefficient 1 = 0.5, all others 0.0
- fir_512_small_ramp.txt: coefficients 0.000001 .. 0.000512

Format: ASCII text, one decimal coefficient per line, dot as decimal separator, 512 lines.
