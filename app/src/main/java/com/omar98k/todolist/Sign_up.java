package com.omar98k.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void haveAccount(View view) {
        Intent intent=new Intent(Sign_up.this,Login.class);
        startActivity(intent);
        finish();
    }

    public void login(View view) {
        Intent intent=new Intent(Sign_up.this,Login.class);
        startActivity(intent);
        finish();
    }
}