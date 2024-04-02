package com.example.assignment_1;

public class Option_Item {

    private String optionName;
    private int optionImage;

    public Option_Item(String optionName, int optionImage) {
        this.optionName = optionName;
        this.optionImage = optionImage;
    }

    public String getOptionName() {
        return optionName;
    }

    public int getOptionImage() {
        return optionImage;
    }
}
