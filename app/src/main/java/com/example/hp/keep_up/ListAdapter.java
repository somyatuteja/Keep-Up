package com.example.hp.keep_up;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by HP on 11/3/2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private LayoutInflater inflater;
     ArrayList<ToDoListItem> arrayListItem;
    Context context;
    public ListAdapter(Context context)
    {
        this.context=context;
        inflater=LayoutInflater.from(context);
        arrayListItem= DatabaseHelper.getInstance(context).getArrayList();
    }
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= inflater.inflate(R.layout.list_item,parent,false);
        ListViewHolder holder= new ListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListAdapter.ListViewHolder holder, int position) {
        ToDoListItem listItem=arrayListItem.get(position);
     holder.title.setText(listItem.title);
        if(listItem.status.equals("true"))
        {
            holder.status.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        if(arrayListItem==null)
        {
            return 0;
        }
        else
        return arrayListItem.size();
    }

    class  ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
               TextView title;
               CheckBox status;
        public ListViewHolder(View itemView) {
            super(itemView);

            title=(TextView) itemView.findViewById(R.id.titleTextView);
            status=(CheckBox)itemView.findViewById(R.id.statusCheckBox);

            status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToDoListItem listItem = arrayListItem.get(getPosition());
                    listItem.changeStatus();
                }
            });
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Description_Activity.class);
                    Log.v("MainActivity","inOnClick");
                    ToDoListItem listItem = arrayListItem.get(getPosition());
                    intent.putExtra("ID",listItem.id);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Description_Activity.class);
            Log.v("MainActivity","inOnClick");
            ToDoListItem listItem = arrayListItem.get(getPosition());
            intent.putExtra("ID",listItem.id);
           context.startActivity(intent);
        }
    }
}
