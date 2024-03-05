import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.*;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class AngryBirds extends Application {

    private ResizableCanvas canvas;
    private World world;
    private MousePicker mousePicker;
    private Camera camera;
    private boolean debugSelected = true;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private Body angryBlackBird;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();

        // Add debug button
        javafx.scene.control.CheckBox showDebug = new CheckBox("Show debug");
        showDebug.setOnAction(e -> {
            debugSelected = showDebug.isSelected();
        });
        mainPane.setTop(showDebug);

        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        camera = new Camera(canvas, g -> draw(g), g2d);
        mousePicker = new MousePicker(canvas);

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane, 1920, 1080));
        stage.setTitle("Angry Birds");
        stage.show();
        draw(g2d);
    }

    public void init() {
        world = new World();
        world.setGravity(new Vector2(0, -9.8));

        Body background = new Body();
        gameObjects.add(new GameObject("/images/background.png", background, new Vector2(0,-60), 1.1));


        Body beam = new Body();
        BodyFixture beamFixture = new BodyFixture(new Rectangle(20,1));
        beam.addFixture(beamFixture);
        beam.getTransform().setTranslation(new Vector2(0,-4.7));
        beam.setMass(MassType.INFINITE);
        world.addBody(beam);

        Body catapult = new Body();
        BodyFixture catapultFixture = new BodyFixture(new Rectangle(0.3,1.1));
        catapult.setMass(MassType.INFINITE);
        catapult.addFixture(catapultFixture);
        catapult.getTransform().setTranslation(new Vector2(-8,-3.7));
        world.addBody(catapult);
        gameObjects.add(new GameObject("/images/catapult.png", catapult, new Vector2(-50,-200),.25));


        {
            Body box1 = new Body();
            BodyFixture boxFixture = new BodyFixture(new Rectangle(0.5,0.5));
            boxFixture.setRestitution(0.1);
            box1.getTransform().setTranslation((new Vector2(1.1, -3)));
            box1.addFixture(boxFixture);
            box1.setMass(MassType.NORMAL);
            world.addBody(box1);
            gameObjects.add(new GameObject("/images/block.png", box1, new Vector2(0,0), 0.5));

            Body box2 = new Body();
            BodyFixture box2Fixture = new BodyFixture(new Rectangle(0.5,0.5));
            box2.getTransform().setTranslation((new Vector2(1.4, -4)));
            box2Fixture.setRestitution(0.1);
            box2.addFixture(box2Fixture);
            box2.setMass(MassType.NORMAL);
            world.addBody(box2);
            gameObjects.add(new GameObject("/images/block.png", box2, new Vector2(0,0), 0.5));

            Body box3 = new Body();
            BodyFixture box3Fixture = new BodyFixture(new Rectangle(0.5, 0.5));
            box3.getTransform().setTranslation((new Vector2(0.8, -4)));
            box3Fixture.setRestitution(0.1);
            box3.addFixture(box3Fixture);
            box3.setMass(MassType.NORMAL);
            world.addBody(box3);
            gameObjects.add(new GameObject("/images/block.png", box3, new Vector2(0, 0), 0.5));
        }

        angryBlackBird = new Body();
        BodyFixture angryBlackBirdFixture = new BodyFixture(new Circle(0.3));
        angryBlackBirdFixture.setRestitution(0.3);
        angryBlackBirdFixture.setFriction(1);
        angryBlackBird.addFixture(angryBlackBirdFixture);
        angryBlackBird.getTransform().setTranslation(new Vector2(-8,-2.7));
        angryBlackBird.setMass(MassType.NORMAL);
        world.addBody(angryBlackBird);
        gameObjects.add(new GameObject("/images/blackbird.png", angryBlackBird, new Vector2(0,-60), 0.18));


        Body angryBlackestBird = new Body();
        BodyFixture angryBlackestBirdFixture = new BodyFixture(Geometry.createTriangle(new Vector2(-0.3,-0.5), new Vector2(0.3,-0.5), new Vector2(0,0)));
        angryBlackestBirdFixture.setRestitution(0.3);
        angryBlackestBird.addFixture(angryBlackestBirdFixture);
        angryBlackestBird.getTransform().setTranslation(new Vector2(-9.2,-3));
        angryBlackestBird.setMass(MassType.NORMAL);
        world.addBody(angryBlackestBird);
        gameObjects.add(new GameObject("/images/blackestbird.png", angryBlackestBird, new Vector2(0,100), 0.3));
    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        AffineTransform originalTransform = graphics.getTransform();

        graphics.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));
        graphics.scale(1, -1);

        for (GameObject go : gameObjects) {
            go.draw(graphics);
        }

        if (debugSelected) {
            graphics.setColor(Color.blue);
            DebugDraw.draw(graphics, world, 100);
        }

        graphics.setTransform(originalTransform);
    }

    public void addBlock(MouseEvent e, double x, double y) {
        if (!e.isControlDown())
            return;
        Body box = new Body();
        BodyFixture boxFixture = new BodyFixture(new Rectangle(0.5,0.5));
        boxFixture.setRestitution(0.1);
        box.getTransform().setTranslation((new Vector2(x, y)));
        box.addFixture(boxFixture);
        box.setMass(MassType.NORMAL);
        world.addBody(box);
        gameObjects.add(new GameObject("/images/block.png", box, new Vector2(0,0), 0.5));

    }
    public void update(double deltaTime) {
        mousePicker.update(world, camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()), 100);

        canvas.setOnMousePressed(e -> addBlock(e, (e.getX()/100-9.6), (e.getY()/100-5.4)*-1));
//        canvas.setOnKeyPressed(e -> );
        world.update(deltaTime);

    }

    public static void main(String[] args) {
        launch(AngryBirds.class);
    }

}
