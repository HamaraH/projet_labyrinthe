package com.project.labyrinth.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.labyrinth.factory.TextureFactory;
import com.project.labyrinth.model.Labyrinth;
import com.project.labyrinth.model.Potion;
import com.project.labyrinth.model.monster.Monster;
import com.project.labyrinth.model.wall.Wall;

public class ViewLabyrinth {

    private Labyrinth labyrinth;

    public ViewLabyrinth(Labyrinth l){
        this.labyrinth = l;
    }

    /**
     * draw the sprites
     * @param spriteBatch, base sprite
     */
    public void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(TextureFactory.getInstance().getMapTexture(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for(Wall w : labyrinth.getWalls()){
            spriteBatch.draw(TextureFactory.getInstance().getWallTexture(), w.getPosX(), w.getPosY(), w.getSize(), w.getSize());
        }
        spriteBatch.draw(TextureFactory.getInstance().getPlayerTexture(), labyrinth.getPlayer().getPositionX(), labyrinth.getPlayer().getPositionY(), labyrinth.getPlayer().getSize(), labyrinth.getPlayer().getSize());

        for(Monster m : labyrinth.getMonsters()) {
            if (m.isMonster1())
                spriteBatch.draw(TextureFactory.getInstance().getMonsterTexture2(), m.getPositionX(), m.getPositionY(), m.getSize(), m.getSize());
            else
                spriteBatch.draw(TextureFactory.getInstance().getMonsterTexture1(), m.getPositionX(), m.getPositionY() , m.getSize(), m.getSize());

        }
        for(Potion p : labyrinth.getPotions()) {
            spriteBatch.draw(TextureFactory.getInstance().getPotionOfLife(), p.getBodyPositionX(), p.getBodyPositionY(), p.getSize(), p.getSize());
        }
        if(labyrinth.getEndCell().isTreasureCell()) {
            spriteBatch.draw(TextureFactory.getInstance().getTreasure(), labyrinth.getEndCell().getBodyPositionX(), labyrinth.getEndCell().getBodyPositionY(), labyrinth.getEndCell().getSize(), labyrinth.getEndCell().getSize());
        }else{
            spriteBatch.draw(TextureFactory.getInstance().getGangway(), labyrinth.getEndCell().getBodyPositionX(), labyrinth.getEndCell().getBodyPositionY(), labyrinth.getEndCell().getSize(), labyrinth.getEndCell().getSize());
        }

        for(int i = 0; i < labyrinth.getPlayer().getHp(); i++){
            spriteBatch.draw(TextureFactory.getInstance().getHeart(), 0, i*32, labyrinth.getWalls().get(0).getSize(), labyrinth.getWalls().get(0).getSize());
        }
    }
}
