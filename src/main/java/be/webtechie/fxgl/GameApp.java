package be.webtechie.fxgl;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

import be.webtechie.fxgl.GameFactory.EntityType;
import be.webtechie.fxgl.component.PlayerComponent;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import java.util.Map;

import com.almasb.fxgl.entity.SpawnData;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameApp extends GameApplication {

    private static final int SPEED = 5;

    /**
     * Reference to the factory which will defines how all the types must be created.
     */
    private final GameFactory snakeGameFactory = new GameFactory();

    /**
     * Player object we are going to use to provide to the factory so it can start a bullet from the player center.
     */
    private Entity player;

    /**
     * Main entry point where the application starts.
     *
     * @param args Start-up arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * General game settings. For now only the title is set, but a longer list of options is available.
     *
     * @param settings The settings of the game which can be further extended here.
     */
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(64 * 15);
        settings.setHeight(64 * 15);
        settings.setTitle("Oracle Java Magazine FXGL example game");
        //settings.setTicksPerSecond(10);
    }

    /**
     * General game variables. Used to hold the points and lives.
     *
     * @param vars The variables of the game which can be further extended here.
     */
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
        vars.put("lives", 5);
    }

    @Override
    protected void initUI() {
        Text scoreLabel = getUIFactoryService().newText("Score", Color.BLACK, 22);
        Text scoreValue = getUIFactoryService().newText("", Color.BLACK, 22);
        Text livesLabel = getUIFactoryService().newText("Lives", Color.BLACK, 22);
        Text livesValue = getUIFactoryService().newText("", Color.BLACK, 22);

        scoreLabel.setTranslateX(20);
        scoreLabel.setTranslateY(20);

        scoreValue.setTranslateX(90);
        scoreValue.setTranslateY(20);

        livesLabel.setTranslateX(getAppWidth() - 100);
        livesLabel.setTranslateY(20);

        livesValue.setTranslateX(getAppWidth() - 30);
        livesValue.setTranslateY(20);

        scoreValue.textProperty().bind(getWorldProperties().intProperty("score").asString());
        livesValue.textProperty().bind(getWorldProperties().intProperty("lives").asString());

        getGameScene().addUINodes(scoreLabel, scoreValue, livesLabel, livesValue);
    }

    /**
     * Input configuration, here you configure all the input events like key presses, mouse clicks, etc.
     */
    @Override
    protected void initInput() {

        onKeyDown(KeyCode.LEFT, "Move Left", () -> this.player
                .getComponentOptional(PlayerComponent.class)
                .ifPresent(PlayerComponent::left));
        onKeyDown(KeyCode.RIGHT, "Move Right", () -> this.player
                .getComponentOptional(PlayerComponent.class)
                .ifPresent(PlayerComponent::right));
        onKeyDown(KeyCode.UP, "Move Up", () -> this.player
                .getComponentOptional(PlayerComponent.class)
                .ifPresent(PlayerComponent::up));
        onKeyDown(KeyCode.DOWN, "Move Down", () -> this.player
                .getComponentOptional(PlayerComponent.class)
                .ifPresent(PlayerComponent::down));

        onKeyDown(KeyCode.F, () -> {
            spawn("cloud", getAppWidth() / 2, getAppHeight() / 2);
        });
/*
        onKeyDown(KeyCode.G, () -> {
            player.getComponent(SnakeHeadComponent.class).log();
        });
        */
    }

    /**
     * Initialization of the game by providing the {@link EntityFactory}.
     */
    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(this.snakeGameFactory);
        spawn("background", new SpawnData(0, 0).put("width", getAppWidth())
                .put("height", getAppHeight()));
        int circleRadius = 80;
        spawn("center", new SpawnData((getAppWidth() / 2) - (circleRadius / 2), (getAppHeight() / 2) - (circleRadius / 2))
                .put("x", (circleRadius / 2))
                .put("y", (circleRadius / 2))
                .put("radius", circleRadius));

        // Add the player
        this.player = spawn("duke", 0, 0);
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.DUKE, EntityType.CENTER, (duke, center) -> {
            this.player.getComponentOptional(PlayerComponent.class)
                    .ifPresent(PlayerComponent::die);
        });

        onCollisionBegin(EntityType.DUKE, EntityType.CLOUD, (enemy, cloud) -> {
            inc("score", 1);
            cloud.removeFromWorld();
        });
    }
}