package com.example.sharedwhiteboardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {
    private EditText joinCodeEditText;
    private static final String VALID_JOIN_CODE = "1234";  // Example join code, replace with desired logic

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        joinCodeEditText = findViewById(R.id.joinCodeEditText);
        Button joinButton = findViewById(R.id.joinButton);

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredCode = joinCodeEditText.getText().toString().trim();
                if (enteredCode.equals(VALID_JOIN_CODE)) {
                    Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(JoinActivity.this, "Invalid join code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
