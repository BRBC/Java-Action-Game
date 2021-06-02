package PaooGame.Entities;

import PaooGame.Animator.Animator;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.KeyHandler.KeyHandler;
import PaooGame.TileMap.TileMap;

import java.awt.*;

public class Knight extends Player
{
    public Knight(int x, int y, int width, int height, KeyHandler keyHandler, TileMap mapRef, Game gameRef)
    {
        super(x,y,width,height,keyHandler,mapRef,gameRef);
        idleRight= new Animator(Assets.knightIdleRight,100);
        idleLeft = new Animator(Assets.knightIdleLeft,100);
        runRight = new Animator(Assets.knightRunRight,100);
        runLeft = new Animator(Assets.knightRunLeft,100);
        attackRight = new Animator(Assets.knightAttackRight,100);
        attackLeft = new Animator(Assets.knightAttackLeft,100);
    }
    public void Draw(Graphics graphics)
    {
        super.Draw(graphics);
    }
}
