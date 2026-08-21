package lab8;
//Eralp Yigit Boz 22403188
//Cs 101 Lab 08
public class aBasketRev extends aEntityRev {
    private int hlth;

    public aBasketRev(int xCrd, int yCrd, int hlth) {
        super(xCrd, yCrd, 'U');


        this.hlth = hlth;
    }

    public void moveLeft() {
        if (this.x > 0) {
            this.x--;
        }
    }public void setHealth(int hlth) {
        this.hlth = hlth;
    }

    public void moveRight() {
        if (this.x < 19) {
            this.x++;
        }
    }

    public int getHealth() {
        return this.hlth;
    }
    
}