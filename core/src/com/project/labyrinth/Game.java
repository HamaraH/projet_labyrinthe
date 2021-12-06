package com.project.labyrinth;
import com.badlogic.gdx.Gdx;
import com.project.labyrinth.factory.TextureFactory;
import com.project.labyrinth.model.Labyrinth;
import com.project.labyrinth.view.GameScreen;
import com.project.labyrinth.view.MenuScreen;

public class Game extends com.badlogic.gdx.Game {


	private GameScreen gameScreen;
	private MenuScreen menuScreen;
	private boolean started = false;

	/**
	 * create the labyrinth and the screen game
	 */
	@Override
	public void create ()
	{
		Gdx.graphics.setTitle("PokéRPG");
		launchMenu();
	}


	/**
	 * --- javadoc of game ----
	 * Called when the Application should render itself.
	 */
	@Override
	public void render ()
	{

		super.render();

		if (started) {
			if (gameScreen.isBackToMenu()){
				launchMenu();
				started = false;
			}
		}else {
			if (menuScreen.isStart() && !started) {
				launchGame();
			}
			if (menuScreen.isQuit()) {
				Gdx.app.exit();
			}
		}
	}


	/**
	 * ----- javacdoc of game -----
	 * Called when the Application is resized
	 * @param width ,the new width in pixels
	 * @param height ,the new height in pixels
	 */
	@Override
	public void resize(int width, int height){
		super.resize(width,height);

	}


	/**
	 * ---- javadoc of game -----
	 * Called when the Application is destroyed
	 */
	@Override
	public void dispose () {
		if(started)
			gameScreen.dispose();
		else
			menuScreen.dispose();

		super.dispose();
	}

	private void launchMenu(){
		menuScreen = new MenuScreen();
		this.setScreen(menuScreen);
	}

	private void launchGame(){
		Labyrinth labyrinth = new Labyrinth(19, 19);
		gameScreen = new GameScreen(labyrinth, this);
		this.setScreen(gameScreen);
		started = true;
		menuScreen.setStart(false);
	}
}