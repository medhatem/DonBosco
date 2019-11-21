package equipeSoccer;

import java.sql.Timestamp;
import java.util.ArrayList; 

public class TupleRencontre {
	int idRencontre;
	ArrayList<TupleJoueur> equipeA;
	ArrayList<TupleJoueur> equipeB;
	Timestamp date;

	public TupleRencontre(int idRencontre, Timestamp date) {
		this.idRencontre = idRencontre;
		this.date = date;
		equipeA = new ArrayList<TupleJoueur>();
		equipeB = new ArrayList<TupleJoueur>();
	}
	
	public TupleRencontre() {
	}
	
	public int getIdRencontre()
	{
		return idRencontre;
	}

	public void setIdRencontre(int idReservation)
	{
		this.idRencontre = idReservation;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public ArrayList<TupleJoueur> getEquipeA()
	{
		return equipeA;
	}

	public void setEquipeA(ArrayList<TupleJoueur> equipeA)
	{
		this.equipeA = equipeA;
	}

	public ArrayList<TupleJoueur> getEquipeB()
	{
		return equipeB;
	}

	public void setEquipeB(ArrayList<TupleJoueur> equipeB)
	{
		this.equipeB = equipeB;
	}

}
