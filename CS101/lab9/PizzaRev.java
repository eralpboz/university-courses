public class PizzaRev extends MenuItemRev {
    private static final int BASE_PRICE = 30;
// Name: Eralp Yigit Boz
// Student No: 22403188
// Cs 101 Lab 9
    public PizzaRev(String itemName) {
        super(itemName);
    }

    public PizzaRev(PizzaRev other) {
        super(other);
    }

    public MenuItemRev copy() {
        PizzaRev nwPzzCpyObjRtrn = new PizzaRev(this);
        return nwPzzCpyObjRtrn;
    }

    public double calculatePrice() {
        double fnlPzzTtlPrcAmt = BASE_PRICE;
        int whlLpIndxCnt = 0;

        while (whlLpIndxCnt < ingredients.size()) {
            IngredientRev crrntIngrdntChk = ingredients.get(whlLpIndxCnt);
            if (!crrntIngrdntChk.getName().equalsIgnoreCase("Dough")) {
                fnlPzzTtlPrcAmt += crrntIngrdntChk.getPrice();
            }
            whlLpIndxCnt++;
        }

        return fnlPzzTtlPrcAmt;
    }

    public boolean removeIngredient(String ingredientName) {
        if (ingredientName.equalsIgnoreCase("Dough")) {
            return false;
        }

        boolean isIngrdntRmvSucc = super.removeIngredient(ingredientName);
        return isIngrdntRmvSucc;
    }

    public boolean isValid() {
        boolean crrntPzzVldStts = false;

        if (ingredients.size() >= 2 && containsIngredientByName("Dough")) {
            return true;
        }

        return crrntPzzVldStts;
    }
}