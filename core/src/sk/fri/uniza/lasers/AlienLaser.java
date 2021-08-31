package sk.fri.uniza.lasers;

public class AlienLaser extends Laser {

    public AlienLaser(float x, float y) {
        super(x, y, 10);
    }

    @Override
    public void move(float delta) {
        super.setPosition(getPosition().x, getPosition().y - delta * 250);
    }
}
