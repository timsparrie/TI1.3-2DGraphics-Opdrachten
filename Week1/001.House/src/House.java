import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Line2D;

public class House extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1024, 768);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("House");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {
        graphics.draw(new Line2D.Double(100,300,100,600));
        graphics.draw(new Line2D.Double(400,300,400,600));
        graphics.draw(new Line2D.Double(100,600,400,600));
        graphics.draw(new Line2D.Double(100,300,250,150));
        graphics.draw(new Line2D.Double(400,300,250,150));
        graphics.draw(new Line2D.Double(130,600,130,500));
        graphics.draw(new Line2D.Double(200,600,200,500));
        graphics.draw(new Line2D.Double(130,500,200,500));
    }



    public static void main(String[] args) {
        launch(House.class);
    }

}
