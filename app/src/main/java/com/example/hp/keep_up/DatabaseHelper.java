package com.example.hp.keep_up;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/*
 * Created by HP on 10/2/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DB_NAME="ToDoList.db";
    public static String TABLE_NAME="ToDoTable";
    public static String COL1="_id";
    public static String COL2="Title";
    public static String COL3="Details";
    public static String COL4="status";
    private static DatabaseHelper dbIsntance;
    public static DatabaseHelper getInstance(Context context){
        if(dbIsntance==null){
            dbIsntance= new DatabaseHelper(context);
        }
        return dbIsntance;
    }
    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL2+" VARCHAR,"+COL3+" VARCHAR,"+COL4+" VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+DB_NAME);
        onCreate(db);
    }
    public void insertData(String title, String details, String status)
    {
        Log.v("MainActivity","here");
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL2,title);
        contentValues.put(COL3,details);
        contentValues.put(COL4,status);
        long result= db.insert(TABLE_NAME,null,contentValues);
        Log.v("MainActivity",Long.toString(result));

    }
    public Cursor getData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from "+TABLE_NAME,null);
        return res;
    }
    public void deleteData(String uname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME," user = ?",new String[] {uname});
    }
    public void updateData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from "+TABLE_NAME,null);

    }
    public ArrayList<ToDoListItem> getArrayList()
    {  ArrayList<ToDoListItem> arrayListItem= new ArrayList<ToDoListItem>();
        Cursor cursor=getData();
        if(cursor.getCount()==0)
        {
            return null;
        }
        while (cursor.moveToNext())
        {
            ToDoListItem toDoListItem = new ToDoListItem(cursor.getString(1),cursor.getString(2), cursor.getString(3));
            arrayListItem.add(toDoListItem);
        }
 return  arrayListItem;

    }
}