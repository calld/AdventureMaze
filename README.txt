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


