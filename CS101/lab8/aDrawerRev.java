package lab8;
//Eralp Yigit Boz 22403188
//Cs 101 Lab 08
import java.util.List;

public class aDrawerRev {

    public static void printControls() {
        System.out.println("Controls: 'A' to move Left, 'D' to move Right, 'S' to Stay, 'Q' to Quit.");
        System.out.println("Catch the stars ('*') with your basket ('U').");
        System.out.println("Missed stars cost health!\n");
    }

        public static void render(aBasketRev bsk, List<aStarRev> strs, List<aHealthStone> hlts, int scr) {
        System.out.println(); 
        
        int h = 6;
        int w = 20;
        char[][] grd = new char[h][w];

        for (int i = 0; i < h; i++) {

            
            for (int j = 0; j < w; j++) {
                grd[i][j] = ' ';
            }
        }

        for (int i = 0; i < strs.size(); i++) {
            aStarRev s = strs.get(i);
            int sX = s.getX();


            int sY = s.getY();

            if (sX >= 0 && sX < w && sY >= 0 && sY < h) {
                grd[sY][sX] = s.getSymbol();
            }
        }

        for (int i = 0; i < hlts.size(); i++) {
            aHealthStone hs = hlts.get(i);


            int hsX = hs.getX();

            int hsY = hs.getY();

            if (hsX >= 0 && hsX < w && hsY >= 0 && hsY < h) {


                grd[hsY][hsX] = hs.getSymbol();
            }
        }

        int bX = bsk.getX();

        int bY = bsk.getY();


        if (bX >= 0 && bX < w && bY >= 0 && bY < h) {


            grd[bY][bX] = bsk.getSymbol();

        }

        printField(grd);
        System.out.println("Score: " + scr + " | Health: " + bsk.getHealth());
    }

    public static void printField(char[][] grd) {
        int hhg= grd.length;
        int wwg = grd[0].length;

        int i = 0;
        while (i < wwg + 2) {
            System.out.print("=");
            i++;
        }
        System.out.println();

        int r = 0;
        while (r < hhg) {
            System.out.print("|");
            int c = 0;
            while (c < wwg) {
                System.out.print(grd[r][c]);
                c++;
            }
            System.out.println("|");
            r++;
        }

        int k = 0;


        while (k < wwg + 2) {
            System.out.print("=");
            k++;
        }
        System.out.println();
    }
}