package equipeSoccer;

import java.sql.SQLException;
import java.util.ArrayList;

import equipeSoccer_Servlet.ListeHtml;

public class GestionJoueur
{
	private TableRencontres rencontre;
	private TableJoueurs joueur;
	private Connexion cx;

	public GestionJoueur(TableJoueurs client, TableRencontres reservations) throws IFT287Exception
	{
		this.cx = client.getConnexion();
		this.joueur = client;
		this.rencontre = reservations;
	}

	public void ajouter(TupleJoueur c)
			throws SQLException, IFT287Exception, Exception
	{
		try
		{
			// V�rifie si le client existe d�j�
			if (joueur.existe(c.getIdClient()))
				throw new IFT287Exception("Client existe deja : "
						+ c.getIdClient());

			// Ajout du Client dans la table des clients
			joueur.creer(c);

			// Commit
			cx.commit();
		}
		catch (Exception e)
		{
			cx.rollback();
			throw e;
		}
	}

	public void supprimer(int idClient)
			throws SQLException, IFT287Exception, Exception
	{
		try
		{
			// V�rifie si le client existe d�j�
			if (!joueur.existe(idClient))
				throw new IFT287Exception("Le client n'existe pas : "
						+ idClient);
			
			// Vérifie si le client a des reservations
			for(TupleRencontre r : rencontre.listeRencontresJoueur(idClient)){

			}

			// Suppression du Client dans la table des clients
			joueur.supprimer(idClient);

			// Commit
			cx.commit();
		}
		catch (Exception e)
		{
			cx.rollback();
			throw e;
		}
	}

	public String listerJoueurs(String selection)
			throws SQLException, IFT287Exception, Exception
	{

		ArrayList<TupleJoueur> clients = joueur.getJoueurs();

		// Les titres

		System.out.println("idClient nom prenom prix " + selection);

		ListeHtml listeHtml = new ListeHtml("Liste des clients").addTitre("Nom").addTitre("Prenom");

		if (selection != null)
			listeHtml.selectionner(selection);

		for (TupleJoueur c : clients)
		{
			listeHtml.addItem(((Integer) c.getIdClient()).toString())		// le id
					.addItem(c.getPrenom())									// Le prenom
					.addItem(c.getNom())									// Le nom
					.newLigne();

			System.out.print(c.getIdClient() + " " + c.getPrenom() + " "
					+ c.getNom() + " " + c.getAge() + " \n");

		}
		cx.commit();

		return listeHtml.toHtml();
	}
	
	public String afficherClient(String idClientParam)
	{
		try {
			return afficherJoueur(Integer.parseInt(idClientParam));
		}
		catch (Exception e)
		{
			return "<i>(Client invalide!)</i>";
		}
	}

	public String afficherJoueur(int idClient) throws SQLException
	{
		return joueur.getJoueur(idClient).toHtml();
	}

	public boolean existe(int idClient) throws SQLException
	{
		return joueur.existe(idClient);
	}
}
