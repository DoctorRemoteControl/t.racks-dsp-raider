# FIR408 Startup Rest Command Probe

- Read-only direct raw socket probe.

| Command | Payload | Meaning | Response |
| --- | --- | --- | --- |
| system-info-0x2C | `00 01 01 2C` | startup system/capability response | `01 00 08 2C 00 27 0F 00 00 00 00` |
| startup-zero-0x22 | `00 01 01 22` | reserved zero-filled startup block | `01 00 14 22 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00` |
| startup-status-0x14 | `00 01 01 14` | startup status byte | `01 00 02 14 00` |
| session-ready-0x12 | `00 01 01 12` | session ready/keepalive ACK | `01 00 01 01` |
