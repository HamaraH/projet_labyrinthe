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
import com.project.labyrinth.model.Player;

import java.util.Timer;
import java.util.TimerTask;

public class GameScreen extends ScreenAdapter {


    private Keyboard keyboard;
    private Labyrinth labyrinth;
    private ViewLabyrinth viewLabyrinth;
    private OrthographicCamera camera ;
    private SpriteBatch spriteBatch;
    private Game game;
    private PlayerAnimate playerAnimate;
    private boolean backToMenu = false;



    /**
     * create game display
     * @param labyrinth, model
     */
    public GameScreen(Labyrinth labyrinth, Game game) {



        this.game = game;
        this.labyrinth = labyrinth;
        this.viewLabyrinth = new ViewLabyrinth(labyrinth);
        camera = new OrthographicCamera(labyrinth.getSizeX(), labyrinth.getSizeX());
        spriteBatch = new SpriteBatch();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        keyboard = new Keyboard(labyrinth);
        Gdx.input.setInputProcessor(keyboard);
        Gdx.graphics.setTitle("PokéRPG");
        playerAnimate = new PlayerAnimate(labyrinth);

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
               viewLabyrinth.draw(spriteBatch);
                playerAnimate.act(delta);
                playerAnimate.draw(spriteBatch);
            }else{
                timerMenu();
                game.setScreen(new GameOverScreen());
            }

        }else{
            timerMenu();
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



    private void update(){

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
        TextureFactory.getInstance().dispose();
        SoundFactory.getInstance().dispose();
    }

    private void timerMenu(){
        new Timer().scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        backToMenu = true;
                    }
                }, 1500, 1500);
    }

    public boolean isBackToMenu() {
        return backToMenu;
    }
}