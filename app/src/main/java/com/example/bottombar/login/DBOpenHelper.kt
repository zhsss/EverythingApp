package com.example.bottombar.login

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*


class DBOpenHelper(context: Context?) : SQLiteOpenHelper(context, "db_test", null, 1) {
    private val db: SQLiteDatabase


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS user")
        onCreate(db)
    }


    fun add(name: String, password: String) {
        db.execSQL("INSERT INTO user (name,password) VALUES(?,?)", arrayOf<Any>(name, password))
    }

    fun delete(name: String, password: String) {
        db.execSQL("DELETE FROM user WHERE name = AND password =$name$password")
    }

    fun updata(password: String) {
        db.execSQL("UPDATE user SET password = ?", arrayOf<Any>(password))
    }

    val allData: ArrayList<User>
        get() {
            val list = ArrayList<User>()
            val cursor = db.query("user", null, null, null, null, null, "name DESC")
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val password = cursor.getString(cursor.getColumnIndex("password"))
                list.add(User(name, password))
            }
            return list
        }


    init {
        db = readableDatabase
    }
}