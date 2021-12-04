package com.project.labyrinth.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.factory.TextureFactory;


import com.project.labyrinth.model.cellEffect.CellEnd;
import com.project.labyrinth.model.cellEffect.CellTrap;
import com.project.labyrinth.model.cellEffect.CellTreasure;
import com.project.labyrinth.model.monster.Monster;
import com.project.labyrinth.model.monster.Monster1;
import com.project.labyrinth.model.monster.Monster2;
import com.project.labyrinth.model.wall.Wall;
import com.project.labyrinth.model.wall.WallLimit;
import com.project.labyrinth.model.wall.WallObstacle;
import com.project.labyrinth.pathfinding.AStar;


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
    //private AtomicBoolean monsterAttack;
    private List<Potion> potions;
    private List<CellTrap> traps;
    private CellEnd endCell;
    private int cptLaby ;
    private Random rand;
    private boolean gameOver;
    private AStar pathfinder;
    private int ratio;

    /**
     * create the maze
     * @param sizeX ,size in X
     * @param sizeY ,size in Y
     */
    public Labyrinth(int sizeX, int sizeY){
        this.ratio = Gdx.graphics.getHeight()/sizeY;
        cptLaby = 1;
        initialisation(sizeX, sizeY);

    }


    /**
     *
     * @param x, number of cases in map
     * @param y, number of cases in map
     */
    private void mazeRecursion(int x, int y){
        // Creation of an array to contain the 4 directions in which we can create the maze
        Integer[] dir = randomDirection();

        Random rand = new Random();

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
                    }else{
                        if(rand.nextInt(sizeY/2) == 0) {
                            map[x][y - 1] = 0;
                            mazeRecursion(x, y - 2);
                        }
                    }
                    break;
                case 1 : // down
                    if(y + 2 >= sizeY-1)
                        continue;
                    if(map[x][y + 2] == 1){
                        map[x][y + 1] = 0;
                        map[x][y + 2] = 0;
                        mazeRecursion(x, y + 2);
                    }else{
                        if(rand.nextInt(sizeY/2) == 0) {
                            map[x][y + 1] = 0;
                            mazeRecursion(x, y + 2);
                        }
                    }
                    break;
                case 2 : // left
                    if(x - 2 <= 0)
                        continue;
                    if(map[x - 2][y] == 1){
                        map[x - 1][y] = 0;
                        map[x - 2][y] = 0;
                        mazeRecursion(x - 2, y);
                    }else{
                        if(rand.nextInt(sizeX/2) == 0) {
                            map[x - 1][y] = 0;
                            mazeRecursion(x - 2, y);
                        }
                    }
                    break;
                case 3 : // right
                    if(x + 2 >= sizeX-1)
                        continue;
                    if(map[x + 2][y] == 1){
                        map[x + 1][y] = 0;
                        map[x + 2][y] = 0;
                        mazeRecursion(x + 2, y);
                    }else{
                        if(rand.nextInt(sizeX/2) == 0) {
                            map[x + 1][y] = 0;
                            mazeRecursion(x + 2, y);
                        }
                    }
                    break;
            }
        }
    }

    /**
     *
     */
    private void actionMonsters(){
        for(Monster m : monsters){
            if(true) { // the monster isn't attacking and will move
                if (m.isFinishedMoving()){
                    m.setFinishedMoving(false);
                    m.setPosX((int) (m.getPositionX() + (m.getSize() / 2)) / ratio);
                    m.setPosY((int) (m.getPositionY() + (m.getSize() / 2)) / ratio);
                    player.setPosX((int) (player.getPositionX() + (player.getSize() / 2)) / ratio);
                    player.setPosY((int) (player.getPositionY() + (player.getSize() / 2)) / ratio);
                    if (m.isMonster1()) {
                        //System.out.println("Monster1");
                        m.setGoal(pathfinder.getNextMove(new int[]{m.getPosX(), m.getPosY()}, new int[]{player.getPosX(), player.getPosY()}));
                    } else {
                        //System.out.println("Monster2");
                        if (Math.abs(m.getPosX() - player.getPosX()) > Math.abs(m.getPosY() - player.getPosY())) {
                            if (m.getPosX() > player.getPosX())
                                m.setGoal(new int[]{m.getPosX() - 1, m.getPosY()});
                            else
                                m.setGoal(new int[]{m.getPosX() + 1, m.getPosY()});
                        } else {
                            if (m.getPosY() > player.getPosY())
                                m.setGoal(new int[]{m.getPosX(), m.getPosY() - 1});
                            else
                                m.setGoal(new int[]{m.getPosX(), m.getPosY() + 1});
                        }
                    }
                }
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
                else
                    sb.append("; ");
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
            if (m.isMonster1())
                spriteBatch.draw(TextureFactory.getInstance().getMonsterTexture2(), m.getPositionX(), m.getPositionY(), m.getSize(), m.getSize());
            else
                spriteBatch.draw(TextureFactory.getInstance().getMonsterTexture1(), m.getPositionX(), m.getPositionY() , m.getSize(), m.getSize());

        }
        for(Potion p : potions) {
            spriteBatch.draw(TextureFactory.getInstance().getPotionOfLife(), p.getBodyPositionX(), p.getBodyPositionY(), p.getSize(), p.getSize());
        }
        if(endCell.isTreasureCell()) {
            spriteBatch.draw(TextureFactory.getInstance().getTreasure(), endCell.getBodyPositionX(), endCell.getBodyPositionY(), endCell.getSize(), endCell.getSize());
        }else{
            spriteBatch.draw(TextureFactory.getInstance().getGangway(), endCell.getBodyPositionX(), endCell.getBodyPositionY(), endCell.getSize(), endCell.getSize());
        }

        for(int i = 0; i < player.getHp(); i++){
            spriteBatch.draw(TextureFactory.getInstance().getHeart(), 0, i*32, walls.get(0).getSize(), walls.get(0).getSize());
        }

    }


    /**
     * move the monsters, apply the force of monsters
     */
    public void moveMonsters(){
        for(Monster m : monsters) {
            if (m.getGoal()[0] != -1 && m.getGoal()[1] != -1) {
                float goalX = m.getGoal()[0] * ratio + 5;
                float goalY = m.getGoal()[1] * ratio + 5;

                if (Math.abs(m.getPositionY() - goalY) > Math.abs(m.getPositionX() - goalX)) {
                    if (m.getPositionY() < goalY) //up
                        //m.getBody().setLinearVelocity(0.f, 10.f);
                        m.applyForce(new Vector2(0.f, 5.f/ratio));
                    else if (m.getPositionY() > goalY) //down
                        //m.getBody().setLinearVelocity(0.f, -10.f);
                        m.applyForce(new Vector2(0.f, -5.f/ratio));
                }else {
                    if (m.getPositionX() > goalX) //left
                        //m.getBody().setLinearVelocity(-10.f, 0.f);
                        m.applyForce(new Vector2(-5.f/ratio, 0.f));
                    else if (m.getPositionX() < goalX) //right
                        //m.getBody().setLinearVelocity(10.f, 0.f);
                        m.applyForce(new Vector2(5.f/ratio, 0.f));
                }
                if (goalX - 4 < m.getPositionX() && m.getPositionX() < goalX + 4 && goalY - 4 < m.getPositionY() && m.getPositionY() < goalY + 4) {
                    m.getBody().setLinearVelocity(0.f, 0.f);
                    //m.applyForce(new Vector2(0.f, 0.f));
                    m.setFinishedMoving(true);
                }
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

                            m.setHp(m.getHp() - player.getAttackPoints());
                            playerAttack.set(false);
                            //System.out.println(m.getHp());


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
        monsters.removeIf(m -> m.getHp() <= 0);



    }


    private void deletePotionOfLife(){
        for(Potion p : potions){
            if(!p.isActive()) {
                world.destroyBody(p.getBody());
            }
        }

        //delete potion in arraylist
        potions.removeIf(p -> !p.isActive());
    }






    public void effectCell(){
        world.setContactListener(new ContactListener() {


            @Override
            public void beginContact(Contact contact) {

                for(CellTrap c : traps) {

                    if (contact.getFixtureB().getBody() == c.getBody() && contact.getFixtureA().getBody() == player.getBody()) {

                        c.getEffect(player);

                    }
                }

                if (endCell.isTreasureCell()) {

                    if (contact.getFixtureB().getBody() == endCell.getBody() && contact.getFixtureA().getBody() == player.getBody())
                        ((CellTreasure) endCell).setTreasure(true);
                }else
                    if (contact.getFixtureB().getBody() == endCell.getBody() && contact.getFixtureA().getBody() == player.getBody())
                        initialisation(sizeX, sizeY);

                for(Monster m : monsters) {

                    if (contact.getFixtureB().getBody() == m.getBody() && contact.getFixtureA().getBody() == player.getBody()) {
                        if(rand.nextInt(10) == 3) {
                        player.setHp(player.getHp() - m.getAttackPoints());

                        }
                    }
                }

                for(Potion p : potions){
                    if (contact.getFixtureB().getBody() == p.getBody() && contact.getFixtureA().getBody() == player.getBody()) {
                        player.setHp(player.getHp() + 1);
                        p.setActive(false);}
                }

                if(player.getHp() < 0){
                    gameOver = true;
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

        deletePotionOfLife();

    }

    private void initialisation(int sizeX, int sizeY){
        monsters = new ArrayList<>();
        walls = new ArrayList<>();
        potions = new ArrayList<>();
        traps = new ArrayList<>();
        playerAttack = new AtomicBoolean(false);
        //monsterAttack = new AtomicBoolean(false);
        world = new World(new Vector2(0, 0), true);
        player = new Player(world, ratio , ratio  , (ratio) - 10);
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        gameOver = false;



        rand = new Random();

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

        pathfinder = new AStar(map);

        /*if(cptLaby == 5) {
            cellNext = null;
        }else{
            cellTreasure = null;
        }*/

        //Searching for the corner the furthest away from the player to place the exit
        int lenOpt0 = pathfinder.getPathLength(new int[]{sizeX - 2, sizeY - 2}, new int[]{1, 1}); // top right
        int lenOpt1 = pathfinder.getPathLength(new int[]{1, sizeY - 2}, new int[]{1, 1}); // top left
        int lenOpt2 = pathfinder.getPathLength(new int[]{sizeX - 2, 1}, new int[]{1, 1}); // bottom right
        int opt;
        if(lenOpt0 > lenOpt1)
            opt = 0;
        else
            opt = 1;
        if(opt == 0) {
            if (lenOpt0 < lenOpt2)
                opt = 2;
        }else if (lenOpt1 < lenOpt2)
                opt = 2;

        //System.out.println("cptLaby = " + cptLaby);
        switch (opt){
            case 1:
                //place the exit in the top left corner
                if (cptLaby == 5)
                    endCell = new CellTreasure(world, ratio + 5, (sizeY - 2) * ratio + 5, ratio - 10);
                else
                    endCell = new CellEnd(world, ratio + 5, (sizeY - 2) * ratio + 5, ratio - 10);
                break;
            case 2:
                //place the exit int the bottom right corner
                if (cptLaby == 5)
                    endCell = new CellTreasure(world, (sizeX - 2) * ratio + 5, ratio + 5, ratio - 10);
                else
                    endCell = new CellEnd(world, (sizeX - 2) * ratio + 5, ratio + 5, ratio - 10);
                break;
            default:
                //place the exit in the top right corner
                if (cptLaby == 5)
                    endCell = new CellTreasure(world, (sizeX - 2) * ratio + 5, (sizeY - 2) * ratio + 5, ratio - 10);
                else
                    endCell = new CellEnd(world, (sizeX - 2) * ratio + 5, (sizeY - 2) * ratio + 5, ratio - 10);
        }

        for(int i = 0; i < sizeY; i++)
            for(int j = 0; j < sizeX ; j++)
                if(map[j][i] == 1)
                    if(i==0 || j==0 || j == sizeX-1 || i == sizeX-1) {
                        walls.add(new WallLimit(world, j, i,  ratio));

                    }
                    else {
                        walls.add(new WallObstacle(world, j, i, ratio));
                    }
        //Place the monsters randomly
        int nbMonsters = sizeX / 3;
        for(int i = 0; i < nbMonsters; i++){
            int x = -1;
            int y = -1;
            while((x <= 0 && y <= 0) || (map[x][y] != 0 || (x == 1 && y == 1))){
                x = rand.nextInt(sizeX-1) + 1;
                y = rand.nextInt(sizeY-1) + 1;
            }
            int ghost = rand.nextInt(3);
            Monster nM;
            if(ghost == 0)
                nM = new Monster2(world, x, y, 2, ratio - 10, ratio);
            else
                nM = new Monster1(world, x, y, 3, ratio - 10, ratio);
            monsters.add(nM);
            map[x][y] = 2;
        }

        //Place the potions randomly
        int nbPotions = sizeX/4;
        for(int i = 0; i < nbPotions; i++){
            int x = -1;
            int y = -1;
            while((x <= 0 && y <= 0) || (map[x][y] != 0 || (x == 1 && y == 1))){
                x = rand.nextInt(sizeX-1) + 1;
                y = rand.nextInt(sizeY-1) + 1;
            }
            Potion nP = new Potion(world, x, y, ratio, ratio);
            potions.add(nP);
            map[x][y] = 2;
        }

        //Place the traps randomly
        int nbTraps = sizeX/4;
        for(int i = 0; i < nbTraps; i++){
            int x = -1;
            int y = -1;
            while((x <= 0 && y <= 0) || (map[x][y] != 0 || (x == 1 && y == 1))){
                x = rand.nextInt(sizeX-1) + 1;
                y = rand.nextInt(sizeY-1) + 1;
            }
            CellTrap nT = new CellTrap(world, x, y, ratio, ratio);
            traps.add(nT);
            map[x][y] = 2;
        }

        new Timer().scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        actionMonsters();
                    }
                }, 250, 250);

        actionMonsters();

        cptLaby += 1;
    }

    public boolean isTreasure(){
        boolean res = false;
        if(endCell.isTreasureCell())
            res = ((CellTreasure)endCell).isTreasure();
        return res;
    }

    public World getWorld(){
        return world;
    }

    public int getSizeX() {
        return sizeX * Gdx.graphics.getHeight() / sizeX;
    }


    public boolean isGameOver() {
        return gameOver;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Potion> getPotions() {
        return potions;
    }

    public CellEnd getEndCell() {
        return endCell;
    }
}
