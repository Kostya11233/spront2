package ru.samsung.gamestudio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.samsung.gamestudio.screens.ConfigScreen;
import ru.samsung.gamestudio.screens.ScreenGame;

public class MyGdxGame extends Game {

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public ConfigScreen configScreen;

    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        configScreen = new ConfigScreen(this);
        this.setScreen(new ScreenGame(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}