package com.project.labyrinth.model;

public class Monster extends Entity {
    private int id;
    public Monster(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    private void move(int x, int y){
        this.posX += x;
        this.posY += y;
    }
}
