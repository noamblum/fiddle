package com.mechonot.fiddle.Fid;

public class FidIdGenerator {
    static long nextId = 0;
    static long getNextId(){
        return nextId++;
    }
}
