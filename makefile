CC = javac
FLAGS = -d classes -sourcepath src -Xlint

commandline: classes/ways/generator/MakeLevel.class
	java -cp classes ways.generator.MakeLevel

structure: classes/ways/structure/%.class

generator: classes/ways/generator/%.class

gency: classes/ways/generator/cypher/%.class

strcy: class/ways/structure/cypher/%.class

classes/%.class: src/%.java
	$(CC) $(FLAGS) $<

clean:
	rm -f -r -d classes/*.class
