package equipeSoccer;

import java.sql.SQLException;
import java.util.ArrayList;

import equipeSoccer_Servlet.ListeHtml;

public class GestionFacture
{
	private TableFactures facture;
	private Connexion cx;

	public GestionFacture(TableFactures facture) throws IFT287Exception
	{
		this.cx = facture.getConnexion();
		this.facture = facture;
	}

	public void ajouter(TupleFacture c)
			throws SQLException, IFT287Exception, Exception
	{
		try
		{
			// V�rifie si la commodite existe
			if (facture.existe(c.getIdCommodite()))
				throw new IFT287Exception("La Commodité existe deja : "
						+ c.getIdCommodite());

			// Ajout de la commodite
			facture.creer(c);

			// Commit
			cx.commit();
		}
		catch (Exception e)
		{
			cx.rollback();
			throw e;
		}
	}

	public void supprimer(int idCommodite)
			throws SQLException, IFT287Exception, Exception
	{
		try
		{
			// V�rifie si la commodite existe
			if (!facture.existe(idCommodite))
				throw new IFT287Exception("La commodité n'existe pas : "
						+ idCommodite);
			
			// suppression de la commodite
			facture.supprimer(idCommodite);

			// Commit
			cx.commit();
		}
		catch (Exception e)
		{
			cx.rollback();
			throw e;
		}
	}

	public boolean existe(int idCommodite) throws SQLException
	{
		return facture.existe(idCommodite);
	}
	
	public String afficherFacture(int idFacture) throws SQLException
	{
		return facture.getFacture(idFacture).toHtml();
	}

	public String listerFactures(String selection)
			throws SQLException, IFT287Exception, Exception
	{

		ArrayList<TupleFacture> commodites = facture.getFactures();

		// Les titres

		System.out.println("idCommodite Description prix");

		ListeHtml listeHtml = new ListeHtml("Liste des commodites").addTitre("Description").addTitre("prix");

		if (selection != null)
			listeHtml.selectionner(selection);

		for (TupleFacture c : commodites)
		{
			listeHtml.addItem(((Integer) c.getIdCommodite()).toString())	// le id
					.addItem(c.getDescription())							// La description
					.addItem(((Integer) c.getPrix()).toString())			// Le type de lit
					.newLigne();

			System.out.print(c.getIdCommodite() + " " + c.getDescription() + " "
					+ c.getPrix() + " \n");

		}
		cx.commit();

		return listeHtml.toHtml();
	}
}
