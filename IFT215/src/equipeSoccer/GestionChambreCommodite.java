package equipeSoccer;

import java.sql.SQLException;
import java.util.ArrayList;

public class GestionChambreCommodite
{
	private TableChambreCommodites chambreCommodite;
	private TableCommodites commodite;
	private TableChambres chambre;

	private Connexion cx;

	public GestionChambreCommodite(TableChambreCommodites chambreCommodite,
			TableCommodites commodite, TableChambres chambre)
			throws IFT287Exception
	{
		this.cx = chambreCommodite.getConnexion();
		this.chambreCommodite = chambreCommodite;
		this.commodite = commodite;
		this.chambre = chambre;
	}

	public void ajouter(TupleChambreCommodite c)
			throws SQLException, IFT287Exception, Exception
	{
		try
		{

			// V�rifier l'existence de la commodite
			if (!commodite.existe(c.getIdCommodite()))
				throw new IFT287Exception("La commodite n'existe pas : "
						+ c.getIdCommodite());

			// .. de la chambre
			if (!chambre.existe(c.getIdChambre()))
				throw new IFT287Exception("La chambre n'existe pas : "
						+ c.getIdChambre());

			// V�rifie si l'association existe d�j�
			if (chambreCommodite.existe(c.getIdChambre(), c.getIdCommodite()))
				throw new IFT287Exception("La chambre " + c.getIdChambre()
						+ " a deja la commodite : " + c.getIdCommodite());

			// Ajout de l'association dans la table
			chambreCommodite.creer(c);

			// Commit
			cx.commit();
		}
		catch (Exception e)
		{
			cx.rollback();
			throw e;
		}
	}

	public void supprimer(int idChambre, int idCommodite)
			throws SQLException, IFT287Exception, Exception
	{
		try
		{
			// V�rifie si l'association existe
			if (!chambreCommodite.existe(idChambre, idCommodite))
				throw new IFT287Exception("L'association n'existe pas (chambre / commodite): "
						+ idChambre + " / " + idCommodite);

			// Ajout de l'association
			chambreCommodite.supprimer(idChambre, idCommodite);

			// Commit
			cx.commit();
		}
		catch (Exception e)
		{
			cx.rollback();
			throw e;
		}
	}

	public String afficherChambreCommodite(String idChambreParam) throws NumberFormatException, SQLException, IFT287Exception
	{
		return afficherChambreCommodite(Integer.parseInt(idChambreParam));
	}

	public String afficherChambreCommodite(int idChambre)
			throws SQLException, IFT287Exception
	{

		if (!chambre.existe(idChambre))
			throw new IFT287Exception("Chambre inexistante : " + idChambre);

		TupleChambre c = chambre.getChambre(idChambre);

		System.out.println("idChambre nom typeLit prixBase");
		System.out.println(c.getIdChambre() + " " + c.getNom() + " "
				+ c.getTypeLit() + " " + c.getPrix());

		System.out.println("idCommodite description prix");

		ArrayList<TupleChambreCommodite> listeChambreCommodite = chambreCommodite.getChambreCommodite(c.getIdChambre());

		String listeCommodites = "<H3>Commodités associées :</H3>";

		if (listeChambreCommodite.size() == 0)
			listeCommodites += "<i>(Aucune commodite associée à cette chambre)</i>";

		else
		{
			for (TupleChambreCommodite r : listeChambreCommodite)
			{
				TupleCommodite com = commodite.getCommodite(r.getIdCommodite());
				System.out.println(com.getIdCommodite() + " "
						+ com.getDescription() + " " + com.getPrix());
				listeCommodites += com.toHtml();
			}
		}

		return c.toHtml() + listeCommodites;
	}

}
