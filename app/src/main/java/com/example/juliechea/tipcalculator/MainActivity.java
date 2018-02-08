package com.example.juliechea.tipcalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int tipValue = 0;

    private EditText customInput;
    private EditText totalBill;
    private EditText numPeopleInput;
    private TextView tipText;
    private TextView totalText;
    private TextView totalPerPersonText;
    private RadioButton tenTip;
    private RadioButton fifteenTip;
    private RadioButton twentyTip;
    private RadioButton customTip;
    private Button resetButton;
    private Button calcButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting up edit text
        totalBill = this.findViewById(R.id.totalBill);
        numPeopleInput = this.findViewById(R.id.numPeopleInput);

        // setting up radio button
        tenTip = this.findViewById(R.id.tenPercentTip);
        fifteenTip = this.findViewById(R.id.fifteenPercentTip);
        twentyTip = this.findViewById(R.id.twentyPercentTip);
        customTip = this.findViewById(R.id.customTip);

        // buttons
        resetButton = this.findViewById(R.id.resetButton);
        calcButton = this.findViewById(R.id.calcButton);

        // setting up textviews
        tipText = this.findViewById(R.id.tipText);
        totalText =  this.findViewById(R.id.totalText);
        totalPerPersonText = this.findViewById(R.id.totalPerPersonText);

        calcButton.setEnabled(false); // I feel like this goes somewhere else
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // obtaining input from numPeopleInput to calculate
                int totalPeople = Integer.parseInt(numPeopleInput.getText().toString());
                // obtaining input from totalBill to put into textview 'Total'
                double totalBillIn = Double.parseDouble(totalBill.getText().toString());
                double totalTip = (totalBillIn * tipValue) / 100;
                double tipPerPerson = totalTip / totalPeople;

                totalText.setText("$"+ String.valueOf(totalBillIn) + "0");
                tipText.setText("$" + String.valueOf(totalTip) + "0");
                totalPerPersonText.setText("$" + String.valueOf(tipPerPerson)+ "0");
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("On create", "I clicked reset");

                totalText.setText(R.string.Total_Bill);
                tipText.setText(R.string.Tip);
                totalPerPersonText.setText(R.string.Total_per_person);


            }
        });

    }

    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setNeutralButton("Close",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                findViewById(fieldId).requestFocus();
                            }
                        }).show();
    }

    private View.OnKeyListener mKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            switch (v.getId()) {
                case R.id.totalBill:
                    if(totalBill.getText().length() > 0 && numPeopleInput.getText().length() > 0){
                        calcButton.setEnabled(true);
                    }
                    break;
                case R.id.customInput:
                    if(customInput.getText().length() > 0 ){
                        calcButton.setEnabled(true);
                    }
                    break;
            }

            return false;
        }

    };

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton)view ).isChecked();

        switch(view.getId()){
            case R.id.tenPercentTip:
                if(checked){
                    tipValue = 10;

                }
                break;
            case R.id.fifteenPercentTip:
                if(checked){
                    tipValue = 15;


                }
                break;
            case R.id.twentyPercentTip:
                if(checked){
                    tipValue = 20;

                }
                break;
            case R.id.customTip:
                if(checked){
                    int custom = Integer.parseInt(customInput.getText().toString());
                    tipValue = custom;
                }
                break;





        }

    }
}
