import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Line2D;

public class Spiral extends Application {
    private Canvas canvas;
    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Spiral");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale( 1, -1);
        graphics.drawLine(-1000,0,1000,0);
        graphics.drawLine(0,-1000,0,1000);

        double b = 0.5;
        double lastX = 0;
        double lastY = 0;
        double scale = 50;

        for (double i = 0; i < 100; i+=0.1) {
            double radius = b * i;
            double x = radius * Math.cos(i);
            double y = radius * Math.sin(i);
            graphics.draw(new Line2D.Double(x*scale,y*scale,lastX*scale,lastY*scale));
            lastX = x;
            lastY = y;


        }

//        for (double n = 0; n < 0.3; n+= 0.1) {
//            double r = ;
//            double formule = n * r;
//            float x = (float) (r * Math.cos(formule));
//            float y = (float) (r * Math.sin(formule));
//            System.out.println(x);
//            System.out.println(y);
//            graphics.draw(new Line2D.Double(x, y, lastX, lastY));
//            lastX = x;
//            lastY = y;
//        }
    }
    
    
    
    public static void main(String[] args) {
        launch(Spiral.class);
    }

}
