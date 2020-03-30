package com.example.cruiseTrip.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.database.entity.Room;

import java.util.List;

public class GridItemAdapter extends BaseAdapter {
    private List<Room> rooms;
    private Context context;
    private LayoutInflater inflater;
    private int imgNormal;
    private int imgSelected;
    private int imgSold;
    private int imgUnavailable;
    private String roomType;

    public GridItemAdapter(Context context, List<Room> rooms, int[] imgArray, String roomType) {
        this.context = context;
        this.rooms = rooms;
        this.imgNormal = imgArray[0];
        this.imgSelected = imgArray[1];
        this.imgSold = imgArray[2];
        this.imgUnavailable = imgArray[3];
        this.roomType = roomType;
    }

    @Override
    public int getCount() {
        return rooms.size();
    }

    @Override
    public Room getItem(int position) {
        return rooms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rooms.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } else if(convertView == null) {
            convertView = inflater.inflate(R.layout.row_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.image_view);
        TextView textView = convertView.findViewById(R.id.text_view);
        Room room = getItem(position);

        if(room.getType().equals(roomType)) {
            imageView.setImageResource(imgNormal);

            if (!room.isState())
                imageView.setImageResource(imgSold);
        }
        else {
            imageView.setImageResource(imgUnavailable);
        }

        textView.setText(Integer.toString(room.getId()));
        return convertView;
    }

}
