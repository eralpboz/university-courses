public class bReceiver extends bStaff {
    int receivingCapacity;
    

    public bReceiver(String name, String surname,boolean isPermenant ,int receivingCapacity) {
        super(name, surname,isPermenant);
        this.receivingCapacity = receivingCapacity;
        this.salary=receivingCapacity/ 2;
        
            this.salary=receivingCapacity/ 2;
        
    }

    public int getReceivingCapacity() {
        return receivingCapacity;
    }

    public String toString(){
        return "|Receiver| Name: " + name+ " Surname: " +surname+ " Receiving Capacity: "+receivingCapacity+" Salary: "+salary + " Permenant: "+ isPermenant;
    }
}
