package PaooGame.Entities;

import PaooGame.Animator.Animator;
import PaooGame.Game;
import PaooGame.TileMap.TileMap;
import PaooGame.Tiles.Tile;

import java.awt.Rectangle;
import java.awt.Graphics;

public abstract class Entity extends Rectangle {
    protected boolean left=false,right=false,running=false;
    protected Animator idleLeft,idleRight,runLeft,runRight;
    protected long time;


    protected TileMap mapRef;
    protected Game gameRef;

    public Entity(int x, int y, int width, int height,TileMap mapRef, Game gameRef)
    {
        setBounds(x,y,width,height);
        this.mapRef = mapRef;
        this.gameRef = gameRef;
    }

    public abstract void Update();

    public abstract void Draw(Graphics graphics);

    protected void Collide()
    {
        boolean falling = true;
        boolean positionAdjustment = false; // O variabila care imi arata daca am ajustat deja pozitia personajului in caz ca intra intr-un tile

        for(int i = 0; i < mapRef.GetHeight(); i++)
        {
            for(int j = 0; j < mapRef.GetWidth(); j++)
            {
                if(mapRef.GetTile(j,i).IsSolid())
                {
                    if (((this.x >= j * Tile.TILE_WIDTH) && (this.x < (j + 1) * Tile.TILE_WIDTH))
                            || ((this.x + this.width >= j * Tile.TILE_WIDTH) && (this.x + this.width < (j + 1) * Tile.TILE_WIDTH))
                            || (this.x < j*Tile.TILE_WIDTH) && (this.x+this.width > (j+1)*Tile.TILE_WIDTH))
                    {
                        if(this.y+this.height == i * Tile.TILE_HEIGHT)
                        {
                            falling = false;
                        }
                        if(this.y+this.height + 4 > i * Tile.TILE_HEIGHT && !positionAdjustment)
                        {
                            this.y = i * Tile.TILE_HEIGHT - this.height;
                            falling = false;
                            positionAdjustment = true;
                        }

                    }
                }
            }
        }
        if (falling)
        {
            this.y = this.y + 4;
        }
    }


}
