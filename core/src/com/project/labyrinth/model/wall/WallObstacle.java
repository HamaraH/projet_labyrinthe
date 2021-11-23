package com.project.labyrinth.model.wall;

import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.factory.Constante;



public class WallObstacle extends Wall {

    /**
     * create a wall
     *
     * @param world ,the world of game (manage all physics entities)
     * @param posX  ,position in x
     * @param posY  ,position in y
     * @param size  ,size wall
     */
    public WallObstacle(World world, int posX, int posY, int size) {
        super(world, posX, posY, size);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Constante.CATEGORY_WALL;
        fixtureDef.filter.maskBits = Constante.CATEGORY_MONSTER1 | Constante.CATEGORY_PLAYER;
        body.createFixture(fixtureDef);
        shape.dispose();

    }
}
