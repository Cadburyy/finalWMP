package com.example.finalwmp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EnrollmentMenuActivity extends AppCompatActivity {

    ArrayList<String> selectedSubjects = new ArrayList<>();
    int totalCredits = 0;

    CheckBox subject1, subject2, subject3, subject4, subject5, subject6, subject7, subject8, subject9, subject10;
    TextView creditCount;
    Button submitButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_menu);

        subject1 = findViewById(R.id.subject1);
        subject2 = findViewById(R.id.subject2);
        subject3 = findViewById(R.id.subject3);
        subject4 = findViewById(R.id.subject4);
        subject5 = findViewById(R.id.subject5);
        subject6 = findViewById(R.id.subject6);
        subject7 = findViewById(R.id.subject7);
        subject8 = findViewById(R.id.subject8);
        subject9 = findViewById(R.id.subject9);
        subject10 = findViewById(R.id.subject10);
        creditCount = findViewById(R.id.creditCount);
        submitButton = findViewById(R.id.submitButton);
        cancelButton = findViewById(R.id.cancelButton);

        submitButton.setOnClickListener(v -> handleSubmit());

        cancelButton.setOnClickListener(v -> finish());
    }

    private void handleSubmit() {
        selectedSubjects.clear();
        totalCredits = 0;

        if (subject1.isChecked()) {
            selectedSubjects.add(subject1.getText().toString());
            totalCredits += 3;
        }
        if (subject2.isChecked()) {
            selectedSubjects.add(subject2.getText().toString());
            totalCredits += 3;
        }
        if (subject3.isChecked()) {
            selectedSubjects.add(subject3.getText().toString());
            totalCredits += 3;
        }
        if (subject4.isChecked()) {
            selectedSubjects.add(subject4.getText().toString());
            totalCredits += 3;
        }
        if (subject5.isChecked()) {
            selectedSubjects.add(subject5.getText().toString());
            totalCredits += 3;
        }
        if (subject6.isChecked()) {
            selectedSubjects.add(subject6.getText().toString());
            totalCredits += 3;
        }
        if (subject7.isChecked()) {
            selectedSubjects.add(subject7.getText().toString());
            totalCredits += 3;
        }
        if (subject8.isChecked()) {
            selectedSubjects.add(subject8.getText().toString());
            totalCredits += 3;
        }
        if (subject9.isChecked()) {
            selectedSubjects.add(subject9.getText().toString());
            totalCredits += 3;
        }
        if (subject10.isChecked()) {
            selectedSubjects.add(subject10.getText().toString());
            totalCredits += 3;
        }

        creditCount.setText("Total Credits: " + totalCredits);

        if (totalCredits > 24) {
            Toast.makeText(this, "You cannot exceed 24 credits.", Toast.LENGTH_SHORT).show();
        } else {
            Intent resultIntent = new Intent();
            resultIntent.putStringArrayListExtra("selectedSubjects", selectedSubjects);
            resultIntent.putExtra("totalCredits", totalCredits);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}
