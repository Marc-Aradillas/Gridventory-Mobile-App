package com.zybooks.marc_aradillas_inventory_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * LoginActivity:
 * - Handles user authentication and account creation.
 * - Provides input fields for username and password.
 * - Validates credentials against the database.
 * - Allows new users to create an account.
 */
public class LoginActivity extends AppCompatActivity {

    // Database helper for user authentication and account management
    private DatabaseHelper dbHelper;

    // UI components for user input and actions
    private EditText usernameEditText, passwordEditText;

    /**
     * Called when the activity is first created.
     * Sets up the UI components and event listeners.
     *
     * @param savedInstanceState Saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Link UI components with layout elements
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        Button createAccountButton = findViewById(R.id.createAccountButton);

        // Set up login button click listener
        loginButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Validate credentials against the database
            if (dbHelper.checkUser(username, password)) {
                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                // Navigate to the inventory dashboard (MainActivity)
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the login activity
            } else {
                // Show error message for invalid credentials
                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // Set up create account button click listener
        createAccountButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Validate input fields
            if (!username.isEmpty() && !password.isEmpty()) {
                // Add the new user to the database
                if (dbHelper.addUser(username, password)) {
                    Toast.makeText(LoginActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Show error message if account creation fails (e.g., duplicate username)
                    Toast.makeText(LoginActivity.this, "Account Creation Failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Show error message for empty fields
                Toast.makeText(LoginActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }
}