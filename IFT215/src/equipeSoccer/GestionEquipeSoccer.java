package equipeSoccer;

import java.sql.SQLException;
import java.util.ArrayList;

import equipeSoccer_Servlet.EquipeSoccerConstantes;

public class GestionEquipeSoccer
{
    private Connexion cx;
    private TableJoueurs joueur;
    private TableTerrains terrain;
    private TableFactures facture;
    private TableRencontres rencontre;
    private GestionJoueur gestionJoueur;
    private GestionTerrain gestionTerrain;
    private GestionFacture gestionFacture;
    private GestionRencontre gestionRencontre;

    /**
     * Ouvre une connexion avec la BD relationnelle et alloue les gestionnaires
     * de transactions et de tables.
     * 
     * @param serveur SQL
     * @param bd nom de la base de donnees
     * @param user user id pour établir une connexion avec le serveur SQL
     * @param password mot de passe pour le user id
     */
    public GestionEquipeSoccer(String serveur, String bd, String userId, String motDePasse)
            throws IFT287Exception, SQLException
    {
    	// Création de la connexion
    	cx = new Connexion(serveur, bd, userId, motDePasse);
    	
        // Allocation des objets pour le traitement des transactions
        joueur = new TableJoueurs(cx);
        terrain = new TableTerrains(cx);
        facture = new TableFactures(cx);
        rencontre = new TableRencontres(cx);
        setGestionClient(new GestionJoueur(joueur, rencontre));
        setGestionChambre(new GestionTerrain(terrain, rencontre, facture));
        setGestionRencontre(new GestionRencontre(rencontre, joueur, terrain, facture));
    }

	public Connexion getConnexion() {
		return cx;
	}

	public void setConnexion(Connexion cx) {
		this.cx = cx;
	}

	public void fermer() throws SQLException
    {
        // Fermeture de la connexion
        cx.fermer();
    }

	public GestionTerrain getGestionTerrain() {
		return gestionTerrain;
	}

	public void setGestionChambre(GestionTerrain gestionTerrain) {
		this.gestionTerrain = gestionTerrain;
	}

	public GestionFacture getGestionFacture() {
		return gestionFacture;
	}

	public void setGestionFacture(GestionFacture gestionFacture) {
		this.gestionFacture = gestionFacture;
	}

	public GestionJoueur getGestionJoueur() {
		return gestionJoueur;
	}

	public void setGestionClient(GestionJoueur gestionJoueur) {
		this.gestionJoueur = gestionJoueur;
	}

	public GestionRencontre getGestionReservation() {
		return gestionRencontre;
	}

	public void setGestionRencontre(GestionRencontre gestionRencontre) {
		this.gestionRencontre = gestionRencontre;
	}
}