package com.example.grocerydeliveryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, "GroceryDB", factory, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table User" +
                "(id integer primary key autoincrement, " +
                "User_Name text, " +
                "PhoneNum integer, " +
                "address text)");

        db.execSQL("create table Orders(" +
                "o_id integer primary key autoincrement," +
                "o_name text," +
                "o_item_no integer," +
                "o_cost integer)");

        db.execSQL("create table Cart(" +
                "cart_id integer primary key autoincrement," +
                "o_id integer," +
                "user_id integer," +
                "FOREIGN KEY (o_id) REFERENCES Orders(o_id)," +
                "FOREIGN KEY (user_id) REFERENCES User(id)" +
                ")");

        db.execSQL("create table Vegetables(" +
                "v_id integer primary key autoincrement," +
                "v_name text," +
                "v_cost integer," +
                "v_description text," +
                "v_img blob)");

        db.execSQL("create table Fruits(" +
                "f_id integer primary key autoincrement," +
                "f_name text," +
                "f_cost integer," +
                "f_description text," +
                "f_img blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //for admin
    Bitmap image;
    long inserted=0;
    public long insertItems(String tableName, String itemName, int itemCost, String itemDescription, byte[] itemImage){

        //dml languages: insert/update/delete -> getWritableDatabase()
        //select query -> getReadableDatabase()
        SQLiteDatabase db=getWritableDatabase();


        ContentValues cv= new ContentValues();

        //switch case for options, either fruit or vegetable
        switch (tableName){

            case "Vegetables":
                cv.put("v_name", itemName);
                cv.put("v_cost", itemCost);
                cv.put("v_description", itemDescription);
                cv.put("v_img",itemImage);
                inserted=db.insert(tableName,null,cv);
                break;
            case "Fruits":
                cv.put("f_name", itemName);
                cv.put("f_cost", itemCost);
                cv.put("f_description", itemDescription);
                cv.put("f_img", itemImage);
                inserted= db.insert(tableName,null,cv);
                break;
            default:
                break;
        }

        return inserted;
    }

    //getting vegetables records
    public Cursor getAllTableRecords(String table){

        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+table+"",null);

        return cursor;
    }


    public long addCart(String foodName, String personName, String phone, String address, String price, int count){
        inserted=0;
        ContentValues personContents=new ContentValues();
        ContentValues orderContents=new ContentValues();
        SQLiteDatabase db=getWritableDatabase();


        personContents.put("User_Name",personName);
        personContents.put("PhoneNum",Integer.parseInt(phone));
        personContents.put("address",address);

        orderContents.put("o_name",foodName);
        orderContents.put("o_item_no",count);
        orderContents.put("o_cost",Integer.parseInt(price));

        long c1= db.insert("User",null,personContents);
        long c2=db.insert("Orders", null, orderContents);

        if (c1>0 && c2>0){
            inserted=1;
        }

        return inserted;
    }

    int deleted=0;
    public int deleteItems(String tableName, String name){

        SQLiteDatabase db=getWritableDatabase();

        switch (tableName){
            case "Fruits":
                //db.execSQL("delete from "+tableName+" where f_name="+name+"");
                deleted= db.delete(tableName, "f_name" + "=" + name, null);
                break;
            case "Vegetables":
                //db.execSQL("delete from "+tableName+" where v_name="+name+"");
                deleted= db.delete(tableName, "v_name" + "=" + name, null);
                break;
        }

        return 1;
    }

}
