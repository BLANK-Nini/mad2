package com.example.sqdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE student (" +
                "rollno INTEGER PRIMARY KEY, " +
                "name TEXT, " +
                "dept TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS student");
        onCreate(db);
    }

    // INSERT
    public boolean insertData(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert("student", null, values);
        db.close();
        return result != -1;
    }

    // READ
    public Cursor readData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM student", null);
    }

    // UPDATE
    public int updateData(ContentValues values, String rollno) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.update("student", values, "rollno = ?", new String[]{rollno});
        db.close();
        return result;
    }

    // DELETE
    public int deleteData(String rollno) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("student", "rollno = ?", new String[]{rollno});
        db.close();
        return result;
    }
}
