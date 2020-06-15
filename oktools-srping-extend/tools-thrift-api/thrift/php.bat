REM thrift-0.13.0.exe -r -v -o ./php --gen php ./source/index.thrift
thrift-0.13.0.exe -r -v -o ./php --gen php:server ./source/index.thrift
REM thrift-0.13.0.exe -r -v -o ./php --gen php:classmap ./source/index.thrift
pause
