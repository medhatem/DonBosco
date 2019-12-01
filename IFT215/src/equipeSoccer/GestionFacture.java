package equipeSoccer;

import java.util.ArrayList;

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



	public ArrayList<TupleFacture>  listerFactures()
			throws IFT215Exception, Exception
	{

		return facture.getFactures();


	}
}
