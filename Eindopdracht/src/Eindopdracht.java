import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Eindopdracht extends Application {

    private final int WIDTH = 1920;
    private final int HEIGHT = 1080;

    private Group root;
    private ImageView sun;
    private ImageView mercury;
    private ImageView venus;
    private ImageView earth;
    private ImageView moon;
    private ImageView mars;
    private ImageView jupiter;
    private ImageView saturn;
    private ImageView uranus;
    private ImageView neptune;
    private int sunSize = 120;
    private double[] mercuryInt;
    private double[] venusInt;
    private double[] earthInt;
    private double[] moonInt;
    private double[] marsInt;
    private double[] jupiterInt;
    private double[] saturnInt;
    private double[] uranusInt;
    private double[] neptuneInt;
    private TextField speedScale;
    private double scale = 1;


    @Override
    public void start(Stage primaryStage) {
        root = new Group();
        Scene scene = new Scene(root ,WIDTH, HEIGHT, Color.BLACK);
        HBox topBar = new HBox();
        root.getChildren().add(topBar);

        topBar.getChildren().add(speedScale = new TextField("1"));
        speedScale.textProperty().addListener(e -> updateScale());
        primaryStage.setTitle("Eind Opdracht");
        primaryStage.setScene(scene);
        primaryStage.show();

        createSun();
        createMercury();
        createVenus();
        createEarth();
        createMoon();
        createMars();
        createJupiter();
        createSaturn();
        createUranus();
        createNeptune();


        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw();
            }
        }.start();
    }

    private void updateScale() {

        if (speedScale.getText().isEmpty())
            return;
        double scaleDouble = Double.parseDouble(speedScale.getText());
        mercuryInt[5] = mercuryInt[6] * scaleDouble;
        venusInt[5] = venusInt[6] * scaleDouble;
        earthInt[5] = earthInt[6] * scaleDouble;
        moonInt[5] = moonInt[6] * scaleDouble;
        marsInt[5] = marsInt[6] * scaleDouble;
        jupiterInt[5] = jupiterInt[6] * scaleDouble;
        saturnInt[5] = saturnInt[6] * scaleDouble;
        neptuneInt[5] = neptuneInt[6] * scaleDouble;
        uranusInt[5] = uranusInt[6] * scaleDouble;

    }

    private void createSun() {
        Image image = new Image("sun.png");
        sun = new ImageView(image);
        sun.setFitWidth(sunSize);
        sun.setFitHeight(sunSize);
        sun.setTranslateX(WIDTH / 2 - sunSize/2);
        sun.setTranslateY(HEIGHT / 2 - sunSize/2);
        root.getChildren().add(sun);
    }

    private void createMercury() {
        mercuryInt = new double[]{Math.random()*360, 10, 60, 0, 0, 4, 4}; // angle, size, orbitRadius, x, y, speed, default speed
        Image image = new Image("mercury.png");
        mercury = new ImageView(image);
        mercury.setFitWidth(mercuryInt[1]);
        mercury.setFitHeight(mercuryInt[1]);
        root.getChildren().add(mercury);
    }

    private void createVenus() {
        venusInt = new double[]{Math.random()*360, 25, 90, 0, 0, 1.6, 1.6};
        Image image = new Image("venus.png");
        venus = new ImageView(image);
        venus.setFitWidth(venusInt[1]);
        venus.setFitHeight(venusInt[1]);
        root.getChildren().add(venus);
    }

    private void createEarth() {
        earthInt = new double[]{Math.random()*360, 25, 120, 0, 0, 1, 1};
        Image image = new Image("earth.png");
        earth = new ImageView(image);
        earth.setFitWidth(earthInt[1]);
        earth.setFitHeight(earthInt[1]);
        root.getChildren().add(earth);
    }

    private void createMoon() {
        moonInt = new double[]{Math.random()*360, 5, 30, 0, 0, 365, 365};
        Image image = new Image("moon.png");
        moon = new ImageView(image);
        moon.setFitWidth(moonInt[1]);
        moon.setFitHeight(moonInt[1]);
        root.getChildren().add(moon);
    }

    private void createMars() {
        marsInt = new double[]{Math.random()*360, 13, 170, 0, 0, 0.54, 0.54};
        Image image = new Image("mars.png");
        mars = new ImageView(image);
        mars.setFitWidth(marsInt[1]);
        mars.setFitHeight(marsInt[1]);
        root.getChildren().add(mars);
    }

    private void createJupiter() {
        jupiterInt = new double[]{Math.random()*360, 100, 280, 0, 0, 0.084, 0.084};
        Image image = new Image("jupiter.png");
        jupiter = new ImageView(image);
        jupiter.setFitWidth(jupiterInt[1]);
        jupiter.setFitHeight(jupiterInt[1]);
        root.getChildren().add(jupiter);

    }

    private void createSaturn() {
        saturnInt = new double[]{Math.random()*360, 80, 390, 0, 0, 0.033, 0.033};
        Image image = new Image("saturn.png");
        saturn = new ImageView(image);
        saturn.setFitWidth(saturnInt[1]*1.4); // Scaled with 1.4, so it fits with its ring
        saturn.setFitHeight(saturnInt[1]);
        root.getChildren().add(saturn);

    }

    private void createUranus() {
        uranusInt = new double[]{Math.random()*360, 50, 500, 0, 0, 0.012, 0.012};
        Image image = new Image("uranus.png");
        uranus = new ImageView(image);
        uranus.setFitWidth(uranusInt[1]);
        uranus.setFitHeight(uranusInt[1]);
        root.getChildren().add(uranus);

    }

    private void createNeptune() {
        neptuneInt = new double[]{Math.random()*360, 50, 600, 0, 0, 0.006, 0.006};
        Image image = new Image("neptune.png");
        neptune = new ImageView(image);
        neptune.setFitWidth(neptuneInt[1]);
        neptune.setFitHeight(neptuneInt[1]);
        root.getChildren().add(neptune);

    }

    private void update(double deltaTime) {
        // Adds speed to rotation
        mercuryInt[0] += mercuryInt[5];
        venusInt[0] += venusInt[5];
        earthInt[0] += earthInt[5];
        moonInt[0] += moonInt[5];
        marsInt[0] += marsInt[5];
        jupiterInt[0] += jupiterInt[5];
        saturnInt[0] += saturnInt[5];
        neptuneInt[0] += neptuneInt[5];
        uranusInt[0] += uranusInt[5];

        // Calculates x and y relative to sun
        mercuryInt[3] = WIDTH / 2 + mercuryInt[2] * Math.cos(Math.toRadians(mercuryInt[0])); // x
        mercuryInt[4] = HEIGHT / 2 + mercuryInt[2] * Math.sin(Math.toRadians(mercuryInt[0])); // y

        venusInt[3] = WIDTH / 2 + venusInt[2] * Math.cos(Math.toRadians(venusInt[0]));
        venusInt[4] = HEIGHT / 2 + venusInt[2] * Math.sin(Math.toRadians(venusInt[0]));

        earthInt[3] = WIDTH / 2 + earthInt[2] * Math.cos(Math.toRadians(earthInt[0]));
        earthInt[4] = HEIGHT / 2 + earthInt[2] * Math.sin(Math.toRadians(earthInt[0]));

        // Calculates x and y relative to earth
        moonInt[3] = earthInt[3] + moonInt[2] * Math.cos(Math.toRadians(moonInt[0])); // x
        moonInt[4] = earthInt[4] + moonInt[2] * Math.sin(Math.toRadians(moonInt[0])); // y

        marsInt[3] = WIDTH / 2 + marsInt[2] * Math.cos(Math.toRadians(marsInt[0]));
        marsInt[4] = HEIGHT / 2 + marsInt[2] * Math.sin(Math.toRadians(marsInt[0]));

        saturnInt[3] = WIDTH / 2 + saturnInt[2] * Math.cos(Math.toRadians(saturnInt[0]));
        saturnInt[4] = HEIGHT / 2 + saturnInt[2] * Math.sin(Math.toRadians(saturnInt[0]));

        jupiterInt[3] = WIDTH / 2 + jupiterInt[2] * Math.cos(Math.toRadians(jupiterInt[0]));
        jupiterInt[4] = HEIGHT / 2 + jupiterInt[2] * Math.sin(Math.toRadians(jupiterInt[0]));

        neptuneInt[3] = WIDTH / 2 + neptuneInt[2] * Math.cos(Math.toRadians(neptuneInt[0]));
        neptuneInt[4] = HEIGHT / 2 + neptuneInt[2] * Math.sin(Math.toRadians(neptuneInt[0]));

        uranusInt[3] = WIDTH / 2 + uranusInt[2] * Math.cos(Math.toRadians(uranusInt[0]));
        uranusInt[4] = HEIGHT / 2 + uranusInt[2] * Math.sin(Math.toRadians(uranusInt[0]));

    }

    private void draw() {
        // Draws the translated and rotated planets

        sun.setRotate(earthInt[0]);

        mercury.setTranslateX(mercuryInt[3] - mercuryInt[1] / 2);
        mercury.setTranslateY(mercuryInt[4] - mercuryInt[1] / 2);
        mercury.setRotate(mercuryInt[0]);

        venus.setTranslateX(venusInt[3] - venusInt[1] / 2);
        venus.setTranslateY(venusInt[4] - venusInt[1] / 2);
        venus.setRotate(venusInt[0]);

        earth.setTranslateX(earthInt[3] - earthInt[1] / 2);
        earth.setTranslateY(earthInt[4] - earthInt[1] / 2);
        earth.setRotate(earthInt[0]);

        moon.setTranslateX(moonInt[3] - moonInt[1] / 2);
        moon.setTranslateY(moonInt[4] - moonInt[1] / 2);
        moon.setRotate(moonInt[0]);

        mars.setTranslateX(marsInt[3] - marsInt[1] / 2);
        mars.setTranslateY(marsInt[4] - marsInt[1] / 2);
        mars.setRotate(marsInt[0]);

        jupiter.setTranslateX(jupiterInt[3] - jupiterInt[1] / 2);
        jupiter.setTranslateY(jupiterInt[4] - jupiterInt[1] / 2);
        jupiter.setRotate(jupiterInt[0]);

        saturn.setTranslateX(saturnInt[3] - saturnInt[1] / 2);
        saturn.setTranslateY(saturnInt[4] - saturnInt[1] / 2);
        saturn.setRotate(saturnInt[0]);

        uranus.setTranslateX(uranusInt[3] - uranusInt[1] / 2);
        uranus.setTranslateY(uranusInt[4] - uranusInt[1] / 2);
        uranus.setRotate(uranusInt[0]);

        neptune.setTranslateX(neptuneInt[3] - neptuneInt[1] / 2);
        neptune.setTranslateY(neptuneInt[4] - neptuneInt[1] / 2);
        neptune.setRotate(neptuneInt[0]);


    }

    public static void main(String[] args) {
        launch(args);
    }
}