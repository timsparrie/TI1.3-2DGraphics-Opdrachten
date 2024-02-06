import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class YingYang extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Ying Yang");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
//        Area backCircle = new Area(new Ellipse2D.Double(100, 100, 300, 300));
//        graphics.draw(backCircle);
        Area topCircle = new Area(new Ellipse2D.Double(240, 150, 20, 20));
        Area bottomCircle = new Area(new Ellipse2D.Double(240, 320, 20, 20));
        graphics.fill(bottomCircle);
        graphics.draw(topCircle);
        graphics.draw(bottomCircle);
        GeneralPath generalPath = new GeneralPath();
        GeneralPath generalPathBlack = new GeneralPath();
        GeneralPath generalPathBlackHalf = new GeneralPath();
        GeneralPath generalPathHalf = new GeneralPath();
        generalPathHalf.moveTo(250,400);
        generalPathHalf.curveTo(460,400, 460, 100, 250,100);
        generalPathBlackHalf.moveTo(250,100);
        generalPathBlackHalf.curveTo(40,100, 40, 400, 250,400);

        generalPath.moveTo(250, 250);
        generalPath.curveTo(120,250, 120, 400, 250,400);
        generalPathBlack.moveTo(250, 250);
        generalPathBlack.curveTo(380,250, 380, 100, 250,100);

        graphics.fill(generalPathBlackHalf);
//        graphics.setColor(Color.white);
        graphics.draw(generalPathHalf);

        graphics.fill(generalPathBlack);
        graphics.setColor(Color.white);
        graphics.fill(topCircle);
        graphics.fill(generalPath);
        graphics.setColor(Color.BLACK);
        graphics.fill(bottomCircle);
    }


    public static void main(String[] args)
    {
        launch(YingYang.class);
    }

}
