package com.example.huiye.huiye_countbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by huiye on 23/09/17.
 */

/**
 * Adding activity is to add new counter to counter array
 */
public class AddingActivity extends MainActivity {
    private EditText counterNumber1;
    private String counterNumber2;
    private EditText counterName, counterComment;
    private int counterNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_counters);
        final Button addButton1 = (Button) findViewById(R.id.add_counter);



        counterName = (EditText) findViewById(R.id.counter_name);
        counterNumber1 = (EditText) findViewById(R.id.counter_number);
        counterComment = (EditText) findViewById(R.id.counter_comment);

        //Log.d("666","233");



        addButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                if (counterName.getText().toString().trim().length() <= 0) {
                    Toast.makeText(AddingActivity.this, "Counter name empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                counterNumber2 = counterNumber1.getText().toString();
                //counterNumber = Integer.parseInt(counterNumber2);
                Log.d("888","999");
                try {
                    counterNumber = Integer.parseInt(counterNumber2);

                } catch (NumberFormatException e) {
                    Toast.makeText(AddingActivity.this, "Counter number should be an integer! ", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (counterNumber < 0) {
                    Toast.makeText(AddingActivity.this, "Counter number cannot be negative", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(AddingActivity.this, "Counter Add success!", Toast.LENGTH_SHORT).show();


                newCounter =  new Counters(counterName.getText().toString(),counterComment.getText().toString(),counterNumber);
                counters.add(newCounter);
                saveInFile();

            }
        });



    }


}