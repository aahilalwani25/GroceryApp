package com.example.grocerydeliveryapp.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocerydeliveryapp.R;
import com.google.android.material.button.MaterialButton;

public class AdminLoginActivity extends AppCompatActivity {

    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        email =findViewById(R.id.enteruremail);
        password =findViewById(R.id.password);

        MaterialButton LoginBtn =findViewById(R.id.loginbtn);
        //admin and admin

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    //correct
                    Toast.makeText(AdminLoginActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AdminLoginActivity.this,MainAdminActivity.class);
                    startActivity(intent);
                }else
                    //incorrect
                    Toast.makeText(AdminLoginActivity.this, "LOGIN Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}