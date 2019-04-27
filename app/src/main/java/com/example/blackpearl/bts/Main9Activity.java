package com.example.blackpearl.bts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main9Activity extends AppCompatActivity {
ListView lv;
    TextView tv;
    ArrayList<String> result;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        tv=(TextView)findViewById(R.id.textView14);
        lv=(ListView)findViewById(R.id.listview2);
        Intent in=getIntent();
        tv.setText(in.getStringExtra("key"));
        result=new ArrayList<String>();
        final String s1=in.getStringExtra("key");
        db=openOrCreateDatabase("bts",MODE_PRIVATE,null);
        final Cursor c=db.rawQuery("Select * from bug where expert='"+s1+"'",null);
        while(c.moveToNext())
        {
            result.add(c.getInt(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getString(3)+"\n "+c.getString(4));

        }
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,result);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in=new Intent(Main9Activity.this,Main10Activity.class);int a,a1,a2;
               try{
                   Cursor d=db.rawQuery("Select * from bug where serial='"+result.get(position).substring(0,result.get(position).indexOf(' '))+"'",null);
                   d.moveToFirst();
                in.putExtra("bug id",d.getString(0));
                in.putExtra("shit",d.getString(1));
                in.putExtra("pro",d.getString(2));
                in.putExtra("user",d.getString(3));
                  // Toast.makeText(Main9Activity.this, result.get(position).substring(a1+1,a2), Toast.LENGTH_SHORT).show();
           startActivity(in);}
               catch (Exception e)
               {a=result.get(position).indexOf(' ');
                   a1=result.get(position).indexOf(' ',a+1);
                   a2=result.get(position).indexOf(' ',a1+1);
                   Toast.makeText(Main9Activity.this, ""+e.getMessage()+" "+a1+" "+a2, Toast.LENGTH_SHORT).show();
               }

            }
        });
    }
    public void log(View v)
    {
        Intent in=new Intent(this,MainActivity.class);
        startActivity(in);
    }
}