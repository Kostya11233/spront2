package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.samsung.gamestudio.Buton;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.components.MovingBackground;

public class SkinsScreen implements Screen {
    private MovingBackground background;
    private Buton buttonSkin;
    private Buton buttonExit;
    MyGdxGame myGdxGame;
    public String n;
    public static int skinS = 1;
    private Object setBird;
    Buton buttonSkin2;
    Buton buttonSkin3;
    public SkinsScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        buttonSkin = new Buton(490, 400, 300, 110, "BIRD 2");
        buttonSkin2 = new Buton(100, 400, 300, 110, "BIRD 1");
        buttonSkin3 = new Buton(900, 400, 300, 110, "BIRD 3");
        buttonExit = new Buton(100, 100, 400, 80, "EXIT MENU");
        background = new MovingBackground("restart_bg.png");

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (buttonSkin.isHit((int) touch.x, (int) touch.y)) {
                skinS = 2;
                myGdxGame.setScreen(new ScreenGame(myGdxGame));
                return;
            }

            if (buttonSkin2.isHit((int) touch.x, (int) touch.y)) {
                skinS = 1;
                myGdxGame.setScreen(new ScreenGame(myGdxGame));
                return;
            }
            if (buttonExit.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.menuScreen = new MenuScreen(myGdxGame);
                myGdxGame.setScreen(myGdxGame.menuScreen);
                return;
            }
            if (buttonSkin3.isHit((int) touch.x, (int) touch.y)) {
                skinS = 3;
                myGdxGame.setScreen(new ScreenGame(myGdxGame));
                return;
            }
        }
        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        buttonSkin.draw(myGdxGame.batch);
        buttonSkin2.draw(myGdxGame.batch);
        buttonSkin3.draw(myGdxGame.batch);
        buttonExit.draw(myGdxGame.batch);
        myGdxGame.batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        buttonSkin.dispose();
        buttonSkin2.dispose();
        buttonSkin3.dispose();
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