import java.util.*;

public class ShoppingSystem {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void sortByPrice() {
        products.sort(Comparator.comparingDouble(Product::getPrice));
    }

    public void sortByRating() {
        products.sort(Comparator.comparingDouble(Product::getRating).reversed());
    }

    public Product findProductByPrice(double targetPrice) {
        return products.stream()
                .filter(p -> Math.abs(p.getPrice() - targetPrice) < 0.001)
                .findFirst()
                .orElse(null);
    }

    public List<Product> optimizeSelection(double budget) {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(Comparator.comparingDouble(p -> -p.getRating()/p.getPrice()));
        
        List<Product> selected = new ArrayList<>();
        double remaining = budget;
        
        for (Product p : sorted) {
            if (p.getPrice() <= remaining) {
                selected.add(p);
                remaining -= p.getPrice();
            }
        }
        return selected;
    }

    public void displayProducts() {
        System.out.println("\nAvailable Products:");
        products.forEach(System.out::println);
    }

    public double calculateTotalPrice(List<Product> productList) {
        return productList.stream().mapToDouble(Product::getPrice).sum();
    }
}