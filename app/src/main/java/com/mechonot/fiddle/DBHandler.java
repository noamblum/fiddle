package com.mechonot.fiddle;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "fiddle";

    // below int is our database version
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "fids";

    private static final String ID_COL = "id";

    private static final String CREATION_DATE_COL = "creation_date";

    private static final String DURATION_COL = "duration";

    private static final String BODY_COL = "body";

    private static final String IS_LOCAL_COL = "is_local";

    private static final String DEAD_LINE_COL = "dead_line";

    private static final String PRIORITY_COL = "priority";

    private static final String INTERVAL_COL = "interval";

    private static final String NUM_INTERVALS_COL = "num_intervals";


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CREATION_DATE_COL + " TEXT,"
                + DURATION_COL + " INTEGER,"
                + BODY_COL + " TEXT,"
                + IS_LOCAL_COL + " INTEGER,"
                + DEAD_LINE_COL + " TEXT,"
                + PRIORITY_COL + " INTEGER,"
                + INTERVAL_COL + " INTEGER,"
                + NUM_INTERVALS_COL + " INTEGER)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewCourse(Integer id,
                             String create_date,
                             Integer duration,
                             String body,
                             Integer is_local,
                             String dead_line,
                             Integer priority_col,
                             Integer interval,
                             Integer num_intervals) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(ID_COL, id);
        values.put(CREATION_DATE_COL, create_date);
        values.put(DURATION_COL, duration);
        values.put(BODY_COL, body);
        values.put(IS_LOCAL_COL, is_local);
        values.put(DEAD_LINE_COL, dead_line);
        values.put(PRIORITY_COL, priority_col);
        values.put(NUM_INTERVALS_COL, num_intervals);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

