package com.example.grocerydeliveryapp.Fruits;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocerydeliveryapp.AddToCardActivity;
import com.example.grocerydeliveryapp.R;
import com.example.grocerydeliveryapp.Vegetables.Vegetables;
import com.example.grocerydeliveryapp.Vegetables.VegetablesAdapter;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class FruitsAdapter extends RecyclerView.Adapter<FruitsAdapter.MyViewHolder> {



    //VegetablesList
    List<Fruits> fruits;
    Context parentContext;


    public FruitsAdapter(List<Fruits> fruits){

        this.fruits=fruits;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //converting xml od recyclerview-custom into view
        this.parentContext=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(parentContext);
        View view=inflater.inflate(R.layout.recyclerview_custom,parent,false);

        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //here we will set all the images and texts

        Fruits fruits= this.fruits.get(position);
        holder.vegetableName.setText(fruits.getFruitsName());
        holder.cost.setText(Integer.toString(fruits.getCost()));
        holder.description.setText(fruits.getDescription());

        Bitmap imgBmp= getImageBitmap(fruits);
        holder.vegetableImages.setImageBitmap(imgBmp);


        //item click listener
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(parentContext, AddToCardActivity.class);
                intent.putExtra("name",holder.vegetableName.getText().toString());
                intent.putExtra("cost",Integer.parseInt(holder.cost.getText().toString()));
                intent.putExtra("description",holder.description.getText().toString());
                intent.putExtra("image", convertIntoByteImage(imgBmp));

                parentContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }



    //View holder hold views inside card vew (i.e recyclerview_custom.xml
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        //views of recycler_view_custom
        ImageView vegetableImages;
        TextView vegetableName;
        TextView cost;
        TextView description;
        CardView item;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //assign ids of recyclerview_custom.xml
            vegetableImages=itemView.findViewById(R.id.imageView);
            vegetableName=itemView.findViewById(R.id.nameText);
            cost=itemView.findViewById(R.id.costText);
            description=itemView.findViewById(R.id.descriptionText);
            item=itemView.findViewById(R.id.cardView);
        }
    }

    //convert image into byte
    public byte[] convertIntoByteImage(Bitmap img){

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG,0,outputStream);
        return outputStream.toByteArray();
    }

    public Bitmap getImageBitmap(Fruits fruits){
        byte[] data= fruits.getDrawableId();
        Bitmap bmp= BitmapFactory.decodeByteArray(data,0, data.length);
        return bmp;
    }
}
