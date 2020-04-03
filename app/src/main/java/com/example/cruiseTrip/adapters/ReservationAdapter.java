package com.example.cruiseTrip.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.ResRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationHolder> {

    private final LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<ResRecord> resRecords;

    public ReservationAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        resRecords = new ArrayList<>();

    }

    @Override
    public void onBindViewHolder(@NonNull ReservationHolder holder, int position) {

        ResRecord resRecord = resRecords.get(position);

        holder.title.setText(resRecord.getTitle());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

        Date pdate = resRecord.getDate();
        if(pdate != null)
            holder.date.setText(simpleDateFormat.format(pdate));
        else
            holder.date.setText("All Day");

        int num = resRecord.getNum_people();
        if(num == 0)
            holder.num.setVisibility(View.GONE);
        else{
            holder.num.setText(num + " people \n Group: " + resRecord.getGroup() );
        }

        double pprice = resRecord.getPrice();
        if(pprice == 0) {
            holder.price.setText("Free!!!");
        }
        else {
            if(num == 0)
                num = 1;
            holder.num.setVisibility(View.VISIBLE);
            holder.num.setText(resRecord.getGroup());
            holder.price.setText("$" + resRecord.getPrice()*num);
        }

    }


    @NonNull
    @Override
    public ReservationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_resre, parent, false);
        return new ReservationAdapter.ReservationHolder(view);
    }

    @Override
    public int getItemCount() {
        if(resRecords != null)
            return resRecords.size();
        return 0;
    }


    public void setRecord(ArrayList resRecords){
        this.resRecords = resRecords;
        notifyDataSetChanged();
    }

    class ReservationHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView date;
        public TextView num;
        public TextView price;

        ReservationHolder(View view){
            super(view);
            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
            num = view.findViewById(R.id.number);
            price = view.findViewById(R.id.price);

        }

    }
}
