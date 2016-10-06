import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        System.out.println("Alustame pommitamisega!");
        System.out.println("Autor: Krister");
        System.out.println("Aasta: 2016");

        // Laua algseis ja mängijale nähtav osa
        int[][] lauaAlgseis = new int[9][9];
        int[][] lauaSeis = new int[9][9];

        // 0 - meri
        // 1 - laev
        // 2- pihta saanud laev

        // Sisesta lauale 1x1 laevasid
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                lauaAlgseis[i][j] = (int) (Math.random() * 1.05);
            }
        }

        // Näita mängijale lauda
        prindiLaud(lauaSeis);

        // Uus Scanner objekt, hakkame seda kasutama kasutajalt sisendi saamiseks
        Scanner sc = new Scanner(System.in);

        // Mängi enda tsükkel. Töötab nii kaua kuni näeb break; käsku.
        while (true) {
            System.out.println("Kuhu tahad pommitada? Formaat: x-y");
            String input = sc.nextLine();         // Küsi sisendit kasutajalt
            String[] xy = input.split("-");       // poolita x-y string kaheks
            int x = Integer.parseInt(xy[0]) - 1;  // muuda string int'iks
            int y = Integer.parseInt(xy[1]) - 1;

            int tabamus = lauaAlgseis[y][x];      // Küsi x-y positsioonilt number
            if (tabamus == 1) {                   // Tuleb välja, et nr 1 oli seal
                lauaAlgseis[y][x] = 2;            // Salvesta tabamus lauale
                lauaSeis[y][x] = 2;               // Ja et kasutaja ka näeks tabamust
                System.out.println("Pihtas!");    // Et mängijaga näeks inimese keeles ka mis juhtus.
            } else if (tabamus == 0) {            // Juhul kui oli hoopis 0 sellel positsioonil.
                lauaSeis[y][x] = 3;               // 3 on mööda. Seda näitame kasutajale, taustlauale pole vaja märkida
                System.out.println("Mööda!");
            } else if (tabamus == 2) {
                System.out.println("Siia sa juba lasid.");
            } else {
                System.out.println("ERROR: kuidas sa üldse siia said?");
            }

            prindiLaud(lauaSeis);                 // Iga käik prindi ka laud välja

            boolean labi = kasOnMangLabi(lauaAlgseis); // Käivita meetod, mis vaatab laua üle ja ütleb kas laevu veel on.
            if (labi == true) {                   // Kui laevasid ei ole, siis
                break;                            // läheb tsükkel katki ja...
            }
        }
        System.out.println("Mäng on läbi!");      // programm saabki otsa

    }

    // private tähendab, et see meetod on kättesaadav ainult siit failist.
    // static tähendab, et meetodi välja kutsumiseks ei pea eraldi Objekti looma.
    // boolean tähendab, et meetod peab lõpuks return'ima booleani tulemuseks
    // meetod võtab sisendiks maatriksi - ja me nimetame selle maatriksi "laud" muutujaks.
    private static boolean kasOnMangLabi(int[][] laud) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (laud[j][i] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void prindiLaud(int[][] prinditavLaud) {
        for (int i = 0; i < 9; i++) {
            System.out.println(Arrays.toString(prinditavLaud[i]));
        }
    }
}
