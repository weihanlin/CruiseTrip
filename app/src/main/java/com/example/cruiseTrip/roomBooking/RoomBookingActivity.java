package com.example.cruiseTrip.roomBooking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cruiseTrip.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RoomBookingActivity extends AppCompatActivity {

    private EditText etDate;
    private Spinner spinner;
    private Button btnPrice;
    private String date = "";
    private int peopleCount;
    private int fromDays;
    private int toDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_booking);


        // Set Spinner
        spinner = findViewById(R.id.room_people_number);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.price_number_of_people, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        // Set Calendar
        Calendar calendar = Calendar.getInstance();
        final int thisYear = calendar.get(Calendar.YEAR);
        final int thisMonth = calendar.get(Calendar.MONTH);
        final int thisDay = calendar.get(Calendar.DAY_OF_MONTH);

        // Set spinner itemChange Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPeopleCount(position + 1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setPeopleCount(1);
            }
        });

        // Set editText OnClickListener to show calendar
        etDate = findViewById(R.id.room_editText);
        etDate.setInputType(InputType.TYPE_NULL);
        etDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog (
                    RoomBookingActivity.this, setListener, thisYear, thisMonth, thisDay);
            // Available from today to 180 days after
            Calendar calendarTemp = Calendar.getInstance();
            datePickerDialog.getDatePicker().setMinDate(calendarTemp.getTimeInMillis());
            calendarTemp.add(Calendar.DATE, 180);
            datePickerDialog.getDatePicker().setMaxDate(calendarTemp.getTimeInMillis());
            datePickerDialog.show();
        });

        // Set button OnClickListener
        btnPrice = findViewById(R.id.room_btn);
        btnPrice.setOnClickListener(v -> {
            Intent intent = new Intent(RoomBookingActivity.this, RoomTypeActivity.class);
            intent.putExtra("peopleCount", getPeopleCount());
            intent.putExtra("date", date);
            startActivity(intent);
        });
    } // End OnCreate

    DatePickerDialog.OnDateSetListener setListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar calendar = new GregorianCalendar(year, month, day);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setCalendar(calendar);
            date = sdf.format(calendar.getTime());
            etDate.setText(date);
        }
    };

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

}
