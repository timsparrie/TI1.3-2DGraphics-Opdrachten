import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Eindopdracht extends Application {
    private ResizableCanvas canvas;

    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane));
        stage.setTitle("EindOpdracht");
        canvas.resize(1400, 900 );

        stage.show();
        draw(g2d);

    }

    private void update(double deltaTime) {
    }

    private void draw(FXGraphics2D g2d) {

        g2d.setTransform(new AffineTransform());
        g2d.setBackground(Color.white);
        g2d.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
    }
    public static void main(String[] args) {
        launch(Eindopdracht.class);
    }
}
