package co.evecon.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import co.evecon.stargame.base.Sprite;
import co.evecon.stargame.math.Rect;
import co.evecon.stargame.math.Rnd;

public class Star extends Sprite {

    private Vector2 v = new Vector2();
    private Rect worldBounds;

    public Star(TextureRegion region, float vx, float vy, float height) {
        super(region);
        v.set(vx, vy);
        setHeightProportion(height);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posx = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posy = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posx, posy);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkAndhandleBounds();
    }

    private void checkAndhandleBounds(){
        if(getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if(getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if(getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
        if(getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());
    }
}
