package com.example.assignment_1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SupplierDao {

    @Insert
    long insert(Supplier tbl_supplier);

    @Update
    void update(Supplier tbl_supplier);

    @Delete
    void delete(Supplier tbl_supplier);

    @Query("SELECT * FROM tbl_supplier WHERE sid = :x")
    Supplier getSupplierById(int x);

    @Query("SELECT * FROM tbl_supplier")
    List<Supplier> getAll();

    @Query("SELECT * FROM tbl_supplier WHERE name = :name AND password = :password")
    Supplier getSupplierByNameAndPassword(String name, String password);

    @Query("SELECT * FROM tbl_supplier WHERE status = 1")
    Supplier getLogin();

    @Query("SELECT * FROM tbl_supplier WHERE name = :name")
    Supplier getSupplierByName(String name);
}
