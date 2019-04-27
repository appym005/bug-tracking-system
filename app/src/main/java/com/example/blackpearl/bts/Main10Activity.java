package com.example.blackpearl.bts;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main10Activity extends AppCompatActivity {
    TextView tv;
    EditText ed;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        Intent in = getIntent();
        tv = (TextView) findViewById(R.id.textView16);
        ed = (EditText) findViewById(R.id.editText16);
        tv.setText(in.getStringExtra("pro"));
    }

    public void addsolution(View v) {
        Intent in = getIntent();
        try {
            db = openOrCreateDatabase("bts",MODE_PRIVATE, null);
            db.execSQL("create table solution(bugid int(10),user varchar(20),solution varchar(200),PRIMARY KEY(bugid));");
            db.execSQL("insert into solution values(" + in.getStringExtra("bug id") +",'"+in.getStringExtra("user")+"','" + ed.getText() + "');");
            db.execSQL("update bug set status='solved' where serial="+in.getStringExtra("bug id"));
            Toast.makeText(this, "solution added", Toast.LENGTH_SHORT).show();
            Intent in1=new Intent(this,MainActivity.class);
            startActivity(in1);
        } catch (Exception e) {
           try {
               db.execSQL("insert into solution values(" + in.getStringExtra("bug id") +",'"+in.getStringExtra("user")+"','" + ed.getText() + "');");
               db.execSQL("update bug set status='solved' where serial=" + in.getStringExtra("bug id"));
               Toast.makeText(this, "solution added", Toast.LENGTH_SHORT).show();
               Intent in1 = new Intent(this, MainActivity.class);
               startActivity(in1);
           }
           catch (Exception e1)
           {
               Toast.makeText(this, "solution already exist", Toast.LENGTH_SHORT).show();
           }
        }
    }

}
