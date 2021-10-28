package com.project.labyrinth;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {

	SpriteBatch map_batch;
	Texture map_texture;
	SpriteBatch player_batch;
	Texture player_texture;
	SpriteBatch wall;
	Texture wall_texture;
	Texture wall_texture_rotated;
	Music background_music;
	
	@Override
	public void create () {

		// creating the map
		map_batch = new SpriteBatch();
		map_texture= new Texture("grass.png");

		//creating the player object

		player_batch = new SpriteBatch();
		player_texture= new Texture("player_standing.png");

		// creating the texture of walls
		wall = new SpriteBatch();
		wall_texture = new Texture("wall_texture.png");
		wall_texture_rotated = new Texture("wall_texture_rotated.png");
		// music

		background_music = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));
		background_music.setLooping(true);
		background_music.play();

		// changing the window name and size
		Gdx.graphics.setTitle("PokéRPG");

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		map_batch.begin();
		map_batch.draw(map_texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


		map_batch.end();

		wall.begin();
		display_walls();

		wall.end();

		player_batch.begin();
		player_batch.draw(player_texture, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 30, 30);
		player_batch.end();
	}
	
	@Override
	public void dispose () {
		map_batch.dispose();
		map_texture.dispose();
	}

	public void display_walls(){

		for(int i =0 ; i<Gdx.graphics.getWidth() ; i = i+25){
			wall.draw(wall_texture,i,0 , 25, 25);
			wall.draw(wall_texture,i,Gdx.graphics.getHeight()-25 , 25, 25);
		}

		for(int i =0 ; i<Gdx.graphics.getHeight() ; i = i+25){
			wall.draw(wall_texture_rotated,0,i , 25, 25);
			wall.draw(wall_texture_rotated,Gdx.graphics.getWidth()-25,i , 25, 25);
		}



	}
}
