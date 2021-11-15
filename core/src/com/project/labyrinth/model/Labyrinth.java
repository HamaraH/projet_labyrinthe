package com.project.labyrinth.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.factory.TextureFactory;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Labyrinth {
    private int sizeX;
    private int sizeY;
    private int[][] map; //the map is made of zeroes and ones, as to show where the walls are
    private Player player;
    private List<Monster> monsters;
    private List<Wall> walls;
    private World world;
    private AtomicBoolean playerAttack ;


    /**
     * create the maze
     * @param sizeX ,size in X
     * @param sizeY ,size in Y
     */
    public Labyrinth(int sizeX, int sizeY){


        monsters = new ArrayList<>();
        walls = new ArrayList<>();
        playerAttack = new AtomicBoolean(false);
        world = new World(new Vector2(0, 0), true);
        player = new Player(world, Gdx.graphics.getWidth()/sizeX , Gdx.graphics.getHeight()/sizeY  , (Gdx.graphics.getHeight() / sizeY) - 10);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        Random rand = new Random();

        // Initialize the maze
        map = new int[this.sizeX][this.sizeY];
        for(int i = 0 ; i < sizeX ; i++){
            for(int j = 0 ; j < sizeY ; j++){
                    map[i][j] = 1;
            }
        }

        // Generate the maze
        map[1][1] = 0;
        mazeRecursion(1, 1);
        for(int i = 0; i < sizeY; i++)
            for(int j = 0; j < sizeX ; j++)
                if(map[j][i] == 1)
                    walls.add(new Wall(world, j, i, Gdx.graphics.getHeight() / sizeY));

        //Place the monsters randomly
        int nbMonsters = sizeX / 3;
        for(int i = 0; i < nbMonsters; i++){
            int x = -1;
            int y = -1;
            while((x <= 0 && y <= 0) || (map[x][y] != 0 || (x == 1 && y == 1))){
                x = rand.nextInt(sizeX-1) + 1;
                y = rand.nextInt(sizeY-1) + 1;
            }
            Monster nM = new Monster(world, x, y, 50, (Gdx.graphics.getHeight() / sizeY) - 10, Gdx.graphics.getHeight()/sizeY);
            monsters.add(nM);
            map[x][y] = 2;
        }

        new Timer().scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        actionMonsters();
                    }
                }, 1000, 1000);
    }


    /**
     *
     * @param x, number of cases in map
     * @param y, number of cases in map
     */
    private void mazeRecursion(int x, int y){
        // Creation of an array to contain the 4 directions in which we can create the maze
        Integer[] dir = randomDirection();

        //We go through the directions array to find our next path
        for(int i = 0; i < 4; i++){
            switch(dir[i]){
                case 0 : // up
                    // We skip if we're out of the maze
                    if(y - 2 <= 0)
                        continue;
                    // We select the next two tiles to create a path if it hasn't been marked yet
                    if(map[x][y - 2] == 1){
                        map[x][y - 1] = 0;
                        map[x][y - 2] = 0;
                        // Recursive call to continue building the path on the next chosen tile
                        mazeRecursion(x, y - 2);
                    }
                    break;
                case 1 : // down
                    if(y + 2 >= sizeY-1)
                        continue;
                    if(map[x][y + 2] == 1){
                        map[x][y + 1] = 0;
                        map[x][y + 2] = 0;
                        mazeRecursion(x, y + 2);
                    }
                    break;
                case 2 : // left
                    if(x - 2 <= 0)
                        continue;
                    if(map[x - 2][y] == 1){
                        map[x - 1][y] = 0;
                        map[x - 2][y] = 0;
                        mazeRecursion(x - 2, y);
                    }
                    break;
                case 3 : // right
                    if(x + 2 >= sizeX-1)
                        continue;
                    if(map[x + 2][y] == 1){
                        map[x + 1][y] = 0;
                        map[x + 2][y] = 0;
                        mazeRecursion(x + 2, y);
                    }
                    break;
            }
        }
    }

    /**
     *
     */
    private void actionMonsters(){
        Integer[] dir;
        for(Monster m : monsters){
            // Creation of an array to contain the 4 directions in which the monster could move
            dir = randomDirection();
            // Get the monster's position
            float mX1 = m.getPositionX();
            float mY1 = m.getPositionY();
            //System.out.println(mX1 + " ; " + mY1 + " -> " + mX1/m.getRatio() + " ; " + mY1/m.getRatio());
            m.setPosX((int)mX1/m.getRatio());
            m.setPosY((int)mY1/m.getRatio());

            int mX = m.getPosX();
            int mY = m.getPosY();

            //StringBuilder sb = new StringBuilder();
            //sb.append("Monster ( ").append(mX).append(" ; ").append(mY).append(" ) -> ( ");
            for(int i = 0; i < 4; i++) {
                switch (dir[i]){
                    case 0: // up
                        //System.out.println("Trying up");
                        if(mY + 1 >= 0 && map[mX][mY - 1] != 1) {
                            //System.out.println("Moving");
                            map[mX][mY] = 0;
                            m.move(0, -1);
                            i = 4;
                            m.setDirection(0);
                            //continue;
                        }
                        break;
                    case 1: // down
                        //System.out.println("Trying down");
                        if(mY + 1 < sizeY && map[mX][mY + 1] != 1) {
                            //System.out.println("Moving");
                            map[mX][mY] = 0;
                            m.move(0, 1);
                            i = 4;
                            m.setDirection(1);
                            //continue;
                        }
                        break;
                    case 2: // left
                        //System.out.println("Trying left");
                        if(mX - 1 >= 0 && map[mX - 1][mY] != 1) {
                            //System.out.println("Moving");
                            map[mX][mY] = 0;
                            m.move(-1, 0);
                            i = 4;
                            m.setDirection(2);
                            //continue;
                        }
                        break;
                    case 3: // right
                        //System.out.println("Trying right");
                        if(mY + 1 < sizeX && map[mX + 1][mY] != 1) {
                            //System.out.println("Moving");
                            map[mX][mY] = 0;
                            m.move(1, 0);
                            i = 4;
                            m.setDirection(3);
                            //continue;
                        }
                        break;
                }
                map[m.getPosX()][m.getPosY()] = 2;
            }

        }
    }

    /**
     *
     * @return ,Integer table
     */
    private Integer[] randomDirection(){
        ArrayList<Integer> dir = new ArrayList<>(4);
        for(int i = 0; i < 4; i++){
            dir.add(i);
        }
        // Shuffling the directions to get a random direction
        Collections.shuffle(dir);
        return dir.toArray(new Integer[4]);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++)
                if (map[j][i] == 1)
                    sb.append("# ");
                else if (map[j][i] == 0)
                    sb.append("; ");
                else if (map[j][i] == 2)
                    sb.append("k ");
            sb.append("\n");
        }
        return sb.toString();
    }


    /**
     * draw the sprites
     * @param spriteBatch, base sprite
     */
    public void draw(SpriteBatch spriteBatch){

        spriteBatch.draw(TextureFactory.getInstance().getMapTexture(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for(Wall w : walls){
            spriteBatch.draw(TextureFactory.getInstance().getWallTexture(), w.getPosX(), w.getPosY(), w.getSize(), w.getSize());
        }
        spriteBatch.draw(TextureFactory.getInstance().getPlayerTexture(), player.getPositionX(), player.getPositionY(), player.getSize(), player.getSize());

        for(Monster m : monsters) {
            spriteBatch.draw(TextureFactory.getInstance().getMonsterTexture1(), m.getPositionX() , m.getPositionY() , m.getSize(), m.getSize());

        }
    }


    /**
     * move the monsters, apply the force of monsters
     */
    public void moveMonsters(){
        for(Monster m : monsters) {
            switch (m.getDirection()){
                case 0:
                    m.applyForce(new Vector2(.0f, -500.0f));
                    break;
                case 1:
                    m.applyForce(new Vector2(.0f, 500.0f));
                    break;
                case 2:
                    m.applyForce(new Vector2(-500.0f, 0.0f));
                    break;
                case 3:
                    m.applyForce(new Vector2(500.0f, .0f));
                    break;
            }
        }

    }

    public void movePlayer(Vector2 vector2){
        player.applyForce(vector2);
    }


    /**
     * the player attacks the monster if a collision exists
     *
     */
    public void attack(){

        //ready to attack
        playerAttack.set(true);

        //test of contact
        world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {

                for(Monster m : monsters) {
                    if (contact.getFixtureB().getBody() == m.getBody() && contact.getFixtureA().getBody() == player.getBody()){
                        if(playerAttack.get()) {
                            m.hp = m.hp - player.attackPoints;

                            playerAttack.set(false);


                        }
                    }
                }

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }


        });

        //delete body monster
        for(Monster m : monsters) {
            if (m.getHp() <= 0) {
                world.destroyBody(m.getBody());
            }
        }

        //delete monster in arraylist
        monsters.removeIf(m -> m.hp <= 0);

    }


    public World getWorld(){
        return world;
    }

    public int getSizeX() {
        return sizeX * Gdx.graphics.getHeight() / sizeX;
    }

}
