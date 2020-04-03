package com.example.cruiseTrip.roomBooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.adapters.RoomsAdapter;
import com.example.cruiseTrip.authentication.Session;
import com.example.cruiseTrip.database.entity.Room;
import com.example.cruiseTrip.database.entity.RoomService;
import com.example.cruiseTrip.database.viewModel.RoomViewModel;
import com.example.cruiseTrip.ui.ActionActivity;
import com.example.cruiseTrip.util.Converters;
import com.example.cruiseTrip.util.PriceCalculator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ConfirmBookingActivity extends AppCompatActivity {

    private int peopleCount, days;
    private ArrayList<Integer> selectedRoomsId;
    private String roomType;
    private String date;
    private TextView priceTxt;
    private Button btnConfirm;
    private Button btnBack;
    private String s;
    private ArrayList<Room> roomsSelected;
    private int peoplePrice, roomPrice, totalPrice;
    private PriceCalculator pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking);
        
        
        // Get extras
        peopleCount = getIntent().getIntExtra("peopleCount", 0);
        selectedRoomsId = getIntent().getIntegerArrayListExtra("selectedRoomsId");
        roomType = getIntent().getStringExtra("roomType");
        date = getIntent().getStringExtra("date");

        priceTxt = findViewById(R.id.booking_price);
        btnConfirm = findViewById(R.id.booking_confirm);
        btnBack = findViewById(R.id.booking_back);

        int roomCount = selectedRoomsId.size();
        s = "You have selected " + roomCount + " " + roomType + "(s).\n";
        
        // Retrieve selected rooms
        RoomViewModel model = new RoomViewModel(this.getApplication());
        roomsSelected = new ArrayList<>();
        for(int selectedRoomId : selectedRoomsId) {
            roomsSelected.add(model.getRoom(selectedRoomId));
            s += "Room: " + selectedRoomId + " ";
        }
        s += "\n";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Calendar calendar = Calendar.getInstance();
            sdf.setCalendar(calendar);
            String fromDays = sdf.format(calendar.getTime());
            Converters converters = new Converters();
            days = converters.differentiatingDays(fromDays, date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        s += "On the date of " + date + "\n";

        pc = new PriceCalculator();
        peoplePrice = pc.calPeoplePrice(peopleCount);
        roomPrice = pc.calRoomPrice(roomType, days);
        totalPrice = peoplePrice + roomPrice;

        s += "The price for " + peopleCount + " people is: $" + peoplePrice + "\n";
        s += "The price for " + roomType + " is: $" + roomPrice + "\n";
        s += "The total price is: $" + totalPrice;
        priceTxt.setText(s);


        btnConfirm.setOnClickListener(v -> {
            // Get userId from session
            Session session = new Session(getApplicationContext());
            int userId = session.getUserId();

            // Set room state
            for (Room roomSelected : roomsSelected) {
                model.bookRoomById(roomSelected.getId(), userId);
            }
            session.setPrice(totalPrice);
            Intent intent = new Intent(ConfirmBookingActivity.this, RoomServiceActivity.class);
            startActivity(intent);
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ConfirmBookingActivity.this, ActionActivity.class);
            RoomsAdapter.selectedRoomsId = null;
            startActivity(intent);
        });
    }
}
