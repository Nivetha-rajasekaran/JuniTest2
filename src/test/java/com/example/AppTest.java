package com.example;



import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.entity.Inventory;
import com.example.service.InventoryService;
import com.example.service.InventoryServiceImpl;
import com.example.util.DBConnectionUtil;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
  private static InventoryService insp;

  @BeforeAll
  public static void setUp(){
    insp=new InventoryServiceImpl();
  }

  @Test 
  public void test_Util_File_Exist(){
    File file=new File("src/main/java/com/example/util/DBConnectionUtil.java");
    assertTrue(file.exists(),"It should exist");
  }

   @Test 
  public void test_Util_Folder_Exist(){
    File file=new File("src/main/java/com/example/util");
    assertTrue(file.exists() &&file.isDirectory(),"It should exist");
  }

   @Test 
  public void test_check_Method_Exist(){
    try {
        
        InventoryServiceImpl.class.getDeclaredMethod("addItem", Inventory.class);
          InventoryServiceImpl.class.getDeclaredMethod("deleteItem", int.class);
            InventoryServiceImpl.class.getDeclaredMethod("getAllItems");
            assertTrue(true,"All files should exist");
    } catch (Exception e) {
        fail("One or more files missing");
    }
  }


  @Test
  public void test_Create_Query_Exist() throws Exception{

    Inventory ins = new Inventory();
    ins.setItemName("Ins");
    ins.setCategory("Testcategory");
    ins.setQuantity(10);
    ins.setPrice(23.99);

    String res=insp.addItem(ins);
    assertEquals("Success",res,"Order should be added");

    try {
        
        Connection con=DBConnectionUtil.getConnection();
        PreparedStatement ps=con.prepareStatement("select * from inventory where itemName=?");
        ps.setString(1,"Ins");
        ResultSet rs = ps.executeQuery();
        assertTrue(rs.next(),"Order should exist");
    } catch (Exception e) {
        e.printStackTrace();
    
    }

  }


  @Test
  public void test_Delete_Query_Exist() throws Exception{

    Inventory ins = new Inventory();
    ins.setItemName("Delete");
    ins.setCategory("Deletecategory");
    ins.setQuantity(10);
    ins.setPrice(23.99);
     int id=-1;
    try {
        
        Connection con=DBConnectionUtil.getConnection();
        PreparedStatement ps=con.prepareStatement("delete from inventory where itemName=?");
        ps.setString(1,"Delete");
        ResultSet rs = ps.executeQuery();
       rs.next();
       id=rs.getInt("itemId");
      
    }
    catch(SQLException e){
        e.printStackTrace();
    }

    String res=insp.deleteItem(id);

    try {
         Connection con=DBConnectionUtil.getConnection();
        PreparedStatement ps=con.prepareStatement("select * inventory where itemId=?");
        ps.setInt(1,id);
         ResultSet rs = ps.executeQuery();
        assertFalse(rs.next(),"Order should be deleted");
    } 
     catch(SQLException e){
        e.printStackTrace();
    }
    

  }
  
}
