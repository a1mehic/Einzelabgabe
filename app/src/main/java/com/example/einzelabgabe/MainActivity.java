package com.example.einzelabgabe;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String martikelnummer;
    EditText eingabe;
    TextView ausgabe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        eingabe = (EditText) findViewById(R.id.martknr_text);
        ausgabe = (EditText) findViewById(R.id.ausgabefeld);


    }


    public void buttonOnClick(View v) {

        TCPClient client = new TCPClient();
        try {
            client.sendToServer(eingabe.getText().toString());
            if (client.getOutput() != null) {
                ausgabe.setText(client.getOutput());
                //TODO
            }
        } catch (Exception e) {
            e.printStackTrace();
            ausgabe.setText("ERROR");
        }

        //  martikelnummer= (eingabe.getText().toString());
    }

    public void buttonPrimClick(View v) {

        String output = "";
        char[] value = eingabe.getText().toString().toCharArray();

        for (char c : value) {
            if (isPrime(Integer.parseInt(String.valueOf(c)))) {
                output += c;
            }
        }

        ausgabe.setText(output);


    }

    /**
     * Determines if a number is prime
     *
     * @param i number
     * @return true if i is prime
     */
    public static boolean isPrime(int i) {
        if (i == 0 || i == 1) return false;
        if (i == 2) return true;
        for (int j = 2; j < i; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }

       /*public static int[] isPrim (int martknr){

        int[] primzahlen= new int[10];



        for(int i = 0; i<= primzahlen.length; i++){

            if (martknr <=2){
                primzahlen[i]=martknr;
            }
            for(martknr i=2; i *i <= martknr; i++){
                if(martknr % i == 0){

                }
            }
        }

        return primzahlen;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
