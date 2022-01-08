package com.example.internalfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Intent si;
    EditText et;
    String line;
    String display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et);
        et.setText(display);
    }

    @Override
    protected void onStart() {

        FileInputStream fis= null;
        try {
            fis = openFileInput("data.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            sb.append(line+'\n');
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        display=sb.toString();
        try {
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        super.onStart();
    }


    /**
     *
     * gets Menu
     * Creation and inflation of OptionsMenu on this activity
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void creds(MenuItem item) {
        si = new Intent(this,credits.class);
        startActivity(si);

    }

    public void finish(MenuItem item) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("data.txt",MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        try {
            bw.write(et.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();
    }

    public void save(View view) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("data.txt",MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        try {
            bw.write(et.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void reset(View view) {
        et.setText("");
    }
}