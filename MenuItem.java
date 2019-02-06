// *********************

// David Gary
// COSC 436 - Object Oriented Programming
// Assignment 4 - Menu Iterators

// *********************


import java.util.*;

public class MenuItem {
    protected String name;
    protected int category;
    protected boolean heartHealthy;
    protected String price;

    public MenuItem() {
        name = "";
        category = 0;
        heartHealthy = false;
        price = "";
    }
    public MenuItem(String n, int c, boolean h, String p) {
        name = n;
        category = c;
        heartHealthy = h;
        price = p;
    }
    public String getName() {
        return name;
    }
    public int getCategory() {
        return category;
    }
    public boolean getHeartHealthy() {
        return heartHealthy;
    }
    public String getPrice() {
        return price;
    }

}
