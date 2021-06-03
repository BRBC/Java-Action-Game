package PaooGame.Animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

    /*! \class public class Animation
    \brief Clasa Animation implementeaza animatia texturilor folosite in joc.

    */

public class Animation
{
    BufferedImage frame;
    ArrayList<BufferedImage> frames;
    private long previousTime,speed;
    private int currentFrame;

    /*! \fn Animation
    \brief Constructorul clasei
    \param ArrayList<BufferedImage> spriteFrames Arraylist care contine toate frame-urile unei animatii
    \speed parametru care exprima numarul de milisecunde cat un frame din animatie este desenat pe ecran.
    */
    public Animation(ArrayList<BufferedImage> spriteFrames,long speed)
    {
        frames = spriteFrames;
        this.speed = speed;
    }

    /*! \fn Update
    \brief Calculeaza cat timp a trecut de cand a fost frame-ul trecut desenat pe ecran, pentru a determina cand
           trebuie desenat urmatorul

    */

    public BufferedImage Update(long time)
    {
        if(time - previousTime > speed)
        {
            currentFrame++;
            try
            {
                frame = frames.get(currentFrame);
            }catch (IndexOutOfBoundsException e)
            {
                currentFrame = 0;
                frame = frames.get(currentFrame);
            }
            previousTime = time;
        }
        return frame;
    }
}
