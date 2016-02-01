package com.example.ali.databasepk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ali on 31/01/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "school.db";
    public static final String STUDENTS_TABLE= "students";
    public static final String idColumn1= "ID";
    public static final String nameColumn2 ="NAME";
    public static final String classColumn3= "CLASS";

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table " + STUDENTS_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
        db.execSQL("create table "+ STUDENTS_TABLE + " ("+
        idColumn1 + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                "," +
                nameColumn2 +" TEXT ,"+
                classColumn3 +" TEXT )"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ STUDENTS_TABLE);
        onCreate(db);
    }
    public boolean insertData( String name , String classNo){
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(nameColumn2,name);
        contentValues.put(classColumn3,classNo);
        long result = myDb.insert(STUDENTS_TABLE,null,contentValues);
        if(result == -1) { return false; }
        else return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("select * from "+STUDENTS_TABLE,null);
        return res;
    }
    public boolean updateData(String id,String name, String classC){
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(idColumn1,id);
        contentValues.put(nameColumn2,name);
        contentValues.put(classColumn3,classC);
        int rows = myDb.update(STUDENTS_TABLE,contentValues,idColumn1+"= ?", new String[]{id});
        boolean done=false;
        if(rows>=1) done=true;
        return done;
    }
    public Integer deleteData(String id){
        SQLiteDatabase myDb= this.getWritableDatabase();
        Integer rows=myDb.delete(STUDENTS_TABLE,idColumn1+"= ?",new String[]{id});
        return rows;
    }

}
