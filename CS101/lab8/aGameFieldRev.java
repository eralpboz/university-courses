package lab8;

import java.util.Random;
import java.util.List;

//Eralp Yigit Boz 22403188
//Cs 101 Lab 08
public class aGameFieldRev {
    private List<aStarRev> strs;
    private aBasketRev bsk;
    private int scr;
    private List<aHealthStone> hlts;

    public void checkCollisions() {
        for (int i = this.strs.size() - 1; i >= 0; i--) {
            aStarRev s = this.strs.get(i);



            if (s.collidesWith(this.bsk)) {
                this.strs.remove(i);


                this.scr++;


            } else if (s.getY() >= this.bsk.getY()) {
                this.strs.remove(i);
                this.bsk.setHealth(this.bsk.getHealth() - 1);


                
            }
        }

        for (int kk = this.hlts.size() - 1; kk >= 0; kk--) {



            aHealthStone h = this.hlts.get(kk);

            if (h.collidesWith(this.bsk)) {
                this.hlts.remove(kk);


                this.bsk.setHealth(bsk.getHealth() + 1);
                System.out.println();


                System.out.println("*** You healed by one health point! ***");


            } else if (h.getY() >= this.bsk.getY()) {
                this.hlts.remove(kk);


            }
        }
    }

    public int getScore() {
        return this.scr;
    }

    public void spawnStar() {
        Random rnd = new Random();
        int rndXCor = rnd.nextInt(20);
        this.strs.add(new aStarRev(rndXCor, 0));
    }

    public void spawnHealtStone() {
        Random rnd = new Random();
        int rndXCor = rnd.nextInt(20);


        this.hlts.add(new aHealthStone(rndXCor, 0));
    }

    public aGameFieldRev(aBasketRev bsk, List<aStarRev> strs, List<aHealthStone> hlts) {
        this.bsk = bsk;
        this.strs = strs;

        
        this.scr = 0;
        this.hlts = hlts;
    }
}