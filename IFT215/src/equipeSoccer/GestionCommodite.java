package equipeSoccer;

import java.sql.SQLException;
import java.util.ArrayList;

import equipeSoccer_Servlet.ListeHtml;

public class GestionCommodite
{
	private TableCommodites commodite;
	private TableChambreCommodites chambreCommodite;
	private Connexion cx;

	public GestionCommodite(TableCommodites commodite, TableChambreCommodites chambreCommodite) throws IFT287Exception
	{
		this.cx = commodite.getConnexion();
		this.commodite = commodite;
		this.chambreCommodite = chambreCommodite; 
	}

	public void ajouter(TupleCommodite c)
			throws SQLException, IFT287Exception, Exception
	{
		try
		{
			// V�rifie si la commodite existe
			if (commodite.existe(c.getIdCommodite()))
				throw new IFT287Exception("La Commodité existe deja : "
						+ c.getIdCommodite());

			// Ajout de la commodite
			commodite.creer(c);

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
			if (!commodite.existe(idCommodite))
				throw new IFT287Exception("La commodité n'existe pas : "
						+ idCommodite);
			
			// Vérifie si la commodite est utilisée
			if(chambreCommodite.getCommoditeChambre(idCommodite).size() > 0)
				throw new IFT287Exception("La commodité est inclue dans au moins une chambre : SVP supprimer les associations d'abord.");

			// suppression de la commodite
			commodite.supprimer(idCommodite);

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
		return commodite.existe(idCommodite);
	}
	
	public String afficherCommodite(String idCommoditeParam)
	{
		try {
			return afficherCommodite(Integer.parseInt(idCommoditeParam));
		}
		catch (Exception e)
		{
			return "<i>(Commodite invalide!)</i>";
		}
	}

	public String afficherCommodite(int idCommodite) throws SQLException
	{
		return commodite.getCommodite(idCommodite).toHtml();
	}

	public String listerCommodites(String selection)
			throws SQLException, IFT287Exception, Exception
	{

		ArrayList<TupleCommodite> commodites = commodite.getCommodites();

		// Les titres

		System.out.println("idCommodite Description prix");

		ListeHtml listeHtml = new ListeHtml("Liste des commodites").addTitre("Description").addTitre("prix");

		if (selection != null)
			listeHtml.selectionner(selection);

		for (TupleCommodite c : commodites)
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
