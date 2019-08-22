package com.example.sqldatabse.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sqldatabse.R;
import com.example.sqldatabse.adapter.ShowAdapter;
import com.example.sqldatabse.customclass.ShowContent;
import com.example.sqldatabse.customclass.ShowContent.ShowItem;
import com.example.sqldatabse.database.DatabaseColumns;
import com.example.sqldatabse.database.DatabaseQueries;


public class ShowFragment extends Fragment {


    private int mColumnCount = 1;
     public ShowFragment() {
    }



    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_list, container, false);
        recyclerView=view.findViewById(R.id.list);
        // Set the adapter

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), mColumnCount));
            }

            DatabaseQueries databaseQueries=new DatabaseQueries(getContext());
        Cursor cursor=databaseQueries.getData();
        ShowContent.ITEMS.clear();
        if(cursor.moveToFirst())
        {
            do {
                ShowContent.ShowItem showItem=new ShowItem(cursor.getString(cursor.getColumnIndex(DatabaseColumns.NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseColumns.EMAIL)),
                        cursor.getString(cursor.getColumnIndex(DatabaseColumns.PASSWORD)));
                ShowContent.ITEMS.add(showItem);
            }while (cursor.moveToNext());
        }
        recyclerView.setAdapter(new ShowAdapter(ShowContent.ITEMS,getContext()));




            return view;

    }


    }
