public class bCarrier extends bStaff {
    
    int deliverCapacity;
    int priceBoost;

    public bCarrier(String name, String surname, boolean isPermenant , int deliverCapacity, int priceBoost) {
        super(name, surname, isPermenant);
        this.deliverCapacity = deliverCapacity;
        this.priceBoost = priceBoost;
        this.salary=(priceBoost) + (deliverCapacity / 5);
         
            this.salary=(priceBoost) + (deliverCapacity / 5);
        
        
    
    }

    public int getDeliverCapacity() {
        return deliverCapacity;
    }
    public int getPriceBoost(){
        return priceBoost;
    }
    public String toString(){
        return "|Carrier| Name: " + name+ " Surname: " +surname+ " Deliver Capacity: "+deliverCapacity+" Price Boost: "+priceBoost+ " Salary: "+salary+ " Permenant: "+ isPermenant;
    }
}
