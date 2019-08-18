#!/usr/bin/awk -f
BEGIN{
	begin_codon = "ATG";
	end_codons["TAA"];
	end_codons["TGA"];
	end_codons["TAG"];
	search_on = "---";

}
{
	s = $0;

	while (i = index(s, begin_codon)) {
		for (j = i + 3; j < length; j += 3) {
			ss = substr(s, j, 3);
			if (ss in end_codons) {
				print substr(s, i, j + 3 - i);
				break;
			}
			
		}
		sub(begin_codon, search_on, s);

	}

}
