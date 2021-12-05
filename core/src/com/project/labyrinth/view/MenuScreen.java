package com.project.labyrinth.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.labyrinth.Game;
import com.project.labyrinth.controller.MenuKeyboard;
import com.project.labyrinth.factory.TextureFactory;
import com.project.labyrinth.model.Labyrinth;

public class MenuScreen extends ScreenAdapter {
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private static final int borderWidth = 5;
    private boolean newGame = true;
    private MenuKeyboard menuKeyboard;
    private boolean quit = false, start = false;

    public MenuScreen(){
        menuKeyboard = new MenuKeyboard(this);
        Gdx.input.setInputProcessor(menuKeyboard);

        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),  Gdx.graphics.getHeight() );
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        camera.update();
        menuKeyboard = new MenuKeyboard(this);
        Gdx.input.setInputProcessor(menuKeyboard);
        Gdx.graphics.setTitle("PokéRPG");
    }

    @Override
    public void render(float delta) {


        spriteBatch.begin();
        Gdx.gl.glClearColor(255f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.draw(TextureFactory.getInstance().getMenu(), 0, 0, camera.viewportWidth, camera.viewportHeight) ;
        if(newGame){
            spriteBatch.draw(TextureFactory.getInstance().getSelector(), camera.viewportWidth/4, 4*camera.viewportHeight/9 + (borderWidth * 2), camera.viewportWidth/2, camera.viewportHeight/10);
        }else{

            spriteBatch.draw(TextureFactory.getInstance().getSelector(), camera.viewportWidth/4, 2*camera.viewportHeight/9 - (borderWidth * 2), camera.viewportWidth/2, camera.viewportHeight/10);
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

    public boolean isNewGame() {
        return newGame;
    }

    public void setNewGame(boolean newGame) {
        this.newGame = newGame;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
