package com.example.grocerydeliveryapp.admin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.grocerydeliveryapp.Database;
import com.example.grocerydeliveryapp.R;


public class DeleteItemFragment extends Fragment {

    Spinner category;
    EditText itemName;
    Button delItem;
    Context context;

    String selectedCategory, name;
    public DeleteItemFragment(Context context) {
        // Required empty public constructor
        this.context=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_delete_item, container, false);
        category=v.findViewById(R.id.categories_list);
        itemName=v.findViewById(R.id.item_name);
        delItem=v.findViewById(R.id.delete_item_click);

        //changing spinner text and size
        ArrayAdapter adapter= ArrayAdapter.createFromResource(context,R.array.Categories,R.layout.spinner_custom);
        adapter.setDropDownViewResource(R.layout.spinner_custom);
        category.setAdapter(adapter);


        delItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting selected category
                selectedCategory=category.getSelectedItem().toString();
                name=itemName.getText().toString();

                //now saving into database
                Database db=new Database(context,null);
                if(db.deleteItems(selectedCategory,name)>0){
                    Toast.makeText(context, "Item Deleted successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context, "Item NOT deleted",Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }
}