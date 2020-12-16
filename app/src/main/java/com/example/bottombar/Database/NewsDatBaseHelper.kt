package com.example.bottombar.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class NewsDatBaseHelper(val context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {
        private val createNews = "create table newsData (" +"id integer primary key autoincrement,"+"uniquekey text,"+"author text," +"date text,"+ "title text," +"category text,"+"url text,"+"thumbanill text)"
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(createNews)
            Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
        }
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {    }

    }
