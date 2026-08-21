public class bDriver extends bStaff {
    
    int speed;
    int deliverCapacity;

    public bDriver(String name, String surname,boolean isPermenant,int speed, int deliverCapacity) {
        super(name, surname,isPermenant);
        this.speed = speed;
        this.deliverCapacity = deliverCapacity;
       
            this.salary = (speed / 10) + (deliverCapacity / 2);
        
    }

    public int getDeliverCapacity() {
        return deliverCapacity;
    }
    public int getSpeed() {
        return speed;
    }

    public String toString(){
        return "|Driver| Name: " + name+ " Surname: " +surname+ " Deliver Capacity: "+deliverCapacity+" Salary: "+salary+ " Permenant: "+ isPermenant;
    }
}
