package com.example.assignment_1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "admin_table")
public class Admin {

    @PrimaryKey(autoGenerate = true)
    private int aid;

    private String username;
    private String password;

    private int status;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
