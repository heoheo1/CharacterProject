package com.example.characterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class WomanActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_woman_hair,img_woman_clothes,img_woman_shoes,img_woman_Accessories;
    Button btn_save;
    EditText edt_woman;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woman);

        btn_save=findViewById(R.id.btn_save);

        init();
        setOninit();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String woman_name=edt_woman.getText().toString();
                sharedPreferences =getSharedPreferences("pref",MODE_PRIVATE);
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putString("woman_name",woman_name);
                editor.commit();
                Intent intent =new Intent(WomanActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_womanHair1 :
                image_set(img_woman_hair,R.drawable.woman_hair1);
                setPr("womanhair",R.drawable.woman_hair1);
                break;
            case R.id.change_womanHair2 :
                image_set(img_woman_hair,R.drawable.woman_hair2);
                setPr("womanhair",R.drawable.woman_hair2);
                break;
            case R.id.change_womanclothes1 :
                image_set(img_woman_clothes,R.drawable.woman_clothes1);
                setPr("womanclothes",R.drawable.woman_clothes1);
                break;
            case R.id.change_womanclothes2 :
                image_set(img_woman_clothes,R.drawable.woman_clothes2);
                setPr("womanclothes",R.drawable.woman_clothes2);
                break;
            case R.id.change_womanshoes1 :
                image_set(img_woman_shoes,R.drawable.woman_shoes1);
                setPr("womanshoes",R.drawable.woman_shoes1);
                break;
            case R.id.change_womanshoes2 :
                image_set(img_woman_shoes,R.drawable.woman_shoes2);
                setPr("womanshoes",R.drawable.woman_shoes2);
                break;
            case R.id.change_womanAccessories1 :
                image_set(img_woman_Accessories,R.drawable.woman_accessories1);
                setPr("womanaccessories",R.drawable.woman_accessories1);
                break;
            case R.id.change_womanAccessories2 :
                image_set(img_woman_Accessories,R.drawable.woman_accessories2);
                setPr("womanaccessories",R.drawable.woman_accessories2);
                break;
        }
    }
    public void image_set(ImageView imageView ,int drawble){
        imageView.setImageResource(drawble);
    }

    public void init( ){
        img_woman_hair=findViewById(R.id.img_woman_hair);
        img_woman_clothes=findViewById(R.id.img_woman_clothes);
        img_woman_shoes=findViewById(R.id.img_woman_shoes);
        img_woman_Accessories=findViewById(R.id.img_woman_Accessories);
        edt_woman=findViewById(R.id.edt_woman);
    }

    public void setOninit(){
        findViewById(R.id.change_womanHair1).setOnClickListener(this);
        findViewById(R.id.change_womanHair2).setOnClickListener(this);
        findViewById(R.id.change_womanclothes1).setOnClickListener(this);
        findViewById(R.id.change_womanclothes2).setOnClickListener(this);
        findViewById(R.id.change_womanshoes1).setOnClickListener(this);
        findViewById(R.id.change_womanshoes2).setOnClickListener(this);
        findViewById(R.id.change_womanAccessories1).setOnClickListener(this);
        findViewById(R.id.change_womanAccessories2).setOnClickListener(this);
    }
    public void setPr(String img_name,int img){

        sharedPreferences =getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putInt(img_name,img);
        editor.commit();

    }

}