package com.example.characterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class WomanActivity extends AppCompatActivity {

    ImageView change_womanHair1,img_woman_hair,change_womanHair2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woman);
        //헤어
        change_womanHair1 = findViewById(R.id.change_womanHair1);
        change_womanHair2=findViewById(R.id.change_womanHair2);
        img_woman_hair= findViewById(R.id.img_woman_hair);


        change_womanHair1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_woman_hair.setImageResource(R.drawable.woman_hair1);
            }
        });
        change_womanHair2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_woman_hair.setImageResource(R.drawable.woman_hair2);
            }
        });
    }
}