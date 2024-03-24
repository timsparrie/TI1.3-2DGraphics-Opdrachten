import com.sun.javafx.geom.Point2D;
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
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BlockDrag extends Application {
    ResizableCanvas canvas;
    private ArrayList<Renderable> renderables = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
//        init();
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Block Dragging");
        primaryStage.show();
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


        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));

        init();
        draw(g2d);
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        for (Renderable rendereble: renderables) {
            rendereble.draw(graphics);
        }

    }
    public void init() {
        renderables.add(new Renderable(new Rectangle2D.Double(-50,-50,100,100), new Point2D(134,343)));
        renderables.add(new Renderable(new Rectangle2D.Double(-50,-50,100,100), new Point2D(188,122)));
    }
    public void update (double deltatime) {

    }


    public static void main(String[] args)
    {
        launch(BlockDrag.class);
    }

    private void mousePressed(MouseEvent e) {


    }

    private void mouseReleased(MouseEvent e) {

    }

    private void mouseDragged(MouseEvent e) {
        boolean dragged = false;
        for (Renderable renderable: renderables) {
            if (renderable.inBlock(new Point2D((float) e.getX(), (float) e.getY())) && !dragged) {
                renderable.setPosition(new Point2D((float) e.getX(), (float) e.getY()));
                dragged = true;
            }

        }


    }

}
