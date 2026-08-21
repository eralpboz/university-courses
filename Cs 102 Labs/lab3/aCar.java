public class aCar extends aVehicle {

    public aCar(int plateNumber) {
        super(20, 2, 2000, 50, plateNumber);
    }

    public String toString() {
        return "Car " + plateNumber;
    }

}
