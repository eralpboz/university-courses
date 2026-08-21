package lab8;
//Eralp Yigit Boz 22403188
//Cs 101 Lab 08
public class aStarRev extends aEntityRev {

    public aStarRev(int xCrd, int yCrd) {
        super(xCrd, yCrd, '*');


    }

    public void update() {
        
        this.y++;
    }
}