package com.example.blackpearl.bts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main11Activity extends AppCompatActivity {
    TextView solution;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        solution=(TextView) findViewById(R.id.sol);
        in = getIntent();
    }
}
