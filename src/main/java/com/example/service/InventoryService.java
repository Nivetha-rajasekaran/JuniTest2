package com.example.service;
import java.util.List;

import com.example.entity.Inventory;

public interface InventoryService {
    
    String addItem(Inventory item);
    String deleteItem(int itemId);
    List<Inventory> getAllItems();
}
