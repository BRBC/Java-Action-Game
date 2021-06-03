package PaooGame.Items;

import PaooGame.RefLinks;

    /*! \class public class CharacterFactory
    \brief Clasa in care este folosit design pattern-ul Factory pentru a instantia obiecte de tip Monster si Hero.

    */

public class CharacterFactory {
    public static Character GetCharacter(String characterType, RefLinks refLink, float x, float y, int width, int height) {
        if (characterType == null) {
            return null;
        }
        if (characterType.equalsIgnoreCase("Hero")) {
            return new Hero(refLink, x, y, width, height);
        } else if (characterType.equalsIgnoreCase("Monster")) {
            return new Monster(refLink, x, y, width, height);
        }
        return null;
    }
}
