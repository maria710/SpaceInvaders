package sk.fri.uniza.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import sk.fri.uniza.game.MainMenu;

/**
 * InputManagers handles input from player.
 *
 * @author Maria Kuruczova
 */

public class InputManager {

    private static Vector3 vector3 = new Vector3();

    public static float handleSpaceship(float speed, float x, int widthOfShip) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && x > 0) {
            return -speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && x < (Gdx.graphics.getWidth() - widthOfShip)) {
            return speed;
        } else {
            return 0;
        }
    }

    public static boolean handleLaser() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }


    /**
     * Compares coordinates of sprites and player input.
     *
     * @return true if start button is pressed
     */
    public static boolean handleScreens(Sprite startSprite, Sprite exitSprite, OrthographicCamera camera) {

        vector3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(vector3);

        float touchX = vector3.x;
        float touchY = vector3.y;

        if (Gdx.input.justTouched()) {
            if (compareFirstSprite(touchX, touchY, startSprite)) {
                SoundManager.getClickSound().play();
                return true;
            } else if (compareExitSprite(touchX, touchY, exitSprite)) {
                SoundManager.getClickSound().play();
                Gdx.app.exit();
            }
        }
        return false;
    }

    /**
     * Sets sprites and highlights buttons according to player mouse coordinates
     */

    public static void highlightButtons(MainMenu mainMenu, Sprite startSprite, Sprite exitSprite, OrthographicCamera camera) {

        vector3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(vector3);

        float touchX = vector3.x;
        float touchY = vector3.y;

        if (compareFirstSprite(touchX, touchY, startSprite)) {
            mainMenu.setCurrentStartSprite(TextureManager.getStartSprites(1));
        } else {
            mainMenu.setCurrentStartSprite(TextureManager.getStartSprites(0));
        }

        if (compareExitSprite(touchX, touchY, exitSprite)) {
            mainMenu.setCurrentExitSprite(TextureManager.getExitSprites(1));
        } else {
            mainMenu.setCurrentExitSprite(TextureManager.getExitSprites(0));
        }
    }

    private static boolean compareFirstSprite(float touchX, float touchY, Sprite startSprite) {
        return (touchX >= startSprite.getX() && touchX <= startSprite.getX() + startSprite.getWidth()
                && touchY >= startSprite.getY() && touchY <= startSprite.getY() + startSprite.getHeight());
    }

    private static boolean compareExitSprite(float touchX, float touchY, Sprite exitSprite) {
        return (touchX >= exitSprite.getX() && touchX <= exitSprite.getX() + exitSprite.getWidth()
                && touchY >= exitSprite.getY() && touchY <= exitSprite.getY() + exitSprite.getHeight());
    }
}
