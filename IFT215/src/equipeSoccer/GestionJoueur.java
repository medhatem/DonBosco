package equipeSoccer;

import java.util.ArrayList;


public class GestionJoueur
{
	private TableRencontres rencontre;
	private TableJoueurs joueur;

	public GestionJoueur(TableJoueurs client, TableRencontres reservations)
			throws IFT215Exception
	{
		this.joueur = client;
		this.rencontre = reservations;
	}

	public boolean ajouter(TupleJoueur c) throws IFT215Exception, Exception
	{
		// Met le bon id
		c.setIdJoueur(joueur.getJoueurs().size()+1);
		
		// V�rifie si le client existe d�j�
		if (joueur.existe(c.getIdJoueur()))
			throw new IFT215Exception("Joueur existe deja : "
					+ c.getIdJoueur());

		// Ajout du Client dans la table des clients
		return joueur.creer(c);

	}

	public void supprimer(int idJoueur) throws IFT215Exception, Exception
	{

		// V�rifie si le joueur existe d�j�
		if (!joueur.existe(idJoueur))
			throw new IFT215Exception("Le joueur n'existe pas : " + idJoueur);

		// Vérifie si le joueur a des rencontres
		for (TupleRencontre r : rencontre.listeRencontresJoueur(idJoueur))
		{

		}

		// Suppression du Client dans la table des joueurs
		joueur.supprimer(joueur.getJoueur(idJoueur));

	}



	public String afficherClient(String idClientParam)
	{
		try
		{
			return afficherJoueur(Integer.parseInt(idClientParam));
		}
		catch (Exception e)
		{
			return "<i>(Client invalide!)</i>";
		}
	}

	public String afficherJoueur(int idClient)
	{
		return joueur.getJoueur(idClient).toHtml();
	}

	public boolean existe(int idClient)
	{
		return joueur.existe(idClient);
	}

	public TupleJoueur login(String courriel, String motP) throws IFT215Exception
	{
		return joueur.login(courriel, motP);
	}
}
