package com.mechonot.fiddle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mechonot.fiddle.fid.BodyType;
import com.mechonot.fiddle.fid.Fid;
import com.mechonot.fiddle.fid.FidType;

import java.time.LocalDate;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class DatabaseTester {
    private DBHandler db_handler;
    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db_handler = new DBHandler(context);
    }
    @Test
    public void database_creation() {
        Fid fid = new Fid(LocalDate.now(),1,2, FidType.Article, BodyType.TEXT,"desc","body",2,5);
        db_handler.add_new_fid(fid);
//        Fid fid2 = new Fid(1, LocalDate.now(),1,2, FidType.Article, BodyType.TEXT,"test",2,5);
//        db_handler.add_new_fid(fid2);
//        Fid fid3 = new Fid(2, LocalDate.now(),1,2, FidType.Article, BodyType.TEXT,"test",2,5);
//        db_handler.add_new_fid(fid3);
        System.out.print(db_handler.read_fids());
    }
}