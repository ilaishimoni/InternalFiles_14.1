package com.example.internalfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * @author		Ilai Shimoni <ilaishimoni@gmail.com>
 * @version	     1.0
 * @since		03/01/22
 * application saves data into an internal file and shows saved text on the app
 */

public class MainActivity extends AppCompatActivity {

    Intent si;
    EditText et;
    String line;
    String strrd;
    TextView tv;


    /**
     * occurs during app creation ( 1 time only )
     * <p>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et);
        tv = (TextView) findViewById(R.id.tv);

        tv.setText("");

    }


    /**
     * occurs during app start ( after creation )
     * <p>
     */
    @Override
    protected void onStart() {
        super.onStart();

        try{
            FileInputStream fis= openFileInput("data.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            line = br.readLine();
            while (line != null) {
                sb.append(line+'\n');
                line = br.readLine();
            }
            strrd=sb.toString();
            isr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tv.setText(strrd);
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

    /**
     * checks which button was pressed and acts accordingly
     * @param  item
     * @return method either moves user to another activity or closes the app with data save
     */
    public boolean  onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.credits) {
            si = new Intent(this, credits.class);
            startActivity(si);
            return true;
        }
        if (id == R.id.exit) {
            FileOutputStream fos = null;
            try {
                fos = openFileOutput("data.txt", MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            try {
                bw.write(strrd + et.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finish();
            return true;

        }
        return true;

    }






    /**
     * saves data without closing the application
     * @param view (button)\
     * @return method saves data to Internal File
     */
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
            bw.write(strrd + et.getText().toString() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        strrd =  strrd + et.getText().toString();
        tv.setText(strrd);
        et.setText("");



    }

    /**
     * Reseting the file as well as data preserved to user
     * @param view the view
     */
    public void reset(View view) {
        tv.setText("");
        strrd = "";

        try{
            FileOutputStream fos = openFileOutput("data.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(strrd);
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
