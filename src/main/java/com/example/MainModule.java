package com.example;
import java.util.List;
import java.util.Scanner;

import com.example.entity.Inventory;
import com.example.service.InventoryService;
import com.example.service.InventoryServiceImpl;



public class MainModule {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryService ins = new InventoryServiceImpl();

        while(true){
            System.out.println("\1. Add Item\n2. Delete Item\n3.View all items\n4.Exit");
            System.out.println("Enter a choice");
            int n =sc.nextInt();
            sc.nextLine();

            switch(n){
             
                 case 1:
                   Inventory inv = new Inventory();
                   
                   System.out.println("Enter name");
                   String itemName=sc.nextLine();

                     System.out.println("Enter category");
                   String category=sc.nextLine();

                     System.out.println("Enter quantity");
                     int quantity=sc.nextInt();
                    //  sc.nextLine();
                     System.out.println("Enter price");
                     double price = sc.nextDouble();

                     inv.setItemName(itemName);
                     inv.setCategory(category);
                     inv.setQuantity(quantity);
                     inv.setPrice(price);
                     System.out.println(ins.addItem(inv));
                     break;
            case 2:

             System.out.println("Enter Id");
             int itemId=sc.nextInt();
             System.out.println(ins.deleteItem(itemId));
             break;

             case 3:
              List<Inventory> items = ins.getAllItems();
              if(items.isEmpty()){
                System.out.println("No items");
              }

              else{
                for(Inventory i:items){
                    System.out.println(i.getItemId());
                     System.out.println(i.getItemName());
                      System.out.println(i.getCategory());
                       System.out.println(i.getQuantity());
                        System.out.println(i.getPrice());
                }
              }
              break;
              case 4:
              System.out.println("Exiting....");
              System.exit(0);

            }
           
              
        }
    }
}
