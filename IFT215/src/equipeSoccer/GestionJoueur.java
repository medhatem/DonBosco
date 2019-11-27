package equipeSoccer;

import java.util.ArrayList;

import equipeSoccer_Servlet.ListeHtml;

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

	public void ajouter(TupleJoueur c) throws IFT215Exception, Exception
	{
		// Met le bon id
		c.setIdJoueur(joueur.getJoueurs().size()+1);
		
		// V�rifie si le client existe d�j�
		if (joueur.existe(c.getIdJoueur()))
			throw new IFT215Exception("Joueur existe deja : "
					+ c.getIdJoueur());

		// Ajout du Client dans la table des clients
		joueur.creer(c);

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

	public String listerJoueurs(String selection)
			throws IFT215Exception, Exception
	{

		ArrayList<TupleJoueur> clients = joueur.getJoueurs();

		// Les titres

		System.out.println("idClient nom prenom prix " + selection);

		ListeHtml listeHtml = new ListeHtml("Liste des clients").addTitre("Nom").addTitre("Prenom");

		if (selection != null)
			listeHtml.selectionner(selection);

		for (TupleJoueur c : clients)
		{
			listeHtml.addItem(((Integer) c.getIdJoueur()).toString())		// le id
					.addItem(c.getPrenom())									// Le prenom
					.addItem(c.getNom())									// Le nom
					.newLigne();

			System.out.print(c.getIdJoueur() + " " + c.getPrenom() + " "
					+ c.getNom() + "\n");

		}

		return listeHtml.toHtml();
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

	public TupleJoueur login(String courriel, String motP)
	{
		return joueur.login(courriel, motP);
	}
}
