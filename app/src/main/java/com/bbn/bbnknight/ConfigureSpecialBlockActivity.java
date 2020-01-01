package com.bbn.bbnknight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ConfigureSpecialBlockActivity extends AppCompatActivity implements View.OnClickListener {
    TextView XBlock;
    TextView Activites;
    TextView Advisory;
    TextView ClassMeeting;
    TextView Assembly;
    TextView Lunch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configure_special_blocks);
        XBlock = findViewById(R.id.XBlockBlock);
        Activites = findViewById(R.id.ActivitiesBlock);
        Advisory = findViewById(R.id.AdvisoryBlock);
        ClassMeeting = findViewById(R.id.ClassMeetingBlock);
        Assembly = findViewById(R.id.AssemblyBlock);
        Lunch = findViewById(R.id.LunchBlock);
        XBlock.setOnClickListener(this);
        Activites.setOnClickListener(this);
        Advisory.setOnClickListener(this);
        ClassMeeting.setOnClickListener(this);
        Assembly.setOnClickListener(this);
        Lunch.setOnClickListener(this);


    }

    public void onClick(View view) {
        switch ((view.getId())) {
            case R.id.XBlockBlock:
                Intent intent = new Intent(ConfigureSpecialBlockActivity.this,NotificaitonConfigureActivity.class);
                intent.putExtra("Block Name", 0);
                startActivity(intent);
                break;
            case R.id.ActivitiesBlock:
                Intent intent1 = new Intent(ConfigureSpecialBlockActivity.this,NotificaitonConfigureActivity.class);
                intent1.putExtra("Block Name", 2);
                startActivity(intent1);
                break;
            case R.id.AdvisoryBlock:
                Intent intent2 = new Intent(ConfigureSpecialBlockActivity.this,NotificaitonConfigureActivity.class);
                intent2.putExtra("Block Name", 3);
                startActivity(intent2);
                break;
            case R.id.ClassMeetingBlock:
                Intent intent3 = new Intent(ConfigureSpecialBlockActivity.this,NotificaitonConfigureActivity.class);
                intent3.putExtra("Block Name", 4);
                startActivity(intent3);
                break;
            case R.id.LunchBlock:
                Intent intent4 = new Intent(ConfigureSpecialBlockActivity.this,NotificaitonConfigureActivity.class);
                intent4.putExtra("Block Name", 1);
                startActivity(intent4);
                break;
            case R.id.AssemblyBlock:
                Intent intent5 = new Intent(ConfigureSpecialBlockActivity.this,NotificaitonConfigureActivity.class);
                intent5.putExtra("Block Name", 5);
                startActivity(intent5);
                break;
        }

    }

}
