@echo off

if [%1]==[] goto usage
rem java -Xmx8192m -Xss128m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=1024m -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC -cp .;target\NamesPermutations-1.0-SNAPSHOT.jar;lib\streamplify-1.1.0.jar;lib\slf4j-api-1.7.25.jar -Dfile=%1 permutations.ArrangementsStreamplify
java -Xms256m -Xmx4096m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=1024m -XX:NewSize=128m -XX:MaxNewSize=1024m -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:SurvivorRatio=4 -XX:TargetSurvivorRatio=90 -XX:+DisableExplicitGC -XX:+HeapDumpOnOutOfMemoryError -Xloggc:gc.log -cp .;target\NamesPermutations-1.0-SNAPSHOT.jar;lib\streamplify-1.1.0.jar;lib\slf4j-api-1.7.25.jar -Dfile=%1 permutations.ArrangementsStreamplify

goto :eof
:usage
@echo Usage: %0 ^<filename^>
exit /B 1
