import com.sun.javafx.geom.Point2D;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Renderable {

        private Shape shape;
        private Point2D position;
        private float rotation;
        private float scale;

        public Renderable(Shape shape, Point2D position) {
            this.shape = shape;
            this.position = position;
        }

        public void draw(FXGraphics2D g2d) {
            g2d.draw(getTransformedShape());
        }

        public Shape getTransformedShape() {
            return getTransform().createTransformedShape(shape);
        }

        public void setPosition(Point2D position) {
            this.position = position;
        }
        public boolean inBlock(Point2D position) {
            boolean state = false;
            if ((position.x > this.position.x-50 && position.x < this.position.x+50)
                    && position.y > this.position.y-50 && position.y < this.position.y+50) {
                state = true;
            }
            return state;
        }

        public AffineTransform getTransform() {
            AffineTransform tx = new AffineTransform();
            tx.translate(position.x, position.y);
            return tx;
        }


}
