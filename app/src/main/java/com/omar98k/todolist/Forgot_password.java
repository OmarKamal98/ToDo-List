package com.omar98k.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_password extends AppCompatActivity {
     FirebaseAuth mAuth;

    EditText entEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        entEmail=(EditText) findViewById(R.id.editText5);
    }

    public void onClickrecover(View view) {
        resetPass();

    }


    private void resetPass(){
        String emaill=entEmail.getText().toString().trim();

        if(emaill.isEmpty()){
            entEmail.setError("email is required !");
            entEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emaill).matches()){
            entEmail.setError("Please provide valid email !");
            entEmail.requestFocus();
            return;
        }

            // [START send_password_reset]
            FirebaseAuth auth = FirebaseAuth.getInstance();


            auth.sendPasswordResetEmail(emaill)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(Forgot_password.this,Confirmation_message.class));
                                Toast.makeText(Forgot_password.this, "Email Been sent", Toast.LENGTH_LONG).show();
                                Log.d("TAG", "Email sent.");
                            }else {
                                Toast.makeText(Forgot_password.this,"This email is not registered",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
            // [END send_password_reset]


        }



    public void backFromForgetPass(View view) {
      finish();
    }
}