package com.project.labyrinth.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.labyrinth.factory.TextureFactory;

public class WinScreen extends ScreenAdapter {


    private SpriteBatch spriteBatch;
    private OrthographicCamera camera ;


    /**
     * create win screen
     */
    public WinScreen(){

        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),  Gdx.graphics.getHeight() );
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        camera.update();
    }

    @Override
    public void render(float delta) {


        spriteBatch.begin();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.draw(TextureFactory.getInstance().getWin(), 0, 0, camera.viewportWidth, camera.viewportHeight) ;
        spriteBatch.end();

    }

    @Override
    public void resize(int width, int height) {

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
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
        spriteBatch.dispose();

    }



}
