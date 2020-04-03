package com.example.cruiseTrip.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.ResRecord;
import com.example.cruiseTrip.adapters.ItinerariesAdapter;
import com.example.cruiseTrip.adapters.ReservationAdapter;
import com.example.cruiseTrip.authentication.Session;
import com.example.cruiseTrip.database.SpecialActivity;
import com.example.cruiseTrip.database.UsersRepository;
import com.example.cruiseTrip.database.entity.Reservation;
import com.example.cruiseTrip.database.viewModel.ItineraryViewModel;

import java.util.ArrayList;
import java.util.List;

public class ReservationRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_record);

        SharedPreferences sharedPreferences = this.getSharedPreferences("SelectPref", MODE_PRIVATE);
        final int selItin = sharedPreferences.getInt("SELIT",1);

        Session session = new Session(this);
        String username = session.getUsername();
        UsersRepository usersRepository = new UsersRepository(this.getApplication());

        final TextView total = findViewById(R.id.textView6);

        int userId = 0;

        if(usersRepository.getUser(username) != null)
            userId = usersRepository.getUser(username).getId();

        RecyclerView recyclerView = findViewById(R.id.recycler_vi);
        ReservationAdapter reservationRecord = new ReservationAdapter(this);
        recyclerView.setAdapter(reservationRecord);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<ResRecord> resRecords = new ArrayList<>();
        ArrayList<Reservation> reservationArrayList = new ArrayList<>();

        ItineraryViewModel itineraryViewModel = new ItineraryViewModel(this.getApplication());

        LiveData<List<Reservation>> reservation = itineraryViewModel.getReservation(userId);
        reservation.observe(this, new Observer<List<Reservation>>() {
            @Override
            public void onChanged(List<Reservation> reservations) {
                resRecords.clear();
                reservationArrayList.clear();
                reservationArrayList.addAll(reservations);

                for(int i = 0; i < reservationArrayList.size(); i++){
                    int act_id = reservationArrayList.get(i).getActivity_id();
                    SpecialActivity sa = itineraryViewModel.getSpecialActivity(act_id);
                    resRecords.add(new ResRecord(sa.getTitle(),sa.getStart(),reservationArrayList.get(i).getNumberPeople(),sa.getPrice(), sa.getDescription()));
                }

                reservationRecord.setRecord(resRecords);

                double da = 0;
                for(int i = 0; i < resRecords.size(); i++){
                    da += resRecords.get(i).getPrice()*resRecords.get(i).getNum_people();
                }
                total.setText("" + da);
            }
        });




    }
}
