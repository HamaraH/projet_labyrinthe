package com.project.labyrinth.model.CellEffect;

import com.badlogic.gdx.physics.box2d.World;
import com.project.labyrinth.model.CellEffect.CellEffect;
import com.project.labyrinth.model.Player;

public class CellTrap extends CellEffect {


    private final static  int DAMAGE = 10;


    CellTrap(World world, int x, int y, int size) {
        super(world, x, y, size);


    }


    @Override
    public void getEffect(Player player)
    {
        player.setHp(player.getHp() - DAMAGE);
    }

}

