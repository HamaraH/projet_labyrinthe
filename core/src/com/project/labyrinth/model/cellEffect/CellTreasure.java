package com.project.labyrinth.model.cellEffect;

import com.badlogic.gdx.physics.box2d.*;

public class CellTreasure extends CellEnd {


    private boolean treasure;

    public CellTreasure(World world, int x, int y, int size){
        super(world, x, y, size);

        treasure = false;
    }


    public boolean isTreasure() {
        return treasure;
    }

    public void setTreasure(boolean treasure) {
        this.treasure = treasure;
    }

    @Override
    public boolean isTreasureCell() {
        return true;
    }
}

