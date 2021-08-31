package sk.fri.uniza.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.fri.uniza.managers.InputManager;
import sk.fri.uniza.managers.TextureManager;

/**
 * GameFinishedScreen appears when player wins or loses game.It has two buttons - exit, restart.
 *
 * @author Maria Kuruczova
 */

public class GameFinishedScreen implements Screen {

    private Sprite backgroundTexture;
    private Sprite restartButtonSprite;
    private Sprite exitButtonSprite;

    private MainGame game;
    private SpriteBatch batch;

    private OrthographicCamera camera = new OrthographicCamera();

    public GameFinishedScreen(MainGame game, Sprite sprite) {

        this.game = game;
        this.batch = new SpriteBatch();

        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();

        this.camera.setToOrtho(false); //centering camera

        this.exitButtonSprite = TextureManager.getExitSprites(2);
        this.restartButtonSprite = TextureManager.getResetSprite();
        this.backgroundTexture = sprite;

        this.backgroundTexture.setSize(width, height);

        this.restartButtonSprite.setPosition(width / 2 - this.restartButtonSprite.getWidth() / 2, height / 6);
        this.exitButtonSprite.setPosition(width - this.restartButtonSprite.getWidth(), this.exitButtonSprite.getHeight());

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.setProjectionMatrix(this.camera.combined);

        this.batch.begin();
        this.backgroundTexture.draw(this.batch);
        this.restartButtonSprite.draw(this.batch);
        this.exitButtonSprite.draw(this.batch);
        this.batch.end();

        if (InputManager.handleScreens(this.restartButtonSprite, this.exitButtonSprite, this.camera)) {
            this.game.setScreen(new SpaceInvadersGame(this.game));
        }
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
        this.batch.dispose();
    }
}
