package equipeSoccer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import equipeSoccer_Servlet.ListeHtml;

public class GestionRencontre
{
	private TableRencontres rencontre;
	private TableJoueurs joueur;
	private TableTerrains chambre;
	private TableFactures commodite;

	private Connexion cx;

	public GestionRencontre(TableRencontres reservation,
			TableJoueurs client, TableTerrains chambre, TableFactures commodite)
			throws IFT287Exception
	{
		this.cx = reservation.getConnexion();
		this.rencontre = reservation;
		this.joueur = client;
		this.chambre = chambre;
		this.commodite = commodite;
	}

	public void ajouter(TupleRencontre r)
			throws SQLException, IFT287Exception, Exception
	{
		try
		{

			// V�rifier l'existence du client
			if (!joueur.existe(r.getIdClient()))
				throw new IFT287Exception("Le client n'existe pas : "
						+ r.getIdClient());

			// .. de la chambre
			if (!chambre.existe(r.getIdChambre()))
				throw new IFT287Exception("La chambre n'existe pas : "
						+ r.getIdChambre());

			// V�rifie si l'association existe d�j�
			if (rencontre.existe(r.getIdChambre(), r.getIdClient(), r.getDateDebut(), r.getDateFin()))
				throw new IFT287Exception("La reservation existe deja : "
						+ r.getIdChambre() + " " + r.getIdClient() + " "
						+ r.getDateDebut() + " " + r.getDateFin());

			// Verifier si la reservation est valide
			if (r.getDateFin().before(r.getDateDebut()))
				throw new IFT287Exception("La date de reservation est incoherente : "
						+ r.getDateDebut() + " " + r.getDateFin());


			// Ajout de l'association dans la table
			rencontre.creer(r);

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
			if (!joueur.existe(idClient))
				throw new IFT287Exception("Le client n'existe pas : "
						+ idClient);

			// .. de la chambre
			if (!chambre.existe(idChambre))
				throw new IFT287Exception("La chambre n'existe pas : "
						+ idChambre);

			// V�rifie si l'association existe d�j�
			if (!rencontre.existe(idChambre, idClient, dateDebut, dateFin))
				throw new IFT287Exception("L'association n'existe pas (chambre / client): "
						+ idChambre + " / " + idClient);

			TupleRencontre r = rencontre.getRencontre(idChambre, idClient, dateDebut, dateFin);

			rencontre.supprimer(r.getIdReservation());

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
			if (!rencontre.existe(idReservation))
				throw new IFT287Exception("La réservation n'existe pas : "
						+ idReservation);

			// Ajout de l'association dans la table
			rencontre.supprimer(idReservation);

			// Commit
			cx.commit();
		}
		catch (Exception e)
		{
			cx.rollback();
			throw e;
		}
	}

	public void afficherRencontreJoueur(int idJoueur)
			throws SQLException, IFT287Exception, Exception
	{
		if (!joueur.existe(idJoueur))
			throw new IFT287Exception("Le client n'existe pas : " + idJoueur);

		joueur.afficherJoueur(idJoueur);

		ArrayList<TupleRencontre> listeRencontres = rencontre.listeRencontresJoueur(idJoueur);
		System.out.println("Chambre DateDebut DateFin Prix");

		for (TupleRencontre r : listeRencontres)
		{

			System.out.print(chambre.getTerrain(r.getIdChambre()).getNom()
					+ " ");
			System.out.print(r.getDateDebut() + " " + r.getDateFin() + " ");

			// Nombre de jours de la reservation
			long jours = (r.getDateFin().getTime() - r.getDateDebut().getTime())
					/ 86400000;

		}

	}

	public String listerRencontres(String selection)
			throws SQLException, IFT287Exception, Exception
	{

		ArrayList<TupleRencontre> res = rencontre.listeRencontres();

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

		for (TupleRencontre c : res)
		{
			listeHtml.addItem(((Integer) c.getIdReservation()).toString())	// le id
					.addItem(joueur.getJoueur(c.getIdClient()).toString())	// La description
					.addItem(chambre.getTerrain(c.getIdChambre()).toString())	// Le type de lit
					.addItem(c.getDateDebut().toString())	 // Date de debut
					.addItem(c.getDateFin().toString());		 // Date de fin; // Prix reservation
		}
		cx.commit();

		return listeHtml.toHtml();
	}
}
