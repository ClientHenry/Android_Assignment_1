package com.example.assignment_1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);
    
    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("SELECT * FROM product_table")
    List<Product> getAllProducts();

    @Query("SELECT * FROM product_table WHERE pid = :id")
    Product getProduct(int id);

    @Query("Select * FROM product_table WHERE category = :category")
    List<Product> getProductsByCategory(String category);

    @Query("Select * FROM product_table WHERE supplier_id = :supplier_id")
    List<Product> getProductsBySupplier(int supplier_id);

}
