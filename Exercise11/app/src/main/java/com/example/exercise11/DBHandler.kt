package com.example.exercise11

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class DBHandler(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "student-db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "marks"
        const val RNO_COL = "rno"
        const val NAME_COl = "name"
        const val MARKS_COL = "marks"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + RNO_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                MARKS_COL + " TEXT" + ")")

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addEntry(rno:String , name:String, marks:Int) {
        val values = ContentValues()

        values.put(RNO_COL, rno)
        values.put(NAME_COl, name)
        values.put(MARKS_COL, marks)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        db.close()
    }

    fun getEntries(): ArrayList<StudentModel> {
        val db = this.readableDatabase
        val selectQuery = ("SELECT  * FROM $TABLE_NAME")
        val studentsList = ArrayList<StudentModel>();
        val c: Cursor? = db.rawQuery(selectQuery, null)
        if (c != null) {
            if(c.moveToFirst()) {
                if (c != null) {
                    do {
                        studentsList += StudentModel(c.getInt(0), c.getString(1), c.getInt(2));
                    } while (c.moveToNext())
                }
            }
        }
        c?.close()
        return studentsList;
    }

    fun getEntry(rno: String): StudentModel? {
        return try {
            val db = this.readableDatabase
            val selectQuery = ("SELECT  * FROM $TABLE_NAME WHERE $RNO_COL = $rno")
            val c: Cursor = db.rawQuery(selectQuery, null)
            c?.moveToFirst()
            val student = c?.let { StudentModel(c.getInt(0), c.getString(1), it.getInt(2)) }
            c?.close()
            student;
        } catch(e: Exception) {
            null
        }
    }

    fun deleteEntry(rno: String) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "rno=?", arrayOf(rno))
        db.close()
    }

    fun updateEntry(rno:String, name:String, marks: String) {
        val db = this.writableDatabase
        val values = ContentValues()

        if(name!="" && marks!="") {
            values.put(NAME_COl, name)
            values.put(MARKS_COL, marks)
        }
        else if(name!="") {
            values.put(NAME_COl, name)
        }
        else if(marks!="") {
            values.put(MARKS_COL, marks)
        }

        db.update(TABLE_NAME, values, "$RNO_COL = ?", arrayOf(rno.toString()))
        db.close()
    }
}