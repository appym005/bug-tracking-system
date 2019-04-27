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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main7Activity extends AppCompatActivity {
ListView ed1,ed2;
    Button b1;
    TextView tv1,tv2;
    SQLiteDatabase db;
    String s1="",s2="";int n;
    ArrayList<String> al1,al2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        ed1=(ListView) findViewById(R.id.editText14);
        ed2=(ListView) findViewById(R.id.editText15);
        tv1=(TextView)findViewById(R.id.textView10);
        tv2=(TextView)findViewById(R.id.textView15);
        Intent in=getIntent();
        s2=in.getStringExtra("key");
        tv1.setText("Hi "+s2);
        al1=new ArrayList<String>();
        al2=new ArrayList<String>();
        try
        {
            db=openOrCreateDatabase("bts",MODE_PRIVATE,null);
            Cursor c=db.rawQuery("Select * from bug where user='"+s2+"'",null);
            while(c.moveToNext())
            {
                al1.add(" "+c.getInt(0)+" "+c.getString(1)+" "+c.getString(5)+" "+c.getString(2)+" "+c.getString(3)+"\n "+c.getString(4));
            }
            ArrayAdapter<String>ad1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al1);
            ed1.setAdapter(ad1);
        }
        catch (Exception e)
        {

        }
        try
        {s1="";
            db=openOrCreateDatabase("bts",MODE_PRIVATE,null);
            Cursor c=db.rawQuery("Select * from solution where user='"+s2+"'",null);
            while(c.moveToNext())
            {
                al2.add(+c.getInt(0)+" - "+c.getString(2));
            }
            ArrayAdapter<String>ad2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al2);
            ed2.setAdapter(ad2);
        }
        catch (Exception e)
        {

        }
    }
    public void report (View v)
    {
        Intent in=new Intent(this.getApplicationContext(),Main6Activity.class);
        in.putExtra("key",s2);
        startActivity(in);

    }
    public void logout(View v)
    {

        Intent in=new Intent(this.getApplicationContext(),MainActivity.class);
        startActivity(in);
    }
}
