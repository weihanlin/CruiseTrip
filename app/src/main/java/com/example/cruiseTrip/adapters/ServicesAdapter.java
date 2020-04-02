package com.example.cruiseTrip.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.database.entity.Service;
import com.example.cruiseTrip.roomBooking.RoomServiceActivity;
import com.example.cruiseTrip.ui.InvoiceActivity;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.MyViewHolder> {

    private Context context;
    private int images[];
    private List<Service> services;

    public ServicesAdapter(Context ct, int images[], List<Service> services) {
        this.context = ct;
        this.images = images;
        this.services = services;
    }

    @NonNull
    @Override
    public ServicesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_service, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.MyViewHolder holder, int position) {
        holder.serviceImgView.setImageResource(images[position]);
        holder.serviceTxtView.setText(services.get(position).getTitle() + ": $" +
                services.get(position).getPrice());

        holder.serviceImgView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), InvoiceActivity.class);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView serviceImgView;
        TextView serviceTxtView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceImgView = itemView.findViewById(R.id.service_imgView);
            serviceTxtView = itemView.findViewById(R.id.service_txtView);
        }
    }

}
