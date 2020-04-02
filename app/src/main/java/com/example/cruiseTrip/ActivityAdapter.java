package com.example.cruiseTrip;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cruiseTrip.database.SpecialActivity;
import com.example.cruiseTrip.database.entity.Reservation;
import com.example.cruiseTrip.database.viewModel.ItineraryViewModel;

import java.util.List;


public class ActivityAdapter extends ArrayAdapter {

    private final Activity context;
    private final List<SpecialActivity> actityItems;
    private final List<Reservation> reservations;
    private ItineraryViewModel itineraryViewModel;

    public ActivityAdapter(Activity context, List<SpecialActivity> item, List<Reservation> reservations){
        super(context, R.layout.activities_list,item);
        this.actityItems = item;

        this.context = context;

//        this.sharedPreferences = context.getSharedPreferences("SelectPref", MODE_PRIVATE);

        this.itineraryViewModel = new ItineraryViewModel(context.getApplication());
        this.reservations = reservations;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activities_list,null, false);

        TextView title = rowView.findViewById(R.id.title);
        title.setText(actityItems.get(position).getTitle());

        TextView num = rowView.findViewById(R.id.num);

        Button btn = rowView.findViewById(R.id.submit);


        final int activity_id = actityItems.get(position).getId();

        //TODO: fake date user id
        final int userId = 1;


        int count = 0;

        for(int i = 0;i < reservations.size(); i++){
            if(reservations.get(i).getActivity_id() == activity_id) {
                if(reservations.get(i).getUser_id() == userId)
                    btn.setText("Going");

                count++;
            }
        }

        num.setText(""+count);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: or check session
                if(userId  == 0){
                    Toast.makeText(context,"You need to log in first", Toast.LENGTH_LONG).show();
                    return;
                }

                int count = Integer.valueOf(num.getText().toString());

                if(btn.getText().toString().equals("Going")){
                    itineraryViewModel.cancelReservation(userId, activity_id);
                    count--;
                    btn.setText("Interest");

                    Toast.makeText(context,"Canceling ...", Toast.LENGTH_LONG).show();
                }
                else if(btn.getText().toString().equals("Interest")){
                    itineraryViewModel.reserveActivity(userId, activity_id);
                    count++;
                    btn.setText("Going");
                    Toast.makeText(context,"Good to go!", Toast.LENGTH_LONG).show();
                }
                num.setText(""+count);
            }
        });


        //and other information

        return rowView;
    }

}
