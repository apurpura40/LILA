package com.example.apurpura.projectlila;

import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.RecyclerView;
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
        setContentView(R.layout.home_screen);
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.menu_home_screen);
        setSupportActionBar(toolbarTop);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTop.setTitle("");
        toolbarTop.setSubtitle("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the topToolbar menu
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);

        // Inflate and initialize the bottom menu
        ActionMenuView bottomBar = (ActionMenuView)findViewById(R.id.bottom_toolbar);
        Menu toolbarBottom = bottomBar.getMenu();
        getMenuInflater().inflate(R.menu.toolbar_bottom, toolbarBottom);
        for (int i = 0; i < toolbarBottom.size(); i++) {
            toolbarBottom.getItem(i).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
               public boolean onMenuItemClick(MenuItem item) {
 /*                   switch (item.getItemId()) {
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
                            return super.onOptionsItemSelected(item);*/
                    return onOptionsItemSelected(item);
                }
            });
        }

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