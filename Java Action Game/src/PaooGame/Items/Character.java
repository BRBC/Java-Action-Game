package PaooGame.Items;

import PaooGame.Animation.Animation;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

/*! \class public abstract class Character extends Item
    \brief Defineste notiunea abstracta de caracter/individ/fiinta din joc.

    Notiunea este definita doar de viata, viteza de deplasare si distanta cu care trebuie sa se
    miste/deplaseze in urma calculelor.
 */
public abstract class Character extends Item
{
    public static final int DEFAULT_LIFE            = 10;   /*!< Valoarea implicita a vietii unui caracter.*/
    public static final float DEFAULT_SPEED         = 3.0f; /*!< Viteza implicita a unu caracter.*/
    public static final int DEFAULT_CREATURE_WIDTH  = 80;   /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_CREATURE_HEIGHT = 80;   /*!< Inaltimea implicita a imaginii caracterului.*/

    protected int life;     /*!< Retine viata caracterului.*/
    protected float speed;  /*!< Retine viteza de deplasare caracterului.*/
    protected float xMove;  /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;  /*!< Retine noua pozitie a caracterului pe axa Y.*/
    protected Animation idleLeft,idleRight,runLeft,runRight;
    protected boolean left, right, running;
    protected boolean alive;
    protected int immunityFrames;
    protected int attacking;

    /*! \fn public Character(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei Character

        \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
        \param x Pozitia de start pa axa X a caracterului.
        \param y Pozitia de start pa axa Y a caracterului.
        \param width Latimea imaginii caracterului.
        \param height Inaltimea imaginii caracterului.
     */
    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
            ///Apel constructor la clasei de baza
        super(refLink, x,y, width, height);
            //Seteaza pe valorile implicite pentru viata, viteza si distantele de deplasare
        life    = DEFAULT_LIFE;
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
    }

    /*! \fn public void Move()
        \brief Modifica pozitia caracterului
     */
    public void Move()
    {
            ///Modifica pozitia caracterului pe axa X.
            ///Modifica pozitia caracterului pe axa Y.
        MoveX();
        MoveY();
    }

    /*! \fn public void MoveX()
        \brief Modifica pozitia caracterului pe axa X.
     */
    public void MoveX()
    {
            ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa X.
        x += xMove;
    }

    /*! \fn public void MoveY()
        \brief Modifica pozitia caracterului pe axa Y.
     */
    public void MoveY()
    {
            ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa Y.
        y += yMove;
    }

    /*! \fn public int GetLife()
        \brief Returneaza viata caracterului.
     */
    public int GetLife()
    {
        return life;
    }

    /*! \fn public int GetSpeed()
        \brief Returneaza viteza caracterului.
     */
    public float GetSpeed()
    {
        return speed;
    }

    /*! \fn public void SetLife(int life)
        \brief Seteaza viata caracterului.
     */
    public void SetLife(int life)
    {
        this.life = life;
    }

    /*! \fn public void SetSpeed(float speed)
        \brief
     */
    public void SetSpeed(float speed) {
        this.speed = speed;
    }

    /*! \fn public float GetXMove()
        \brief Returneaza distanta in pixeli pe axa X cu care este actualizata pozitia caracterului.
     */
    public float GetXMove()
    {
        return xMove;
    }

    /*! \fn public float GetYMove()
        \brief Returneaza distanta in pixeli pe axa Y cu care este actualizata pozitia caracterului.
     */
    public float GetYMove()
    {
        return yMove;
    }

    /*! \fn public void SetXMove(float xMove)
        \brief Seteaza distanta in pixeli pe axa X cu care va fi actualizata pozitia caracterului.
     */
    public void SetXMove(float xMove)
    {
        this.xMove = xMove;
    }

    /*! \fn public void SetYMove(float yMove)
        \brief Seteaza distanta in pixeli pe axa Y cu care va fi actualizata pozitia caracterului.
     */
    public void SetYMove(float yMove)
    {
        this.yMove = yMove;
    }

     /*! \fn Collide
    \brief Functia Collide se ocupa de verificarea coliziunii intre entitatile de tip Character si harta jocului.

    */

    @Override
    protected void Collide() {
        boolean falling = true;
        boolean positionAdjustment = false; // O variabila care imi arata daca am ajustat deja pozitia personajului in caz ca intra intr-un tile

        for (int i = 0; i < refLink.GetMapHeight(); i++) {
            for (int j = 0; j < refLink.GetMapWidth(); j++) {
                if (refLink.GetTile(j, i).IsSolid()) {
                    if (((this.x >= j * Tile.TILE_WIDTH) && (this.x < (j + 1) * Tile.TILE_WIDTH))
                            || ((this.x + this.width >= j * Tile.TILE_WIDTH) && (this.x + this.width < (j + 1) * Tile.TILE_WIDTH))
                            || (this.x < j * Tile.TILE_WIDTH) && (this.x + this.width > (j + 1) * Tile.TILE_WIDTH)) {
                        if (this.y + this.height == i * Tile.TILE_HEIGHT) {
                            falling = false;
                        }
                        if (this.y + this.height + 4 > i * Tile.TILE_HEIGHT && !positionAdjustment) {
                            this.y = i * Tile.TILE_HEIGHT - this.height;
                            falling = false;
                            positionAdjustment = true;
                        }

                    }
                }
            }
        }
        if (falling) {
            this.y = this.y + 4;
        }
    }

    public boolean GetAlive()
    {
        return alive;
    }

    public void SetAlive(boolean alive)
    {
        this.alive = alive;
    }

    /*! \fn Hit
    \brief Functie ce este apelata atunci cand personajul principat este lovit de un inamic.

    */

    public void Hit(int damage) {
        if (immunityFrames == 0) {
            this.life = this.life - damage;
            immunityFrames = 120;
        }
    }

    public int GetAttacking(){return attacking;}

    public boolean GetRight(){return right;}

}

