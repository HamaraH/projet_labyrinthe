package com.project.labyrinth.model.monster;

import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.model.Entity;


public abstract class Monster extends Entity {

    private int[] goal;
    protected PolygonShape shape;
    private boolean finishedMoving = true;


    /**
     * create a monster
     * @param world ,the world of game (manage all physics entities)
     * @param x ,position in x
     * @param y ,position in y
     * @param hp ,life point
     * @param size ,size world
     * @param ratio ,screen ratio
     */
    Monster(World world, int x, int y, int hp, int size, int ratio){

        this.posX = x;
        this.posY = y;
        this.hp = hp;

        this.size = size;
        this.goal = new int[]{-1, -1};

        int relativePosX = posX * ratio + 5;
        int relativePosY = posY * ratio + 5;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(relativePosX, relativePosY);
        body = world.createBody(bodyDef);

        shape = new PolygonShape();
        shape.set(new float[]{0f, 0f, 0f, size, size, size, size, 0f});


        body.setLinearDamping(0f);

        body.setUserData("Monster");



    }

    public int[] getGoal() {
        return goal;
    }

    public void setGoal(int[] goal) {
        this.goal = goal;
    }

    public boolean isMonster1(){
        return false;
    }

    public boolean isFinishedMoving() {
        return finishedMoving;
    }

    public void setFinishedMoving(boolean finishedMovingX) {
        this.finishedMoving = finishedMoving;
    }
}