package sk.fri.uniza.gameObjects;

/**
 * ILivingObject interface contains methods for all living objects in game.
 *
 * @author Maria Kuruczova
 */

public interface ILivingObject {

    void addLives(int numberOfLives);
    void decreaseLives(int numberOfLives);
    boolean recovery();
    boolean isDead();
}
