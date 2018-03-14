package com.example.hongv.listviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hongv.listviewdemo.CalculatorActivity;
import com.example.hongv.listviewdemo.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private int mNum;

    private ListView lvHistory;
    private ArrayList<String> mData = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.etInput); //tạo ra 1 biến trỏ đến vùng nhớ của đối tượng

        //list view
        lvHistory = (ListView) findViewById(R.id.lvHistory);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData);
        lvHistory.setAdapter(mAdapter);

        lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //phai getApplicationContext vi
                Toast.makeText(getApplicationContext(), "Selected: " +mData.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //chờ nhận dữ liệu trả về


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            int result = data.getIntExtra("pass_receive", 0);
//            Toast.makeText(this, result + "", Toast.LENGTH_SHORT).show();

            String str = mNum + " x " + mNum + " = " + result;
            mData.add(str);

            mAdapter.notifyDataSetChanged();
        }
    }

    public void send(View view) {
        String str = etInput.getText().toString();
        try {
            mNum = Integer.parseInt(str);
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            intent.putExtra("pass_int", mNum);

//            startActivity(intent); //bat dau activity, day du lieu di

            //gui du lieu di, chờ ket qua tra ve thong qua requestCode
            startActivityForResult(intent, 1000);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Wrong number!!!", Toast.LENGTH_SHORT).show();
        }

    }
}
