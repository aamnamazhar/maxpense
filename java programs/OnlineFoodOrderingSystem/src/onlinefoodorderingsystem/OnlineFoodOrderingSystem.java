/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package onlinefoodorderingsystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Food {
    private String name;
    private double smallPrice;
    private double mediumPrice;
    private double largePrice;
    private String size;
    private List<String> toppings;

    public Food(String name, double smallPrice, double mediumPrice, double largePrice) {
        this.name = name;
        this.smallPrice = smallPrice;
        this.mediumPrice = mediumPrice;
        this.largePrice = largePrice;
        this.size = "";
        this.toppings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getPrice(String size) {
        switch (size.toLowerCase()) {
            case "small":
                return smallPrice;
            case "medium":
                return mediumPrice;
            case "large":
                return largePrice;
            default:
                return 0.0; // Return 0 for invalid size
        }
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void addTopping(String topping) {
        toppings.add(topping);
    }
}

class Order {
    private static int orderCount = 0;

    private int orderId;
    private List<Food> items;
    private String deliveryTime;
    private String customerName;
    private String customerAddress;
    private String contactNumber;
    private double deliveryFee = 5.0; // Default delivery fee
    private double discount;
    private String specialInstructions;
     private String status = "Pending"; // Default status
    
    public Order() {
        this.orderId = ++orderCount;
        this.items = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Food> getItems() {
        return items;
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

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void applyDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public double calculateTotalWithDiscount() {
        double total = calculateTotal();
        return total - discount;
    }

    public double calculateTotalWithDelivery() {
        return calculateTotalWithDiscount() + deliveryFee;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

     public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void displayOrderDetails() {
        System.out.println("Order #" + orderId);
        System.out.println("Customer Details:");
        System.out.println("Name: " + customerName);
        System.out.println("Address: " + customerAddress);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Delivery Time: " + deliveryTime);

        System.out.println("\nOrder Summary:");
        for (Food food : items) {
            System.out.println(food.getName() + " (" + food.getSize() + "): $" + food.getPrice(food.getSize()));
            if (!food.getToppings().isEmpty()) {
                System.out.println("   Toppings: " + String.join(", ", food.getToppings()));
            }
        }

        System.out.println("\nSubtotal: $" + calculateTotal());
        System.out.println("Discount: $" + discount);
        System.out.println("Delivery Fee: $" + deliveryFee);
        System.out.println("Total: $" + calculateTotalWithDelivery());

        System.out.println("\nSpecial Instructions: " + specialInstructions);
         System.out.println("Order Status: " + status);
    }
}


public class OnlineFoodOrderingSystem {
    public static void main(String[] args) {
     System.out.println("****************WELCOME TO THE MUNCH CRAZE************");
      Scanner scanner = new Scanner(System.in);
Food burger = new Food("Burger", 5.99, 7.99, 9.99);
        Food pizza = new Food("Pizza", 8.99, 11.99, 14.99);
        Food salad = new Food("Salad", 3.99, 5.99, 7.99);
        Food pasta = new Food("Pasta", 10.99, 13.99, 16.99);
        Food steak = new Food("Steak", 15.99, 18.99, 21.99);
        Food sushi = new Food("Sushi", 12.99, 15.99, 18.99);
        Food sandwich = new Food("Sandwich", 6.99, 8.99, 10.99);
        Food iceCream = new Food("Ice Cream", 4.99, 6.99, 8.99);
        Food curry = new Food("Curry", 9.99, 11.99, 14.99);
        Food smoothie = new Food("Smoothie", 3.49, 4.99, 6.49);
        Food fries = new Food("Fries", 2.99, 3.99, 4.99);
        Food chickenWings = new Food("Chicken Wings", 7.99, 9.99, 12.99);
        Food soup = new Food("Soup", 5.99, 7.99, 9.99);
        Food ribs = new Food("Ribs", 13.99, 16.99, 19.99);

        // Display menu
        System.out.println("Menu:");
        System.out.println("1. " + burger.getName());
        System.out.println("2. " + pizza.getName());
        System.out.println("3. " + salad.getName());
        System.out.println("4. " + pasta.getName());
        System.out.println("5. " + steak.getName());
        System.out.println("6. " + sushi.getName());
        System.out.println("7. " + sandwich.getName());
        System.out.println("8. " + iceCream.getName());
        System.out.println("9. " + curry.getName());
        System.out.println("10. " + smoothie.getName());
        System.out.println("11. " + fries.getName());
        System.out.println("12. " + chickenWings.getName());
        System.out.println("13. " + soup.getName());
        System.out.println("14. " + ribs.getName());

        char anotherOrder;
        do {
            // Create an order
            Order order = new Order();

            // Take user input for ordering
            char choice = 0;
            do {
                System.out.print("\nEnter the number of the item you want to order (0 to finish): ");
                int itemNumber = scanner.nextInt();

                switch (itemNumber) {
                    case 1:
                        order.addItem(burger);
                        break;
                    case 2:
                        order.addItem(pizza);
                        break;
                    case 3:
                        order.addItem(salad);
                        break;
                    case 4:
                        order.addItem(pasta);
                        break;
                    case 5:
                        order.addItem(steak);
                        break;
                    case 6:
                        order.addItem(sushi);
                        break;
                    case 7:
                        order.addItem(sandwich);
                        break;
                    case 8:
                        order.addItem(iceCream);
                        break;
                    case 9:
                        order.addItem(curry);
                        break;
                    case 10:
                        order.addItem(smoothie);
                        break;
                    case 11:
                        order.addItem(fries);
                        break;
                    case 12:
                        order.addItem(chickenWings);
                        break;
                    case 13:
                        order.addItem(soup);
                        break;
                    case 14:
                        order.addItem(ribs);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid item number.");
                        continue;
                }

                if (itemNumber != 0) {
                    // Ask for size
                    System.out.print("Available sizes for " + order.getItems().get(order.getItems().size() - 1).getName() + ": Small, Medium, Large\n");
                    System.out.print("Enter the size you want: ");
                    String newSize = scanner.next().toLowerCase();
                    order.getItems().get(order.getItems().size() - 1).setSize(newSize);

                    // Ask for toppings
                    System.out.print("Do you want to add toppings? (y/n): ");
                    char addToppingsChoice = scanner.next().charAt(0);
                    if (addToppingsChoice == 'y') {
                        scanner.nextLine(); // Consume the newline character
                        System.out.print("Enter toppings (comma-separated): ");
                        String toppingsInput = scanner.nextLine();
                        String[] toppingsArray = toppingsInput.split(",");
                        for (String topping : toppingsArray) {
                            order.getItems().get(order.getItems().size() - 1).addTopping(topping.trim());
                        }
                    }
                }

                System.out.print("Do you want to order anything else? (y/n): ");
                choice = scanner.next().charAt(0);

            } while (choice != 'n');

            // Take user input for special instructions
            System.out.print("\nAny special instructions for this order? (Enter 'none' for no instructions): ");
            scanner.nextLine(); // Consume the newline character
            String specialInstructions = scanner.nextLine();
            if (!specialInstructions.toLowerCase().equals("none")) {
                order.setSpecialInstructions(specialInstructions);
            }
System.out.println("");
System.out.println("Enter your details: ");
             // Ask for customer details
        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter your address: ");
        String address = scanner.next();
        System.out.print("Enter your contact number: ");
        String contactNumber = scanner.next();

        order.setCustomerDetails(name, address, contactNumber);
            
             // Take user input for delivery time
            System.out.println("\nAvailable Delivery Times:");
            System.out.println("1. 12:00 PM");
            System.out.println("2. 3:00 PM");
            System.out.println("3. 6:00 PM");
            System.out.println("4. 9:00 PM");
            System.out.print("Choose a delivery time (enter the number): ");
            int deliveryTimeChoice = scanner.nextInt();
            switch (deliveryTimeChoice) {
                case 1:
                    order.setDeliveryTime("12:00 PM");
                    break;
                case 2:
                    order.setDeliveryTime("3:00 PM");
                    break;
                case 3:
                    order.setDeliveryTime("6:00 PM");
                    break;
                case 4:
                    order.setDeliveryTime("9:00 PM");
                    break;
                default:
                    System.out.println("Invalid choice. Setting default delivery time to 2:00 PM.");
                    order.setDeliveryTime("2:00 PM");
                    break;
            }
             
            //order tacking
             order.setStatus("Processing");
            System.out.println("\nOrder status: " + order.getStatus());

            // Simulate order processing time (you can replace this with actual order processing logic)
            try {
                Thread.sleep(15000); // Simulating processing time (15 seconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Update order status to "Out for Delivery"
            order.setStatus("Out for Delivery");
            System.out.println("Order status: " + order.getStatus());

            // Simulate delivery time (you can replace this with actual delivery logic)
            try {
                Thread.sleep(20000); // Simulating delivery time (20 seconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Update order status to "Delivered"
            order.setStatus("Delivered");
            System.out.println("Order status: " + order.getStatus());
            
            // Display the final order details
            order.displayOrderDetails();

            // Ask if the user wants to place another order
            System.out.print("\nDo you want to place another order? (y/n): ");
            anotherOrder = scanner.next().charAt(0);

        } while (anotherOrder == 'y');
        
        // Close the scanner
        scanner.close();            
       System.out.println("********TAHNK YOU FOR ORDERING***********");
    }
}
