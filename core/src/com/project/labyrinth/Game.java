package com.project.labyrinth;
import com.project.labyrinth.factory.TextureFactory;
import com.project.labyrinth.model.Labyrinth;
import com.project.labyrinth.view.GameScreen;

public class Game extends com.badlogic.gdx.Game {


	private GameScreen gameScreen;

	/**
	 * create the labyrinth and the screen game
	 */
	@Override
	public void create () {

		Labyrinth labyrinth = new Labyrinth(19, 19);
		gameScreen = new GameScreen(labyrinth, this);
		this.setScreen(gameScreen);
	}


	/**
	 * --- javadoc of game ----
	 * Called when the Application should render itself.
	 */
	@Override
	public void render ()
	{

		super.render();
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
		gameScreen.dispose();

		super.dispose();
	}
}