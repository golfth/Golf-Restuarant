package com.example.golfth1.golfrestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by golfth1 on 9/4/2559.
 */
public class MyOpenHelper extends SQLiteOpenHelper{
    //Explicit
    public static final String database_name = "Restaurant.db";
    private static final int database_version = 1;

    private static final String create_user_Table = "create table userTable (" +
            "_id integer primary key, " +
            "User text, " +
            "Password text," +
            "Name text)";

    private static final String create_food_Table = "create table foodTable (" +
            "_id integer primary key," +
            "Food text," +
            "Price text," +
            "Source text)";

    public MyOpenHelper(Context context ) {
        super(context, database_name, null, database_version);
    }  // Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_food_Table);
        sqLiteDatabase.execSQL(create_user_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
} // Main Class
