package com.example.huiye.huiye_countbook;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.huiye.huiye_countbook.R.id.list_item;

/**
 * Created by huiye on 29/09/17.
 */

public class CounterAdapter extends ArrayAdapter<Counters> {


    public CounterAdapter(Context context, List<Counters> counters)  {
        super (context, R.layout.list_item, counters);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Counters currentCounter = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView counterName = (TextView) convertView.findViewById(R.id.counterName);
        counterName.setText("counter name: " + currentCounter.getName());

        TextView date = (TextView) convertView.findViewById(R.id.counterDate);
        date.setText("adding date: " + currentCounter.getDate());

        TextView initial = (TextView) convertView.findViewById(R.id.counterInitialValue);
        initial.setText("initial value: " + currentCounter.getInitialValue());

        TextView current = (TextView) convertView.findViewById(R.id.counterCurrentValue);
        current.setText("current value: " + currentCounter.getCurrentValue());

        //TextView comment = (TextView) convertView.findViewById(R.id.counterComment);
        //comment.setText("comment: " + currentCounter.getComment());

        return convertView;
    }


}
