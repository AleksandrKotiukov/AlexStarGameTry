package co.evecon.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import co.evecon.stargame.base.Base2DScreen;
import co.evecon.stargame.base.Sprite;
import co.evecon.stargame.math.Rect;
import co.evecon.stargame.math.Rnd;
import co.evecon.stargame.sprite.Background;
import co.evecon.stargame.sprite.Button;
import co.evecon.stargame.sprite.Star;


/**
 * Экран меню
 */

public class MenuScreen extends Base2DScreen {


    private Background background;
    private Texture bg;
    private int starAmount;
    private ArrayList<Star> star;
    private TextureAtlas atlas;
    private Button playButton, exitButton;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        TextureRegion starRegion = atlas.findRegion("star");
        starAmount = 250;
        star = new ArrayList<Star>();
        for (int i = 0; i < starAmount ; i++) {
            star.add(new Star(starRegion, Rnd.nextFloat(-0.005f,0.005f), Rnd.nextFloat(-0.5f,-0.1f), 0.01f));
        }
        TextureRegion playButtonRegion = atlas.findRegion("btPlay");
        playButton = new Button(playButtonRegion,0.2f);
        playButton.setLeft();
        TextureRegion exitButtonRegion = atlas.findRegion("btExit");
        exitButton = new Button(exitButtonRegion,0.2f);
        exitButton.setRight();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta){
        for (int i = 0; i < starAmount; i++) {
            star.get(i).update(delta);
        }
    }

    public void draw(){
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < starAmount; i++) {
            star.get(i).draw(batch);
        }
        playButton.draw(batch);
        exitButton.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public void touchDown(Vector2 touch, int pointer) {
        super.touchDown(touch, pointer);
        if (playButton.isMe(touch)) {
            playButton.setScale(0.5f);
        }
        if (exitButton.isMe(touch)) {
            exitButton.setScale(0.5f);
        }
    }

    @Override
    public void touchUp(Vector2 touch, int pointer) {
        super.touchUp(touch, pointer);
        if (playButton.isMe(touch)) {
            playButton.setScale(1f);
        }
        if (exitButton.isMe(touch)) {
            exitButton.setScale(1f);
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < starAmount; i++) {
            star.get(i).resize(worldBounds);
        }
        playButton.resize(worldBounds);
        exitButton.resize(worldBounds);
    }
}
