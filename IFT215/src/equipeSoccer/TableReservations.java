package equipeSoccer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableReservations
{
	private PreparedStatement stmtExiste;
	private PreparedStatement stmtExisteId;
	private PreparedStatement stmtListeChambre;
	private PreparedStatement stmtListeAll;
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtDelete;
	private PreparedStatement stmtListeClient;

	private Connexion cx;

	/**
	 * Creation d'une instance. Des énoncés SQL pour chaque requête sont
	 * précompilés.
	 */
	public TableReservations(Connexion cx) throws SQLException
	{
		this.cx = cx;

		stmtExiste = cx.getConnection().prepareStatement("select id, chambre, client, datedebut, datefin from reservation where chambre = ? and client = ? and datedebut = ? and datefin = ?");

		stmtExisteId = cx.getConnection().prepareStatement("select id, chambre, client, datedebut, datefin from reservation where id = ?");

		stmtListeAll = cx.getConnection().prepareStatement("select id, chambre, client, datedebut, datefin from reservation");

		stmtListeClient = cx.getConnection().prepareStatement("select id, chambre, client, datedebut, datefin from reservation where client = ?");

		stmtListeChambre = cx.getConnection().prepareStatement("select id, chambre, client, datedebut, datefin from reservation where chambre = ?");

		stmtInsert = cx.getConnection().prepareStatement("insert into reservation (chambre, client, datedebut, datefin) values (?,?,?,?)");

		stmtDelete = cx.getConnection().prepareStatement("delete from reservation where id = ?");
	}

	/**
	 * Retourner la connexion associée.
	 */
	public Connexion getConnexion()
	{
		return cx;
	}

	/**
	 * Vérifie si une reservation existe.
	 * 
	 */
	public boolean existe(int idChambre, int idClient, Date dateDebut, Date dateFin)
			throws SQLException
	{
		stmtExiste.setInt(1, idChambre);
		stmtExiste.setInt(2, idClient);
		stmtExiste.setDate(3, dateDebut);
		stmtExiste.setDate(4, dateFin);

		ResultSet rset = stmtExiste.executeQuery();
		boolean reservationExiste = rset.next();
		rset.close();
		return reservationExiste;
	}
	
	public boolean existe(int idReservation)
			throws SQLException
	{
		stmtExisteId.setInt(1, idReservation);

		ResultSet rset = stmtExisteId.executeQuery();
		boolean reservationExiste = rset.next();
		rset.close();
		return reservationExiste;
	}
	
	public TupleReservation getReservation(int idChambre, int idClient, Date dateDebut, Date dateFin)
			throws SQLException
	{
		stmtExiste.setInt(1, idChambre);
		stmtExiste.setInt(2, idClient);
		stmtExiste.setDate(3, dateDebut);
		stmtExiste.setDate(4, dateFin);

		ResultSet rset = stmtExiste.executeQuery();
		
		TupleReservation t = new TupleReservation();

		t.setIdReservation(rset.getInt(1));
		t.setIdChambre(rset.getInt(2));
		t.setIdClient(rset.getInt(3));
		t.setDateDebut(rset.getDate(4));
		t.setDateFin(rset.getDate(5));

		return t;
	}

	/**
	 * Ajout d'une nouvelle reservation dans la base de donnees
	 */
	public void creer(TupleReservation reservation) throws SQLException
	{
		/* Ajout du client. */
		stmtInsert.setInt(1, reservation.getIdChambre());
		stmtInsert.setInt(2, reservation.getIdClient());
		stmtInsert.setDate(3, reservation.getDateDebut());
		stmtInsert.setDate(4, reservation.getDateFin());
		stmtInsert.executeUpdate();
	}

	/**
	 * Suppression d'une reservation
	 */
	public int supprimer(int idReservation) throws SQLException
	{
		stmtDelete.setInt(1, idReservation);

		return stmtDelete.executeUpdate();
	}

	public ArrayList<TupleReservation> listeReservationsClient(int idClient)
			throws SQLException
	{
		ArrayList<TupleReservation> listeReservations = new ArrayList<TupleReservation>();

		stmtListeClient.setInt(1, idClient);
		ResultSet rset = stmtListeClient.executeQuery();

		while (rset.next())
		{
			TupleReservation t = new TupleReservation();

			t.setIdReservation(rset.getInt(1));
			t.setIdChambre(rset.getInt(2));
			t.setIdClient(rset.getInt(3));
			t.setDateDebut(rset.getDate(4));
			t.setDateFin(rset.getDate(5));

			listeReservations.add(t);
		}

		rset.close();
		return listeReservations;
	}
	
	public ArrayList<TupleReservation> listeReservationsChambre(int idChambre)
			throws SQLException
	{
		ArrayList<TupleReservation> listeReservations = new ArrayList<TupleReservation>();

		stmtListeChambre.setInt(1, idChambre);
		ResultSet rset = stmtListeChambre.executeQuery();

		while (rset.next())
		{
			TupleReservation t = new TupleReservation();

			t.setIdReservation(rset.getInt(1));
			t.setIdChambre(rset.getInt(2));
			t.setIdClient(rset.getInt(3));
			t.setDateDebut(rset.getDate(4));
			t.setDateFin(rset.getDate(5));

			listeReservations.add(t);
		}

		rset.close();
		return listeReservations;
	}

	public ArrayList<TupleReservation> listeReservations() throws SQLException
	{
		ArrayList<TupleReservation> listeReservations = new ArrayList<TupleReservation>();

		ResultSet rset = stmtListeAll.executeQuery();

		while (rset.next())
		{
			TupleReservation t = new TupleReservation();

			t.setIdReservation(rset.getInt(1));
			t.setIdChambre(rset.getInt(2));
			t.setIdClient(rset.getInt(3));
			t.setDateDebut(rset.getDate(4));
			t.setDateFin(rset.getDate(5));

			listeReservations.add(t);
		}

		rset.close();
		return listeReservations;
	}

	public boolean chambreEstDisponible(TupleReservation r) throws SQLException
	{

		stmtListeChambre.setInt(1, r.getIdChambre());

		ResultSet rset = stmtListeChambre.executeQuery();

		while (rset.next())
		{
			Date debut = rset.getDate(4);
			Date fin = rset.getDate(5);

			// Si les dates s'overlappent
			if (r.getDateDebut().after(debut) && r.getDateDebut().before(fin)
					|| r.getDateFin().after(debut)
							&& r.getDateDebut().before(fin))
				return false;

		}

		return true;

	}
	
	public boolean reservationEstPerimee(TupleReservation r) {
		// Est-ce que maintenant est avant la date de fin de la reservation?
		return (new java.util.Date()).after(r.dateFin);
	}
}
