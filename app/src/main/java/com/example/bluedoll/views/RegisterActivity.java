package com.example.bluedoll.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bluedoll.R;
import com.example.bluedoll.database.UsersHelper;

import java.text.DecimalFormat;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    EditText fullName, emailText, passText, rePassText;
    RadioGroup rb_group;
    CheckBox cb_terms;
    Button dob_btn, registerBtn;
    TextView loginHere, dob_text;
    DatePickerDialog.OnDateSetListener dateSetListener;

    UsersHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    public void init() {
        fullName = findViewById(R.id.fullNameText);
        emailText = findViewById(R.id.emailText);
        passText = findViewById(R.id.passText);
        rePassText = findViewById(R.id.rePassText);
        rb_group = findViewById(R.id.rb_group);
        loginHere = findViewById(R.id.login_here);
        cb_terms = findViewById(R.id.cb_terms);
        dob_btn = findViewById(R.id.datePicker);
        registerBtn = findViewById(R.id.register_btn);
        dob_text = findViewById(R.id.dob_text);

        db = new UsersHelper(getApplicationContext());


        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = month + "/" + day + "/" + year;
                dob_text.setText(date);
            }
        };

        registerBtn.setOnClickListener(this::RegisterOnClick);
        loginHere.setOnClickListener(this::LoginHereOnClick);

    }


    public void datePickerDialog(View v) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(RegisterActivity.this,
                android.R.style.Theme_Holo_Dialog, dateSetListener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void RegisterOnClick(View v) {
        String email = emailText.getText().toString();
        String name = fullName.getText().toString();
        String password = passText.getText().toString();
        String passwordC = rePassText.getText().toString();
        String date = dob_text.getText().toString();

        int selectedGender = rb_group.getCheckedRadioButtonId();

        RadioButton radioButton = findViewById(selectedGender);

        if (email.isEmpty() || !email.contains("@") || !email.endsWith(".com") || email.contains("@.")) {
            Toast.makeText(RegisterActivity.this, "Email must be filled with a valid format", Toast.LENGTH_SHORT).show();
        } else if (name.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Name must be filled", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty() || password.length() < 6) {
            Toast.makeText(RegisterActivity.this, "Password must be filled and contains at least 6 characters", Toast.LENGTH_SHORT).show();
        } else if (passwordC.isEmpty() || !passwordC.equals(password)) {
            Toast.makeText(RegisterActivity.this, "Password confirmation must be filled and match with the password", Toast.LENGTH_SHORT).show();
        } else if (selectedGender == -1) {
            Toast.makeText(RegisterActivity.this, "Gender must be chosen", Toast.LENGTH_SHORT).show();
        } else if (date.equals("Date of Birth")) {
            Toast.makeText(RegisterActivity.this, "Birthday must be filled", Toast.LENGTH_SHORT).show();
        } else if (!cb_terms.isChecked()) {
            Toast.makeText(RegisterActivity.this, "You must agreed on the terms and conditions", Toast.LENGTH_SHORT).show();
        }
        else if(db.checkRegister(email) == true){
            Toast.makeText(RegisterActivity.this, "Email must be unique", Toast.LENGTH_SHORT).show();
        }
        else{
            if(db.getUsersData() == null){
                db.insertUsers("US00"+1,name,email,password,radioButton.getText().toString(),"Admin");
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }else{
                int sizeDB = db.getUsersData().size();
                String lastID = db.getUsersData().get(sizeDB-1).getUserID();
                String fLastID = lastID.substring(3,5);
                DecimalFormat df = new DecimalFormat("000");
                String id = df.format(Integer.parseInt(fLastID)+1);

                db.insertUsers("US"+id,name,email,password,radioButton.getText().toString(),"User");
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        }

    }

    public void LoginHereOnClick(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}