import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Spotlight extends Application {
    private ResizableCanvas canvas;
    private int mouseX;
    private int mouseY;
    private Random r = new Random();
    private Shape shape;


    @Override
    public void start(Stage stage) throws Exception
    {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now)
            {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Spotlight");

        stage.show();
        draw(g2d);

    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.setClip(0,0, (int) canvas.getWidth(), (int) canvas.getHeight());

        shape = new Ellipse2D.Double(mouseX -100, mouseY-100, 200,200);
        graphics.draw(shape);
        graphics.clip(shape);
        for(int i = 0; i < 1000; i++) {
            graphics.setPaint(Color.getHSBColor(r.nextFloat(), 1, 1));
            graphics.drawLine((int) (r.nextInt() % canvas.getWidth()), (int) (r.nextInt() % canvas.getHeight()),
                    (int) (r.nextInt() % canvas.getWidth()), (int) (r.nextInt() % canvas.getHeight()));
        }


        graphics.setClip(0,0, (int) canvas.getWidth(), (int) canvas.getHeight());




    }

    public void update(double deltaTime) {
        canvas.setOnMouseMoved(e -> mouseLocation(e));
    }

    private void mouseLocation(MouseEvent e) {
        mouseX = (int) e.getX();
        mouseY = (int) e.getY();
    }

    public static void main(String[] args) {
        launch(Spotlight.class);
    }

}
