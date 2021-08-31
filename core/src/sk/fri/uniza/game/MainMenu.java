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
 * MainMenu is initial screen. It has two buttons available - start and exit.
 *
 * @author Maria Kuruczova
 */

public class MainMenu implements Screen {

    private SpriteBatch batch;

    private Sprite currentStartSprite;
    private Sprite currentExitSprite;

    private Sprite backgroundSprite;

    private MainGame game;

    private final float height = Gdx.graphics.getHeight();
    private final float width = Gdx.graphics.getWidth();

    private OrthographicCamera camera = new OrthographicCamera();

    public MainMenu(MainGame game) {
        this.game = game;

        this.batch = new SpriteBatch();
        this.camera.setToOrtho(false); //center the camera

        this.currentStartSprite = TextureManager.getStartSprites(0);
        this.currentExitSprite = TextureManager.getExitSprites(0);
        this.backgroundSprite = TextureManager.getInitializationSprite();

        this.setPositionOfExitSprite();
        this.setPositionOfStartSprite();

        this.backgroundSprite.setSize(width, height);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        backgroundSprite.draw(batch);
        currentExitSprite.draw(batch);
        currentStartSprite.draw(batch);
        batch.end();

        InputManager.highlightButtons(this, currentStartSprite, currentExitSprite, camera);
        if (InputManager.handleScreens(currentStartSprite, currentExitSprite, camera)) {
            game.setScreen(new SpaceInvadersGame(game));
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

    private void setPositionOfStartSprite() {
        this.currentStartSprite.setSize(this.currentStartSprite.getWidth(), this.currentStartSprite.getHeight());
        this.currentStartSprite.setPosition((width / 2f - this.currentStartSprite.getWidth() / 2), width / 3);
    }

    private void setPositionOfExitSprite() {
        this.currentExitSprite.setSize(this.currentExitSprite.getWidth() * 0.7f, this.currentExitSprite.getHeight() * 0.7f);
        this.currentExitSprite.setPosition((width / 2f - this.currentExitSprite.getWidth() / 2), width / 5);
    }

    public void setCurrentStartSprite(Sprite currentStartSprite) {
        this.currentStartSprite = currentStartSprite;
        this.setPositionOfStartSprite();
    }

    public void setCurrentExitSprite(Sprite currentExitSprite) {
        this.currentExitSprite = currentExitSprite;
        this.setPositionOfExitSprite();
    }
}
