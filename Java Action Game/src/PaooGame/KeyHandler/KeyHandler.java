package PaooGame.KeyHandler;

import PaooGame.Entities.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class KeyHandler implements KeyListener
{
    private ArrayList<Object> observers;
    private ArrayList<Integer> keysPressed;

    public KeyHandler()
    {
        keysPressed= new ArrayList<>();
        observers= new ArrayList<>();
    }

    public void addObserver(Object observer)
    {
        observers.add(observer);
    }

    public ArrayList<Integer> getKeysPressed()
    {
        return keysPressed;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (!keysPressed.contains(e.getKeyCode()))
        {
            keysPressed.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
           keysPressed.remove(Integer.valueOf(e.getKeyCode()));
    }
}
