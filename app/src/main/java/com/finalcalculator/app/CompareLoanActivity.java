package com.finalcalculator.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompareLoanActivity extends AppCompatActivity {
    private EditText loan1AmountEditText, loan1InterestEditText, loan1TenureEditText;
    private EditText loan2AmountEditText, loan2InterestEditText, loan2TenureEditText;
    private RadioGroup loan1FrequencyRadioGroup, loan2FrequencyRadioGroup;
    private Button compareButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compareloan);

        loan1AmountEditText = findViewById(R.id.loan1_amount_edittext);
        loan1InterestEditText = findViewById(R.id.loan1_interest_edittext);
        loan1TenureEditText = findViewById(R.id.loan1_tenure_edittext);
        loan1FrequencyRadioGroup = findViewById(R.id.loan1_frequency_radiogroup);

        loan2AmountEditText = findViewById(R.id.loan2_amount_edittext);
        loan2InterestEditText = findViewById(R.id.loan2_interest_edittext);
        loan2TenureEditText = findViewById(R.id.loan2_tenure_edittext);
        loan2FrequencyRadioGroup = findViewById(R.id.loan2_frequency_radiogroup);

        compareButton = findViewById(R.id.compare_button);
        resultTextView = findViewById(R.id.result_textview);

        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get loan details from EditText fields
                double loan1Amount = Double.parseDouble(loan1AmountEditText.getText().toString());
                double loan1Interest = Double.parseDouble(loan1InterestEditText.getText().toString());
                int loan1Tenure = Integer.parseInt(loan1TenureEditText.getText().toString());
                boolean loan1IsMonthly = loan1FrequencyRadioGroup.getCheckedRadioButtonId() == R.id.loan1_monthly_radiobutton;

                double loan2Amount = Double.parseDouble(loan2AmountEditText.getText().toString());
                double loan2Interest = Double.parseDouble(loan2InterestEditText.getText().toString());
                int loan2Tenure = Integer.parseInt(loan2TenureEditText.getText().toString());
                boolean loan2IsMonthly = loan2FrequencyRadioGroup.getCheckedRadioButtonId() == R.id.loan2_monthly_radiobutton;

                // Calculate EMIs for both loans
                double loan1EMI = calculateEMI(loan1Amount, loan1Interest, loan1Tenure, loan1IsMonthly);
                double loan2EMI = calculateEMI(loan2Amount, loan2Interest, loan2Tenure, loan2IsMonthly);

                // Calculate total interest payable for both loans
                double loan1TotalInterest = calculateTotalInterest(loan1Amount, loan1EMI, loan1Tenure, loan1IsMonthly);
                double loan2TotalInterest = calculateTotalInterest(loan2Amount, loan2EMI, loan2Tenure, loan2IsMonthly);

                // Calculate total payment (principle + interest) for both loans
                double loan1TotalPayment = loan1Amount + loan1TotalInterest;
                double loan2TotalPayment = loan2Amount + loan2TotalInterest;

                // Compare the EMIs
                String result;
                if (loan1EMI < loan2EMI) {
                    result = "Loan 1 has a lower EMI: " + loan1EMI;
                } else if (loan1EMI > loan2EMI) {
                    result = "Loan 2 has a lower EMI: " + loan2EMI;
                } else {
                    result = "Both loans have the same EMI: " + loan1EMI;
                }

                // Display the result in the TextView
                resultTextView.setText(result + "\n\nLoan 1 Total Interest Payable: " + loan1TotalInterest +
                        "\nLoan 1 Total Payment: " + loan1TotalPayment +
                        "\n\nLoan 2 Total Interest Payable: " + loan2TotalInterest +
                        "\nLoan 2 Total Payment: " + loan2TotalPayment);
                resultTextView.setVisibility(View.VISIBLE);
            }
        });
    }

    // Custom method to calculate the EMI (Equated Monthly Installment)
    private double calculateEMI(double principle, double interest, int tenure, boolean isMonthly) {
        // Convert interest rate to monthly rate if the tenure is in months
        if (isMonthly) {
            interest = interest / 12 / 100;
        } else {
            interest = interest / 100;
        }

        // Calculate EMI using custom formula
        double emi;
        if (interest == 0) {
            emi = principle / tenure;
        } else {
            double base = Math.pow(1 + interest, tenure);
            double numerator = principle * interest * base;
            double denominator = base - 1;
            emi = numerator / denominator;
        }

        return emi;
    }

    // Custom method to calculate the total interest payable
    private double calculateTotalInterest(double principle, double emi, int tenure, boolean isMonthly) {
        // Convert interest rate to monthly rate if the tenure is in months
        if (isMonthly) {
            tenure = tenure * 12;
        }

        // Calculate total interest payable using custom formula
        double totalInterest = (emi * tenure) - principle;

        return totalInterest;
    }
}




