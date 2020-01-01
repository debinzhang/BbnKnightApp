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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SetClassActivity extends AppCompatActivity {
    public static class ClassItem {
        String name;
        String block;
        String location;
        String days;
        int color;
    };

    ListView mListView;
    static public ArrayList<ClassItem> mClasses = new ArrayList<>();
    static ClassListAdaptor mClassListAdaptor;

    // on click Add Class button
    public void addClassButtonClicked(View view) {
        Intent intent = new Intent(this, addOrDelClassActivity.class);
        intent.putExtra("action", "add");
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_class);

        mListView = findViewById(R.id.classListView);
        mClassListAdaptor = new ClassListAdaptor(this, android.R.layout.simple_list_item_1,
                mClasses);
        mListView.setAdapter(mClassListAdaptor);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SetClassActivity.this, addOrDelClassActivity.class);
                intent.putExtra("action", "edit");
                intent.putExtra("classId", i);
                startActivity(intent);
            }
        });
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
            classNameTextView.setTextColor(mClasses.get(position).color);
            classBlockTextView.setText(mClasses.get(position).block);

            return view;
        }
    }

}
