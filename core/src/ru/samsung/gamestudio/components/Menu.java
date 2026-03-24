package ru.samsung.gamestudio.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

public class Menu {

    Texture bgTexture;
    Texture buttonTexture;

    BitmapFont font;
    GlyphLayout layout;

    float buttonX, buttonY, buttonW, buttonH;
    String buttonText;

    int gamePoints;

    public Menu() {
        Texture bgTexture = new Texture("restart_bg.png");
        Texture buttonTexture = new Texture("button_bg.png");
        font = new BitmapFont();
        font.getData().setScale(3f);
        font.setColor(Color.WHITE);
        layout = new GlyphLayout();
        buttonW = 400;
        buttonH = 150;
        buttonX = SCR_WIDTH / 2f - buttonW / 2f;
        buttonY = SCR_HEIGHT / 2f - buttonH / 2f;
    }

    public void setButtonText(String text) {
        this.buttonText = text;
    }

    public void setGamePoints(int points) {
        this.gamePoints = points;
    }

    public boolean isButtonHit(float touchX, float touchY) {
        float realY = SCR_HEIGHT - touchY;
        return touchX >= buttonX && touchX <= buttonX + buttonW &&
                realY >= buttonY && realY <= buttonY + buttonH;
    }

    public boolean isExitHit(float touchX, float touchY) {
        float realY = SCR_HEIGHT - touchY;
        return touchX >= 50 && touchX <= 200 && realY >= 50 && realY <= 150;
    }

    public boolean isPauseHit(float touchX, float touchY) {
        float realY = SCR_HEIGHT - touchY;
        return touchX >= SCR_WIDTH - 200 && touchX <= SCR_WIDTH - 50 &&
                realY >= SCR_HEIGHT - 150 && realY <= SCR_HEIGHT - 50;
    }
    public boolean isConfigHit(float tx, float ty) {
        float realY = SCR_HEIGHT - ty;
        return tx >= buttonX && tx <= buttonX + buttonW &&
                realY >= buttonY && realY <= buttonY + buttonH;
    }

    public void draw(Batch batch) {
        batch.draw(bgTexture, 0, 0, SCR_WIDTH, SCR_HEIGHT);


        batch.draw(buttonTexture, buttonX, buttonY, buttonW, buttonH);


        if (buttonText != null) {
            layout.setText(font, buttonText);
            float textX = buttonX + (buttonW - layout.width) / 2f;
            float textY = buttonY + buttonH / 2f + layout.height / 2f;
            font.draw(batch, buttonText, textX, textY);
        }

        if (gamePoints >= 0) {
            String scoreText = "SCORE: " + gamePoints;
            layout.setText(font, scoreText);
            float scoreX = SCR_WIDTH / 2f - layout.width / 2f;
            float scoreY = buttonY - 30;
            font.draw(batch, scoreText, scoreX, scoreY);
        }
        if (gamePoints >= 0) {
            String config = "CONFIG";
            layout.setText(font, config);
            float configX = SCR_WIDTH / 5f - layout.width / 2f;
            float configY = buttonY - -340;
            batch.draw(buttonTexture, 140, 550, 240, 100);
            font.draw(batch, config, configX, configY);
        }


        batch.draw(buttonTexture, 50, 50, 150, 100);
        font.draw(batch, "EXIT", 90, 120);
    }

    public void drawPauseButton(Batch batch) {
        batch.draw(buttonTexture, SCR_WIDTH - 200, SCR_HEIGHT - 150, 150, 100);
        font.draw(batch, "MENU", SCR_WIDTH - 180, SCR_HEIGHT - 80);
    }

    public void dispose() {
        bgTexture.dispose();
        buttonTexture.dispose();
        font.dispose();
    }
}