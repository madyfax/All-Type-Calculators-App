package com.finalcalculator.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Under_Banking_Calculation_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_banking_calculator);

    }

    public void currency(View view) {
        Intent intent=new Intent(Under_Banking_Calculation_Activity.this,Currency_Calculator_Activity.class);
        startActivity(intent);
    }



    public void PPF(View view) {
        Intent intent=new Intent(Under_Banking_Calculation_Activity.this,PPF_Calculator_Activity.class);
        startActivity(intent);
    }

    public void simple(View view) {
        Intent intent=new Intent(Under_Banking_Calculation_Activity.this,SimpleCompoundActivity.class);
        startActivity(intent);
    }

    public void cash(View view) {
        Intent intent=new Intent(Under_Banking_Calculation_Activity.this,Mortatorium_calculator_Activity.class);
        startActivity(intent);
    }

}
