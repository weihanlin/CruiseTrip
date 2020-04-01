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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.util.PriceCalculator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class PriceActivity extends AppCompatActivity {

    private EditText etDate;
    private Spinner spinner;
    private Button btnPrice;
    private Button btnConfirm;
    private TextView priceTxt;
    private String s = "";
    private int peopleCount;
    private int fromDays;
    private int toDays;
    private int days;
    private PriceCalculator pc;
    private int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        priceTxt = findViewById(R.id.price_txt);

        // Retrieve extras
        String roomType = getIntent().getStringExtra("roomType");
        ArrayList<Integer> selectedRoomsId = getIntent().getIntegerArrayListExtra("selectedRoomsId");
        int roomCount = selectedRoomsId.size();
        s += "You have selected " + roomCount + " " + roomType + "(s).\n";
        for(int selectedRoomId : selectedRoomsId) { s += "Room: " + selectedRoomId + " "; }
        s += "\n";
        priceTxt.setText(s);

        // Set Spinner
        spinner = findViewById(R.id.price_people_number);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.price_number_of_people, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        // Set Calendar
        Calendar calendar = Calendar.getInstance();
        final int thisYear = calendar.get(Calendar.YEAR);
        final int thisMonth = calendar.get(Calendar.MONTH);
        final int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
        setFromDays(calendar.get(Calendar.DAY_OF_YEAR));

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
                    PriceActivity.this, setListener, thisYear, thisMonth, thisDay);
            // Available from today to 240 days after
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            calendar.add(Calendar.DATE, 240);
            datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
            datePickerDialog.show();
        });

        // Set button OnClickListener
        btnPrice = findViewById(R.id.price_btn);
        btnConfirm = findViewById(R.id.price_btn_confirm);
        btnPrice.setOnClickListener(v -> {
            if (getFromDays() < getToDays()) {
                days = getToDays() - getFromDays();
            } else {
                days = getFromDays() - getToDays() + 365;
            }

            pc = new PriceCalculator();
            int peoplePrice = pc.calPeoplePrice(getPeopleCount());
            int roomPrice = pc.calRoomPrice(roomType, days);
            totalPrice = peoplePrice + roomPrice;

            s += "The total price is: $" + totalPrice;
            priceTxt.setText(s);

            btnConfirm.setVisibility(View.VISIBLE);
        });

        btnConfirm.setOnClickListener(v -> {
            Intent intent = new Intent(PriceActivity.this, ReservationActivity.class);
            startActivity(intent);
        });
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public int getFromDays() {
        return fromDays;
    }

    public void setFromDays(int fromDays) {
        this.fromDays = fromDays;
    }

    public int getToDays() {
        return toDays;
    }

    public void setToDays(int toDays) {
        this.toDays = toDays;
    }

    DatePickerDialog.OnDateSetListener setListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            month = month + 1;
            String date = month + "/" + day + "/" + year;
            etDate.setText(date);
            Calendar calendar = new GregorianCalendar(year, month - 1, day);
            setToDays(calendar.get(Calendar.DAY_OF_YEAR));
        }
    };
}
