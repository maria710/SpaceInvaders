package sk.fri.uniza.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    private static Sound laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/mixkit_laser_sound.wav"));
    private static Sound soundAdd = Gdx.audio.newSound(Gdx.files.internal("sounds/mixkit-quick-gain-life.wav"));
    private static Sound soundDecrease = Gdx.audio.newSound(Gdx.files.internal("sounds/mixkit-video-game-bomb-alert-2803.wav"));
    private static Sound soundOver = Gdx.audio.newSound(Gdx.files.internal("sounds/mixkit-arcade-retro-game-over.wav"));
    private static Sound soundNextLevel = Gdx.audio.newSound(Gdx.files.internal("sounds/mixkit-completion-of-a-level-2063.wav"));
    private static Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/mixkit-video-game-retro-click.wav"));

    public static Sound getLaserSound() {
        return laserSound;
    }

    public static Sound getSoundAdd() {
        return soundAdd;
    }

    public static Sound getSoundDecrease() {
        return soundDecrease;
    }

    public static Sound getSoundOver() {
        return soundOver;
    }

    public static Sound getSoundNextLevel() {
        return soundNextLevel;
    }

    public static Sound getClickSound() {
        return clickSound;
    }

    public static void dispose() {
        soundAdd.dispose();
        soundDecrease.dispose();
        soundOver.dispose();
        soundNextLevel.dispose();
        clickSound.dispose();
    }
}
