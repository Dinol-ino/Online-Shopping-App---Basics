import java.util.List;  // Add this import statement
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShoppingSystem shop = new ShoppingSystem();
        Scanner scanner = new Scanner(System.in);
        
        // Add sample products
        shop.addProduct(new Product(1, "Laptop", 899.99, 4.5));
        shop.addProduct(new Product(2, "Smartphone", 499.99, 4.7));
        shop.addProduct(new Product(3, "Headphones", 149.99, 4.2));
        shop.addProduct(new Product(4, "Tablet", 349.99, 4.0));
        shop.addProduct(new Product(5, "Smartwatch", 199.99, 3.8));
        
        while (true) {
            System.out.println("\n1. View All\n2. Sort\n3. Search by Price\n4. Optimize\n5. Exit");
            System.out.print("Enter choice (1-5): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        shop.displayProducts();
                        break;
                    case 2:
                        System.out.print("Sort by:\n1. Price\n2. Rating\nEnter choice: ");
                        int sortChoice = Integer.parseInt(scanner.nextLine());
                        if (sortChoice == 1) shop.sortByPrice();
                        else shop.sortByRating();
                        shop.displayProducts();
                        break;
                    case 3:
                        System.out.print("Enter price to search: $");
                        double price = Double.parseDouble(scanner.nextLine());
                        Product found = shop.findProductByPrice(price);
                        System.out.println(found != null ? found : "No product found");
                        break;
                    case 4:
                        System.out.print("Enter budget: $");
                        List<Product> optimized = shop.optimizeSelection(Double.parseDouble(scanner.nextLine()));
                        System.out.println("Optimized List:");
                        optimized.forEach(System.out::println);
                        System.out.printf("Total: $%.2f\n", shop.calculateTotalPrice(optimized));
                        break;
                    case 5:
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }
}