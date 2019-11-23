package equipeSoccer;

import java.sql.Timestamp;
import java.util.ArrayList;

import equipeSoccer_Servlet.ListeHtml;

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

	public String listerRencontres(String selection)
			throws IFT215Exception, Exception
	{

		ArrayList<TupleRencontre> res = rencontre.listeRencontres();

		// Les titres
		ListeHtml listeHtml = new ListeHtml("Liste des reservations").addTitre("Client").addTitre("Chambre").addTitre("Date début").addTitre("Date fin").addTitre("Coût de la réservation");
		
		listeHtml.setBordure(true);
		if (selection != null)
			listeHtml.selectionner(selection);

		for (TupleRencontre c : res)
		{
			listeHtml.addItem(((Integer) c.getIdRencontre()).toString())	// le id
					.addItem(c.getDate().toString());	 // Date
		}

		return listeHtml.toHtml();
	}
}
