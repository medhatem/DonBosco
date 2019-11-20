package equipeSoccer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableChambreCommodites {
    private PreparedStatement stmtExiste;
    private PreparedStatement stmtListeChambre;
    private PreparedStatement stmtListeCommodite;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;
    
    private Connexion cx;

    /**
     * Creation d'une instance. Des énoncés SQL pour chaque requête sont
     * précompilés.
     */
    public TableChambreCommodites(Connexion cx) throws SQLException
    {
        this.cx = cx;
        
        stmtExiste = cx.getConnection().prepareStatement("SELECT chambre, commodite FROM chambrecommodite WHERE chambre = ? AND commodite = ?");
        
        stmtListeChambre = cx.getConnection().prepareStatement("SELECT chambre, commodite from chambrecommodite WHERE chambre = ?");
        
        stmtListeCommodite = cx.getConnection().prepareStatement("SELECT chambre, commodite from chambrecommodite WHERE commodite = ?");
        
        stmtInsert = cx.getConnection().prepareStatement("INSERT INTO chambrecommodite (chambre, commodite) VALUES (?,?)");
        
        stmtDelete = cx.getConnection().prepareStatement("DELETE FROM chambrecommodite WHERE chambre = ? AND commodite = ?");

    }

    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Vérifie si une association chambre commodite existe.
     */
    public boolean existe(int idChambre, int idCommodite) throws SQLException
    {
    	stmtExiste.setInt(1, idChambre);
    	stmtExiste.setInt(2, idCommodite);
        
        ResultSet rset = stmtExiste.executeQuery();
        boolean chambreCommoditeExiste = rset.next();
        rset.close();
        return chambreCommoditeExiste;
    }
    

    /**
     * Lecture d'une liste de commodites
     */
    public ArrayList<TupleChambreCommodite> getChambreCommodite(int idChambre) throws SQLException
    {
		ArrayList<TupleChambreCommodite> listeCommodites = new ArrayList<TupleChambreCommodite>();
		
		stmtListeChambre.setInt(1,  idChambre);
		ResultSet rset = stmtListeChambre.executeQuery();
		
		while(rset.next()) {
			TupleChambreCommodite t = new TupleChambreCommodite();
			
			t.setIdChambre(rset.getInt(1));
			t.setIdCommodite(rset.getInt(2));
			
			listeCommodites.add(t);
		}
		
		rset.close();
		return listeCommodites;
    }
    
    public ArrayList<TupleChambreCommodite> getCommoditeChambre(int idCommodite) throws SQLException
    {
		ArrayList<TupleChambreCommodite> listeChambres = new ArrayList<TupleChambreCommodite>();
		
		stmtListeCommodite.setInt(1,  idCommodite);
		ResultSet rset = stmtListeCommodite.executeQuery();
		
		while(rset.next()) {
			TupleChambreCommodite t = new TupleChambreCommodite();
			
			t.setIdChambre(rset.getInt(1));
			t.setIdCommodite(rset.getInt(2));
			
			listeChambres.add(t);
		}
		
		rset.close();
		return listeChambres;
    }

    /**
     * Ajout d'une nouvelle association chambre commodite dans la base de donnees
     */
    public void creer(TupleChambreCommodite chambreCommodite) throws SQLException
    {
        /* Ajout du client. */
        stmtInsert.setInt(1, chambreCommodite.getIdChambre());
        stmtInsert.setInt(2, chambreCommodite.getIdCommodite());
        stmtInsert.executeUpdate();
    }

	public int supprimer(int idChambre, int idCommodite) throws SQLException {
        stmtDelete.setInt(1, idChambre);
        stmtDelete.setInt(2, idCommodite);
        return stmtDelete.executeUpdate();
	}
}
