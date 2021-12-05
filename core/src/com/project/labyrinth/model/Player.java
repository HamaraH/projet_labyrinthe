package com.project.labyrinth.model;
import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.factory.Constante;

public class Player extends Entity {


    /**
     * create a player
     * @param world ,the world of game (manage all physics entities)
     * @param x ,position in x
     * @param y ,position in y
     * @param size ,size player
     */

    public enum direction {RIGHT, LEFT, UP, DOWN } ;
    private  direction sens;

    Player(World world, int x, int y, int size){


        sens = direction.DOWN;
        this.hp = 10;
        this.attackPoints = 1;
        this.size = size;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.set(new float[]{0f, 0f, 0f, size, size, size, size, 0f});

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0 ;
        fixtureDef.restitution = 0.25f ;
        fixtureDef.filter.categoryBits = Constante.CATEGORY_PLAYER;


        body.createFixture(fixtureDef);
        body.setLinearDamping(3f);
        body.setUserData("Player");

        shape.dispose();

    }

    public direction getSens() {
        return sens;
    }

    public void setSens(direction sens) {
        this.sens = sens;
    }

    public void stop(){
        body.setLinearVelocity(0,0);
    }
}