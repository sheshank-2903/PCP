package com.college.rase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends Activity {


    private EditText eMail;
    private EditText pass;
    String email;
    String password;
    private TextView forgotPassoword;
    private Button login;
    private  TextView createOne;
    FirebaseUser user;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        eMail = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        forgotPassoword= findViewById(R.id.forgotPassword);
        login=findViewById(R.id.loginButton);
        createOne=findViewById(R.id.createOne);
        user=FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(LoginPage.this, Homepage.class);
            startActivity(intent);
            finish();
        }
        mauth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=eMail.getText().toString().trim();
                password=pass.getText().toString().trim();
//                Toast.makeText(LoginPage.this , ""+task.getException() , Toast.LENGTH_LONG).show();
                mauth.signInWithEmailAndPassword(email , password).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull  Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        checkIfEmailVerified();
                                    }else{
                                        Toast.makeText(LoginPage.this , ""+task.getException() , Toast.LENGTH_LONG).show();
                                    }
                            }
                        });
            }
        });

        createOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(LoginPage.this , SignUpPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
            }
        });

        forgotPassoword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(LoginPage.this , ResetPassword.class);
                    startActivity(intent);
            }
        });
    }

    void checkIfEmailVerified(){
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
            Toast.makeText(LoginPage.this , "Welcome to RASE" , Toast.LENGTH_LONG).show();
            Intent intent=new Intent(LoginPage.this , Homepage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
    }
}