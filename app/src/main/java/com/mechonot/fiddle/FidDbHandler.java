package com.mechonot.fiddle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mechonot.fiddle.fid.Fid;
import com.mechonot.fiddle.fid.FidFactory;
import com.mechonot.fiddle.fid.FidType;
import com.mechonot.fiddle.fid.BodyType;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class FidDbHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "fiddle";

    // below int is our database version
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "fids";

    private static final String ID_COL = "id";

    private static final String CREATION_DATE_COL = "creation_date";

    private static final String DURATION_COL = "duration";

    private static final String DESCRIPTION_COL = "description";

    private static final String BODY_COL = "body";

    private static final String PRIORITY_COL = "priority";

    private static final String INTERVAL_COL = "interval";

    private static final String NUM_RECURRENCES_COL = "num_of_recurrences";

    private static final String FID_TYPE_COL = "fid_type";

    private static final String BODY_TYPE_COL = "body_type";

    private static final String DONE_COL = "done";


    // creating a constructor for our database handler.
    public FidDbHandler(Context context) {
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
                + PRIORITY_COL + " INTEGER,"
                + DURATION_COL + " INTEGER,"
                + FID_TYPE_COL + " TEXT,"
                + BODY_TYPE_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + BODY_COL + " TEXT,"
                + INTERVAL_COL + " INTEGER,"
                + NUM_RECURRENCES_COL + " INTEGER,"
                + DONE_COL + " INTEGER)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    public void add_new_fid(Fid f){
        this.add_new_fid(
                f.getId(),
                f.getCreationDate().toString(),
                f.getDuration(),
                f.getDescription(),
                f.getBody(),
                f.getPriority(),
                f.getInterval(),
                f.getIntervalLeft(),
                f.getFidType().toString(),
                f.getBodyType().toString());
    }

    // this method is use to add new course to our sqlite database.
    public void add_new_fid(Integer id,
                             String create_date,
                             Integer duration,
                             String description,
                             String body,
                             Integer priority_col,
                             Integer interval,
                             Integer interval_left,
                            String fid_type,
                            String body_type) {

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
        values.put(DESCRIPTION_COL, description);
        values.put(BODY_COL, body);
        values.put(PRIORITY_COL, priority_col);
        values.put(INTERVAL_COL, interval);
        values.put(NUM_RECURRENCES_COL, interval_left);
        values.put(FID_TYPE_COL, fid_type);
        values.put(BODY_TYPE_COL, body_type);
        values.put(DONE_COL, 0);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public int get_max_id(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor_fids =db.query(TABLE_NAME,new String [] {"MAX(id)"},null,null,null,null,null);
        if (cursor_fids.moveToFirst()){
            return Integer.parseInt(cursor_fids.getString(0));
        }
        else {
            return 0;
        }

    }

    public void mark_fid_done(int fid_id){
        ContentValues cv = new ContentValues();
        cv.put("done", 1);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, cv, "id" + "= ?", new String[] {String.valueOf(fid_id)});
    }

    public ArrayList<Fid> read_fids(FidType fidType){
        return read_fids(fidType,true);
    }

    public ArrayList<Fid> read_fids(){
        return read_fids(null,true);
    }

    public ArrayList<Fid> read_fids(FidType fidType, boolean only_not_done){
    // on below line we are creating a
    // database for reading our database.
    SQLiteDatabase db = this.getReadableDatabase();

    String query = "SELECT * FROM " + TABLE_NAME;
    if (only_not_done == true){
        if(fidType == null){
            query += " WHERE done = 0";
        }
        else {
            query = query + " WHERE done = 0 and fid_type='" + fidType.name()+"'";
        }
    }
    else{
        if(fidType != null){
            query =  query + " WHERE fid_type='" + fidType.name()+"'";
        }
    }

    // on below line we are creating a cursor with query to read data from database.
    Cursor cursor_fids = db.rawQuery(query,null);
    // on below line we are creating a new array list.
    ArrayList<Fid> fids = new ArrayList<>();


    // moving our cursor to first position.
    if (cursor_fids.moveToFirst()) {
        do {
            // on below line we are adding the data from cursor to our array list.

            fids.add(
                    FidFactory.createFidFromDb(cursor_fids.getString(0),
                            cursor_fids.getString(1),
                            cursor_fids.getString(2),
                            cursor_fids.getString(3) ,
                            cursor_fids.getString(4) ,
                            cursor_fids.getString(5) ,
                            cursor_fids.getString(6) ,
                            cursor_fids.getString(7),
                            cursor_fids.getString(8),
                            cursor_fids.getString(9)
                    ));
        } while (cursor_fids.moveToNext());
        // moving our cursor to next.
    }
    // at last closing our cursor
    // and returning our array list.
    cursor_fids.close();
    return fids;
}



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

