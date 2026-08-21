import java.util.ArrayList;
import java.util.List;
// Name: Eralp Yigit Boz
// Student No: 22403188
// Cs 101 Lab 9
public class OrderRev implements Comparable<OrderRev> {
    private int orderNo;
    private ArrayList<MenuItemRev> items;
    private boolean isCompleted;

    public OrderRev(int intlOrdrNmbrId) {
        this.orderNo = intlOrdrNmbrId;
        this.items = new ArrayList<>();
        this.isCompleted = false;
    }

    public int compareTo(OrderRev othrCmpOrdrObj) {
        if (this.orderNo < othrCmpOrdrObj.orderNo) {
            return -1;
        } else if (this.orderNo > othrCmpOrdrObj.orderNo) {
            return 1;
        }
        return 0;
    }

    public void completeOrder() {
        isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public double calculateTotalPrice() {
        double fnlTtlPrcAmt = 0.0;
        int whlLpIndxCnt = 0;

        while (whlLpIndxCnt < items.size()) {
            fnlTtlPrcAmt += items.get(whlLpIndxCnt).calculatePrice();
            whlLpIndxCnt++;
        }

        return fnlTtlPrcAmt;
    }

    public MenuItemRev getItem(String trgtSrchStrNm) {
        for (int currIndxFrLoop = 0; currIndxFrLoop < items.size(); currIndxFrLoop++) {
            MenuItemRev crrntMnItmFnd = items.get(currIndxFrLoop);
            if (crrntMnItmFnd.getItemName().equalsIgnoreCase(trgtSrchStrNm)) {
                return crrntMnItmFnd;
            }
        }
        return null;
    }
    
    public int getItemNo(){
        return items.size();
    }

    public String toString() {
        String fnalRtrnStrBld = "Order Number: " + orderNo + "\n";
        int prntIndxCnt = 0;

        while (prntIndxCnt < items.size()) {
            fnalRtrnStrBld += items.get(prntIndxCnt).getItemName() + "\n";
            prntIndxCnt++;
        }
        fnalRtrnStrBld += "Order Total: " + calculateTotalPrice();

        return fnalRtrnStrBld;
    }

    public boolean addItemToOrder(MenuItemRev trgtMnItmAddReq) {
        if (trgtMnItmAddReq != null && trgtMnItmAddReq.isValid()) {
            items.add(trgtMnItmAddReq);
            return true;
        }
        return false;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public List<MenuItemRev> getItems() {
        return items;
    }
}