#!/usr/bin/awk -f
BEGIN{
	repl["A"] = "T";
	repl["T"] = "A";
	repl["G"] = "C";
	repl["C"] = "G";
}
{
	print $0;
	for (i = length; i!=0; i--) {
		printf(repl[substr($0, i, 1)]);
	}
	printf("\n")
}
