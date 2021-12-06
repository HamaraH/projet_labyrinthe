package com.project.labyrinth.model.monster;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.project.labyrinth.factory.Constante;
import com.project.labyrinth.model.monster.Monster;


public class Monster2 extends Monster {

    /**
     * create a monster type 2
     * @param world ,the world of game (manage all physics entities)
     * @param x ,position in x
     * @param y ,position in y
     * @param hp ,life point
     * @param size ,size world
     * @param ratio ,screen ratio
     */

    public Monster2(World world, int x, int y, int hp, int size, int ratio) {
        super(world, x, y, hp, size, ratio);

        this.attackPoints = 1;
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0 ;
        fixtureDef.restitution = 0f ;
        fixtureDef.filter.categoryBits = Constante.CATEGORY_MONSTER2;

        body.createFixture(fixtureDef);
        shape.dispose();

    }
}