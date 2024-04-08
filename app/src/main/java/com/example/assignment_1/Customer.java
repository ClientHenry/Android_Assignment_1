package com.example.assignment_1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_customer")
public class Customer {
    @PrimaryKey(autoGenerate = true)
    public int cid;

    public String name;
    public String password;
    public String address;
    public String phoneNum;

    public Customer(String name, String password, String address, String phoneNum) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.phoneNum = phoneNum;
    }


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
