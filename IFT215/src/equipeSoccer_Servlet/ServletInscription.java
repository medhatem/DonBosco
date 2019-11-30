package equipeSoccer_Servlet;

import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import equipeSoccer.GestionEquipeSoccer;
import equipeSoccer.IFT215Exception;
import equipeSoccer.TupleJoueur;
import equipeSoccer.TupleRencontre;

/**
 * Classe traitant la requ�te provenant de la page Inscription.jsp
 */

public class ServletInscription extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletInscription()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
		dispatcher.forward(request, response);
	}

	private void Annuler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
		dispatcher.forward(request, response);
	}

	private void Creer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IFT215Exception, Exception
	{
		HttpSession session = request.getSession();

		// lecture des param�tres du formulaire inscription.jsp
		String nom = request.getParameter("Nom");
		String prenom = request.getParameter("Prenom");
		String dateN = request.getParameter("DateN");
		String adresse = request.getParameter("Adrs");
		String type = request.getParameter("optionsRadios");
		String motP = request.getParameter("motP");

		if (nom == null || prenom == null || dateN == null || adresse == null
				|| type == null || motP == null)
			throw new IFT215Exception("paramètres manquants.");

		int typeInt = 0;

		if (type == "Joueur")
			typeInt = 1;
		else
			typeInt = 0; 

		GestionEquipeSoccer equipeSoccerUpdate = (GestionEquipeSoccer) session.getAttribute("equipeSoccerUpdate");

		synchronized (equipeSoccerUpdate)
		{
			if(equipeSoccerUpdate.getGestionJoueur().ajouter(new TupleJoueur(-1, prenom, nom, dateN, nom, motP, adresse, typeInt)))
				session.setAttribute("notification", 1);
		}

		// On retourne a la page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();

		try
		{
			// Lecture de la commande reçue
			// Commandes possibles :
			// "Enregistrer" : Supprime la chambre sélectionnée
			// "Annuler" : Ouvre la page index.jsp
			String commande = request.getParameter("bouton");

			// lecture des param�tres du formulaire chambrecommodite.jsp
			String nom = request.getParameter("Nom");
			String prenom = request.getParameter("Prenom");
			String dateN = request.getParameter("DateN");
			String adresse = request.getParameter("Adrs");
			String type = request.getParameter("optionsRadios");
			String motP = request.getParameter("motP");
			

			// conversion du parametre en entier
			Timestamp dateNaissance;

			if (nom != null && prenom != null && dateN != null
					&& adresse != null && type != null)
			{
				try
				{
					dateNaissance = new Timestamp(1985, 07, 28, 0, 0, 0, 0);
				}
				catch (NumberFormatException e)
				{
					throw new IFT215Exception("Format de date de naissance "
							+ dateN + " incorrect.");
				}
			}

			System.out.println("Commande recue : " + commande + "(" + nom + ", "
					+ prenom + ", " + dateN + ", " + adresse + ", "  + motP + ", " + type
					+ ")");

			if (commande == null)
			{
				Annuler(request, response);
			}
			else if (commande.equals("Annuler"))
			{
				Annuler(request, response);
			}
			else if (commande.equals("Enregistrer"))
			{
				Creer(request, response);
			}
			else
			{
				throw new IFT215Exception("Commande inconnue : " + commande);
			}
		}
		catch (IFT215Exception e)
		{
			List<String> listeMessageErreur = new LinkedList<String>();
			listeMessageErreur.add(e.toString());
			request.setAttribute("listeMessageErreur", listeMessageErreur);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
			dispatcher.forward(request, response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}