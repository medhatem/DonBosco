package equipeSoccer;

public class TupleChambre {
	int idChambre;
	String nom;
	String typeLit;
	int prix;
	
	public TupleChambre(int idChambre, String nom, String typeLit, int prix) {
		this.idChambre = idChambre;
		this.nom = nom;
		this.typeLit = typeLit;
		this.prix = prix;
	}

	public TupleChambre() {
	}

	public int getIdChambre() {
		return idChambre;
	}

	public void setIdChambre(int idChambre) {
		this.idChambre = idChambre;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTypeLit() {
		return typeLit;
	}

	public void setTypeLit(String typeLit) {
		this.typeLit = typeLit;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public String toHtml() {
		String html = "<H2>Détails de la chambre :</H2>";
		html += "<b>Nom : </b>" + getNom() + "<br>";
		html += "<b>Type de lit : </b>" + getTypeLit() + "<br>";
		html += "<b>Prix de base : </b>" + getPrix() + " $<br>";
		return html;
	}
	
	public String toString()
	{
		return "(" + getIdChambre() + ") " + getNom();
	}
}
