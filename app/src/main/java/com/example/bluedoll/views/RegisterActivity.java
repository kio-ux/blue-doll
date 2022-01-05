package com.example.bluedoll.views;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bluedoll.R;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    EditText fullName, emailText, passText, rePassText;
    RadioGroup rb_group;
    CheckBox cb_terms;
    Button dob_btn, registerBtn;
    TextView loginHere, dob_text;
    DatePickerDialog.OnDateSetListener dateSetListener;


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

        registerBtn.setOnClickListener(this::RegisterOnClick);
        loginHere.setOnClickListener(this::LoginHereOnClick);


        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = month + "/" + day + "/" + year;
                dob_text.setText(date);
            }
        };
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

    }

    public void LoginHereOnClick(View v) {

    }
}