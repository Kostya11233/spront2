package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.characters.Bird;
import ru.samsung.gamestudio.characters.Tube;
import ru.samsung.gamestudio.components.MovingBackground;
import ru.samsung.gamestudio.components.PointCounter;

import java.util.ArrayList;

public class ScreenGame implements Screen {

    MyGdxGame game;

    Bird bird;
    ArrayList<Tube> tubes;
    int tubeCount = 3;
    MovingBackground background;
    PointCounter pointCounter;
    public int gamePoints;
    boolean isGameOver = false;

    public ScreenGame(MyGdxGame game) {
        this.game = game;

        bird = new Bird(200, 300, 10, 100, 100);


        pointCounter = new PointCounter(100, 600);
        background = new MovingBackground();
        initTubes();
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            bird.onClick();
        }
        if (isGameOver) {
            game.setScreen(game.restartScreen);
        }
        else {
            bird.fly();
            for (Tube t : tubes) t.move();
            background.move();

            for (Tube t : tubes) {
                if (t.isHit(bird)) {
                    isGameOver = true;
                } else if (t.needAddPoint(bird)) {
                    gamePoints++;
                    t.setPointReceived();
                }
            }

            if (bird.isOutOfScreen()) {
                isGameOver = true;
            }
        }
        ScreenUtils.clear(1, 0, 0, 1);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        background.draw(game.batch);
        for (Tube t : tubes) t.draw(game.batch);
        bird.draw(game.batch);

        pointCounter.draw(game.batch, gamePoints);


        game.batch.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        for (Tube t : tubes) t.dispose();
        pointCounter.dispose();
        background.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int w, int h) {

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
    public void initTubes() {

        tubes = new ArrayList<>();
        for (int i = 0; i < tubeCount; i++) {
            tubes.add(new Tube(tubeCount, i));
        }
    }
}