package sk.fri.uniza.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Life class represents lives of spaceship, which can also collect them during playing.
 *
 * @author Maria Kuruczova
 */

public class Life extends GameObject {

    public Life(float x, float y, Sprite sprite) {
        super(x, y, 50, 20, 20, sprite);
    }

    @Override
    public void move(float delta) {
        super.setPosition(getPosition().x, super.getPosition().y - delta * super.getSpeed());
    }
}
