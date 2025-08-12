/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package javaapplication134;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Food {
    private String name;
    private double price;
    private String size;

    public Food(String name, double price, String size) {
        this.name = name;
        this.price = price;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }
}

class Order {
    private List<Food> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(Food food) {
        items.add(food);
    }

    public double calculateTotal() {
        double total = 0;
        for (Food food : items) {
            total += food.getPrice();
        }
        return total;
    }

    public void displayOrder() {
        System.out.println("Your Order:");
        for (Food food : items) {
            System.out.println(food.getName() + " (" + food.getSize() + "): $" + food.getPrice());
        }
        System.out.println("Total: $" + calculateTotal());
    }
}

public class JavaApplication134 {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        // Create some sample food items
        Food burger = new Food("Burger", 5.99, "");
        Food pizza = new Food("Pizza", 8.99, "");
        Food salad = new Food("Salad", 3.99, "");
        Food pasta = new Food("Pasta", 7.99, "");
        Food sandwich = new Food("Sandwich", 4.99, "");
        Food iceCream = new Food("Ice Cream", 2.99, "");
        Food sushi = new Food("Sushi", 10.99, "");
        Food curry = new Food("Curry", 6.99, "");
        Food smoothie = new Food("Smoothie", 3.49, "");

        // Display menu
        System.out.println("Menu:");
        System.out.println("1. " + burger.getName() + " - $" + burger.getPrice());
        System.out.println("2. " + pizza.getName() + " - $" + pizza.getPrice());
        System.out.println("3. " + salad.getName() + " - $" + salad.getPrice());
        System.out.println("4. " + pasta.getName() + " - $" + pasta.getPrice());
        System.out.println("5. " + sandwich.getName() + " - $" + sandwich.getPrice());
        System.out.println("6. " + iceCream.getName() + " - $" + iceCream.getPrice());
        System.out.println("7. " + sushi.getName() + " - $" + sushi.getPrice());
        System.out.println("8. " + curry.getName() + " - $" + curry.getPrice());
        System.out.println("9. " + smoothie.getName() + " - $" + smoothie.getPrice());

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
                selectedFood = new Food(selectedFood.getName(), selectedFood.getPrice(), newSize);
                order.addItem(selectedFood);
            } else if (itemNumber == 0) {
                break; // Exit the loop if the user enters 0
            } else {
                System.out.println("Invalid choice. Please enter a valid item number.");
            }

            System.out.print("Do you want to order anything else? (y/n): ");
            choice = scanner.next().charAt(0);

        } while (choice != 'n');

        // Display the final order
        order.displayOrder();

        // Close the scanner
        scanner.close();
    }
}

  