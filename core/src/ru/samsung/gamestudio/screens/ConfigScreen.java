package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import ru.samsung.gamestudio.MyGdxGame;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;

public class ConfigScreen implements Screen {
    float btnX = 200;
    float btnY = 150;
    float btnW = 150;
    float btnH = 100;
    MyGdxGame game;
    public ConfigScreen(MyGdxGame game) {
        this.game = game;

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();
            float realY = SCR_HEIGHT - touchY;

            if (touchX >= btnX && touchX <= btnX + btnW &&
                    realY >= btnY && realY <= btnY + btnH) {
                Gdx.app.exit();
            }
        }
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
