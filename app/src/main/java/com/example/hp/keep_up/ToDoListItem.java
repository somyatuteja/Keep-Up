package com.example.hp.keep_up;

import android.content.Context;

public class ToDoListItem {
    int id;
     public String title;
     String description;
    String status;
    ToDoListItem(String title, String description)
    {
        this.title=title;
        this.description=description;
        status="false";
    }
    void addToDataBase(Context context)
    {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        databaseHelper.insertData(this.title, this.description, this.status);
    }
    ToDoListItem(int id,String title, String description, String status)
    {
        this.id=id;
        this.title=title;
        this.description=description;
        this.status=status;

    }
    void changeStatus()
    {
        if(this.status.equals("false"))
        this.status="true";
        else
        if (this.status.equals("true"))
            this.status="false";
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        databaseHelper.updatedoneStatus(this.id);
    }
    static void deleteItems()
    {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        databaseHelper.deleteData();
    }


}
