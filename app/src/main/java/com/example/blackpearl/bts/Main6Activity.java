package com.example.blackpearl.bts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Main6Activity extends AppCompatActivity {
Button b1;
    Spinner s1;SQLiteDatabase db;
    EditText ed1,ed2;ArrayList<String> pr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        b1=(Button)findViewById(R.id.button6);
        ed1=(EditText)findViewById(R.id.editText12);
        ed2=(EditText)findViewById(R.id.editText13);
        s1=(Spinner)findViewById(R.id.spinner2);
        db=openOrCreateDatabase("bts",MODE_PRIVATE,null);
        pr=new ArrayList<String>();

        try{

            db.execSQL(" CREATE TABLE " + "bug" + " ( " +
                    "serial" + "INTEGER "+"PRIMARY KEY "+"AUTOINCREMENT,"+
                    "  id " + " INTEGER  , " +
                    "  product " + " TEXT , " +
                    " expert " + " TEXT , " +
                    " user " + " TEXT , " +
                    " status " + " TEXT , " +
                    " describe " + " TEXT , " +
                    " description1 " + " TEXT );"
            );
            Toast.makeText(this, "created table bug", Toast.LENGTH_SHORT).show();
        }
        catch(Exception rr){
            Toast.makeText(this, rr.getMessage(), Toast.LENGTH_SHORT).show();
        }
       Cursor c=db.rawQuery("select name from product",null);
        while(c.moveToNext())
        {
            pr.add(c.getString(0));
        }

        ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,pr);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(ad);
    }
    public void submit(View v)
    {Intent in1=getIntent();
        String s3=in1.getStringExtra("key");

        try
        {  db=openOrCreateDatabase("bts",MODE_PRIVATE,null);
           db.execSQL("create table bug(serial INTEGER PRIMARY KEY AUTOINCREMENT,id integer,product varchar(10),user varchar(20),status varchar(10),describe varchar(200),expert int(10));");
            db.execSQL("insert into bug(id,product,user,status,describe,expert) values('"+ed2.getText()+"', '" + s1.getSelectedItem() + "','" + s3 + "','unsolved','"+ed1.getText()+"','unassigned');");
            Toast.makeText(this, "problem added", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);

        }
        catch (Exception e) {
           db.execSQL("insert into bug(id,product,user,status,describe,expert) values('"+ed2.getText()+"', '" + s1.getSelectedItem()
                   + "','" + s3 + "','unsolved','"+ed1.getText()+"','unassigned');");
                Toast.makeText(this,"problem added", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
            }
    }

}
