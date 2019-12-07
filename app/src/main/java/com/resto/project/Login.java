package com.resto.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class Login extends AppCompatActivity {

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.reset_button)
    Button resetButton;
    @BindView(R.id.btn_signup)
    Button btnSignup;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        firebaseAuth = FirebaseAuth.getInstance();

        //auto login process
        //move to main activity if user already sign in
        if (firebaseAuth.getCurrentUser() != null) {
            // User is logged in
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.this.startActivity(new Intent(Login.this, SignUp.class));
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.this.startActivity(new Intent(Login.this, SignUp.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String useremail = email.getText().toString();
                final String userpassword = password.getText().toString();

                if (TextUtils.isEmpty(useremail)) {
                    Toast.makeText(Login.this.getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(userpassword)) {
                    Toast.makeText(Login.this.getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //login user
                firebaseAuth.signInWithEmailAndPassword(useremail,userpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {

                                    if (userpassword.length() < 6) {
                                        password.setError(Login.this.getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(Login.this, Login.this.getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
                                    }

                                } else if(useremail.contentEquals("admin@admin.com")){
                                    Login.this.startActivity(new Intent(Login.this, MainActivity.class));
                                    Login.this.finish();
                                } else {
                                    Login.this.startActivity(new Intent(Login.this, MainActivity.class));
                                    Login.this.finish();
                                }
                            }
                        });

            }
        });

    }
}
