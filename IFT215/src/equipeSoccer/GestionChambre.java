package equipeSoccer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import equipeSoccer_Servlet.ListeHtml;

public class GestionChambre {
	private TableChambres chambre;
	private TableReservations reservation;
	private TableChambreCommodites chambreCommodite;
	private TableCommodites commodite;
	
	private Connexion cx;
	private PreparedStatement stmtListeChambreLibres;
	private PreparedStatement stmtListeChambres;

	public GestionChambre(TableChambres chambre, TableReservations reservation, TableChambreCommodites chambreCommodite, TableCommodites commodite) throws IFT287Exception, SQLException {
		this.cx = chambre.getConnexion();
		this.chambre = chambre;
		this.reservation = reservation;
		this.chambreCommodite = chambreCommodite; 
		this.commodite = commodite;
		
		// Semble bizarre de mettre une transaction ici, mais c'est inspire de l'exemple stmtLivresRetard de BibliothequeJDBC
		stmtListeChambreLibres = cx.getConnection().prepareStatement("SELECT c.idchambre, c.nom, c.typelit, c.prixbase, COALESCE(SUM(co.prix), 0)\r\n" + 
				"FROM chambre c\r\n" + 
				"	LEFT OUTER JOIN chambrecommodite cc ON c.idchambre = cc.chambre\r\n" + 
				"	LEFT OUTER JOIN commodites co ON co.idcommodite = cc.commodite\r\n" + 
				"	LEFT OUTER JOIN reservation r ON c.idchambre = r.chambre\r\n" + 
				"WHERE r.datedebut IS NULL OR NOT (CURRENT_DATE BETWEEN r.datedebut AND r.datefin)\r\n" + 
				"GROUP BY c.idchambre order by c.nom ASC");
		
		stmtListeChambres = cx.getConnection().prepareStatement("SELECT c.idchambre, c.nom, c.typelit, c.prixbase, COALESCE(SUM(co.prix), 0)\r\n" + 
				"FROM chambre c\r\n" + 
				"	LEFT OUTER JOIN chambrecommodite cc ON c.idchambre = cc.chambre\r\n" + 
				"	LEFT OUTER JOIN commodites co ON co.idcommodite = cc.commodite\r\n" + 
				"GROUP BY c.idchambre order by c.nom ASC");
	}

	public void ajouter(TupleChambre c) throws SQLException, IFT287Exception, Exception {
		try {
			// V�rifie si la chambre existe d�j�
			if (chambre.existe(c.getIdChambre()))
				throw new IFT287Exception("Chambre existe deja�: " + c.getIdChambre());

			// Ajout du chambre dans la table
			chambre.creer(c);

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	public void supprimer(int idChambre) throws SQLException, IFT287Exception, Exception {
		try {
			// V�rifie si la chambre existe
			if (!chambre.existe(idChambre))
				throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
			
			// Vérifie si le client a des reservations
			for(TupleReservation r : reservation.listeReservationsChambre(idChambre)){
				if (!reservation.reservationEstPerimee(r))
					throw new IFT287Exception("La chambre a au moins une réservation : (" + idChambre + ") " + chambre.getChambre(idChambre).getNom() + "\nSVP supprimer les réservations d'abord.");
				else {
					throw new IFT287Exception("Le client a au moins une réservation (périmée) : (" + idChambre + ") " + chambre.getChambre(idChambre).getNom() + "\nSVP supprimer les réservations d'abord.");
				}
			}
			
			// Vérifie si la chambre a des commodités
			if(chambreCommodite.getChambreCommodite(idChambre).size() > 0)
				throw new IFT287Exception("La chambre a au moins une commodité : SVP supprimer les associations d'abord.");
			
			// supression de la chambre
			chambre.supprimer(idChambre);

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	public String listerChambres(String selection) throws SQLException, IFT287Exception, Exception {

        ResultSet rset = stmtListeChambres.executeQuery();
        
        // Les titres
        System.out.println("Selection : " + selection);
        
        System.out.println("idChambre Nom TypeLit prixBase prixCommodites");
        
        ListeHtml listeHtml = new ListeHtml("Liste des chambres")
        .addTitre("Nom")
        .addTitre("Type de lit")
        .addTitre("Prix de base")
        .addTitre("Prix des commodités");
        
        if(selection != null)
        	listeHtml.selectionner(selection);
        
        while (rset.next())
        {
        	listeHtml.addItem(rset.getString(1))	// le id
        	.addItem(rset.getString(2))				// Le nom
        	.addItem(rset.getString(3))				// Le type de lit
        	.addItem(rset.getString(4) + " $")		// Le prix de base
        	.addItem(rset.getString(5) + " $")		// Le prix des commodites
        	.newLigne();
        	
            System.out.print(rset.getInt(1) + " " + rset.getString(2) + " " + rset.getString(3) + " " + rset.getInt(4)+ " \n");
 
        }
        cx.commit();
        
        return listeHtml.toHtml();
	}
	
	public String listerChambresCommodites(String selection) throws SQLException, IFT287Exception, Exception {

        ResultSet rset = stmtListeChambres.executeQuery();
        
        // Les titres
        System.out.println("Selection : " + selection);
        
        System.out.println("idChambre Nom TypeLit prixBase prixCommodites");
        
        ListeHtml listeHtml = new ListeHtml("Liste des chambres")
        .addTitre("Nom")
        .addTitre("Commodités");
        
        if(selection != null)
        	listeHtml.selectionner(selection);
        
        while (rset.next())
        {
        	listeHtml.addItem(rset.getString(1))	// le id
        	.addItem(rset.getString(2));			// Le nom

        	String listeComm = "(";
        	for(TupleChambreCommodite cc : chambreCommodite.getChambreCommodite(rset.getInt(1))){
        		listeComm += commodite.getCommodite(cc.getIdCommodite()).getDescription() + ", ";
        	}
        	listeComm += ")";
        	
        	listeHtml.addItem(listeComm)	// La liste des commodites
        	.newLigne();
        }
        cx.commit();
        
        return listeHtml.toHtml();
	}

	public boolean existe(int idChambre) throws SQLException {
		return chambre.existe(idChambre);
	}
	
	public String afficher(String idChambreParam) {
		try
		{
			int idChambre = Integer.parseInt(idChambreParam);
			return chambre.afficher(idChambre);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "Chambre invalide: " + idChambreParam;
		}
	}
}
