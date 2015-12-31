package com.example.patrick.canvaswrite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

/**
 * Created by Patrick on 3/9/2015.
 */
public class DatabaseManager {

    private SQLiteDatabase db; // a reference to the database manager class.
    private static final String DB_NAME = "English3"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    private static final String TABLE_NAME = "letters";// table name

    // the names for our database columns
    private static final String TABLE_ROW_ID = "id";
    private static final String TABLE_ROW_UPPER = "upper";
    private static final String TABLE_ROW_RIGHT = "right";
    private static final String TABLE_ROW_WRONG = "wrong";
    private Context context;

    public DatabaseManager(Context context) {
        this.context = context;

        // create or open the database
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        this.db = helper.getWritableDatabase();
    }

    // the beginnings our SQLiteOpenHelper class
    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // the SQLite query string that will create our column database
            // table.
            String newTableQueryString =
                    "create table " + TABLE_NAME + " (\n"
                            + TABLE_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                            + TABLE_ROW_UPPER + " TEXT NOT NULL,\n"
                            + TABLE_ROW_RIGHT + " INTEGER DEFAULT '0' NOT NULL,\n"
                            + TABLE_ROW_WRONG + " INTEGER DEFAULT '0' NOT NULL" + ");";

            // execute the query string to the database.
            db.execSQL(newTableQueryString);

            addRow(db, "A");
            addRow(db, "B");
            addRow(db, "C");
            addRow(db, "D");
            addRow(db, "E");
            addRow(db, "F");
            addRow(db, "G");
            addRow(db, "H");
            addRow(db, "I");
            addRow(db, "J");
            addRow(db, "K");
            addRow(db, "L");
            addRow(db, "M");
            addRow(db, "N");
            addRow(db, "O");
            addRow(db, "P");
            addRow(db, "Q");
            addRow(db, "R");
            addRow(db, "S");
            addRow(db, "T");
            addRow(db, "U");
            addRow(db, "V");
            addRow(db, "W");
            addRow(db, "X");
            addRow(db, "Y");
            addRow(db, "Z");


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            // LATER, WE WOULD SPECIFIY HOW TO UPGRADE THE DATABASE
            // FROM OLDER VERSIONS.
            String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(DROP_TABLE);
            onCreate(db);

        }
    }

    public void addRow(SQLiteDatabase db, String letter) {
        // ask the database object to insert the new data

        try{
            wait(4000);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }

        Log.e("DATABASE:","STARTING");
        if(db.isDbLockedByCurrentThread())
            Log.w("DATABASE:","is locked");
        Log.e("DATABASE:","STARTING     2");

        try {
            String insertStatment = "INSERT INTO " + TABLE_NAME + " \n("
                    + TABLE_ROW_UPPER + ") \n" + " VALUES "
                    + "\n('" + letter + "')";

            SQLiteStatement s = db.compileStatement(insertStatment);
            s.execute();
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString()); // prints the error message to
            // the log
            e.printStackTrace(); // prints the stack trace to the log
        }
    }

    public String[] getRowAsObjectAlternative(String letter) {

        String[] record = new String[]{"","","","","","","",""};
        LetterModel rowContactObj = new LetterModel();
        Cursor cursor;

        try {
            // query to fetch all the columns and rows of the table
            String queryStatement = "SELECT * FROM " + TABLE_NAME + " WHERE "
                    + TABLE_ROW_UPPER + "='" + letter + "'";

            cursor = db.rawQuery(queryStatement, null);
            cursor.moveToFirst();
            record[0] = cursor.getString(0);
            record[1] = cursor.getString(1);
            record[2] = cursor.getString(2);
            record[3] = cursor.getString(3);
            // }
        } catch (SQLException e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }

        return record;
    }

    public void updateRowRight(String letter) {

        String[] record = getRowAsObjectAlternative(letter);
        int right = Integer.parseInt(record[2]);
        right++;

        String updateStatement = "UPDATE " + TABLE_NAME + " SET "
                + TABLE_ROW_RIGHT + "=" + right
                + " WHERE " + TABLE_ROW_UPPER + "='" + letter + "';";

        SQLiteStatement s = db.compileStatement(updateStatement);
        s.executeUpdateDelete();
    }

    public void updateRowWrong(String letter) {

        String[] record = getRowAsObjectAlternative(letter);
        int wrong = Integer.parseInt(record[3]);
        wrong++;

        String updateStatement = "UPDATE " + TABLE_NAME + " SET "
                + TABLE_ROW_WRONG + "=" + wrong
                + " WHERE " + TABLE_ROW_UPPER + "='" + letter + "';";

        SQLiteStatement s = db.compileStatement(updateStatement);
        s.executeUpdateDelete();
    }
}
