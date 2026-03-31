package ru.samsung.gamestudio.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.samsung.gamestudio.MyGdxGame;

public class Bird {
    public boolean isGameOver;
    public float x;
    public float y;
    public float width;
    public float height;
    float speed;
    float jumpHeight;
    final float maxHeightOfJump = 100;
    boolean jump;
    int frameCounter;
    Texture[] frames;
    public Bird(float x, float y, float speed, float width, float height) {
        this.x = x;
        this.y = y;
        this.speed = speed = 5;
        this.width = width;
        this.height = height;
        frameCounter = 0;
        frames = new Texture[] {
                new Texture("birdTiles/bird0.png"),
                new Texture("birdTiles/bird1.png"),
                new Texture("birdTiles/bird2.png"),
                new Texture("birdTiles/bird1.png")
        };
    }

    public void onClick() {
        jump = true;
        jumpHeight = y + maxHeightOfJump;
    }

    public void fly() {
        if (jump && y < jumpHeight) {
            y += speed;
        } else {
            y -= speed;
            jump = false;
        }

        if (y < 0) {
            y = 0;
            jump = false;
        }


    }

    public void draw(Batch batch) {

        int frameIndex = (frameCounter / 10) % frames.length;
        batch.draw(frames[frameIndex], x, y, width, height);
        frameCounter++;
    }

    public void dispose() {
        for (Texture t : frames) t.dispose();
    }
    public boolean isOutOfScreen() {
        if (y + height < 0) return true;
        if (y > MyGdxGame.SCR_HEIGHT) return true;
        return false;
    }
    public boolean needAddPoint(Bird bird) {
        return false;
    }
}