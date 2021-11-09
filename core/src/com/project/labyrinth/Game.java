package com.project.labyrinth;
import com.badlogic.gdx.Gdx;
import com.project.labyrinth.model.Labyrinth;
import com.project.labyrinth.view.GameScreen;

public class Game extends com.badlogic.gdx.Game {

	Labyrinth labyrinth ;

	public GameScreen gameScreen;

	@Override
	public void create () {

		labyrinth = new Labyrinth(9,9);
		gameScreen = new GameScreen(labyrinth);
		this.setScreen(gameScreen);

	}


	@Override
	public void render () {
		super.render();

	}

	@Override
	public void resize(int width, int height){
		super.resize(width,height);

	}


	@Override
	public void dispose () {
		gameScreen.dispose();
		super.dispose();
	}
}
