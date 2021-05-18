package be.webtechie.fxgl.component;

import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerComponent extends Component {

    private Point2D direction = new Point2D(1, 0);

    @Override
    public void onUpdate(double tpf) {
        entity.translate(direction.multiply(2));
        checkForBounds();
    }

    private void checkForBounds() {
        if (entity.getX() < 0)
            die();

        if (entity.getX() >= getAppWidth())
            die();

        if (entity.getY() < 0)
            die();

        if (entity.getY() >= getAppHeight())
            die();
    }

    public void die() {
        inc("lives", -1);
        entity.setPosition(0, 0);
        right();
    }

    public void up() {
        direction = new Point2D(0, -1);
    }

    public void down() {
        direction = new Point2D(0, 1);
    }

    public void left() {
        direction = new Point2D(-1, 0);
    }

    public void right() {
        direction = new Point2D(1, 0);
    }
}