import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Colors extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Colors");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        graphics.fill( new Rectangle2D.Double(20, 20, 50, 50));
        graphics.setColor(Color.pink);
        graphics.fill(new Rectangle2D.Double(70, 70, 50, 50));
        graphics.setColor(Color.black);
        graphics.fill(new Rectangle2D.Double(120, 70, 50, 50));
        graphics.setColor( Color.blue);
        graphics.fill(new Rectangle2D.Double(70, 120, 50, 50));
        graphics.setColor(Color.gray);
        graphics.fill(new Rectangle2D.Double(120, 120, 50, 50));
        graphics.setColor(Color.green);
        graphics.fill(new Rectangle2D.Double(70, 20, 50, 50));
        graphics.setColor(Color.red);
        graphics.fill(new Rectangle2D.Double(120, 20, 50, 50));
        graphics.setColor(Color.MAGENTA);
        graphics.fill(new Rectangle2D.Double(20, 70, 50, 50));
        graphics.setColor(Color.orange);
        graphics.fill(new Rectangle2D.Double(20, 120, 50, 50));
        graphics.setColor(Color.YELLOW);
        graphics.fill(new Rectangle2D.Double(170, 70, 50, 50));
        graphics.setColor(Color.lightGray);
        graphics.fill(new Rectangle2D.Double(170, 20, 50, 50));
        graphics.setColor(Color.darkGray);
        graphics.fill(new Rectangle2D.Double(170, 120, 50, 50));
        graphics.setColor(Color.cyan);


    }

    public void square() {

    }

    public static void main(String[] args) {
        launch(Colors.class);
    }

}
