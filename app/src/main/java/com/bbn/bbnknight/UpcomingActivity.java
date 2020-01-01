package com.bbn.bbnknight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

public class UpcomingActivity extends AppCompatActivity {

    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);

        mCalendarView = findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView,
                                            int year, int month, int day) {
                String dateStr = (month+1) + "/" + day + "/" + year;
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                // dayofWeek: Sun:1, Mon:2, Tue:3, W:4, Th:5, F:6, Sat:7
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                if (dayOfWeek==1 || dayOfWeek==7) {
                    Toast.makeText(UpcomingActivity.this, "No class on Weekend",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(UpcomingActivity.this, FutureDayActivity.class);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                intent.putExtra("year", year);
                intent.putExtra("dayOfWeek", dayOfWeek);

                startActivity(intent);
            }
        });
    }
}
