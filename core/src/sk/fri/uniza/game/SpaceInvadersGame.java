package sk.fri.uniza.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.fri.uniza.managers.GameManager;

/**
 * SpaceInvadersGame is main screen of program.
 * Initializes GameManagers class and renders whole game.
 *
 * @author Maria Kuruczova
 */

public class SpaceInvadersGame implements Screen {
    private SpriteBatch batch;

    public SpaceInvadersGame(MainGame game) {
        this.batch = new SpriteBatch();
        GameManager.initialize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), game);
    }

    @Override
    public void render(float delta) {
        delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        GameManager.render(delta);
        GameManager.draw(batch);
        batch.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        GameManager.dispose();
    }


}
