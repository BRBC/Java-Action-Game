package PaooGame.States;

import PaooGame.Game;
import PaooGame.KeyHandler.KeyHandler;
import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class MenuState
{
    protected KeyHandler observable;
    protected int buttonCounter=0,buttons;
    protected boolean downValue=false,upValue=false,enterValue=false;
    protected Font font=new Font("arial",Font.BOLD,50);
    protected String[] buttonArray;
    protected int width,height;
    protected String returnString;




    public MenuState(int width,int height,String[] buttonArray,KeyHandler keyHandler)
    {
        this.buttons=buttonArray.length;
        this.observable=keyHandler;
        this.buttonArray=buttonArray;
        this.width=width;
        this.height=height;
    }

    public String Update()
    {
        returnString="";
        if(!downValue&&observable.getKeysPressed().contains(KeyEvent.VK_DOWN))
        {
            downValue=true;
        }
        if(downValue&&!observable.getKeysPressed().contains(KeyEvent.VK_DOWN))
        {
            buttonCounter++;
            downValue=false;
        }
        if(!upValue&&observable.getKeysPressed().contains(KeyEvent.VK_UP))
        {
            upValue=true;
        }
        if(upValue&&!observable.getKeysPressed().contains(KeyEvent.VK_UP))
        {
            buttonCounter--;
            upValue=false;
        }

        if(buttonCounter<0)
        {
            buttonCounter=buttons-1;
        }
        if(buttonCounter>=buttons)
        {
            buttonCounter=0;
        }

        if(!enterValue&&observable.getKeysPressed().contains(KeyEvent.VK_ENTER))
        {
            enterValue=true;
        }
        if(enterValue&&!observable.getKeysPressed().contains(KeyEvent.VK_ENTER))
        {
            if(buttonArray[buttonCounter].equals("Play"))
            {
                returnString="Play";
            }
            if(buttonArray[buttonCounter].equals("Quit"))
            {
                returnString="Quit";
            }
            if(buttonArray[buttonCounter].equals("Options"))
            {
                returnString = "Options";
            }
            enterValue=false;
        }
        return returnString;
    }

    public void Draw(Graphics graphics)
    {
        graphics.drawImage(Assets.menuStateBackground,0,0,960,540,null);
        graphics.setFont(font);
        for(int i=0; i<buttons;i++)
        {
            if(i==buttonCounter)
            {
                graphics.setColor(Color.red);
            }
            else
            {
                graphics.setColor(Color.black);
            }
            graphics.drawString(buttonArray[i],width/8,((height)/(buttons*2)*(i+1)));
        }
    }
}
