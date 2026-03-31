package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.samsung.gamestudio.Buton;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.components.MovingBackground;

public class ResumeScreen implements Screen {
    private MovingBackground background;
    private Buton buttonResume;
    private Buton buttonExit;
    MyGdxGame myGdxGame;
    public ResumeScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        buttonResume = new Buton(490, 400, 300, 110, "RESUME");
        buttonExit = new Buton(100, 100, 400, 80, "EXIT MENU");
        background = new MovingBackground("restart_bg.png");
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (buttonResume.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenGame);
                return;
            }
            if (buttonExit.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.menuScreen = new MenuScreen(myGdxGame);
                myGdxGame.setScreen(myGdxGame.menuScreen);
                return;
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        buttonResume.draw(myGdxGame.batch);
        buttonExit.draw(myGdxGame.batch);
        myGdxGame.batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        buttonResume.dispose();
        buttonExit.dispose();
    }

    @Override public void show() {

    }
    @Override public void resize(int i, int i1) {

    }
    @Override public void pause() {

    }
    @Override public void resume() {

    }
    @Override public void hide() {

    }
}