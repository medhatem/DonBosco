package equipeSoccer_Servlet;

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import equipeSoccer.GestionEquipeSoccer;
import equipeSoccer.IFT287Exception;
import equipeSoccer.TupleFacture;

/**
 * Classe traitant la requ�te provenant de la page commodite.jsp
 */

public class Facture extends HttpServlet
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
				// "Afficher" : Affiche la commodite sélectionnée
				// "Supprimer" : Supprime la commodite sélectionnée
				// "Reserver..." : Ouvre la page chambrecommodite.jsp
				String commande = request.getParameter("bouton");

				String idCommoditeParam = request.getParameter("Liste des commodites");

				System.out.println("Commande recue : " + commande + "("
						+ idCommoditeParam + ")");

				int idCommodite = -1;
				if (idCommoditeParam != null)
				{
					try
					{
						idCommodite = Integer.parseInt(idCommoditeParam);
					}
					catch (NumberFormatException e)
					{
						throw new IFT287Exception("Format de no Client "
								+ idCommoditeParam + " incorrect.");
					}
				}

				if (commande == null || commande.equals("Afficher"))
				{
					Afficher(request, response, idCommodite);
				}
				else if (commande.equals("Supprimer"))
				{
					Supprimer(request, response, idCommodite);
				}
				else if (commande.equals("Inclure..."))
				{
					Inclure(request, response, idCommodite);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/commodite.jsp");
				dispatcher.forward(request, response);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void Supprimer(HttpServletRequest request, HttpServletResponse response, int idCommodite)
			throws ServletException, IOException, SQLException, IFT287Exception,
			Exception
	{
		HttpSession session = request.getSession();
		GestionEquipeSoccer aubergeUpdate = (GestionEquipeSoccer) session.getAttribute("aubergeUpdate");
		
		if(idCommodite == -1)
			throw new IFT287Exception("SVP sélectionner une commodite.");
		
		synchronized (aubergeUpdate)
		{
			aubergeUpdate.getGestionFacture().supprimer(idCommodite);
		}

		// transfert de la requ�te � la page JSP pour affichage
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/commodite.jsp");
		session.setAttribute("commoditeEnCours", null);
		dispatcher.forward(request, response);
	}

	private void Inclure(HttpServletRequest request, HttpServletResponse response, int idCommodite)
			throws ServletException, IOException, SQLException, IFT287Exception
	{
		HttpSession session = request.getSession();
		session.setAttribute("commoditeEnCours", String.valueOf(idCommodite));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambrecommodite.jsp");
		dispatcher.forward(request, response);
	}

	private void Creer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, IFT287Exception,
			Exception
	{
		HttpSession session = request.getSession();

		// lecture des param�tres du formulaire Commodite.jsp
		String idParam = request.getParameter("id");
		String nomParam = request.getParameter("description");
		String prixParam = request.getParameter("prix");

		if (nomParam == null || nomParam.equals(""))
			throw new IFT287Exception("La description de la commodité ne peut être vide.");

		int id;
		int prix;

		try
		{
			id = Integer.parseInt(idParam);
			prix = Integer.parseInt(prixParam);
		}
		catch (NumberFormatException e)
		{
			throw new IFT287Exception("Id / prix au format incorrect : "
					+ idParam + " / " + prixParam);
		}

		GestionEquipeSoccer aubergeUpdate = (GestionEquipeSoccer) session.getAttribute("aubergeUpdate");
		synchronized (aubergeUpdate)
		{
			aubergeUpdate.getGestionFacture().ajouter(new TupleFacture(id, nomParam, prix));;
		}

		// On retourne a la page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/commodite.jsp");
		dispatcher.forward(request, response);
	}

	private void Afficher(HttpServletRequest request, HttpServletResponse response, int idCommodite)
			throws ServletException, IOException, SQLException, IFT287Exception
	{
		HttpSession session = request.getSession();

		if (idCommodite != -1)
		{
			// transfert de la requete a la page JSP pour affichage
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/commodite.jsp");
			session.setAttribute("commoditeEnCours", String.valueOf(idCommodite));
			dispatcher.forward(request, response);
		}
		else
		{
			session.setAttribute("commoditeEnCours", null);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/commodite.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}
}