Adventure Maze Generator for table top RPGs
By: Devon Call

Current working main in src/ways/generator/MakeLevel.java
uses commandline i/o:
start up options:
load file_name: load existing save file.
new num: generate new world of size num.

use options:
j[ump] num
	displays location indexed by num
example:
j 10
jump 2345

f[ind] name
	displays game element of given name
example:
f Passage 1000
find Holy Saphire

n[ote] note_string
	adds a note to the current element
example:
n Dragon defeated here
note Heros insulted this village

r[emove] num
	deletes note of index num
example:
r 1
remove 4

s[ave] file_name
	creates a save file. I've been using .ams extention for "[A]dventure [M]aze [S]ave", though the file is text based save
example:
s mysave.ams
save othersave.ams

change
	recomputes passage destinations

tofile file_name
	generate human readable text file of the world.
example:
tofile reference.txt

<EOF> ends the program


Copy of Example CommandLine Use:

new 100
Map Size: 100
1: Warm Forest:
This is a Warm Forest.
Notes:
1: No world connection
Contains: General Anesthetic(3), Sealing Silver(3)
Season: Summer
Length: 200.00 ft Width: 200.00 ft
Exits:
	Direction	Location		Passage
	North		to 96: Cloudy Archipelago	Passage 2 (diff: 2)
	East		to 100: Cool Mountain	Passage 1 (diff: 4)
	South		to 2: Frozen Desert	Passage 3 (diff: 1)
	West		to 3: Hot Desert		Passage 4 (diff: 0)
Current Groups: </none>

j 3
3: Hot Desert:
This is a Hot Desert.
Notes:
1: No world connection
Contains: Memory Alter(4), Temp Stat Pool (might)(3)
Season: Summer
Length: 200.00 ft Width: 200.00 ft
Exits:
	Direction	Location		Passage
	North		to 8: Cold Swamp		Passage 11 (diff: 5)
	East		to 100: Cool Mountain	Passage 10 (diff: 4)
	South		to 2: Frozen Desert	Passage 9 (diff: 11)
	West		to 10: Warm Archipelago	Passage 12 (diff: 0)
Current Groups: </none>

n hello world
3: Hot Desert:
This is a Hot Desert.
Notes:
1: No world connection
2: hello world
Contains: Memory Alter(4), Temp Stat Pool (might)(3)
Season: Summer
Length: 200.00 ft Width: 200.00 ft
Exits:
	Direction	Location		Passage
	North		to 8: Cold Swamp		Passage 11 (diff: 5)
	East		to 100: Cool Mountain	Passage 10 (diff: 4)
	South		to 2: Frozen Desert	Passage 9 (diff: 11)
	West		to 10: Warm Archipelago	Passage 12 (diff: 0)
Current Groups: </none>

r 2
3: Hot Desert:
This is a Hot Desert.
Notes:
1: No world connection
Contains: Memory Alter(4), Temp Stat Pool (might)(3)
Season: Summer
Length: 200.00 ft Width: 200.00 ft
Exits:
	Direction	Location		Passage
	North		to 8: Cold Swamp		Passage 11 (diff: 5)
	East		to 100: Cool Mountain	Passage 10 (diff: 4)
	South		to 2: Frozen Desert	Passage 9 (diff: 11)
	West		to 10: Warm Archipelago	Passage 12 (diff: 0)
Current Groups: </none>

f Memory Alter
Element not found
3: Hot Desert:
This is a Hot Desert.
Notes:
1: No world connection
Contains: Memory Alter(4), Temp Stat Pool (might)(3)
Season: Summer
Length: 200.00 ft Width: 200.00 ft
Exits:
	Direction	Location		Passage
	North		to 8: Cold Swamp		Passage 11 (diff: 5)
	East		to 100: Cool Mountain	Passage 10 (diff: 4)
	South		to 2: Frozen Desert	Passage 9 (diff: 11)
	West		to 10: Warm Archipelago	Passage 12 (diff: 0)
Current Groups: </none>

f Memory Alter(4)
Memory Alter(4):
Level: 4
This material effects memory
Notes:</none>

j 3
3: Hot Desert:
This is a Hot Desert.
Notes:
1: No world connection
Contains: Memory Alter(4), Temp Stat Pool (might)(3)
Season: Summer
Length: 200.00 ft Width: 200.00 ft
Exits:
	Direction	Location		Passage
	North		to 8: Cold Swamp		Passage 11 (diff: 5)
	East		to 100: Cool Mountain	Passage 10 (diff: 4)
	South		to 2: Frozen Desert	Passage 9 (diff: 11)
	West		to 10: Warm Archipelago	Passage 12 (diff: 0)
Current Groups: </none>

j 1
1: Warm Forest:
This is a Warm Forest.
Notes:
1: No world connection
Contains: General Anesthetic(3), Sealing Silver(3)
Season: Summer
Length: 200.00 ft Width: 200.00 ft
Exits:
	Direction	Location		Passage
	North		to 96: Cloudy Archipelago	Passage 2 (diff: 2)
	East		to 100: Cool Mountain	Passage 1 (diff: 4)
	South		to 2: Frozen Desert	Passage 3 (diff: 1)
	West		to 3: Hot Desert		Passage 4 (diff: 0)
Current Groups: </none>

j 2
2: Frozen Desert:
This is a Frozen Desert.
Notes:
1: connects to unsettled land
Contains: Temp Stat Edge (speed)(4)
Season: Winter
Length: 200.00 ft Width: 200.00 ft
Exits:
	Direction	Location		Passage
	North		to 5: Warm Hills		Passage 8 (diff: 7)
	East		to 4: Cool Archipelago	Passage 7 (diff: 4)
	South		to 98: Sunny Swamp	Passage 6 (diff: 0)
	West		to 1: Warm Forest	Passage 5 (diff: 6)
Current Groups: </none>

j 3
3: Hot Desert:
This is a Hot Desert.
Notes:
1: No world connection
Contains: Memory Alter(4), Temp Stat Pool (might)(3)
Season: Summer
Length: 200.00 ft Width: 200.00 ft
Exits:
	Direction	Location		Passage
	North		to 8: Cold Swamp		Passage 11 (diff: 5)
	East		to 100: Cool Mountain	Passage 10 (diff: 4)
	South		to 2: Frozen Desert	Passage 9 (diff: 11)
	West		to 10: Warm Archipelago	Passage 12 (diff: 0)
Current Groups: </none>

change
3: Hot Desert:
This is a Hot Desert.
Notes:
1: No world connection
Contains: Memory Alter(4), Temp Stat Pool (might)(3)
Season: Summer
Length: 200.00 ft Width: 200.00 ft
Exits:
	Direction	Location		Passage
	North		to 6: Cold Mountain	Passage 11 (diff: 5)
	East		to 100: Cool Mountain	Passage 10 (diff: 4)
	South		to 2: Frozen Desert	Passage 9 (diff: 11)
	West		to 4: Cool Archipelago	Passage 12 (diff: 0)
Current Groups: </none>

j 1
1: Warm Forest:
This is a Warm Forest.
Notes:
1: No world connection
Contains: General Anesthetic(3), Sealing Silver(3)
Season: Summer
Length: 200.00 ft Width: 200.00 ft
Exits:
	Direction	Location		Passage
	North		to 6: Cold Mountain	Passage 2 (diff: 2)
	East		to 98: Sunny Swamp	Passage 1 (diff: 4)
	South		to 100: Cool Mountain	Passage 3 (diff: 1)
	West		to 3: Hot Desert		Passage 4 (diff: 0)
Current Groups: </none>

j 2
2: Frozen Desert:
This is a Frozen Desert.
Notes:
1: connects to unsettled land
Contains: Temp Stat Edge (speed)(4)
Season: Winter
Length: 200.00 ft Width: 200.00 ft
Exits:
	Direction	Location		Passage
	North		to 97: Warm Archipelago	Passage 8 (diff: 7)
	East		to 4: Cool Archipelago	Passage 7 (diff: 4)
	South		to 1: Warm Forest	Passage 6 (diff: 0)
	West		to 5: Warm Hills		Passage 5 (diff: 6)
Current Groups: </none>

j 3
3: Hot Desert:
This is a Hot Desert.
Notes:
1: No world connection
Contains: Memory Alter(4), Temp Stat Pool (might)(3)
Season: Summer
Length: 200.00 ft Width: 200.00 ft
Exits:
	Direction	Location		Passage
	North		to 6: Cold Mountain	Passage 11 (diff: 5)
	East		to 100: Cool Mountain	Passage 10 (diff: 4)
	South		to 2: Frozen Desert	Passage 9 (diff: 11)
	West		to 4: Cool Archipelago	Passage 12 (diff: 0)
Current Groups: </none>