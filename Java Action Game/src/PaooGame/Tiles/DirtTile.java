package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class DirtTile extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public DirtTile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.dirt, id);
    }

    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
