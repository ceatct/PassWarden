package com.difl.passwarden.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class MyDbManager (context: Context) {

    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB(){
        db = myDbHelper.writableDatabase
    }

    fun insert (site: String, name: String, password: String){
        val values = ContentValues().apply{
            put(MyNameClass.SITE, site)
            put(MyNameClass.LOGIN, name)
            put(MyNameClass.PASSWORD, password)
        }
        db?.insert(MyNameClass.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun read():ArrayList<String>{
        val datalist = ArrayList<String>()
            val cursor = db?.query(MyNameClass.TABLE_NAME, null, null, null,
            null, null, null)

        while (cursor?.moveToNext()!!){
            val site = cursor.getString(cursor.getColumnIndex(MyNameClass.SITE))
            val login = cursor.getString(cursor.getColumnIndex(MyNameClass.LOGIN))
            val password = cursor.getString(cursor.getColumnIndex(MyNameClass.PASSWORD))
            datalist.add(site.toString())
            datalist.add(login.toString())
            datalist.add(password.toString())
        }
        return datalist
    }

    @SuppressLint("Range")
    fun site():ArrayList<String>{
        val datalist = ArrayList<String>()
        val cursor = db?.query(MyNameClass.TABLE_NAME, null, null, null,
            null, null, null)

        while (cursor?.moveToNext()!!){
            val site = cursor.getString(cursor.getColumnIndex(MyNameClass.SITE))
            datalist.add(site.toString())
        }
        return datalist
    }

    @SuppressLint("Range")
    fun login():ArrayList<String>{
        val datalist = ArrayList<String>()
        val cursor = db?.query(MyNameClass.TABLE_NAME, null, null, null,
            null, null, null)

        while (cursor?.moveToNext()!!){
            val site = cursor.getString(cursor.getColumnIndex(MyNameClass.LOGIN))
            datalist.add(site.toString())
        }
        return datalist
    }

    @SuppressLint("Range")
    fun pass():ArrayList<String>{
        val datalist = ArrayList<String>()
        val cursor = db?.query(MyNameClass.TABLE_NAME, null, null, null,
            null, null, null)

        while (cursor?.moveToNext()!!){
            val site = cursor.getString(cursor.getColumnIndex(MyNameClass.PASSWORD))
            datalist.add(site.toString())
        }
        return datalist
    }

    @SuppressLint("Range")
    fun id():ArrayList<String>{
        val datalist = ArrayList<String>()
        val cursor = db?.query(MyNameClass.TABLE_NAME, null, null, null,
            null, null, null)

        while (cursor?.moveToNext()!!){
            val site = cursor.getString(cursor.getColumnIndex(BaseColumns._ID))
            datalist.add(site.toString())
        }
        return datalist
    }

    fun delete(){
        db?.delete(MyNameClass.TABLE_NAME, null, null)
    }

    fun delitem(id: String){
        val selection = BaseColumns._ID + "=$id"
        db?.delete(MyNameClass.TABLE_NAME, selection, null)
    }

}