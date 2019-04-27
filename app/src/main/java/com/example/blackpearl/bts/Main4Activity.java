package com.example.blackpearl.bts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
EditText ed1;
EditText usename;
EditText passwd;
EditText email;

    TextView tv1;
    SQLiteDatabase db;ArrayList<String> pr;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ed1=(EditText)findViewById(R.id.editText7);
        usename=(EditText)findViewById(R.id.edusername);
        passwd=(EditText)findViewById(R.id.passwd);
        email=(EditText)findViewById(R.id.email);
        tv1=(TextView)findViewById(R.id.textView9);
        spinner=(Spinner)findViewById(R.id.spinner4);
       try {
           db = openOrCreateDatabase("bts", MODE_PRIVATE, null);
           Cursor c = db.rawQuery("select id from expert", null);
           c.moveToLast();
           tv1.setText("" + (c.getInt(0) + 1));
       }
       catch (Exception e)
       {
           tv1.setText(""+140141);
       }
        pr=new ArrayList<String>();
        Cursor  c=db.rawQuery("select name from product",null);
        while(c.moveToNext())
        {
            pr.add(c.getString(0));
        }

        ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,pr);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ad);
    }

    public void addex(View v)
    {
        db = openOrCreateDatabase("bts", MODE_PRIVATE, null);
        try {
            db.execSQL("create table expert(id integer PRIMARY KEY AUTOINCREMENT,name varchar(20),product varchar(20),passwd varchar(20),email varchar(30),username varchar(20));");
            db.execSQL("insert into expert(name,product,passwd,email,username) values( '" + ed1.getText() + "','" +spinner.getSelectedItem()+"','"+passwd.getText()+"','"+email.getText()+"','"+usename.getText()+"');");
            Toast.makeText(this, "expert added", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(in);
        } catch (Exception e) {
            try {
                db.execSQL("insert into expert(name,product,passwd,email,username) values( '" + ed1.getText() + "','" +spinner.getSelectedItem()+"','"+passwd.getText()+"','"+email.getText()+"','"+usename.getText()+"');");
                Toast.makeText(this, "expert added", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(in);
            }
            catch (Exception e1)
            {
                Toast.makeText(this, e1.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}
