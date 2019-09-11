package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText new_title;
    private String title;

    private EditText new_main;
    private String main;

    private ArrayList<String> spinner_list = new ArrayList<>();
    private Spinner spinner;
    private String spinner_val;

    private RatingBar ratting;
    private float rtval;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_info);
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
                int sp_val = spinner.getSelectedItemPosition();
                Toast.makeText(getApplicationContext(), String.valueOf(sp_val), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
