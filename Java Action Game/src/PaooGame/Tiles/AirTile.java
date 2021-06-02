package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class AirTile extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public AirTile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.air, id);
    }
}