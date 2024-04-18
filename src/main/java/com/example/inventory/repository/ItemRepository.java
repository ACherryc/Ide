package com.example.inventory.repository;

import java.util.ArrayList;
import com.example.inventory.model.Item;

public interface  ItemRepository {
    ArrayList<Item> getItems();

    Item getItemId(int itemId);

    Item addItem(Item item);

    Item updateItem(int itemId, Item item);

    void deleteItem(int itemId);
}
