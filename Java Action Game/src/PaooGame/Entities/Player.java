package PaooGame.Entities;

import java.awt.*;
import java.awt.event.KeyEvent;

import PaooGame.Animator.Animator;
import PaooGame.Game;
import PaooGame.KeyHandler.KeyHandler;
import PaooGame.TileMap.TileMap;

import static java.lang.System.currentTimeMillis;

public abstract class Player extends Entity
{
    private KeyHandler observable;

    protected Animator attackLeft,attackRight;

    private int healthPoints;
    private int immunityFrames = 0;

    private int attacking = 0;
    private boolean spaceValue = false;

    public Player(int x, int y, int width, int height, KeyHandler keyHandler, TileMap mapRef, Game gameRef)
    {
        super(x,y,width,height,mapRef,gameRef);
        observable=keyHandler;
        this.right=true;
        this.healthPoints = 100;
    }

    public void Update()
    {
        if (immunityFrames != 0)
    {
        immunityFrames = immunityFrames - 1;
    }

        this.time=currentTimeMillis();
        Collide();

        if(attacking != 0)
        {
            attacking = attacking -1;
        }
        else {

            if (!spaceValue && observable.getKeysPressed().contains(KeyEvent.VK_SPACE)) {
                spaceValue = true;
            }
            if (spaceValue && !observable.getKeysPressed().contains(KeyEvent.VK_SPACE)) {
                attacking = 37;
                spaceValue = false;
            }

            if (observable.getKeysPressed().contains(KeyEvent.VK_RIGHT) || observable.getKeysPressed().contains(KeyEvent.VK_LEFT))
            {
                running = observable.getKeysPressed().contains(KeyEvent.VK_RIGHT) ^ observable.getKeysPressed().contains(KeyEvent.VK_LEFT);
                if (observable.getKeysPressed().contains(KeyEvent.VK_LEFT)) {
                    x = x - 3;
                    if (right) {
                        right = false;
                    }
                    if (!left) {
                        left = true;
                    }
                }
                if (observable.getKeysPressed().contains(KeyEvent.VK_RIGHT)) {
                    x = x + 3;
                    if (left) {
                        left = false;
                    }
                    if (!right) {
                        right = true;
                    }
                }
            } else {
                running = false;
            }

        }
        gameRef.getCamera().Center(this);
    }

    public void Draw(Graphics graphics)
    {
        if((immunityFrames/10) % 2 == 0)
        {
            if(attacking != 0)
            {
                if(right) {
                    graphics.drawImage(attackRight.Update(time), x - gameRef.getCamera().getxOffset(), y, width, height, null);
                }
                else
                {
                    graphics.drawImage(attackLeft.Update(time), x - gameRef.getCamera().getxOffset(), y, width, height, null);
                }
            }
            else {
                if (!running && left) {
                    graphics.drawImage(idleLeft.Update(time), x - gameRef.getCamera().getxOffset(), y, width, height, null);
                }

                if ((!running && right && left) || (!running && right)) {
                    graphics.drawImage(idleRight.Update(time), x - gameRef.getCamera().getxOffset(), y, width, height, null);
                }

                if (running && right) {
                    graphics.drawImage(runRight.Update(time), x - gameRef.getCamera().getxOffset(), y, width, height, null);
                }
                if (running && left) {
                    graphics.drawImage(runLeft.Update(time), x - gameRef.getCamera().getxOffset(), y, width, height, null);
                }
            }
        }
    }

    public void setHealthPoints(int hp)
    {
        this.healthPoints = hp;
    }

    public int getHealthPoints()
    {
        return this.healthPoints;
    }

    public void Hit()
    {
        if(immunityFrames == 0)
        {
            this.healthPoints = this.healthPoints - 50;
            immunityFrames = 120;
        }
    }

    public int getAttacking()
    {
        return this.attacking;
    }

    public boolean getRight()
    {
        return this.right;
    }
}
