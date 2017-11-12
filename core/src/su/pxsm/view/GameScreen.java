package su.pxsm.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import su.pxsm.model.GameObject;


/**
 * Created by vjskibin on 11.11.2017.
 */

public class GameScreen implements Screen {

    private Texture texture;
    private Texture appear;
    private SpriteBatch batch;
    private Sprite playerPlanet;
    private Sprite appearObject;
    //OrthographicCamera camera;
    Vector3 touchPos;
    Rectangle plan;
    //Vector3 touchPos;
    Array<Rectangle> elementAppear;
    long lastAppear;
    BitmapFont font;
    private Game game;

    public GameScreen(Game game)
    {
        this.game = game;
    }

    private void spawnElement()
    {
        Rectangle elappear = new Rectangle();
        elappear.x = MathUtils.random(0,Gdx.graphics.getWidth() - Gdx.graphics.getHeight() / 6);
        elappear.y = MathUtils.random(0,Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 6);
        elappear.width = Gdx.graphics.getHeight() / 6;
        elappear.height = Gdx.graphics.getHeight() / 6;
        elementAppear.add(elappear);
        lastAppear = TimeUtils.millis();

    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("planet.png"));
        appear = new Texture(Gdx.files.internal("person.png"));
        //camera = new OrthographicCamera();
        touchPos = new Vector3();
        //camera.setToOrtho(false,Gdx.graphics.getWidth()*2,Gdx.graphics.getHeight()*2);
        plan = new Rectangle();
        font = new BitmapFont();
        font.setColor(0,0,0,1);
        //font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(4);



        plan.x = Gdx.graphics.getWidth()/2;
        plan.y = Gdx.graphics.getHeight()/2;
        plan.width = Gdx.graphics.getHeight()/3;
        plan.height = Gdx.graphics.getHeight()/3;

        elementAppear = new Array<Rectangle>();

        appearObject = new Sprite(appear);
        playerPlanet = new Sprite(texture);

        playerPlanet.setSize(Gdx.graphics.getHeight() / 3, Gdx.graphics.getHeight() / 3);
        playerPlanet.setCenter(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight()/2);

        appearObject.setSize(Gdx.graphics.getHeight() / 6, Gdx.graphics.getHeight() / 6);
        spawnElement();

    }

    //private float posX = 0f;
    //private float scale = Gdx.graphics.getHeight()/Gdx.graphics.getWidth();
    int score = 0;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255,255,255,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       // camera.update();
       // batch.setProjectionMatrix(camera.combined);
        //posX++;
        batch.begin();
        for (Rectangle elappear: elementAppear)
        //    batch.draw(appear, elappear.x,elappear.y,Gdx.graphics.getHeight()/6,Gdx.graphics.getHeight()/6);
        {
            appearObject.setCenter(elappear.x,elappear.y);
            appearObject.draw(batch);
        }

        playerPlanet.setCenter(plan.x, plan.y);
        playerPlanet.draw(batch);




        //batch.draw(texture, plan.x, plan.y,Gdx.graphics.getHeight()/3,Gdx.graphics.getHeight()/3);

        font.draw(batch, Integer.toString(score), 60, 80);
        batch.end();

        if (Gdx.input.isTouched())
        {
         //   Vector3 touchPos = new Vector3();
           // touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            //camera.unproject(touchPos);
            plan.x = Gdx.input.getX();
            plan.y = Math.abs(Gdx.graphics.getHeight()-Gdx.input.getY());
        }




        if (TimeUtils.millis() - lastAppear > 1000 - score*2) spawnElement();
        Iterator<Rectangle> iter = elementAppear.iterator();
        while(iter.hasNext())
        {
            Rectangle elAppear = iter.next();
            if(elAppear.overlaps(plan))
            {
                iter.remove();
                score++;
            }
        }

        if (elementAppear.size > 3)
        {
            game.setScreen(new FailScreen(game, score));
        }

    }
  
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        texture.dispose();
        batch.dispose();
        appear.dispose();
        font.dispose();
        //game.dispose();
    }
}
