package ru.samsung.gamestudio.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.samsung.gamestudio.MyGdxGame;

public class MovingBackground {
    Texture texture;
    int texture1x, texture2x;
    int speed = 2;

    public MovingBackground() {
        texture1x = 0;
        texture2x = MyGdxGame.SCR_WIDTH;
        texture = new Texture("game_bg.png");
    }

    public MovingBackground(String imagePath) {
        texture1x = 0;
        texture2x = MyGdxGame.SCR_WIDTH;
        texture = new Texture(imagePath);
    }

    public void move() {
        texture1x -= speed;
        texture2x -= speed;
        if (texture1x <= -MyGdxGame.SCR_WIDTH) texture1x = MyGdxGame.SCR_WIDTH;
        if (texture2x <= -MyGdxGame.SCR_WIDTH) texture2x = MyGdxGame.SCR_WIDTH;
    }

    public void draw(Batch batch) {
        batch.draw(texture, texture1x, 0, MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);
        batch.draw(texture, texture2x, 0, MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);
    }

    public void dispose() {
        texture.dispose();
    }
}