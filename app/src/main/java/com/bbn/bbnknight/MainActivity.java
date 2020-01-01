package com.bbn.bbnknight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    Date currentTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        // retrieve class information
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                "com.bbn.bbnknight", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("classes", "");

        if (!json.isEmpty()) {
            Type type = new TypeToken<ArrayList<SetClassActivity.ClassItem>>(){}.getType();
            SetClassActivity.mClasses = gson.fromJson(json, type);
        }

        //test code
        BlocksInWeek.initBlocks();
        String str = BlocksInWeek.weekBlock.get(0).getBlock(0).name;
        Log.i("Debin-1", "weekblock: " + str);
        str = BlocksInWeek.weekBlock.get(1).getBlock(1).name;
        Log.i("Debin-2", "weekblock: " + str);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        //The following is to handle setting menu
        //Switch(item.getItemId()) {
        switch (item.getItemId()) {
            case R.id.setBlock:
                Toast.makeText(getApplicationContext(), "block setting selected", Toast.LENGTH_LONG).show();
                break;
            case R.id.setClass:
                Toast.makeText(getApplicationContext(), "class setting selected", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), SetClassActivity.class);
                startActivity(intent);

                break;
            case R.id.setLunch:
                Toast.makeText(getApplicationContext(), "class setting selected", Toast.LENGTH_LONG).show();

                Intent Lunchintent = new Intent(getApplicationContext(), configureLunchBlockActivity.class);
                startActivity(Lunchintent);
                Toast.makeText(getApplicationContext(), "lunch setting selected", Toast.LENGTH_LONG).show();
                break;
            case R.id.profile:
                Toast.makeText(getApplicationContext(), "profile selected", Toast.LENGTH_LONG).show();
                break;
            case R.id.credit:
                Toast.makeText(getApplicationContext(), "credit setting selected", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), "unknown item selected", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.Today) {
            Toast.makeText(this, "Today is selected", Toast.LENGTH_LONG).show();
        } else if (id == R.id.Upcoming) {
            Toast.makeText(this, "Upcoming is selected", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, UpcomingActivity.class);
            startActivity(intent);
        } else if (id == R.id.log) {
            Toast.makeText(this, "logout is selected", Toast.LENGTH_LONG).show();
        }

        return false;
    }


    // add setting menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.setting_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }
}
