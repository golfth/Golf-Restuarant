package com.example.golfth1.golfrestaurant;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by golfth1 on 9/4/2559.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MyManage(Context context) {

        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    } // Constructor
} // Main Class
