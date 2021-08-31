package sk.fri.uniza.aliens;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import sk.fri.uniza.lasers.AlienLaser;
import sk.fri.uniza.managers.GameManager;
import sk.fri.uniza.player.Spaceship;

import java.util.ArrayList;

/**
 * OffensiveAlien is subclass to Alien.
 *
 * @author Maria Kuruczova
 */

public class OffensiveAlien extends Alien {

    private Spaceship spaceship;
    private ArrayList<AlienLaser> alienLasers;


    public OffensiveAlien(float x, float y, Sprite sprite, Spaceship spaceship) {
        super(x, y, 100, sprite, 3, 3);
        this.spaceship = spaceship;
        this.alienLasers = new ArrayList<>();
    }

    /**
     * Following player by using his coordinates.
     */
    @Override
    public void move(float delta) {

        float positionXOfShip = this.spaceship.getPosition().x;
        if (positionXOfShip >= super.getPosition().x) {
            super.setPosition(super.getPosition().x + delta * (super.getSpeed() * 1.1f), super.getPosition().y - delta * getSpeed());
        } else if (positionXOfShip <= super.getPosition().x) {
            super.setPosition(super.getPosition().x - delta * (super.getSpeed() * 1.1f), super.getPosition().y - delta * getSpeed());
        }

        for (AlienLaser alienLaser : alienLasers) {
            alienLaser.move(delta);
        }
    }

    public void drawLasers(SpriteBatch batch) {
        for (AlienLaser alienLaser : alienLasers) {
            alienLaser.draw(batch);
        }
    }

    public void addLasers() {
        if (super.getPosition().x <= GameManager.getSpaceship().getPosition().x + GameManager.getSpaceship().getWidth() &&
                super.getPosition().x >= GameManager.getSpaceship().getPosition().x &&
                alienLasers.size() < 2) {
            alienLasers.add(new AlienLaser(super.getPosition().x + (super.getSprite().getWidth() / 2), super.getPosition().y));
        }
    }

    public void clearLasers() {
        for (int i = 0; i < this.alienLasers.size(); i++) {
            if (this.alienLasers.get(i).getPosition().y <= 0) {
                this.alienLasers.remove(i);
            }
        }
    }

    public boolean checkCollisionsOfLaserWithSpaceship() {
        for (int i = 0; i < this.alienLasers.size(); i++) {
            if (Intersector.overlaps(this.alienLasers.get(i).getRectangle(), spaceship.getRectangle())) {
                this.alienLasers.remove(i);
                return true;
            }
        }
        return false;
    }


}

