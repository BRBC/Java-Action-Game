package PaooGame.Items;

import PaooGame.Animation.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

import static java.lang.System.currentTimeMillis;

    /*! \class public class Monster
    \brief Clasa Monster extinde clasa Character si reprezinta inamicii jocului.

    */

public class Monster extends Character {

    private float origin, offset;
    private int speed = 1;

    public Monster(RefLinks refLinks, float origin, float offset, int width, int height)
    {
        super(refLinks, origin, 0, width, height);
        runRight = new Animation(Assets.monsterRunRight,100);
        runLeft = new Animation(Assets.monsterRunLeft,100);
        this.origin = origin;
        this.right = true;
        this.offset = offset;
        this.alive = true;
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea monstrilor.
     */

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

    /*! \fn public void Draw()
        \brief Deseneaza pe ecran sprite-ul monstrilor.
     */

    public void Draw(Graphics g)
    {
        if(alive) {
            if (right) {
                g.drawImage(runRight.Update(time), (int)(x - refLink.GetCamera().getxOffset()),(int)(y), width, height, null);
            } else {
                g.drawImage(runLeft.Update(time), (int)(x - refLink.GetCamera().getxOffset()), (int)(y), width, height, null);
            }
        }
    }
}
