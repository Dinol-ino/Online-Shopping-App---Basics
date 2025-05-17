import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShoppingSystem shop = new ShoppingSystem();
        
        // Add sample products
        shop.addProduct(new Product(1, "Laptop", 899.99, 4.5, 8));
        shop.addProduct(new Product(2, "Smartphone", 499.99, 4.7, 5));
        shop.addProduct(new Product(3, "Headphones", 149.99, 4.2, 3));
        shop.addProduct(new Product(4, "Tablet", 349.99, 4.0, 6));
        shop.addProduct(new Product(5, "Smartwatch", 199.99, 3.8, 2));
        
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        
        while (choice != 5) {
            displayMenu();
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        shop.displayProducts(shop.getAllProducts());
                        break;
                        
                    case 2:
                        handleSorting(scanner, shop);
                        break;
                        
                    case 3:
                        handlePriceSearch(scanner, shop);
                        break;
                        
                    case 4:
                        handleOptimization(scanner, shop);
                        break;
                        
                    case 5:
                        System.out.println("Thank you for using the Online Shopping System!");
                        break;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n===== Online Shopping System =====");
        System.out.println("1. Display All Products");
        System.out.println("2. Sort Products (Price/Rating)");
        System.out.println("3. Find Product by Price");
        System.out.println("4. Optimize Shopping Cart (Knapsack)");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void handleSorting(Scanner scanner, ShoppingSystem shop) {
        System.out.println("Sort by: ");
        System.out.println("1. Price (Low to High)");
        System.out.println("2. Rating (High to Low)");
        System.out.print("Enter your choice: ");
        int sortChoice = Integer.parseInt(scanner.nextLine());
        
        Product[] products = shop.getAllProducts().toArray(new Product[0]);
        if (sortChoice == 1) {
            shop.sortByPrice(products);
            System.out.println("\n===== Products Sorted by Price =====");
        } else {
            shop.sortByRating(products);
            System.out.println("\n===== Products Sorted by Rating =====");
        }
        
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("=======================\n");
    }
    
    private static void handlePriceSearch(Scanner scanner, ShoppingSystem shop) {
        System.out.print("Enter the price to search for: $");
        double targetPrice = Double.parseDouble(scanner.nextLine());
        Product foundProduct = shop.findProductByPrice(targetPrice);
        
        if (foundProduct != null) {
            System.out.println("\nProduct found: " + foundProduct);
        } else {
            System.out.println("\nNo product found with the exact price of $" + targetPrice);
        }
    }
    
    private static void handleOptimization(Scanner scanner, ShoppingSystem shop) {
        System.out.print("Enter your budget: $");
        double budget = Double.parseDouble(scanner.nextLine());
        
        List<Product> optimizedSelection = shop.optimizeSelection(budget);
        double totalCost = shop.calculateTotalPrice(optimizedSelection);
        
        System.out.println("\n===== Optimized Selection (Knapsack) =====");
        System.out.println("Budget: $" + budget);
        System.out.println("Total Cost: $" + String.format("%.2f", totalCost));
        System.out.println("Selected Products:");
        for (Product product : optimizedSelection) {
            System.out.println(product);
        }
        System.out.println("=======================\n");
    }
}