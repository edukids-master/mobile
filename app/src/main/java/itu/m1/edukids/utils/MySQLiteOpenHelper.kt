package itu.m1.edukids.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySQLiteOpenHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    private val creation: String = "create table user (" +
            "_id TEXT PRIMARY KEY," +
            "prenom TEXT NOT NULL," +
            "nom TEXT NOT NULL," +
            "email TEXT NOT NULL," +
            "date_naissance DATE NOT NULL," +
            "date_inscription DATE NOT NULL," +
            "login TEXT NOT NULL," +
            "password TEXT NOT NULL" +
            ")"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(creation)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}