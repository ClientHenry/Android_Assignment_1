package com.example.assignment_1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class Deal_Detail {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String category;
    public double star;
    public String Date;
    public double price;
    public double discount;
    public int image_rsc;



}
