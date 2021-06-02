package PaooGame.States;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.KeyHandler.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OptionsState extends MenuState
{
    private GameState gameState;

    public OptionsState(int width,int height,String[] buttonArray,KeyHandler keyHandler, GameState gameState)
    {
        super(width,height,buttonArray,keyHandler);
        this.gameState = gameState;
    }

    @Override
    public String Update() {
        returnString = "";
        if (!downValue && observable.getKeysPressed().contains(KeyEvent.VK_DOWN)) {
            downValue = true;
        }
        if (downValue && !observable.getKeysPressed().contains(KeyEvent.VK_DOWN)) {
            buttonCounter++;
            downValue = false;
        }
        if (!upValue && observable.getKeysPressed().contains(KeyEvent.VK_UP)) {
            upValue = true;
        }
        if (upValue && !observable.getKeysPressed().contains(KeyEvent.VK_UP)) {
            buttonCounter--;
            upValue = false;
        }

        if (buttonCounter < 0) {
            buttonCounter = buttons - 1;
        }
        if (buttonCounter >= buttons) {
            buttonCounter = 0;
        }

        if (!enterValue && observable.getKeysPressed().contains(KeyEvent.VK_ENTER))
        {
            enterValue = true;
        }
        if (enterValue && !observable.getKeysPressed().contains(KeyEvent.VK_ENTER))
        {
            if (buttonArray[buttonCounter].equals("Easy"))
            {
                returnString = "Menu";
                gameState.SetDifficulty(false);
            }
            if (buttonArray[buttonCounter].equals("Hard"))
            {
                returnString = "Menu";
                gameState.SetDifficulty(true);
            }
            enterValue=false;
        }
        return returnString;
    }
}
