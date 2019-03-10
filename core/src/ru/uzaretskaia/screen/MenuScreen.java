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
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(space, 0, 0);
        batch.draw(moon, pos.x, pos.y);
        batch.end();

//        System.out.println("delta.x=" + Math.abs(Math.abs(dest_pos.x) - Math.abs(pos.x))
//                + '\t' + "delta.y=" + Math.abs(Math.abs(dest_pos.y) - Math.abs(pos.y))
//                + '\t' + "pos.x=" + pos.x + '\t' + "pos.y=" + pos.y
//                + '\t' + "abs_pos.x=" + pos.x + moon.getWidth() + '\t' + "abs_pos.y=" + pos.y + moon.getHeight()
//                + '\t' + "dest.x=" + dest_pos.x + '\t' + "dest.y=" + dest_pos.y);

        // 2 - произвольное число, цель проверки, чтобы обе координаты положения были как можно ближе к координатам назначения
        if (Math.abs(Math.abs(dest_pos.x) - Math.abs(pos.x)) <= 2 && Math.abs(Math.abs(dest_pos.y) - Math.abs(pos.y)) <= 2) {
            v.setZero();
        }
        pos.add(v);

        // Проверку ниже пока не получилось реализовать, не разобралась, как привялаться именно к границам экрана
//        // Проверим, подошли ли к границам экрана
//        if ((v.x != 0 && v.y != 0) && (pos.x + moon.getWidth() >= space.getWidth()
//        || pos.x <= 0
//        || pos.y + moon.getHeight() >= space.getHeight()
//        || pos.y <= 0)){
//            v.setZero();
//        }
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
//        System.out.println("---------------------------");
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
//        System.out.println("touch.x = " + touch.x + " touch.y = " + touch.y);

        dest_pos.set(touch.x, touch.y);
        v.set(dest_pos.cpy().sub(pos).cpy().nor()); // через разность вычислим направление и нормализуем вектор

//        System.out.println("v.x = " + v.x + " v.y = " + v.y);
//        System.out.println("pos.x = " + pos.x + " pos.y = " + pos.y);
//        System.out.println("dest_pos.x = " + dest_pos.x + " dest_pos.y = " + dest_pos.y);

        return super.touchDown(screenX, screenY, pointer, button);
    }
}
