package com.finalcalculator.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.text.DecimalFormat;

public class EmiCalculationActivity extends AppCompatActivity {

    TextView textView;
    TextView textView1;
    EditText editText;
    EditText editText1;
    EditText editText2;
    TextView ResetButton;
    TextView ShareButton;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    EditText editText3;
    float fflt = 0.0f;
    float fght = 0.0f;
    float intrestrate = 0.0f;
    float monthlyrate = 0.0f;
    float fliyh = 0.0f;
    float flatg = 0.0f;
    float paydayintrest = 0.0f;
    float PayableInterestPercent = 0.0f;
    float PayablePrinciplePercent = 0.0f;
    float PrincipleAmount = 0.0f;
    float flat3 = 0.0f;
    float flity = 0.0f;
    float flaty = 0.0f;
    DatabaseHelperAS helperAS;
    DecimalFormat format = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emicalculator);



        helperAS = new DatabaseHelperAS(this);
        editText2 = (EditText) findViewById(R.id.principle_amount_edit_text);
        editText = (EditText) findViewById(R.id.interest_rate_edit_text);
        editText3 = (EditText) findViewById(R.id.years_edit_text);
        editText1 = (EditText) findViewById(R.id.months_edit_text);
        textView1 = (TextView) findViewById(R.id.emi_result_text_view);
        textView2 = (TextView) findViewById(R.id.total_interest_result_text_view);
        textView4 = (TextView) findViewById(R.id.total_principle_result_text_view);
        textView3 = (TextView) findViewById(R.id.total_payable_amount_result_text_view);
        textView = (TextView) findViewById(R.id.calculate_text_view);
        ResetButton = (TextView) findViewById(R.id.resetbtn);
        ShareButton = (TextView) findViewById(R.id.shareUs);

        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(textView.getWindowToken(), 0);
                EMI();

            }
        });

        ResetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ResetButton.getWindowToken(), 0);
                EmiCalculationActivity.this.editText2.setText("");
                EmiCalculationActivity.this.editText.setText("");
                EmiCalculationActivity.this.editText3.setText("");
                EmiCalculationActivity.this.editText1.setText("");
                EmiCalculationActivity.this.textView1.setText("0");
                EmiCalculationActivity.this.textView2.setText("0");
                EmiCalculationActivity.this.textView4.setText("0");
                EmiCalculationActivity.this.textView3.setText("0");
            }
        });
        this.ShareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (EmiCalculationActivity.this.flity != 0.0f) {
                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                        String shareMessage = "\nLet me recommend you this application\n\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "choose one"));
                    } catch (Exception e) {
                        //e.toString();
                    }
                    return;
                }
                Toast.makeText(EmiCalculationActivity.this.getApplicationContext(), "No Result To Share!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.editText2.setText(HistoryHelper.sdp2);
        this.editText.setText(HistoryHelper.sdp3);
        this.editText3.setText(HistoryHelper.sdp4);
        this.editText1.setText(HistoryHelper.sdp1);

    }



    public void EMI() {
        if (!editText2.getText().toString().trim().isEmpty()) {
            PrincipleAmount = Float.parseFloat(editText2.getText().toString().trim());
            if (!editText.getText().toString().trim().isEmpty()) {
                intrestrate = Float.parseFloat(editText.getText().toString().trim());
                if (!editText3.getText().toString().trim().isEmpty() || !editText1.getText().toString().trim().isEmpty()) {
                    if (editText3.getText().toString().trim().isEmpty()) {
                        flaty = 0.0f;
                    } else {
                        flaty = Float.parseFloat(editText3.getText().toString().trim());
                    }
                    if (editText1.getText().toString().trim().isEmpty()) {
                        monthlyrate = 0.0f;
                    } else {
                        monthlyrate = Float.parseFloat(editText1.getText().toString().trim());
                    }
                    flatg = PrincipleAmount;
                    float f = intrestrate / 1200.0f;
                    flat3 = f;
                    float f2 = monthlyrate + (flaty * 12.0f);
                    fliyh = f2;
                    float pow = (float) Math.pow((double) (f + 1.0f), (double) f2);
                    float f3 = flatg * ((flat3 * pow) / (pow - 1.0f));
                    fflt = f3;
                    fght = f3;
                    float f4 = f3 * fliyh;
                    flity = f4;
                    float f5 = PrincipleAmount;
                    float f6 = f4 - f5;
                    paydayintrest = f6;
                    PayableInterestPercent = (f6 / f4) * 100.0f;
                    PayablePrinciplePercent = (f5 / f4) * 100.0f;
                    TextView textView = textView1;
                    textView.setText(" " + format.format((double) fflt));
                    TextView textView2 = this.textView2;
                    textView2.setText(" " + format.format((double) paydayintrest));
                    TextView textView3 = this.textView4;
                    textView3.setText(" " + format.format((double) PrincipleAmount));
                    TextView textView4 = this.textView3;
                    textView4.setText(" " + format.format((double) flity));
//                    InterestProgress.setProgress(Float.parseFloat(decimalFormat.format((double) _PayableInterestPercent)));
//                    PrincipleProgress.setProgress(Float.parseFloat(decimalFormat.format((double) _PayablePrinciplePercent)));
                    helperAS.addEMI_History(String.valueOf(format.format((double) PrincipleAmount)), String.valueOf(format.format((double) intrestrate)), String.valueOf(this.format.format((double) this.flaty)), String.valueOf(this.format.format((double) this.monthlyrate)));
                    return;
                }
                Toast.makeText(getApplicationContext(), "Please fill all the information to Calculate EMI", Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(getApplicationContext(), "Please fill all the information to Calculate EMI", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "Please fill all the information to Calculate EMI", Toast.LENGTH_LONG).show();
    }

    @Override
    public void     onBackPressed() {
        finish();

    }
}

