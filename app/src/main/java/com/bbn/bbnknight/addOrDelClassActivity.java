package com.bbn.bbnknight;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashSet;

import yuku.ambilwarna.AmbilWarnaDialog;

public class addOrDelClassActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int mColor;
    EditText mClassNameEditText;
    EditText mLocationEditText;
    Spinner mBlockspinner;
    EditText mDaysEditText;
    Button mPickColorButton, mSaveButton, mDelButton, mCancelButton;
    int classIndex = -1;
    boolean addNewClass = false;
    String selectedBlock;

    // pick color button clicked
    public void openColorPicker(View view) {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, mColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mColor = color;
                mClassNameEditText.setTextColor(mColor);
                mPickColorButton.setBackgroundColor(mColor);
            }
        });
        colorPicker.show();
    }

    public void saveClassInfo() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                "com.bbn.bbnknight", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(SetClassActivity.mClasses);
        prefsEditor.putString("classes", json);
        prefsEditor.commit();
    }

    // Save button clicked
    public void saveButtonClicked(View view) {
        String className = mClassNameEditText.getText().toString();
        if (className.isEmpty()) {
            Toast.makeText(this, "class name CANNOT be empty", Toast.LENGTH_LONG).show();
            return;
        }

        String location = mLocationEditText.getText().toString();
        if (location.isEmpty()) {
            Toast.makeText(this, "Location CANNOT be empty", Toast.LENGTH_LONG).show();
            return;
        }
        String block = selectedBlock;
        if (block.isEmpty()) {
            Toast.makeText(this, "Block CANNOT be empty", Toast.LENGTH_LONG).show();
            return;
        }
        String days = mDaysEditText.getText().toString();

        SetClassActivity.ClassItem classItem = new SetClassActivity.ClassItem();
        classItem.name = className;
        classItem.location = location;
        classItem.block = block;
        classItem.color = mColor;
        classItem.days = days;
        if (addNewClass) {
            SetClassActivity.mClasses.add(classItem);
        } else {
            // this is the edit case, just update class
            SetClassActivity.mClasses.set(classIndex, classItem);
        }
        SetClassActivity.mClassListAdaptor.notifyDataSetChanged();

        saveClassInfo();

        finish();
    }

    // Cancle button clicked
    public void cancelButtonClicked(View view) {
        finish();
    }

    // Delete button clicked
    public void delButtonClicked(View view) {
        if (classIndex == -1)
            return;

        Log.i("Debin", "delete button is clicked.0");
        AlertDialog.Builder alertDiaglogBuilder = new AlertDialog.Builder(this);
        alertDiaglogBuilder
                .setTitle("Are you sure?")
                .setMessage("do you really want to delete this class")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SetClassActivity.mClasses.remove(classIndex);
                        SetClassActivity.mClassListAdaptor.notifyDataSetChanged();
                        saveClassInfo();
                        finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDiaglogBuilder.create();
        alertDialog.show();

        //finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_del_class);
        String action;
        String className, location, block, days;

        mClassNameEditText = findViewById(R.id.classNameEditText);

        mBlockspinner = findViewById(R.id.blockspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.classBlocks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBlockspinner.setAdapter(adapter);
        mBlockspinner.setOnItemSelectedListener(this);

        mLocationEditText = findViewById(R.id.locationEditText);
        mDaysEditText = findViewById(R.id.daysEditText);
        mPickColorButton = findViewById(R.id.pickColorButton);
        mSaveButton = findViewById(R.id.newClassSaveButton);
        mDelButton = findViewById(R.id.editClassDelButton);
        mCancelButton = findViewById(R.id.editClassCancelButton);

        Intent intent = getIntent();
        action = intent.getStringExtra("action");
        classIndex = intent.getIntExtra("classId", -1);
        if (action.equals("add")) {
            addNewClass = true;
        } else {
            if (!action.equals("edit")) {
                Log.i("Debin", "Invalid edit class action: " + action);
                finish();
            }
        }

        if (addNewClass) {
            // do not show delete button if user is to add new class
            mDelButton.setVisibility(View.INVISIBLE);
        }

        if (addNewClass) {
            mColor = 0xAAAAAA; // default color, defined in xml
        } else {
            // this is the edit class case
            SetClassActivity.ClassItem classItem = SetClassActivity.mClasses.get(classIndex);
            Log.i("Debin", "Select classId: " + classIndex);
            mColor = classItem.color;
            className = classItem.name;
            location = classItem.location;
            block = classItem.block;
            days = classItem.days;

            mClassNameEditText.setText(className);
            mClassNameEditText.setTextColor(mColor);
            mBlockspinner.setTooltipText(block);
            mLocationEditText.setText(location);
            mDaysEditText.setText(days);
            mPickColorButton.setBackgroundColor(mColor);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedBlock = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),selectedBlock,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
