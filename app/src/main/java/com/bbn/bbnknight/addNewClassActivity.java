package com.bbn.bbnknight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import yuku.ambilwarna.AmbilWarnaDialog;

public class addNewClassActivity extends AppCompatActivity {
    int mColor;
    TextView mClassNameEditText;
    Button mPickColorButton;

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
        Toast.makeText(this, "save new class", Toast.LENGTH_SHORT).show();
    }

    // Cancle button clicked
    public void cancelButtonClicked(View view) {
        Toast.makeText(this, "Cancleclass", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_class);

        mColor  = ContextCompat.getColor(this, R.color.colorPrimary);
        mClassNameEditText = findViewById(R.id.classNameEditText);
        mPickColorButton = findViewById(R.id.pickColorButton);
    }
}
