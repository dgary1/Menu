/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// *********************

// David Gary
// COSC 436 - Object Oriented Programming
// Assignment 4 - Menu Iterators

// *********************


import java.util.Scanner;

/**
 *
 * @author davidgary
 */
public class MenuClient {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        char choice;
        String g = "";
        int option;
        Menu eatAtJoesMenu = new Menu();
        // populating eatAtJoesMenu
        eatAtJoesMenu.add("Lobster Dinner", Menu.MAIN_DISH, Menu.NOT_HEART_HEALTHY, "24.99");
        eatAtJoesMenu.add("Rice Pudding", Menu.DESSERT, Menu.NOT_HEART_HEALTHY, "3.50");
        eatAtJoesMenu.add("Macaroni and Cheese", Menu.MAIN_DISH, Menu.NOT_HEART_HEALTHY, "7.99");
        eatAtJoesMenu.add("French Fries", Menu.APPETIZERS, Menu.NOT_HEART_HEALTHY, "3.49");
        eatAtJoesMenu.add("Chocolate Sundae", Menu.DESSERT, Menu.NOT_HEART_HEALTHY, "10.99");
        eatAtJoesMenu.add("Chicken Ceasar Salad", Menu.MAIN_DISH, Menu.HEART_HEALTHY, "12.99");
        eatAtJoesMenu.add("Spinach Dip", Menu.APPETIZERS, Menu.NOT_HEART_HEALTHY, "6.49");
        eatAtJoesMenu.add("Alaskan Crab", Menu.DESSERT, Menu.HEART_HEALTHY, "34.99");
        // Intro to starting the program
        System.out.println("Welcome to Joes Restaurant!\nWould you like to begin (Y/N)?:");
        choice = input.next().charAt(0);
        // input validation
        while (choice != 'Y' && choice != 'y' && choice != 'N' && choice != 'n') {
            System.out.print("Whoops! You didn't choose the right answer. Try again. Yes or No (Y/N)?: ");
            choice = input.next().charAt(0);
        }
        // begins main part of the program if Y or y is entered
        while (choice == 'Y' || choice == 'y') {
            menu();
            // input validation to make sure someone puts in an integer as a choice
            option = getInt(g);
            switch (option) {
                case 1: {
                    Menu.MenuIterator itr = eatAtJoesMenu.getAllItemsIterator();
                    System.out.println("All Menu Items:");
                    Menu.print(itr);
                    break;
                }
                case 2: {
                    Menu.MenuIterator itr = eatAtJoesMenu.getItemIterator(Menu.APPETIZERS);
                    System.out.println("Appetizers:");
                    Menu.print(itr);
                    break;
                }
                case 3: {
                    Menu.MenuIterator itr = eatAtJoesMenu.getItemIterator(Menu.MAIN_DISH);
                    System.out.println("Main Dishes:");
                    Menu.print(itr);
                    break;
                }
                case 4: {
                    Menu.MenuIterator itr = eatAtJoesMenu.getItemIterator(Menu.DESSERT);
                    System.out.println("Desserts:");
                    Menu.print(itr);
                    break;
                }
                case 5: {
                    Menu.MenuIterator itr = eatAtJoesMenu.getHeartHealthyIterator(Menu.HEART_HEALTHY);
                    System.out.println("Heart Healthy Items: ");
                    Menu.print(itr);
                    break;
                }
                case 6: {
                    Menu.MenuIterator itr = eatAtJoesMenu.getPriceIterator("15.00");
                    System.out.println("All items under $15.00:");
                    Menu.print(itr);
                    break;
                }
                case 7: {
                    addItem(eatAtJoesMenu);
                    break;
                }
                case 8: {
                    System.out.print("Enter the dish number you want to remove: ");
                    int num = getInt(g);
                    eatAtJoesMenu.delete(num);
                    break;
                }
            }
            System.out.print("Would you like to go back to your options? (Y/N): ");
            choice = input.next().charAt(0);
        }

    }
    public static void menu() {
        String g = "";
        System.out.println("Menu:");
        System.out.println("1 - Display all menu items");
        System.out.println("2 - Display all appetizers");
        System.out.println("3 - Display all main dishes");
        System.out.println("4 - Display all desserts");
        System.out.println("5 - Display all heart healthy items");
        System.out.println("6 - Display all main dishes under a specified price");
        System.out.println("7 - Add menu item");
        System.out.println("8 - Remove menu item\n");
        System.out.println("Choose what you would like to do: ");
    }
    public static int getInt(String g) {
        Scanner input = new Scanner(System.in);
        while(!input.hasNextInt()) {
            System.out.print("Error! Wrong input type. Try again. Enter a key for split: ");
            input.next();
        }
        return input.nextInt();
    }
    public static void addItem(Menu m) {
        Scanner input = new Scanner(System.in);
        String g = "";
        // asks for dish name
        System.out.println("Enter the name of the new dish you would like to add to the menu:");
        String name = input.next();
        // chose which dish category it applies to
        System.out.println("Enter the type of dish\nYour choices are:\n1 = Appetizer\n2 = Dish\n3 = Dessert\nEnter:");
        int type = getInt(g);
        while (type < 1 || type > 3) {
            System.out.print("Oops, thats not a choice. Try again. Re-enter: ");
            type = getInt(g);
        }
        switch (type) {
            case 1: type = Menu.APPETIZERS;
                break;
            case 2: type = Menu.MAIN_DISH;
                break;
            case 3: type = Menu.DESSERT;
                break;
        }
        // asks if it is heart healthy or not
        System.out.print("Is the item Heart Healthy? (Y/N): ");
        char heartHealth = input.next().charAt(0);
        boolean health;
        health = (heartHealth != 'Y' || heartHealth != 'y')?Menu.NOT_HEART_HEALTHY:Menu.HEART_HEALTHY;
        // asks for price
        System.out.print("Enter the price of the new dish: ");
        String p = input.next();
        m.add(name, type, health,p);
    }
}
