package com.example.finalwmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView profileName, profileEmail, subjectSummary, creditSummary;
    TextView titleName, titleUsername;
    Button enrollSubjects;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firestore = FirebaseFirestore.getInstance();

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        titleName = findViewById(R.id.titleName);
        titleUsername = findViewById(R.id.titleUsername);
        subjectSummary = findViewById(R.id.subjectSummary);
        creditSummary = findViewById(R.id.creditSummary);
        enrollSubjects = findViewById(R.id.enrollButton);

        showAllUserData();

        enrollSubjects.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EnrollmentMenuActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    public void showAllUserData() {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        firestore.collection("users")
                .document(username)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String name = document.getString("name");
                            String email = document.getString("email");

                            titleName.setText(name != null ? name : "Name not found");
                            titleUsername.setText(username != null ? username : "Username not found");
                            profileName.setText(name != null ? name : "Name not found");
                            profileEmail.setText(email != null ? email : "Email not found");
                        }
                    } else {
                        profileName.setText("Error loading data");
                        profileEmail.setText("Error loading data");
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            ArrayList<String> selectedSubjects = data.getStringArrayListExtra("selectedSubjects");
            int totalCredits = data.getIntExtra("totalCredits", 0);

            if (selectedSubjects != null && !selectedSubjects.isEmpty()) {
                StringBuilder summary = new StringBuilder("Enrolled Subjects:\n");
                for (String subject : selectedSubjects) {
                    summary.append("- ").append(subject).append("\n");
                }
                subjectSummary.setText(summary.toString());
            } else {
                subjectSummary.setText("No subjects selected.");
            }

            creditSummary.setText("Total Credits: " + totalCredits + " / 24");
        }
    }
}
