package equipeSoccer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import equipeSoccer_Servlet.ListeHtml;

public class GestionReservation
{
	private TableReservations reservation;
	private TableClients client;
	private TableChambres chambre;
	private TableChambreCommodites chambreCommodite;
	private TableCommodites commodite;

	private Connexion cx;

	public GestionReservation(TableReservations reservation,
			TableClients client, TableChambres chambre,
			TableChambreCommodites chambreCommodite, TableCommodites commodite)
			throws IFT287Exception
	{
		this.cx = reservation.getConnexion();
		this.reservation = reservation;
		this.client = client;
		this.chambre = chambre;
		this.chambreCommodite = chambreCommodite;
		this.commodite = commodite;
	}

	public void ajouter(TupleReservation r)
			throws SQLException, IFT287Exception, Exception
	{
		try
		{

			// V�rifier l'existence du client
			if (!client.existe(r.getIdClient()))
				throw new IFT287Exception("Le client n'existe pas : "
						+ r.getIdClient());

			// .. de la chambre
			if (!chambre.existe(r.getIdChambre()))
				throw new IFT287Exception("La chambre n'existe pas : "
						+ r.getIdChambre());

			// V�rifie si l'association existe d�j�
			if (reservation.existe(r.getIdChambre(), r.getIdClient(), r.getDateDebut(), r.getDateFin()))
				throw new IFT287Exception("La reservation existe deja : "
						+ r.getIdChambre() + " " + r.getIdClient() + " "
						+ r.getDateDebut() + " " + r.getDateFin());

			// Verifier si la reservation est valide
			if (r.getDateFin().before(r.getDateDebut()))
				throw new IFT287Exception("La date de reservation est incoherente : "
						+ r.getDateDebut() + " " + r.getDateFin());

			// V�rifier si la chambre est disponible � la date demandee
			if (!reservation.chambreEstDisponible(r))
				throw new IFT287Exception("La chambre n'est pas disponible a la date demandee : "
						+ r.getIdChambre() + " " + r.getDateDebut() + " "
						+ r.getDateFin());

			// Ajout de l'association dans la table
			reservation.creer(r);

			// Commit
			cx.commit();
		}
		catch (Exception e)
		{
			cx.rollback();
			throw e;
		}
	}

	public void supprimer(int idChambre, int idClient, Date dateDebut, Date dateFin)
			throws SQLException, IFT287Exception, Exception
	{
		try
		{
			// V�rifier l'existence du client
			if (!client.existe(idClient))
				throw new IFT287Exception("Le client n'existe pas : "
						+ idClient);

			// .. de la chambre
			if (!chambre.existe(idChambre))
				throw new IFT287Exception("La chambre n'existe pas : "
						+ idChambre);

			// V�rifie si l'association existe d�j�
			if (!reservation.existe(idChambre, idClient, dateDebut, dateFin))
				throw new IFT287Exception("L'association n'existe pas (chambre / client): "
						+ idChambre + " / " + idClient);

			TupleReservation r = reservation.getReservation(idChambre, idClient, dateDebut, dateFin);

			reservation.supprimer(r.getIdReservation());

			// Commit
			cx.commit();
		}
		catch (Exception e)
		{
			cx.rollback();
			throw e;
		}

	}

	public void supprimer(int idReservation)
			throws SQLException, IFT287Exception, Exception
	{
		try
		{
			// V�rifie si l'association existe d�j�
			if (!reservation.existe(idReservation))
				throw new IFT287Exception("La réservation n'existe pas : "
						+ idReservation);

			// Ajout de l'association dans la table
			reservation.supprimer(idReservation);

			// Commit
			cx.commit();
		}
		catch (Exception e)
		{
			cx.rollback();
			throw e;
		}
	}

	public void afficherReservationsClient(int idClient)
			throws SQLException, IFT287Exception, Exception
	{
		if (!client.existe(idClient))
			throw new IFT287Exception("Le client n'existe pas : " + idClient);

		client.afficherClient(idClient);

		ArrayList<TupleReservation> listeReservations = reservation.listeReservationsClient(idClient);
		System.out.println("Chambre DateDebut DateFin Prix");

		for (TupleReservation r : listeReservations)
		{

			System.out.print(chambre.getChambre(r.getIdChambre()).getNom()
					+ " ");
			System.out.print(r.getDateDebut() + " " + r.getDateFin() + " ");

			// Nombre de jours de la reservation
			long jours = (r.getDateFin().getTime() - r.getDateDebut().getTime())
					/ 86400000;

			System.out.println(jours
					* (chambre.getChambre(r.getIdChambre()).getPrix()
							+ commodite.getPrixCommodites(chambreCommodite.getChambreCommodite(r.getIdChambre())))
					+ " ");
		}

	}

	public String prixReservation(TupleReservation r) throws SQLException
	{
		// Nombre de jours de la reservation
		long jours = (r.getDateFin().getTime() - r.getDateDebut().getTime())
				/ 86400000;

		float prixCommodites = commodite.getPrixCommodites(chambreCommodite.getChambreCommodite(r.getIdChambre()));
		float prixTotal = jours
				* (chambre.getChambre(r.getIdChambre()).getPrix()
						+ prixCommodites);

		return prixTotal + " $";
	}

	public String listerReservations(String selection)
			throws SQLException, IFT287Exception, Exception
	{

		ArrayList<TupleReservation> res = reservation.listeReservations();

		// Les titres
		ListeHtml listeHtml = new ListeHtml("Liste des reservations")
				.addTitre("Client")
				.addTitre("Chambre")
				.addTitre("Date début")
				.addTitre("Date fin")
				.addTitre("Coût de la réservation");

		listeHtml.setBordure(true);
		if (selection != null)
			listeHtml.selectionner(selection);

		for (TupleReservation c : res)
		{
			listeHtml.addItem(((Integer) c.getIdReservation()).toString())	// le id
					.addItem(client.getClient(c.getIdClient()).toString())	// La description
					.addItem(chambre.getChambre(c.getIdChambre()).toString())	// Le type de lit
					.addItem(c.getDateDebut().toString())	 // Date de debut
					.addItem(c.getDateFin().toString())		 // Date de fin
					.addItem(prixReservation(c)).newLigne(); // Prix reservation
		}
		cx.commit();

		return listeHtml.toHtml();
	}
}
