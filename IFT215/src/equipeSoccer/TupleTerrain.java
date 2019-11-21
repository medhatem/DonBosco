package equipeSoccer;

public class TupleTerrain {
	int idTerrain;
	String nom;
	
	public TupleTerrain(int idTerrain, String nom) {
		this.idTerrain = idTerrain;
		this.nom = nom;
	}

	public TupleTerrain() {
	}

	public int getIdTerrain() {
		return idTerrain;
	}

	public void setIdTerrain(int idChambre) {
		this.idTerrain = idChambre;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String toHtml() {
		String html = "<H2>Détails de la chambre :</H2>";
		html += "<b>Nom : </b>" + getNom() + "<br>";
		return html;
	}
	
	public String toString()
	{
		return "(" + getIdTerrain() + ") " + getNom();
	}
}
