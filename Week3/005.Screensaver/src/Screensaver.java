import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Screensaver extends Application {
    private ResizableCanvas canvas;
    private double timer;
    private ArrayList<Point2D> points1 = new ArrayList<>();
    private ArrayList<Point2D> points2 = new ArrayList<>();
    private ArrayList<Point2D> points3 = new ArrayList<>();
    private ArrayList<Point2D> points4 = new ArrayList<>();
    private Point2D.Double point1;
    private Point2D.Double point2;
    private Point2D.Double point3;
    private Point2D.Double point4;
    private double animationSpeed = 1/5; // lower is faster
    private int distance = 3; // distance between lines
    private int loopAmount = 50; // how many lines get saved
    private int directionXPoint1 = -distance;
    private int directionYPoint1 = -distance;
    private int directionXPoint2 = distance;
    private int directionYPoint2 = -distance;
    private int directionXPoint3 = -distance;
    private int directionYPoint3 = distance;
    private int directionXPoint4 = -distance;
    private int directionYPoint4 = -distance;

    @Override
    public void start(Stage stage) throws Exception
    {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        canvas.resize(800,600);
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
        stage.setTitle("Screensaver");
        stage.show();
        draw(g2d);
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.BLACK);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.setColor(new Color(65,253,254));

        for (int i = 0; i < points1.size()-1; i++) {
            graphics.drawLine((int) points1.get(i).getX(), (int) points1.get(i).getY(), (int) points2.get(i).getX(), (int) points2.get(i).getY());
            graphics.drawLine((int) points2.get(i).getX(), (int) points2.get(i).getY(), (int) points3.get(i).getX(), (int) points3.get(i).getY());
            graphics.drawLine((int) points3.get(i).getX(), (int) points3.get(i).getY(), (int) points4.get(i).getX(), (int) points4.get(i).getY());
            graphics.drawLine((int) points4.get(i).getX(), (int) points4.get(i).getY(), (int) points1.get(i).getX(), (int) points1.get(i).getY());

        }

    }

    public void init()
    {
        point1 = new Point2D.Double( 150,100);
        points1.add(point1);

        point2 = new Point2D.Double( 100 + Math.random() * 300,300 + Math.random() * 100);
        points2.add(point2);

        point3 = new Point2D.Double( 100 + Math.random() * 200,100 + Math.random() * 200);
        points3.add(point3);

        point4 = new Point2D.Double( 0 + Math.random() * 100,0 + Math.random() * 100);
        points4.add(point4);

    }

    public void update(double deltaTime) {
        timer += deltaTime;
        if (timer >= animationSpeed) {
            timer -= animationSpeed;

            directionXPoint1 = XUpdate(point1, directionXPoint1);
            directionYPoint1 = YUpdate(point1, directionYPoint1);
            point1 = new Point2D.Double(point1.getX()+directionXPoint1, point1.getY()+directionYPoint1);
            points1.add(point1);
            if (points1.size() > loopAmount)
                points1.remove(0);


            directionXPoint2 = XUpdate(point2, directionXPoint2);
            directionYPoint2 = YUpdate(point2, directionYPoint2);
            point2 = new Point2D.Double(point2.getX()+directionXPoint2, point2.getY()+directionYPoint2);
            points2.add(point2);
            if (points2.size() > loopAmount)
                points2.remove(0);


            directionXPoint3 = XUpdate(point3, directionXPoint3);
            directionYPoint3 = YUpdate(point3, directionYPoint3);
            point3 = new Point2D.Double(point3.getX()+directionXPoint3, point3.getY()+directionYPoint3);
            points3.add(point3);
            if (points3.size() > loopAmount)
                points3.remove(0);


            directionXPoint4 = XUpdate(point4, directionXPoint4);
            directionYPoint4 = YUpdate(point4, directionYPoint4);
            point4 = new Point2D.Double(point4.getX()+directionXPoint4, point4.getY()+directionYPoint4);
            points4.add(point4);
            if (points4.size() > loopAmount)
                points4.remove(0);




        }

    }

    public int XUpdate (Point2D point, int direction) {

        if (point.getX() + direction > canvas.getWidth()) {
            return -distance;
        }
        if (point.getX() + direction < 0) {
            return distance;
        }

        return direction;

    }
    public int YUpdate(Point2D point, int direction) {


        if (point.getY() + direction > canvas.getHeight()) {
            return -distance;
        }
        if (point.getY() + direction < 0) {
            return distance;
        }

        return direction;
    }

    public static void main(String[] args)
    {
        launch(Screensaver.class);
    }

}
