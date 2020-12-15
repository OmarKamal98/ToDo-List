package com.omar98k.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void OnClickNextInFirstPage(View view) {
        Intent intent=new Intent(Splash.this,Sign_up.class);
        startActivity(intent);
        finish();
    }
}