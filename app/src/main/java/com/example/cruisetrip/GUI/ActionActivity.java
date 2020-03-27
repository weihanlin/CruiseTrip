package com.example.cruisetrip.GUI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.cruisetrip.ActivityAdapter;
import com.example.cruisetrip.model.DaysAdapter;
import com.example.cruisetrip.model.ItineraryViewModel;
import com.example.cruisetrip.R;
import com.example.cruisetrip.database.SpecialActivity;

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
        final ArrayList<SpecialActivity> activities = new ArrayList<>();

        ItineraryViewModel itineraryViewModel = new ItineraryViewModel(this.getApplication());
        itineraryViewModel.getDays(selItin).observe(this, new Observer<List<SpecialActivity>>() {
            @Override
            public void onChanged(List<SpecialActivity> specialActivities) {
                days.addAll(specialActivities);

            }
        });

        itineraryViewModel.getActivities(selItin).observe(this, new Observer<List<SpecialActivity>>() {
            @Override
            public void onChanged(List<SpecialActivity> specialActivities) {
                activities.addAll(specialActivities);

            }
        });

        DaysAdapter adapter = new DaysAdapter(this, days);
        ListView listView = findViewById(R.id.theDay);


        ActivityAdapter adapter1 = new ActivityAdapter(this, activities);


        final TextView titleView = findViewById(R.id.itTitle);
        titleView.setText(sharedPreferences.getString("ITNAME","PLACE"));


        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.action_list, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Toast.makeText(view.getContext(),"Itinerary",Toast.LENGTH_LONG).show();
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //open another activity to show the port of call

                        }
                    });
                }
                if(position == 1){
                    Toast.makeText(view.getContext(),"Activity",Toast.LENGTH_LONG).show();
                    listView.setAdapter(adapter1);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //open another activity to show the port of call

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
