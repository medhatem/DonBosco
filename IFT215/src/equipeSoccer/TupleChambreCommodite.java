package equipeSoccer;

public class TupleChambreCommodite {
	int idCommodite;
	int idChambre;
	
	public TupleChambreCommodite(int idChambre,int idCommodite) {
		super();
		this.idCommodite = idCommodite;
		this.idChambre = idChambre;
	}
	
	public TupleChambreCommodite()
	{}

	public int getIdCommodite() {
		return idCommodite;
	}

	public void setIdCommodite(int idCommodite) {
		this.idCommodite = idCommodite;
	}

	public int getIdChambre() {
		return idChambre;
	}

	public void setIdChambre(int idChambre) {
		this.idChambre = idChambre;
	}
}
