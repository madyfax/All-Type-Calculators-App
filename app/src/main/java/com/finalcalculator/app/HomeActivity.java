package com.finalcalculator.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void basic(View view) {
        Intent intent=new Intent(HomeActivity.this,BasicCalculator.class);
        startActivity(intent);
    }



    public void unit(View view) {
        Intent intent=new Intent(HomeActivity.this,UnitConversionActivity.class);
        startActivity(intent);
    }

    public void currency(View view) {
        Intent intent=new Intent(HomeActivity.this,CurrencyCalculator.class);
        startActivity(intent);
    }
    public void emi(View view) {
        Intent intent=new Intent(HomeActivity.this, Under_EmiCalculator_Activity.class);
        startActivity(intent);
    }
    public void bank(View view) {
        Intent intent=new Intent(HomeActivity.this, Under_Banking_Calculation_Activity.class);
        startActivity(intent);
    }

    public void mutual(View view) {
        Intent intent=new Intent(HomeActivity.this, Under_LoanCalculator_Activity.class);
        startActivity(intent);
    }

    public void loan(View view) {
        Intent intent=new Intent(HomeActivity.this, Under_LoanCalculator_Activity.class);
        startActivity(intent);
    }
}
