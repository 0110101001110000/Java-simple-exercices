@echo off
chcp 65001 >nul
cls

setlocal enabledelayedexpansion

set "bar="
for /l %%i in (1,1,10) do (
    cls
    set /a percent=%%i*10
    set "bar="
    
    for /l %%j in (1,1,%%i) do (
        set "bar=!bar!█"
    )
    
    for /l %%k in (%%i,1,9) do (
        set "bar=!bar!▒"
    )
    
    echo Carregando: !bar! !percent!%%
    timeout /t 1 >nul
)

cls
echo Carregamento completo: ██████████ 100%%
timeout /t 2 >nul
