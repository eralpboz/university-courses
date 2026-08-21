public class aHelicopter extends aVehicle {

    public aHelicopter(int plateNumber) {
        super(50, 2, 15000, 100, plateNumber);
        
    }

    public String toString() {
        return "Helicopter " + plateNumber;
    }
}
