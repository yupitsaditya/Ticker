package jajodia.aditya.com.bunkmanager2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kunalsingh on 13/12/16.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "SubjectDatabase";
    public static final int DB_VER = 1;

    public static DatabaseOpenHelper databaseOpenHelper;
    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SubjectTable.CREATE_TABLE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static long insertData(Context c , String subject , int total , int present){

        databaseOpenHelper = new DatabaseOpenHelper(c);
        SQLiteDatabase openHelper = databaseOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(SubjectTable.Colums.SUBJECT,subject);
        values.put(SubjectTable.Colums.STATUS,1);
        values.put(SubjectTable.Colums.TOTAL , total);
        values.put(SubjectTable.Colums.PRESENT , present);

        long t = openHelper.insert(SubjectTable.TABLE_NAME,null,values);

        return t;
    }

    public static Cursor readData(Context c , String subject){

        databaseOpenHelper = new DatabaseOpenHelper(c);
        SQLiteDatabase database = databaseOpenHelper.getReadableDatabase();

        String projection [] = {
                SubjectTable.Colums.STATUS,
            SubjectTable.Colums.SUBJECT,
                SubjectTable.Colums.TOTAL,
                SubjectTable.Colums.PRESENT
        };
        String selection = SubjectTable.Colums.SUBJECT + " = ?";
        String selectionArgs[] = {subject};

       Cursor cursor =  database.query(SubjectTable.TABLE_NAME,projection,selection,selectionArgs,null,null,null);

        return  cursor;
    }


}