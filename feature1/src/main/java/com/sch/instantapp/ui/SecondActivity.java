package com.sch.instantapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.sch.instantapp.feature1.R;

public class SecondActivity extends AppCompatActivity {
    @Override
    @SuppressWarnings("ConstantConditions")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button okButton = (Button) findViewById(R.id.button_ok);
        okButton.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });
    }
}
