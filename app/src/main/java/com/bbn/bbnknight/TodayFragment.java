package com.bbn.bbnknight;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

import static com.bbn.bbnknight.FutureDayActivity.mBlockListAdaptor;
import static java.time.temporal.ChronoUnit.MINUTES;


public class TodayFragment extends Fragment {
    class ViewInfo {
        boolean isSchoolOn;
        String message;
        View view;
        DayOfWeek dayOfWeek;
        int remainingMinutes;
        boolean beforeSchoolStart;
        ArrayList<BlocksInWeek.BlockItem> dayBlocks;
        ArrayList<BlocksInWeek.BlockItem> remainingDayBlocks;

        ViewInfo() {
            isSchoolOn = false;
            message = "";
            view = null;
            remainingDayBlocks = null;
            remainingMinutes = 0;
            beforeSchoolStart = false;
            remainingDayBlocks = new ArrayList<BlocksInWeek.BlockItem>();
        }
    }

    class TodayBlockListAdaptor extends ArrayAdapter<BlocksInWeek.BlockItem> {
        public TodayBlockListAdaptor(@NonNull Context context, int resource,
                                ArrayList<BlocksInWeek.BlockItem> blockList) {
            super(context, resource, blockList);
        }

        @Override
        public int getCount() {
            //Log.i("Debin", "getCount: numOfBlock = " + mSelectDayBlocks.size());
            return mViewInfo.remainingDayBlocks.size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = null;
            int remainingMinutes = 0;
            if (position == 0) {
                // today's first block
                view = getLayoutInflater().inflate(R.layout.block_item_layout_alt, null);
            } else {
                view = getLayoutInflater().inflate(R.layout.block_item_layout, null);
            }
            TextView classNameTv = view.findViewById(R.id.classNameTv);
            TextView timeTv = view.findViewById(R.id.timeTv);
            TextView blockNameTv = view.findViewById(R.id.blockNameTv);
            TextView roomTv = view.findViewById(R.id.roomTv);
            TextView timeLeftTv = view.findViewById(R.id.timeLeftTv);
            if (position == 0 && timeLeftTv!=null) {
                if (mViewInfo.beforeSchoolStart) {
                    timeLeftTv.setText(mViewInfo.remainingMinutes + " minutes before school start");
                } else {
                    timeLeftTv.setText(mViewInfo.remainingMinutes + " minutes left for this class");
                }
            }
            BlocksInWeek.BlockItem block = mViewInfo.remainingDayBlocks.get(position);
            String blockName = block.name;
            BlocksInWeek.Block_Type type = block.type;
            String className = "No Class";
            int color = 0;
            String location = "N/A";
            boolean first_lunch = !configureLunchBlockActivity.mLunchBlocks[mViewInfo.dayOfWeek.getValue()-1];
            boolean isLunchBlock = false;

            // find block's corresponding class
            //  static public ArrayList<ClassItem> mClasses = new ArrayList<>();
            boolean classFound = false;
            for (SetClassActivity.ClassItem classItem : SetClassActivity.mClasses) {
                if (classItem.block.equals(blockName)) {
                    classFound = true;
                    className = classItem.name;
                    color = classItem.color;
                    location = classItem.location;
                    break;
                }
            }

            classNameTv.setText(className);
            timeTv.setText(block.start_time + " -> " + block.end_time);
            blockNameTv.setText(block.name);
            roomTv.setText(location);

            if (classFound) {
                classNameTv.setTextColor(color);
                timeTv.setTextColor(color);
                blockNameTv.setTextColor(color);
                roomTv.setTextColor(color);
                if (timeLeftTv != null && position == 0) {
                    timeLeftTv.setTextColor(color);
                }
            }

            // adjust for lunch block
            if (type == BlocksInWeek.Block_Type.WITH_LUNCH && first_lunch) {
                timeTv.setText(block.alt_start_time + " -> " + block.alt_end_time);
                isLunchBlock = true;
            }

            if (type == BlocksInWeek.Block_Type.LUNCH) {
                if(!first_lunch) {
                    isLunchBlock = true;
                } else {
                    timeTv.setText(block.alt_start_time + " -> " + block.alt_end_time);
                }
            }

            if (isLunchBlock) {
                classNameTv.setText(BlocksInWeek.LUNCH_BLOCK);
                classNameTv.setTextColor(0xFF008888); // set lunch color to Cyan
                blockNameTv.setText("");
                roomTv.setText("Cafeteria");
                roomTv.setTextColor(0xFF008888);
                timeTv.setTextColor(0xFF008888);
            }

            return view;
        }
    }

    ViewInfo mViewInfo;
    ListView mListView;

    //private ArrayList<BlocksInWeek.BlockItem> mSelectDayBlocks;
    private TodayBlockListAdaptor mTodayBlockListAdaptor;


    public void checkSchoolDay() {
        LocalDate date = LocalDate.now();
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));

        day = DayOfWeek.TUESDAY; // for testing only
        //day = DayOfWeek.SUNDAY; // for testing only
        mViewInfo.dayOfWeek = day;

        switch (day) {
            case SATURDAY:
            case SUNDAY:
                mViewInfo.message= "This is weekend. No class today.";
                mViewInfo.isSchoolOn = false;
                return;
            case MONDAY:
                mViewInfo.dayBlocks = BlocksInWeek.weekBlock.get(0);
                break;
            case TUESDAY:
                mViewInfo.dayBlocks = BlocksInWeek.weekBlock.get(1);
                break;
            case WEDNESDAY:
                mViewInfo.dayBlocks = BlocksInWeek.weekBlock.get(2);
                break;
            case THURSDAY:
                mViewInfo.dayBlocks = BlocksInWeek.weekBlock.get(3);
                break;
            case FRIDAY:
                mViewInfo.dayBlocks = BlocksInWeek.weekBlock.get(4);
                break;
            default:
                mViewInfo.message = "Invalid date";
                mViewInfo.dayOfWeek = null;
                return;
        }

        int blockNum = mViewInfo.dayBlocks.size();
        String lastBlockEndTime = mViewInfo.dayBlocks.get(blockNum - 1).end_time;
        Log.i("Debin", "lastBlockEndTime: " + lastBlockEndTime);
        //LocalTime endTimeLt = LocalTime.parse("2:00PM",
        LocalTime endTimeLt = LocalTime.parse(lastBlockEndTime,
                DateTimeFormatter.ofPattern("h:mma"));
        Log.i("Debin", " endtime= " + endTimeLt.toString());
        //LocalTime now = LocalTime.now(); // comment out for testing
        LocalTime now = LocalTime.parse("7:50AM",
            DateTimeFormatter.ofPattern("h:mma"));
        int compVal = now.compareTo(endTimeLt);

        if (compVal > 0) {
            Log.i("Debin", "School is over for today");
            mViewInfo.message = "School is over for today";
            mViewInfo.isSchoolOn = false;
        } else {
            long diffInMinutes = now.until(endTimeLt, MINUTES);
            mViewInfo.message = diffInMinutes + " minutes before school end.";
            mViewInfo.isSchoolOn = true;
            Log.i("Debin", " minutes before school end: " + diffInMinutes);
        }

        if (mViewInfo.isSchoolOn) {
            boolean firstBlockFound = false;
            int index = 0;
            for (BlocksInWeek.BlockItem block : mViewInfo.dayBlocks) {
                LocalTime classEndTime = LocalTime.parse(block.end_time,
                        DateTimeFormatter.ofPattern("h:mma"));
                Log.i("Debin", "class end time: " + classEndTime.toString());

                if ( !firstBlockFound && now.compareTo(classEndTime) < 0 ) {
                    firstBlockFound = true;
                    Log.i("Debin", "firstBlockFund: position: " + index +
                            " classEndTime: " + classEndTime.toString());
                    long diffInMinutes = now.until(classEndTime, MINUTES);
                    mViewInfo.remainingMinutes = (int) diffInMinutes;

                    if (index == 0) {
                        // check if it is before school start
                        LocalTime schoolBeginTime = LocalTime.parse(block.start_time,
                                DateTimeFormatter.ofPattern("h:mma"));
                        if (now.compareTo(schoolBeginTime) < 0) {
                            mViewInfo.beforeSchoolStart = true;
                            diffInMinutes = now.until(schoolBeginTime, MINUTES);
                            Log.i("Debin", "before school start: " + diffInMinutes);
                            mViewInfo.remainingMinutes = (int)diffInMinutes;
                        }
                    }
                }

                if (firstBlockFound) {
                    mViewInfo.remainingDayBlocks.add(block);
                }
                index++;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        mViewInfo = new ViewInfo();
        checkSchoolDay();

        if (!mViewInfo.isSchoolOn) {
            view = inflater.inflate(R.layout.fragment_no_class, container, false);
            TextView noClassTv = view.findViewById(R.id.noClassTv);
            noClassTv.setText(mViewInfo.message);
        } else {
            view = inflater.inflate(R.layout.fragment_school_day, container, false);
            mListView = view.findViewById(R.id.school_day_list_view);

            mTodayBlockListAdaptor = new TodayBlockListAdaptor(getContext(),
                    android.R.layout.simple_list_item_1, mViewInfo.remainingDayBlocks);
            mListView.setAdapter(mTodayBlockListAdaptor);
        }

        return view;
    }
}
