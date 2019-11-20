package equipeSoccer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableFactures {
	private PreparedStatement stmtExiste;
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtDelete;
	private PreparedStatement stmtListAll;
	
	private Connexion cx;

	/**
	 * Creation d'une instance. Des énoncés SQL pour chaque requête sont
	 * précompilés.
	 */
	public TableFactures(Connexion cx) throws SQLException {
		this.cx = cx;

		stmtExiste = cx.getConnection()
				.prepareStatement("select idcommodite, description, prix from commodites where idcommodite = ?");
		
		stmtListAll= cx.getConnection()
				.prepareStatement("select idcommodite, description, prix from commodites");

		stmtInsert = cx.getConnection()
				.prepareStatement("insert into commodites (idcommodite, description, prix) values (?,?,?)");

		stmtDelete = cx.getConnection().prepareStatement("delete from commodites where idcommodite = ?");
	}

	/**
	 * Retourner la connexion associée.
	 */
	public Connexion getConnexion() {
		return cx;
	}

	/**
	 * Vérifie si une commodite existe.
	 */
	public boolean existe(int idCommodite) throws SQLException {
		stmtExiste.setInt(1, idCommodite);
		ResultSet rset = stmtExiste.executeQuery();
		boolean commoditeExiste = rset.next();
		rset.close();
		return commoditeExiste;
	}

	/**
	 * Lecture d'une commodite
	 */
	public TupleFacture getFacture(int idCommodite) throws SQLException {
		stmtExiste.setInt(1, idCommodite);
		ResultSet rset = stmtExiste.executeQuery();
		if (rset.next()) {
			TupleFacture tupleCommodite = new TupleFacture();
			tupleCommodite.setIdCommodite(idCommodite);
			tupleCommodite.setDescription(rset.getString(2));
			tupleCommodite.setPrix(rset.getInt(3));

			rset.close();
			return tupleCommodite;
		} else
			return null;
	}

	/**
	 * Ajout d'une nouvelle commodite dans la base de donnees
	 */
	public void creer(TupleFacture commodite) throws SQLException {
		/* Ajout du client. */
		stmtInsert.setInt(1, commodite.getIdCommodite());
		stmtInsert.setString(2, commodite.getDescription());
		stmtInsert.setFloat(3, commodite.getPrix());
		stmtInsert.executeUpdate();
	}

	/**
	 * Suppression d'une commodite
	 */
	public int supprimer(int idCommodite) throws SQLException {
		stmtDelete.setInt(1, idCommodite);
		return stmtDelete.executeUpdate();
	}

	public ArrayList<TupleFacture> getFactures() throws SQLException
	{
		ArrayList<TupleFacture> commodites = new ArrayList<TupleFacture>();
		
		ResultSet rset = stmtListAll.executeQuery();

        while (rset.next())
        {
			TupleFacture tupleCommodite = new TupleFacture();
			tupleCommodite.setIdCommodite(rset.getInt(1));
			tupleCommodite.setDescription(rset.getString(2));
			tupleCommodite.setPrix(rset.getInt(3));
			commodites.add(tupleCommodite);
		}
        
        return commodites;
	}
}
