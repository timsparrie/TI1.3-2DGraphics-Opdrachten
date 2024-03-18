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
import java.io.IOException;

public class FadingImage extends Application {
    private ResizableCanvas canvas;
    private BufferedImage image1;
    private BufferedImage image2;
    private float alpha;
    private boolean state = true;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        alpha = 0f;

        try {
            image1 = ImageIO.read(getClass().getResource("/halo.png"));
            image2 = ImageIO.read(getClass().getResource("/old.png"));
            System.out.println("succes?");
        } catch (IOException e) {
            System.out.println("very much no succes");
            throw new RuntimeException(e);
        }

        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
		if(last == -1)
                    last = now;
		update((now - last) / 1000000000.0);
		last = now;
		draw(g2d);
            }
        }.start();
        
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Fading image");
        stage.show();
        draw(g2d);
    }
    
    
    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int)canvas.getWidth(), (int)canvas.getHeight());
        graphics.drawImage(image1, 0,0,Color.black, null);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        graphics.drawImage(image2, 0,0,Color.black, null);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    

    public void update(double deltaTime) {
        deltaTime /=5;
        if (alpha + deltaTime > 1)
            state = false;
        if (alpha - deltaTime < 0)
            state = true;
        if (state)
            alpha += (float) deltaTime;
        else
            alpha -= (float) deltaTime;
	
    }

    public static void main(String[] args) {
        launch(FadingImage.class);
    }

}
