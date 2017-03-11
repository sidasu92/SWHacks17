package com.example.natarajan.transitproject;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "transit_project.db";

    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper mInstance = null;
    public String databasePath;
    private Context mCxt;



    public static DatabaseHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public DatabaseHelper(Context ctx) {

        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.mCxt = ctx;
        databasePath = ctx.getDatabasePath("transit_project.db").getPath();
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {

    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database,int oldVersion,int newVersion){

    }
}
