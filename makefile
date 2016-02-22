STRUCTURE = classes/ways/structure/Allied.class classes/ways/structure/Bag.class classes/ways/structure/BasicItem.class classes/ways/structure/Box.class classes/ways/structure/Character.class classes/ways/structure/Container.class classes/ways/structure/ContainerObj.class classes/ways/structure/Demeanor.class classes/ways/structure/Direction.class classes/ways/structure/Element.class classes/ways/structure/ElementObj.class classes/ways/structure/Faction.class classes/ways/structure/FactionObj.class classes/ways/structure/Group.class classes/ways/structure/Item.class classes/ways/structure/Location.class classes/ways/structure/LocationObj.class classes/ways/structure/Passage.class classes/ways/structure/PassageObj.class
CC = javac
FLAGS = -d classes -sourcepath src -Xlint

commandline: classes/ways/generator/*.class classes/ways/structure/*.class classes/ways/structure/cypher/*.class classes/ways/generator/cypher/*.class
	java -cp classes ways.generator.MakeLevel

structure: classes/ways/structure/%.class

generator: classes/ways/generator/%.class

gency: classes/ways/generator/cypher/%.class

strcy: class/ways/structure/cypher/%.class

classes/%.class: src/%.java
	$(CC) $(FLAGS) $<

clean:
	rm -f -r classes/*
