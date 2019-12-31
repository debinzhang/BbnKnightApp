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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import yuku.ambilwarna.AmbilWarnaDialog;

public class addNewClassActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int mColor;
    EditText mClassNameEditText;
    EditText mLocationEditText;
    Spinner mBlockspinner;
    EditText mDaysEditText;
    Button mPickColorButton;
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
        SetClassActivity.mClasses.add(classItem);
        SetClassActivity.mClassListAdaptor.notifyDataSetChanged();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                "com.bbn.bbnknight", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(SetClassActivity.mClasses);
        prefsEditor.putString("classes", json);
        prefsEditor.commit();

        finish();
    }

    // Cancle button clicked
    public void cancelButtonClicked(View view) {
        Toast.makeText(this, "Cancleclass", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Evan", "onCreate 1");
        setContentView(R.layout.activity_add_new_class);
        Log.i("Evan", "onCreate 2");

        //mColor  = ContextCompat.getColor(this, R.color.colorPrimary);
        mColor = 0xAAAAAA; // default color, defined in xml
        mClassNameEditText = findViewById(R.id.classNameEditText);
        mBlockspinner = findViewById(R.id.blockspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.classBlocks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBlockspinner.setAdapter(adapter);
        mBlockspinner.setOnItemSelectedListener(this);
        mLocationEditText = findViewById(R.id.locationEditText);
        mDaysEditText = findViewById(R.id.daysEditText);
        mPickColorButton = findViewById(R.id.pickColorButton);
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
