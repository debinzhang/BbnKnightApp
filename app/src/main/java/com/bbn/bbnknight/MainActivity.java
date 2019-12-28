package com.bbn.bbnknight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


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
