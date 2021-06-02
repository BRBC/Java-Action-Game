package PaooGame.Animator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator
{
    BufferedImage frame;
    ArrayList<BufferedImage> frames;

    //private volatile boolean playing=true;
    private long previousTime,speed;
    private int currentFrame;

    public Animator(ArrayList<BufferedImage> spriteFrames,long speed)
    {
        frames=spriteFrames;
        this.speed=speed;
    }

    public BufferedImage Update(long time)
    {
            if(time-previousTime>speed)
            {
                currentFrame++;
                try
                {
                    frame=frames.get(currentFrame);
                }catch (IndexOutOfBoundsException e)
                {
                    currentFrame=0;
                    frame=frames.get(currentFrame);
                }
                previousTime=time;
            }
            return frame;
    }
}
