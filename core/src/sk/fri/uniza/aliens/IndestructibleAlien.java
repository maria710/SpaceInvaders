package sk.fri.uniza.aliens;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * IndestructibleAlien inherits from class Alien. It can recover if its dead.
 *
 * @author Maria Kuruczova
 */

public class IndestructibleAlien extends Alien {

    private int numberOdRecoveriesAllowed;

    public IndestructibleAlien(float x, float y, Sprite sprite, int numberOdRecoveriesAllowed) {
        super(x, y, 100f, sprite, 7, 7);
        this.numberOdRecoveriesAllowed = numberOdRecoveriesAllowed;
    }

    @Override
    public boolean recovery() {
        if (super.isDead()) {
            if (this.numberOdRecoveriesAllowed > 0) {
                this.numberOdRecoveriesAllowed--;
                super.addLives(super.getMaxNumberOfLives());
                return true;
            }
        }
        return false;
    }
}
