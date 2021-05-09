call :CdToGitRoot

call :ApplyToDir %~1 .idea/runConfigurations
git update-index %~1 src/main/resources/application.properties

exit %ERRORLEVEL%

:: Procedures ::

:CdToGitRoot
@echo off
FOR /F "tokens=* USEBACKQ" %%F IN (`git rev-parse --show-cdup`) DO (
   cd %%F
)
@echo on
exit /b 0

:ApplyToDir
@echo off
set previousDir=%CD%
cd %2
@echo on

FOR /F "tokens=* USEBACKQ" %%F IN (`git ls-files`) DO (
   git update-index %~1 %%F
)

@echo off
cd %previousDir%
@echo on

exit /b 0
