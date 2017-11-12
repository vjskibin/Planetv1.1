package su.pxsm.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by vjskibin on 11.11.2017.
 */

public class StartScreen implements Screen{

    private BitmapFont font;
    private SpriteBatch batch;
    private Game game;
    private Texture playBtn;
    private Sprite play;
    private OrthographicCamera cam;
    private Vector3 input;


    public StartScreen(Game game)
    {
        this.game = game;
    }

    @Override
    public void show()
    {
        font = new BitmapFont();
        font.setColor(0,0,0,1);
        font.getData().setScale(3);
        playBtn = new Texture(Gdx.files.internal("play.png"));
        play = new Sprite(playBtn);
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        play.setSize(Gdx.graphics.getHeight()/6,Gdx.graphics.getHeight()/6);
        play.setCenterX(Gdx.graphics.getWidth() / 2);
        play.setCenterY(Gdx.graphics.getHeight() / 2);
    }




    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255,255,255,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();

        //play.
        batch = new SpriteBatch();
        batch.begin();

        font.draw(batch, "Press anywhere to start...", 20, Gdx.graphics.getHeight() - 20);
        play.draw(batch);

        batch.end();


            //game.setScreen(new GameScreen(game));

       // }
        if(Gdx.input.isTouched())

        {
            int x1 = Gdx.input.getX();
            int y1 = Gdx.input.getY();
            input = new Vector3(x1, y1, 0);

            cam.unproject(input);
            //Now you can use input.x and input.y, as opposed to x1 and y1, to determine if the moving
            //sprite has been clicked

            if (play.getBoundingRectangle().contains(input.x, input.y)) {
                //Do whatever you want to do with the sprite when clicked
                game.setScreen(new GameScreen(game));
            }
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

        font.dispose();
        //game.dispose();
        batch.dispose();
        playBtn.dispose();

    }
}
