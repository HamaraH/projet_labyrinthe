package com.project.labyrinth.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.project.labyrinth.Game;
import com.project.labyrinth.controller.Keyboard;
import com.project.labyrinth.factory.SoundFactory;
import com.project.labyrinth.factory.TextureFactory;
import com.project.labyrinth.model.Labyrinth;

public class GameScreen extends ScreenAdapter {


    private Keyboard keyboard;
    private Labyrinth labyrinth;
    private OrthographicCamera camera ;
    private SpriteBatch spriteBatch;
    private Game game;



    /**
     * create game display
     * @param labyrinth, model
     */
    public GameScreen(Labyrinth labyrinth, Game game) {



        this.game = game;
        this.labyrinth = labyrinth;
        camera = new OrthographicCamera(labyrinth.getSizeX(), labyrinth.getSizeX());
        spriteBatch = new SpriteBatch();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        keyboard = new Keyboard(labyrinth);
        Gdx.input.setInputProcessor(keyboard);
        Gdx.graphics.setTitle("PokéRPG");

        //play music in loop
        SoundFactory.getInstance().getBackgroundMusic().setLooping(true);
        SoundFactory.getInstance().getBackgroundMusic().play();
    }


    /**
     *  ------- javadoc of the interface screen -----
     * Called when the screen should render itself.
     * @param delta The time in seconds since the last render
     */
    @Override
    public void render(float delta) {


        labyrinth.getWorld().step(1/60f, 6, 2);
        labyrinth.movePlayer(new Vector2(keyboard.getAcc().x, keyboard.getAcc().y));
        labyrinth.moveMonsters();
        labyrinth.effectCell();



        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        spriteBatch.begin();
        if(!labyrinth.isTreasure()) {
            if(!labyrinth.isGameOver()){
                labyrinth.draw(spriteBatch);
            }else{
                game.setScreen(new GameOverScreen());
            }

        }else{

            game.setScreen(new WinScreen());
        }

        //display bodies
        if(keyboard.isDebug()) {

            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
            box2DDebugRenderer.render(labyrinth.getWorld(), camera.combined);
        }


        camera.update();
        spriteBatch.end();

    }


    /**
     * resize of screen
     * @param width ,of screen
     * @param height ,of screen
     */
    @Override
    public void resize(int width, int height) {
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

    }


    /**
     * ------- javadoc of the interface screen -----
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        spriteBatch.dispose();
    }

}