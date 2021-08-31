package sk.fri.uniza.game;

import com.badlogic.gdx.Game;

/**
 * MainGame sets actual screen, which player can see.
 *
 * @author Maria Kuruczova
 */

public class MainGame extends Game {
    @Override
    public void create() {
        setScreen(new MainMenu(this));
    }
}
