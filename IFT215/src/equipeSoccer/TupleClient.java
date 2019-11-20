package equipeSoccer;

public class TupleClient {
	int idClient;
	String prenom;
	String nom;
	int age;
	
	public TupleClient(int idClient, String prenom, String nom, int age) {
		this.idClient = idClient;
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
	}

	public TupleClient() {
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int date) {
		this.age = date;
	}
	
	public String toHtml() {
		String html = "<H2>Détails du client :</H2>";
		html += "<b>Id : </b>" + getIdClient() + "<br>";
		html += "<b>Nom : </b>" + getNom() + "<br>";
		html += "<b>Prenom : </b>" + getPrenom() + "<br>";
		html += "<b>Âge : </b>" + getAge() + "<br>";
		return html;
	}

	public String toString()
	{
		return "(" + getIdClient() + ") " + getNom() + " " + getPrenom();
	}
}
