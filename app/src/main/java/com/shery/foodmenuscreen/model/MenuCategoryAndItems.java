package com.shery.foodmenuscreen.model;

import java.util.ArrayList;

public class MenuCategoryAndItems {
    private String categoryName;
    private boolean isClicked;
    private ArrayList<MenuItems> menuItemsLists;

    public MenuCategoryAndItems(String categoryName, ArrayList<MenuItems> menuItemsLists) {
        this.categoryName = categoryName;
        this.menuItemsLists = menuItemsLists;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<MenuItems> getMenuItemsLists() {
        return menuItemsLists;
    }

    public void setMenuItemsLists(ArrayList<MenuItems> menuItemsLists) {
        this.menuItemsLists = menuItemsLists;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
