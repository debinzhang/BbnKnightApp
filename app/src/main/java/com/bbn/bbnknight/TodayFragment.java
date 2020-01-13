package com.bbn.bbnknight;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Locale;

import static java.time.temporal.ChronoUnit.MILLIS;

public class TodayFragment extends Fragment {
    class ViewInfo {
        boolean isSchoolOn;
        String message;
        View view;
        DayOfWeek dayOfWeek;
        long remainingTimeMils;
        boolean beforeSchoolStart;
        boolean beforeClassStart;
        ArrayList<BlocksInWeek.BlockItem> dayBlocks;
        ArrayList<BlocksInWeek.BlockItem> remainingDayBlocks;

        ViewInfo() {
            resetViewInfo();
        }

        public void resetViewInfo() {
            isSchoolOn = false;
            message = "";
            view = null;
            remainingDayBlocks = null;
            remainingTimeMils = 0;
            beforeSchoolStart = false;
            beforeClassStart = false;
            remainingDayBlocks = new ArrayList<BlocksInWeek.BlockItem>();
        }
    }

    class TodayBlockListAdaptor extends ArrayAdapter<BlocksInWeek.BlockItem> {
        public CountDownTimer mCountDownTimer;
        private long mTimerLeftInMillis;
        TextView mTimeLeftTv;
        TextView mTimeLeftMsgTv;

        public TodayBlockListAdaptor(@NonNull Context context, int resource,
                                ArrayList<BlocksInWeek.BlockItem> blockList) {
            super(context, resource, blockList);
        }

        @Override
        public int getCount() {
            return mViewInfo.remainingDayBlocks.size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = null;
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
            ImageView blockImageView = view.findViewById(R.id.blockImage);

            if (position == 0) {
                mTimeLeftTv = view.findViewById(R.id.timeLeftTv);
                mTimeLeftMsgTv = view.findViewById(R.id.timeLeftMsgTv);
                if (mViewInfo.beforeSchoolStart) {
                    mViewInfo.message = "to school start";
                } else if (mViewInfo.beforeClassStart) {
                    mViewInfo.message = "before class start";
                } else {
                    mViewInfo.message = "left for class";
                }
                startTimer();
            }
            BlocksInWeek.BlockItem block = mViewInfo.remainingDayBlocks.get(position);
            String blockNameStr = block.name;
            BlocksInWeek.Block_Type type = block.type;
            String className = "No Class";
            int color = 0;
            String location = "N/A";
            boolean first_lunch = !configureLunchBlockActivity.mLunchBlocks[mViewInfo.dayOfWeek.getValue()-1];
            boolean isLunchBlock = false;

            if (blockImageView != null) {
                blockImageView.setImageResource(block.blockImage);
            }

            // find block's corresponding class
            //  static public ArrayList<ClassItem> mClasses = new ArrayList<>();
            boolean classFound = false;
            for (SetClassActivity.ClassItem classItem : SetClassActivity.mClasses) {
                if (classItem.block.equals(block.name)) {
                    classFound = true;
                    className = classItem.name;
                    color = classItem.color;
                    location = classItem.location;

                    if (block.type == BlocksInWeek.Block_Type.LAB_CONF) {
                        blockNameStr += " / lab conf";
                    }

                    break;
                }
            }

            classNameTv.setText(className);
            timeTv.setText(block.start_time + " -> " + block.end_time);
            blockNameTv.setText(blockNameStr);
            roomTv.setText(location);

            if (classFound) {
                classNameTv.setTextColor(color);
                timeTv.setTextColor(color);
                blockNameTv.setTextColor(color);
                roomTv.setTextColor(color);
                if (mTimeLeftTv != null && position == 0) {
                    mTimeLeftTv.setTextColor(color);
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

            // if it is advisory or assembly or activities block, just show block name, no class name
            if (block.type == BlocksInWeek.Block_Type.ADVISORY ||
                    block.type == BlocksInWeek.Block_Type.ASSEMBLY ||
                    block.type == BlocksInWeek.Block_Type.ACTIVITIES) {
                classNameTv.setText(block.name);
                blockNameTv.setText("");
            } else if (block.type == BlocksInWeek.Block_Type.LUNCH) {
                classNameTv.setText(BlocksInWeek.LUNCH_BLOCK);
                blockNameTv.setText("");
            }

            return view;
        }

        private void startTimer() {
            Log.i("Debin", "CountDownTimer started timer!!!!!!!: time: " + Integer.toString((int)mViewInfo.remainingTimeMils));
            mCountDownTimer = new CountDownTimer(mViewInfo.remainingTimeMils, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTimerLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                }

                @Override
                public void onFinish() {
                    Log.i("Debin", "onFinish() called");
                    Log.i("Debin", "Canceling countCDownTimer --------");
                    mCountDownTimer.cancel();
                    testFlag = true;
                    mViewInfo.resetViewInfo();
                    checkSchoolDay();

                    Fragment frg = null;
                    frg = getFragmentManager().findFragmentById(R.id.fragment_container);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(frg);
                    ft.attach(frg);
                    ft.commit();

                }
            }.start();
        }

        private void updateCountDownText() {
            int minites = (int) mTimerLeftInMillis / 1000 / 60;
            int seconds = (int) (mTimerLeftInMillis / 1000) % 60;

            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minites, seconds);
            if (mTimeLeftTv != null) {
                mTimeLeftTv.setText(timeLeftFormatted);
                mTimeLeftMsgTv.setText(mViewInfo.message);
            }
        }
    }

    ViewInfo mViewInfo;
    ListView mListView;
    boolean testFlag = false;

    private TodayBlockListAdaptor mTodayBlockListAdaptor;

    public void checkSchoolDay() {
        LocalDate date = LocalDate.now();
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));

        //day = DayOfWeek.TUESDAY; // for testing only
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
        LocalTime endTimeLt = LocalTime.parse(lastBlockEndTime,
                DateTimeFormatter.ofPattern("h:mma"));
        Log.i("Debin", " last block endtime= " + endTimeLt.toString());

        LocalTime now = LocalTime.now(); // comment out for testing
        Log.i("Debin", "now: " + now.toString());

        int compVal = now.compareTo(endTimeLt);

        if (compVal > 0) {
            Log.i("Debin", "School is over for today");
            mViewInfo.message = "School is over for today";
            mViewInfo.isSchoolOn = false;
        } else {
            mViewInfo.isSchoolOn = true;
        }

        if (mViewInfo.isSchoolOn) {
            boolean firstBlockFound = false;
            int index = 0;
            for (BlocksInWeek.BlockItem block : mViewInfo.dayBlocks) {
                LocalTime classStartTime = LocalTime.parse(block.start_time,
                        DateTimeFormatter.ofPattern("h:mma"));
                LocalTime classEndTime = LocalTime.parse(block.end_time,
                        DateTimeFormatter.ofPattern("h:mma"));

                Log.i("Debin", "index=" + index + " start: " + classStartTime.toString() + " end: " + classEndTime.toString());

                if ( !firstBlockFound && now.compareTo(classEndTime) < 0 ) {
                    firstBlockFound = true;
                    Log.i("Debin", "firstBlockFound: position: " + index);

                    // check if it is before class or school start
                    long diffInMinutes;
                    if (now.compareTo(classStartTime)<0) {
                        diffInMinutes = now.until(classStartTime, MILLIS);
                        if (index == 0) {
                            Log.i("Debin", "It is before school start. diffInMinutes: " + diffInMinutes);
                            mViewInfo.beforeSchoolStart = true;
                        } else {
                            Log.i("Debin", "It is before class start. diffInMinutes: " + diffInMinutes);
                            mViewInfo.beforeClassStart = true;
                        }
                        mViewInfo.remainingTimeMils = diffInMinutes;
                    } else {
                        diffInMinutes = now.until(classEndTime, MILLIS);
                        Log.i("Debin", "It is before class end. DiffInMinutes: " + diffInMinutes);
                        mViewInfo.remainingTimeMils = diffInMinutes;
                    }
                }

                if (firstBlockFound) {
                    Log.i("Debin", "block: " + index + " is added");
                    mViewInfo.remainingDayBlocks.add(block);
                } else {
                    Log.i("Debin", "block: " + index + " is skipped");
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
            Log.i("Debin", "school is not on");
            view = inflater.inflate(R.layout.fragment_no_class, container, false);
            TextView noClassTv = view.findViewById(R.id.noClassTv);
            noClassTv.setText(mViewInfo.message);
        } else {
            Log.i("Debin", "school is on");
            view = inflater.inflate(R.layout.fragment_school_day, container, false);
            mListView = view.findViewById(R.id.school_day_list_view);

            mTodayBlockListAdaptor = new TodayBlockListAdaptor(getContext(),
                    android.R.layout.simple_list_item_1, mViewInfo.remainingDayBlocks);
            mListView.setAdapter(mTodayBlockListAdaptor);
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i("Debin", "onDestroy() is called");
        if (mTodayBlockListAdaptor.mCountDownTimer != null) {
            Log.i("Debin", "Timer is destroyed!");
            mTodayBlockListAdaptor.mCountDownTimer.cancel();
        }
    }
}
