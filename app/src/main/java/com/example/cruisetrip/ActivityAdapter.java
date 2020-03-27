package com.example.cruisetrip;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cruisetrip.database.SpecialActivity;

import java.util.List;


public class ActivityAdapter extends ArrayAdapter {

    private final Activity context;
    private final List<SpecialActivity> actityItems;

    public ActivityAdapter(Activity context, List<SpecialActivity> item){
        super(context, R.layout.activities_list,item);
        this.actityItems = item;

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activities_list,null, false);

        TextView title = rowView.findViewById(R.id.title);
        title.setText(actityItems.get(position).getTitle());

        //and other information

        return rowView;
    }

}
