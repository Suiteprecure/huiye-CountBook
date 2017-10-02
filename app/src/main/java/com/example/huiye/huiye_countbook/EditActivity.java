package com.example.huiye.huiye_countbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by huiye on 29/09/17.
 */
/*
 * Edit activity is let user make some change
 */
public class EditActivity extends MainActivity {
    private EditText counterNumber1, counterName, counterComment;
    private String counterNumber2;
    private int counterNumber;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_counters);

        Intent intent = getIntent();
        final int position = intent.getIntExtra("edit", 0);


        Button saveButton = (Button) findViewById(R.id.editSave);

        loadFromFile();
        Counters current = counters.get(position);

        counterName = (EditText) findViewById(R.id.edit_name);
        counterNumber1 = (EditText) findViewById(R.id.edit_number);
        counterComment = (EditText) findViewById(R.id.edit_comment);

        Log.d("edit","edit");

//
//        counterName.setText(current.getName());
//        counterComment.setText(current.getComment());
//        counterNumber1.setText(current.getCurrentValue());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                if (counterName.getText().toString().trim().length() <= 0) {
                    Toast.makeText(EditActivity.this, "Counter name empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                counterNumber2 = counterNumber1.getText().toString();
                //counterNumber = Integer.parseInt(counterNumber2);
                Log.d("888","999");
                try {
                    counterNumber = Integer.parseInt(counterNumber2);

                } catch (NumberFormatException e) {
                    Toast.makeText(EditActivity.this, "Counter number should be an integer! ", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (counterNumber < 0) {
                    Toast.makeText(EditActivity.this, "Counter number cannot be negative", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(EditActivity.this, "Counter Update success!", Toast.LENGTH_SHORT).show();
                newCounter =  new Counters(counterName.getText().toString(),counterComment.getText().toString(),counterNumber);
                counters.set(position, newCounter);
                saveInFile();

            }
        });


    }

}
