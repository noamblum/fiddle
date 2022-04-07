package com.mechonot.fiddle.fid;

public class FidIdGenerator {
    static int nextId = 0;
    static int getNextId(){
        return nextId++;
    }
}
