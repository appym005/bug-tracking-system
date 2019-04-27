package com.example.blackpearl.bts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main8Activity extends AppCompatActivity {
Spinner s1;
    SQLiteDatabase db;
    TextView tv,tv2;
    Button b1;ArrayList<String> result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        s1=(Spinner)findViewById(R.id.spinner3);
        tv=(TextView)findViewById(R.id.textView13);
        tv2=(TextView)findViewById(R.id.textView14);
        b1=(Button)findViewById(R.id.button8);
        Intent in=getIntent();
      String s2=  in.getStringExtra("key");
      String s3= in.getStringExtra("bug id");
        tv.setText(s2);
        tv2.setText(s3);
        result=new ArrayList<String>();
        try
        {
            db=openOrCreateDatabase("bts",MODE_PRIVATE,null);
            Cursor c=db.rawQuery("select name from expert where product='"+s3+"';",null);
            while(c.moveToNext())
            {
                result.add(c.getString(0));
            }
            ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,result);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s1.setAdapter(ad);
        }
        catch (Exception e){}
    }
    public void assign(View v)
    {
        Intent in=getIntent();
      String s3=  in.getStringExtra("key");
        db=openOrCreateDatabase("bts",MODE_PRIVATE,null);
        try {
            db.execSQL("update bug set expert='"+s1.getSelectedItem()+"' where id='"+s3+"';");
            Toast.makeText(this, "expert assinged", Toast.LENGTH_SHORT).show();
            Intent in1=new Intent(this,Main3Activity.class);
            startActivity(in1);
        }
catch (Exception e)
{
    Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
}
    }
}
