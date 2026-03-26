package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.samsung.gamestudio.Buton;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.components.MovingBackground;

public class ScreenRestart implements Screen {
    private MovingBackground background;
    private Buton buttonRestart;
    private Buton buttonExit;
    MyGdxGame myGdxGame;

    public ScreenRestart(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        buttonRestart = new Buton(440, 400, "RESTART");
        buttonExit = new Buton(540, 250, "EXIT");
        background = new MovingBackground("restart_bg.png");
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (buttonRestart.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(new ScreenGame(myGdxGame));
                return;
            }
            if (buttonExit.isHit((int) touch.x, (int) touch.y)) {
                Gdx.app.exit();
                return;
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);
        buttonRestart.draw(myGdxGame.batch);
        buttonExit.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        buttonRestart.dispose();
        buttonExit.dispose();
    }

    @Override public void show() {}
    @Override public void resize(int i, int i1) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}