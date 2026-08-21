package lab8;
//Eralp Yigit Boz 22403188
//Cs 101 Lab 08
public class aEntityRev {
    protected int y;
    private char smbl;
    
    protected int x;
    


public int getX() {
        return this.x;
    }


    public aEntityRev(int xCrd, int yCrd, char smbl) {
        this.x = xCrd;
        this.y = yCrd;
        this.smbl = smbl;
    }

    

    

    public char getSymbol() {
        return this.smbl;
    }

    public boolean collidesWith(aEntityRev othr) {
        if (this.x == othr.getX() && this.y == othr.getY()) {
            return true;
        }
        return false;

    }public int getY() {
        return this.y;
    }
}