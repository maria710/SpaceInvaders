package sk.fri.uniza.aliens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import sk.fri.uniza.managers.GameManager;
import sk.fri.uniza.managers.SoundManager;
import sk.fri.uniza.managers.TextureManager;
import sk.fri.uniza.player.Spaceship;

import java.util.ArrayList;
import java.util.Random;

/**
 * Alien attack class stores all aliens and manage attack on the ship.
 *
 * @author Maria Kuruczova
 */

public class AlienAttack {

    private static Array<Alien> aliens = new Array<>();
    private static Random random = new Random();

    private static float width;
    private static  float height;
    private static Spaceship spaceship;

    private static ArrayList<Alien> attackingAliens = new ArrayList<>();

    private static int numberOfOffensiveAliens = 0;
    private static int numberOfIndestructibleAliens = 0;
    private static int numberOfAliensAliens = 0;

    public enum Level {
        EASY,
        MEDIUM,
        HARD,
        FINISHED
    }

    private static Level currentLevel;

    public static void initialize(float width, float height, Spaceship spaceship) {
        aliens.clear();
        attackingAliens.clear();
        currentLevel = Level.EASY;

        AlienAttack.height = height;
        AlienAttack.width = width;
        AlienAttack.spaceship = spaceship;

        fillArray(width, height, spaceship);
        attackingAliens.add(aliens.pop());

    }

    /**
     * Moving of aliens, switching levels.
     */

    public static void attack(float delta) {

        for (Alien attackingAlien : attackingAliens) {
            attackingAlien.move(delta);
        }

        if (isLevelFinished()) {
            switch (currentLevel) {
                case EASY:
                    currentLevel = Level.MEDIUM;
                    SoundManager.getSoundNextLevel().play(0.5f);
                    fillArray(width, height, spaceship);
                    break;
                case MEDIUM:
                    SoundManager.getSoundNextLevel().play(0.5f);
                    currentLevel = Level.HARD;
                    fillArray(width, height, spaceship);
                    break;
                case HARD:
                    SoundManager.getSoundNextLevel().play(0.5f);
                    currentLevel = Level.FINISHED;
                    break;
            }
        }

        clearArrayList();
        addAliens();

        removeOldLasers();
        addLasers();
    }

    private static void clearArrayList() {
        if (attackingAliens.size() > 0 && attackingAliens.get(0).getPosition().y <= 0) {
            attackingAliens.remove(0);
        }
        destroyAliens();
    }

    /**
     * Adds aliens to arrayList of attacking aliens based on position of the last one.
     */
    private static void addAliens() {
        try {
            if (attackingAliens.get(attackingAliens.size() - 1).getPosition().y <= 600) {
                if (aliens.size > 0) {
                    attackingAliens.add(aliens.pop());
                }
            }
        } catch (Exception e) {
            fillArray(width, height, spaceship);
        }

    }

    private static boolean isLevelFinished() {
        return aliens.isEmpty();
    }

    public static void draw(SpriteBatch batch) {
        for (Alien attackingAlien : attackingAliens) {
            attackingAlien.draw(batch);
            if (attackingAlien instanceof OffensiveAlien) {
                ((OffensiveAlien)attackingAlien).drawLasers(batch);
            }
        }
    }

    public static String getLevel() {
        return currentLevel.name();
    }

    /**
     * Fills array with many different aliens based on number of picked aliens. \
     */
    private static void fillArray(float width, float height, Spaceship spaceship) {
        aliens.clear();
        pickingNumbersOfAliens();

        for (int i = 0; i < numberOfAliensAliens; i++) {
            aliens.add(new Alien(random.nextInt((int)width - 50), height, 50, TextureManager.getAlienSprites(0), 1, 1));
        }
        for (int i = 0; i < numberOfIndestructibleAliens; i++) {
            aliens.add(new IndestructibleAlien(random.nextInt((int)width - 50), height, TextureManager.getAlienSprites(1), 2));
        }
        for (int i = 0; i < numberOfOffensiveAliens; i++) {
            aliens.add(new OffensiveAlien(random.nextInt((int)width - 50), height, TextureManager.getAlienSprites(2), spaceship));
        }
        aliens.shuffle();
    }

    /**
     * Chooses randomly number of aliens to fill array depending on current level.
     */
    private static void pickingNumbersOfAliens() {
        switch (currentLevel) {
            case EASY:
                numberOfOffensiveAliens = random.nextInt(5) + 10;
                numberOfIndestructibleAliens = random.nextInt(10) + 10;
                numberOfAliensAliens = 30;
                break;
            case MEDIUM:
                numberOfOffensiveAliens = random.nextInt(15) + 10;
                numberOfIndestructibleAliens = random.nextInt(10) + 15;
                numberOfAliensAliens = 25;
                break;
            case HARD:
                numberOfOffensiveAliens = random.nextInt(10) + 20;
                numberOfIndestructibleAliens = random.nextInt(15) + 20;
                numberOfAliensAliens = 20;
                break;
        }
    }

    public static void restartLevel() {
        attackingAliens.clear();
        fillArray(width, height, spaceship);
        attackingAliens.add(aliens.pop());
    }

    public static boolean checkCollisionsWithShip(Spaceship spaceship) {
        try {
            for (Alien attackingAlien : attackingAliens) {
                if (Intersector.overlaps(spaceship.getRectangle(), attackingAlien.getRectangle())) {
                    attackingAliens.remove(attackingAlien);
                    return true;
                }
            }
        } catch (NullPointerException e) {
            return false;
        }

        return false;
    }

    public static boolean checkCollisionWithLasers() {
        for (Alien attackingAlien : attackingAliens) {
            for (int i = 0; i < GameManager.getLaserArrayListSize(); i++) {
                if (Intersector.overlaps(attackingAlien.getRectangle(), GameManager.getLaser(i).getRectangle())) {
                    attackingAlien.decreaseLives(1);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkCollisionsOfLasersWithShip() {
        for (Alien attackingAlien : attackingAliens) {
            if (attackingAlien instanceof OffensiveAlien) {
                if (((OffensiveAlien)attackingAlien).checkCollisionsOfLaserWithSpaceship()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void addLasers() {
        for (Alien attackingAlien : attackingAliens) {
            if (attackingAlien instanceof OffensiveAlien) {
                ((OffensiveAlien)attackingAlien).addLasers();
            }
        }
    }

    private static void removeOldLasers() {
        for (int i = 0; i < attackingAliens.size(); i++) {
            if (attackingAliens.get(i) instanceof OffensiveAlien) {
                ((OffensiveAlien)attackingAliens.get(i)).clearLasers();
            }
        }
    }

    /**
     * Deletes dead aliens from arrayList of attacking aliens.
     */
    private static void destroyAliens() {
        for (int i = 0; i < attackingAliens.size(); i++) {
            if (attackingAliens.get(i).isDead()) {
                if (!attackingAliens.get(i).recovery()) {
                    addPointsAccordingToAlien(attackingAliens.get(i));
                    attackingAliens.remove(attackingAliens.get(i));
                }
            }
        }
    }

    /**
     * Add points to score, depending on which alien was killed.
     */
    private static void addPointsAccordingToAlien(Alien attackingAlien) {
        if (attackingAlien instanceof OffensiveAlien) {
            GameManager.setScore(GameManager.getScore() + 3);
        } else if (attackingAlien instanceof IndestructibleAlien) {
            GameManager.setScore(GameManager.getScore() + 5);
        } else {
            GameManager.setScore(GameManager.getScore() + 1);
        }

        if (GameManager.getScore() > GameManager.getHighScore()) {
            GameManager.setHighScore(GameManager.getScore());
        }
    }

}
