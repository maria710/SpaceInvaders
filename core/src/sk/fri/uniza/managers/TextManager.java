package sk.fri.uniza.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.fri.uniza.aliens.AlienAttack;

/**
 * TextManager creates and displays text.
 *
 * @author Maria Kuruczova
 */

public class TextManager {

    private static BitmapFont font;

    private static float width;
    private static float height;

    public static void initialize(float width, float height) {
        font = new BitmapFont();
        TextManager.width = width;
        TextManager.height = height;

        font.setColor(Color.RED);
        TextManager.font.getData().setScale(width / 500f);
    }

    public static void display(SpriteBatch batch) {
        float fontWidth = 50;
        font.draw(batch, "Score: " + GameManager.getScore(), width - fontWidth - (width / 10f), height * 0.95f);
        font.draw(batch, "High Score: " + GameManager.getHighScore(), width / 40f, height * 0.95f);
        font.draw(batch, "Level: " + AlienAttack.getLevel(), width / 2f - fontWidth, height * 0.95f);
    }

}
