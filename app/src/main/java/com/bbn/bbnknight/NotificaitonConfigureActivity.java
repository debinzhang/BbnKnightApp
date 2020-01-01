package com.bbn.bbnknight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.gson.Gson;

public class NotificaitonConfigureActivity extends AppCompatActivity{

    Switch beforeBlockNotification;
    Switch beforeBlockEndNotification;
    int block;
    BlockNotofication notification;
    boolean beforeBlock;
    boolean beforeBlockEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_block_configuration_layout);
        Intent intent = getIntent();
        block = intent.getIntExtra("Block Name",-1);
        notification = new BlockNotofication();
        beforeBlockNotification = findViewById(R.id.BeforeBlockNotificationSwitch);
        beforeBlockEndNotification = findViewById(R.id.BeforeBlockEndNOtificationSwitch);
        beforeBlockEndNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    beforeBlockEnd = true;
                    if(beforeBlock == true && beforeBlockEnd == true) {
                        notification.setBlockNotification(block,3);
                        Log.i("Evan", "3");
                    } else if(beforeBlock == false && beforeBlockEnd == false) {
                        notification.setBlockNotification(block,4);
                        Log.i("Evan", "4");
                    } else if(beforeBlock == true && beforeBlockEnd == false) {
                        notification.setBlockNotification(block,1);
                        Log.i("Evan", "1");
                    } else {
                        notification.setBlockNotification(block,2);
                        Log.i("Evan", "2");
                    }
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                            "com.bbn.bbnknight", Context.MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(SetClassActivity.mClasses);
                    prefsEditor.putString("classes", json);
                    prefsEditor.commit();

                    Log.i("Evan", "in listener1");
                } else {
                    beforeBlockEnd = false;
                    if(beforeBlock == true && beforeBlockEnd == true) {
                        notification.setBlockNotification(block,3);
                        Log.i("Evan", "3");
                    } else if(beforeBlock == false && beforeBlockEnd == false) {
                        notification.setBlockNotification(block,4);
                        Log.i("Evan", "4");
                    } else if(beforeBlock == true && beforeBlockEnd == false) {
                        notification.setBlockNotification(block,1);
                        Log.i("Evan", "1");
                    } else {
                        notification.setBlockNotification(block,2);
                        Log.i("Evan", "2");
                    }
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                            "com.bbn.bbnknight", Context.MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(SetClassActivity.mClasses);
                    prefsEditor.putString("classes", json);
                    prefsEditor.commit();

                    Log.i("Evan", "in listener1");

                }
            }
        });

        beforeBlockNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    beforeBlock = true;
                    if(beforeBlock == true && beforeBlockEnd == true) {
                        notification.setBlockNotification(block,3);
                        Log.i("Evan", "3");
                    } else if(beforeBlock == false && beforeBlockEnd == false) {
                        notification.setBlockNotification(block,4);
                        Log.i("Evan", "4");
                    } else if(beforeBlock == true && beforeBlockEnd == false) {
                        notification.setBlockNotification(block,1);
                        Log.i("Evan", "1");
                    } else {
                        notification.setBlockNotification(block,2);
                        Log.i("Evan", "2");
                    }
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                            "com.bbn.bbnknight", Context.MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(SetClassActivity.mClasses);
                    prefsEditor.putString("classes", json);
                    prefsEditor.commit();

                    Log.i("Evan", "in listener2");

                } else {
                    beforeBlock = false;
                    if(beforeBlock == true && beforeBlockEnd == true) {
                        notification.setBlockNotification(block,3);
                        Log.i("Evan", "3");
                    } else if(beforeBlock == false && beforeBlockEnd == false) {
                        notification.setBlockNotification(block,4);
                        Log.i("Evan", "4");
                    } else if(beforeBlock == true && beforeBlockEnd == false) {
                        notification.setBlockNotification(block,1);
                        Log.i("Evan", "1");
                    } else {
                        notification.setBlockNotification(block,2);
                        Log.i("Evan", "2");
                    }
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                            "com.bbn.bbnknight", Context.MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(SetClassActivity.mClasses);
                    prefsEditor.putString("classes", json);
                    prefsEditor.commit();

                    Log.i("Evan", "in listener2");

                }
            }
        });
    }
}
