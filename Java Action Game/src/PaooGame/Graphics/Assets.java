package PaooGame.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets
{

    public static BufferedImage grass;
    public static BufferedImage dirt;
    public static BufferedImage air;

    public static BufferedImage menuStateBackground;
    public static BufferedImage gameStateBackground;

    public static ArrayList<BufferedImage> knightIdleRight = new ArrayList<>();
    public static ArrayList<BufferedImage> knightIdleLeft = new ArrayList<>();
    public static ArrayList<BufferedImage> knightRunRight = new ArrayList<>();
    public static ArrayList<BufferedImage> knightRunLeft = new ArrayList<>();
    public static ArrayList<BufferedImage> knightAttackRight = new ArrayList<>();
    public static ArrayList<BufferedImage> knightAttackLeft = new ArrayList<>();


    public static ArrayList<BufferedImage> skeletonRunRight = new ArrayList<>();
    public static ArrayList<BufferedImage> skeletonRunLeft = new ArrayList<>();


    public static void Init()
    {

        SpriteSheet TileSet = new SpriteSheet(ImageLoader.LoadImage("/textures/64Tileset.png"),64,64);

        SpriteSheet knightIdleRightSheet= new SpriteSheet(ImageLoader.LoadImage("/textures/HeroKnightIdleRight.png"),50,50);
        SpriteSheet knightIdleLeftSheet= new SpriteSheet(ImageLoader.LoadImage("/textures/HeroKnightIdleLeft.png"),50,50);

        SpriteSheet knightRunRightSheet= new SpriteSheet(ImageLoader.LoadImage("/textures/HeroKnightRunRight.png"),50,50);
        SpriteSheet knightRunLeftSheet= new SpriteSheet(ImageLoader.LoadImage("/textures/HeroKnightRunLeft.png"),50,50);

        SpriteSheet knightAttackRightSheet = new SpriteSheet(ImageLoader.LoadImage(("/textures/HeroKnightAttackRight.png")),50,50);
        SpriteSheet knightAttackLeftSheet = new SpriteSheet(ImageLoader.LoadImage(("/textures/HeroKnightAttackLeft.png")),50,50);

        SpriteSheet skeletonRunRightSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/SkeletonRunRight.png"),44,66);
        SpriteSheet skeletonRunLeftSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/SkeletonRunLeft.png"),44,66);

        SpriteSheet menuStateBackroundSheet= new SpriteSheet(ImageLoader.LoadImage("/textures/MenuStateBackground.png"),960,540);
        SpriteSheet gameStateBackroundSheet= new SpriteSheet(ImageLoader.LoadImage("/textures/GameStateBackground.png"),960,540);

        grass = TileSet.cropTiles(1,3);
        dirt = TileSet.cropTiles(1,4);
        air = TileSet.cropTiles(0,0);

        menuStateBackground = menuStateBackroundSheet.cropTiles(0,0);
        gameStateBackground = gameStateBackroundSheet.cropTiles(0,0);

        knightIdleRightSheet.cropEntityFramesRight(knightIdleRight);
        knightRunRightSheet.cropEntityFramesRight(knightRunRight);

        knightIdleLeftSheet.cropEntityFramesLeft(knightIdleLeft);
        knightRunLeftSheet.cropEntityFramesLeft(knightRunLeft);

        knightAttackRightSheet.cropEntityFramesRight(knightAttackRight);
        knightAttackLeftSheet.cropEntityFramesLeft(knightAttackLeft);

        skeletonRunRightSheet.cropEntityFramesRight(skeletonRunRight);
        skeletonRunLeftSheet.cropEntityFramesLeft(skeletonRunLeft);
    }
}
