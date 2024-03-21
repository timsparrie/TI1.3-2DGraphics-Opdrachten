import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class MovingCharacter extends Application {
    private ResizableCanvas canvas;
    private ResizableCanvas canvas2;
    int i = 32;
    int xImage = 0;
    private int k = 0;
    private boolean clicked = false;

    @Override
    public void start(Stage stage) throws Exception
    {
        HelloImage();
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
        stage.setTitle("Moving Character");
        stage.show();
        draw(g2d);
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.drawImage(tiles[i], xImage,250, null);

    }


    public void update(double deltaTime)
    {

        canvas.setOnMousePressed(e -> clicked = true);
        if (!clicked) {
            if (i >= 40) {
                i = 32;
            }
        } else {
            if (i < 40)
                i = 40;
        }
        if (i > 48) {
            i= 32;
            clicked = false;
        }
            k++;
        if (k >= 10) { // k = timer for how often it goes to next picture
            i++;
            k = 0;
        }
        xImage++;
        if (xImage >= (int) canvas.getWidth())
            xImage = 0;


    }
    private BufferedImage[] tiles;
    public void HelloImage() {
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/images/sprite.png"));
            tiles = new BufferedImage[65];
            for(int i = 0; i < 65; i++)
                tiles[i] = image.getSubimage(64 * (i%8), 64 * (i/8), 64, 64);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args)
    {
        launch(MovingCharacter.class);
    }

}
