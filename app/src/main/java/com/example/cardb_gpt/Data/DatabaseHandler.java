package com.example.cardb_gpt.Data;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.cardb_gpt.Utils.Util;


public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CARS_TABLE = "CREATE TABLE "
                +Util.TABLE_NAME+"("
                +Util.KEY_ID+" INTEGER PRIMARY KEY, "
                +Util.KEY_NAME+" TEXT"
                +Util.KEY_PRICE+" TEXT" +")";
        sqLiteDatabase.execSQL(CREATE_CARS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
