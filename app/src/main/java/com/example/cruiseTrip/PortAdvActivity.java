package com.example.cruiseTrip;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cruiseTrip.database.SpecialActivity;
import com.example.cruiseTrip.database.viewModel.ItineraryViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PortAdvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port_adv);

        RecyclerView recyclerView = findViewById(R.id.adv_list);
        AdventureAdapter adventureAdapter = new AdventureAdapter(this);
        recyclerView.setAdapter(adventureAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItineraryViewModel itineraryViewModel = new ItineraryViewModel(this.getApplication());

        SharedPreferences sharedPreferences = this.getSharedPreferences("SelectPref", MODE_PRIVATE);
        final int selItin = sharedPreferences.getInt("SELIT",1);


        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
        Date date= new Date();
        try {
            date = fmt.parse("2020-05-21");
        }
        catch (ParseException e){}

        final long selDate = sharedPreferences.getLong("DATE", date.getTime());
//        adventureAdapter.setSpecialActivities(itineraryViewModel.getPortAdventure(1,date));

        List<PortAdventure> adventures = new ArrayList<>();
        itineraryViewModel.getPortAdventure(selItin, new Date(selDate)).observe(this, new Observer<List<SpecialActivity>>() {
            @Override
            public void onChanged(List<SpecialActivity> specialActivities) {

                for(int i = 0; i < specialActivities.size(); i++){
                    SpecialActivity sp = specialActivities.get(i);

                    boolean found = false;
                    for(int j = 0; j < adventures.size(); j++) {
                        if(adventures.get(j).getTitle().equals(sp.getTitle())){
                            found = true;
                            adventures.get(j).addAgeGroup(sp.getDescription());
                            adventures.get(j).addPrice(sp.getPrice());
                            break;
                        }
                    }
                    if(found == false) {
                        PortAdventure portAdventure = new PortAdventure(sp.getTitle());
                        portAdventure.addPrice(sp.getPrice());
                        portAdventure.addAgeGroup(sp.getDescription());
                        adventures.add(portAdventure);
                    }
                }
                adventureAdapter.setSpecialActivities(adventures);
            }
        });

    }
}
