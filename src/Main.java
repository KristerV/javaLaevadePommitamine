import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    // Klassi küljes muutujad, mida saab igast meetodist kätte
    GridPane laud;                                              // Sisu määran seadistaLava() meetodis
    int lauaPikkusLaevades = 4;                                 // Mitu laeva on laual kõrvuti
    int laevaPikkusPx = 50;                                     // Mitu pikslit on üks laev
    Stage mainGameStage;                                        // Mängu aken
    Image laevaPilt = new Image("pirate.png");                  // Laeva pilt
    ImagePattern laevaMuster = new ImagePattern(laevaPilt);     // Ja pildi musted, et saaksin ruut.setFill() kasutada

    // Kust programm algab
    @Override
    public void start(Stage primaryStage) throws Exception {
        seadistaLava();
        sisestaLaevad();
        reageeriKlikile();
    }

    // Määrab mis juhtub, kui lauale klikitakse
    private void reageeriKlikile() {
        laud.setOnMouseClicked(event -> {                       // Juhtum. Ehk klikile reageerimine.
            Rectangle ruut = (Rectangle) event.getTarget();     // getTarget saab laualt Rectangle kätte
            String tyyp = ruut.getId();                         // Mis on ruudu ID?
            if (tyyp.equals("meri")) {                          // Kui ruut on meri, siis
                ruut.setFill(Color.DARKBLUE);                   // värvi lihtsalt tumedamaks
            } else if (tyyp.equals("laev")) {                   // Kui ruut on laev, siis
                ruut.setFill(laevaMuster);                      // näita laeva pilti ja
                ruut.setId("põhjas");                           // muuda ID ära
            }

            if (!laevasidOnAlles()) {                           // Kui laevasid alles ei ole, siis
                gameover();                                     // Käivita gameover meetod
            }
        });
    }

    // Mis juhtub, kui mäng on läbi
    private void gameover() {
        mainGameStage.close();                                  // Pane mängu aken kinni
        StackPane stack = new StackPane();                      // Loo uus aken teatega
        Label go = new Label("Võitsid!");
        stack.getChildren().add(go);
        Scene scene = new Scene(stack, 300, 150);
        Stage goStage = new Stage();
        goStage.setScene(scene);
        goStage.show();                                         // ja näita seda akent.
    }

    // Kas laual on vähemalt üks laev veel alles?
    private boolean laevasidOnAlles() {
        for (Node ruut : laud.getChildren()) {                  // iga laua lapse kohta võta välja ruut
            if (ruut.getId().equals("laev")) {                  // kui ruudu ID on laev, siis
                return true;                                    // tagasta "jah"
            }
        }
        return false;                                           // Kui ühtegi laeva ei ole, tagasta "ei"
    }

    // Aseta tühjale GridPane lauale laevad
    private void sisestaLaevad() {
        for (int i = 0; i < lauaPikkusLaevades; i++) {          // Käime iga x-y koordinaadi laual läbi
            for (int j = 0; j < lauaPikkusLaevades; j++) {
                // Siia tulen 9 * 9 korda
                Rectangle ruut = new Rectangle(laevaPikkusPx, laevaPikkusPx); // Loon uue ruudu, ehk laev/meri
                int rand = (int) (Math.random() * 1.3);         // Tõenäosus, kas tuleb laev või meri
                if (rand == 1) {
                    ruut.setId("laev");                         // ID kasutajale ei näidata
                } else {                                        // aga koodis saan küsida selle väärtust
                    ruut.setId("meri");
                }
                ruut.setFill(Color.BLUE);                       // Värvin kogu laua esiteks siniseks
                laud.add(ruut, i, j);                           // Lisan ruutu GridPane sisse koordinaatidel i-j
            }
        }
    }

    // Seadista aken ja muud JavaFX vajalikud asjad
    private void seadistaLava() {
        laud = new GridPane();
        Scene scene = new Scene(laud, lauaPikkusLaevades * laevaPikkusPx, lauaPikkusLaevades * laevaPikkusPx);
        mainGameStage = new Stage();
        scene.setFill(Color.DARKBLUE);
        mainGameStage.setScene(scene);
        mainGameStage.show();
    }
}