package com.difl.passwarden.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper (context: Context) : SQLiteOpenHelper(context, MyNameClass.DATABASE_NAME,
    null, MyNameClass.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(MyNameClass.CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(MyNameClass.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

}