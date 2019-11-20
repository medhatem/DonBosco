package equipeSoccer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableChambres {
    private PreparedStatement stmtExiste;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;
    private Connexion cx;

    /**
     * Creation d'une instance. Des énoncés SQL pour chaque requête sont
     * précompilés.
     */
    public TableChambres(Connexion cx) throws SQLException
    {
        this.cx = cx;
        
        stmtExiste = cx.getConnection().prepareStatement("select idchambre, nom, typelit, prixbase from chambre where idchambre = ?");
        
        stmtInsert = cx.getConnection().prepareStatement("insert into chambre (idchambre, nom, typelit, prixbase) values (?,?,?,?)");
        
        stmtDelete = cx.getConnection().prepareStatement("delete from chambre where idchambre = ?");
    
    }

    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Vérifie si une chambre existe.
     */
    public boolean existe(int idChambre) throws SQLException
    {
        stmtExiste.setInt(1, idChambre);
        ResultSet rset = stmtExiste.executeQuery();
        boolean clientExiste = rset.next();
        rset.close();
        return clientExiste;
    }
    

    /**
     * Lecture d'une chambre
     */
    public TupleChambre getChambre(int idChambre) throws SQLException
    {
        stmtExiste.setInt(1, idChambre);
        ResultSet rset = stmtExiste.executeQuery();
        if (rset.next())
        {
            TupleChambre tupleChambre = new TupleChambre();
            tupleChambre.setIdChambre(idChambre);
            tupleChambre.setNom(rset.getString(2));
            tupleChambre.setTypeLit(rset.getString(3));
            tupleChambre.setPrix(rset.getInt(4));
            
            rset.close();
            return tupleChambre;
        }
        else
            return null;
    }

    /**
     * Ajout d'une nouvelle chambre dans la base de donnees
     */
    public void creer(TupleChambre chambre) throws SQLException
    {
        stmtInsert.setInt(1, chambre.getIdChambre());
        stmtInsert.setString(2, chambre.getNom());
        stmtInsert.setString(3, chambre.getTypeLit());
        stmtInsert.setFloat(4, chambre.getPrix());
        stmtInsert.executeUpdate();
    }
    
    /**
     * Suppression d'une chambre
     */
    public int supprimer(int idChambre) throws SQLException
    {
        stmtDelete.setInt(1, idChambre);
        return stmtDelete.executeUpdate();
    }

	public String afficher(int idChambre) throws SQLException
	{
		return getChambre(idChambre).toHtml();
	}

}
