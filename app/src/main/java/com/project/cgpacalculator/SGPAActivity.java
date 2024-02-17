package com.project.cgpacalculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SGPAActivity extends AppCompatActivity {

    private Spinner gradeSpinner1, gradeSpinner2, gradeSpinner3;
    private EditText creditEditText1, creditEditText2, creditEditText3;
    private TextView sgpaTextView;
    AppCompatButton calculateButton;


    //    TextView nameTV, degreeTV;
//    AppCompatButton enterBtn;
//    EditText sub1Credit, sub2Credit, sub3Credit;
//    TextView resultTV;
//    Spinner sub1gradeSpinner, sub2gradeSpinner, sub3gradeSpinner;
//    String sub1Grade, sub2Grade, sub3Grade;
//
//    String[] subGrades = new String[3];
//    private EditText[] editTexts = new EditText[8];
//
//
//    String[] grades = {"O", "A+", "A", "B+", "B", "C", "F", "Ab"};
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgpaactivity);

        gradeSpinner1 = findViewById(R.id.sub1Grade);
        gradeSpinner2 = findViewById(R.id.sub2Grade);
        gradeSpinner3 = findViewById(R.id.sub3Grade);

        creditEditText1 = findViewById(R.id.sub1Credit);
        creditEditText2 = findViewById(R.id.sub2Credit);
        creditEditText3 = findViewById(R.id.sub3Credit);

        sgpaTextView = findViewById(R.id.resultTV);
        calculateButton = findViewById(R.id.enterBtn);
//
//        sub1gradeSpinner = findViewById(R.id.sub1Grade);
//        sub2gradeSpinner = findViewById(R.id.sub2Grade);
//        sub3gradeSpinner = findViewById(R.id.sub3Grade);
//
//        editTexts[0] = findViewById(R.id.sub1Credit);
//        editTexts[1] = findViewById(R.id.sub2Credit);
//        editTexts[2] = findViewById(R.id.sub3Credit);
//
//
//        nameTV = findViewById(R.id.nameTV);
//        degreeTV = findViewById(R.id.degreeTV);
//
//        enterBtn = findViewById(R.id.enterBtn);
//        resultTV = findViewById(R.id.resultTV);
//
//        getSupportActionBar().setTitle("SGPA Calculator");
//
//        Intent intent = getIntent();
//        String studentName = intent.getStringExtra("student_name");
//        nameTV.setText(studentName);
//        String degreeName = intent.getStringExtra("degree_name");
//        degreeTV.setText(degreeName);
//
//        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, grades);
//        sub1gradeSpinner.setAdapter(ad);
//        sub2gradeSpinner.setAdapter(ad);
//        sub3gradeSpinner.setAdapter(ad);
//
//        subGrades[0] = sub1gradeSpinner.getSelectedItem().toString();   // "o"
//        subGrades[1] = sub2gradeSpinner.getSelectedItem().toString();   // "a+"
//        subGrades[2] = sub3gradeSpinner.getSelectedItem().toString();   // "f"
//
//        enterBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                calculateAverage(view);
//            }
//        });
//
//    }


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grades_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gradeSpinner1.setAdapter(adapter);
        gradeSpinner2.setAdapter(adapter);
        gradeSpinner3.setAdapter(adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSGPA();
            }
        });
    }

    private void calculateSGPA() {

        if (creditEditText1.getText().toString().equals("") || creditEditText2.getText().toString().equals("") || creditEditText3.getText().toString().equals("")) {
            sgpaTextView.setText("Some Values are Missing.");
            return;
        } else {

            int credits1 = Integer.parseInt(creditEditText1.getText().toString());
            int credits2 = Integer.parseInt(creditEditText2.getText().toString());
            int credits3 = Integer.parseInt(creditEditText3.getText().toString());

            String grade1 = gradeSpinner1.getSelectedItem().toString();
            String grade2 = gradeSpinner2.getSelectedItem().toString();
            String grade3 = gradeSpinner3.getSelectedItem().toString();

            double gradePoint1 = getGradePoint(grade1);
            double gradePoint2 = getGradePoint(grade2);
            double gradePoint3 = getGradePoint(grade3);

            double totalCredits = credits1 + credits2 + credits3;
            double sgpa = ((credits1 * gradePoint1) + (credits2 * gradePoint2) + (credits3 * gradePoint3)) / totalCredits;


            sgpaTextView.setText("SGPA: " + sgpa);
        }
    }

    private double getGradePoint(String grade) {
        switch (grade) {
            case "O":
                return 10.0;
            case "A+":
                return 9.0;
            case "A":
                return 8.0;
            case "B+":
                return 7.0;
            case "B":
                return 6.0;
            case "C":
                return 5.0;
            default:
                return 0.0;
        }
    }
}