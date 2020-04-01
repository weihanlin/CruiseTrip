package com.example.cruiseTrip.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.database.SpecialActivity;

import java.util.List;

public class DaysAdapter extends ArrayAdapter {

    private final Activity context;
    private final List<SpecialActivity> dayItem;

    public DaysAdapter(Activity context, List<SpecialActivity> item){
        super(context, R.layout.item_days,item);
        this.dayItem = item;

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_days,null, false);

        TextView title = rowView.findViewById(R.id.dayTitle);

        title.setText(dayItem.get(position).getTitle());

        return rowView;
    }
}
