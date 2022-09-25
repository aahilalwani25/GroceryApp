package com.example.grocerydeliveryapp.Fruits;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocerydeliveryapp.Database;
import com.example.grocerydeliveryapp.R;
import com.example.grocerydeliveryapp.Vegetables.Vegetables;
import com.example.grocerydeliveryapp.Vegetables.VegetablesAdapter;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;

public class FruitsFragment extends Fragment {

    ArrayList<Fruits> fruits;
    RecyclerView recyclerView;

    Context context;
    public FruitsFragment(Context context){
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //converts an xml code into view components
        View v = inflater.inflate(R.layout.fragment_fruits,container,false);

        recyclerView=v.findViewById(R.id.rv2);
        Stetho.initializeWithDefaults(context);


        Database db=new Database(context,null);
        Cursor cursor;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        fruits=new ArrayList<>();
        cursor=db.getAllTableRecords("Fruits");
        setRecyclerView(cursor,fruits);
        return v;
    }

    public void setRecyclerView(Cursor cursor, ArrayList<Fruits> fruits){

        while (cursor.moveToNext()){
            //adding vegetables into list
            fruits.add(new Fruits(cursor.getString(1),cursor.getInt(2),cursor.getString(3), cursor.getBlob(4)));
        }
        recyclerView.setAdapter(new FruitsAdapter(fruits));

    }
}
