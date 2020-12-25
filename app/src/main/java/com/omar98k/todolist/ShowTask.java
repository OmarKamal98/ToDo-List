package com.omar98k.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TintInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ShowTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);
    }

    public void backtask(View view) {
    }

    public void OnClickCreateNewtask(View view) {
        Intent intent=new Intent(ShowTask.this,AddTask.class);
        startActivity(intent);
        finish();
    }

    public void delete(View view) {
    }
}