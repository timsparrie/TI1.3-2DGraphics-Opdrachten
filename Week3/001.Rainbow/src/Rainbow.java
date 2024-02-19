import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Rainbow extends Application {
    private ResizableCanvas canvas;
    Font font;
    ArrayList<Shape> shapes = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        font = new Font("Arial", Font.BOLD, 60);
        mainPane.setCenter(canvas);
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Rainbow");
        stage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));


    }


    public void draw(FXGraphics2D graphics)
    {
        double rotate = 0.36;
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        Shape shapeR = font.createGlyphVector(graphics.getFontRenderContext(), "R").getOutline();
        Shape shapeE = font.createGlyphVector(graphics.getFontRenderContext(), "E").getOutline();
        Shape shapeG = font.createGlyphVector(graphics.getFontRenderContext(), "G").getOutline();
        Shape shapeN = font.createGlyphVector(graphics.getFontRenderContext(), "N").getOutline();
        Shape shapeB = font.createGlyphVector(graphics.getFontRenderContext(), "B").getOutline();
        Shape shapeO = font.createGlyphVector(graphics.getFontRenderContext(), "O").getOutline();


        AffineTransform tx = new AffineTransform();
        tx.translate(150,150);
        tx.rotate(30);
        Shape newShapeR = tx.createTransformedShape(shapeR);
        graphics.setColor(Color.ORANGE);
        graphics.fill(newShapeR);
        graphics.setColor(Color.RED);

        tx.translate(30, 0);
        tx.rotate(rotate);
        Shape newShapeE = tx.createTransformedShape(shapeE);
        graphics.fill(newShapeE);
        graphics.setColor(Color.YELLOW);

        tx.translate(30, 0);
        tx.rotate(rotate);
        Shape newShapeG = tx.createTransformedShape(shapeG);
        graphics.fill(newShapeG);
        graphics.setColor(Color.GREEN);


        tx.translate(30, 0);
        tx.rotate(rotate);
        newShapeE = tx.createTransformedShape(shapeE);
        graphics.fill(newShapeE);
        graphics.setColor(Color.CYAN);


        tx.translate(30, 0);
        tx.rotate(rotate);
        Shape newShapeN = tx.createTransformedShape(shapeN);
        graphics.fill(newShapeN);
        graphics.setColor(Color.BLUE);


        tx.translate(30, 0);
        tx.rotate(rotate);
        Shape newShapeB = tx.createTransformedShape(shapeB);
        graphics.fill(newShapeB);
        graphics.setColor(Color.pink);


        tx.translate(30, 0);
        tx.rotate(rotate);
        Shape newShapeO = tx.createTransformedShape(shapeO);
        graphics.fill(newShapeO);
        graphics.setColor(Color.MAGENTA);


        tx.translate(30, 0);
        tx.rotate(rotate);
        newShapeO = tx.createTransformedShape(shapeO);
        graphics.fill(newShapeO);
        graphics.setColor(Color.RED);


        tx.translate(30, 0);
        tx.rotate(rotate);
        newShapeG = tx.createTransformedShape(shapeG);
        graphics.fill(newShapeG);



    }
    public static void regenboog(ArrayList<Shape> shapes) {


    }


    public static void main(String[] args)
    {
        launch(Rainbow.class);
    }

}
