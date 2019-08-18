{    
    startFound = 0;
    for(i=0;i<length($0);i++) {
        if(i%3 ==0){ 
            if(substr($0, i-2, 3) == "ATG"){
                start = i - 2;
                startFound = 1;
            }
            if ((startFound == 1) && (substr($0,i-2,3)=="TAG" || substr($0,i-2,3)=="TAA" || substr($0,i-2,3)=="TGA")) {
                print substr($0,start,(i - start)+1); 
                $0=substr($0,i+1);
                start = 0;
                startFound = 0;
            }
        }
    }
}