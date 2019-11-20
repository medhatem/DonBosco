package equipeSoccer;

import java.sql.SQLException;
import java.util.ArrayList;

import equipeSoccer_Servlet.AubergeConstantes;

public class GestionAuberge
{
    private Connexion cx;
    private TableClients client;
    private TableChambres chambre;
    private TableCommodites commodite;
    private TableChambreCommodites chambreCommodite;
    private TableReservations reservation;
    private GestionClient gestionClient;
    private GestionChambre gestionChambre;
    private GestionCommodite gestionCommodite;
    private GestionChambreCommodite gestionChambreCommodite;
    private GestionReservation gestionReservation;

    /**
     * Ouvre une connexion avec la BD relationnelle et alloue les gestionnaires
     * de transactions et de tables.
     * 
     * @param serveur SQL
     * @param bd nom de la base de donnees
     * @param user user id pour établir une connexion avec le serveur SQL
     * @param password mot de passe pour le user id
     */
    public GestionAuberge(String serveur, String bd, String userId, String motDePasse)
            throws IFT287Exception, SQLException
    {
    	// Création de la connexion
    	cx = new Connexion(serveur, bd, userId, motDePasse);
    	
        // Allocation des objets pour le traitement des transactions
        client = new TableClients(cx);
        chambre = new TableChambres(cx);
        commodite = new TableCommodites(cx);
        chambreCommodite = new TableChambreCommodites(cx);
        reservation = new TableReservations(cx);
        setGestionClient(new GestionClient(client, reservation));
        setGestionChambre(new GestionChambre(chambre, reservation, chambreCommodite, commodite));
        setGestionCommodite(new GestionCommodite(commodite, chambreCommodite));
        setGestionReservation(new GestionReservation(reservation, client, chambre, chambreCommodite, commodite));
        setGestionChambreCommodite(new GestionChambreCommodite(chambreCommodite, commodite, chambre));
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

	public GestionChambre getGestionChambre() {
		return gestionChambre;
	}

	public void setGestionChambre(GestionChambre gestionChambre) {
		this.gestionChambre = gestionChambre;
	}

	public GestionCommodite getGestionCommodite() {
		return gestionCommodite;
	}

	public void setGestionCommodite(GestionCommodite gestionCommodite) {
		this.gestionCommodite = gestionCommodite;
	}

	public GestionChambreCommodite getGestionChambreCommodite() {
		return gestionChambreCommodite;
	}

	public void setGestionChambreCommodite(GestionChambreCommodite gestionChambreCommodite) {
		this.gestionChambreCommodite = gestionChambreCommodite;
	}

	public GestionClient getGestionClient() {
		return gestionClient;
	}

	public void setGestionClient(GestionClient gestionClient) {
		this.gestionClient = gestionClient;
	}

	public GestionReservation getGestionReservation() {
		return gestionReservation;
	}

	public void setGestionReservation(GestionReservation gestionReservation) {
		this.gestionReservation = gestionReservation;
	}
	
    public String testListe() {
        ArrayList<String> titres = new ArrayList<String>();
        
        titres.add("Nom");
        titres.add("Type de lit");
        titres.add("Prix");
        
        ArrayList<String> obj1 = new ArrayList<String>();
        
        obj1.add("id1");
        obj1.add("Chambre 1");
        obj1.add("King");
        obj1.add("102.95$");
        obj1.add("102.95$");
        
        
        ArrayList<String> obj2 = new ArrayList<String>();
        
        obj2.add("id2");
        obj2.add("Chambre 2");
        obj2.add("Queen");
        obj2.add("152.95$");
        
        ArrayList<ArrayList<String>> listeItems = new ArrayList<ArrayList<String>>();
        
        listeItems.add(obj1);
        listeItems.add(obj2);
        
        return AubergeConstantes.listToHtml(listeItems, "Titre de la liste", titres);
    }
}