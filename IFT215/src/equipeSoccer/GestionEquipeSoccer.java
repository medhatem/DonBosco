package equipeSoccer;

import java.sql.Timestamp;
import java.util.ArrayList;

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
		setGestionJoueur(new GestionJoueur(joueur, rencontre));
		setGestionChambre(new GestionTerrain(terrain, rencontre, facture));
		setGestionRencontre(new GestionRencontre(rencontre, joueur, terrain, facture));
		setGestionFacture(new GestionFacture(facture));
		
		try
		{
			AjoutValeursBidon();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private void AjoutValeursBidon() throws IFT215Exception, Exception
	{
		// Organisateur
		joueur.creer(new TupleJoueur(0, "Marc", "Fortier", "1985-10-12", "mf@dba.com", "abcde", "123 potatoe street", 2));
		
		// Joueurs
		gestionJoueur.ajouter(new TupleJoueur(1, "John", "Doe", "1985-10-12", "jd@dba.com", "abcde", "123 potatoe street", 1));
		gestionJoueur.ajouter(new TupleJoueur(2, "Steve", "Jobs", "1985-10-12", "sj@dba.com", "abcde", "123 potatoe street", 1));
		gestionJoueur.ajouter(new TupleJoueur(3, "Benjamin", "Franklin", "1782-12-5", "bf@dba.com", "abcde", "123 potatoe street", 1));
		gestionJoueur.ajouter(new TupleJoueur(4, "Mr", "T", "1985-8-2", "mt@dba.com", "abcde", "123 potatoe street", 1));
		gestionJoueur.ajouter(new TupleJoueur(5, "Joseph", "Fortier", "2010-07-01", "jf@dba.com", "abcde", "123 potatoe street", 1));
		gestionJoueur.ajouter(new TupleJoueur(6, "Antoine", "Fortier", "2013-04-02", "af@dba.com", "abcde", "123 potatoe street", 1));
		gestionJoueur.ajouter(new TupleJoueur(7, "François", "Archambault", "2001-05-05", "fa@dba.com", "abcde", "123 potatoe street", 1));
		gestionJoueur.ajouter(new TupleJoueur(8, "Jean-Olivier", "Archambault", "2003-12-5", "joa@dba.com", "abcde", "123 potatoe street", 1));
		
		// Spectateurs
		gestionJoueur.ajouter(new TupleJoueur(9, "Amadeus", "Mozart", "1791-2-15", "am@dba.com", "abcde", "123 potatoe street", 0));

		// Trésorier
		gestionJoueur.ajouter(new TupleJoueur(1, "Baltazar", "Picsou", 	"1990-6-28", "bp@dba.com", "abcde", "123 potatoe street", 3));

		// Terrains
		gestionTerrain.ajouter(new TupleTerrain(0, "Parc St-Alphonse"));
		gestionTerrain.ajouter(new TupleTerrain(1, "Parc Jacques-Cartier"));
		gestionTerrain.ajouter(new TupleTerrain(2, "Parc Nault"));
		
		// Factures
		gestionFacture.ajouter(new TupleFacture(0, "Achat d'un nouveau ballon", 	 45.50, new Timestamp(2019, 6, 10, 0, 0, 0, 0)));
		gestionFacture.ajouter(new TupleFacture(1, "Achat des dossards", 		 82.25, new Timestamp(2019, 6, 18, 0, 0, 0, 0)));
		gestionFacture.ajouter(new TupleFacture(2, "Achat de refraîchissements",  18.75, new Timestamp(2019, 6, 25, 0, 0, 0, 0)));
		gestionFacture.ajouter(new TupleFacture(3, "Location du terrain", 		210.95, new Timestamp(2019, 7, 4, 0, 0, 0, 0)));
		
		// Rencontres
		gestionRencontre.ajouter(new TupleRencontre(0, new Timestamp(2019, 6, 10, 0, 0, 0, 0), terrain.getTerrain(0)));
		gestionRencontre.ajouter(new TupleRencontre(1, new Timestamp(2019, 6, 17, 0, 0, 0, 0), terrain.getTerrain(0)));
		gestionRencontre.ajouter(new TupleRencontre(2, new Timestamp(2019, 6, 21, 0, 0, 0, 0), terrain.getTerrain(0)));
		gestionRencontre.ajouter(new TupleRencontre(3, new Timestamp(2019, 6, 28, 0, 0, 0, 0), terrain.getTerrain(0)));
		gestionRencontre.ajouter(new TupleRencontre(4, new Timestamp(2019, 7, 4, 0, 0, 0, 0),  terrain.getTerrain(0)));
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

	public void setGestionJoueur(GestionJoueur gestionJoueur)
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