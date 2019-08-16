{    
	for(i=length;i!=0;i--)
		x=x substr($0,i,1);
    
    print $0;
    gsub("A", "_", x);
    gsub("T", "~", x);
    gsub("G", "@", x);
    gsub("C", "^", x);
    gsub("_", "T", x);
    gsub("~", "A", x);
    gsub("@", "C", x);
    gsub("\^", "G", x);
    
    print x;
}

END {
	
	
}