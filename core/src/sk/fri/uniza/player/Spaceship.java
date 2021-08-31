package sk.fri.uniza.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import sk.fri.uniza.gameObjects.GameObject;
import sk.fri.uniza.gameObjects.ILivingObject;
import sk.fri.uniza.managers.GameManager;
import sk.fri.uniza.managers.InputManager;

/**
 * Spaceship class represents player, it can move to the left and to the right.
 * It has maximum of three lives, it can gain more lives during game but cannot recover after being killed.
 *
 * @author Maria Kuruczova
 */

public class Spaceship extends GameObject implements ILivingObject {

    private int lives;

    public Spaceship(float x, float y, Sprite spaceshipSprite) {
        super(x, y, 5, 60, 40, spaceshipSprite);
        this.lives = 3;
    }

    @Override
    public void move(float delta) {
        super.setPosition(getPosition().x + InputManager.handleSpaceship(delta * 250, getPosition().x,
                super.getWidth()), super.getPosition().y);
    }

    @Override
    public void addLives(int numberOfLives) {
        if (numberOfLives < 3) {
            this.lives += numberOfLives;
        }
    }

    public int getNumberOfLives() {
        return this.lives;
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
        return lives <= 0;
    }

    public void resetPosition(float x, float y) {
        super.setPosition(x , y);
    }

    /**
     * Checks collision with object Life.
     * In case of collision, spaceship can gain live if it has less than three lives.
     *
     * @return
     */

    public boolean gainedLife() {
        if (GameManager.getLife() != null) {
            if (Intersector.overlaps(super.getRectangle(), GameManager.getLife().getRectangle())) {
                if (lives < 3) {
                    this.addLives(1);
                }
                return true;
            }
        }
        return false;
    }
}
