public class aVan extends aVehicle {
    public aVan(int plateNumber) {
        super(100, 4, 4000, 25, plateNumber);
        
    }

    public String toString() {
        return "Van " + plateNumber;
    }
}
