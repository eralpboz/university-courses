import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// Name: Eralp Yigit Boz
// Student No: 22403188
// Cs 101 Lab 9
public abstract class MenuItemRev {
    protected String itemName;
    protected ArrayList<IngredientRev> ingredients;

    public MenuItemRev(String itemName) {
        this.itemName = itemName;
        this.ingredients = new ArrayList<>();
    }

    protected MenuItemRev(MenuItemRev other) {
        this.itemName = other.itemName;
        this.ingredients = new ArrayList<>();

        for (IngredientRev i : other.ingredients) {
            this.ingredients.add(i);
        }
    }

    public String getItemName() {
        return itemName;
    }

    public void addIngredient(IngredientRev ingredient) {
        ingredients.add(ingredient);
    }

    public boolean removeIngredient(String ingredientName) {
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName().equalsIgnoreCase(ingredientName)) {
                ingredients.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean containsIngredientByName(String ingredientName) {
        for (IngredientRev ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
                return true;
            }
        }
        return false;
    }

    public List<IngredientRev> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public boolean isVegetarian() {
        for (IngredientRev ingredient : ingredients) {
            if (!ingredient.isVegetarian()) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllergenFree() {
        for (IngredientRev ingredient : ingredients) {
            if (ingredient.isAllergen()) {
                return false;
            }
        }
        return true;
    }
	public String toString(){
		return itemName + " ";
	}
    public abstract double calculatePrice();

    public abstract boolean isValid();

    public abstract MenuItemRev copy();
}