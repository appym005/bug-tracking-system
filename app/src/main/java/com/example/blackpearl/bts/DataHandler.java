package com.example.blackpearl.bts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BLACK PEARL on 7/13/2017.
 */

public class DataHandler extends SQLiteOpenHelper {
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public DataHandler(Context c)
    {
        super(c,"bts",null,2);
    }
}
