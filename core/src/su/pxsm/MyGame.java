package su.pxsm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import su.pxsm.view.GameScreen;
import su.pxsm.view.StartScreen;

public class MyGame extends Game {
	
	@Override
	public void create () {

		setScreen(new StartScreen(this));
	}


}
