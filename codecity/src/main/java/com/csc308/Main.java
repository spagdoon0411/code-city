package com.csc308;
public class Main {

    public static void main(String[] args)
    {
        FileParser fp = new FileParser("./dummy_classes");
        for(FileInfo fi : fp.getFileInfos()){
            System.out.println(fi.toString());
        }
        
    }
}
