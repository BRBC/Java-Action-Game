package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{
    private Font font = new Font("arial",Font.ITALIC,50);
    private String[] buttonArray = {"Play","Options","About","Quit"};
    private int buttons;
    private int buttonCounter;
    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza.
        super(refLink);
        buttonCounter = 0;
        buttons = buttonArray.length;
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
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
            refLink.GetGame().SetPlayState();
        }

        if(buttonCounter == 1 && refLink.GetKeyManager().enterKeystroke){
            refLink.GetGame().SetSettingsState();
        }

        if(buttonCounter == 2 && refLink.GetKeyManager().enterKeystroke){
            refLink.GetGame().SetAboutState();
        }

        if(buttonCounter == 3 && refLink.GetKeyManager().enterKeystroke){
            refLink.GetGame().StopGame();
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.menuStateBackground,0,0,960,480,null);
        g.setFont(font);
        for(int i = 0; i < buttons; i++)
        {
            if(i == buttonCounter)
            {
                g.setColor(Color.red);
            }
            else
            {
                g.setColor(Color.black);
            }
            g.drawString(buttonArray[i],refLink.GetWidth()/8,((refLink.GetHeight())/(buttons*2)*(i+1)));
        }
    }
}
