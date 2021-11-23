package com.project.labyrinth.model.monster;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.project.labyrinth.factory.Constante;
import com.project.labyrinth.model.monster.Monster;


public class Monster1 extends Monster {
    public Monster1(World world, int x, int y, int hp, int size, int ratio) {
        super(world, x, y, hp, size, ratio);

        this.attackPoints = 1;
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0 ;
        fixtureDef.restitution = 0.f ;
        fixtureDef.filter.categoryBits = Constante.CATEGORY_MONSTER1;

        body.createFixture(fixtureDef);
        shape.dispose();

    }

    @Override
    public boolean isMonster1() {
        return true;
    }
}