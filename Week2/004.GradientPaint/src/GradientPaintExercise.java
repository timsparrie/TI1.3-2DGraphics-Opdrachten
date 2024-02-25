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
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GradientPaintExercise extends Application {
    private ResizableCanvas canvas;
    private double mouseY;
    private double mouseX;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mouseX = canvas.getWidth()/2;
        mouseY = canvas.getHeight()/2;
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


        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("GradientPaint");
        primaryStage.show();
        draw(g2d);


    }

    private void setCenter(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
    public void update(double deltaTime) {

        canvas.setOnMouseDragged(e -> setCenter(e));

    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        Point2D point2D = new Point2D.Double((int) canvas.getWidth()/2, (int) canvas.getHeight()/2);

        float[] dist = {0.0f, 0.3f, 1.0f};
        Color[] colors = {Color.magenta, Color.pink, Color.white};
        Point2D mousePoint2D = new Point2D.Double(mouseX, mouseY);
        float radius = 250;
        RadialGradientPaint paint = new RadialGradientPaint(point2D, radius, mousePoint2D, dist, colors, MultipleGradientPaint.CycleMethod.REFLECT);
        graphics.setPaint(paint);

        graphics.draw(new Rectangle2D.Double(0,0, (int) canvas.getWidth(), (int) canvas.getHeight()));
    }


    public static void main(String[] args)
    {
        launch(GradientPaintExercise.class);
    }

}
