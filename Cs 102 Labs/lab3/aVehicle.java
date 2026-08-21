import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class aVehicle {
    protected int cargoCapacity;
    protected int staffCapacity;
    protected int cost;
    protected int speed;
    protected int plateNumber;
    protected int profit;
    protected ArrayList<bStaff> workers;

    public aVehicle(int cargoCapacity, int staffCapacity, int cost, int speed, int plateNumber) {
        this.cargoCapacity = cargoCapacity;
        this.staffCapacity = staffCapacity;
        this.cost = cost;
        this.speed = speed;
        this.plateNumber = plateNumber;
        this.profit=0;
        this.workers=new ArrayList<>();
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }
    public int getStaffCapacity() {
        return staffCapacity;
    }
    public int getCost() {
        return cost;
    }
    public int getSpeed() {
        return speed;
    }
    public int getPlateNumber() {
        return plateNumber;
    }
    public int getProfit(){
        return profit;
    }
    public void addProfit(int x){
        profit=profit+x;
    }
    public ArrayList<bStaff> getWorkers(){
        return workers;
    }
    public void setWorker(bStaff staff){
        workers.add(staff);
    }
}
