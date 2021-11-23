package com.project.labyrinth.model.wall;
import com.badlogic.gdx.physics.box2d.*;


public abstract class Wall {

    private int posX;
    private int posY;
    private int size ;
    protected Body body;
    protected PolygonShape shape;



    /**
     * create a wall
     * @param world ,the world of game (manage all physics entities)
     * @param posX ,position in x
     * @param posY ,position in y
     * @param size ,size wall
     */
    Wall(World world, int posX, int posY,int size){

        this.posX = posX * size ;
        this.posY = posY * size ;
        this.size = size;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);
        shape = new PolygonShape();

        shape.set(new float[]{this.posX, this.posY,
                this.posX+size,  this.posY,
                this.posX + size ,  this.posY +size,
                this.posX, this.posY+size});

        body.setUserData("Wall");
    }



    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSize() {
        return size;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
