@echo off
set SERVER_HOST=localhost
set SERVER_PORT=51234
set FILE_DIR=D:\clouddrive\OneDrive\Documentos\GitHub\COMPX234-A3\

for %%i in (1,2,3,4,5,6,7,8,9,10) do (
    echo Starting Client %%i...
    start "Client %%i" cmd /c java client %SERVER_HOST% %SERVER_PORT% %FILE_DIR%client_%%i.txt
)