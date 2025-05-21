package com.example.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.Inventory;
import com.example.util.DBConnectionUtil;


public class InventoryServiceImpl implements InventoryService {

    @Override
    
     public String addItem(Inventory item){
        
        String sql="insert into inventory (itemName,category,quantity,price)values(?,?,?,?);";
        try{

            Connection con = DBConnectionUtil.getConnection();
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1,item.getItemName());
            ps.setString(2,item.getCategory());
            ps.setInt(3,item.getQuantity());
            ps.setDouble(4,item.getPrice());
            int r = ps.executeUpdate();

            return r>0?"Success":"Failure";

            
        }
        catch(Exception e){
            return e.getMessage();
        }

    }

     @Override
    
     public String deleteItem(int itemId){
        
        String sql="delete from inventory where itemId=?;";

        try{

            Connection con = DBConnectionUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,itemId);
       
            int r = ps.executeUpdate();

            return r>0?"Success":"Failure";

            
        }
        catch(Exception e){
            return e.getMessage();
        }
    
    }

    @Override 
      
    public List<Inventory> getAllItems(){
       
        List<Inventory> items = new ArrayList<>();

        String sql="select * from inventory;";
        
        try {
             Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps=con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();

             while(rs.next()){
              
                Inventory item= new Inventory();
                 item.setItemId(rs.getInt("itemId"));
                 item.setItemName(rs.getString("itemName"));
                 item.setCategory(rs.getString("category"));
                 item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                items.add(item);
             }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;

    }
    
}
