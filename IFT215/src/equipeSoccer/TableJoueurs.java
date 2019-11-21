package equipeSoccer;

import java.util.ArrayList;

public class TableJoueurs {

	ArrayList<TupleJoueur> joueurs;

    public TableJoueurs()
    {
    	joueurs = new ArrayList<TupleJoueur>();
    }

    public boolean existe(int idClient)
    {
    	return false;
    }
    
    public TupleJoueur getJoueur(int id)
    {
		for(TupleJoueur t : joueurs) {
			if(t.getIdJoueur() == id) {
				return t;
			}
		}
		return null;
    }

    public void creer(TupleJoueur joueur)
    {
        joueurs.add(joueur);
    }
    
    /**
     * Suppression d'un client.
     */
    public void supprimer(TupleJoueur joueur)
    {
        joueurs.remove(joueur);
    }

	public void afficherJoueur(int idClient) {
		TupleJoueur c = getJoueur(idClient);
		
		System.out.println("id prenom nom");
		System.out.println(c.getIdJoueur() + " " + c.getPrenom() + " " + c.getNom());
	
	}

	public ArrayList<TupleJoueur> getJoueurs()
	{
        return joueurs;
	}

}
