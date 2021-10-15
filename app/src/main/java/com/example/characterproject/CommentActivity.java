package com.example.characterproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    String databaseName = "dataDB";
    String databseTable = "datatbl";
    SQLiteDatabase db;
    DBHelper dbHelper;
    Cursor cursor;
    EditText edt_write;
    Button btn_Okey;
    String comment;
    List<String> list;
    ArrayAdapter arrayadapter;
    int dbID;
    int id;
    ListView listview;
    String query;
    Intent intent;
    ContentValues contentValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        edt_write = findViewById(R.id.edt_write);
        btn_Okey = findViewById(R.id.btn_Okey);
        dbHelper = new DBHelper(this, databaseName, null, 1, databseTable);
        db = dbHelper.getWritableDatabase();
        listview = (ListView) findViewById(R.id.list_comment);
        list = new ArrayList<>();
        arrayadapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);

        intent = getIntent();
        id = intent.getIntExtra("id", 0);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + "member" + id + "(" //시작이 숫자로 되면안되서 member라고 임의로 넣어주었다dur
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,Comment TEXT)"); //테이블이 존재하지않으면 테이블 생성
        query = "SELECT * FROM " + "member" + id; //테이블을 조회
        cursor = db.rawQuery(query, null); //가리킨다. rawQuery 커서를 가리킨다.
        while (cursor.moveToNext()) {//while문을 통해 커서를 하나씩 하나씩 가르킨다.
            String comment=cursor.getString(1);
            list.add(comment);
            listview.setAdapter(arrayadapter);
        }

            btn_Okey.setOnClickListener(v -> {
                //리스트뷰에 추가
                String query = "SELECT * FROM " +  "member" + id; //테이블을 조회
                cursor = db.rawQuery(query, null); //가리킨다. rawQuery 커서를 가리킨다.
                comment = edt_write.getText().toString();
                if(!(comment.isEmpty())) {
                    contentValues = new ContentValues();
                    contentValues.put("Comment", comment);
                    db.insert("member" + id, null, contentValues); //테이블에 데이터를 생성
                }
                while (cursor.moveToNext()) {//while문을 통해 커서를 하나씩 하나씩 가르킨다.
                    intent = getIntent();
                    id = intent.getIntExtra("id", 0);
                    dbID = cursor.getInt(0);
                    if (id == dbID - 1) {
                        Log.d("dddddddd", dbID + "");
                        list.add(comment);
                        listview.setAdapter(arrayadapter);
                    }
                }
            });
        }
}
