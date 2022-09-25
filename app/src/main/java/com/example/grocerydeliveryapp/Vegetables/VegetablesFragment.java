package com.example.grocerydeliveryapp.Vegetables;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.grocerydeliveryapp.AddToCardActivity;
import com.example.grocerydeliveryapp.Database;
import com.example.grocerydeliveryapp.R;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;

public class VegetablesFragment extends Fragment{

    Context context;
    public VegetablesFragment(Context context){
        this.context=context;
    }
    RecyclerView recyclerView;
    ArrayList<Vegetables> vegetables;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_vegetables,container,false);
        recyclerView=v.findViewById(R.id.recyclerView);
        Stetho.initializeWithDefaults(context);


        Database db=new Database(context,null);
        Cursor cursor;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        vegetables=new ArrayList<>();
        cursor=db.getAllTableRecords("Vegetables");
        setRecyclerView(cursor,vegetables);

        return v;
    }


    public void setRecyclerView(Cursor cursor, ArrayList<Vegetables> vegetables){


        while (cursor.moveToNext()){
            //adding vegetables into list
            vegetables.add(new Vegetables(cursor.getString(1),cursor.getInt(2),cursor.getString(3), cursor.getBlob(4)));
        }

        recyclerView.setAdapter(new VegetablesAdapter(vegetables));

    }
}

