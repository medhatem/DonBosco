package equipeSoccer;

public class GestionEquipeSoccer
{
	private TableJoueurs joueur;
	private TableTerrains terrain;
	private TableFactures facture;
	private TableRencontres rencontre;
	private GestionJoueur gestionJoueur;
	private GestionTerrain gestionTerrain;
	private GestionFacture gestionFacture;
	private GestionRencontre gestionRencontre;

	public GestionEquipeSoccer() throws IFT215Exception
	{
		// Allocation des objets pour le traitement des transactions
		joueur = new TableJoueurs();
		terrain = new TableTerrains();
		facture = new TableFactures();
		rencontre = new TableRencontres();
		setGestionClient(new GestionJoueur(joueur, rencontre));
		setGestionChambre(new GestionTerrain(terrain, rencontre, facture));
		setGestionRencontre(new GestionRencontre(rencontre, joueur, terrain, facture));
	}

	public GestionTerrain getGestionTerrain()
	{
		return gestionTerrain;
	}

	public void setGestionChambre(GestionTerrain gestionTerrain)
	{
		this.gestionTerrain = gestionTerrain;
	}

	public GestionFacture getGestionFacture()
	{
		return gestionFacture;
	}

	public void setGestionFacture(GestionFacture gestionFacture)
	{
		this.gestionFacture = gestionFacture;
	}

	public GestionJoueur getGestionJoueur()
	{
		return gestionJoueur;
	}

	public void setGestionClient(GestionJoueur gestionJoueur)
	{
		this.gestionJoueur = gestionJoueur;
	}

	public GestionRencontre getGestionReservation()
	{
		return gestionRencontre;
	}

	public void setGestionRencontre(GestionRencontre gestionRencontre)
	{
		this.gestionRencontre = gestionRencontre;
	}
}