import java.util.List;
import java.util.Scanner;
// Name: Eralp Yigit Boz
// Student No: 22403188
// Cs 101 Lab 9
public class RestaurantAppRev {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        RestaurantRev restaurant = new RestaurantRev();
        seedData(restaurant);
        runMenu(restaurant);
    }

    private static void seedData(RestaurantRev restaurant) {
        IngredientRev dough = new IngredientRev("Dough", 3.0, true, false);
        IngredientRev tomato = new IngredientRev("Tomato", 2.0, true, false);
        IngredientRev mushroom = new IngredientRev("Mushroom", 4.0, true, false);
        IngredientRev cheese = new IngredientRev("Cheese", 3.0, true, true);
        IngredientRev beef = new IngredientRev("Beef", 5.0, false, false);
        IngredientRev chicken = new IngredientRev("Chicken", 4.5, false, false);
        IngredientRev lettuce = new IngredientRev("Lettuce", 2.5, true, false);
        IngredientRev olive = new IngredientRev("Olive", 2.0, true, false);
        
        restaurant.addIngredient(dough);
        restaurant.addIngredient(tomato);
        restaurant.addIngredient(mushroom);
        restaurant.addIngredient(cheese);
        restaurant.addIngredient(beef);
        restaurant.addIngredient(chicken);
        restaurant.addIngredient(lettuce);
        restaurant.addIngredient(olive);
        
        PizzaRev veggiePizza = new PizzaRev("Veggie Pizza");
        veggiePizza.addIngredient(dough);
        veggiePizza.addIngredient(tomato);
        veggiePizza.addIngredient(mushroom);
        veggiePizza.addIngredient(olive);
        
        PizzaRev cheesePizza = new PizzaRev("Cheese Pizza");
        cheesePizza.addIngredient(dough);
        cheesePizza.addIngredient(tomato);
        cheesePizza.addIngredient(cheese);
        
        PizzaRev meatPizza = new PizzaRev("Meat Pizza");
        meatPizza.addIngredient(dough);
        meatPizza.addIngredient(beef);
        meatPizza.addIngredient(cheese);
        
        PizzaRev chickenPizza = new PizzaRev("Chicken Pizza");
        chickenPizza.addIngredient(dough);
        chickenPizza.addIngredient(chicken);
        chickenPizza.addIngredient(tomato);
        
        SaladRev gardenSalad = new SaladRev("Garden Salad");
        gardenSalad.addIngredient(lettuce);
        gardenSalad.addIngredient(tomato);
        gardenSalad.addIngredient(olive);
        
        SaladRev proteinSalad = new SaladRev("Protein Salad");
        proteinSalad.addIngredient(lettuce);
        proteinSalad.addIngredient(chicken);
        proteinSalad.addIngredient(tomato);
        
        SaladRev simpleSalad = new SaladRev("Simple Salad");
        simpleSalad.addIngredient(lettuce);
        simpleSalad.addIngredient(tomato);
        
        restaurant.addItemToMenu(veggiePizza);
        restaurant.addItemToMenu(cheesePizza);
        restaurant.addItemToMenu(meatPizza);
        restaurant.addItemToMenu(chickenPizza);
        restaurant.addItemToMenu(gardenSalad);
        restaurant.addItemToMenu(proteinSalad);
        restaurant.addItemToMenu(simpleSalad);
    }

    private static void printIngredients(RestaurantRev restaurant) {
        for (IngredientRev i : restaurant.getIngredients()) {
            System.out.println("- " + i.getName());
        }
    }

    private static void printItemIngredients(MenuItemRev item) {
        System.out.println("Ingredients:");
        for (IngredientRev i : item.getIngredients()) {
            System.out.println("- " + i.getName());
        }
    }

    private static void runMenu(RestaurantRev restaurant) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Restaurant Menu ===");
            System.out.println("1. View Menu");
            System.out.println("2. View Vegetarian Dishes");
            System.out.println("3. View Allergen-Free Dishes");
            System.out.println("4. Add Item to Order");
            System.out.println("5. Customize Order Item");
            System.out.println("6. Display Orders");
            System.out.println("7. Remove Item from Order");
            System.out.println("8. Quit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                viewMenu(restaurant);
            } else if (choice == 2) {
                viewVegetarian(restaurant);
            } else if (choice == 3) {
                viewAllergenFree(restaurant);
            } else if (choice == 4) {
                placeOrder(restaurant);
            } else if (choice == 5) {
                customizeItem(restaurant);
            } else if (choice == 6) {
                displayOrders(restaurant);
            } else if (choice == 7) {
                removeItemFromOrder(restaurant);
            } else if (choice == 8) {
                running = false;
                System.out.println("Goodbye.");
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static void displayOrders(RestaurantRev restaurant) {
        List<OrderRev> pndngOrdLst = restaurant.getPendingOrders();
        java.util.Collections.sort(pndngOrdLst);
        for (int lpIndx = 0; lpIndx < pndngOrdLst.size(); lpIndx++) {
            System.out.println(pndngOrdLst.get(lpIndx).toString());
        }
    }

    private static void viewAllergenFree(RestaurantRev restaurant) {
        System.out.println("Allergen Free Menu:");
        List<MenuItemRev> mnLstItms = restaurant.getMenu();
        for (int lpIndx = 0; lpIndx < mnLstItms.size(); lpIndx++) {
            MenuItemRev crrntMnItm = mnLstItms.get(lpIndx);
            if (crrntMnItm.isAllergenFree()) {
                System.out.println(crrntMnItm.getItemName() + " " + crrntMnItm.calculatePrice());
            }
        }
    }

    private static void placeOrder(RestaurantRev restaurant) {
        viewMenu(restaurant);
        System.out.print("Enter choice:");
        int chsItmNmb = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter order number (0 for new order): ");
        int ordrNmbInp = scanner.nextInt();
        scanner.nextLine();
        OrderRev trgtOrdrObj;
        if (ordrNmbInp != 0) {
            trgtOrdrObj = restaurant.findPendingOrder(ordrNmbInp);
        } else {
            trgtOrdrObj = restaurant.createNewOrder();
            restaurant.addPendingOrder(trgtOrdrObj);
        }
        if (trgtOrdrObj != null && chsItmNmb > 0 && chsItmNmb <= restaurant.getMenu().size()) {
            MenuItemRev slctdMnItm = restaurant.getMenu().get(chsItmNmb - 1);
            MenuItemRev cpySlctdMnItm = slctdMnItm.copy();
            trgtOrdrObj.addItemToOrder(cpySlctdMnItm);
            System.out.println("Item added to order " + trgtOrdrObj.getOrderNo() + ".");
        } else if (trgtOrdrObj == null) {
            System.out.println("Order not found.");
        }
    }

    private static void removeItemFromOrder(RestaurantRev restaurant) {
        System.out.print("Enter order number: ");
        int ordrNmbInp = scanner.nextInt();
        scanner.nextLine();
        OrderRev trgtOrdrObj = restaurant.findPendingOrder(ordrNmbInp);
        if (trgtOrdrObj == null) {
            System.out.println("Order not found.");
            return;
        }
        System.out.println("Items in Order:");
        List<MenuItemRev> ordrItmsLst = trgtOrdrObj.getItems();
        int aa=0;
        while(aa < ordrItmsLst.size()){
            System.out.println("* "+ordrItmsLst.get(aa++).getItemName());
        }
        
        System.out.print("Enter item name to remove: ");
        String itmNmRmvInp = scanner.nextLine();
        MenuItemRev fndItmRmv = null;
        for (int lpIndx = 0; lpIndx < ordrItmsLst.size(); lpIndx++) {
            if (ordrItmsLst.get(lpIndx).getItemName().equalsIgnoreCase(itmNmRmvInp)) {
                fndItmRmv = ordrItmsLst.get(lpIndx);
            }
        }
        if (fndItmRmv != null) {
            ordrItmsLst.remove(fndItmRmv);
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found in order.");
        }
        List<OrderRev> pp= restaurant.getPendingOrders();
        for(int i=0;i<pp.size();i++){
            OrderRev oo=pp.get(i);
            if(oo.getItemNo()==0){
                restaurant.getPendingOrders().remove(i);
            }
        }
    }

    private static void customizeItem(RestaurantRev restaurant) {
        System.out.print("Enter order number to customize: ");
        int ordrNmbInp = scanner.nextInt();
        scanner.nextLine();
        OrderRev trgtOrdrObj = restaurant.findPendingOrder(ordrNmbInp);
        if (trgtOrdrObj == null) {
            return;
        }
        List<MenuItemRev> ordrItmsLst = trgtOrdrObj.getItems();
        int lpIndx = 0;
        while (lpIndx < ordrItmsLst.size()) {
            System.out.println((lpIndx + 1) + " " + ordrItmsLst.get(lpIndx).getItemName());
            lpIndx++;
        }
        System.out.print("Enter item to customize: ");
        int itmChsInp = scanner.nextInt();
        scanner.nextLine();
        if (itmChsInp > 0 && itmChsInp <= ordrItmsLst.size()) {
            MenuItemRev trgtMnItm = ordrItmsLst.get(itmChsInp - 1);
            System.out.println(trgtMnItm.getItemName());
            boolean kpKstMzng = true;
            while (kpKstMzng) {
                System.out.println("1. Add Ingredient\n2. Remove Ingredient");
                System.out.println("3. Done");
                System.out.print("Enter choice: ");
                int cstmChsInp = scanner.nextInt();
                scanner.nextLine();
                if (cstmChsInp == 1) {
                    printIngredients(restaurant);
                    System.out.print("Enter ingredient to add: ");
                    String addIngrdNm = scanner.nextLine();
                    IngredientRev fndIngrd = restaurant.findIngredientByName(addIngrdNm);
                    if (fndIngrd != null) {
                        trgtMnItm.addIngredient(fndIngrd);
                        System.out.println("Added.");
                    }
                } else if (cstmChsInp == 2) {
                    printItemIngredients(trgtMnItm);
                    System.out.print("Enter ingredient to remove: ");
                    String rmvIngrdNm = scanner.nextLine();
                    boolean isRmvScss = trgtMnItm.removeIngredient(rmvIngrdNm);
                    if (isRmvScss) {
                        System.out.println(rmvIngrdNm + " removed from " +
                                trgtMnItm.getItemName());
                    } else {
                        System.out.println(trgtMnItm.getItemName() + " does not contain " +
                                rmvIngrdNm);
                    }
                } else if (cstmChsInp == 3) {
                    System.out.println("Quitting");
                    kpKstMzng = false;
                }
            }
        }
    }

    private static void viewVegetarian(RestaurantRev restaurant) {
        System.out.println("Vegetarian Menu:");
        List<MenuItemRev> mnLstItms = restaurant.getMenu();
        for (int lpIndx = 0; lpIndx < mnLstItms.size(); lpIndx++) {
            MenuItemRev crrntMnItm = mnLstItms.get(lpIndx);
            if (crrntMnItm.isVegetarian()) {
                System.out.println(crrntMnItm.getItemName() + " " + crrntMnItm.calculatePrice());
            }
        }
    }

    private static void viewMenu(RestaurantRev restaurant) {
        System.out.println("Menu:");
        List<MenuItemRev> mnLstItms = restaurant.getMenu();
        for (int lpIndx = 0; lpIndx < mnLstItms.size(); lpIndx++) {
            MenuItemRev crrntMnItm = mnLstItms.get(lpIndx);
            System.out.println((lpIndx + 1) + " " + crrntMnItm.getItemName() + " " +
                    crrntMnItm.calculatePrice());
        }
    }
}