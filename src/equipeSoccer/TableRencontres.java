package equipeSoccer;

import java.sql.Timestamp;
import java.util.ArrayList;

public class TableRencontres
{
	ArrayList<TupleRencontre> rencontres;
	
	public TableRencontres()
	{
		rencontres = new ArrayList<TupleRencontre>();
	}
	
	/**
	 * Vérifie si une rencontre existe.
	 * 
	 */
	public boolean existe(int id)
	{

		for(TupleRencontre t : rencontres) {
			if(t.getIdRencontre() == id) {
				return true;
			}
		}
		return false;
	}
	
	public TupleRencontre getRencontre(int idRencontre)
	{
		for(TupleRencontre t : rencontres) {
			if(t.getIdRencontre() == idRencontre) {
				return t;
			}
		}
		return null;
	}

	/**
	 * Ajout d'une nouvelle reservation dans la base de donnees
	 */
	public void creer(TupleRencontre rencontre)
	{
		rencontres.add(rencontre);
	}

	/**
	 * Suppression d'une reservation
	 */
	public int supprimer(int idRencontre)
	{
		for(TupleRencontre t : rencontres) {
			if(t.getIdRencontre() == idRencontre) {
				rencontres.remove(t);
				return idRencontre;
			}
		}
		return -1;
	}

	public ArrayList<TupleRencontre> listeRencontresJoueur(int idClient)
	{
		ArrayList<TupleRencontre> listeRencontres = new ArrayList<TupleRencontre>();
		return listeRencontres;
	}
	
	public ArrayList<TupleRencontre> listeRencontresTerrain(int idTerrain)
	{
		ArrayList<TupleRencontre> listeReservations = new ArrayList<TupleRencontre>();
		return listeReservations;
	}

	public ArrayList<TupleRencontre> listeRencontres()
	{
		return rencontres;
	}

}
