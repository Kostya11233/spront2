package ru.samsung.gamestudio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

public class ScreenGame implements Screen {

    MyGdxGame game;

    Bird bird;
    ArrayList<Tube> tubes;
    int tubeCount = 3;

    MovingBackground background;
    PointCounter pointCounter;
    Menu menu;

    BitmapFont font;

    int gamePoints;
    boolean isGameStarted = false;
    boolean isGameOver = false;

    final int pointCounterMarginTop = 60;
    final int pointCounterMarginRight = 400;

    public ScreenGame(MyGdxGame game) {
        this.game = game;

        bird = new Bird(200, 300, 10, 100, 100);

        tubes = new ArrayList<>();
        for (int i = 0; i < tubeCount; i++) {
            tubes.add(new Tube(tubeCount, i));
        }

        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);
        font = new BitmapFont();
        background = new MovingBackground();

        menu = new Menu(SCR_WIDTH / 2f - 200, SCR_HEIGHT / 2f - 100, 200, 400);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            float tx = Gdx.input.getX();
            float ty = Gdx.input.getY();

            if (!isGameStarted) {
                if (menu.isHit(tx, ty)) {
                    isGameStarted = true;
                    isGameOver = false;
                    gamePoints = 0;

                    bird = new Bird(200, 300, 10, 100, 100);

                    tubes = new ArrayList<>();
                    for (int i = 0; i < tubeCount; i++) {
                        tubes.add(new Tube(tubeCount, i));
                    }
                }
            } else if (isGameOver) {
                if (menu.isHit(tx, ty)) {
                    isGameStarted = true;
                    isGameOver = false;
                    gamePoints = 0;

                    bird = new Bird(200, 300, 10, 100, 100);

                    tubes = new ArrayList<>();
                    for (int i = 0; i < tubeCount; i++) {
                        tubes.add(new Tube(tubeCount, i));
                    }
                }
            } else {
                bird.onClick();
            }
        }

        if (isGameStarted && !isGameOver) {
            bird.fly();
            for (Tube t : tubes) {
                t.move();
            }
            background.move();

            for (Tube t : tubes) {
                if (t.isHit(bird)) {
                    isGameOver = true;
                    menu.showRestart();
                } else if (t.needAddPoint(bird)) {
                    gamePoints++;
                    t.setPointReceived();
                }
            }

            if (bird.isOutOfScreen()) {
                isGameOver = true;
                menu.showRestart();
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();

        background.draw(game.batch);

        for (Tube t : tubes) {
            t.draw(game.batch);
        }
        bird.draw(game.batch);

        if (isGameStarted && !isGameOver) {
            pointCounter.draw(game.batch, gamePoints);
        }

        if (!isGameStarted || isGameOver) {
            menu.draw(game.batch);
        }



        game.batch.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        for (Tube t : tubes) {
            t.dispose();
        }
        pointCounter.dispose();
        font.dispose();
        background.dispose();
        menu.dispose();
    }

    @Override public void show() {

    }
    @Override public void resize(int width, int height) {

    }
    @Override public void pause() {

    }
    @Override public void resume() {

    }
    @Override public void hide() {

    }
}