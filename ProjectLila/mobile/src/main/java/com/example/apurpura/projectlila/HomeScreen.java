package com.example.apurpura.projectlila;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lania on 05/30/2017.
 */

public class HomeScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_bottom);
        Toolbar bottomToolbar = (Toolbar) findViewById(R.id.toolbar_bottom);
        setSupportActionBar(bottomToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        bottomToolbar.setTitle("");
        bottomToolbar.setSubtitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_bottom, menu);
        return true;
    }
    //SOME COMMENT Add more comment


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sequence_something:

                return true;

            case R.id.action_say_something:

                return true;

            case R.id.action_be_somewhere:

                return true;

            case R.id.action_do_something:

                return true;

            case R.id.action_get_metrics:

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}