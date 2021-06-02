package PaooGame.Entities;
import PaooGame.Animator.Animator;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.TileMap.TileMap;

import java.awt.*;

import static java.lang.System.currentTimeMillis;

public class Enemy extends Entity
{
    private boolean right;
    private int origin, offset;
    private Animator runRight;
    private int speed = 1;
    private boolean alive;


    public Enemy(int xPos, int width, int height, int offset, TileMap mapRef,Game gameRef)
    {
        super(xPos,0,width,height,mapRef,gameRef);
        this.origin = xPos;
        this.right = true;
        this.offset = offset;
        this.runRight = new Animator(Assets.skeletonRunRight,100);
        this.runLeft = new Animator(Assets.skeletonRunLeft,100);
        this.alive = true;
    }

    public void Update()
    {
        if(alive) {
            this.time = currentTimeMillis();
            Collide();
            if (right && this.x < origin + offset) {
                this.x = this.x + speed;
            }
            if (right && this.x >= origin + offset) {
                right = false;
            }

            if (!right && this.x > origin) {
                this.x = this.x - speed;
            }
            if (!right && this.x <= origin) {
                right = true;
            }
        }
    }

    public void Draw(Graphics g)
    {
        if(alive) {
            if (right) {
                g.drawImage(runRight.Update(time), x - gameRef.getCamera().getxOffset(), y, width, height, null);
            } else {
                g.drawImage(runLeft.Update(time), x - gameRef.getCamera().getxOffset(), y, width, height, null);
            }
        }
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public boolean getAlive()
    {
        return alive;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }
}
