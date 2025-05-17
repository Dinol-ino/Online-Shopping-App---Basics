import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ShoppingSystem {
    private List<Product> products;

    public ShoppingSystem() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return products;
    }

    // Simplified Knapsack Algorithm
    public List<Product> optimizeSelection(double budget) {
        int n = products.size();
        int W = (int)(budget * 100);
        
        // Create arrays for dynamic programming
        double[][] dp = new double[n + 1][W + 1];
        boolean[][] selected = new boolean[n + 1][W + 1];
        
        // Fill the dp table
        for (int i = 1; i <= n; i++) {
            Product product = products.get(i - 1);
            int weight = (int)(product.getPrice() * 100);
            double value = product.getRating() * product.getWeight();
            
            for (int w = 0; w <= W; w++) {
                if (weight <= w && dp[i - 1][w - weight] + value > dp[i - 1][w]) {
                    dp[i][w] = dp[i - 1][w - weight] + value;
                    selected[i][w] = true;
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        // Backtrack to find selected items
        List<Product> selectedProducts = new ArrayList<>();
        int w = W;
        for (int i = n; i > 0; i--) {
            if (selected[i][w]) {
                selectedProducts.add(products.get(i - 1));
                w -= (int)(products.get(i - 1).getPrice() * 100);
            }
        }
        
        return selectedProducts;
    }
    
    // Simplified Binary Search
    public Product findProductByPrice(double targetPrice) {
        Product[] sortedProducts = products.toArray(new Product[0]);
        Arrays.sort(sortedProducts, Comparator.comparingDouble(Product::getPrice));
        
        int left = 0, right = sortedProducts.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            double midPrice = sortedProducts[mid].getPrice();
            
            if (Math.abs(midPrice - targetPrice) < 0.001) {
                return sortedProducts[mid];
            }
            
            if (midPrice < targetPrice) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return null;
    }
    
    // Simplified sorting methods
    public Product[] sortByPrice(Product[] arr) {
        Arrays.sort(arr, Comparator.comparingDouble(Product::getPrice));
        return arr;
    }
    
    public Product[] sortByRating(Product[] arr) {
        Arrays.sort(arr, Comparator.comparingDouble(Product::getRating).reversed());
        return arr;
    }
    
    // Display products
    public void displayProducts(List<Product> productList) {
        System.out.println("\n===== Product List =====");
        for (Product product : productList) {
            System.out.println(product);
        }
        System.out.println("=======================\n");
    }
    
    // Calculate total price
    public double calculateTotalPrice(List<Product> productList) {
        return productList.stream().mapToDouble(Product::getPrice).sum();
    }
}