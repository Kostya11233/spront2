package ru.samsung.gamestudio.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.screens.SkinsScreen;

public class Bird {
    public boolean isGameOver;
    public float x;
    public float y;
    public float width;
    public float height;
    float speed = 1;
    float jumpHeight;
    float maxHeightOfJump = 100;
    boolean jump;
    int flyy;
    int frameCounter;
    Texture[] frames;
    SkinsScreen SkinsScreen;
    public Bird(float x, float y, float speed, float width, float height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        frameCounter = 0;


        if (SkinsScreen.skinS == 1) {
            frames = new Texture[]{
                    new Texture("birdTiles/bird0.png"),
                    new Texture("birdTiles/bird1.png"),
                    new Texture("birdTiles/bird2.png"),
                    new Texture("birdTiles/bird1.png")

            };
            flyy = 10;
            maxHeightOfJump = 167;
        } else if (SkinsScreen.skinS == 2) {
            frames = new Texture[]{
                    new Texture("kol/bird00.png"),
                    new Texture("kol/bird10.png"),
                    new Texture("kol/bird20.png"),
                    new Texture("kol/bird10.png")
            };
            maxHeightOfJump = 67;
            flyy = 1;

        } else if (SkinsScreen.skinS == 3) {
            frames = new Texture[]{
                    new Texture("kol/bird000.png"),
                    new Texture("kol/bird001.png"),
                    new Texture("kol/bird002.png"),
                    new Texture("kol/bird001.png")
            };
            maxHeightOfJump = 142;
            flyy = 9;
        }
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
        int frameIndex = (frameCounter / flyy) % frames.length;
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

    public void setFrames(Texture[] textures) {

    }
}