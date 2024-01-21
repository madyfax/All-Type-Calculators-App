package com.finalcalculator.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Loan_Amt_Calc_Activity extends AppCompatActivity {

    private EditText emiEditText, interestRateEditText, loanTenureEditText;
    private Spinner tenureSpinner;
    private Button calculateButton, resetButton;
    private TextView totalInterestTextView, totalPrincipleTextView, totalPaymentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_amt_calculator);

        emiEditText = findViewById(R.id.emi_edit_text);
        interestRateEditText = findViewById(R.id.interest_rate_edit_text);
        loanTenureEditText = findViewById(R.id.loan_tenure_edit_text);
        tenureSpinner = findViewById(R.id.tenure_spinner);
        calculateButton = findViewById(R.id.calculate_button);
        resetButton = findViewById(R.id.reset_button);
        totalInterestTextView = findViewById(R.id.total_interest_text_view);
        totalPrincipleTextView = findViewById(R.id.total_principle_text_view);
        totalPaymentTextView = findViewById(R.id.total_payment_text_view);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLoanAmount();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCalculator();
            }
        });
    }

    private void calculateLoanAmount() {
        // Get user input values
        double emi = Double.parseDouble(emiEditText.getText().toString());
        double interestRate = Double.parseDouble(interestRateEditText.getText().toString());
        int loanTenure = Integer.parseInt(loanTenureEditText.getText().toString());
        String tenureOption = tenureSpinner.getSelectedItem().toString();

        // Calculate loan amount based on the input values
        double principle = 0.0;
        if (tenureOption.equals("Yearly")) {
            principle = (emi * 12 * (Math.pow(1 + interestRate / 100, loanTenure) - 1)) / (interestRate / 100 * Math.pow(1 + interestRate / 100, loanTenure));
        } else if (tenureOption.equals("Monthly")) {
            principle = (emi * (Math.pow(1 + interestRate / 100, loanTenure) - 1)) / (interestRate / 100 * Math.pow(1 + interestRate / 100, loanTenure));
        }

        // Calculate total interest payable
        double totalInterest = emi * loanTenure - principle;

        // Calculate total payment (principle + interest)
        double totalPayment = principle + totalInterest;

        // Display the results
        totalInterestTextView.setText("Total Interest Payable: " + totalInterest);
        totalPrincipleTextView.setText("Total Principle: " + principle);
        totalPaymentTextView.setText("Total Payment (Principle + Interest): " + totalPayment);
    }

    private void resetCalculator() {
        emiEditText.setText("");
        interestRateEditText.setText("");
        loanTenureEditText.setText("");
        totalInterestTextView.setText("Total Interest Payable:");
        totalPrincipleTextView.setText("Total Principle:");
        totalPaymentTextView.setText("Total Payment (Principle + Interest):");
    }
}














