package com.zybooks.marc_aradillas_inventory_app;

/**
 * Item:
 * - A simple model class representing an inventory item.
 * - Contains an ID, a name, and a quantity for the item.
 * - Provides getter methods to access the item details.
 */
public class Item {

    // Unique identifier for the item
    private final int id;

    // Name of the item
    private final String name;

    // Quantity of the item
    private final int quantity;

    /**
     * Constructor for Item.
     *
     * @param id       Unique identifier for the item.
     * @param name     Name of the item.
     * @param quantity Quantity of the item.
     */
    public Item(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * Gets the unique ID of the item.
     *
     * @return The item's unique ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the item.
     *
     * @return The item's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the quantity of the item.
     *
     * @return The item's quantity.
     */
    public int getQuantity() {
        return quantity;
    }
}