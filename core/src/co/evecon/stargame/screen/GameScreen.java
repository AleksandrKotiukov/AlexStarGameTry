package co.evecon.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

import co.evecon.stargame.base.Base2DScreen;
import co.evecon.stargame.base.Sprite;
import co.evecon.stargame.math.Rect;
import co.evecon.stargame.math.Rnd;
import co.evecon.stargame.sprite.Background;
import co.evecon.stargame.sprite.BigEnemyShip;
import co.evecon.stargame.sprite.LittleEnemyShip;
import co.evecon.stargame.sprite.MainShip;
import co.evecon.stargame.sprite.MediumEnemyShip;
import co.evecon.stargame.sprite.Star;

public class GameScreen extends Base2DScreen {

    Random generator = new Random();
    private Background background;
    private Texture bg;
    private int starAmount;
    private int nextShip;
    private ArrayList<Star> star;
    private TextureAtlas atlas, mainAtlas;
    private MainShip mainShip;
    private ArrayList<Sprite> enemyShips;
    private float generateInterval = 4f;
    private float generateTimer = 4f;
    private Rect worldBounds;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        mainAtlas = new TextureAtlas("textures/mainAtlas.tpack");
        TextureRegion starRegion = atlas.findRegion("star");
        starAmount = 250;
        star = new ArrayList<Star>();
        for (int i = 0; i < starAmount; i++) {
            star.add(new Star(starRegion, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), 0.01f));
        }
        mainShip = new MainShip(mainAtlas);
        enemyShips = new ArrayList<Sprite>();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        for (int i = 0; i < starAmount; i++) {
            star.get(i).update(delta);
        }
        mainShip.update(delta);
        generateTimer += delta;
        if (generateInterval <= generateTimer) {
            generateTimer = 0f;
            nextShip = generator.nextInt(100);
            if (nextShip > 0 && nextShip <= 10) {
                enemyShips.add(new BigEnemyShip(mainAtlas, worldBounds));
            } else if (nextShip > 10 && nextShip <= 20) {
                enemyShips.add(new MediumEnemyShip(mainAtlas, worldBounds));
            } else if (nextShip > 20 && nextShip <= 100) {
                enemyShips.add(new LittleEnemyShip(mainAtlas, worldBounds));
            }
            System.out.println(nextShip);
        }
        for (int i = 0; i < enemyShips.size(); i++) {
            enemyShips.get(i).update(delta);
        }
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < starAmount; i++) {
            star.get(i).draw(batch);
        }
        mainShip.draw(batch);
        for (int i = 0; i < enemyShips.size(); i++) {
            enemyShips.get(i).draw(batch);
        }
        batch.end();
    }

    public void checkCollisions() {
    }

    public void deleteAllDestroyed() {
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    public void touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
    }

    @Override
    public void touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < starAmount; i++) {
            star.get(i).resize(worldBounds);
        }
        mainShip.resize(worldBounds);
        for (int i = 0; i < enemyShips.size(); i++) {
            enemyShips.get(i).resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        mainAtlas.dispose();
    }
}
