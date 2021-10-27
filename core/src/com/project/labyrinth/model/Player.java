package com.project.labyrinth.model;

public class Player extends Entity {


    public Player(int x, int y){
        this.posX = x;
        this.posY = y;

    }

    public void move(int x,int y){

        this.posX += x;
        this.posY += y;
    }


}