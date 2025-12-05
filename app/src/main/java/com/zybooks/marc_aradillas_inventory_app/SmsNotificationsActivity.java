package com.zybooks.marc_aradillas_inventory_app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * SmsNotificationsActivity:
 * - Manages SMS notification settings for the application.
 * - Handles SMS permission requests.
 * - Provides a toggle switch to enable/disable SMS notifications.
 * - Allows users to navigate back to the main inventory dashboard.
 */
public class SmsNotificationsActivity extends AppCompatActivity {

    private static final int SMS_PERMISSION_REQUEST_CODE = 101; // Unique code for SMS permission
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch smsToggle; // Toggle switch for enabling/disabling SMS notifications

    /**
     * Called when the activity is first created.
     * Sets up the UI components and event listeners.
     *
     * @param savedInstanceState Saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_notifications);

        // Initialize Back to Dashboard button
        Button backToDashboardButton = findViewById(R.id.backToDashboardButton);
        backToDashboardButton.setOnClickListener(v -> {
            finish(); // Close this activity and return to the previous one
        });

        // Initialize UI components
        smsToggle = findViewById(R.id.smsToggle);
        Button requestPermissionButton = findViewById(R.id.requestSmsPermissionButton);

        // Check current SMS permission status and update the toggle
        updateSmsToggleState();

        // Handle request permission button click
        requestPermissionButton.setOnClickListener(v -> requestSmsPermission());

        // Handle changes to the SMS toggle switch
        smsToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (hasSmsPermission()) {
                    // If permission is already granted, enable notifications
                    Toast.makeText(this, "SMS Notifications Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    // If permission is not granted, request it
                    Toast.makeText(this, "SMS Permission Required!", Toast.LENGTH_SHORT).show();
                    smsToggle.setChecked(false); // Reset switch if permission isn't granted
                }
            } else {
                // SMS notifications disabled
                Toast.makeText(this, "SMS Notifications Disabled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Requests SMS permission if it has not been granted already.
     */
    private void requestSmsPermission() {
        if (!hasSmsPermission()) {
            // Request SMS permission using Android runtime permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_REQUEST_CODE);
        } else {
            // Notify user if permission is already granted
            Toast.makeText(this, "SMS Permission Already Granted", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Checks if the SMS permission has been granted.
     *
     * @return True if permission is granted, false otherwise.
     */
    private boolean hasSmsPermission() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Updates the toggle switch state based on current SMS permission status.
     */
    private void updateSmsToggleState() {
        smsToggle.setChecked(hasSmsPermission());
    }

    /**
     * Handles the result of the SMS permission request.
     *
     * @param requestCode  The request code used when requesting permission.
     * @param permissions  The requested permissions.
     * @param grantResults The results for the requested permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Check if the result corresponds to the SMS permission request
        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                Toast.makeText(this, "SMS Permission Granted", Toast.LENGTH_SHORT).show();
                smsToggle.setChecked(true); // Update the toggle state
            } else {
                // Permission denied
                Toast.makeText(this, "SMS Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}