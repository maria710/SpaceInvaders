package sk.fri.uniza.lasers;

import sk.fri.uniza.gameObjects.GameObject;
import sk.fri.uniza.managers.SoundManager;
import sk.fri.uniza.managers.TextureManager;

/**
 * Laser object represents shot of spaceship.
 *
 * @author Maria Kuruczova
 */

public class Laser extends GameObject {

    public Laser(float x, float y, float speed) {
        super(x, y, speed, 4, 15, TextureManager.getLaserSprite());
        SoundManager.getLaserSound().play(0.2f);
    }

    @Override
    public void move(float delta) {
        super.setPosition(getPosition().x, getPosition().y + delta * 250);
    }

    public void disposeSound() {
        SoundManager.getLaserSound().dispose();
    }
}
