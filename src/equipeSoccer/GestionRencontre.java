package equipeSoccer;

import java.sql.Timestamp;
import java.util.ArrayList;


public class GestionRencontre
{
	private TableRencontres rencontre;
	private TableJoueurs joueur;
	private TableTerrains chambre;
	private TableFactures facture;

	public GestionRencontre(TableRencontres reservation, TableJoueurs client,
			TableTerrains chambre, TableFactures commodite)
			throws IFT215Exception
	{
		this.rencontre = reservation;
		this.joueur = client;
		this.chambre = chambre;
		this.facture = commodite;
	}

	public void ajouter(TupleRencontre r) throws IFT215Exception, Exception
	{
		ArrayList<TupleJoueur> equipeA = r.getEquipeA();
		ArrayList<TupleJoueur> equipeB = r.getEquipeB();
		
		for(TupleJoueur j : joueur.getJoueurs()) {
			if(j.getIdJoueur() < joueur.getJoueurs().size()/2) {
				equipeA.add(j);
			} else {
				equipeB.add(j);
			}
		}
		
		rencontre.creer(r);
	}

	public void supprimer(int idRencontre) throws IFT215Exception, Exception
	{

		if (!rencontre.existe(idRencontre))
			throw new IFT215Exception("La réservation n'existe pas : "
					+ idRencontre);

		// Ajout de l'association dans la table
		rencontre.supprimer(idRencontre);

	}

	public void afficherRencontreJoueur(int idJoueur)
			throws IFT215Exception, Exception
	{

	}

	
	
	public TableRencontres getRencontre() {
		return rencontre;
	}

	public void setRencontre(TableRencontres rencontre) {
		this.rencontre = rencontre;
	}
	
}
