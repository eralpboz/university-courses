public abstract class bStaff {
    protected String name;
    protected String surname;
    protected int salary;
    protected boolean isAvailable;
    protected boolean isPermenant;

    public bStaff(String name, String surname, boolean isPermenant) {
        this.name = name;
        this.surname = surname;
        this.isAvailable=true;
        this.isPermenant=isPermenant;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getSalary() {
        return salary;
    }

    public void increaseSalary(int newSalary){
        this.salary+=newSalary;
    }

    public boolean getIsAvailable(){
        return isAvailable;
    }
    public void setIsAvailable(boolean x){
        isAvailable = x;
    }
    public boolean  getIsPermenant(){
        return isPermenant;
    }
    public void setIsPermenant(boolean x){
        isPermenant=x;
    }
    
}
