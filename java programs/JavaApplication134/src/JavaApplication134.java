/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package javaapplication134;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Food {
    private String name;
    private Map<String, Double> prices;

    public Food(String name, double smallPrice, double mediumPrice, double largePrice) {
        this.name = name;
        this.prices = new HashMap<>();
        prices.put("Small", smallPrice);
        prices.put("Medium", mediumPrice);
        prices.put("Large", largePrice);
    }

    public String getName() {
        return name;
    }

    public double getPrice(String size) {
        return prices.get(size);
    }
}

class Order {
    private List<Food> items;
    private String deliveryTime;
    private String customerName;
    private String customerAddress;
    private String contactNumber;

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(Food food) {
        items.add(food);
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setCustomerDetails(String name, String address, String contactNumber) {
        this.customerName = name;
        this.customerAddress = address;
        this.contactNumber = contactNumber;
    }

    public double calculateTotal() {
        double total = 0;
        for (Food food : items) {
            total += food.getPrice(food.getSize());
        }
        return total;
    }

    public void displayOrder() {
        System.out.println("Customer Details:");
        System.out.println("Name: " + customerName);
        System.out.println("Address: " + customerAddress);
        System.out.println("Contact Number: " + contactNumber);

        System.out.println("\nYour Order:");
        for (Food food : items) {
            System.out.println(food.getName() + " (" + food.getSize() + "): $" + food.getPrice(food.getSize()));
        }

        System.out.println("\nTotal: $" + calculateTotal());
        System.out.println("Delivery Time: " + deliveryTime);
    }
}

public class JavaApplication134 {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        // Create some sample food items with different sizes and prices
        Food burger = new Food("Burger", 5.99, 7.99, 9.99);
        Food pizza = new Food("Pizza", 8.99, 11.99, 14.99);
        Food salad = new Food("Salad", 3.99, 5.99, 7.99);
        Food pasta = new Food("Pasta", 7.99, 9.99, 12.99);
        Food sandwich = new Food("Sandwich", 4.99, 6.99, 8.99);
        Food iceCream = new Food("Ice Cream", 2.99, 4.99, 6.99);
        Food sushi = new Food("Sushi", 10.99, 13.99, 16.99);
        Food curry = new Food("Curry", 6.99, 9.99, 12.99);
        Food smoothie = new Food("Smoothie", 3.49, 4.99, 6.49);

        // Display menu
        System.out.println("Menu:");
        System.out.println("1. " + burger.getName());
        System.out.println("2. " + pizza.getName());
        System.out.println("3. " + salad.getName());
        System.out.println("4. " + pasta.getName());
        System.out.println("5. " + sandwich.getName());
        System.out.println("6. " + iceCream.getName());
        System.out.println("7. " + sushi.getName());
        System.out.println("8. " + curry.getName());
        System.out.println("9. " + smoothie.getName());

        // Create an order
        Order order = new Order();

        // Take user input for ordering
        char choice;
        do {
            System.out.print("Enter the number of the item you want to order (0 to finish): ");
            int itemNumber = scanner.nextInt();

            if (itemNumber >= 1 && itemNumber <= 9) {
                Food selectedFood = null;
                switch (itemNumber) {
                    case 1:
                        selectedFood = burger;
                        break;
                    case 2:
                        selectedFood = pizza;
                        break;
                    case 3:
                        selectedFood = salad;
                        break;
                    case 4:
                        selectedFood = pasta;
                        break;
                    case 5:
                        selectedFood = sandwich;
                        break;
                    case 6:
                        selectedFood = iceCream;
                        break;
                    case 7:
                        selectedFood = sushi;
                        break;
                    case 8:
                        selectedFood = curry;
                        break;
                    case 9:
                        selectedFood = smoothie;
                        break;
                }

                // Ask for size
                System.out.println("Available sizes for " + selectedFood.getName() + ": Small, Medium, Large");
                System.out.print("Enter the size you want: ");
                String newSize = scanner.next();

                // Update food item with selected size
                selectedFood = new Food(selectedFood.getName(), selectedFood.getPrice(newSize), newSize);
                order.addItem(selectedFood);
            } else if (itemNumber == 0) {
                break; // Exit the loop if the user enters 0
            } else {
                System.out.println("Invalid choice. Please enter a valid item number.");
            }

            System.out.print("Do you want to order anything else? (y/n): ");
            choice = scanner.next().charAt(0);

        } while (choice != 'n');

        // Ask for delivery time
        System.out.print("Enter the desired delivery time: ");
        String deliveryTime = scanner.next();
        order.setDeliveryTime(deliveryTime);

        // Ask for customer details
        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter your address: ");
        String address = scanner.next();
        System.out.print("Enter your contact number: ");
        String contactNumber = scanner.next();

        order.setCustomerDetails(name, address, contactNumber);

        // Display the final order
        order.displayOrder();

        // Close the scanner
        scanner.close();
    }
}

  