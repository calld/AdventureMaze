@echo off
title Adventure Maze Compile
javac -d classes -sourcepath src -Xlint src/ways/generator/MakeLevel.java
rem del tempManifest.txt
rem echo Main-class: ways.generator.MakeLevel> tempManifest.txt
rem jar cfmv AdventureMaze.jar tempManifest.txt -C classes .
rem del tempManifest.txt
pause