package co.evecon.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import co.evecon.stargame.base.Base2DScreen;

//Menu screen

public class MenuScreen extends Base2DScreen {
    SpriteBatch batch;
    Texture img;
    Vector2 pos, endPos, dir;
    Vector2 v;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        pos = new Vector2(0f,0f);
        endPos = new Vector2(350f,200f);
        dir = endPos.sub(pos);
        v = new Vector2(dir.x/400,dir.y/400);
        System.out.println(v);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        pos.add(v);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        endPos = new Vector2(screenX,Gdx.graphics.getHeight() - screenY);
        dir = endPos.sub(pos);
        v = new Vector2(dir.x/400,dir.y/400);
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
