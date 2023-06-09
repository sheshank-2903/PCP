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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;

public class SignUpPage extends Activity {

    private EditText emailField;
    private EditText passwordField;
    private EditText confrimPasswordField;
    private Button signUpButton;
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        emailField=findViewById(R.id.email);
        passwordField=findViewById(R.id.password);
        confrimPasswordField=findViewById(R.id.confrimPassword);
        signUpButton=findViewById(R.id.signUpButton);
        mauth=FirebaseAuth.getInstance();
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String email=emailField.getText().toString().trim();
                    String password=passwordField.getText().toString().trim();
                    String confrimPassword=confrimPasswordField.getText().toString().trim();
                    if(password.equals(confrimPassword)){
                        Toast.makeText(SignUpPage.this, ""+email+"=="+password, Toast.LENGTH_SHORT).show();
                        mauth.createUserWithEmailAndPassword(email , password)
                                .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                Intent intent=new Intent(SignUpPage.this , Profile.class);
                                                intent.putExtra("from" , "signUp");
                                                startActivity(intent);
                                                finish();
                                            }
                                            else {
                                                // If sign in fails, display a message to the user
                                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                            }
                                    }
                                });
                    }else{
                        Toast.makeText(SignUpPage.this, "Password is not same in both fields" , Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
}