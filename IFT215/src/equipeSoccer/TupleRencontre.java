package equipeSoccer;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.util.ArrayList; 

public class TupleRencontre {
	int idRencontre;
	ArrayList<TupleJoueur> equipeA;
	ArrayList<TupleJoueur> equipeB;
	Timestamp date;
	TupleTerrain terrain;

	public TupleRencontre(int idRencontre, Timestamp date, TupleTerrain t) {
		this.idRencontre = idRencontre;
		this.date = date;
		equipeA = new ArrayList<TupleJoueur>();
		equipeB = new ArrayList<TupleJoueur>();
		terrain = t;
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
	 public String getMonthForInt() {
		 
		 int num= this.getDate().getMonth();
	        String month = "wrong";
	        DateFormatSymbols dfs = new DateFormatSymbols();
	        String[] months = dfs.getMonths();
	        if (num >= 0 && num <= 11 ) {
	            month = months[num];
	        }
	        return month;
	    }
}
