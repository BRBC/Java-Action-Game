package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State
{
    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    protected Font font;
    private String[] buttonArray = {"Easy","Hard"};
    private int buttons;
    private int buttonCounter;

    public SettingsState(RefLinks refLink)
    {
            ///Apel al construcotrului clasei de baza.
        super(refLink);
        font = new Font("arial",Font.ITALIC,50);
        buttonCounter = 0;
        buttons = buttonArray.length;
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update()
    {
        if(refLink.GetKeyManager().downKeystroke)
        {
            buttonCounter++;
        } else if(refLink.GetKeyManager().upKeystroke) {
            buttonCounter--;
        }

        if(buttonCounter < 0) {
            buttonCounter = buttonArray.length - 1;
        }

        if(buttonCounter > buttonArray.length - 1) {
            buttonCounter = 0;
        }

        if(buttonCounter == 0 && refLink.GetKeyManager().enterKeystroke){
            refLink.GetGame().GetPlayState().SetEasyDifficulty();
            refLink.GetGame().SetMenuState();
        }

        if(buttonCounter == 1 && refLink.GetKeyManager().enterKeystroke){
            refLink.GetGame().GetPlayState().SetHardDifficulty();
            refLink.GetGame().SetMenuState();
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.menuStateBackground,0,0,960,480,null);
        g.setFont(font);
        for(int i = 0; i < buttons; i++) {
            if (i == buttonCounter) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.black);
            }
            g.drawString(buttonArray[i], refLink.GetWidth() / 8, ((refLink.GetHeight()) / (buttons * 2) * (i + 1)));
        }
    }
}
