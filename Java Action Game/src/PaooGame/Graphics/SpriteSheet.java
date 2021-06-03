package PaooGame.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*! \class public class SpriteSheet
    \brief Clasa retine o referinta catre o imagine formata din dale (sprite sheet)

    Metoda crop() returneaza o dala de dimensiuni fixe (o subimagine) din sprite sheet
    de la adresa (x * latimeDala, y * inaltimeDala)
 */
public class SpriteSheet
{
    private BufferedImage       spriteSheet;        /*!< Referinta catre obiectul BufferedImage ce contine sprite sheet-ul.*/
    private int    tileWidth;   /*!< Latimea unei dale din sprite sheet.*/
    private int    tileHeight;   /*!< Inaltime unei dale din sprite sheet.*/

    /*! \fn public SpriteSheet(BufferedImage sheet)
        \brief Constructor, initializeaza spriteSheet.

        \param buffImg Un obiect BufferedImage valid.
     */
    public SpriteSheet(BufferedImage buffImg,int tileWidth, int tileHeight)
    {
            /// Retine referinta catre BufferedImage object.
        spriteSheet = buffImg;
        this.tileHeight=tileHeight;
        this.tileWidth=tileWidth;
    }

    /*! \fn public BufferedImage crop(int x, int y)
        \brief Returneaza un obiect BufferedImage ce contine o subimage (dala).

        Subimaginea este localizata avand ca referinta punctul din stanga sus.

        \param x numarul dalei din sprite sheet pe axa x.
        \param y numarul dalei din sprite sheet pe axa y.
     */
    public BufferedImage crop(int x, int y)
    {
            /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
            /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
            /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
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
