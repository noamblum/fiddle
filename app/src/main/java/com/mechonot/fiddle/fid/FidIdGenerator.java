package com.mechonot.fiddle.fid;

public class FidIdGenerator {
    static long nextId = 0;
    static long getNextId(){
        return nextId++;
    }
}
