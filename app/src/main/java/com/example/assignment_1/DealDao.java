package com.example.assignment_1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DealDao {

    @Insert
    void insert(Deal deal);

    @Query("SELECT * FROM tbl_deal")
    List<Deal> getAllDeals();

    @Delete
    void delete(Deal deal);

    @Update
    void update(Deal deal);

    @Query("SELECT * FROM tbl_deal WHERE productId = :productId")
    List<Deal> getDealsByProductId(int productId);

    @Query("SELECT * FROM tbl_deal WHERE customerId = :customerId")
    List<Deal> getDealsByCustomerId(int customerId);


}
