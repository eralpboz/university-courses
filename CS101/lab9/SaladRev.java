public class SaladRev extends MenuItemRev {
    private static final int BASE_PRICE = 15;
// Name: Eralp Yigit Boz
// Student No: 22403188
// Cs 101 Lab 9
    public SaladRev(String itemName) {
        super(itemName);
    }

    public SaladRev(SaladRev other) {
        super(other);
    }

    public boolean isValid() {
        boolean crrntSldVldStts = false;

        if (ingredients.size() >= 2 && isVegetarian()) {
            crrntSldVldStts = true;
        }

        return crrntSldVldStts;
    }

    public double calculatePrice() {
        double fnlSldTtlPrcAmt = BASE_PRICE;
        int whlLpIndxCnt = 0;

        while (whlLpIndxCnt < ingredients.size()) {
            fnlSldTtlPrcAmt += ingredients.get(whlLpIndxCnt).getPrice();
            whlLpIndxCnt++;
        }

        return fnlSldTtlPrcAmt;
    }

    public MenuItemRev copy() {
        SaladRev nwSldCpyObjRtrn = new SaladRev(this);
        return nwSldCpyObjRtrn;
    }
}