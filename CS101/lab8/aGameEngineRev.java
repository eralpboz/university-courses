package lab8;

//Eralp Yigit Boz 22403188
//Cs 101 Lab 08
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class aGameEngineRev {

    private List<aStarRev> strs;private List<aHealthStone> hlts;

    private final int HEIGHT = 6;
    private final int BASKET_HEALTH = 3;
    private final int WIDTH = 20;
    private aGameFieldRev fld;
    private aBasketRev bsk;
    private Scanner in;
    
    

    public void run() {
        System.out.println("Welcome to Star Catcher!");


        aDrawerRev.printControls();



        boolean isPlay = true;

        while (isPlay && this.bsk.getHealth() > 0) {
            aDrawerRev.render(this.bsk, this.strs, this.hlts, this.fld.getScore());

            System.out.print("Enter command: ");
            String input = this.in.next();


            char cmd = input.toUpperCase().charAt(0);

            if (cmd == 'Q') {
                isPlay = false;
            } else {


                update(cmd);
            }
        }

        if (this.bsk.getHealth() <= 0) {
            aDrawerRev.render(this.bsk, this.strs, this.hlts, this.fld.getScore());
            System.out.println("Game Over! ");

            System.out.print(" You ran out of health.");
            System.out.println("Final Score:  " + this.fld.getScore());
        }
    }

    public void update(char cmd) {
        if (cmd == 'A') {
            this.bsk.moveLeft();


        } else if (cmd == 'D') {
            this.bsk.moveRight();
        }

        for (aStarRev x : this.strs) {
            x.update();
        }

        for (aHealthStone x : this.hlts) {
            x.update();
            ;
        }

        this.fld.checkCollisions();

        Random rnd = new Random();
        int posibility = rnd.nextInt(100);
        if (posibility < 30 && posibility > 10) {
            this.fld.spawnStar();
        }
        if (posibility < 10) {
            this.fld.spawnHealtStone();
        }
    }

    public aGameEngineRev() {
        this.strs = new ArrayList<>();


        this.hlts = new ArrayList<>();
        this.bsk = new aBasketRev(WIDTH / 2, HEIGHT - 1, BASKET_HEALTH);
        this.fld = new aGameFieldRev(this.bsk, this.strs, this.hlts);

        
        this.in = new Scanner(System.in);
    }

}