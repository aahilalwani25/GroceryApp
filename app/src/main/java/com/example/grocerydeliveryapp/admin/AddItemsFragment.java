package com.example.grocerydeliveryapp.admin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.grocerydeliveryapp.Database;
import com.example.grocerydeliveryapp.R;

import java.io.ByteArrayOutputStream;


public class AddItemsFragment extends Fragment {

    Spinner category;
    EditText itemName;
    EditText itemDescription;
    EditText itemCost;
    ImageView itemImage;
    ImageButton galleryButton;
    Button addItem;

    String selectedCategory, name, description;
    int cost;

    byte[] image;


    //public static final int REQUEST_CODE=102;
    public static final int GALLERY_CODE=32;

    Context context;
    public AddItemsFragment(Context context) {
        // Required empty public constructor
        this.context=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_add_items, container, false);

        //assigning ids
        category=v.findViewById(R.id.categories_list);
        itemName=v.findViewById(R.id.item_name);
        itemDescription=v.findViewById(R.id.item_description);
        itemCost=v.findViewById(R.id.item_cost);
        itemImage=v.findViewById(R.id.item_image);
        addItem=v.findViewById(R.id.add_item_click);
        galleryButton=v.findViewById(R.id.add_image_click);


        //changing spinner text and size
        ArrayAdapter adapter= ArrayAdapter.createFromResource(context,R.array.Categories,R.layout.spinner_custom);
        adapter.setDropDownViewResource(R.layout.spinner_custom);
        category.setAdapter(adapter);

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();

                //setType() means what type of content can be selected
                // image/* means admin can select all types of images (eg. png, jpeg, etc)
                intent.setType("image/jpeg");

                //open gallery action
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, GALLERY_CODE);
                onActivityResult(GALLERY_CODE,40,intent);
            }
        });

        //add item Click Event listener
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting selected category
                selectedCategory=category.getSelectedItem().toString();
                name=itemName.getText().toString();
                description=itemDescription.getText().toString();
                cost=Integer.parseInt(itemCost.getText().toString());

                //convert the image into bitmap
                BitmapDrawable drawable = (BitmapDrawable) itemImage.getDrawable();
                Bitmap img = drawable.getBitmap();

                //store the image in bytes
                image=convertIntoByteImage(img);

                //now saving into database
                Database db=new Database(context,null);
                if(db.insertItems(selectedCategory,name,cost,description,image)>0){
                    Toast.makeText(context, "Item inserted successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context, "Item NOT inserted",Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri=data.getData();
        itemImage.setImageURI(uri);
    }

    //convert image into byte
    public byte[] convertIntoByteImage(Bitmap img){

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG,0,outputStream);
        return outputStream.toByteArray();
    }
}