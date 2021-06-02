package PaooGame.Camera;

import PaooGame.Entities.Player;
import PaooGame.Game;
import PaooGame.TileMap.TileMap;
import PaooGame.Tiles.Tile;

import javax.naming.TimeLimitExceededException;

public class Camera {
    private Game game;
    private int xOffset;
    private TileMap tileMap;

    public Camera(Game game,int xOffset)
    {
        this.game = game;
        this.xOffset = xOffset;
    }

    public void Center(Player p)
    {
        xOffset = p.x - game.getWidth() / 2;
        if(xOffset < 0)
        {
            xOffset = 0;
        }
        if(xOffset > tileMap.GetWidth() * Tile.TILE_WIDTH - game.getWidth())
        {
            xOffset = tileMap.GetWidth() * Tile.TILE_WIDTH - game.getWidth();
        }

    }

    public int getxOffset()
    {
        return xOffset;
    }

    /*public void setxOffset(int xOffset)
    {
        this.xOffset = xOffset;
    }*/

    public void setTileMap(TileMap tileMap)
    {
        this.tileMap = tileMap;
    }


}
