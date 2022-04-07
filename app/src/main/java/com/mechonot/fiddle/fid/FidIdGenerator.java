package com.mechonot.fiddle.fid;

public class FidIdGenerator {
    static int nextId;
    static int getNextId(){
        return nextId++;
    }
    public static void init_next(int ind){
        nextId = ind + 1;
    }
}
