package co.evecon.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import co.evecon.stargame.base.Sprite;
import co.evecon.stargame.math.Rect;
import co.evecon.stargame.math.Rnd;

public class LittleEnemyShip extends Sprite {

    private static final float SHIP_HEIGHT = 0.1f;

    private Vector2 v = new Vector2();
    private Vector2 v0 = new Vector2(0f, -0.05f);

    private Rect worldBounds;

    public LittleEnemyShip(TextureAtlas atlas, Rect worldBounds) {
        super(atlas.findRegion("enemy0"), 1, 2, 2);
        setHeightProportion(SHIP_HEIGHT);
        this.worldBounds = worldBounds;
        float posx = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posy = 1f;
        pos.set(posx, posy);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posx = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posy = 1f;
        pos.set(posx, posy);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v0, delta);
    }
}
