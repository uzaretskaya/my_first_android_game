package ru.uzaretskaia.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.uzaretskaia.base.Sprite;

public class Background extends Sprite {

    public Background(TextureRegion region, float height, float wight) {
        super(region);
        setSize(wight, height);
        this.pos = new Vector2();
    }

}
