package co.evecon.stargame.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import co.evecon.stargame.Utils.Regions;
import co.evecon.stargame.math.Rect;

public class Sprite extends Rect {

    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame;

    public Sprite (TextureRegion region){
        if (region == null){
            throw new NullPointerException();
        }
        regions = new TextureRegion[1];
        regions[0] = region;
    }

    public Sprite (TextureRegion region, int rows, int cols, int frames){
        if (region == null){
            throw new NullPointerException();
        }
        regions = Regions.split(region, rows, cols, frames);
    }

    public void draw(SpriteBatch batch){
        batch.draw(
                regions[frame], //region to draw
                getLeft(),getBottom(), //starting drawing point
                halfWidth, halfHeight, //rotating point
                getWidth(), getHeight(), //width and height
                scale, scale, //scaling per x and per y
                angle //rotating angle
        );
    }

    public void setHeightProportion(float height){
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float)regions[frame].getRegionHeight();
        setWidth(height * aspect);
    };

    public void resize(Rect worldBounds){}


    public void touchDown(Vector2 touch, int pointer) {
    }

    public void touchUp(Vector2 touch, int pointer){
    }

    public void update(float delta){}

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}