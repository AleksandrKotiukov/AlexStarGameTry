package co.evecon.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import co.evecon.stargame.base.Sprite;
import co.evecon.stargame.math.Rect;

public class Button extends Sprite {
    //We have two buttons and they will be align to the bottom. So we need to understand should the button be align to the left or to the right.
    private boolean setLeft; //If those logic variable is true the button will be align to the left if it is false the button will be align to the right

    public Button(TextureRegion region, float height) {
        super(region);
        setHeightProportion(height);
        setLeft = true;
    }

    public void setLeft(){
        setLeft = true;
    }

    public void setRight(){
        setLeft = false;
    }

    @Override
    public void resize(Rect worldBounds) {
        if (setLeft) {
            float posX = worldBounds.getLeft() + getHalfWidth();
            float posY = worldBounds.getBottom() + getHalfHeight();
            pos.set(posX,posY);
        }
        if (!setLeft){
            float posX = worldBounds.getRight() - getHalfWidth();
            float posY = worldBounds.getBottom() + getHalfHeight();
            pos.set(posX,posY);
        }
    }
}
