public class aBicycle extends aVehicle {

    public aBicycle(int plateNumber) {
        super(5, 1, 100, 10, plateNumber);
        
    }

    public String toString() {
        return "Bicycle " + plateNumber;
    }
}