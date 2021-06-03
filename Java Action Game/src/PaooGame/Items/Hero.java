package PaooGame.Items;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import PaooGame.Animation.Animation;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;

import static java.lang.System.currentTimeMillis;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Hero extends Character {
    //private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/

    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    private Animation attackRight, attackLeft;

    public Hero(RefLinks refLink, float x, float y, int width, int height) {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y, width, height);
        ///Seteaza imaginea de start a eroului
        //image = Assets.heroLeft;
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
//        normalBounds.x = 16;
//        normalBounds.y = 16;
//        normalBounds.width = 16;
//        normalBounds.height = 32;

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
//        attackBounds.x = 10;
//        attackBounds.y = 10;
//        attackBounds.width = 38;
//        attackBounds.height = 38;
        idleRight = new Animation(Assets.heroIdleRight, 100);
        idleLeft = new Animation(Assets.heroIdleLeft, 100);
        runRight = new Animation(Assets.heroRunRight, 100);
        runLeft = new Animation(Assets.heroRunLeft, 100);
        attackRight = new Animation(Assets.heroAttackRight,100);
        attackLeft = new Animation(Assets.heroAttackLeft,100);
        running = false;
        right = true;
        left = false;
        immunityFrames = 0;
        attacking = 0;
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update() {
        if(life <= 0) {
            SetAlive(false);
        }

        if (immunityFrames != 0) {
            immunityFrames = immunityFrames - 1;
        }

        ///Verifica daca a fost apasata o tasta
        ///Actualizeaza pozitia
        ///Actualizeaza imaginea
        Collide();
        if(attacking != 0)
        {
            attacking--;
        }
        else {
            GetInput();
            Move();
            if (refLink.GetKeyManager().left || refLink.GetKeyManager().right) {
                running = refLink.GetKeyManager().right ^ refLink.GetKeyManager().left;
                if (refLink.GetKeyManager().left) {
                    if (right) {
                        right = false;
                    }
                    if (!left) {
                        left = true;
                    }
                }
                if (refLink.GetKeyManager().right) {
                    if (left) {
                        left = false;
                    }
                    if (!right) {
                        right = true;
                    }
                }
            } else {
                running = false;
            }
        }

        this.time = currentTimeMillis();
        refLink.GetCamera().Center(this);
    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput() {
        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;
//        ///Verificare apasare tasta "sus"
//        if (refLink.GetKeyManager().up) {
//            yMove = -speed;
//        }
//        ///Verificare apasare tasta "jos"
//        if (refLink.GetKeyManager().down) {
//            yMove = speed;
//        }
        if(refLink.GetKeyManager().space) {
            if(attacking == 0) {
                attacking = 35;
            }
        }

        if (!refLink.GetKeyManager().left || !refLink.GetKeyManager().right) {
            ///Verificare apasare tasta "left"
            if (refLink.GetKeyManager().left) {
                xMove = -speed;
            }
            ///Verificare apasare tasta "dreapta"
            if (refLink.GetKeyManager().right) {
                xMove = speed;
            }
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g) {
        if ((immunityFrames / 10) % 2 == 0) {
            if(attacking == 0) {
                if (right && !running) {
                    g.drawImage(idleRight.Update(time), (int) (x - refLink.GetCamera().getxOffset()), (int) y, width, height, null);
                }
                if (left && !running) {
                    g.drawImage(idleLeft.Update(time), (int) (x - refLink.GetCamera().getxOffset()), (int) y, width, height, null);
                }
                if (right && running) {
                    g.drawImage(runRight.Update(time), (int) (x - refLink.GetCamera().getxOffset()), (int) y, width, height, null);
                }
                if (left && running) {
                    g.drawImage(runLeft.Update(time), (int) (x - refLink.GetCamera().getxOffset()), (int) y, width, height, null);
                }
            } else {
                if(right){
                    g.drawImage(attackRight.Update(time), (int) (x - refLink.GetCamera().getxOffset()), (int) y, width, height, null);
                } else {
                    g.drawImage(attackLeft.Update(time), (int) (x - refLink.GetCamera().getxOffset()), (int) y, width, height, null);
                }
            }
        }
    }

//    public void Hit(int damage) {
//        if (immunityFrames == 0) {
//            this.life = this.life - damage;
//            immunityFrames = 120;
//        }
//    }
//
//    public int GetAttacking(){return attacking;}
//
//    public boolean GetRight(){return right;}
//
//    public int GetLife(){return life;}
}


            ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
//        g.setColor(Color.blue);
//        g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);


