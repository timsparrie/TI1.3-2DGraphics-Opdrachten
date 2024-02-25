import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Line2D;

public class Graph extends Application {

    Canvas canvas;
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Graph");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {

        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale( 1, -1);
        graphics.setColor(Color.BLACK);
        graphics.drawLine(-1000,0,1000,0);
        graphics.drawLine(0,-1000,0,1000);
        graphics.setColor(Color.red);

        double resolution = 0.1;
        double scale = 50.0;
        double lastY = Math.sin(-10);

        for(double x = -10; x < 10; x += resolution)
        {
            float y = (float)Math.sin(x);
            graphics.draw(new Line2D.Double(x*scale, y*scale, (x-resolution)*scale, lastY*scale));
            lastY = y;
        }
    }




    public static void main(String[] args) {
        launch(Graph.class);
    }

}
