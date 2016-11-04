package com.example.hp.keep_up;

import android.content.Context;

public class ToDoListItem {
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
    ToDoListItem(String title, String description, String status)
    {
        this.title=title;
        this.description=description;
        this.status=status;

    }
}
