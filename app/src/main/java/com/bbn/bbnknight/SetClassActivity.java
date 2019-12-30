package com.bbn.bbnknight;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SetClassActivity extends AppCompatActivity {
    public class ClassItem {
        String name;
        String block;
        String location;
        String days;
        int color;
    };

    // on click Add Class button
    public void addClassButtonClicked(View view) {
        Log.i("Debin", "addClassButtonClicked");
        Toast.makeText(this, "addClassButtonClicked!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, addNewClassActivity.class);
        startActivity(intent);
    }

    ListView mListView;
    public ArrayList<ClassItem> mClasses = new ArrayList<>();
    ClassListAdaptor mClassListAdaptor;

    public void initClassBlocks() {
        ClassItem classItem;
        classItem = new ClassItem();
        classItem.name = "Math";
        classItem.block = "A Block";
        mClasses.add(classItem);

        classItem = new ClassItem();
        classItem.name = "English";
        classItem.block = "C Block";
        mClasses.add(classItem);

        classItem = new ClassItem();
        classItem.name = "Chemistry";
        classItem.block = "D Block";
        mClasses.add(classItem);

        classItem = new ClassItem();
        classItem.name = "Computer Science";
        classItem.block = "G Block";
        mClasses.add(classItem);

        classItem = new ClassItem();
        classItem.name = "Physics";
        classItem.block = "H Block";
        mClasses.add(classItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_class);

        initClassBlocks();

        mListView = findViewById(R.id.classListView);
        mClassListAdaptor = new ClassListAdaptor(this, android.R.layout.simple_list_item_1,
                mClasses);
        mListView.setAdapter(mClassListAdaptor);
    }

    class ClassListAdaptor extends ArrayAdapter<ClassItem> {
        public ClassListAdaptor(@NonNull Context context, int resource, ArrayList<ClassItem> classList) {
            super(context, resource, classList);
        }

        @Override
        public int getCount() {
            return mClasses.size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.class_item_layout, null);

            TextView classNameTextView = view.findViewById(R.id.classNameTextView);
            TextView classBlockTextView = view.findViewById(R.id.classBlockTextView);

            classNameTextView.setText(mClasses.get(position).name);
            classBlockTextView.setText(mClasses.get(position).block);

            return view;
        }
    }

}
