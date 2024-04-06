package com.example.assignment_1;

public class Deal_List {

    private String name;
    private String category;
    private int image_src;
    private  double star;
    private String date;
    private double price;
    private double discount;

    public Deal_List(String name, String category, int image_src, double star, String date, double price, double discount) {
        this.name = name;
        this.category = category;
        this.image_src = image_src;
        this.star = star;
        this.date = date;
        this.price = price;
        this.discount = discount;
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

    public int getImage_src() {
        return image_src;
    }

    public void setImage_src(int image_src) {
        this.image_src = image_src;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
