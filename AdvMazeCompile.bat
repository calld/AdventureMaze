@echo off
title Adventure Maze Compile
javac -d classes -sourcepath src -Xlint src/ways/generator/MakeLevel.java
del tempManifest.txt
echo Main-class: ways.generator.MakeLevel> tempManifest.txt
jar cfm AdventureMaze.jar tempManifest.txt -C classes .
del tempManifest.txt