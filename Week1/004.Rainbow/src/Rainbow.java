import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;

public class Rainbow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Rainbow");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        int graphX = 0;
        int graphY=0;
        int graphWidth = 1920;
        graphics.setColor(Color.magenta);
        graphics.fillArc(graphX,graphY,graphWidth,2160,0,180);

        graphX += 50;
        graphY += 50;
        graphWidth -= 100;
        graphics.setColor(Color.cyan);
        graphics.fillArc(graphX,graphY,graphWidth,2160,0,180);

        graphX += 50;
        graphY += 50;
        graphWidth -= 100;
        graphics.setColor(Color.blue);
        graphics.fillArc(graphX,graphY,graphWidth,2160,0,180);

        graphX += 50;
        graphY += 50;
        graphWidth -= 100;
        graphics.setColor(Color.green);
        graphics.fillArc(graphX,graphY,graphWidth,2160,0,180);

        graphX += 50;
        graphY += 50;
        graphWidth -= 100;
        graphics.setColor(Color.YELLOW);
        graphics.fillArc(graphX,graphY,graphWidth,2160,0,180);

        graphX += 50;
        graphY += 50;
        graphWidth -= 100;
        graphics.setColor(Color.orange);
        graphics.fillArc(graphX,graphY,graphWidth,2160,0,180);

        graphX += 50;
        graphY += 50;
        graphWidth -= 100;
        graphics.setColor(Color.red);
        graphics.fillArc(graphX,graphY,graphWidth,2160,0,180);

        graphX += 50;
        graphY += 50;
        graphWidth -= 100;
        graphics.setColor(Color.white);
        graphics.fillArc(graphX,graphY,graphWidth,2160,0,180);


    }
    
    
    
    public static void main(String[] args) {
        launch(Rainbow.class);
    }

}
