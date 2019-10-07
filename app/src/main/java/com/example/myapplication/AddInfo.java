package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class AddInfo extends AppCompatActivity {

    private EditText new_title;
    private String title;

    private EditText new_main;
    private String main;

    private ArrayList<String> spinner_list = new ArrayList<>();
    private Spinner spinner;
    private int sp_val;

    private RatingBar ratting;
    private float rtval;

    //등록버튼
    private ImageButton add1;
    private int add1_val;

    //취소버튼(임시 확인버튼)
    private ImageButton cancle1;
    private int cancle_val;

    private final String dbname = "testdb";
   // private final String dbname2 = "first_infodb";
    private final String tablename = "info";
    //private final String tablename2 = "first_info";
    private SQLiteDatabase sampleDB = null;
   // private SQLiteDatabase sampleDB2 = null;

    // MainActivity 에서 선택한 연산이 더하기 연산인지 빼기 연산인지 구분하기 위한 변수
    // 더하기라면 true 를 빼기라면 false 를 전달 받음.
    private boolean isPlus;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        //메인 타이틀
        new_title = (EditText) findViewById(R.id.editText2);
        title = new_title.getText().toString();
//메인 본문
        new_main = (EditText) findViewById(R.id.editText5);
        main = new_main.getText().toString();

        //스피너

        spinner_list.add("개인(본인)정보");
        spinner_list.add("보호자 정보");
        spinner_list.add("의료 정보");
        spinner_list.add("병원 정보");
        spinner_list.add("기타 정보");
        spinner = findViewById(R.id.spinner2);
        ArrayAdapter adpter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinner_list);
        spinner.setAdapter(adpter);

        //레이팅 바
        ratting = (RatingBar) findViewById(R.id.ratingBar1);
        rtval = ratting.getRating();

        Toast.makeText(this, String.valueOf(rtval), Toast.LENGTH_SHORT).show();

        ratting.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            public void onRatingChanged(
                    RatingBar ratingBar, float rating, boolean fromUser) {
                rtval = ratting.getRating();
                Toast.makeText(getApplicationContext(), "rating:" + String.valueOf(rtval), Toast.LENGTH_LONG).show();
            }
        });

//스피너 감지

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp_val = spinner.getSelectedItemPosition();
                Toast.makeText(getApplicationContext(), String.valueOf(sp_val), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //db
        sampleDB =this.openOrCreateDatabase(dbname, MODE_PRIVATE,null);
        sampleDB.execSQL("CREATE TABLE IF NOT EXISTS "+ tablename + "(title VARCHAR(40), main VARCHAR(400),sp_val VARCHAR(10),rtval VARCHAR(20) )");
        //sampleDB.execSQL("DELETE FROM " + tablename);
//        sampleDB.execSQL("INSERT INTO" + tablename + "(title , main, sp_val, rtval) Values("+title+","+main+","+sp_val+","+rtval+",111)");
        sampleDB.close();
        showDB();

//버튼누르면 정보 갱신

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp_val = spinner.getSelectedItemPosition();
                title = new_title.getText().toString();
                rtval = ratting.getRating();
                sp_val = spinner.getSelectedItemPosition();
                sampleDB.execSQL("INSERT INTO" + tablename + "(title , main, sp_val, rtval) Values("+title+","+main+","+sp_val+","+rtval+",111)");
            }
        });

    }

    private void showDB() {

        SQLiteDatabase readDB = this.openOrCreateDatabase(dbname, MODE_PRIVATE, null);

        Cursor c = readDB.rawQuery("SELECT * FROM " + tablename, null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String title = c.getString(c.getColumnIndex("title"));
                    String main = c.getString(c.getColumnIndex("main"));
                    String sp_val = c.getString(c.getColumnIndex("sp_val"));
                    String rtval = c.getString(c.getColumnIndex("rtval"));

                    String result = title + " " + main + " " + sp_val + " " + rtval;

                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                } while ( c.moveToNext() );
            }
        }

        readDB.close();
    }

}
