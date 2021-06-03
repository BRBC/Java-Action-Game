package PaooGame.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage heroLeft;
    public static BufferedImage heroRight;
    public static BufferedImage background;
    public static BufferedImage menuStateBackground;
    public static BufferedImage air;
    public static BufferedImage soil;
    public static BufferedImage grass;
    public static BufferedImage mountain;
    public static BufferedImage townGrass;
    public static BufferedImage townGrassDestroyed;
    public static BufferedImage townSoil;
    public static BufferedImage water;
    public static BufferedImage rockUp;
    public static BufferedImage rockDown;
    public static BufferedImage rockLeft;
    public static BufferedImage rockRight;
    public static BufferedImage tree;

    public static ArrayList<BufferedImage> heroIdleRight = new ArrayList<>();
    public static ArrayList<BufferedImage> heroIdleLeft = new ArrayList<>();
    public static ArrayList<BufferedImage> heroRunRight = new ArrayList<>();
    public static ArrayList<BufferedImage> heroRunLeft = new ArrayList<>();
    public static ArrayList<BufferedImage> monsterRunRight = new ArrayList<>();
    public static ArrayList<BufferedImage> monsterRunLeft = new ArrayList<>();
    public static ArrayList<BufferedImage> heroAttackRight = new ArrayList<>();
    public static ArrayList<BufferedImage> heroAttackLeft = new ArrayList<>();

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        try {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
            SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Tileset.png"), 16, 16);
            SpriteSheet heroIdleRightSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/HeroIdleRightSpriteSheet.png"), 50, 37);
            SpriteSheet heroIdleLeftSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/HeroIdleLeftSpriteSheet.png"), 50, 37);
            SpriteSheet heroRunRightSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/HeroRunRightSpriteSheet.png"), 50, 37);
            SpriteSheet heroRunLeftSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/HeroRunLeftSpriteSheet.png"), 50, 37);
            SpriteSheet monsterRunRightSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/MonsterRunRightSpriteSheet.png"), 48, 48);
            SpriteSheet monsterRunLeftSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/MonsterRunLeftSpriteSheet.png"), 48, 48);
            SpriteSheet backgroundSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Background.png"), 320, 192);
            SpriteSheet menuStateBackgroundSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/MenuStateBackground.png"), 960, 480);
            SpriteSheet heroAttackRightSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/HeroAttackRightSpriteSheet.png"), 50, 37);
            SpriteSheet heroAttackLeftSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/HeroAttackLeftSpriteSheet.png"), 50, 37);


            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        grass = sheet.crop(1, 0);
        soil = sheet.crop(1, 1);
        air = sheet.crop(7,1);
        background = backgroundSheet.crop(0,0);
        menuStateBackground = menuStateBackgroundSheet.crop(0,0);
        //water = sheet.crop(2, 0);
        //mountain = sheet.crop(3, 0);
        //townGrass = sheet.crop(0, 1);
        //townGrassDestroyed = sheet.crop(1, 1);
        //townSoil = sheet.crop(2, 1);
        //tree = sheet.crop(3, 1);
        //heroLeft = sheet.crop(0, 2);
        //heroRight = sheet.crop(1, 2);
        //rockUp = sheet.crop(2, 2);
        //rockDown = sheet.crop(3, 2);
        //rockLeft = sheet.crop(0, 3);
        //rockRight = sheet.crop(1, 3);

        heroIdleRightSheet.cropEntityFramesRight(heroIdleRight);
        heroIdleLeftSheet.cropEntityFramesLeft(heroIdleLeft);
        heroRunRightSheet.cropEntityFramesRight(heroRunRight);
        heroRunLeftSheet.cropEntityFramesLeft(heroRunLeft);
        monsterRunRightSheet.cropEntityFramesRight(monsterRunRight);
        monsterRunLeftSheet.cropEntityFramesLeft(monsterRunLeft);
        heroAttackRightSheet.cropEntityFramesRight(heroAttackRight);
        heroAttackLeftSheet.cropEntityFramesLeft(heroAttackLeft);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
