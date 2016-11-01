import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Laevastik {
    ArrayList<Laev> laevad = new ArrayList();

    public Laevastik(int mitu, int maxPos) {

        int maxTsykleid = mitu * 20;
        int loendur = 0;

        // Genereerime nii mitu laeva kui kästud
        for (int i = 0; i < mitu; i++) {

            // Uus laev
            int laevaPikkus = Math.max(5 - i, 1);       // Maksimaalselt 5 ruutu, alusta suuremast
            Laev laev = new Laev(laevaPikkus, maxPos);

            // Kas laev kattub mõne eelnevaga?
            boolean yksi = true;
            for (int j = 0; j < laevad.size(); j++) {
                yksi = yksi && !laev.isLahendal(laevad.get(j));
            }

            // Kui laev ei kattu, siis lisa lauale
            if (yksi)
                laevad.add(laev);
            // Kui kattub, siis unusta see laev ära ja tekita uus
            else {
                i--;
            }

            // Igavese tsükli kaitse
            if (loendur > maxTsykleid)
                break;
            else
                loendur++;
        }
    }

    // Kasutaja klikkis, nüüd teata kõiki laevu
    public void lask(int[] koordinaadid) {
        for (int i = 0; i < laevad.size(); i++) {
            boolean pihtas = laevad.get(i).pihtas(koordinaadid);
            if (pihtas)                            // Kui leidsime pihta saanud laeva...
                return;                            // lõpetame otsimise.
        }
        System.out.println("Mööda");               // Kui polnudki pihta saajat.
    }

    public Color getPosColor(int i, int j) {

        // Küsi iga laeva käest, et leida kes siin ruudul on.
        for (int k = 0; k < laevad.size(); k++) {
            Laev laev = laevad.get(k);                   // Leia järgmine laev
            int seis = laev.getPosSeis(new int[]{i, j}); // mis seis sellel ruudul on?
            if (seis == 0)                               // 0 tähendab elus laeva ruutu
                return Color.DARKBLUE;
            else if (seis == 1)                          // 1 on pihta saanud laeva ruut
                return Color.RED;
        }
        return Color.BLUE;                               // ruutu ei leitudki ühegi laeva pealt
    }

    public boolean isGameOver() {
        for (Laev laev : laevad) {
            if (!laev.isPohjas())
                return false;
        }
        return true;
    }
}
