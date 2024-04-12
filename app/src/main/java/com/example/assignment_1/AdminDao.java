package com.example.assignment_1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface AdminDao {

    @Insert
    void insert(Admin admin);

    @Update
    void update(Admin admin);

    @Delete
    void delete(Admin admin);

    @Query("SELECT * FROM admin_table")
    List<Admin> getAllAdmins();

    @Query("SELECT * FROM admin_table WHERE aid = :id")
    Admin getAdmin(int id);

    @Query("Select * FROM admin_table WHERE username = :username")
    Admin getAdminByUsername(String username);

    @Query("Select * FROM admin_table WHERE password = :password")
    Admin getAdminByPassword(String password);

    @Query("Select * FROM admin_table WHERE username = :username AND password = :password")
    Admin getAdminByUsernameAndPassword(String username, String password);

    @Query("SELECT * FROM admin_table WHERE status = 1")
    Admin getLogin();
}
