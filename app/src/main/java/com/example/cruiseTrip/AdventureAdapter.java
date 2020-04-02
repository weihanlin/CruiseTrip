package com.example.cruiseTrip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdventureAdapter extends RecyclerView.Adapter<AdventureAdapter.AdventureHolder> {
    private Context context;
    private final LayoutInflater layoutInflater;
    private List<PortAdventure> portAdventures;

    AdventureAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(@NonNull AdventureHolder holder, int position) {
        if(portAdventures !=null){

            holder.title.setText(portAdventures.get(position).getTitle());

            ArrayList<String> groups = portAdventures.get(position).getAgeGroup();
            ArrayList<Double> prices = portAdventures.get(position).getPrice();

            holder.submit.setEnabled(false);

            for(int i = 0; i < groups.size(); i++){
                holder.group.get(i).setText(groups.get(i));
                holder.price.get(i).setText("$" +prices.get(i));
            }

            holder.check_pri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        holder.check_adult.setEnabled(false);
                        holder.check_kid.setEnabled(false);
                        holder.num_adult.setEnabled(false);
                        holder.num_kid.setEnabled(false);
                    }
                    else {
                        holder.check_adult.setEnabled(true);
                        holder.check_kid.setEnabled(true);

                    }
                    holder.submit.setEnabled(false);

                }
            });

            holder.check_adult.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked){
                        holder.num_adult.setEnabled(true);
                    }
                    else {
                        holder.num_adult.setEnabled(false);
                    }

                    holder.submit.setEnabled(false);

                }
            });


            holder.check_kid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked){
                        holder.num_kid.setEnabled(true);

                    }
                    else {
                        holder.num_kid.setEnabled(false);
                    }

                    holder.submit.setEnabled(false);
                }
            });

            holder.caculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double total = 0;
                    if(holder.check_pri.isChecked()){
                        holder.total_pri.setText(holder.price_pri.getText());
                        return;
                    }

                    if(holder.check_adult.isChecked()){
                        if(!holder.num_adult.getText().toString().equals("")) {
                            total += Double.valueOf(holder.price_adult.getText().subSequence(1, holder.price_adult.getText().length()).toString())
                                    * Integer.valueOf(holder.num_adult.getText().toString());
                        }
                    }

                    if(holder.check_kid.isChecked()){
                        if(!holder.num_kid.getText().toString().equals("")) {
                            total += Double.valueOf(holder.price_kid.getText().subSequence(1, holder.price_kid.getText().length()).toString())
                                    * Integer.valueOf(holder.num_kid.getText().toString());
                        }
                    }

                    holder.total_pri.setText("$" + total);
                    holder.submit.setEnabled(true);
                }
            });

        }
        else {
            holder.title.setText("NO DATA");
        }


    }

    @NonNull
    @Override
    public AdventureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =layoutInflater.inflate(R.layout.adventure_item, parent, false);
        return new AdventureHolder(view);
    }

    @Override
    public int getItemCount() {
        if(portAdventures != null)
            return portAdventures.size();

        return 0;
    }


    public void setSpecialActivities(List<PortAdventure> portAdventures){
        this.portAdventures = portAdventures;
        notifyDataSetChanged();
    }

    class AdventureHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ArrayList<CheckBox> group = new ArrayList<>();
        public ArrayList<TextView> price = new ArrayList<>();

        public CheckBox check_pri;
        public CheckBox check_adult;
        public CheckBox check_kid;

        public TextView price_pri;
        public TextView price_adult;
        public TextView price_kid;

        public EditText num_adult;
        public EditText num_kid;

        public TextView total_pri;

        public Button caculate;
        public Button submit;

        AdventureHolder(View view) {
            super(view);

            title = view.findViewById(R.id.title);
            check_pri = view.findViewById(R.id.checkBox_pri);
            check_adult = view.findViewById(R.id.checkBox_adult);
            check_kid = view.findViewById(R.id.checkBox_kid);

            num_adult = view.findViewById(R.id.editText_adult);
            num_adult.setEnabled(false);

            num_kid = view.findViewById(R.id.editText_kid);
            num_kid.setEnabled(false);

            group.add(check_pri);
            group.add(check_adult);
            group.add(check_kid);

            price_pri = view.findViewById(R.id.price_pri);
            price_adult = view.findViewById(R.id.price_adult);
            price_kid = view.findViewById(R.id.price_kid);

            price.add(price_pri);
            price.add(price_adult);
            price.add(price_kid);

            total_pri = view.findViewById(R.id.price_total);

            caculate = view.findViewById(R.id.calcu);
            submit = view.findViewById(R.id.submit);
        }
    }
}
