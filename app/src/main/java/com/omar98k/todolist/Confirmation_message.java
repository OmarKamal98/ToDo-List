package com.omar98k.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Confirmation_message extends AppCompatActivity {
    final String package1  =  "com.google.android.gm" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_message);
    }

    public void OnClickcancel(View view) {

        startActivity(new Intent(this,Login.class));
        finish();
    }

    public void GoToEmail(View view) {


// return true if gmail is installed
        boolean isGmailInstalled = isAppInstalled(this, package1);

        Intent intent = new Intent(Intent.ACTION_SEND);


        if (isGmailInstalled) {
            intent.setType("text/html");
            intent.setPackage(package1);
            startActivity(intent);
        } else {  // allow user to choose a different app to send email with
            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "choose an email client"));
        }
    }
// Method to check if app is installed
        private boolean isAppInstalled(Context context, String packageName) {
            try {
                context.getPackageManager().getApplicationInfo(packageName, 0);
                return true;
            } catch (PackageManager.NameNotFoundException e) {
                return false;
            }
        }



}