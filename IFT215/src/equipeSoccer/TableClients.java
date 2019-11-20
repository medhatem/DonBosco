package equipeSoccer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableClients {
    private PreparedStatement stmtExiste;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;
	private PreparedStatement stmtListAll;
	
    private Connexion cx;

    /**
     * Creation d'une instance. Des énoncés SQL pour chaque requête sont
     * précompilés.
     */
    public TableClients(Connexion cx) throws SQLException
    {
        this.cx = cx;
        
        stmtExiste = cx.getConnection().prepareStatement("select idclient, prenom, nom, age from client where idclient = ?");
        
        stmtListAll = cx.getConnection().prepareStatement("select idclient, prenom, nom, age from client");
        
        stmtInsert = cx.getConnection().prepareStatement("insert into client (idclient, prenom, nom, age) values (?,?,?,?)");
        
        stmtDelete = cx.getConnection().prepareStatement("delete from client where idclient = ?");
    }

    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Vérifie si un client existe.
     */
    public boolean existe(int idClient) throws SQLException
    {
        stmtExiste.setInt(1, idClient);
        ResultSet rset = stmtExiste.executeQuery();
        boolean clientExiste = rset.next();
        rset.close();
        return clientExiste;
    }
    

    /**
     * Lecture d'un client.
     */
    public TupleClient getClient(int idClient) throws SQLException
    {
        stmtExiste.setInt(1, idClient);
        ResultSet rset = stmtExiste.executeQuery();
        if (rset.next())
        {
            TupleClient tupleClient = new TupleClient();
            tupleClient.setIdClient(idClient);
            tupleClient.setPrenom(rset.getString(2));
            tupleClient.setNom(rset.getString(3));
            tupleClient.setAge(rset.getInt(4));
            rset.close();
            return tupleClient;
        }
        else
            return null;
    }

    /**
     * Ajout d'un nouveau client dans la base de données.
     */
    public void creer(TupleClient client) throws SQLException
    {
        /* Ajout du client. */
        stmtInsert.setInt(1, client.getIdClient());
        stmtInsert.setString(2, client.getPrenom());
        stmtInsert.setString(3, client.getNom());
        stmtInsert.setInt(4, client.getAge());
        stmtInsert.executeUpdate();
    }
    
    /**
     * Suppression d'un client.
     */
    public int supprimer(int idClient) throws SQLException
    {
        stmtDelete.setInt(1, idClient);
        return stmtDelete.executeUpdate();
    }

	public void afficherClient(int idClient)  throws SQLException {
		TupleClient c = getClient(idClient);
		
		System.out.println("idClient prenom nom age");
		System.out.println(c.getIdClient() + " " + c.getPrenom() + " " + c.getNom() + " " + c.getAge());
	
	}

	public ArrayList<TupleClient> getClients() throws SQLException
	{
		ArrayList<TupleClient> clients = new ArrayList<TupleClient>();
		
		ResultSet rset = stmtListAll.executeQuery();

        while (rset.next())
        {
        	TupleClient tupleClient = new TupleClient();
        	tupleClient.setIdClient(rset.getInt(1));
        	tupleClient.setNom(rset.getString(2));
        	tupleClient.setPrenom(rset.getString(3));
        	tupleClient.setAge(rset.getInt(4));
        	
			clients.add(tupleClient);
		}
        
        return clients;
	}

}
