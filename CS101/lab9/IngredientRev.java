public class IngredientRev {
    private String ingredientName;
    private double price;
    private int stockCount;
    private boolean isVeg;
    private boolean isAllergen;
// Name: Eralp Yigit Boz
// Student No: 22403188
// Cs 101 Lab 9
    public IngredientRev(String name, double price, boolean vegetarian, boolean allergen) {
        this.ingredientName = name;
        this.price = price;
        this.isVeg = vegetarian;
        this.isAllergen = allergen;
        this.stockCount = 0;
    }

    public String getName() {
        return ingredientName;
    }

    public double getPrice() {
        return price;
    }

    public int getStockCount() {
        return stockCount;
    }

    public boolean isVegetarian() {
        return isVeg;
    }

    public boolean isAllergen() {
        return isAllergen;
    }

    public boolean consume(int quantity) {
        if (quantity <= 0) {
            return false;
        }
        if (stockCount < quantity) {
            return false;
        }
        stockCount -= quantity;
        return true;
    }

    public boolean equals(Object obj) {
        if (obj instanceof IngredientRev) {
            IngredientRev other = (IngredientRev) obj;
            return ingredientName.equalsIgnoreCase(other.getName());
        }
        return false;
    }

    public String toString() {
        return String.format("%s (%.2f c, stock: %d)", ingredientName, price, stockCount);
    }
}