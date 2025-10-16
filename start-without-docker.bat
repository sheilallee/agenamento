@echo off
echo Iniciando projeto sem Docker...
echo.

echo 1. Iniciando backend Quarkus...
start "Backend" cmd /k "cd /d backend && mvn quarkus:dev"

echo 2. Aguardando 10 segundos...
timeout /t 10 /nobreak >nul

echo 3. Iniciando frontend Nuxt...
start "Frontend" cmd /k "cd /d frontend && npm run dev"

echo.
echo Projeto iniciado!
echo Backend: http://localhost:8080
echo Frontend: http://localhost:3000
echo Swagger: http://localhost:8080/swagger-ui
echo Metricas: http://localhost:8080/q/metrics
echo.
pause