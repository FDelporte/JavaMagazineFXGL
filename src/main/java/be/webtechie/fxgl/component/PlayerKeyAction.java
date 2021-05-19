package be.webtechie.fxgl.component;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;

public class PlayerKeyAction extends UserAction {

    private final KeyCode keyCode;

    public PlayerKeyAction(String name, KeyCode keyCode) {
        super(name);
        this.keyCode = keyCode;
    }

    private void handleKey() {
        PlayerComponent player = getGameWorld().getEntitiesByComponent(PlayerComponent.class).get(0).getComponent(PlayerComponent.class);
        switch (keyCode) {
            case UP:
                player.up();
                break;
            case DOWN:
                player.down();
                break;
            case LEFT:
                player.left();
                break;
            case RIGHT:
                player.right();
                break;
            default:
                // NOP
        }
    }

    @Override
    protected void onActionBegin() {
        // action just started (key has just been pressed), play swinging animation
        handleKey();
    }

    @Override
    protected void onAction() {
        // action continues (key is held), increase swing power
        handleKey();
    }

    @Override
    protected void onActionEnd() {
        // action finished (key is released), play hitting animation based on swing power
        handleKey();
    }
}
