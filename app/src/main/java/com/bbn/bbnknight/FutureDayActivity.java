package com.bbn.bbnknight;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FutureDayActivity extends AppCompatActivity {
    static private ArrayList<BlocksInWeek.BlockItem> mSelectDayBlocks;
    static private int mDay, mMonth, mYear, mDayOfWeek;

    ListView mListView;
    static public BlockListAdaptor mBlockListAdaptor;

    class BlockListAdaptor extends ArrayAdapter<BlocksInWeek.BlockItem> {
        public BlockListAdaptor(@NonNull Context context, int resource,
                                ArrayList<BlocksInWeek.BlockItem> blockList) {
            super(context, resource, blockList);
        }

        @Override
        public int getCount() {
            //Log.i("Debin", "getCount: numOfBlock = " + mSelectDayBlocks.size());
            return mSelectDayBlocks.size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Log.i("Debin", "getView...position: " + position);
            View view = getLayoutInflater().inflate(R.layout.block_item_layout, null);
            TextView classNameTv = view.findViewById(R.id.classNameTv);
            TextView timeTv = view.findViewById(R.id.timeTv);
            TextView blockNameTv = view.findViewById(R.id.blockNameTv);
            TextView roomTv = view.findViewById(R.id.roomTv);
            BlocksInWeek.BlockItem block = mSelectDayBlocks.get(position);
            String startTime = block.start_time;
            String endTime = block.end_time;
            String blockName = block.name;
            Log.i("Debin", "blockName:" + blockName + "... startTime: " + startTime + " ... endTime:" + endTime);

            classNameTv.setText("Math");
            timeTv.setText(block.start_time + " - " + block.end_time);
            blockNameTv.setText(block.name);
            roomTv.setText("123");


            return view;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_day);

        mDay = getIntent().getIntExtra("day", -1);
        mMonth = getIntent().getIntExtra("month", -1);
        mYear = getIntent().getIntExtra("year", -1);
        mDayOfWeek = getIntent().getIntExtra("dayOfWeek", -1);
        mListView = findViewById(R.id.blockListview);
        
        Log.i("Debin", "Receiving date: " + (mMonth+1) + "/" + mDay + "/" + mYear);

        if (mDayOfWeek != -1 && mDayOfWeek >= 2 && mDayOfWeek <= 6) {
            Log.i("Debin", "futureDayActivity get dayOfWeek: " + mDayOfWeek);
            // dayofWeek: Sun:1, Mon:2, Tue:3, W:4, Th:5, F:6, Sat:7
            mSelectDayBlocks = BlocksInWeek.weekBlock.get(mDayOfWeek - 2);

            if (mSelectDayBlocks == null) {
                Log.i("Debin", "Get Null mBlock");
                return;
            }

            String str = mSelectDayBlocks.get(1).name;
            Log.i("Debin-10", "weekblock: " + str);
            str = mSelectDayBlocks.get(2).name;
            Log.i("Debin-20", "weekblock: " + str);
        } else {
            Toast.makeText(FutureDayActivity.this, "Invalid day of week:" + mDayOfWeek,
                    Toast.LENGTH_LONG).show();
            return;
        }

        Log.i("Debin", "futureDayActivity 1");
        mBlockListAdaptor = new BlockListAdaptor(FutureDayActivity.this,
                android.R.layout.simple_list_item_1, mSelectDayBlocks);
        Log.i("Debin", "futureDayActivity 2");
        
        mListView.setAdapter(mBlockListAdaptor);
        Log.i("Debin", "futureDayActivity 3");

    }
}
