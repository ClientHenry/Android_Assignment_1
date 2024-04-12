package com.example.assignment_1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Random;

@Entity(tableName = "tbl_customer")
public class Customer {
    @PrimaryKey(autoGenerate = true)
    public int cid;

    public String name;
    public String password;
    public String address;
    public String phoneNum;

    public int  status;

    public String idNumber;

    public Customer(String name, String password, String address, String phoneNum) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.phoneNum = phoneNum;
        this.status = 0;
        this.idNumber = createID();
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


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
