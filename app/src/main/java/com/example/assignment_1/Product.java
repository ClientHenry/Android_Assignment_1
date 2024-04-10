package com.example.assignment_1;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "product_table",
        foreignKeys = @ForeignKey(entity = Supplier.class,
                parentColumns = "sid", childColumns = "supplier_id",
                onDelete = ForeignKey.NO_ACTION),
                indices = {@Index("supplier_id")})
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int pid;
    private String name;
    private String category;
    private int price;
    private int discount;
    private String description;
    private String Date;
    private String mark;
    private int image_1;
    private int image_2;
    private int image_3;
    private int image_4;

    private int supplier_id;


    public Product(String name, String category, int price, int discount,
                   String description, int image_1, int image_2,
                   int image_3, int image_4, int supplier_id, String Date, String mark) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.image_1 = image_1;
        this.image_2 = image_2;
        this.image_3 = image_3;
        this.image_4 = image_4;
        this.supplier_id = supplier_id;
        this.Date = Date;
        this.mark = mark;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage_1() {
        return image_1;
    }

    public void setImage_1(int image_1) {
        this.image_1 = image_1;
    }

    public int getImage_2() {
        return image_2;
    }

    public void setImage_2(int image_2) {
        this.image_2 = image_2;
    }

    public int getImage_3() {
        return image_3;
    }

    public void setImage_3(int image_3) {
        this.image_3 = image_3;
    }

    public int getImage_4() {
        return image_4;
    }

    public void setImage_4(int image_4) {
        this.image_4 = image_4;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
