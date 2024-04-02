package com.example.assignment_1;

public class Deal_List {

    private String name;
    private String category;
    private int image_src;

    public Deal_List(String name, String category, int image_src) {
        this.name = name;
        this.category = category;
        this.image_src = image_src;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
