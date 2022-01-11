package com.example.bluedoll.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bluedoll.R;
import com.example.bluedoll.database.UsersHelper;

public class LoginActivity extends AppCompatActivity {


    EditText etEmail, etPassword;
    Button btnLogin;
    TextView txtRegister;

    UsersHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);

        txtRegister = findViewById(R.id.txtRegister);

        db = new UsersHelper(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();


                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Email must be filled", Toast.LENGTH_SHORT).show();
                }
                else if (password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Password must be filled", Toast.LENGTH_SHORT).show();
                }
                else if(db.checkLogin(email,password)==false){
                    Toast.makeText(LoginActivity.this, "Email/Password not registered", Toast.LENGTH_SHORT).show();
                }
                else if(db.checkLogin(email,password)==true){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userID",db.getUsersDataLogin(email).get(0).getUserID());
                    intent.putExtra("userName",db.getUsersDataLogin(email).get(0).getUserName());
                    intent.putExtra("userRole",db.getUsersDataLogin(email).get(0).getUserRole());
                    startActivity(intent);
                }
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}