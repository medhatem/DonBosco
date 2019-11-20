package equipeSoccer;

import java.sql.Date; 

public class TupleReservation {
	int idReservation;
	int idChambre;
	int idClient;
	Date dateDebut;
	Date dateFin;

	public TupleReservation(int idReservation, int idClient, int idChambre, Date dateDebut, Date dateFin) {
		this.idReservation = idReservation;
		this.idChambre = idChambre;
		this.idClient = idClient;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	
	// Dans le cas ou le tuple sert a creer un élément dans la BD (on ne connait pas encore le id)
	public TupleReservation(int idClient, int idChambre, Date dateDebut, Date dateFin) {
		this.idChambre = idChambre;
		this.idClient = idClient;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public int getIdReservation()
	{
		return idReservation;
	}

	public void setIdReservation(int idReservation)
	{
		this.idReservation = idReservation;
	}

	public TupleReservation() {
	}

	public int getIdChambre() {
		return idChambre;
	}

	public void setIdChambre(int idChambre) {
		this.idChambre = idChambre;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

}
