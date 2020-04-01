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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GridItemAdapter extends BaseAdapter {
    private List<Room> rooms;
    private Context context;
    private LayoutInflater inflater;
    private int imgNormal;
    private int imgSelected;
    private int imgSold;
    private int imgUnavailable;
    private String roomType;
    private AtomicBoolean indicator;
    public static ArrayList<Integer> selectedRoomsId;

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
        }
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_seat, null);
        }

        ImageView imageView = convertView.findViewById(R.id.image_view);
        TextView itemView = convertView.findViewById(R.id.txt_item_room);

        Room room = getItem(position);
        selectedRoomsId = new ArrayList<>();
        indicator = new AtomicBoolean(false);

        // if(room.getId() == 206) room.setState(false);

        // Set images
        if(room.getType().equals(roomType) && room.isState()) {
            imageView.setImageResource(imgNormal);

            convertView.setOnClickListener(v -> {
                if (!indicator.get()) {
                    imageView.setImageResource(imgSelected);
                    indicator.set(true);
                } else {
                    imageView.setImageResource(imgNormal);
                    indicator.set(false);
                }
                if(indicator.get()) {
                    selectedRoomsId.add(room.getId());
                }
            });
        } else if (!room.isState()) {
            imageView.setImageResource(imgSold);
        } else {
            imageView.setImageResource(imgUnavailable);
        }

        // Set text
        itemView.setText(Integer.toString(room.getId()));

        return convertView;
    }
}
