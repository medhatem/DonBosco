package equipeSoccer_Servlet;

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import equipeSoccer.GestionEquipeSoccer;
import equipeSoccer.IFT287Exception;
import equipeSoccer.TupleJoueur;

/**
 * Classe traitant la requ�te provenant de la page client.jsp
 */

public class Joueur extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Integer etat = (Integer) session.getAttribute("etat");

		if (etat == null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			try
			{

				// Lecture de la commande reçue
				// Commandes possibles :
				// "Afficher" : Affiche le client sélectionnée
				// "Supprimer" : Supprime le client sélectionnée
				// "Reserver..." : Ouvre la page reservation.jsp
				String commande = request.getParameter("bouton");
				String idClientParam = request.getParameter("Liste des clients");

				System.out.println("Commande recue : " + commande + "("
						+ idClientParam + ")");

				int idClient = -1;
				if (idClientParam != null)
				{
					try
					{
						idClient = Integer.parseInt(idClientParam);
					}
					catch (NumberFormatException e)
					{
						throw new IFT287Exception("Format de no Client "
								+ idClientParam + " incorrect.");
					}
				}

				if (commande == null || commande.equals("Afficher"))
				{
					Afficher(request, response, idClient);
				}
				else if (commande.equals("Supprimer"))
				{
					Supprimer(request, response, idClient);
				}
				else if (commande.equals("Reserver..."))
				{
					Reserver(request, response, idClient);
				}
				else if (commande.equals("Creer"))
				{
					Creer(request, response);
				}
				else
				{
					throw new IFT287Exception("Commande inconnue : "
							+ commande);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
			}
			catch (IFT287Exception e)
			{
				List<String> listeMessageErreur = new LinkedList<String>();
				listeMessageErreur.add(e.toString());
				request.setAttribute("listeMessageErreur", listeMessageErreur);
				request.setAttribute("clientEnCours", null);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/client.jsp");
				dispatcher.forward(request, response);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void Supprimer(HttpServletRequest request, HttpServletResponse response, int idClient)
			throws ServletException, IOException, SQLException, IFT287Exception,
			Exception
	{
		HttpSession session = request.getSession();
		GestionEquipeSoccer aubergeUpdate = (GestionEquipeSoccer) session.getAttribute("aubergeUpdate");
		
		if(idClient == -1)
			throw new IFT287Exception("SVP sélectionner un client.");
		
		synchronized (aubergeUpdate)
		{
			aubergeUpdate.getGestionJoueur().supprimer(idClient);
		}

		// transfert de la requ�te � la page JSP pour affichage
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/client.jsp");
		session.setAttribute("clientEnCours", null);
		dispatcher.forward(request, response);

	}

	private void Reserver(HttpServletRequest request, HttpServletResponse response, int idClient)
			throws ServletException, IOException, SQLException, IFT287Exception
	{
		HttpSession session = request.getSession();
		session.setAttribute("clientEnCours", String.valueOf(idClient));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
		dispatcher.forward(request, response);
	}

	private void Creer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, IFT287Exception,
			Exception
	{
		HttpSession session = request.getSession();

		session.setAttribute("clientEnCours", null);

		// lecture des param�tres du formulaire Client.jsp
		String idParam = request.getParameter("id");
		String nomParam = request.getParameter("nom");
		String prenomParam = request.getParameter("prenom");
		String prixParam = request.getParameter("age");

		if (nomParam == null || nomParam.equals(""))
			throw new IFT287Exception("Le nom ne peut être vide.");

		if (prenomParam == null || prenomParam.equals(""))
			throw new IFT287Exception("Le prenom ne peut être vide.");

		int id;
		int age;

		try
		{
			id = Integer.parseInt(idParam);
			age = Integer.parseInt(prixParam);
		}
		catch (NumberFormatException e)
		{
			throw new IFT287Exception("Id / age au format incorrect : "
					+ idParam + " / " + prixParam);
		}

		GestionEquipeSoccer aubergeUpdate = (GestionEquipeSoccer) session.getAttribute("aubergeUpdate");

		synchronized (aubergeUpdate)
		{
			aubergeUpdate.getGestionJoueur().ajouter(new TupleJoueur(id, prenomParam, nomParam, age));
		}

		// On retourne a la page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/client.jsp");
		dispatcher.forward(request, response);
	}

	private void Afficher(HttpServletRequest request, HttpServletResponse response, int idClient)
			throws ServletException, IOException, SQLException, IFT287Exception
	{
		HttpSession session = request.getSession();

		session.setAttribute("clientEnCours", String.valueOf(idClient));

		if (idClient != -1)
		{
			// transfert de la requ�te � la page JSP pour affichage
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/client.jsp");
			session.setAttribute("clientEnCours", String.valueOf(idClient));
			dispatcher.forward(request, response);
		}
		else
		{
			session.setAttribute("clientEnCours", null);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/client.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}
}