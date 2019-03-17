package ru.uzaretskaia.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.uzaretskaia.base.Base2DScreen;
import ru.uzaretskaia.sprite.Background;
import ru.uzaretskaia.sprite.Moon;

public class MenuScreen extends Base2DScreen {

    private Moon moon;
    private Background background;
    private Texture imgSpace;
    private Texture imgMoon;

    @Override
    public void show() {
        super.show();
        imgMoon = new Texture("Moon.png");
        moon = new Moon(new TextureRegion(imgMoon));
        imgSpace = new Texture("space.jpg");
        background = new Background(new TextureRegion(imgSpace), getWorldBoundsHeight(), getWorldBoundsWight());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        moon.draw(batch);
        batch.end();
        moon.update();
    }

    @Override
    public void dispose() {
        imgMoon.dispose();
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        background = new Background(new TextureRegion(imgSpace), getWorldBoundsHeight(), getWorldBoundsWight());
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        moon.touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        /*if (keycode == 21){
            dest_pos.set(0, pos.y); // влево
        } else if (keycode == 22){
            dest_pos.set(width, pos.y); // вправо
        } else if (keycode == 19){
            dest_pos.set(pos.x, height); // вверх
        } else if (keycode == 20) {
            dest_pos.set(pos.x, 0); // вниз
        }
        calculateV(v, dest_pos);*/
        return super.keyDown(keycode);
    }

}
