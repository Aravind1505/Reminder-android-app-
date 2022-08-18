package com.winivin.listapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    LinearLayout EntryLayout;
    EditText entry;
    Button entryButton, date_pick, time_pick;
    ScrollView sv;

    String date_selected, time_selected;

    private boolean check(String str){
        return str.length() != 0 && str.trim().length() != 0;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private View entry(){
        String text = entry.getText().toString();
        if(!check(text))
            return null;

        TextView temp = new TextView(this);
        temp.setText(text.trim());
        temp.setTextSize(24);

        if(date_selected == null)
            return null;

        TextView temp1_date = new TextView(this);
        temp1_date.setText(date_selected);
        temp1_date.setTextSize(12);

        if(time_selected == null)
            return null;

        TextView temp1_time = new TextView(this);
        temp1_time.setText(time_selected);
        temp1_time.setTextSize(12);

        LinearLayout lyv = new LinearLayout(this);
        lyv.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 25);
        lyv.setLayoutParams(params);
        lyv.setBackground(getDrawable(R.drawable.roundstyle));
        lyv.setPadding(25, 10,25,10);

        temp.setTextColor(Color.parseColor("#EEEEEEEE"));
        temp.setTypeface(null, Typeface.BOLD);
        temp.setTextSize(28);

        temp1_date.setTextColor(Color.parseColor("#EEEEEEEE"));
        temp1_date.setTypeface(null, Typeface.ITALIC);
        temp1_date.setTextSize(13);

        temp1_time.setTextColor(Color.parseColor("#EEEEEEEE"));
        temp1_time.setTypeface(null, Typeface.ITALIC);
        temp1_time.setTextSize(13);

        lyv.addView(temp);
        lyv.addView(temp1_date);
        lyv.addView(temp1_time);

        date_selected = null;
        time_selected = null;
        entry.setText(null);

        return lyv;
    }

    // Testing Code - Begin
    @SuppressLint("UseCompatLoadingForDrawables")
    private View entry(String str, String date, String time){

        TextView temp = new TextView(this);
        temp.setText(str.trim());
        temp.setTextSize(24);

        TextView temp1_date = new TextView(this);
        temp1_date.setText(date);
        temp1_date.setTextSize(12);

        TextView temp1_time = new TextView(this);
        temp1_time.setText(time);
        temp1_time.setTextSize(12);

        LinearLayout lyv = new LinearLayout(this);
        lyv.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 25);
        lyv.setLayoutParams(params);
        lyv.setBackground(getDrawable(R.drawable.roundstyle));
        lyv.setPadding(25, 10,25,10);

        temp.setTextColor(Color.parseColor("#EEEEEEEE"));
        temp.setTypeface(null, Typeface.BOLD);
        temp.setTextSize(28);

        temp1_date.setTextColor(Color.parseColor("#EEEEEEEE"));
        temp1_date.setTypeface(null, Typeface.ITALIC);
        temp1_date.setTextSize(13);

        temp1_time.setTextColor(Color.parseColor("#EEEEEEEE"));
        temp1_time.setTypeface(null, Typeface.ITALIC);
        temp1_time.setTextSize(13);

        lyv.addView(temp);
        lyv.addView(temp1_date);
        lyv.addView(temp1_time);

        date_selected = null;
        time_selected = null;
        entry.setText(null);
        return lyv;
    }
    //Testing Code - End

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EntryLayout = (LinearLayout) findViewById(R.id.EntriesLayout);
        entry = (EditText) findViewById(R.id.inputfield);
        entryButton = (Button) findViewById(R.id.entrybutton);
        sv = (ScrollView) findViewById(R.id.scrollview);

        date_pick = (Button)findViewById(R.id.date_pick);
        date_pick.setOnClickListener(view -> {
            com.winivin.listapp.DatePicker datePickerx;
            datePickerx = new com.winivin.listapp.DatePicker();
            datePickerx.show(getSupportFragmentManager(), "Date Pick");
        });

        time_pick = (Button)findViewById(R.id.time_pick);
        time_pick.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    (view1, hourOfDay, minute1) -> time_selected = hourOfDay + ":" + minute1, hour, minute, false);
            timePickerDialog.show();
        });

        entryButton.setOnClickListener(this);
        EntryLayout.setPadding(20,10,20,10);

        //Testing Entries

        for(int i = 0; i < 20; i++)
            EntryLayout.addView((LinearLayout)entry("String"+(i+1), "Hello"+(i+1), "Good"+(i+1)));
    }

    @Override
    public void onClick(View view) {
        LinearLayout lyv = (LinearLayout) entry();
        if(lyv != null)
            EntryLayout.addView(lyv);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR,year);
        mCalendar.set(Calendar.MONTH,month);
        mCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        date_selected = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
    }
}