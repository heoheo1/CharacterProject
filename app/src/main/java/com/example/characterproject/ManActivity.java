package com.example.characterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ManActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_man_hair,img_man_clothes,img_man_shoes,img_man_Accessories;
    Button btn_save;
    EditText edt_man;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man);

        btn_save=findViewById(R.id.btn_save);

        init();
        setOninit();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String man_name=edt_man.getText().toString();
                sharedPreferences =getSharedPreferences("pref",MODE_PRIVATE);
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putString("man_name",man_name);
                editor.commit();
                Intent intent =new Intent(ManActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_manHair1 :
                image_set(img_man_hair,R.drawable.man_hair1);
                setPr("manhair",R.drawable.man_hair1);
                break;
            case R.id.change_manHair2 :
                image_set(img_man_hair,R.drawable.man_hair2);
                setPr("manhair",R.drawable.man_hair2);
                break;
            case R.id.change_manclothes1 :
                image_set(img_man_clothes,R.drawable.man_clothes1);
                setPr("manclothes",R.drawable.man_clothes1);
                break;
            case R.id.change_manclothes2 :
                image_set(img_man_clothes,R.drawable.man_clothes2);
                setPr("manclothes",R.drawable.man_clothes2);
                break;
            case R.id.change_manshoes1 :
                image_set(img_man_shoes,R.drawable.man_shoes1);
                setPr("manshoes",R.drawable.man_shoes1);
                break;
            case R.id.change_manshoes2 :
                image_set(img_man_shoes,R.drawable.man_shoes2);
                setPr("manshoes",R.drawable.man_shoes2);
                break;
            case R.id.change_manAccessories1 :
                image_set(img_man_Accessories,R.drawable.man_accessories1);
                setPr("manaccessories",R.drawable.man_accessories1);
                break;
            case R.id.change_manAccessories2 :
                image_set(img_man_Accessories,R.drawable.man_accessories2);
                setPr("manaccessories",R.drawable.man_accessories2);
                break;
        }
    }
    public void image_set(ImageView imageView ,int drawble){
        imageView.setImageResource(drawble);
    }

    public void init( ){
        img_man_hair=findViewById(R.id.img_man_hair);
        img_man_clothes=findViewById(R.id.img_man_clothes);
        img_man_shoes=findViewById(R.id.img_man_shoes);
        img_man_Accessories=findViewById(R.id.img_man_Accessories);
        edt_man=findViewById(R.id.edt_man);
    }

    public void setOninit(){
        findViewById(R.id.change_manHair1).setOnClickListener(this);
        findViewById(R.id.change_manHair2).setOnClickListener(this);
        findViewById(R.id.change_manclothes1).setOnClickListener(this);
        findViewById(R.id.change_manclothes2).setOnClickListener(this);
        findViewById(R.id.change_manshoes1).setOnClickListener(this);
        findViewById(R.id.change_manshoes2).setOnClickListener(this);
        findViewById(R.id.change_manAccessories1).setOnClickListener(this);
        findViewById(R.id.change_manAccessories2).setOnClickListener(this);
    }
    public void setPr(String img_name,int img){

        sharedPreferences =getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putInt(img_name,img);
        editor.commit();

    }
}