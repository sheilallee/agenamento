# Script para iniciar o backend e frontend
Write-Host "Iniciando o Sistema de Agendamento..." -ForegroundColor Green
Write-Host ""

# Iniciar o backend
Write-Host "Iniciando Backend (Quarkus)..." -ForegroundColor Cyan
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$PSScriptRoot\backend'; mvn quarkus:dev"

# Aguardar 5 segundos para o backend iniciar
Start-Sleep -Seconds 5

# Iniciar o frontend
Write-Host "Iniciando Frontend (Nuxt)..." -ForegroundColor Cyan
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$PSScriptRoot\frontend'; npm run dev"

Write-Host ""
Write-Host "Sistema iniciado!" -ForegroundColor Green
Write-Host "Backend: http://localhost:8080" -ForegroundColor Yellow
Write-Host "Frontend: http://localhost:3000" -ForegroundColor Yellow
Write-Host "API Docs: http://localhost:8080/swagger-ui" -ForegroundColor Yellow
