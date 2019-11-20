package equipeSoccer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import equipeSoccer_Servlet.ListeHtml;

public class GestionTerrain {
	private TableTerrains terrain;
	private TableRencontres rencontre;
	private TableFactures facture;
	
	private Connexion cx;
	private PreparedStatement stmtListeChambreLibres;
	private PreparedStatement stmtListeChambres;

	public GestionTerrain(TableTerrains terrain, TableRencontres rencontre, TableFactures facture) throws IFT287Exception, SQLException {
		this.cx = terrain.getConnexion();
		this.terrain = terrain;
		this.rencontre = rencontre;
		this.facture = facture;
		
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

	public void ajouter(TupleTerrain c) throws SQLException, IFT287Exception, Exception {
		try {
			// V�rifie si la chambre existe d�j�
			if (terrain.existe(c.getIdChambre()))
				throw new IFT287Exception("Chambre existe deja�: " + c.getIdChambre());

			// Ajout du chambre dans la table
			terrain.creer(c);

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
			if (!terrain.existe(idChambre))
				throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
			

			
			// supression de la chambre
			terrain.supprimer(idChambre);

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	public String listerTerrains(String selection) throws SQLException, IFT287Exception, Exception {

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
	
	public boolean existe(int idChambre) throws SQLException {
		return terrain.existe(idChambre);
	}
	
	public String afficher(String idChambreParam) {
		try
		{
			int idChambre = Integer.parseInt(idChambreParam);
			return terrain.afficher(idChambre);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "Chambre invalide: " + idChambreParam;
		}
	}
}
