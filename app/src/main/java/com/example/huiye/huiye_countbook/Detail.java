package com.example.huiye.huiye_countbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by huiye on 30/09/17.
 */

public class Detail extends MainActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_detail);

        Intent intent = getIntent();
        int position = intent.getIntExtra("pos", 0);

        loadFromFile();
        Counters current = counters.get(position);
        //Log.d("value", "position: " + current.getName());
        TextView counterName = (TextView) findViewById(R.id.detailName);
        counterName.setText("counter name: " + current.getName());

        TextView date = (TextView) findViewById(R.id.detailDate);
        date.setText("adding date: " + current.getDate());

        TextView initialValue = (TextView) findViewById(R.id.detailInitialValue);
        initialValue.setText("initial value: " + current.getInitialValue());

        TextView currentValue = (TextView) findViewById(R.id.detailCurrentValue);
        currentValue.setText("current value: " + current.getCurrentValue());

        TextView comment = (TextView) findViewById(R.id.detailComment);
        comment.setText("current comment: " + current.getComment());
    }

}
