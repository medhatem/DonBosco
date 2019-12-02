package equipeSoccer_Servlet;

import java.util.ArrayList;

public class ListeHtml {
	
	// J'ai fait ceci ...
	//  ... ENSUITE, ... 
	// J'ai lu comment faire un table directement en JSP.
	// Mais ... ça marche.

	private String titre;
	ArrayList<String> titres;
	ArrayList<String> ligneEnCours;
	ArrayList<ArrayList<String>> listeItems;
	String selection;
	boolean bordure;

	public ListeHtml(String titreListe) {
		this.titre = titreListe;
		titres = new ArrayList<String>();
		listeItems = new ArrayList<ArrayList<String>>();
		ligneEnCours = new ArrayList<String>();
		bordure = false;
	}

	public boolean isBordure()
	{
		return bordure;
	}

	public void setBordure(boolean bordure)
	{
		this.bordure = bordure;
	}

	public ListeHtml addTitre(String titre) {
		titres.add(titre);
		return this;
	}

	public ListeHtml addItem(String item) {
		ligneEnCours.add(item);
		return this;
	}

	public ListeHtml newLigne() {
		listeItems.add(ligneEnCours);
		ligneEnCours = new ArrayList<String>();
		return this;
	}

	// Bâtit la liste en HMTL avec des boutons radio
	public String toHtml() {

		if (listeItems == null || titres == null)
			return "(Liste invalide)<BR>";

		if (listeItems.size() == 0)
			return "(Aucun élément dans la liste " + titre + ")<BR>";

		// Doit avoir le bon nombre de colonnes + un id
		if (listeItems.get(0).size() - 1 != titres.size())
			return "(Liste invalide)<BR>";

		// En-tête
		String listeHtml = "<TABLE" + avecBordure() + "><THEAD><TR><TH>\n";

		// Les titres
		for (String titre : titres) {
			listeHtml += "<TH>" + titre + "<BR>\n";
		}

		// Les lignes
		listeHtml += "</TR></THEAD><TBODY><CENTER>\n";

		for (ArrayList<String> item : listeItems) {
			// Possède au moins un id
			listeHtml += "<TR>" + "<TD><INPUT type=\"radio\" name=\"" + titre + "\" value=\"" + item.get(0) + "\""
					+ estSelectionne(item.get(0)) + ">\n";
			for (int i = 0; i < item.size() - 1; ++i) {
				// Pour chaque titre, on doit avoir une valeur
				listeHtml += "<TD><CENTER>" + item.get(i + 1) + "</CENTER>\n";
			}
		}

		// Fin
		listeHtml += "</TBODY></TABLE>\n";

		return listeHtml;
	}

	private String avecBordure()
	{
		if(bordure)
			return " border=\"1\"";
		return "";
	}

	public void selectionner(String selection) {
		this.selection = selection;
	}

	// Sert a ajouter le parametre "checked" si une chambre est deja selectionnee
	private String estSelectionne(String id) {
		if (selection == null)
			return "";

		if (Integer.parseInt(id) == Integer.parseInt(selection)) {
			return " CHECKED=\"checked\" ";
		} else {
			return "";
		}
	}
}
