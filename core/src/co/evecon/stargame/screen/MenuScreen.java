package co.evecon.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import co.evecon.stargame.base.ActionListener;
import co.evecon.stargame.base.Base2DScreen;
import co.evecon.stargame.math.Rect;
import co.evecon.stargame.math.Rnd;
import co.evecon.stargame.sprite.Background;
import co.evecon.stargame.base.Button;
import co.evecon.stargame.sprite.ExitButton;
import co.evecon.stargame.sprite.PlayButton;
import co.evecon.stargame.sprite.Star;


/**
 * Экран меню
 */

public class MenuScreen extends Base2DScreen implements ActionListener {


    private Background background;
    private Texture bg;
    private int starAmount;
    private ArrayList<Star> star;
    private TextureAtlas atlas;

    private static final float PRESS_SCALE = 0.9f;
    private static final float BUTTON_HEIGHT = 0.15f;

    private ExitButton exitButton;
    private PlayButton playButton;

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
        exitButton = new ExitButton(atlas, PRESS_SCALE, this);
        exitButton.setHeightProportion(BUTTON_HEIGHT);
        playButton = new PlayButton(atlas, PRESS_SCALE, this);
        playButton.setHeightProportion(BUTTON_HEIGHT);
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
        exitButton.draw(batch);
        playButton.draw(batch);
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
        exitButton.touchDown(touch, pointer);
        playButton.touchDown(touch, pointer);
    }

    @Override
    public void touchUp(Vector2 touch, int pointer) {
        super.touchUp(touch, pointer);
        exitButton.touchUp(touch, pointer);
        playButton.touchUp(touch, pointer);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < starAmount; i++) {
            star.get(i).resize(worldBounds);
        }
        exitButton.resize(worldBounds);
        playButton.resize(worldBounds);
    }

    @Override
    public void actionPerformed(Object src) {
        if (src == exitButton){
            Gdx.app.exit();
        } else if (src == playButton){
            game.setScreen(new GameScreen(game));
        }
        else {
            throw new RuntimeException("Unknown src");
        }
    }
}
