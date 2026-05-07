@echo off
setlocal

set BASE_DIR=%~dp0
set JAR=%BASE_DIR%dsp408-proxy-0.1.0-SNAPSHOT-all.jar
set LOG_DIR=%BASE_DIR%proxy_logs
set DSP_IP=192.168.0.166

if not exist "%JAR%" (
    echo JAR not found:
    echo %JAR%
    pause
    exit /b 1
)

if not exist "%LOG_DIR%" (
    mkdir "%LOG_DIR%"
)

echo Starting DSP408 Proxy...
echo JAR      : %JAR%
echo DSP-IP   : %DSP_IP%
echo LOG-DIR  : %LOG_DIR%
echo.

java -jar "%JAR%" ^
  --listen-host 127.0.0.1 ^
  --listen-port 9761 ^
  --target-host %DSP_IP% ^
  --target-port 9761 ^
  --log-dir "%LOG_DIR%" ^
  --stream-host 127.0.0.1 ^
  --stream-port 19081 ^
  --control-host 127.0.0.1 ^
  --control-port 19082

echo.
echo Proxy stopped.
pause
