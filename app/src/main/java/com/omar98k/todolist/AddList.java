package com.omar98k.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class AddList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
    }

    public void OnClickCancle(View view) {
        finish();
    }

    public void onClickSave(View view) {
    }
}