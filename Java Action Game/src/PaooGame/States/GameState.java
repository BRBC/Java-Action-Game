package PaooGame.States;

import PaooGame.Entities.Enemy;
import PaooGame.Entities.Knight;
import PaooGame.Entities.Player;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.KeyHandler.KeyHandler;
import PaooGame.TileMap.TileMap;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

public class GameState
{
    private Player player;
    private ArrayList<Enemy> enemies;
    private TileMap tileMap;
    private Game game;
    private int score, killScore = 100;
    protected Font font=new Font("arial",Font.BOLD,30);
    private boolean victory;

    public GameState(Game game, int x, int y, int width, int height, KeyHandler keyHandler)
    {
        this.game = game;
        tileMap = new TileMap(game);
        game.getCamera().setTileMap(tileMap);

        player = new Knight(x,y,width,height,keyHandler,tileMap,game);

        keyHandler.addObserver(player);

        enemies = new ArrayList<>();
        enemies.add(new Enemy(12 * Tile.TILE_WIDTH,44,66,5 * Tile.TILE_WIDTH,tileMap,game));
        enemies.add(new Enemy(18 * Tile.TILE_WIDTH,44,66,6 * Tile.TILE_WIDTH,tileMap,game));
        enemies.add(new Enemy(29 * Tile.TILE_WIDTH,44,66,2 * Tile.TILE_WIDTH,tileMap,game));
        enemies.add(new Enemy(35 * Tile.TILE_WIDTH,44,66,3 * Tile.TILE_WIDTH,tileMap,game));

        score = 0;
    }

    public void SetDifficulty(boolean difficulty)
    {
        if(difficulty)
        {
            player.setHealthPoints(50);
            for (Enemy enemy : enemies)
            {
                enemy.setSpeed(3);
            }
            killScore = 300;
            tileMap.LoadWorld2();
        }
        else
        {
            player.setHealthPoints(100);
            for (Enemy enemy : enemies)
            {
                enemy.setSpeed(1);
            }
        }
    }

    public void Update()
    {
        if(Victory())
        {
            game.StopGame();
        }
        player.Update();

        if(player.getHealthPoints()<=0)
        {
            game.StopGame();
        }

        for (Enemy enemy : enemies)
        {
            enemy.Update();
        }
        CheckCollisions();
        CheckAttackCollisions();
    }

    public void Draw(Graphics g)
    {
        g.setFont(font);
        g.setColor(Color.red);

        g.drawImage(Assets.gameStateBackground,0,0,960,540,null);
        tileMap.Draw(g);
        player.Draw(g);
        for (Enemy enemy : enemies)
        {
            enemy.Draw(g);
        }
        g.drawString("HP:"+(player.getHealthPoints()),0,30);
        g.drawString("Score:"+score,120,30);
    }

    private void CheckCollisions()
    {
        for (Enemy enemy : enemies)
        {
            if(enemy.getAlive())
                {
                if ((player.x + 20 < enemy.x && player.x + player.width - 20 > enemy.x)
                        || (player.x + 20 < enemy.x + enemy.width && player.x + player.width - 20 > enemy.x + enemy.width))
                {
                    player.Hit();
                }
            }
        }
    }

    private void CheckAttackCollisions()
    {
        if(player.getAttacking()!=0)
        {
            if(player.getRight())
            {
                for (Enemy enemy : enemies)
                {
                    if(enemy.x < player.x + player.width + 20 && enemy.getAlive())
                    {
                        enemy.setAlive(false);
                        score = score + killScore;
                    }
                }
            }
            else
            {
                for (Enemy enemy : enemies)
                {
                    if(enemy.x + enemy.width +20 > player.x && enemy.getAlive())
                    {
                        enemy.setAlive(false);
                        score = score + killScore;
                    }
                }
            }
        }
    }

    private boolean Victory()
    {
        boolean victory = true;
        for (Enemy enemy : enemies)
        {
            victory = victory && !enemy.getAlive();
        }
        return(victory && player.x > tileMap.GetWidth()*Tile.TILE_WIDTH);
    }
}
