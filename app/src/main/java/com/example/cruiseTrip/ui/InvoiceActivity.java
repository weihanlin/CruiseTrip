package com.example.cruiseTrip.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cruiseTrip.R;

public class InvoiceActivity extends AppCompatActivity {

    private int peoplePrice;
    private int roomPrice;
    private int totalPrice;
    private int peopleCount;
    private int servicePrice;
    private TextView priceTxtView;
    private Button backBtn;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        peopleCount = getIntent().getIntExtra("peopleCount", 0);
        peoplePrice = getIntent().getIntExtra("peoplePrice",0);
        roomPrice = getIntent().getIntExtra("roomPrice",0);
        totalPrice = getIntent().getIntExtra("totalPrice",0);

        priceTxtView = findViewById(R.id.invoice_txt);
        s = "You have " + peopleCount + " people booked in this trip.\n";
        s += "The price for " + peopleCount + " people: $" + peoplePrice + ".\n";
        s += "The room price: $" + roomPrice + ".\n";
        s += "The total price: $" + totalPrice + ".\n";
        priceTxtView.setText(s);

        backBtn = findViewById(R.id.invoice_btn);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(InvoiceActivity.this, ActionActivity.class);
            startActivity(intent);
        });
    }
}
