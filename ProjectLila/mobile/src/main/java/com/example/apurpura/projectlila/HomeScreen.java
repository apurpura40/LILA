package com.example.apurpura.projectlila;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


/**
 * Created by lania on 05/30/2017.
 */

public class HomeScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Intent i = new Intent(this, SigningOnActivity.class);
        //startActivityForResult(i, 1);

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

        ActionMenuView bottomBar = (ActionMenuView) findViewById(R.id.bottom_toolbar);
        Menu toolbarBottom = bottomBar.getMenu();
        getMenuInflater().inflate(R.menu.toolbar_bottom, toolbarBottom);
        for (int i = 0; i < toolbarBottom.size(); i++) {
            toolbarBottom.getItem(i).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

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

        switch (item.getItemId()) {
            case R.id.action_do_something:
                //Intent doSomething = new Intent(this, DoSomethingEditCreate.class);
                //this.startActivity(doSomething);
                startActivity(new Intent(this, DoSomethingEditCreate.class));
                break;
            //case R.id.action_be_somewhere:
            // break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");

                setContentView(R.layout.home_screen);
                Toolbar toolbarTop = (Toolbar) findViewById(R.id.menu_home_screen);
                setSupportActionBar(toolbarTop);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                toolbarTop.setTitle("");
                toolbarTop.setSubtitle("");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult*/

}