package com.finalcalculator.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Under_LoanCalculator_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_loan_calculator);

    }

    public void loan_amt(View view) {
        Intent intent=new Intent(Under_LoanCalculator_Activity.this,Loan_Amt_Calc_Activity.class);
        startActivity(intent);
    }

    public void loan_tenure(View view) {
        Intent intent=new Intent(Under_LoanCalculator_Activity.this,Loan_Tenure_Calculator.class);
        startActivity(intent);
    }

    public void interst_rate(View view) {
        Intent intent=new Intent(Under_LoanCalculator_Activity.this,Loan_interest_rate_Calculator.class);
        startActivity(intent);
    }

    public void morto_calc(View view) {
        Intent intent=new Intent(Under_LoanCalculator_Activity.this,Mortatorium_calculator_Activity.class);
        startActivity(intent);
    }


}
