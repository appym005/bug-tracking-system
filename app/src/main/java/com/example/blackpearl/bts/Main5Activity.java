package com.example.blackpearl.bts;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
EditText ed1,ed2,ed3;
    Button b1;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ed1=(EditText)findViewById(R.id.editText9);
        ed2=(EditText)findViewById(R.id.editText10);
        ed3=(EditText)findViewById(R.id.editText11);
        b1=(Button)findViewById(R.id.button4);
    }
    public void addpr(View v)
    {
        db = openOrCreateDatabase("bts", MODE_PRIVATE, null);
        try {
            db.execSQL("create table product(name varchar(10),serial INTEGER,version INTEGER, PRIMARY KEY(serial));");
            db.execSQL("insert into product values( '" + ed1.getText() + "'," + ed2.getText() + "," + ed3.getText()+");");
            Toast.makeText(this, "product added", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(in);
        } catch (Exception e) {
            try {
                db.execSQL("insert into product values( '" + ed1.getText() + "','" + ed2.getText() + "','" + ed3.getText()+"');");
                Toast.makeText(this, "product added", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(in);
            }
            catch (Exception e1)
            {
                Toast.makeText(this, "Already exists", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
