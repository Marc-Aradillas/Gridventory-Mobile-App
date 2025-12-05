package com.zybooks.marc_aradillas_inventory_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * MainActivity:
 * - Displays the inventory dashboard.
 * - Allows users to add, view, and delete inventory items.
 * - Provides access to SMS settings and a logout option via the app bar menu.
 */
public class MainActivity extends AppCompatActivity {

    private InventoryAdapter inventoryAdapter; // RecyclerView adapter for inventory list
    private List<Item> inventoryList; // List of items displayed in RecyclerView
    private DatabaseHelper dbHelper; // Database helper for database operations

    private EditText itemNameInput;    // Input field for item name
    private EditText itemQuantityInput; // Input field for item quantity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_dashboard); // Set content layout

        // Set up the app bar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Set up RecyclerView for displaying inventory items
        RecyclerView inventoryRecyclerView = findViewById(R.id.inventoryRecyclerView);
        inventoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load all items from the database into inventoryList
        inventoryList = dbHelper.getAllItems();
        inventoryAdapter = new InventoryAdapter(this, inventoryList, this::deleteItem);
        inventoryRecyclerView.setAdapter(inventoryAdapter);

        // Initialize input fields and "Add Item" button
        itemNameInput = findViewById(R.id.itemNameInput);
        itemQuantityInput = findViewById(R.id.itemQuantityInput);
        Button addButton = findViewById(R.id.addButton);

        // Set click listener for Add Button
        addButton.setOnClickListener(v -> addItem());
    }

    /**
     * Adds a new item to the database and updates the RecyclerView.
     */
    @SuppressLint("NotifyDataSetChanged")
    private void addItem() {
        String itemName = itemNameInput.getText().toString().trim(); // Get trimmed item name
        String itemQuantityString = itemQuantityInput.getText().toString().trim(); // Get trimmed quantity

        // Validate input fields
        if (itemName.isEmpty()) {
            itemNameInput.setError("Item name is required");
            return;
        }

        if (itemQuantityString.isEmpty()) {
            itemQuantityInput.setError("Quantity is required");
            return;
        }

        try {
            int itemQuantity = Integer.parseInt(itemQuantityString); // Parse quantity to integer

            // Add item to database
            dbHelper.addItem(itemName, itemQuantity);

            // Refresh RecyclerView with updated inventory list
            inventoryList.clear();
            inventoryList.addAll(dbHelper.getAllItems());
            inventoryAdapter.notifyDataSetChanged();

            // Clear input fields
            itemNameInput.setText("");
            itemQuantityInput.setText("");

            Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            itemQuantityInput.setError("Invalid quantity");
        }
    }

    /**
     * Deletes an item from the database and updates RecyclerView.
     *
     * @param position Position of the item to be deleted.
     */
    private void deleteItem(int position) {
        if (position >= 0 && position < inventoryList.size()) {
            // Delete item from database using its ID
            dbHelper.deleteItem(inventoryList.get(position).getId());

            // Remove item from list and update RecyclerView
            inventoryList.remove(position);
            inventoryAdapter.notifyItemRemoved(position);
        } else {
            Toast.makeText(this, "Error: Invalid item index", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Inflates the options menu into the app bar.
     *
     * @param menu The options menu in which items are placed.
     * @return true to display the menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inventory_dashboard, menu); // Inflate menu resource
        return true;
    }

    /**
     * Handles menu item clicks for SMS settings and logout.
     *
     * @param item The selected menu item.
     * @return true if the menu item is handled successfully.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_sms_settings) {
            // Navigate to SMS Notifications Settings Activity
            startActivity(new Intent(this, SmsNotificationsActivity.class));
            return true;
        } else if (item.getItemId() == R.id.menu_logout) {
            // Navigate back to LoginActivity and clear back stack
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish(); // Finish current activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}