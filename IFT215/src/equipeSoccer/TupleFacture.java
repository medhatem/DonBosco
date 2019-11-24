package equipeSoccer;

import java.sql.Timestamp;
import java.util.ArrayList;

public class TupleFacture {
	int idFacture;
	String description;
	ArrayList<TupleJoueur> joueursAyantPaye;
	ArrayList<TupleJoueur> JoueursNAyantPasPaye;
	double prix;
	Timestamp Date;
	
	public TupleFacture(int idFacture, String description, double p,
			Timestamp date)
	{
		super();
		this.idFacture = idFacture;
		this.description = description;
		this.prix = p;
		Date = date;
		
		joueursAyantPaye = new ArrayList<TupleJoueur>();
		JoueursNAyantPasPaye = new ArrayList<TupleJoueur>();
	}
	
	public TupleFacture() 
	{}

	public int getIdFacture()
	{
		return idFacture;
	}

	public void setIdFacture(int idFacture)
	{
		this.idFacture = idFacture;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public ArrayList<TupleJoueur> getJoueursAyantPaye()
	{
		return joueursAyantPaye;
	}

	public void setJoueursAyantPaye(ArrayList<TupleJoueur> joueursAyantPaye)
	{
		this.joueursAyantPaye = joueursAyantPaye;
	}

	public ArrayList<TupleJoueur> getJoueursNAyantPasPaye()
	{
		return JoueursNAyantPasPaye;
	}

	public void setJoueursNAyantPasPaye(ArrayList<TupleJoueur> joueursNAyantPasPaye)
	{
		JoueursNAyantPasPaye = joueursNAyantPasPaye;
	}

	public double getPrix()
	{
		return prix;
	}

	public void setPrix(int prix)
	{
		this.prix = prix;
	}

	public Timestamp getDate()
	{
		return Date;
	}

	public void setDate(Timestamp date)
	{
		Date = date;
	}

	public String toHtml() {
		String html = "(Id : " + getIdFacture() + ") " + getDescription() + " " + getPrix() + " $<br>";
		return html;
	}
	
}
