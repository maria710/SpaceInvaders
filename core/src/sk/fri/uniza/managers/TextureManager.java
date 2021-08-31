package sk.fri.uniza.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * TextureManager class creates sprites for all objects, buttons and backgrounds in the game.
 *
 * @author Maria Kuruczova
 */

public class TextureManager {

    private static Texture spaceshipTexture;
    private static Texture laserTexture;
    private static Texture backgroundTexture;
    private static Texture alienTexture;
    private static Texture indestructibleAlienTexture;
    private static Texture offensiveAlienTexture;
    private static Texture lifeTexture;
    private static Texture gameOverTexture;
    private static Texture initializationTexture;
    private static Texture youWonTexture;

    private static Texture exitButtonTexture;
    private static Texture exitBorderButtonTexture;
    private static Texture exitArrowButtonTexture;
    private static Texture startButtonTexture;
    private static Texture startBorderButtonTexture;
    private static Texture restartButtonTexture;


    public static Sprite getSpaceshipSprite() {
        spaceshipTexture = new Texture(Gdx.files.internal("spaceship/spaceship.png"));
        return new Sprite(spaceshipTexture);
    }

    public static Sprite getGameOverSprite() {
        gameOverTexture = new Texture(Gdx.files.internal("background/gameOver.png"));
        return new Sprite(gameOverTexture);
    }

    public static Sprite getYouWonSprite() {
        youWonTexture = new Texture(Gdx.files.internal("background/youWon.png"));
        return new Sprite(youWonTexture);
    }

    public static Sprite getInitializationSprite() {
        initializationTexture = new Texture(Gdx.files.internal("background/spaceInvadersBg.png"));
        return new Sprite(initializationTexture);
    }

    public static Sprite getAlienSprites(int index) {

        alienTexture = new Texture(Gdx.files.internal("aliens/alien.png"));
        indestructibleAlienTexture = new Texture(Gdx.files.internal("aliens/indestructibleAlien.png"));
        offensiveAlienTexture = new Texture(Gdx.files.internal("aliens/offensiveAlien2.png"));

        Sprite[] sprites = new Sprite[] {
            new Sprite(alienTexture),
            new Sprite(indestructibleAlienTexture),
            new Sprite(offensiveAlienTexture)
        };
        return sprites[index];
    }

    public static Sprite getLaserSprite() {
        laserTexture = new Texture(Gdx.files.internal("laser/laser.png"));
        return new Sprite(laserTexture);
    }

    public static Sprite getBackgroundSprite() {
        backgroundTexture = new Texture(Gdx.files.internal("background/bgWithLine.png"));
        return new Sprite(backgroundTexture);
    }

    public static Sprite getLifeSprite() {
        lifeTexture = new Texture(Gdx.files.internal("life/life.png"));
        return new Sprite(lifeTexture);
    }

    public static Sprite getExitSprites(int index) {

        exitButtonTexture = new Texture(Gdx.files.internal("buttons/exitButton.png"));
        exitBorderButtonTexture = new Texture(Gdx.files.internal("buttons/exitBorderButton.png"));
        exitArrowButtonTexture = new Texture(Gdx.files.internal("buttons/exitArrowButton.png"));

        Sprite[] sprites = new Sprite[] {
            new Sprite(exitButtonTexture),
            new Sprite(exitBorderButtonTexture),
            new Sprite(exitArrowButtonTexture)
        };
        return sprites[index];
    }

    public static Sprite getStartSprites(int index) {

        startButtonTexture = new Texture(Gdx.files.internal("buttons/startButton.png"));
        startBorderButtonTexture = new Texture(Gdx.files.internal("buttons/startBorderButton.png"));

        Sprite[] sprites = new Sprite[] {
            new Sprite(startButtonTexture),
            new Sprite(startBorderButtonTexture),
        };
        return sprites[index];
    }

    public static Sprite getResetSprite() {
        restartButtonTexture = new Texture(Gdx.files.internal("buttons/resetButton.png"));
        return new Sprite(restartButtonTexture);
    }

    public static void dispose() {

        alienTexture.dispose();
        indestructibleAlienTexture.dispose();
        offensiveAlienTexture.dispose();
        backgroundTexture.dispose();
        lifeTexture.dispose();
        initializationTexture.dispose();
        laserTexture.dispose();
        gameOverTexture.dispose();
        spaceshipTexture.dispose();
        youWonTexture.dispose();

        exitButtonTexture.dispose();
        exitBorderButtonTexture.dispose();
        exitArrowButtonTexture.dispose();
        startButtonTexture.dispose();
        startBorderButtonTexture.dispose();
        restartButtonTexture.dispose();

    }
}
