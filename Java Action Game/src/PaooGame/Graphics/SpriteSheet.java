package PaooGame.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet
{
    private BufferedImage       spriteSheet;
    private  int    tileWidth;
    private  int    tileHeight;

    public SpriteSheet(BufferedImage buffImg,int tileWidth, int tileHeight)
    {
        spriteSheet = buffImg;
        this.tileHeight=tileHeight;
        this.tileWidth=tileWidth;
    }

    public BufferedImage cropTiles(int x, int y)
    {
        return spriteSheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }

    public void cropEntityFramesRight(ArrayList<BufferedImage> frames)
    {
        int width=spriteSheet.getWidth();
        for(int i=0;i<width/tileWidth;i++)
        {
            frames.add(spriteSheet.getSubimage(i*tileWidth,0*tileHeight,tileWidth,tileHeight));
        }
    }

    public void cropEntityFramesLeft(ArrayList<BufferedImage> frames)
    {
        int width=spriteSheet.getWidth();
        for(int i=width/tileWidth-1;i>=0;i--)
        {
            frames.add(spriteSheet.getSubimage(i*tileWidth,0*tileHeight,tileWidth,tileHeight));
        }
    }
}
