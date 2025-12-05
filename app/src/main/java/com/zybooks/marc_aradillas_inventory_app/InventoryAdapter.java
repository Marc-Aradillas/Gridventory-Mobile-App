package com.zybooks.marc_aradillas_inventory_app;

import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * InventoryAdapter:
 * - RecyclerView Adapter for displaying, editing, and deleting inventory items.
 * - Handles item display, deletion, and editing using a custom ViewHolder.
 */
public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

    private final List<Item> inventoryList; // List of inventory items
    private final OnDeleteClickListener deleteClickListener; // Interface for delete button clicks
    private final Context context; // Context for building dialogs
    private final DatabaseHelper dbHelper; // Database helper for update operations

    /**
     * Interface for handling delete button clicks.
     */
    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    /**
     * Constructor for InventoryAdapter.
     *
     * @param context            Application context for dialog building.
     * @param inventoryList      List of inventory items to display.
     * @param deleteClickListener Callback interface for handling delete actions.
     */
    public InventoryAdapter(Context context, List<Item> inventoryList, OnDeleteClickListener deleteClickListener) {
        this.context = context;
        this.inventoryList = inventoryList;
        this.deleteClickListener = deleteClickListener;
        this.dbHelper = new DatabaseHelper(context); // Initialize database helper
    }

    /**
     * Inflates the item layout and creates the ViewHolder.
     */
    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false); // Inflate row layout
        return new InventoryViewHolder(view);
    }

    /**
     * Binds data to the ViewHolder for the current position.
     */
    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        Item item = inventoryList.get(position);

        // Set item name and quantity to the TextViews
        holder.itemNameTextView.setText(item.getName());
        holder.itemQuantityTextView.setText(String.valueOf(item.getQuantity()));

        // Set delete button click listener
        holder.deleteButton.setOnClickListener(v -> deleteClickListener.onDeleteClick(position));

        // Set edit button click listener to show an edit dialog
        holder.editButton.setOnClickListener(v -> showEditDialog(item, position));
    }

    /**
     * Displays a dialog to edit an inventory item's name and quantity.
     *
     * @param item     The inventory item being edited.
     * @param position The position of the item in the RecyclerView.
     */
    private void showEditDialog(Item item, int position) {
        // Create an AlertDialog for editing
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Item");

        // Layout for input fields (vertical orientation)
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Input field for item name
        final EditText itemNameInput = new EditText(context);
        itemNameInput.setHint("Item Name");
        itemNameInput.setText(item.getName());
        layout.addView(itemNameInput);

        // Input field for item quantity
        final EditText itemQuantityInput = new EditText(context);
        itemQuantityInput.setHint("Quantity");
        itemQuantityInput.setText(String.valueOf(item.getQuantity()));
        itemQuantityInput.setInputType(InputType.TYPE_CLASS_NUMBER); // Set input type to number
        layout.addView(itemQuantityInput);

        builder.setView(layout); // Set layout to the dialog

        // Positive button: Update the item
        builder.setPositiveButton("Update", (dialog, which) -> {
            String updatedName = itemNameInput.getText().toString().trim();
            String updatedQuantityString = itemQuantityInput.getText().toString().trim();

            // Validate inputs
            if (!updatedName.isEmpty() && !updatedQuantityString.isEmpty()) {
                try {
                    int updatedQuantity = Integer.parseInt(updatedQuantityString);

                    // Update the item in the database
                    dbHelper.updateItem(item.getId(), updatedName, updatedQuantity);

                    // Update the list and notify the adapter
                    inventoryList.set(position, new Item(item.getId(), updatedName, updatedQuantity));
                    notifyItemChanged(position);
                } catch (NumberFormatException e) {
                    itemQuantityInput.setError("Invalid quantity");
                }
            }
        });

        // Negative button: Cancel the dialog
        builder.setNegativeButton("Cancel", null);
        builder.show(); // Display the dialog
    }

    /**
     * Returns the total number of items in the inventory list.
     */
    @Override
    public int getItemCount() {
        return inventoryList.size();
    }

    /**
     * ViewHolder class for holding views for each inventory item.
     */
    static class InventoryViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView;     // Displays the item name
        TextView itemQuantityTextView; // Displays the item quantity
        ImageButton deleteButton;      // Button to delete the item
        Button editButton;             // Button to edit the item

        /**
         * Constructor for initializing views in the ViewHolder.
         *
         * @param itemView The layout view for a single item.
         */
        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemName);
            itemQuantityTextView = itemView.findViewById(R.id.itemQuantity);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            editButton = itemView.findViewById(R.id.editButton);
        }
    }
}