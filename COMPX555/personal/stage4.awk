{
    aseq = "";
    for(i=1; i < length($0); (i = i + 3)){
        seq = substr($0, i, 3);
        if(seq ~ /ATG/){
            aseq = aseq "M";
        } else if (seq ~ /TT(T|C)/){
            aseq = aseq "F";
        } else if (seq ~ /TT(A|G)/){
            aseq = aseq "L";
        } else if (seq ~ /TC./) {
            aseq = aseq "S";
        } else if (seq ~ /TA(T|C)/) {
            aseq = aseq "Y";
        } else if (seq ~ /TA./) {
            aseq = aseq "*";
        } else if (seq ~ /TG(T|C)/) {
            aseq = aseq "C";
        } else if (seq ~ /TGA/) {
            aseq = aseq "*";
        } else if (seq ~ /TGG/) {
            aseq = aseq "W";
        } else if (seq ~ /CT./) {
            aseq = aseq "L";
        } else if (seq ~ /CC./) {
            aseq = aseq "P";
        } else if (seq ~ /CA(T|C)/) {
            aseq = aseq "H";
        } else if (seq ~ /CA(A|G)/) {
            aseq = aseq "G";
        } else if (seq ~ /CG./) {
            aseq = aseq "R";
        } else if (seq ~ /AT(T|C|A)/) {
            aseq = aseq "I";
        } else if (seq ~ /AC./) {
            aseq = aseq "T";
        } else if (seq ~ /AA(T|C)/) {
            aseq = aseq "N";
        } else if (seq ~ /AA(A|G)/) {
            aseq = aseq "K";
        } else if (seq ~ /AG(T|C)/) {
            aseq = aseq "S";
        } else if (seq ~ /AG(A|G)/) {
            aseq = aseq "R";
        } else if (seq ~ /GT./) {
            aseq = aseq "V";
        } else if (seq ~ /GC./) {
            aseq = aseq "A";
        } else if (seq ~ /GA(T|C)/) {
            aseq = aseq "D";
        } else if (seq ~ /GA(A|G)/) {
            aseq = aseq "E";
        } else if (seq ~ /GG./) {
            aseq = aseq "G";
        }
        

    }
    print aseq;
}