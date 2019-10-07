package com.example.myapplication;
//package socket.client;


//ackage com.tistory.webnautes.userecyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {




    private ArrayList<Dictionary> mArrayList;
    private CustomAdapter mAdapter;
    private int count = -1;

    //socket communication
    private String html = "";

    private Handler mHandler;
    private Socket socket;
    private String name;
    private BufferedReader networkReader;
    private BufferedWriter networkWriter;
    private String ip = "192.168.0.12"; // IP
    private int port = 9999; // PORT번호

    @Override

    protected void onStop() {

        // TODO Auto-generated method stub

        super.onStop();

        try {
            socket.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
        int numberOfColumns = 2;
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        mArrayList = new ArrayList<>();

        mAdapter = new CustomAdapter( mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        ImageButton buttonInsert = (ImageButton) findViewById(R.id.imageButton5);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;

                Dictionary data = new Dictionary(count+"","Apple" + count, "사과" + count);

                //mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
                mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입

                mAdapter.notifyDataSetChanged();             }
        });


    }



}


//add info
/*

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
 */


/*
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
        sampleDB = this.openOrCreateDatabase(dbname, MODE_PRIVATE,null);
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

*/

//new info
/*
//db

    private final String dbname = "testdb";
    private final String dbname2 = "first_infodb";
    private final String tablename = "info";
    private final String tablename2 = "first_info";
    private SQLiteDatabase sampleDB = null;
    private SQLiteDatabase sampleDB2 = null;






    //first add window
    //first add info button_cancle
    private ImageButton first_cancle;
    private int first_cancleval;

    //first add info button add
    private ImageButton first_add;
    private int frist_addval;

    private EditText first_name;
    private String f_name;

    private TextView first_birth;
    private String f_birth;

    private TextView first_address;
    private String f_address;

    private Spinner spinner_abo;
    private int f_spval;

    private ArrayList<String> spinner_abo1 = new ArrayList<>();

    private Switch mf_switch;

    //date picker
    private DatePicker dp;
    private Button btnGet;
    private TextView tvw;
    private Calendar cal;
    private int year;
    private int month;
    private int dayOfMonth;

    //date picker end

    //new info window

        //등록,취소 버튼


        //이름입력

        first_name = (EditText)findViewById(R.id.editText3);
        f_name = first_name.getText().toString();

        //성별 스위치
        mf_switch = (Switch)findViewById(R.id.switch1);


        spinner_abo1.add("A형");
        spinner_abo1.add("B형");
        spinner_abo1.add("O형");
        spinner_abo1.add("AB형");
        spinner_abo = findViewById(R.id.spinner);
        ArrayAdapter adpter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinner_abo1);
        spinner_abo.setAdapter(adpter2);

        //address
        first_address = (EditText)findViewById(R.id.editText);
        f_address = first_address.getText().toString();

        //date picker
        cal=Calendar.getInstance();
        year = cal.get(cal.YEAR);
        month = cal.get(cal.MONTH);
        dayOfMonth = cal.get(cal.DAY_OF_MONTH);

//first info db
        sampleDB2 = this.openOrCreateDatabase(dbname2, MODE_PRIVATE,null);
        sampleDB2.execSQL("CREATE TABLE IF NOT EXISTS "+ tablename2 + "(name_val VARCHAR(40), year_val VARCHAR(10),month_val VARCHAR(10),day_val VARCHAR(10),mfsw_val VARCHAR(10),abo_val VARCHAR(20) ,address_val VARCHAR(400))");
        //sampleDB.execSQL("DELETE FROM " + tablename2);
//  sampleDB.execSQL("INSERT INTO" + tablename2 + "(title , main, sp_val, rtval) Values("+title+","+main+","+sp_val+","+rtval+",111)");
        sampleDB2.close();
        showDB();

        //FIRST UI CODE END

        //버튼누르면 정보 추가

        first_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_spval = spinner_abo.getSelectedItemPosition();
                f_name = first_name.getText().toString();
                year = cal.get(cal.YEAR);
                month = cal.get(cal.MONTH);
                dayOfMonth = cal.get(cal.DAY_OF_MONTH);
                //스위치 상태 코드 보완(추가) 필요
                f_address = first_address.getText().toString();


                sampleDB.execSQL("INSERT INTO" + tablename + "(f_spval , f_name, year, month,dayofmonth,f_address) Values("+f_spval+","+f_name+","+year+","+month+","+dayOfMonth+","+f_address+",111)");
            }
        });

 */
