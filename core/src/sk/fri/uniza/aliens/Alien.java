package sk.fri.uniza.aliens;

import com.badlogic.gdx.graphics.g2d.Sprite;
import sk.fri.uniza.gameObjects.GameObject;
import sk.fri.uniza.gameObjects.ILivingObject;

/**
 * Alien represents ordinary alien in the game and it is superclass to Offensive and Indestructible aliens.
 *
 * @author Maria Kuruczova
 */

public class Alien extends GameObject implements ILivingObject {

    private int lives;
    private int maxNumberOfLives;


    public Alien(float x, float y, float speed, Sprite sprite, int lives, int maxNumberOfLives) {
        super(x, y, speed, 50, 50, sprite);
        this.lives = lives;
        this.maxNumberOfLives = maxNumberOfLives;
    }

    public int getMaxNumberOfLives() {
        return this.maxNumberOfLives;
    }

    @Override
    public void move(float delta) {
        super.setPosition(super.getPosition().x, super.getPosition().y - delta * super.getSpeed());
    }

    @Override
    public void addLives(int numberOfLives) {
        this.lives = Math.min(numberOfLives + this.lives, this.maxNumberOfLives);
    }

    @Override
    public void decreaseLives(int numberOfLives) {
        this.lives -= numberOfLives;
    }

    @Override
    public boolean recovery() {
        return false;
    }

    @Override
    public boolean isDead() {
        return this.lives <= 0;
    }
}
