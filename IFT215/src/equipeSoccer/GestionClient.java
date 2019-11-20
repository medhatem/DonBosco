package equipeSoccer;

import java.sql.SQLException;
import java.util.ArrayList;

import equipeSoccer_Servlet.ListeHtml;

public class GestionClient
{
	private TableReservations reservation;
	private TableClients client;
	private Connexion cx;

	public GestionClient(TableClients client, TableReservations reservations) throws IFT287Exception
	{
		this.cx = client.getConnexion();
		this.client = client;
		this.reservation = reservations;
	}

	public void ajouter(TupleClient c)
			throws SQLException, IFT287Exception, Exception
	{
		try
		{
			// V�rifie si le client existe d�j�
			if (client.existe(c.getIdClient()))
				throw new IFT287Exception("Client existe deja : "
						+ c.getIdClient());

			// Ajout du Client dans la table des clients
			client.creer(c);

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
			if (!client.existe(idClient))
				throw new IFT287Exception("Le client n'existe pas : "
						+ idClient);
			
			// Vérifie si le client a des reservations
			for(TupleReservation r : reservation.listeReservationsClient(idClient)){
				if (!reservation.reservationEstPerimee(r))
					throw new IFT287Exception("Le client a au moins une réservation : (" + idClient + ") " + client.getClient(idClient).getNom() + ", " + client.getClient(idClient).getPrenom() + "\nSVP supprimer les réservations d'abord.");
				else {
					throw new IFT287Exception("Le client a au moins une réservation (périmée) : (" + idClient + ") " + client.getClient(idClient).getNom() + ", " + client.getClient(idClient).getPrenom() + "\nSVP supprimer les réservations d'abord.");
				}
			}

			// Suppression du Client dans la table des clients
			client.supprimer(idClient);

			// Commit
			cx.commit();
		}
		catch (Exception e)
		{
			cx.rollback();
			throw e;
		}
	}

	public String listerClients(String selection)
			throws SQLException, IFT287Exception, Exception
	{

		ArrayList<TupleClient> clients = client.getClients();

		// Les titres

		System.out.println("idClient nom prenom prix " + selection);

		ListeHtml listeHtml = new ListeHtml("Liste des clients").addTitre("Nom").addTitre("Prenom");

		if (selection != null)
			listeHtml.selectionner(selection);

		for (TupleClient c : clients)
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
			return afficherClient(Integer.parseInt(idClientParam));
		}
		catch (Exception e)
		{
			return "<i>(Client invalide!)</i>";
		}
	}

	public String afficherClient(int idClient) throws SQLException
	{
		return client.getClient(idClient).toHtml();
	}

	public boolean existe(int idClient) throws SQLException
	{
		return client.existe(idClient);
	}
}
