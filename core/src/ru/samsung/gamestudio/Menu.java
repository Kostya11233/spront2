package ru.samsung.gamestudio;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;

public class Menu {

    Texture startTexture;
    Texture restartTexture;

    float x, y;
    float width, height;

    boolean isStartVisible;

    public Menu(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        startTexture = new Texture("start_game.png");
        restartTexture = new Texture("stop_game.png");

        isStartVisible = true;
    }

    public void draw(Batch batch) {
        if (isStartVisible) {
            batch.draw(startTexture, x, y, width, height);
        } else {
            batch.draw(restartTexture, x, y, width, height);
        }
    }

    public boolean isHit(float touchX, float touchY) {
        float realY = SCR_HEIGHT - touchY;
        return touchX >= x && touchX <= x + width &&
                realY >= y && realY <= y + height;
    }

    public void showStart() {
        isStartVisible = true;
    }

    public void showRestart() {
        isStartVisible = false;
    }

    public void dispose() {
        startTexture.dispose();
        restartTexture.dispose();
    }
}