package com.example.bluedoll.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bluedoll.R;
import com.example.bluedoll.database.DollsHelper;

public class DollDetailsActivity extends AppCompatActivity {

    TextView txtDetailDolls, txtDetailDescription;
    ImageView imgDolls;

    DollsHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doll_details);

        Integer dollID = getIntent().getIntExtra("dollID",0);

        txtDetailDolls = findViewById(R.id.txtDetailDolls);
        txtDetailDescription = findViewById(R.id.txtDetailDescription);
        imgDolls = findViewById(R.id.imgDolls);

        db = new DollsHelper(getApplicationContext());

        String dollNameDB = db.getDollsID(dollID).get(0).getDollName();

        String dollDescriptionDB =  db.getDollsID(dollID).get(0).getDollDesc();
        byte [] dollImageDB =  db.getDollsID(dollID).get(0).getDollImage();

        Bitmap bmp = BitmapFactory.decodeByteArray(dollImageDB, 0, dollImageDB.length);
        txtDetailDolls.setText(dollNameDB);
        txtDetailDescription.setText(dollDescriptionDB);
        imgDolls.setImageBitmap(bmp);
    }
}