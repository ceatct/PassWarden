package com.difl.passwarden.Database

import android.provider.BaseColumns

object MyNameClass : BaseColumns {

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "Pas.db"
    const val TABLE_NAME = "passwords"
    const val SITE = "site"
    const val LOGIN = "login"
    const val PASSWORD = "password"

    const val CREATE =  "CREATE TABLE $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," + "$SITE TEXT, " +
            "$LOGIN TEXT, $PASSWORD TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"


}