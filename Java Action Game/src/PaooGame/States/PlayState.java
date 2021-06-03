package PaooGame.States;

import PaooGame.Items.Character;
import PaooGame.Items.CharacterFactory;
import PaooGame.Items.Hero;
import PaooGame.Items.Monster;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private Character hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Map map;    /*!< Referinta catre harta curenta.*/
    private ArrayList<Character> monsters;
    private int score, killScore;
    protected Font font;
    private int enemyDamage;
    private int healthbar;
    private int gameOverTicks = 0;
    private int victoryTicks = 0;


    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
            ///Construieste harta jocului
        map = new Map(refLink);
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
            ///Construieste eroul
        hero = CharacterFactory.GetCharacter("Hero",refLink,100, 100,80,80);
        //hero = new Hero(refLink,100, 100,80,80);
        monsters = new ArrayList<>();
        monsters.add(CharacterFactory.GetCharacter("Monster",refLink, 10 * Tile.TILE_WIDTH,5 * Tile.TILE_WIDTH, 96,96));
        monsters.add(CharacterFactory.GetCharacter("Monster",refLink, 24 * Tile.TILE_WIDTH,6 * Tile.TILE_WIDTH, 96,96));
        monsters.add(CharacterFactory.GetCharacter("Monster",refLink, 40 * Tile.TILE_WIDTH,5 * Tile.TILE_WIDTH, 96,96));
        monsters.add(CharacterFactory.GetCharacter("Monster",refLink, 69 * Tile.TILE_WIDTH,7 * Tile.TILE_WIDTH, 96,96));
//        monsters.add(new Monster(refLink, 10 * Tile.TILE_WIDTH,5 * Tile.TILE_WIDTH, 96,96));
//        monsters.add(new Monster(refLink, 24 * Tile.TILE_WIDTH,6 * Tile.TILE_WIDTH, 96,96));
//        monsters.add(new Monster(refLink, 40 * Tile.TILE_WIDTH,5 * Tile.TILE_WIDTH, 96,96));
//        monsters.add(new Monster(refLink, 69 * Tile.TILE_WIDTH,7 * Tile.TILE_WIDTH, 96,96));
        score = 0;
        killScore = 10;
        enemyDamage = 4;
        font = new Font("arial",Font.ITALIC,30);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */


    @Override
    public void Update()
    {
        if(gameOverTicks == 0) {
            if (hero.GetLife() <= 0) {
                gameOverTicks = 300;
            }
            map.Update();
            hero.Update();
            for (Character monster : monsters)
            {
                monster.Update();
            }
            CheckCollisions();
            CheckAttackCollisions();
        }
        else {
            gameOverTicks--;
        }
        if (gameOverTicks == 1)
        {
            refLink.GetGame().SetMenuState();
            refLink.GetGame().SetPlayState(new PlayState(refLink));
        }
        if(Victory())
        {
            refLink.GetGame().SetMenuState();
            refLink.GetGame().SetPlayState(new PlayState(refLink));
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        healthbar = 14 * hero.GetLife();
        if(healthbar <= 0)
        {
            healthbar = 0;
        }
        g.setFont(font);
        map.Draw(g);
        for (Character monster : monsters)
        {
            monster.Draw(g);
        }
        hero.Draw(g);
//        g.drawString("HP:"+(hero.GetLife()),0,30);
        //g.drawRect(10,10,140,30);
        g.setColor(Color.black);
        g.fillRect(5,5,150,40);
        g.setColor(Color.red);
        g.fillRect(10,10,healthbar,30);
        g.drawString("Score:" + score,160,35);
        if(gameOverTicks != 0)
        {
            g.drawString("GAME OVER", refLink.GetWidth()/2 - 75,refLink.GetHeight()/2);
        }
    }

    /*! \fn public void CheckCollisions()
        \brief Verifica daca au avut loc coliziuni intre monstrii si erou.
     */

    private void CheckCollisions()
    {
        for (Character monster : monsters)
        {
            if(monster.GetAlive())
            {
                if ((hero.GetX() + 30 < monster.GetX() && hero.GetX() + hero.GetWidth() - 30 > monster.GetX())
                        || (hero.GetX() + 30 < monster.GetX() + monster.GetWidth() && hero.GetX() + hero.GetWidth() - 30 > monster.GetX() + monster.GetWidth()))
                {
                    hero.Hit(enemyDamage);
                }
            }
        }
    }

    /*! \fn public void CheckAttackCollisions()
        \brief Verifica daca eroul a lovit monstrii.
     */

    private void CheckAttackCollisions()
    {
        if(hero.GetAttacking()!=0)
        {
            if(hero.GetRight())
            {
                for (Character monster : monsters)
                {
                    if((monster.GetX() < hero.GetX() + hero.GetWidth() + 25) && (monster.GetX() > hero.GetX()) && monster.GetAlive())
                    {
                        monster.SetAlive(false);
                        score = score + killScore;
                    }
                }
            }
            else
            {
                for (Character monster : monsters)
                {
                    if((monster.GetX() + monster.GetWidth() + 25 > hero.GetX()) && (monster.GetX() < hero.GetX()) && monster.GetAlive())
                    {
                        monster.SetAlive(false);
                        score = score + killScore;
                    }
                }
            }
        }
    }

    public void SetHardDifficulty()
    {
        enemyDamage = 5;
        killScore = 15;
    }

    public void SetEasyDifficulty()
    {
        enemyDamage = 4;
        killScore = 10;
    }

    /*! \fn public void Victory()
        \brief Verifica daca au fost indeplinite conditiile victoriei.
     */

    private boolean Victory()
    {
        boolean victory = true;
        for (Character monster : monsters)
        {
            victory = victory && !monster.GetAlive();
        }
        return(victory && hero.GetX() > refLink.GetMapWidth()*Tile.TILE_WIDTH);
    }
}
