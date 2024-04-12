package com.example.assignment_1;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Random;

@Entity(tableName = "tbl_deal",
        foreignKeys = {
                @ForeignKey(entity = Product.class,
                        parentColumns = "pid",
                        childColumns = "productId",
                        onDelete = ForeignKey.NO_ACTION),
                @ForeignKey(entity = Customer.class,
                        parentColumns = "cid",
                        childColumns = "customerId",
                        onDelete = ForeignKey.NO_ACTION)
        },
        indices= {
        @Index("productId"),
        @Index("customerId")
        }
)

public class Deal {

    @PrimaryKey(autoGenerate = true)
    public int did;

    public int productId;
    public int customerId;

    public String idNumber;


    public Deal(int productId, int customerId) {
        this.productId = productId;
        this.customerId = customerId;
        this.idNumber = createID();
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
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
