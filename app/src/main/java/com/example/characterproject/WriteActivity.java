package com.example.characterproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class WriteActivity extends AppCompatActivity {

    ImageView imageView;
    Button btn_OK;
    EditText edt_contents;
    String  databaseName ="dataDB";
    String databseTable="datatbl";
    SQLiteDatabase db;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        edt_contents=findViewById(R.id.edt_contents);
        btn_OK=findViewById(R.id.btn_OK);
        imageView=findViewById(R.id.picture_img);
        DBHelper dbHelper =new DBHelper(this,databaseName,null,1,databseTable);
        db=dbHelper.getWritableDatabase();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent,0);

            }
        });
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contents=edt_contents.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put("Image", String.valueOf(selectedImageUri));
                contentValues.put("Contents",contents);
                db.insert(databseTable, null, contentValues); //테이블에 데이터를 생성
                Intent intent = new Intent(getApplicationContext(), SaveActivity.class); //데이터를 생성후 Login 화면으로 이동
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();


            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                try {

                    selectedImageUri =data.getData();
                    Glide.with(getApplicationContext()).load(selectedImageUri).into(imageView);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
        }
    }

}