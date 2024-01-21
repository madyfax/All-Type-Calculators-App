package com.finalcalculator.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class UnitConversionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText valueEditText;
    private Spinner unitTypeSpinner;
    private Button convertButton;
    private TextView convertedValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_conversion);

        valueEditText = findViewById(R.id.valueEditText);
        unitTypeSpinner = findViewById(R.id.unitTypeSpinner);
        convertButton = findViewById(R.id.convertButton);
        convertedValueTextView = findViewById(R.id.convertedValueTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.unit_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitTypeSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.convertButton) {
            convertUnits();
        }
    }

    private void convertUnits() {
        String inputValue = valueEditText.getText().toString().trim();

        if (inputValue.isEmpty()) {
            Toast.makeText(this, "Please enter a value to convert", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double value = Double.parseDouble(inputValue);
            String selectedUnit = unitTypeSpinner.getSelectedItem().toString();
            double convertedValue = performUnitConversion(value, selectedUnit);

            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            convertedValueTextView.setText("Converted Value: " + decimalFormat.format(convertedValue));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input. Please enter a valid number.", Toast.LENGTH_SHORT).show();
        }
    }

    private double performUnitConversion(double value, String unitType) {
        double convertedValue;

        // Length Unit Conversions
        if (unitType.equals("Centimeters to Inches")) {
            convertedValue = value * 0.393701;
        } else if (unitType.equals("Inches to Centimeters")) {
            convertedValue = value * 2.54;
        } else if (unitType.equals("Meters to Feet")) {
            convertedValue = value * 3.28084;
        } else if (unitType.equals("Feet to Meters")) {
            convertedValue = value * 0.3048;
        }
        // Weight Unit Conversions
        else if (unitType.equals("Kilograms to Pounds")) {
            convertedValue = value * 2.20462;
        } else if (unitType.equals("Pounds to Kilograms")) {
            convertedValue = value * 0.453592;
        }
        // Temperature Unit Conversions
        else if (unitType.equals("Celsius to Fahrenheit")) {
            convertedValue = (value * 9 / 5) + 32;
        } else if (unitType.equals("Fahrenheit to Celsius")) {
            convertedValue = (value - 32) * 5 / 9;
        }
        // Time Unit Conversions
        else if (unitType.equals("Seconds to Minutes")) {
            convertedValue = value / 60;
        } else if (unitType.equals("Minutes to Seconds")) {
            convertedValue = value * 60;
        }
        // Currency Unit Conversions
        else if (unitType.equals("USD to EUR")) {
            convertedValue = value * 0.82;
        } else if (unitType.equals("EUR to USD")) {
            convertedValue = value * 1.22;
        }
        // Add more unit conversions as needed
        else {
            convertedValue = value; // No conversion needed
        }

        return convertedValue;
    }

}
