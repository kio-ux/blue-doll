package com.example.bluedoll.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bluedoll.R;
import com.example.bluedoll.models.Dolls;

import java.util.ArrayList;

public class ViewDollsAdapter extends ArrayAdapter<Dolls> {
    Context context;
    int resource;

    public ViewDollsAdapter(@NonNull Context context, int resource, ArrayList<Dolls> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource,parent, false);

        Integer id = getItem(position).getId();
        String name = getItem(position).getDollName();
        String description = getItem(position).getDollDesc();
        String creator = getItem(position).getDollCreator();
        byte [] imgDolls = getItem(position).getDollImage();

        Bitmap bmp = BitmapFactory.decodeByteArray(imgDolls, 0 , imgDolls.length);

        TextView txtName = convertView.findViewById(R.id.txtName);
        txtName.setText(name);

        TextView txtDescription = convertView.findViewById(R.id.txtDescription);
        txtDescription.setText(description);

        TextView txtCreator= convertView.findViewById(R.id.txtCreator);
        txtCreator.setText(creator);

        ImageView imageDoll = convertView.findViewById(R.id.imageDoll);
        imageDoll.setImageBitmap(bmp);

        Button btnView = convertView.findViewById(R.id.view_btn);
        Button btnEdit= convertView.findViewById(R.id.edit_btn);
        Button btnDelete = convertView.findViewById(R.id.delete_btn);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((ListView)parent).performItemClick(view,position,0);
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListView)parent).performItemClick(view,position,0);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListView)parent).performItemClick(view,position,0);
            }
        });




        return convertView;
    }
}
