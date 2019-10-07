package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class FirstNew extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_new);

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
