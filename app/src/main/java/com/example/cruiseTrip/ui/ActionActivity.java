package com.example.cruiseTrip.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.cruiseTrip.adapters.DaysAdapter;
import com.example.cruiseTrip.database.viewModel.ItineraryViewModel;
import com.example.cruiseTrip.R;
import com.example.cruiseTrip.database.SpecialActivity;

import java.util.ArrayList;
import java.util.List;

public class ActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        SharedPreferences sharedPreferences = this.getSharedPreferences("SelectPref", MODE_PRIVATE);
        final int selItin = sharedPreferences.getInt("SELIT",1);

        final ArrayList<SpecialActivity> days = new ArrayList<>();

        ItineraryViewModel itineraryViewModel = new ItineraryViewModel(this.getApplication());
        itineraryViewModel.getDays(selItin).observe(this, new Observer<List<SpecialActivity>>() {
            @Override
            public void onChanged(List<SpecialActivity> specialActivities) {
                days.addAll(specialActivities);

            }
        });

        DaysAdapter adapter = new DaysAdapter(this, days);
        ListView listView = findViewById(R.id.theDay);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //open another activity to show the port of call

            }
        });




        final TextView titleView = findViewById(R.id.itTitle);
        titleView.setText(sharedPreferences.getString("ITNAME","PLACE"));

    }
}
