package sk.fri.uniza.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import sk.fri.uniza.aliens.AlienAttack;
import sk.fri.uniza.game.GameFinishedScreen;
import sk.fri.uniza.game.MainGame;
import sk.fri.uniza.lasers.Laser;
import sk.fri.uniza.gameObjects.Life;
import sk.fri.uniza.player.Spaceship;

import java.util.LinkedList;
import java.util.Random;

/**
 * GameManager class controls all objects.
 *
 * @author Maria Kuruczova
 */

public class GameManager {

    private static Sprite backgroundSprite = TextureManager.getBackgroundSprite();
    private static Spaceship spaceship;
    private static MainGame game;
    private static Array<Laser> lasers = new Array<>();

    private static int indexOfRemovingLaser;
    private static int score;
    private static int highScore;

    private static Preferences preferences;

    private static float height;
    private static float width;
    private static float counter = 0;

    private static Life life;
    private static LinkedList<Sprite> lives = new LinkedList<>();

    private static Random rand = new Random();

    public static void initialize(float width, float height, MainGame game) {

        GameManager.game = game;

        GameManager.width = width;
        GameManager.height = height;

        TextManager.initialize(width, height);

        score = 0;
        preferences = Gdx.app.getPreferences("My Preferences"); // in order to get previous result from game
        highScore = preferences.getInteger("highScore");

        backgroundSprite.setPosition(0, 0);
        backgroundSprite.setSize(width, height);
        spaceship = new Spaceship(width / 2f - 20, height / 15f, TextureManager.getSpaceshipSprite());
        AlienAttack.initialize(width, height, spaceship);


        initializeLives();
        life = null;
        lasers.clear();

    }

    public static void render(float delta) {
        spaceship.move(delta);
        manageLaser();

        for (Laser laser : lasers) {
            laser.move(delta);
        }

        AlienAttack.attack(delta);
        checkCollisions();

        preferences.putInteger("highScore", score);
        preferences.flush();

        manageLifeFalling(delta);

        if (AlienAttack.getLevel().endsWith("FINISHED")) {
            game.setScreen(new GameFinishedScreen(game, TextureManager.getYouWonSprite()));
        }
    }

    public static void draw(SpriteBatch batch) {
        backgroundSprite.draw(batch);
        spaceship.draw(batch);

        for (Laser laser : lasers) {
            laser.draw(batch);
        }

        AlienAttack.draw(batch);
        TextManager.display(batch);

        if (canFall()) {
            life.draw(batch);
        }

        for (int i = 0; i < spaceship.getNumberOfLives(); i++) {
            lives.get(i).draw(batch);
        }
    }

    private static void manageLaser() {
        if (InputManager.handleLaser()) {
            lasers.add(new Laser(spaceship.getPosition().x + (spaceship.getWidth() / 2f) - 2,
                    spaceship.getPosition().y + spaceship.getHeight(), 7f));
        }
        for (int i = 0; i < lasers.size; i++) {
            if (lasers.get(i).getPosition().y > height) {
                lasers.removeIndex(i);
            }
        }
    }

    public static Laser getLaser(int index) {
        indexOfRemovingLaser = index;
        return lasers.get(indexOfRemovingLaser);
    }

    private static void manageLifeFalling(float delta) {
        if (delta + counter >= 50f && life == null) {
            life = new Life(rand.nextInt((int)(width - 20)), height, TextureManager.getLifeSprite());
            counter = 0;
        } else {
            counter += delta;
        }

        if (canFall()) {
            life.move(delta);
        }

        if (spaceship.gainedLife()) {
            SoundManager.getSoundAdd().play(0.3f);
            life = null;
        }

        if (life != null) {
            if (life.getPosition().y <= 0) {
                life = null;
            }
        }
    }

    private static void initializeLives() {
        lives.add(new Sprite(TextureManager.getLifeSprite()));
        lives.add(new Sprite(TextureManager.getLifeSprite()));
        lives.add(new Sprite(TextureManager.getLifeSprite()));

        for (int i = 1; i <= 3; i++) {
            lives.get(i - 1).setSize(25, 25);
            lives.get(i - 1).setPosition(width - (lives.get(i - 1).getWidth() * i), 10);
        }
    }

    /**
     * Checks aliens collisions with spaceship and lasers.
     */
    private static void checkCollisions() {
        if (AlienAttack.checkCollisionsWithShip(spaceship) || AlienAttack.checkCollisionsOfLasersWithShip()) {
            spaceship.decreaseLives(1);
            if (spaceship.isDead()) {
                SoundManager.getSoundOver().play();
                game.setScreen(new GameFinishedScreen(game, TextureManager.getGameOverSprite()));
            } else {
                SoundManager.getSoundDecrease().play(0.5f);
                spaceship.resetPosition(width / 2f - 20, height / 15f);
                AlienAttack.restartLevel();
                life = null;
                score = score < 5 ? 0 : score - 5;
            }
        }

        if (AlienAttack.checkCollisionWithLasers()) {
            lasers.removeIndex(indexOfRemovingLaser);
        }
    }

    public static void dispose() {
        TextureManager.dispose();
        for (Laser laser : lasers) {
            laser.disposeSound();
        }

        SoundManager.dispose();
    }

    public static Life getLife() {
        return life;
    }

    private static boolean canFall() {
        return life != null;
    }

    public static int getLaserArrayListSize() {
        return lasers.size;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GameManager.score = score;
    }

    public static int getHighScore() {
        return highScore;
    }

    public static void setHighScore(int highScore) {
        GameManager.highScore = highScore;
    }

    public static Spaceship getSpaceship() {
        return spaceship;
    }
}
