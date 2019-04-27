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

public class  Main2Activity extends AppCompatActivity {
    Button b1;
    EditText ed1,ed2,ed3,ed4;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b1=(Button)findViewById(R.id.button3);
        ed1=(EditText)findViewById(R.id.editText3);
        ed2=(EditText)findViewById(R.id.editText4);
        ed3=(EditText)findViewById(R.id.editText5);
        ed4=(EditText)findViewById(R.id.editText6);


    }
    public void regis(View v) {
        db = openOrCreateDatabase("bts", MODE_PRIVATE, null);



            try {
                db.execSQL("create table login(type varchar(10),name varchar(20),username varchar(20),pass varchar(20),email varchar(20), PRIMARY KEY(username));");
                db.execSQL("insert into login values( 'admin','gabel','gabel','gabel','gabel@gmail.com');");
                db.execSQL("insert into login values( 'user','" + ed1.getText() + "','" + ed2.getText() + "','" + ed3.getText() + "','" + ed4.getText() + "');");
                Toast.makeText(this, "values has inserted", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
            } catch (Exception e) {
try {
    db.execSQL("insert into login values( 'user','" + ed1.getText() + "','" + ed2.getText() + "','" + ed3.getText() + "','" + ed4.getText() + "');");
    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    Intent in = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(in);
}
catch (Exception e1)
{
    Toast.makeText(this, "username already exist", Toast.LENGTH_SHORT).show();
}

            }
        }
    }


