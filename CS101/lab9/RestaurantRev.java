import java.util.ArrayList;
import java.util.List;
// Name: Eralp Yigit Boz
// Student No: 22403188
// Cs 101 Lab 9
public class RestaurantRev {
    private ArrayList<IngredientRev> ingredients;
    private ArrayList<OrderRev> pendingOrders;
    private static List<MenuItemRev> menu = new ArrayList<>();
    private static int nextOrderNo = 1000;

    public RestaurantRev() {
        ingredients = new ArrayList<>();
        pendingOrders = new ArrayList<>();
    }

    public OrderRev createNewOrder() {
        OrderRev nwRstrntOrdrCrt = new OrderRev(nextOrderNo);
        nextOrderNo++;
        return nwRstrntOrdrCrt;
    }

    public IngredientRev findIngredientByName(String name) {
        for (IngredientRev ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(name)) {
                return ingredient;
            }
        }
        return null;
    }

    public List<MenuItemRev> getMenu() {
        return menu;
    }

    public List<OrderRev> getPendingOrders() {
        return pendingOrders;
    }

    public List<IngredientRev> getIngredients() {
        return ingredients;
    }

    public void addIngredient(IngredientRev ingredient) {
        ingredients.add(ingredient);
    }

    public void addPendingOrder(OrderRev order) {
        boolean isOrdrAlrdyPndng = true;
        int lpIndxCnt = 0;

        while (lpIndxCnt < pendingOrders.size()) {
            OrderRev crrntPndngOrdrChk = pendingOrders.get(lpIndxCnt);
            if (crrntPndngOrdrChk.getOrderNo() == order.getOrderNo()) {
                isOrdrAlrdyPndng = false;
                break;
            }
            lpIndxCnt++;
        }

        if (isOrdrAlrdyPndng) {
            pendingOrders.add(order);
        }
    }

    public OrderRev findPendingOrder(int orderNo) {
        for (int lpIndxCnt2 = 0; lpIndxCnt2 < pendingOrders.size(); lpIndxCnt2++) {
            OrderRev crrntFndOrdrObj = pendingOrders.get(lpIndxCnt2);
            if (crrntFndOrdrObj.getOrderNo() == orderNo) {
                return crrntFndOrdrObj;
            }
        }
        return null;
    }

    public boolean addItemToMenu(MenuItemRev item) {
        boolean isMnItmAddSucc = false;
        if (item != null && item.isValid()) {
            menu.add(item);
            return true;
        }
        return isMnItmAddSucc;
    }
}