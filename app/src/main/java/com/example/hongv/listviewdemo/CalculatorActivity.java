package com.example.hongv.listviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    private TextView tvReceive;
    private  int mNum;
    private Button btnSendBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        tvReceive = findViewById(R.id.tvReceive);
        btnSendBack = findViewById(R.id.btnSendBack);

        Intent intent = getIntent();
        mNum = intent.getIntExtra("pass_int", 0);

        tvReceive.setText(mNum + "");// truyen vao ham 1 string: so + string = string

        //cach thong thuong bat su kien cho button
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //send back data
//            }
//        };
//        btnSendBack.setOnClickListener(listener);

        //cach thay the
        btnSendBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra("pass_receive", mNum * mNum);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
