package com.example.cruisetrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DestinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_list);

        ImageView imgAlaska = (ImageView) findViewById(R.id.alaskaImg);
        ImageView imgEuro = (ImageView) findViewById(R.id.euroImg);

        imgAlaska.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DestinationActivity.this, AlaskaActivity.class);
                startActivity(i);
            }
        });

        imgEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DestinationActivity.this, EuropeActivity.class);
                startActivity(i);
            }
        });
    }
}
