package com.example.hp.keep_up;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DescriptionFragment extends android.support.v4.app.Fragment{
public TextView mTitleTextView;
public TextView mDescriptionTextView;
public TextView mStatusTextView;
    ToDoListItem toDoListItem;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id= getArguments().getInt("idInt", 0);
        Log.v("Fragment","creating the fragment, got id="+id);
        //Log.v("Fragment","inside the getInstance");
        DatabaseHelper dbHelper=DatabaseHelper.getInstance();
        toDoListItem= dbHelper.getItem(id);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("Fragment","inside the onCreateView");
        View inflatedView= inflater.inflate(R.layout.fragment_layout, container, false);

        mDescriptionTextView=(TextView)inflatedView.findViewById(R.id.fragmentDescription);
        mDescriptionTextView.setText(toDoListItem.description);
        mTitleTextView=(TextView)inflatedView.findViewById(R.id.fragmentTitle);
        mTitleTextView.setText(toDoListItem.title);
        mStatusTextView=(TextView)inflatedView.findViewById(R.id.fragmentStatus);
        if(toDoListItem.status.equals("true"))
           mStatusTextView.setText(R.string.completed);
        if(toDoListItem.status.equals("false"))
            mStatusTextView.setText(R.string.not_completed);

        return inflatedView;
    }
     static DescriptionFragment getInstance(int id)
     {
         Log.v("Fragment","inside the getInstance");
         DescriptionFragment myFragment = new DescriptionFragment();
         Log.v("Fragment","gotFragment");

         Bundle args = new Bundle();
         args.putInt("idInt", id);
         myFragment.setArguments(args);

         return myFragment;
     }


}
