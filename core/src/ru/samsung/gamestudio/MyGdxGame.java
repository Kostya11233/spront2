package ru.samsung.gamestudio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.samsung.gamestudio.screens.ConfigScreen;
import ru.samsung.gamestudio.screens.ScreenGame;
import ru.samsung.gamestudio.screens.ScreenRestart;

public class MyGdxGame extends Game {

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public ConfigScreen configScreen;

    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;
    public Screen restartScreen;
    public ScreenGame gameScreen;

    public void setScreen() {
        this.setScreen(new ScreenGame(this));
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        configScreen = new ConfigScreen(this);
        restartScreen = new ScreenRestart(this);
        gameScreen = (new ScreenGame(this));
        setScreen(gameScreen);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}