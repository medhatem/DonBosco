package equipeSoccer;


public class TupleFacture {
	int idCommodite;
	String description;
	int prix;
	
	public TupleFacture(int idCommodite, String description, int prix) {
		this.idCommodite = idCommodite;
		this.description = description;
		this.prix = prix;
	}
	
	public TupleFacture() 
	{}

	public int getIdCommodite() {
		return idCommodite;
	}

	public void setIdCommodite(int idCommodite) {
		this.idCommodite = idCommodite;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public String toHtml() {
		String html = "(Id : " + getIdCommodite() + ") " + getDescription() + " " + getPrix() + " $<br>";
		return html;
	}
	
}
