package sk.fri.uniza.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * GameObject abstract class represents every object in game.
 *
 * @author Maria Kuruczova
 */

public abstract class GameObject {

    private float speed;
    private int width;
    private int height;

    private Sprite sprite;

    private Vector2 position = new Vector2();
    private Rectangle rectangle = new Rectangle();

    public GameObject(float x, float y, float speed, int width, int height, Sprite sprite) {
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        this.setPosition(x, y);
        this.sprite.setSize(width, height);
        this.rectangle.height = height;
        this.rectangle.width = width;

    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.sprite.setPosition(this.position.x, this.position.y);
        this.rectangle.setPosition(this.position.x, this.position.y);
    }

    public float getSpeed() {
        return this.speed;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public void draw(SpriteBatch batch) {
        this.sprite.draw(batch);
    }

    public abstract void move(float delta);

}
