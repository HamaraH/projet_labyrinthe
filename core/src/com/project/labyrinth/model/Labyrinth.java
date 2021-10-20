package com.project.labyrinth.model;

import java.util.*;

public class Labyrinth {
    private int sizeX, sizeY, nbMonsters = 5;
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
        ArrayList<Integer> dir = new ArrayList<>(4);
        for(int i = 0; i < 4; i++){
            dir.add(i);
        }
        // Shuffling the directions to get a random maze creation
        Collections.shuffle(dir);
        Integer[] dirsTab = new Integer[4];
        dirsTab = dir.toArray(new Integer[4]);

        //We go through the directions array to find our next path
        for(int i = 0; i < 4; i++){
            switch(dirsTab[i]){
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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++)
                if (map[i][j] == 1)
                    sb.append("# ");
                else if (map[i][j] == 0)
                    sb.append("; ");
                else if (map[i][j] == 2)
                    sb.append("k ");
            sb.append("\n");
        }
        return sb.toString();
    }
}
