package com.ardenolgundemir.espressotestexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ((TextView)findViewById(R.id.tvTitle)).setVisibility(View.GONE);
                startActivity(new Intent(MainActivity.this,NewActivity.class));
            }
        });
    }
}
