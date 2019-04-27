package com.example.blackpearl.bts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
Button b1,b2,b3;
    SQLiteDatabase db;
    ListView lv;ArrayList<String> result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        b1=(Button)findViewById(R.id.button14);
        b2=(Button)findViewById(R.id.button15);
        b3=(Button)findViewById(R.id.button);
        lv=(ListView)findViewById(R.id.listview);
    }
    public void addexpert(View v)
    {
        Intent in=new Intent(this,Main4Activity.class);
        startActivity(in);
    }
    public void addproduct(View v)
    {
        Intent in=new Intent(this,Main5Activity.class);
        startActivity(in);
    }
    public void show(View v)
    {
        result=new ArrayList<String>();
       db=openOrCreateDatabase("bts",MODE_PRIVATE,null);
        Cursor c=db.rawQuery("Select * from bug",null);
        while(c.moveToNext())
        {
        result.add(c.getInt(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getString(3)+" Des:"+c.getString(5)+" Expert:"+c.getString(6)+"\n "+c.getString(4));

            }
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,result);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in=new Intent(Main3Activity.this,Main8Activity.class);
                int a=result.get(position).indexOf(' ');
                String s3=result.get(position).substring(a+1,result.get(position).indexOf(' ',a+1));
                int b=result.get(position).indexOf(' ',a+1);
                String s4=result.get(position).substring(b+1,result.get(position).indexOf(' ',b+1));
       in.putExtra("key",s3);
//          in.putExtra("bug id",result.get(position).substring(0,result.get(position).indexOf(' ')));
          in.putExtra("bug id",s4);

                startActivity(in);

        }
        });        }
    public void logadmin(View v)
    {
        Intent in=new Intent(this,MainActivity.class);
        startActivity(in);
    }
    }


