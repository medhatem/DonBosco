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
	int scoreA;
	public TupleTerrain getTerrain() {
		return terrain;
	}

	public void setTerrain(TupleTerrain terrain) {
		this.terrain = terrain;
	}

	public int getScoreA() {
		return scoreA;
	}

	public void setScoreA(int scoreA) {
		this.scoreA = scoreA;
	}

	public int getScoreB() {
		return scoreB;
	}

	public void setScoreB(int scoreB) {
		this.scoreB = scoreB;
	}
	int scoreB;

	public TupleRencontre(int idRencontre, Timestamp date, TupleTerrain t,int scoreA,int scoreB) {
		this.idRencontre = idRencontre;
		this.date = date;
		equipeA = new ArrayList<TupleJoueur>();
		equipeB = new ArrayList<TupleJoueur>();
		terrain = t;
		this.scoreA=scoreA;
		this.scoreB=scoreB;
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
