package be.webtechie.fxgl;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.texture;

import com.almasb.fxgl.dsl.components.AutoRotationComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The factory which defines how each entity looks like
 */
public class GameFactory implements EntityFactory {

    /**
     * Types of objects we are going to use in our game.
     */
    public enum EntityType {
        BACKGROUND, DUKE
    }

    @Spawns("background")
    public Entity spawnBackground(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.BACKGROUND)
                .view(new Rectangle(data.<Integer>get("width"), data.<Integer>get("height"), Color.DARKBLUE))
                .with(new IrremovableComponent())
                .zIndex(-100)
                .build();
    }

    @Spawns("duke")
    public Entity newSnakeHead(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.DUKE)
                .viewWithBBox(texture("duke.png", 32, 32))
                .collidable()
                .with(new AutoRotationComponent())
                .build();
    }

    /*
    @Spawns("snakeBody")
    public Entity newSnakeBody(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.SNAKE_BODY)
                .viewWithBBox(texture(FXGLMath.random(textureNames).get(), 32, 32))
                .collidable()
                .with(new AutoRotationComponent())
                .build();
    }
    */
}
