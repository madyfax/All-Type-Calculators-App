package com.finalcalculator.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Under_EmiCalculator_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_emi_calculator);

    }

    public void emmi(View view) {
        Intent intent=new Intent(Under_EmiCalculator_Activity.this,EmiCalculationActivity.class);
        startActivity(intent);
    }



    public void compare(View view) {
        Intent intent=new Intent(Under_EmiCalculator_Activity.this,CompareLoanActivity.class);
        startActivity(intent);
    }

    public void advance(View view) {
        Intent intent=new Intent(Under_EmiCalculator_Activity.this,CurrencyCalculator.class);
        startActivity(intent);
    }


}
