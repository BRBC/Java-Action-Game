package PaooGame.Camera;

import PaooGame.Items.Hero;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

/*! \class public class Camera
    \brief Clasa Camera este folosita pentru a urmari personajul principal pe intreaga harta, chiar daca
           aceasta depaseste dimenstiunea ecranului.
    */

public class Camera {
    private RefLinks refLink;
    private float xOffset;

    /*! \fn Camera
    \brief Constructorul clasei Camera
    \param refLink contine referinte catre obiectele Game, Map
    */

    public Camera(RefLinks refLink)
    {
        this.refLink = refLink;
        xOffset = 0;
    }

    /*! \fn Center
    \brief Functia Center este folosita pentru a centra personajul pe ecran in functie de pozitia lui actuala pe harta

    */

    public void Center(Hero hero)
    {
        xOffset = hero.GetX() - refLink.GetWidth() / 2;
        if(xOffset < 0)
        {
            xOffset = 0;
        }
        if(xOffset > refLink.GetMapWidth() * Tile.TILE_WIDTH - refLink.GetWidth())
        {
            xOffset = refLink.GetMapWidth() * Tile.TILE_WIDTH - refLink.GetWidth();
        }

    }

    public float getxOffset()
    {
        return xOffset;
    }
}

