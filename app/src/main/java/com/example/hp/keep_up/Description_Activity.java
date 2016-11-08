package com.example.hp.keep_up;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

public class Description_Activity extends ActionBarActivity {
  ViewPager viewPager=null;
    int curid;
    private ArrayList<ToDoListItem> mToDoListItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Fragment","enters the activity");
        super.onCreate(savedInstanceState);
      Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_description_activity);
        DatabaseHelper databaseHelper=DatabaseHelper.getInstance(getApplicationContext());
        mToDoListItemList=databaseHelper.getArrayList();
         curid=getIntent().getIntExtra("ID",0);
        Log.v("Fragment","gets curid="+curid);

        viewPager =(ViewPager)findViewById(R.id.descriptionViewPager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new myViewPagerAdapter(fragmentManager));
        for (int i = 0; i < mToDoListItemList.size(); i++) {
            ToDoListItem toDoListItem = mToDoListItemList.get(i);
            if (toDoListItem.id==curid) {
                Log.v("Fragment","found the correct fragment");
                viewPager.setCurrentItem(i);
                break;
            }
        }}
    class myViewPagerAdapter extends FragmentStatePagerAdapter
    {

        public myViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            ToDoListItem toDoListItem=mToDoListItemList.get(position);
            Log.v("Fragment","creating fragment for id= "+position);

            Fragment mDescFragment=DescriptionFragment.getInstance(toDoListItem.id);
            return mDescFragment;
        }

        @Override
        public int getCount() {
            return mToDoListItemList.size();
        }

    }



}
