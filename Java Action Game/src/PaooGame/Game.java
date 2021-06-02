package PaooGame;

import PaooGame.Camera.Camera;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.KeyHandler.KeyHandler;
import PaooGame.States.GameState;
import PaooGame.States.MenuState;
import PaooGame.States.OptionsState;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game implements Runnable
{
    private static Game instance = null;

    private GameWindow      wnd;
    private boolean         runState;
    private Thread          gameThread;
    private BufferStrategy  bs;

    private Graphics g;
    private KeyHandler keyHandler;
    private Camera camera;
    private enum STATE{
        MENU,GAME,OPTIONS
}
    private String[] menuStateButtons={"Play","Options","Quit"};
    private String[] optionsStateButtons = {"Easy", "Hard"};
    private MenuState menuState;
    private GameState gameState;
    private OptionsState optionsState;
    private String nextState;

    private STATE state=STATE.MENU;

    public static Game getInstance(String title, int width, int height)
    {
        if (instance == null)
        {
            instance = new Game(title,width,height);
        }
        return instance;
    }

    private Game(String title, int width, int height)
    {
        wnd = new GameWindow(title, width, height);
        runState = false;
    }

    private void InitGame()
    {
        wnd.BuildGameWindow();

        Assets.Init();

        keyHandler=new KeyHandler();
        wnd.GetWindow().addKeyListener(keyHandler);


        menuState = new MenuState(960,540,menuStateButtons,keyHandler);
        camera = new Camera(this,0);
        gameState = new GameState(this,0,0,100,100,keyHandler);
        optionsState = new OptionsState(960, 540,optionsStateButtons, keyHandler,gameState);

        keyHandler.addObserver(menuState);
        keyHandler.addObserver(optionsState);
    }

    public void run()
    {
        InitGame();
        long oldTime = System.nanoTime();
        long curentTime;

        final int framesPerSecond   = 60;
        final double timeFrame      = 1000000000 / framesPerSecond;

        while (runState == true)
        {
            curentTime = System.nanoTime();
            if((curentTime - oldTime) > timeFrame)
            {
                Update();
                Draw();
                oldTime = curentTime;
            }
        }

    }

    public synchronized void StartGame()
    {
        if(runState == false)
        {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
        else
        {
            return;
        }
    }

    public synchronized void StopGame()
    {
        if(runState == true)
        {
            runState = false;
            try
            {
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        else
        {
            return;
        }
    }

    private void Update()
    {
        nextState="";
        if(state==STATE.GAME)
        {
            gameState.Update();
        }

        if(state==STATE.OPTIONS)
        {
            nextState = optionsState.Update();
            if(nextState.equals("Menu"))
            {
                state = STATE.MENU;
            }
        }

        if (state==STATE.MENU)
        {
            nextState=menuState.Update();
            // verific daca nextState este play sau quit sau gol
            if(nextState.equals("Play"))
            {
                state=STATE.GAME;
            }
            if(nextState.equals("Quit"))
            {
                StopGame();
            }
            if(nextState.equals("Options"))
            {
                state=STATE.OPTIONS;
            }
        }
    }

    private void Draw()
    {
        bs = wnd.GetCanvas().getBufferStrategy();
        if(bs == null)
        {
            try
            {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        if(state==STATE.GAME)
        {
            g.drawImage(Assets.gameStateBackground,0,0,960,540,null);
            gameState.Draw(g);
        }
        if(state==STATE.MENU)
        {
            menuState.Draw(g);
        }
        if(state==STATE.OPTIONS)
        {
            optionsState.Draw(g);
        }

        bs.show();
        
        g.dispose();
    }

    public Camera getCamera()
    {
        return camera;
    }

    public int getWidth()
    {
        return wnd.GetWndWidth();
    }

    public int getHeight()
    {
        return wnd.GetWndHeight();
    }
}

