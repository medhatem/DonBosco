package equipeSoccer;

import java.util.ArrayList;
import equipeSoccer_Servlet.ListeHtml;

public class GestionFacture
{
	private TableFactures facture;

	public GestionFacture(TableFactures facture) throws IFT215Exception
	{
		this.facture = facture;
	}

	public void ajouter(TupleFacture c) throws IFT215Exception, Exception
	{

		// V�rifie si la facture existe
		if (facture.existe(c.getIdFacture()))
			throw new IFT215Exception("La Commodité existe deja : "
					+ c.getIdFacture());

		// Ajout de la facture
		facture.creer(c);

	}

	public void supprimer(int idFacture) throws IFT215Exception, Exception
	{
		// V�rifie si la facture existe
		if (!facture.existe(idFacture))
			throw new IFT215Exception("La commodité n'existe pas : "
					+ idFacture);

		// suppression de la commodite
		facture.supprimer(facture.getFacture(idFacture));
	}

	public boolean existe(int idCommodite)
	{
		return facture.existe(idCommodite);
	}

	public String afficherFacture(String idFacture)
	{
		return facture.getFacture(Integer.parseInt(idFacture)).toHtml();
	}

	public String listerFactures(String selection)
			throws IFT215Exception, Exception
	{

		ArrayList<TupleFacture> commodites = facture.getFactures();

		// Les titres

		System.out.println("idCommodite Description prix");

		ListeHtml listeHtml = new ListeHtml("Liste des commodites").addTitre("Description").addTitre("prix");

		if (selection != null)
			listeHtml.selectionner(selection);

		for (TupleFacture c : commodites)
		{
			listeHtml.addItem(((Integer) c.getIdFacture()).toString())	// le id
					.addItem(c.getDescription())							// La description
					.addItem(((Integer) c.getPrix()).toString())			// Le type de lit
					.newLigne();

			System.out.print(c.getIdFacture() + " " + c.getDescription() + " "
					+ c.getPrix() + " \n");

		}

		return listeHtml.toHtml();
	}
}
