import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Meri {
    private GridPane grid = new GridPane();
    private int lauaLaiusPx = 600;
    private int lauaLaiusLaevades = 9;
    private double laevaLaius = lauaLaiusPx / lauaLaiusLaevades;
    private Laevastik laevad;
    private Scene scene = new Scene(grid, lauaLaiusPx, lauaLaiusPx);

    // Konstruktor, ehk esimene asi mis juhtub kui Objekt luuakse.
    public Meri(int mituLaeva) {
        startStage();                            // kogu stagemise krempel
        laevad = new Laevastik(mituLaeva, lauaLaiusLaevades); // loo laevad
        renderdaLaevastik();                     // Kuva laevad lauale
        setClickEvent();                         // Salvesta click event
    }

    private void startStage() {
        Stage stage = new Stage();
        scene.setFill(Color.BLUE);
        stage.setScene(scene);
        stage.show();
    }

    // Võta laevastik ja kuva laevad pildil
    private void renderdaLaevastik() {

        // Tühjenda olemasolevad laevad
        grid.getChildren().clear();
        for (int i = 0; i < lauaLaiusLaevades; i++) {
            for (int j = 0; j < lauaLaiusLaevades; j++) {

                // Laevastik objekt tagastab kohe värvi vastavalt mis seis
                // laeval parasjagu on
                Color seis = laevad.getPosColor(i, j);

                // Joonista ruut
                Rectangle rect = new Rectangle(laevaLaius, laevaLaius, seis);
                grid.add(rect, i, j);
            }
        }
    }

    // Reageeri klikile
    private void setClickEvent() {

        grid.setOnMouseClicked(event -> {

            // event.getTarget() saab õige laeva rectangli kätte,
            // aga kuna Java ei tea mis tüüpi objekt sealt tuleb, siis
            // pean talle kinnitama, et see on kindlasti "(Rectangle)".
            Rectangle rect = (Rectangle) event.getTarget();

            // Mis on x-y koordinaadid?
            int x = GridPane.getColumnIndex(rect);
            int y = GridPane.getRowIndex(rect);

            // Registreeri lask laevale
            laevad.lask(new int[]{x, y});

            // Mängu lõpp
            if (laevad.isGameOver())
                manguLopp();
            else
                renderdaLaevastik(); // Renderda uus laua seis

        });
    }

    private void manguLopp() {
        StackPane stack = new StackPane();
        Label teade = new Label("Võitsid!");
        teade.setFont(Font.font("Cambria", 32));
        stack.getChildren().add(teade);
        scene.setRoot(stack);
    }

}
