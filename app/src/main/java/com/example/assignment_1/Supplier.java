package com.example.assignment_1;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Random;

@Entity(tableName = "tbl_supplier")
public class Supplier {

    @PrimaryKey(autoGenerate = true)
    public int sid;

    public String name;

    public String password;
    public String address;
    public String phoneNum;

    public String idNumber;

    public int  status;

    public Supplier(String name, String password, String address, String phoneNum) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.phoneNum = phoneNum;
        this.idNumber = createID();
        this.status = 0;
    }


    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String createID() {

        Random random = new Random();

        ArrayList<Character> alphabet = new ArrayList<>();
        String id = "";

        for(char ch = 'A'; ch <= 'Z'; ch++) {

            alphabet.add(ch);
        }

        ArrayList <Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 10; i++) {

            numbers.add(i);
        }

        for(int i = 1; i <=3; i++) {

            int randomIndex = random.nextInt(26);
            char randomCharacter = alphabet.get(randomIndex);
            id += randomCharacter;

            randomIndex = random.nextInt(10);
            int randomNum = numbers.get(randomIndex);
            id += randomNum;
        }

        return id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
