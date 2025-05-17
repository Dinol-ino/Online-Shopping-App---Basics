public class Product{
    
        private int id;
        private String name;
        private double price;
        private double rating;
        private int weight; // Used for knapsack algorithm (can represent shipping weight or importance)
    
        public Product(int id, String name, double price, double rating, int weight) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.rating = rating;
            this.weight = weight;
        }
    
        // Getters
        public int getId() {
            return id;
        }
    
        public String getName() {
            return name;
        }
    
        public double getPrice() {
            return price;
        }
    
        public double getRating() {
            return rating;
        }
    
        public int getWeight() {
            return weight;
        }
    
        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price=$" + price +
                    ", rating=" + rating + "/5" +
                    ", weight=" + weight +
                    '}';
        }
    }
                    