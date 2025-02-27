package equipeSoccer;

import java.sql.Timestamp;

import equipeSoccer_Servlet.EquipeSoccerConstantes;

public class TupleJoueur {
	int idJoueur;
	String prenom;
	String nom;
	String DateNaissance;
	String courriel;
	String motDePasse;
	String adresse;
	int type; // 0 = spectateur, 1 = joueur, 2 = organisateur, 3=tresorier

	public TupleJoueur(int idJoueur, String prenom, String nom,
			String dateNaissance, String courriel, String motDePasse,
			String adresse, int type)
	{
		super();
		this.idJoueur = idJoueur;
		this.prenom = prenom;
		this.nom = nom;
		this.DateNaissance = dateNaissance;
		this.courriel = courriel;
		this.motDePasse = motDePasse;
		this.adresse = adresse;
		this.type = type;
	}
	
	public TupleJoueur()
	{}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int getIdJoueur()
	{
		return idJoueur;
	}

	public void setIdJoueur(int idJoueur)
	{
		this.idJoueur = idJoueur;
	}

	public String getPrenom()
	{
		return prenom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getDateNaissance()
	{
		return DateNaissance;
	}

	public void setDateNaissance(String dateNaissance)
	{
		DateNaissance = dateNaissance;
	}

	public String getCourriel()
	{
		return courriel;
	}

	public void setCourriel(String courriel)
	{
		this.courriel = courriel;
	}

	public String getMotDePasse()
	{
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse)
	{
		this.motDePasse = motDePasse;
	}

	public String getAdresse()
	{
		return adresse;
	}

	public void setAdresse(String adresse)
	{
		this.adresse = adresse;
	}
	



	public String toHtml() {
		String html = "<H2>Détails du client :</H2>";
		html += "<b>Id : </b>" + getIdJoueur() + "<br>";
		html += "<b>Nom : </b>" + getNom() + "<br>";
		html += "<b>Prenom : </b>" + getPrenom() + "<br>";
		html += "<b>Courriel : </b>" + getCourriel() + "<br>";
		html += "<b>Adresse : </b>" + getAdresse() + "<br>";
		html += "<b>Mot de passe : </b>" + getMotDePasse() + "<br>";
		html += "<b>type : </b>" + getType() + "<br>";
		return html;
	}

	public String toString()
	{
		return "(" + getIdJoueur() + ") " + getNom() + " " + getPrenom();
	}
	
	
	
	public int JoueurEstDeType() {
		if(this.type == 0) {
			return EquipeSoccerConstantes.SPECTATEUR;
		}else if(this.type == 1) {
			return EquipeSoccerConstantes.JOUEUR;
		}else if(this.type == 2) {
			return EquipeSoccerConstantes.ORGANISATEUR;
		}else if(this.type == 3) {
			return EquipeSoccerConstantes.TRESORIER;
		}
		
		//code d'erreur= -1
		return -1;
	}
}
