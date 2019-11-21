package equipeSoccer;

import equipeSoccer_Servlet.ListeHtml;

public class GestionTerrain
{
	private TableTerrains terrain;
	private TableRencontres rencontre;
	private TableFactures facture;

	public GestionTerrain(TableTerrains terrain, TableRencontres rencontre,
			TableFactures facture) throws IFT215Exception
	{
		this.terrain = terrain;
		this.rencontre = rencontre;
		this.facture = facture;
	}

	public void ajouter(TupleTerrain c)
			throws IFT215Exception, Exception
	{

		// V�rifie si la chambre existe d�j�
		if (terrain.existe(c.getIdTerrain()))
			throw new IFT215Exception("Terrain existe deja�: "
					+ c.getIdTerrain());

		// Ajout du chambre dans la table
		terrain.creer(c);

	}

	public void supprimer(int idTerrain) throws IFT215Exception, Exception
	{
		if (!terrain.existe(idTerrain))
			throw new IFT215Exception("Le terrain n'existe pas : " + idTerrain);

		// supression du terrain
		terrain.supprimer(terrain.getTerrain(idTerrain));
	}

	public String listerTerrains(String selection)
			throws IFT215Exception, Exception
	{
		// Les titres
		System.out.println("Selection : " + selection);

		System.out.println("idChambre Nom TypeLit prixBase prixCommodites");

		ListeHtml listeHtml = new ListeHtml("Liste des terrains").addTitre("Nom").addTitre("Type de lit").addTitre("Prix de base").addTitre("Prix des commodités");

		return listeHtml.toHtml();
	}

	public boolean existe(int id)
	{
		return terrain.existe(id);
	}

	public String afficher(String idTerrainParam)
	{
		int idTerrain = Integer.parseInt(idTerrainParam);
		return terrain.afficher(terrain.getTerrain(idTerrain));
	}
}
