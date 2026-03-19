package ru.samsung.gamestudio.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.samsung.gamestudio.characters.Bird;

import java.util.Random;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;
import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;

public class Coin {
    Texture texture;
    float x;
    float y;
    float size = 80;
    Random random;
    static int coinScore;
    public Coin() {
        texture = new Texture("money.png"); // картинка монеты
        random = new Random();
        spawn(); // сразу появляем в случайном месте
    }
    public void spawn() {
        x = random.nextInt(SCR_WIDTH - (int) size);
        y = random.nextInt(SCR_HEIGHT - (int) size);
    }

    public boolean giveMoney(Bird bird) {
        return x < bird.x + bird.width &&
                x + size > bird.x &&
                y < bird.y + bird.height &&
                y + size > bird.y;


    }
    public void draw(Batch batch) {
        batch.draw(texture, x, y, size, size);
    }

    public void dispose() {
        texture.dispose();
    }

}

