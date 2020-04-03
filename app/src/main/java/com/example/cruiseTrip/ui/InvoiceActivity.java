package com.example.cruiseTrip.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.authentication.Session;

public class InvoiceActivity extends AppCompatActivity {

    private int totalPrice;
    private int serviceCharge;
    private TextView priceTxtView;
    private Button backBtn;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        serviceCharge = (int)getIntent().getDoubleExtra("service_price", 0);

        Session session = new Session(getApplication());
        totalPrice = session.getPrice();

        priceTxtView = findViewById(R.id.invoice_txt);
        s = "The total price is: $" + totalPrice + ";\n";
        s += "The room service charge is: $" + serviceCharge + ";\n";
        s += "The 15% tips charge is: $" + (int)((totalPrice + serviceCharge) * 0.15) + ";\n";
        s += "The 7% GST charge is: $" + (int)((totalPrice + serviceCharge) * 0.07) + ";\n";
        s += "The 5% PST charge is: $" + (int)((totalPrice + serviceCharge) * 0.05) + ";\n";
        s += "The total price after tax is: $" + (int)((totalPrice + serviceCharge) * 1.27) + ".";
        priceTxtView.setText(s);

        backBtn = findViewById(R.id.invoice_btn);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(InvoiceActivity.this, ActionActivity.class);
            startActivity(intent);
        });
    }
}
