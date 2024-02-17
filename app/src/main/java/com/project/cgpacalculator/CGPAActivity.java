package com.project.cgpacalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CGPAActivity extends AppCompatActivity {

    TextView nameTV, degreeTV;
    AppCompatButton enterBtn;
    TextView resultTV;

    private EditText[] editTexts = new EditText[8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpaactivity);



        nameTV = findViewById(R.id.nameTV);
        degreeTV = findViewById(R.id.degreeTV);

//        firstSemET = findViewById(R.id.firstSemET);
//        secondSemET = findViewById(R.id.secondSemET);
//        thirdSemET = findViewById(R.id.thirdSemET);
//        forthSemET = findViewById(R.id.forthSemET);
//        fifthSemET = findViewById(R.id.fifthSemET);
//        sixthSemET = findViewById(R.id.sixthSemET);
//        seventhSemET = findViewById(R.id.seventhSemET);
//        eighthSemET = findViewById(R.id.eighthSemET);

        editTexts[0] = findViewById(R.id.firstSemET);
        editTexts[1] = findViewById(R.id.secondSemET);
        editTexts[2] = findViewById(R.id.thirdSemET);
        editTexts[3] = findViewById(R.id.forthSemET);
        editTexts[4] = findViewById(R.id.fifthSemET);
        editTexts[5] = findViewById(R.id.sixthSemET);
        editTexts[6] = findViewById(R.id.seventhSemET);
        editTexts[7] = findViewById(R.id.eighthSemET);

        enterBtn = findViewById(R.id.enterBtn);
        resultTV = findViewById(R.id.resultTV);

        getSupportActionBar().setTitle("CGPA Calculator");


        Intent intent = getIntent();
        String studentName = intent.getStringExtra("student_name");
        nameTV.setText(studentName);
        String degreeName = intent.getStringExtra("degree_name");
        degreeTV.setText(degreeName);

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAverage(view);
            }
        });

    }

    public void calculateAverage(View view) {
        int sum = 0;
        int count = 0;

        for (EditText editText : editTexts) {
            String valueStr = editText.getText().toString().trim();
            if (!TextUtils.isEmpty(valueStr)) {
                double value = Double.parseDouble(valueStr);
                sum += value;
                count++;
            }
        }

        if (count > 0) {
            double average = (double) sum / count;
            resultTV.setText("CGPA: " + average);
        } else {
            resultTV.setText("No values entered.");
        }
    }

}
