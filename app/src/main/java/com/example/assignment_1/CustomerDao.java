package com.example.assignment_1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CustomerDao {

    @Insert
    long insert(Customer tbl_customer);

    @Update
    void update(Customer tbl_customer);

    @Delete
    void delete(Customer tbl_customer);

    @Query("SELECT * FROM tbl_customer WHERE cid = :x")
    Customer getCustomerById(int x);

    @Query("SELECT * FROM tbl_customer")
    List<Customer> getAll();

    @Query("SELECT * FROM tbl_customer WHERE status = 1")
    Customer getLogin();

    @Query("SELECT * FROM tbl_customer WHERE name = :name AND password = :password")
    Customer getCustomerByNameAndPassword(String name, String password);

    @Query("SELECT * FROM tbl_customer WHERE name = :name")
    Customer getCustomerByName(String name);

}
