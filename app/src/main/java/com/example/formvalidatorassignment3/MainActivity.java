package com.example.formvalidatorassignment3;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText handle, email, password;
    Button registerButton;

    // Regex for Handle (only letters, numbers, and underscores allowed)
    private static final Pattern HANDLE_PATTERN = Pattern.compile("^[a-zA-Z0-9_]+$");

    // Regex for Password (at least one digit, one uppercase letter, and one lowercase letter, 6-20 characters)
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Components Initialize
        handle = findViewById(R.id.handle);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);

        // Register Button Click Listener
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    Toast.makeText(MainActivity.this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Validate Form Inputs
    private boolean validateForm() {
        String handleInput = handle.getText().toString().trim();
        String emailInput = email.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();

        // Validate Handle
        if (TextUtils.isEmpty(handleInput)) {
            handle.setError("Handle is required");
            return false;
        } else if (!HANDLE_PATTERN.matcher(handleInput).matches()) {
            handle.setError("Handle can only contain letters, numbers, and underscores");
            return false;
        }

        // Validate Email using Android's built-in email pattern
        if (TextUtils.isEmpty(emailInput)) {
            email.setError("Email is required");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Invalid email format");
            return false;
        }

        // Validate Password (6-20 characters, with at least one digit, uppercase, and lowercase)
        if (TextUtils.isEmpty(passwordInput)) {
            password.setError("Password is required");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password must contain at least one digit, one uppercase, one lowercase letter, and be 6-20 characters long");
            return false;
        }

        return true;
    }
}
