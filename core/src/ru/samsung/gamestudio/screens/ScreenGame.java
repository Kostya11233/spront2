package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.samsung.gamestudio.Buton;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.characters.Bird;
import ru.samsung.gamestudio.characters.Tube;
import ru.samsung.gamestudio.components.MovingBackground;
import ru.samsung.gamestudio.components.PointCounter;

import java.util.ArrayList;

import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;
import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;

public class ScreenGame implements Screen {

    MyGdxGame game;
    Bird bird;
    ArrayList<Tube> tubes;
    int tubeCount = 3;
    MovingBackground bg;
    PointCounter pointCounter;
    BitmapFont font;
    Buton menuButton;

    public int gamePoints;
    public boolean isGameOver = false;
    int lives = 1;
    int nextLifeAt = 10;

    public ScreenGame(MyGdxGame game) {
        this.game = game;
        initGame();
        pointCounter = new PointCounter(100, 600);
        bg = new MovingBackground();
        font = new BitmapFont();
        font.getData().setScale(4f);
        menuButton = new Buton(SCR_WIDTH - 200, SCR_HEIGHT - 100, 180, 70, "MENU");
    }

    public void initGame() {
        bird = new Bird(200, 300, 10, 100, 100);
        initTubes();
    }

    public void initTubes() {
        tubes = new ArrayList<>();
        for (int i = 0; i < tubeCount; i++) {
            tubes.add(new Tube(tubeCount, i));
        }
    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {
            float tx = Gdx.input.getX();
            float ty = Gdx.input.getY();
            float realY = SCR_HEIGHT - ty;


            if (menuButton.isHit((int) tx, (int) realY)) {
                game.setScreen(game.resumeScreen);
                return;
            }


            if (!isGameOver) {
                bird.onClick();
            }
        }

        if (isGameOver) {
            game.screenRestart = new ScreenRestart(game, gamePoints);
            game.setScreen(game.screenRestart);
            return;
        }


        bird.fly();
        for (Tube t : tubes) t.move();
        bg.move();

        for (Tube t : tubes) {
            if (t.isHit(bird)) {
                lives--;
                if (lives <= 0) {
                    isGameOver = true;
                } else {
                    bird = new Bird(200, 300, 10, 100, 100);
                    initTubes();
                }
                break;
            } else if (t.needAddPoint(bird)) {
                gamePoints++;
                t.setPointReceived();
                if (gamePoints >= nextLifeAt) {
                    lives++;
                    nextLifeAt += 10;
                }
            }
        }
        if (bird.isOutOfScreen()) isGameOver = true;
        ScreenUtils.clear(1, 0, 0, 1);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        bg.draw(game.batch);
        for (Tube t : tubes) t.draw(game.batch);
        bird.draw(game.batch);
        pointCounter.draw(game.batch, gamePoints);
        font.draw(game.batch, "LIVES: " + lives, 50, SCR_HEIGHT - 50);
        menuButton.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        for (Tube t : tubes) t.dispose();
        pointCounter.dispose();
        bg.dispose();
        font.dispose();
        menuButton.dispose();
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