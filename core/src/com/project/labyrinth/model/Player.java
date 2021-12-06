package com.project.labyrinth.model;
import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.factory.Constante;
import com.project.labyrinth.factory.SoundFactory;
import com.project.labyrinth.model.monster.Monster;

public class Player extends Entity {



    public enum direction {RIGHT, LEFT, UP, DOWN } ;
    private  direction sens;
    private static final int BASEHP = 10;
    private static final int BOOSTHP = 3;

    /**
     * create a player
     * @param world ,the world of game (manage all physics entities)
     * @param x ,position in x
     * @param y ,position in y
     * @param size ,size player
     */
    Player(World world, int x, int y, int size, int lvl){


        sens = direction.DOWN;
        this.hp = BASEHP + (BOOSTHP * lvl);
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
    public void attack(Monster m){
        SoundFactory.getInstance().getAttack().play();
        m.setHp(m.getHp() - getAttackPoints());

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