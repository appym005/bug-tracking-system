package com.example.blackpearl.bts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText ed1,ed2;
    SQLiteDatabase db;
    String s1[]={"admin","user","expert"};
    Spinner s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button2);
        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        s=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,s1);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(ad);
    }
    public void login(View v)
    {db=openOrCreateDatabase("bts",MODE_PRIVATE,null);
        try {
            db = openOrCreateDatabase("bts", MODE_PRIVATE, null);
            if(s.getSelectedItem()=="admin"){
                Cursor c = db.rawQuery("select pass,name from login where type='" + s.getSelectedItem() + "'" + "and username='" + ed1.getText() + "'", null);
                if (c.moveToNext()) {

                    if (("" + c.getString(0)).equals("" + ed2.getText())) {
                        if (s.getSelectedItem().equals("admin")) {
                            Intent in = new Intent(getApplicationContext(), Main3Activity.class);
                            in.putExtra("key", "" + c.getString(1));
                            startActivity(in);
                        }

                    }
                    else
                        Toast.makeText(this, "username or password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
            if(s.getSelectedItem()=="user"){
                Cursor c = db.rawQuery("select pass,name from login where type='" + s.getSelectedItem() + "'" + "and username='" + ed1.getText() + "'", null);
                if (c.moveToNext()) {

                    if (("" + c.getString(0)).equals("" + ed2.getText())) {
                        if (s.getSelectedItem().equals("user")) {
                            Intent in = new Intent(getApplicationContext(), Main7Activity.class);
                            in.putExtra("key", "" + c.getString(1));
                            startActivity(in);
                        }

                    }
                    else
                        Toast.makeText(this, "username or password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
            if(s.getSelectedItem()=="expert"){
                Cursor c = db.rawQuery("select passwd,username,name from expert where username='" + ed1.getText() + "'", null);
                if (c.moveToNext()) {
                if (("" + c.getString(0)).equals("" + ed2.getText())) {
                    Intent in = new Intent(getApplicationContext(), Main9Activity.class);
                    in.putExtra("key", "" + c.getString(2));
                    startActivity(in);
                }
                else
                    Toast.makeText(this, "username or password is incorrect", Toast.LENGTH_SHORT).show();
            }}
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    public void register(View v)
    {
        Intent in=new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(in);
    }
}

