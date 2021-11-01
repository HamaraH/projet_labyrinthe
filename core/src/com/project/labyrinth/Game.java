package com.project.labyrinth;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.project.labyrinth.model.Labyrinth;

public class Game extends ApplicationAdapter {

	Labyrinth labyrinth = new Labyrinth(9, 9);

	SpriteBatch map_batch;
	Texture map_texture;
	SpriteBatch player_batch;
	Texture player_texture;
	SpriteBatch wall;
	Texture wall_texture;
	Texture wall_texture_rotated;
	Music background_music;

	SpriteBatch monster_batch1;
	Texture monster_texture1;

	SpriteBatch monster_batch2;
	Texture monster_texture2;

	SpriteBatch obstacle_batch;
	Texture obstacle_texture;

	int random_positionX1;
	int random_positionY1;

	int random_positionX2;
	int random_positionY2;


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

		//creating the monsters

		monster_batch1 = new SpriteBatch();
		monster_texture1 = new Texture("monster1.png");

		monster_batch2 = new SpriteBatch();
		monster_texture2 = new Texture("monster2.png");

		random_positionX1 = (int)(Math.random() * (Gdx.graphics.getWidth()-50)+25);
		random_positionY1 = (int) (Math.random() * (Gdx.graphics.getHeight()-50)+25);

		random_positionX2 = (int)(Math.random() * (Gdx.graphics.getWidth()-50)+25);
		random_positionY2 = (int) (Math.random() * (Gdx.graphics.getHeight()-50)+25);

		//obstacle

		obstacle_batch = new SpriteBatch();
		obstacle_texture = new Texture("obstacle.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		//System.out.println("\n-----------------------------------------------------\n");
		//labyrinth.actionMonsters();
		//System.out.println(labyrinth.toString());


		map_batch.begin();
		map_batch.draw(map_texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		map_batch.end();

		wall.begin();
		display_walls();

		wall.end();

		player_batch.begin();
		player_batch.draw(player_texture, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 30, 30);
		player_batch.end();

		monster_batch1.begin();
		monster_batch1.draw(monster_texture1, random_positionX1, random_positionY1, 30, 30);
		monster_batch1.end();

		monster_batch2.begin();
		monster_batch2.draw(monster_texture2, random_positionX2, random_positionY2, 50, 50);
		monster_batch2.end();

		obstacle_batch.begin();
		obstacle_batch.draw(obstacle_texture,Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/3, 100, 50);
		obstacle_batch.end();

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
