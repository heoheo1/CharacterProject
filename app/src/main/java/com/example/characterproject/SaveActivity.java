package com.example.characterproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SaveActivity extends AppCompatActivity {
    FloatingActionButton btn_fb;
    Intent intent;
    ArrayList itemArrayList;
    MyAdapter adapter;
    String  databaseName ="dataDB";
    String databseTable = "datatbl";
    SQLiteDatabase db;
    DBHelper dbHelper;
    Cursor cursor;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        dbHelper =new DBHelper(this,databaseName,null,1,databseTable);
        db =dbHelper.getWritableDatabase();

        itemArrayList =new ArrayList();
        adapter =new MyAdapter(this,itemArrayList);

        btn_fb=findViewById(R.id.btn_fb);

        btn_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent =new Intent(getApplicationContext(),WriteActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        recyclerView =findViewById(R.id.recyclerView);
        itemArrayList =new ArrayList();
        adapter =new MyAdapter(this,itemArrayList);

        String query = "SELECT * FROM " +databseTable; //테이블을 조회
        cursor = db.rawQuery(query, null); //가리킨다. rawQuery 커서를 가리킨다.
        while (cursor.moveToNext()) {//while문을 통해 커서를 하나씩 하나씩 가르킨다.
            String image =cursor.getString(1);
            String content =cursor.getString(2);
            itemArrayList.add(new Item(content, image));
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}