package com.mechonot.fiddle.fid;

import android.content.Context;

import com.mechonot.fiddle.DBHandler;

import androidx.test.core.app.ApplicationProvider;

public class FidIdGenerator {
    static int nextId;
    static int getNextId(){
        return nextId++;
    }
    static {
        Context context = ApplicationProvider.getApplicationContext(); //TODO change context to screen?
        DBHandler db_handler = new DBHandler(context);
        nextId = db_handler.get_max_id();
    }
}
