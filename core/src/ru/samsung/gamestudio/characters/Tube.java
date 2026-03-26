package ru.samsung.gamestudio.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

public class Tube {

    Texture top;
    Texture bottom;

    Random random;

    int x;
    int gapY;
    int distance;
    int speed = 7;
    final int width = 200;
    final int height = 700;
    int gapHeight = 400;
    int padding = 100;

    public Tube(int count, int index) {
        random = new Random();

        gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        distance = (SCR_WIDTH + width) / (count - 1);
        x = distance * index + SCR_WIDTH;

        top = new Texture("Tubes/tube_flipped.png");
        bottom = new Texture("Tubes/tube.png");
    }

    public void move() {
        x -= speed;

        if (x < -width) {
            isPointReceived = false;
            x = SCR_WIDTH + distance;
            gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        }
    }

    public void draw(Batch batch) {
        batch.draw(top, x, gapY + gapHeight / 2, width, height);
        batch.draw(bottom, x, gapY - gapHeight / 2 - height, width, height);
    }

    public void dispose() {
        top.dispose();
        bottom.dispose();
    }
    public boolean isHit(Bird bird) {
        if (bird.y <= gapY - gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x + width) {
            return true;
        }

        if (bird.y + bird.height >= gapY + gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x + width) {
            return true;
        }

        return false;
    }
    boolean isPointReceived = false;

    public boolean needAddPoint(Bird bird) {
        return bird.x > x + width && !isPointReceived;
    }

    public void setPointReceived() {
        isPointReceived = true;
    }

}