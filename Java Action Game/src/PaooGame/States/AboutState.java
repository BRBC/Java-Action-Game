package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class public class AboutState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class AboutState extends State
{
    private Font font;
    private String[] buttonArray = {"Back"};
    private int buttons;
    private int buttonCounter;
    /*! \fn public AboutState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public AboutState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza.
        super(refLink);
        font=new Font("arial",Font.ITALIC,50);
        buttonCounter = 0;
        buttons = buttonArray.length;
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniu about.
     */
    @Override
    public void Update()
    {
        if(refLink.GetKeyManager().enterKeystroke)
        {
            refLink.GetGame().SetMenuState();
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniu about.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
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
            g.drawString(buttonArray[i], refLink.GetWidth()/4*3, refLink.GetHeight()/4*3);
        }
        g.drawString("Joc PAOO",30,100);
        g.drawString("Catalina Besliu",30,150);
    }
}
