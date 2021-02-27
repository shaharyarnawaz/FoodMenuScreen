package com.shery.foodmenuscreen.model;

public class MenuItems {
    private String name;
    private String description;
    private String price;

    public MenuItems(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public MenuItems(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
