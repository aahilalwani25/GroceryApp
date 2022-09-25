package com.example.grocerydeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddToCardActivity extends AppCompatActivity {

    private int count=1;

    EditText personName, phone, address;
    TextView foodName, cost;
    ImageView add,minus, image;
    TextView itemCount, description;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_card);

        personName=findViewById(R.id.Namebox);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);
        add=findViewById(R.id.add);
        minus=findViewById(R.id.minus);
        itemCount=findViewById(R.id.itemCount);
        foodName=findViewById(R.id.foodName);
        cost=findViewById(R.id.cost);
        btn=findViewById(R.id.insertBtn);
        String originalCost= cost.getText().toString();
        description=findViewById(R.id.detail_description);
        image=findViewById(R.id.detailimage);

        //sett
        Intent intent=getIntent();

        //first we need to convert byte[] into image
        byte[] data = intent.getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        image.setImageBitmap(bmp);

        foodName.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("description"));
        cost.setText(Integer.toString(intent.getIntExtra("cost",0)));
        itemCount.setText(Integer.toString(count));

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(itemCount.getText().toString())==1){
                    Toast.makeText(AddToCardActivity.this,"Can not decrease",Toast.LENGTH_LONG);
                }else{
                    count--;
                    itemCount.setText(Integer.toString(count));

                    //set the cost according to the item count
                    cost.setText(Integer.toString(intent.getIntExtra("cost",0)*count));
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                itemCount.setText(Integer.toString(count));
                //set the cost according to the item count
                cost.setText(Integer.toString(intent.getIntExtra("cost",0)*count));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fN=foodName.getText().toString();
                String pN=personName.getText().toString();
                String ad= address.getText().toString();
                String price=cost.getText().toString();
                String ph=phone.getText().toString();


                AddCartItems(AddToCardActivity.this,fN, pN, ph, ad, price, count);

            }
        });

    }

    private void AddCartItems(Context con, String fN, String pN, String ph, String ad, String price, int count) {

        Database db=new Database(con,null);
        long confirm= db.addCart(fN,pN,ph,ad,price,count);

        if(confirm>0){
            Toast.makeText(con,"Added successfully",Toast.LENGTH_LONG).show();
        }else{

            Toast.makeText(con,"Not added",Toast.LENGTH_LONG).show();
        }

    }

}