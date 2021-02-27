package com.shery.foodmenuscreen.constants;


import com.shery.foodmenuscreen.model.MenuCategoryAndItems;
import com.shery.foodmenuscreen.model.MenuItems;

import java.util.ArrayList;

public class Constant {
    public static ArrayList<MenuCategoryAndItems> generateData() {
        ArrayList<MenuCategoryAndItems> list = new ArrayList<>();


        ArrayList<MenuItems> breakfastList = new ArrayList<>();
        breakfastList.add(new MenuItems("Paratha", "100"));
        breakfastList.add(new MenuItems("anda", "This is a test description", "100"));
        breakfastList.add(new MenuItems("cholay", "100"));
        breakfastList.add(new MenuItems("puri", "This is a test description", "100"));


        ArrayList<MenuItems> lunchList = new ArrayList<>();
        lunchList.add(new MenuItems("Qorma", "100"));
        lunchList.add(new MenuItems("Karahae", "This is a test description", "100"));
        lunchList.add(new MenuItems("Dal", "This is a test description", "100"));


        ArrayList<MenuItems> dinnerList = new ArrayList<>();
        dinnerList.add(new MenuItems("Sabzi", "This is a test description", "100"));
        dinnerList.add(new MenuItems("BBQ", "100"));
        dinnerList.add(new MenuItems("Fast Food", "This is a test description", "100"));

        ArrayList<MenuItems> snacksList = new ArrayList<>();
        snacksList.add(new MenuItems("Lays", "This is a test description", "100"));
        snacksList.add(new MenuItems("Oye Hoye", "100"));
        snacksList.add(new MenuItems("Pati fry", "This is a test description", "100"));
        snacksList.add(new MenuItems("Top pops", "100"));
        snacksList.add(new MenuItems("Kurkuray", "This is a test description", "100"));
        snacksList.add(new MenuItems("Zee chips", "100"));
        snacksList.add(new MenuItems("Noni POP chips", "30"));
        snacksList.add(new MenuItems("Top POP chips", "30"));


        ArrayList<MenuItems> beveragesList = new ArrayList<>();
        beveragesList.add(new MenuItems("Pepsi", "100"));
        beveragesList.add(new MenuItems("CocaCola", "This is a test description", "100"));
        beveragesList.add(new MenuItems("Funta", "100"));
        beveragesList.add(new MenuItems("Slice", "This is a test description", "100"));
        beveragesList.add(new MenuItems("Sting", "This is a test description", "100"));
        beveragesList.add(new MenuItems("Marinda", "100"));
        beveragesList.add(new MenuItems("Sprite", "This is a test description", "100"));
        beveragesList.add(new MenuItems("7up", "This is a test description", "100"));


        ArrayList<MenuItems> desertList = new ArrayList<>();
        desertList.add(new MenuItems("Kheer", "100"));
        desertList.add(new MenuItems("Custard", "This is a test description", "100"));
        desertList.add(new MenuItems("Lab-e-Sheeren", "This is a test description", "100"));
        desertList.add(new MenuItems("Sohan Halwa", "This is a test description", "100"));
        desertList.add(new MenuItems("Habshi Halwa", "100"));
        desertList.add(new MenuItems("Burfi", "This is a test description", "100"));
        desertList.add(new MenuItems("Gulab Jaman", "This is a test description", "100"));
        desertList.add(new MenuItems("Rasgula", "This is a test description", "100"));
        desertList.add(new MenuItems("Cham Cham", "100"));


        list.add(new MenuCategoryAndItems("BreakFast", breakfastList));
        list.add(new MenuCategoryAndItems("Lunch", lunchList));
        list.add(new MenuCategoryAndItems("Dinner", dinnerList));
        list.add(new MenuCategoryAndItems("Desert", desertList));
        list.add(new MenuCategoryAndItems("Beverages", beveragesList));
        list.add(new MenuCategoryAndItems("Snacks", snacksList));

        return list;
    }
}
