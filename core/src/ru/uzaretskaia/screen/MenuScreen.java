package ru.uzaretskaia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.uzaretskaia.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {
    private SpriteBatch batch;
    private Texture moon;
    private Texture space;
    private Vector2 touch;
    private Vector2 pos;
    private Vector2 v;
    private Vector2 dest_pos;
    private Vector2 moonCenter;

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        moon = new Texture("Moon.png");
        space = new Texture("space.jpg");
        touch = new Vector2();
        pos = new Vector2();
        dest_pos = new Vector2();
        v = new Vector2();
        moonCenter = new Vector2(moon.getWidth() / 2, moon.getHeight() / 2);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(space, 0, 0);
        batch.draw(moon, pos.x, pos.y);
        batch.end();

        // Считаем, что картинка достигла точки назначения если разность соответствующих координат меньше констант (2 - произвольное число)
        // Проверим, подошли ли к границам экрана (если еще движемся)
        if (v.x != 0) {
            if (v.x < 0){
                // движемся влево
                if (Math.abs(pos.x - dest_pos.x) <= 2 || pos.x <= 0) {
                    v.set(0, v.y);
                }
            } else {
                // движемся вправо
                if (Math.abs(dest_pos.x - pos.x) <= 2 || pos.x + moon.getWidth() >= width) {
                    v.set(0, v.y);
                }
            }
        }
        if (v.y != 0) {
            if (v.y < 0){
                // движемся вниз
                if (Math.abs(pos.y - dest_pos.y) <= 2 || pos.y <= 0){
                    v.set(v.x, 0);
                }
            } else {
                // движемся вверх
                if (Math.abs(dest_pos.y - pos.y) <= 2 || pos.y + moon.getHeight() >= height){
                    v.set(v.x, 0);
                }
            }
        }
        pos.add(v);

    }

    @Override
    public void dispose() {
        batch.dispose();
        moon.dispose();
        space.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY); // получим точку клика
        dest_pos.set(touch.x - moonCenter.x, touch.y - moonCenter.y); // получим координаты назначения с учетом центра картинки
        calculateV(v, dest_pos);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == 21){
            dest_pos.set(0, pos.y); // влево
        } else if (keycode == 22){
            dest_pos.set(width, pos.y); // вправо
        } else if (keycode == 19){
            dest_pos.set(pos.x, height); // вверх
        } else if (keycode == 20) {
            dest_pos.set(pos.x, 0); // вниз
        }
        calculateV(v, dest_pos);
        return super.keyDown(keycode);
    }

    void calculateV(Vector2 v, Vector2 dest_pos){
        v.set(dest_pos.cpy().sub(pos).cpy().nor().scl(2)); // рассчитаем скорость и направление движения (умножим на 2 чтобы побыстрее)
    }
}
