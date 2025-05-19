public class Product {
    private int id;
    private String name;
    private double price;
    private double rating;

    public Product(int id, String name, double price, double rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }

    @Override
    public String toString() {
        return String.format("ID: %d, %s - $%.2f (Rating: %.1f)", id, name, price, rating);
    }
}