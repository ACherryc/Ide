package com.example.inventory.service;

import com.example.inventory.model.Item;
inport com.example.inventory.repository.ItemJpaRepository;
import com.example.inventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;


@Service
public class ItemJpaService implements ItemRepository {
    @Autowired
    public ItemJpaRepository itemJpaRepository;

    @Override
    public ArrayList<Item> getItems(){
        ArrayList<Item> itemList = itemJpaRepository.findAll();
        ArrayList<Item> items = new ArrayList<>(itemList);
        return items;
    }

    @Override
    public Item getItemId(int itemId) {
        try {
            Item item = itemJpaRepository.findById(itemId).get();
            return item;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Item addItem(Item item) {
        itemJpaRepository.save(item);
        return item;
    }

    @Override
    public Item updateItem(int itemId, Item item) {
        try {
            Item newItem = itemJpaRepository.findById(itemId).get();
            if (item.getItemName() != null) {
                newItem.setItemName(item.getItemName());
            }
            if (item.getQuantity() != 0) {
                newItem.setQuantity(item.getQuantity());
            }
            if (item.getSupplier() != null) {
                newItem.setSupplier(item.getSupplier());
            }
            itemJpaRepository.save(newItem);
            return newItem;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public void deleteItem(int itemId) {
        try {
            itemJpaRepository.deleteById(itemId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
    }
}
