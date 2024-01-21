package com.finalcalculator.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperAS extends SQLiteOpenHelper {
    public static final String DB_Name = "FinancialCalculator.db";
    public static final String Table_Name1 = "EMI_History";
    public static final String column1 = "principle";
    public static final String column2 = "interest";
    public static final String column3 = "years";
    public static final String column4 = "months";
    public static final int version = 1;

    public DatabaseHelperAS(Context context) {
        super(context, DB_Name, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table EMI_History (principle VARCHAR,interest TEXT,years VARCHAR,months VARCHAR)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS EMI_History");
        onCreate(sQLiteDatabase);
    }

    public void addEMI_History(String str, String str2, String str3, String str4) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column1, str);
        contentValues.put(column2, str2);
        contentValues.put(column3, str3);
        contentValues.put(column4, str4);
        writableDatabase.insert(Table_Name1, (String) null, contentValues);
    }

    public Cursor getEMI_History() {
        return getWritableDatabase().rawQuery("select * from EMI_History", (String[]) null);
    }
}
