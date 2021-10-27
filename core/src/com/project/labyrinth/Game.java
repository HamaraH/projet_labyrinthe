package com.project.labyrinth;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {
	SpriteBatch map_batch;
	Texture map_texture;
	SpriteBatch player_batch;
	Texture player_texture;

	
	@Override
	public void create () {
		// creating the map
		map_batch = new SpriteBatch();
		map_texture= new Texture("grass.png");

		//creating the player object

		player_batch = new SpriteBatch();
		player_texture= new Texture("player_standing.png");

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		map_batch.begin();
		map_batch.draw(map_texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		map_batch.end();

		player_batch.begin();
		player_batch.draw(player_texture, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 50, 50);
		player_batch.end();
	}
	
	@Override
	public void dispose () {
		map_batch.dispose();
		map_texture.dispose();
	}
}
