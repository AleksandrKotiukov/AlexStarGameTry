package co.evecon.stargame.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import co.evecon.stargame.base.Sprite;
import co.evecon.stargame.math.Rect;

import static com.badlogic.gdx.Input.Keys.I;

public class MainShip extends Sprite {
    private static final float SHIP_HEIGHT = 0.15f;
    private static final float BUTTON_MARGIN = 0.05f;

    private Vector2 v = new Vector2();
    private Vector2 v0 = new Vector2(0.5f, 0f);

    private boolean pressedLeft;
    private boolean pressedRight;

    private Rect worldBounds;

    private int pointer;
    private boolean pressedMove;

    public MainShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        setHeightProportion(SHIP_HEIGHT);
        pressedMove = false;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + BUTTON_MARGIN);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkAndhandleBounds();
    }

    private void checkAndhandleBounds(){
        if(getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if(getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
    }

    public void keyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
        }
    }

    public void keyUp(int keyCode) {
        switch (keyCode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight == true) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft == true) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
    }

    public void touchDown(Vector2 touch, int pointer) {
        if (touch.x > this.getRight() && pressedMove == false) {
            pressedRight = true;
            this.pointer = pointer;
            pressedMove = true;
            moveRight();
        }
        if (touch.x < this.getLeft() && pressedMove == false) {
            pressedLeft = true;
            this.pointer = pointer;
            pressedMove = true;
            moveLeft();
        }
    }

    public void touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer || pressedMove == false){
            return;
        }
        pressedMove = false;
        pressedRight = false;
        pressedLeft = false;
        stop();
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void moveRight() {
        v.set(v0);
    }

    private void stop() {
        v.setZero();
    }
}
