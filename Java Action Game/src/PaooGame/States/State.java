package PaooGame.States;

import PaooGame.Game;
import PaooGame.KeyHandler.KeyHandler;

import java.awt.*;

public abstract class State
{
    private KeyHandler observable;
    private Game game;

    public State(Game game)
    {
        this.game = game;
    }

    public abstract void Update();
    public abstract void Draw(Graphics g);
}
