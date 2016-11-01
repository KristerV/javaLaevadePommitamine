import java.util.Arrays;

/*
 * Laeval on koordinaadid [x, y, pihtas]. Viimane "pihtas" märgib ära, kas antud
 * ruut on pihta saanud või mitte. Massiivi luues on kõik väärtused 0, seega eraldi
 * määrama ei pea.
 */
public class Laev {
    private int[][] koordinaadid; // formaat on [[x, y, pihtas],[x, y, pihtas],[x, y, pihtas]]

    // Genereeri laev suvalistele koordinaatidele, aga mitte lauast välja
    public Laev(int kohaline, int maxPos) {

        // Paneme laeva pikkuse paika. [3] on [x, y, pihtas]
        koordinaadid = new int[kohaline][3];

        // Genereeri suvaline alguskoht laevale
        koordinaadid[0][0] = Math.min((int) (Math.random() * maxPos), maxPos-kohaline);
        koordinaadid[0][1] = Math.min((int) (Math.random() * maxPos), maxPos-kohaline);

        // Kas laeva pikkus läheb horisontaalselt või vertikaalselt?
        int paremale = (int) Math.round(Math.random());
        int alla = paremale == 1 ? 0 : 1;

        // Genereeri ülejäänud koordinaadid ehk laeva pikkus.
        for (int i = 1; i < koordinaadid.length; i++) {
            koordinaadid[i][0] = koordinaadid[i-1][0] + paremale;
            koordinaadid[i][1] = koordinaadid[i-1][1] + alla;
        }

        // Lihtsalt infoks konsooli
        System.out.println("Uus laev: " + Arrays.deepToString(koordinaadid));
    }

    // Kas käesolev laev jagab pinda teise laevaga
    public boolean isLahendal(Laev teine) {

        // teise laeva koordinaadid
        int[][] koord2 = teine.getKoordinaadid();

        // Võrdle mõlema laeva igat ruutu omavahel
        for (int i = 0; i < koord2.length; i++) {                     // iga laev2 ruut
            for (int j = 0; j < koordinaadid.length; j++) {           // iga laev1 ruut
                /*
                 * Järgnevad kaks tsüklit on selleks, et kontrollida ruutu ümbritsevat kaheksat ruutu.
                 * Positsioonid relatiivselt ruudule on sellised:
                 *
                 * [-1 -1] [0 -1] [1 -1]
                 * [-1  0] [0  0] [1  0]
                 * [-1  1] [0  1] [1  1]
                 *
                 * Ehk iga ruudu kohta on tegelt 9 kontrolli.
                 */
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (koord2[i][0] == koordinaadid[j][0] + k && koord2[i][1] == koordinaadid[j][1] + l) {
                            System.out.println("Kattumine: " + Arrays.toString(koord2[i]));
                            return true; // Jah on vähemalt üks kattuvus
                        }
                    }
                }
            }
        }
        return false; // ühtegi kattuvust ei leitud
    }

    // Tagastab laeva koordinaadid. Miks mitte teha koordinaadid publicuks? Sest
    // me ei taha, et mingi kood seda otse muuta saaks.
    public int[][] getKoordinaadid() {
        return koordinaadid;
    }

    // Laeva tulistati, kas sai pihta.
    public boolean pihtas(int[] lask) {

        // Iga koordinaat mis laeval on...
        for (int i = 0; i < koordinaadid.length; i++) {

            // Kui kattub lasuga
            if (koordinaadid[i][0] == lask[0] && koordinaadid[i][1] == lask[1]) {
                koordinaadid[i][2] = 1;         // Siis märgista pihta saanuks
                System.out.println("Pihta");    // Prindi kasutajale infoks
                return true;                    // vasta "jah, sai pihta"
            }
        }
        return false;                           // Kui laevale ei saadud pihta
    }

    // Kui laeval on vastav koordinaat, siis tagasta koordinaat
    public int getPosSeis(int[] xy) {
        for (int i = 0; i < koordinaadid.length; i++) {
            if (koordinaadid[i][0] == xy[0] && koordinaadid[i][1] == xy[1])
                return koordinaadid[i][2];
        }
        return -1; // kui koordinaat puudub, vasta -1
    }

    // Kas laev on põhjas?
    public boolean isPohjas() {
        for (int i = 0; i < koordinaadid.length; i++) {
            if (koordinaadid[i][2] == 0)
                return false;
        }
        return true;
    }
}
