package equipeSoccer;

import java.util.ArrayList;

public class TableTerrains {
    
	ArrayList<TupleTerrain> terrains;

    public TableTerrains()
    {
    	terrains = new ArrayList<TupleTerrain>();
    }

    /**
     * Vérifie si un terrain existe.
     */
	public boolean existe(int id)
	{

		for(TupleTerrain t : terrains) {
			if(t.getIdTerrain() == id) {
				return true;
			}
		}
		return false;
	}

    /**
     * Lecture d'une chambre
     */
    public TupleTerrain getTerrain(int id)
    {
		for(TupleTerrain t : terrains) {
			if(t.getIdTerrain() == id) {
				return t;
			}
		}
		return null;
    }

    /**
     * Ajout d'une nouvelle chambre dans la base de donnees
     */
    public void creer(TupleTerrain terrain)
    {
        terrains.add(terrain);
    }
    
    /**
     * Suppression d'une chambre
     * @return 
     */
    public void supprimer(TupleTerrain terrain)
    {
        terrains.remove(terrain);
    }

	public String afficher(TupleTerrain terrain)
	{
		return terrain.toHtml();
	}

}
