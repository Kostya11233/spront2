package ru.samsung.gamestudio.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class PointCounter {

    int x;
    int y;
    BitmapFont font;
    int points = 0;
    public PointCounter(int x, int y) {
        this.x = x;
        this.y = y;
        font = new BitmapFont();
        font.getData().setScale(4f);
        font.setColor(Color.WHITE);
    }

    public void draw(Batch batch, int points) {
        font.draw(batch, "SCORE: " + points, x, y);
    }

    public void dispose() {
        font.dispose();
    }
}