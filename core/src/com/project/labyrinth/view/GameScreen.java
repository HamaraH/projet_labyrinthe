package com.project.labyrinth.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.project.labyrinth.controller.Keyboard;
import com.project.labyrinth.factory.SoundFactory;
import com.project.labyrinth.model.Labyrinth;

public class GameScreen extends ScreenAdapter {


    Keyboard keyboard;
    Labyrinth labyrinth;
    OrthographicCamera camera ;
    SpriteBatch spriteBatch;


    public GameScreen(Labyrinth labyrinth) {


        this.labyrinth = labyrinth;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spriteBatch = new SpriteBatch();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        keyboard = new Keyboard();
        Gdx.input.setInputProcessor(keyboard);
        Gdx.graphics.setTitle("PokéRPG");

        SoundFactory.getInstance().getBackground_music().setLooping(true);
        SoundFactory.getInstance().getBackground_music().play();


    }


    private void updateMove(){

        labyrinth.getWorld().step(1/60f, 6, 2);
        labyrinth.getPlayer().applyForce(new Vector2(keyboard.getAccG().x, keyboard.getAccG().y));


    }

    @Override
    public void render(float delta) {

        updateMove();

        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        labyrinth.draw(spriteBatch);

        if(keyboard.isDebug()) {

            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
            box2DDebugRenderer.render(labyrinth.getWorld(), camera.combined);
        }

        spriteBatch.end();

    }


    @Override
    public void resize(int width, int height) {


        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();


    }

}