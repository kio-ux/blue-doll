package com.example.bluedoll.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bluedoll.R;
import com.example.bluedoll.database.DollsHelper;

import java.io.ByteArrayOutputStream;

public class InsertDollsActivity extends AppCompatActivity {

    EditText etName,etDescription;
    ImageView imgDolls;

    Spinner spinnerImage;
    Button btnSave, btnDelete;

    DollsHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_dolls);

        Integer dollID = getIntent().getIntExtra("dollID",0);

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);

        spinnerImage = findViewById(R.id.spinnerImage);

        imgDolls = findViewById(R.id.imgDolls);

        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        db = new DollsHelper(getApplicationContext());

        String descImg [] = {"None","Dead Dolls","Cute Dolls","Teddy bear Dolls","Baby Dolls","Girl Dolls"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(InsertDollsActivity.this, android.R.layout.simple_spinner_dropdown_item,descImg);
        spinnerImage.setAdapter(arrayAdapter);


        if(dollID == 0){
            spinnerImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    if(spinnerImage.getItemAtPosition(position)==descImg[0]){
                        imgDolls.setImageResource(R.drawable.toyslogo);
                    }
                    else if(spinnerImage.getItemAtPosition(position).toString()==descImg[1]){
                        imgDolls.setImageResource(R.drawable.img_doll1);
                    }
                    else if(spinnerImage.getItemAtPosition(position).toString()==descImg[2]){
                        imgDolls.setImageResource(R.drawable.img_dolll2);
                    }
                    else if(spinnerImage.getItemAtPosition(position).toString()==descImg[3]){
                        imgDolls.setImageResource(R.drawable.img_doll3);
                    }
                    else if(spinnerImage.getItemAtPosition(position).toString()==descImg[4]){
                        imgDolls.setImageResource(R.drawable.img_doll4);
                    }
                    else if(spinnerImage.getItemAtPosition(position).toString()==descImg[5]){
                        imgDolls.setImageResource(R.drawable.img_doll5);
                    }

                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            insertDolls();

        }else{
            spinnerImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    if(spinnerImage.getItemAtPosition(position)==descImg[0]){
                        imgDolls.setImageResource(R.drawable.toyslogo);
                    }
                    else if(spinnerImage.getItemAtPosition(position).toString()==descImg[1]){
                        imgDolls.setImageResource(R.drawable.img_doll1);
                    }
                    else if(spinnerImage.getItemAtPosition(position).toString()==descImg[2]){
                        imgDolls.setImageResource(R.drawable.img_dolll2);
                    }
                    else if(spinnerImage.getItemAtPosition(position).toString()==descImg[3]){
                        imgDolls.setImageResource(R.drawable.img_doll3);
                    }
                    else if(spinnerImage.getItemAtPosition(position).toString()==descImg[4]){
                        imgDolls.setImageResource(R.drawable.img_doll4);
                    }
                    else if(spinnerImage.getItemAtPosition(position).toString()==descImg[5]){
                        imgDolls.setImageResource(R.drawable.img_doll5);
                    }

                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            updateDolls();
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnClickUpdate();
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.deleteDolls(dollID);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    public void insertDolls(){
        Integer dollID = getIntent().getIntExtra("dollID",0);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClick();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteDolls(dollID);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public void updateDolls(){
        Integer dollID = getIntent().getIntExtra("dollID",0);

        String dollNameDB = db.getDollsID(dollID).get(0).getDollName();
        String dollDescriptionDB =  db.getDollsID(dollID).get(0).getDollDesc();
        byte [] dollImageDB =  db.getDollsID(dollID).get(0).getDollImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(dollImageDB, 0, dollImageDB.length);


        etName.setText(dollNameDB);
        etDescription.setText(dollDescriptionDB);
        imgDolls.setImageBitmap(bmp);



    }




    public void btnClick(){
        Integer dollID = getIntent().getIntExtra("dollID",0);

        String name = etName.getText().toString();
        String description = etDescription.getText().toString();

        imgDolls.setDrawingCacheEnabled(true);
        imgDolls.buildDrawingCache();
        Bitmap bm = imgDolls.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imgConvert = stream.toByteArray();

        String userID = getIntent().getStringExtra("userID");

        if (name.isEmpty()){
            Toast.makeText(InsertDollsActivity.this, "Doll name must be filled",Toast.LENGTH_SHORT).show();
        }
        else if(db.checkDollName(name) == true){
            Toast.makeText(InsertDollsActivity.this, "Doll name must be unique",Toast.LENGTH_SHORT).show();
        }
        else if(description.isEmpty()){
            Toast.makeText(InsertDollsActivity.this, "Doll description must be filled",Toast.LENGTH_SHORT).show();
        }
        else if(spinnerImage.getSelectedItem().toString()=="None"){
            Toast.makeText(InsertDollsActivity.this, "Doll image must be chosen",Toast.LENGTH_SHORT).show();
        }
        else{
            db.insertDolls(name,description,imgConvert,userID);
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("userID",getIntent().getStringExtra("userID"));
            intent.putExtra("userName",getIntent().getStringExtra("userName"));
            intent.putExtra("userRole",getIntent().getStringExtra("userRole"));
            startActivity(intent);

        }
    }


    public void btnClickUpdate(){
        Integer dollID = getIntent().getIntExtra("dollID",0);

        String name = etName.getText().toString();
        String description = etDescription.getText().toString();

        imgDolls.setDrawingCacheEnabled(true);
        imgDolls.buildDrawingCache();
        Bitmap bm = imgDolls.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imgConvert = stream.toByteArray();

        String userID = getIntent().getStringExtra("userID");

        if (name.isEmpty()){
            Toast.makeText(InsertDollsActivity.this, "Doll name must be filled",Toast.LENGTH_SHORT).show();
        }
        else if(description.isEmpty()){
            Toast.makeText(InsertDollsActivity.this, "Doll description must be filled",Toast.LENGTH_SHORT).show();
        }
        else if(spinnerImage.getSelectedItem().toString()=="None"){
            Toast.makeText(InsertDollsActivity.this, "Doll image must be chosen",Toast.LENGTH_SHORT).show();
        }
        else{
            db.update(dollID, name,description,imgConvert);
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("userID",getIntent().getStringExtra("userID"));
            intent.putExtra("userName",getIntent().getStringExtra("userName"));
            intent.putExtra("userRole",getIntent().getStringExtra("userRole"));
            startActivity(intent);
        }
    }
    }

