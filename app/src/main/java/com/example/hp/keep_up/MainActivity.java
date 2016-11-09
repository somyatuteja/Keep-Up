package com.example.hp.keep_up;

import android.app.Dialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

 private RecyclerView mRecyclerView;
    private com.example.hp.keep_up.ListAdapter listAdapter;

    private void showToast(String msg, boolean length) {
        Context context = getApplicationContext();
        Toast toast;
        if (length) {
         toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        }
        else
        {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView =(RecyclerView) findViewById(R.id.toDoListRecyclerView);
       // mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
       getUpdatedRecyclerView();
    }
    void getUpdatedRecyclerView()
    {
        listAdapter =new com.example.hp.keep_up.ListAdapter(getApplicationContext());
        mRecyclerView.setAdapter(listAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
       // mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(Color.RED).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu,menu);
        Log.v("MainActivity","Creating Menu");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        Log.v("MainActivity","on selection");
        switch (item.getItemId()) {

            case R.id.addMenuItem :
                Log.v("MainActivity","Selected new");
               openDialog();
                break;
            case R.id.deleteMenuItem :
             ToDoListItem.deleteItems();
                getUpdatedRecyclerView();
                break;
        }
    return true;
    }
    void openDialog()
    {
final Dialog dialog=new Dialog(this);
        Log.v("MainActivity","Created Dialog");
        dialog.setContentView(R.layout.add_new_dialog);
        Log.v("MainActivity","Dialog should appear");
        dialog.setTitle(R.string.dialog_title);
        final EditText newTitleEditText = (EditText) dialog.findViewById(R.id.newTitleEditText);
        final EditText newDescEditText =(EditText) dialog.findViewById(R.id.newDescriptionEditText);
        Button mAddNewButton =(Button)dialog.findViewById(R.id.addNewButton);
        final Context context=getApplicationContext();
        mAddNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle= newTitleEditText.getText().toString();
                String newDescription=newDescEditText.getText().toString();
                ToDoListItem toDoListItem=new ToDoListItem(newTitle, newDescription);
                toDoListItem.addToDataBase(context);
               getUpdatedRecyclerView();
                showToast("New Item has been Added",true);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}