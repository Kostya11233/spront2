package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.samsung.gamestudio.components.Menu;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.characters.Bird;
import ru.samsung.gamestudio.characters.Tube;
import ru.samsung.gamestudio.components.MovingBackground;
import ru.samsung.gamestudio.components.PointCounter;


import java.util.ArrayList;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;

public class ScreenGame implements Screen {

    MyGdxGame game;

    Bird bird;
    ArrayList<Tube> tubes;
    int tubeCount = 3;
    MovingBackground background;
    PointCounter pointCounter;
    Menu menu;

    int gamePoints;
    boolean isGameStarted = false;
    boolean isGameOver = false;
    boolean isPaused = false;

    public ScreenGame(MyGdxGame game) {
        this.game = game;

        bird = new Bird(200, 300, 10, 100, 100);

        tubes = new ArrayList<>();
        for (int i = 0; i < tubeCount; i++) {
            tubes.add(new Tube(tubeCount, i));
        }

        pointCounter = new PointCounter(100, 600);
        background = new MovingBackground();
        menu = new Menu();
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            float tx = Gdx.input.getX();
            float ty = Gdx.input.getY();


            if (isPaused) {
                if (menu.isButtonHit(tx, ty)) {
                    isPaused = false;
                }
                if (menu.isExitHit(tx, ty)) {
                    Gdx.app.exit();
                }
            } else if (!isGameStarted && !isGameOver) {
                if (menu.isButtonHit(tx, ty)) {

                    gamePoints = 0;
                    bird = new Bird(200, 300, 10, 100, 100);
                    tubes = new ArrayList<>();
                    for (int i = 0; i < tubeCount; i++) {
                        tubes.add(new Tube(tubeCount, i));
                    }
                }
                if (menu.isExitHit(tx, ty)) {
                    Gdx.app.exit();
                }

                if (menu.isConfigHit(tx, ty)) {
                    game.setScreen(game.configScreen);
                }
            } else if (isGameOver) {
                if (menu.isButtonHit(tx, ty)) {
                    isGameStarted = true;
                    isGameOver = false;
                    bird = new Bird(200, 300, 10, 100, 100);
                    tubes = new ArrayList<>();
                    for (int i = 0; i < tubeCount; i++) {
                        tubes.add(new Tube(tubeCount, i));
                    }
                }
                if (menu.isExitHit(tx, ty)) {
                    Gdx.app.exit();
                }
            } else if (isGameStarted && !isGameOver && !isPaused) {
                if (menu.isPauseHit(tx, ty)) {
                    isPaused = true;
                } else {
                    bird.onClick();
                }
            }
        }
        if (isGameStarted && !isGameOver && !isPaused) {
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

        if (isGameStarted && !isGameOver && !isPaused) {
            pointCounter.draw(game.batch, gamePoints);
        }


        if (isGameStarted && !isGameOver && !isPaused) {
            menu.drawPauseButton(game.batch);
        }

        if (Gdx.input.justTouched()) {
            float tx = Gdx.input.getX();
            float ty = Gdx.input.getY();
            float realY = SCR_HEIGHT - ty;


            if (isGameStarted && !isGameOver && !isPaused) {
                if (menu.isPauseHit(tx, ty)) {
                }
            }
        }



        if (!isGameStarted) {
            menu.setButtonText("START");
            menu.setGamePoints(0);
            menu.draw(game.batch);
        } else if (isPaused) {
            menu.setButtonText("RESUME");
            menu.setGamePoints(gamePoints);
            menu.draw(game.batch);
        } else if (isGameOver) {
            menu.setButtonText("RESTART");
            menu.setGamePoints(gamePoints);
            menu.draw(game.batch);
        }


        game.batch.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        for (Tube t : tubes) t.dispose();
        pointCounter.dispose();
        background.dispose();
        menu.dispose();
    }

    @Override public void show() {

    }
    @Override public void resize(int w, int h) {

    }
    @Override public void pause() {

    }
    @Override public void resume() {

    }
    @Override public void hide() {

    }
}