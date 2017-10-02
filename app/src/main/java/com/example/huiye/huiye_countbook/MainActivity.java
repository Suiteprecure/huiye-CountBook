package com.example.huiye.huiye_countbook;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends Activity {
    public Counters newCounter;

    private static final String FILENAME = "file.sav";
   // private EditText bodyText;

    public ListView oldCounterList;

    public ArrayList<Counters> counters = new ArrayList<Counters>();
    public ArrayAdapter<Counters> adapter;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.add);

        oldCounterList = findViewById(R.id.oldCounter);
        registerForContextMenu(oldCounterList);

        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                //go to add counter activity
                Intent createActivity = new Intent(MainActivity.this, AddingActivity.class);
                startActivity(createActivity);

            }
        });


    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //counters.clear();
        //saveInFile();
        loadFromFile();
        Log.d("gg","gg");
        adapter = new CounterAdapter(this,counters);
        oldCounterList.setAdapter(adapter);
    }



    public void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            counters = gson.fromJson(in, new TypeToken<ArrayList<Counters>>(){}.getType());

            fis.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            counters = new ArrayList<Counters>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();

            gson.toJson(counters, out);

            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.oldCounter) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
    }

    /**
     * check which part of contex menu user click, go to different acitvity for different user move.
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        switch(item.getItemId()) {
            case R.id.details:
                Intent detail = new Intent(MainActivity.this, Detail.class);
                detail.putExtra("pos", position);
                startActivity(detail);
                return true;

            case R.id.edit:
                // edit stuff here
                Intent edit = new Intent(MainActivity.this, EditActivity.class);
                edit.putExtra("edit",position);
                startActivity(edit);
                adapter = new CounterAdapter(this,counters);
                oldCounterList.setAdapter(adapter);
                return true;

            case R.id.delete:
                // remove stuff here
                counters.remove(position);
                saveInFile();
                adapter = new CounterAdapter(this,counters);
                oldCounterList.setAdapter(adapter);
                Toast.makeText(MainActivity.this, "Delete!", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.increase:
                //increase
                Counters currentCounterIncrease = counters.get(position);
                currentCounterIncrease.incrementCurrent();
                Toast.makeText(MainActivity.this, "increase", Toast.LENGTH_SHORT).show();
                saveInFile();
                adapter = new CounterAdapter(this,counters);
                oldCounterList.setAdapter(adapter);
                return true;

            case R.id.decrease:
                //decrease
                Counters currentCounterDecrease = counters.get(position);
                if (currentCounterDecrease.decrementCurrent()){
                    Toast.makeText(MainActivity.this, "Current Value is 0", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "decrease", Toast.LENGTH_SHORT).show();
                }
                saveInFile();
                adapter = new CounterAdapter(this,counters);
                oldCounterList.setAdapter(adapter);
                return true;

            case R.id.reset:
                Counters currentReset = counters.get(position);
                currentReset.resetCurrentValue();
                Toast.makeText(MainActivity.this, "reset", Toast.LENGTH_SHORT).show();
                saveInFile();
                adapter = new CounterAdapter(this,counters);
                oldCounterList.setAdapter(adapter);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


}

