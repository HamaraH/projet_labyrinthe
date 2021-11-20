package com.project.labyrinth.model.wall;

import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.factory.Constante;
import com.project.labyrinth.model.wall.Wall;

public class WallLimit extends Wall {



    /**
     * create a wall
     * @param world ,the world of game (manage all physics entities)
     * @param posX ,position in x
     * @param posY ,position in y
     * @param size ,size wall
     */
    public WallLimit(World world, int posX, int posY, int sizeX, int sizeY, int size){
        super(world,posX,posY,sizeX,sizeY,size);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Constante.CATEGORY_WALL;
        body.createFixture(fixtureDef);
        shape.dispose();

    }




}
