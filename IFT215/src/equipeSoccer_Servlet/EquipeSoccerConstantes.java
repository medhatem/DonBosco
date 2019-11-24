package equipeSoccer_Servlet;

import java.util.ArrayList;

public class EquipeSoccerConstantes {
	public final static int CONNECTE = 0;
	public final static int MEMBRE_SELECTIONNE = 1;
	
	// Type de joueur
	public final static int SPECTATEUR = 0;
	public final static int JOUEUR = 1;
	public final static int ORGANISATEUR = 2;
	public final static int TRESORIER = 3;
	
	// Utilitaire pour formatter une liste
	public static String listToHtml(ArrayList<ArrayList<String>> listeItems, String nom, ArrayList<String> titres) {
		
		if(listeItems == null || titres == null)
			return "(Liste invalide)<BR>";
		
		if(listeItems.size() == 0)
			return "(Aucun élément dans la liste " + nom + ")<BR>";
		
		// Doit avoir le bon nombre de colonnes + un id
		if(listeItems.get(0).size()-1 != titres.size())
			return "(Liste invalide)<BR>";
		
		// En-tête
		String listeHtml = "<TABLE><THEAD><TR><TH>\n";
		
		// Les titres
		for(String titre : titres) {
			listeHtml += "<TH>" + titre + "<BR>\n";
		}
		
		// Les lignes
		listeHtml += "</TR></THEAD><TBODY><CENTER>\n";
		
		for(ArrayList<String> item : listeItems) {
			// Possède au moins un id
			listeHtml += "<TR>" + 
					"<TD><INPUT type=\"radio\" name=\"" + nom + "\" value=\""+ item.get(0) + "\">\n";
			for(int i=0; i<item.size()-1; ++i) {
				// Pour chaque titre, on doit avoir une valeur
				listeHtml += "<TD><CENTER>" + item.get(i+1) + "</CENTER>\n";
			}
		}
		
		// Fin
		listeHtml += "</TBODY></TABLE>\n";
		
		return listeHtml;
	}
}
