@echo off
rem set MAVEN_OPTS="-Xmx4096m"
rem set MAVEN_OPTS="-Xmx8192m"
set JAVA_OPTS="-Xms256m -Xmx4096m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=1024m -XX:NewSize=128m -XX:MaxNewSize=1024m -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:SurvivorRatio=4 -XX:TargetSurvivorRatio=90 -XX:+DisableExplicitGC -XX:+HeapDumpOnOutOfMemoryError -Xloggc:gc.log"
