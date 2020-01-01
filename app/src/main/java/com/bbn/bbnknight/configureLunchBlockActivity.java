package com.bbn.bbnknight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class configureLunchBlockActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner mondayScroll;
    Spinner tuesdayScroll;
    Spinner wednesdayScroll;
    Spinner thursdayScroll;
    Spinner fridayScroll;
    TextView mondayText;
    TextView tuesdayText;
    TextView wednesdayText;
    TextView thursdayText;
    TextView fridayText;
    ArrayAdapter<CharSequence> fridayAdapter;
    ArrayAdapter<CharSequence> thursdayAdapter;
    ArrayAdapter<CharSequence> wednesdayAdapter;
    ArrayAdapter<CharSequence> tuesdayAdapter;
    ArrayAdapter<CharSequence> mondayAdapter;
    Button saveButton;
    Button cancelButton;
    LunchBlock mLunchBlock;
    String selectedBlock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Evan","LunchCreate1");
        setContentView(R.layout.activity_configure_lunch_block);
        Log.i("Evan","LunchCreate2");
        mondayScroll = findViewById(R.id.MondayLunch);
        mondayAdapter = ArrayAdapter.createFromResource(this,R.array.lunchTime, android.R.layout.simple_spinner_item);
        mondayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mondayScroll.setAdapter(mondayAdapter);
        mondayScroll.setOnItemSelectedListener(this);
        mondayText = findViewById(R.id.Monday);

        tuesdayScroll = findViewById(R.id.TuesdayLunch);
        tuesdayAdapter = ArrayAdapter.createFromResource(this,R.array.lunchTime, android.R.layout.simple_spinner_item);
        tuesdayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tuesdayScroll.setAdapter(tuesdayAdapter);
        tuesdayScroll.setOnItemSelectedListener(this);
        tuesdayText = findViewById(R.id.Tuesday);

        wednesdayScroll = findViewById(R.id.WednesdayLunch);
        wednesdayAdapter = ArrayAdapter.createFromResource(this,R.array.lunchTime, android.R.layout.simple_spinner_item);
        wednesdayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wednesdayScroll.setAdapter(wednesdayAdapter);
        wednesdayScroll.setOnItemSelectedListener(this);
        wednesdayText = findViewById(R.id.Wednesday);

        thursdayScroll = findViewById(R.id.ThursdayLunch);
        thursdayAdapter = ArrayAdapter.createFromResource(this,R.array.lunchTime, android.R.layout.simple_spinner_item);
        thursdayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thursdayScroll.setAdapter(thursdayAdapter);
        thursdayScroll.setOnItemSelectedListener(this);
        thursdayText = findViewById(R.id.Thursday);

        fridayScroll = findViewById(R.id.FridayLunch);
        fridayAdapter = ArrayAdapter.createFromResource(this,R.array.lunchTime, android.R.layout.simple_spinner_item);
        fridayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fridayScroll.setAdapter(fridayAdapter);
        fridayScroll.setOnItemSelectedListener(this);
        fridayText = findViewById(R.id.Friday);

        saveButton = findViewById(R.id.newClassSaveButton);
        cancelButton = findViewById(R.id.newClassCancelButton);
        mLunchBlock = new LunchBlock();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedBlock = adapterView.getItemAtPosition(i).toString();

        switch(adapterView.getId()) {
            case R.id.MondayLunch:
                Log.i("Evan","Enter into Selection");
                if(adapterView.getItemAtPosition(i).toString() == "1st Lunch") {
                    mLunchBlock.setLunchTime(true, 0);
                } else {
                    mLunchBlock.setLunchTime(false,0);
                }
                Log.i("Evan", "clicked on Monday");
                break;
            case R.id.TuesdayLunch:
                if(adapterView.getItemAtPosition(i).toString() == "1st Lunch") {
                    mLunchBlock.setLunchTime(true, 1);
                } else {
                    mLunchBlock.setLunchTime(false,1);
                }
                Log.i("Evan", "clicked on Tuesday");
                break;
            case R.id.WednesdayLunch:
                if(adapterView.getItemAtPosition(i).toString() == "1st Lunch") {
                    mLunchBlock.setLunchTime(true, 2);
                } else {
                    mLunchBlock.setLunchTime(false,2);
                }
                Log.i("Evan", "clicked on Wednesday");
                break;
            case R.id.ThursdayLunch:
                if(adapterView.getItemAtPosition(i).toString() == "1st Lunch") {
                    mLunchBlock.setLunchTime(true, 3);
                } else {
                    mLunchBlock.setLunchTime(false,3);
                }
                Log.i("Evan", "clicked on Thursday");
                break;
            case R.id.FridayLunch:
                if(adapterView.getItemAtPosition(i).toString() == "1st Lunch") {
                    mLunchBlock.setLunchTime(true, 4);
                } else {
                    mLunchBlock.setLunchTime(false,4);
                }
                Log.i("Evan", "clicked on Friday");
                break;
            default:
                Log.i("Evan", "failed to find any id:" + adapterView.getId());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void cancelButtonClicked(View view) {
        Toast.makeText(this, "Cancleclass", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void saveButtonClicked(View view) {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                "com.bbn.bbnknight", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(SetClassActivity.mClasses);
        prefsEditor.putString("classes", json);
        prefsEditor.commit();

        finish();
    }
}
