public class aTruck extends aVehicle {

    public aTruck(int plateNumber) {
        super(200, 5, 7000, 20, plateNumber);
        
    }

    public String toString() {
        return "Truck " + plateNumber;
    }
}
