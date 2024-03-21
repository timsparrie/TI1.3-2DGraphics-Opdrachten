import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Mirror extends Application {
    ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Mirror");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        AffineTransform affineTransform = new AffineTransform();
        graphics.setTransform(affineTransform);
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale(1,-1);
        graphics.drawLine((int) (-canvas.getWidth()/2), 0, (int) (canvas.getWidth()/2), 0);
        graphics.drawLine(0, (int) (-canvas.getHeight()/2), 0, (int) (canvas.getHeight()/2));
        int lastY = (int) (-canvas.getWidth()/2);

        for (int x = (int) (-canvas.getWidth()/2); x < canvas.getHeight()/2; x++) {
            int y = (int) (2.5*x);
            graphics.drawLine(x,y,x-1,lastY);
            lastY = y;
        }
        Area block = new Area(new Rectangle2D.Double(-50,100, 100,100));
        graphics.draw(block);
        AffineTransform tx = new AffineTransform(2/7.25-1,5/7.25,5/7.25,12.5/7.25-1 ,0,0 );
        block.transform(tx);
        graphics.draw(block);


    }


    public static void main(String[] args)
    {
        launch(Mirror.class);
    }

}
