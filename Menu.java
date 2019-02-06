// *********************

// David Gary
// COSC 436 - Object Oriented Programming
// Assignment 4 - Menu Iterators

// *********************

import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Menu {
    private MenuItem[] menu;
    private int menu_size;
    // CONSTANTS
    public static final int APPETIZERS = 1;
    public static final int MAIN_DISH = 2;
    public static final int DESSERT = 3;
    public static final boolean HEART_HEALTHY = true;
    public static final boolean NOT_HEART_HEALTHY = false;

    //Menu default constructor
    public Menu() {
        menu = new MenuItem[20];
        menu_size = 0;
    }
    public void add(String g, int n, boolean b, String h) {
        MenuItem u = new MenuItem(g, n, b, h);
        menu[menu_size++] = u;
    }

    public interface MenuIterator {
        // returns true is items of appropriate type left in list
        public boolean hasNext();
        // returns current item and advances to next item
        public MenuItem next();
    }

    // Getter methods for the iterators
    public MenuIterator getAllItemsIterator() {
        return new AllItemsIterator(menu[0]);
    }

    public MenuIterator getItemIterator(int itemClassification) {
        return new ItemIterator(itemClassification);
    }

    public MenuIterator getHeartHealthyIterator(boolean itemHealth) {
        return new HeartHealthyIterator(itemHealth);
    }

    public MenuIterator getPriceIterator(String itemPrice) {
        return new PriceIterator(itemPrice);
    }
    // Different types of iterators implementing the iterator interface
    private class AllItemsIterator implements MenuIterator {
        private int index;
        public AllItemsIterator(MenuItem a) {
            index = 0;
        }
        public boolean hasNext() {
            return menu.length != index;
        }
        public MenuItem next() {
            if(hasNext()) {
                return menu[index++];
            } else {
                throw new NoSuchElementException();
            }
        }
    }
    private class ItemIterator implements MenuIterator {
        private MenuItem item;
        private int index;
        private int typeOfFood;

        public ItemIterator(int type) {
            index = 0;
            typeOfFood = type;
        }
        public boolean hasNext() {
            return menu.length != index;
        }
        public MenuItem next() {
            if (hasNext()) {
                item = menu[index++];
                // The item typepassed as a parameter and the
                // menu items type are compared to see
                // if they're what the user is looking for
                if (typeOfFood == item.getCategory()) {
                    return item;
                } else {
                    return null;
                }
            } else {
                throw new NoSuchElementException();
            }
        }
    }
    private class HeartHealthyIterator implements MenuIterator {
        private MenuItem item;
        private int index;
        private boolean healthy;

        public HeartHealthyIterator(boolean itemHealth) {
            index = 0;
            healthy = itemHealth;
        }
        public boolean hasNext() {
            return menu.length != index;
        }
        public MenuItem next() {
            if (hasNext()) {
                item = menu[index];
                if (healthy == item.getHeartHealthy()) {
                    return item;
                } else {
                    return null;
                }
            } else {
                throw new NoSuchElementException();
            }
        }
    }
    private class PriceIterator implements MenuIterator {
        private MenuItem item;
        private int index;
        private String price;

        public PriceIterator(String itemPrice) {
            index = 0;
            price = itemPrice;
        }
        public boolean hasNext() {
            return menu.length != index;
        }
        public MenuItem next() {
            if (hasNext()) {
                // creating a new spot for the MenuItem
                item = menu[index++];
                // changes the strings into doubles so the comparison in the iterator
                // is easier to differentiate if the MenuItem price is less than the proposed
                // price
                double upperPrice = Double.parseDouble(price);
                double itemPrice = Double.parseDouble(item.getPrice());
                if (upperPrice > itemPrice)
                    return item;
                else
                    return null;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    public MenuItem delete(int indexOfDelete) {
        if (indexOfDelete < menu_size) {
            MenuItem item = menu[indexOfDelete];
            menu[indexOfDelete] = null;
            int temp = indexOfDelete;
            while (temp < menu_size) {
                menu[temp] = menu[temp + 1];
                menu[temp + 1] = null;
                temp++;
            }
            menu_size--;
            return item;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    public static void print(MenuIterator itr) {
        MenuItem item;
        try {
            while(itr.hasNext()) {
                item =itr.next();
                if (item != null) {
                    System.out.println(item.getName() + " $" + item.getPrice() + "\n");
                }
            }
        } catch (NullPointerException e) {}
    }

}


