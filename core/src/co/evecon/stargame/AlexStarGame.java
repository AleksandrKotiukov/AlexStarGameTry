package co.evecon.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class AlexStarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture backgroundImg;
	Vector2 pos;
	Vector2 v;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
//		backgroundImg = new Texture("kayak.jpg");
		pos = new Vector2(0f,0f);
		v = new Vector2(0.5f,0.3f);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		pos.add(v);
		batch.begin();
//		batch.draw(backgroundImg, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(img, pos.x, pos.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
//		backgroundImg.dispose();
	}
}
