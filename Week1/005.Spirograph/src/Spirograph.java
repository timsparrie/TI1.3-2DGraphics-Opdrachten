import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.Random;

public class Spirograph extends Application {
    private TextField v1;
    private TextField v2;
    private TextField v3;
    private TextField v4;
    private Canvas canvas;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(1920, 1080);
       
        VBox mainBox = new VBox();
        HBox topBar = new HBox();
        mainBox.getChildren().add(topBar);
        mainBox.getChildren().add(new Group(canvas));
        
        topBar.getChildren().add(v1 = new TextField("300"));
        topBar.getChildren().add(v2 = new TextField("1"));
        topBar.getChildren().add(v3 = new TextField("462"));
        topBar.getChildren().add(v4 = new TextField("10"));
                
        v1.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v2.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v3.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v4.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(mainBox));
        primaryStage.setTitle("Spirograph");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        //you can use Double.parseDouble(v1.getText()) to get a double value from the first textfield
        //feel free to add more textfields or other controls if needed, but beware that swing components might clash in naming
        AffineTransform tx = new AffineTransform();
        graphics.setTransform(tx);
        graphics.setBackground(Color.white);
        graphics.clearRect(0,0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.setColor(Color.black);

        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale( 1, -1);
        graphics.drawLine(-1000,0,1000,0);
        graphics.drawLine(0,-1000,0,1000);
        double lastX = 0;
        double lastY = 0;
        System.out.println(v1.getText().isEmpty());
        if (!v1.getText().isEmpty() && !v2.getText().isEmpty() && !v3.getText().isEmpty() && !v4.getText().isEmpty()) {

            for (double n = 0; n < Double.parseDouble(v1.getText()); n+= Double.parseDouble(v2.getText())/Double.parseDouble(v4.getText())) {
                Random random = new Random();
                int red = random.nextInt(256);
                int green = random.nextInt(256);
                int blue = random.nextInt(256);
                Color randomColor = new Color(red, green, blue);
                double r = Double.parseDouble(v3.getText());
                double formule = n * r;
                float x = (float) (r * Math.cos(formule));
                float y = (float) (r * Math.sin(formule));
                graphics.draw(new Line2D.Double(x, y, lastX, lastY));
                lastX = x;
                lastY = y;
                graphics.setColor(randomColor);
            }
        }

//        for (double i = 0; i < Double.parseDouble(v3.getText()); i += 0.5) {
//            for (double j = 0; j < Double.parseDouble(v3.getText()); j += 0.5) {
//
//                double radius = b * i + Double.parseDouble(v4.getText());
//                ;
//                double x = radius * Math.tan(i);
//                double y = radius * Math.sin(i);
//                graphics.draw(new Line2D.Double(x+j * scale, y * scale, lastX * scale, lastY+j * scale));
//                lastX = x;
//                lastY = y;
//            }
//
//        }
    }
    
    
    
    public static void main(String[] args) {
        launch(Spirograph.class);
    }

}
