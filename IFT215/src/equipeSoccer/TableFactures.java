package equipeSoccer;

import java.util.ArrayList;

public class TableFactures {
	ArrayList<TupleFacture> factures;

    public TableFactures()
    {
    	factures = new ArrayList<TupleFacture>();
    }

	public boolean existe(int idCommodite) {
		return false;
	}

	public TupleFacture getFacture(int id) {
		for(TupleFacture t : factures) {
			if(t.getIdFacture() == id) {
				return t;
			}
		}
		return null;
	}

	public void creer(TupleFacture facture) {
		factures.add(facture);
	}

	public void supprimer(TupleFacture facture) {
		factures.remove(facture);
	}

	public ArrayList<TupleFacture> getFactures()
	{
		return factures;
	}
}
