package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.components.MovingBackground;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;

public class ConfigScreen implements Screen {
    private final MovingBackground background;
    MyGdxGame game;
    public ConfigScreen(MyGdxGame game) {
        this.game = game;
        background = new MovingBackground();


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        if (Gdx.input.justTouched()) {

        }
        ScreenUtils.clear(1, 0, 0, 1);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        background.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int i, int i1) {

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

    }
}
