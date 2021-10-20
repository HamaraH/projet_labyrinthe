package com.project.labyrinth.model;

import java.util.*;

public class Labyrinth {
    private int sizeX, sizeY, nbMonsters = 1;
    private int[][] map; //the map is made of zeroes and ones, as to show where the walls are
    private Player player;
    List<Monster> monsters = new ArrayList<Monster>();

    public Labyrinth(int sizeX, int sizeY){
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

        //Place the monsters randomly
        for(int i = 0; i < nbMonsters; i++){
            int x = -1;
            int y = -1;
            while((x <= 0 || map[x][y] != 0) && (y <= 0 || map[x][y] != 0)){
                x = rand.nextInt(sizeX-1) + 1;
                y = rand.nextInt(sizeY-1) + 1;
            }
            Monster newMonster = new Monster(x, y);
            monsters.add(newMonster);
            map[x][y] = 2;
        }

        // Print the maze
        System.out.println(this.toString());
    }

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

    public void actionMonsters(){
        Integer[] dir;
        for(Monster m : monsters){
            // Creation of an array to contain the 4 directions in which the monster could move
            dir = randomDirection();
            // Get the monster's position
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
                            //continue;
                        }
                        break;
                }
                map[m.getPosX()][m.getPosY()] = 2;
            }
            //sb.append(m.getPosX()).append(" ; ").append(m.getPosY()).append(" )");
            //System.out.println(sb.toString());
        }
    }

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
}
