package equipeSoccer_Servlet;

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import equipeSoccer.GestionEquipeSoccer;
import equipeSoccer.IFT287Exception;
import equipeSoccer.TupleTerrain;

/**
 * Classe traitant la requete provenant de la page chambre.jsp
 */

public class Terrain extends HttpServlet
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
				// "Afficher" : Affiche la chambre sélectionnée
				// "Supprimer" : Supprime la chambre sélectionnée
				// "Reserver..." : Ouvre la page reservation.jsp
				String commande = request.getParameter("bouton");
				String idChambreParam = request.getParameter("Liste des chambres");
				int idChambre = -1;

				System.out.println("Commande recue : " + commande + "("
						+ idChambreParam + ")");

				if (idChambreParam != null)
				{
					try
					{
						idChambre = Integer.parseInt(idChambreParam);
					}
					catch (NumberFormatException e)
					{
						throw new IFT287Exception("Format de no Chambre "
								+ idChambreParam + " incorrect.");
					}
				}

				if (commande == null || commande.equals("Afficher"))
				{
					Afficher(request, response, idChambre);
				}
				else if (commande.equals("Supprimer"))
				{
					Supprimer(request, response, idChambre);
				}
				else if (commande.equals("Reserver..."))
				{
					Reserver(request, response, idChambre);
				}
				else if (commande.equals("Creer"))
				{
					Creer(request, response);
				}
				else if (commande.equals("Commodites..."))
				{
					Commodites(request, response, idChambre);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambre.jsp");
				dispatcher.forward(request, response);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void Commodites(HttpServletRequest request, HttpServletResponse response, int idChambre)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		session.setAttribute("chambreEnCours", String.valueOf(idChambre));

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambrecommodite.jsp");
		dispatcher.forward(request, response);
	}

	private void Supprimer(HttpServletRequest request, HttpServletResponse response, int idChambre)
			throws ServletException, IOException, SQLException, IFT287Exception,
			Exception
	{
		HttpSession session = request.getSession();
		GestionEquipeSoccer aubergeUpdate = (GestionEquipeSoccer) session.getAttribute("aubergeUpdate");
		
		if(idChambre == -1)
			throw new IFT287Exception("SVP sélectionner une chambre.");
		
		synchronized (aubergeUpdate)
		{
			aubergeUpdate.getGestionTerrain().supprimer(idChambre);
		}

		// transfert de la requ�te � la page JSP pour affichage
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambre.jsp");
		session.setAttribute("chambreEnCours", null);
		dispatcher.forward(request, response);
	}

	private void Reserver(HttpServletRequest request, HttpServletResponse response, int idChambre)
			throws ServletException, IOException, SQLException, IFT287Exception
	{
		HttpSession session = request.getSession();

		session.setAttribute("chambreEnCours", String.valueOf(idChambre));

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
		dispatcher.forward(request, response);
	}

	private void Creer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, IFT287Exception,
			Exception
	{
		HttpSession session = request.getSession();

		String idParam = request.getParameter("id");
		String nomParam = request.getParameter("nom");
		String typeLitParam = request.getParameter("typeLit");
		String prixParam = request.getParameter("prix");

		if (nomParam == null || nomParam.equals(""))
			throw new IFT287Exception("Le nom de la chambre ne peut être vide.");

		if (typeLitParam == null || typeLitParam.equals(""))
			throw new IFT287Exception("Le type de lit ne peut être vide.");

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
			aubergeUpdate.getGestionTerrain().ajouter(new TupleTerrain(id, nomParam, typeLitParam, prix));;
		}

		// On retourne a la page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambre.jsp");
		session.setAttribute("chambreEnCours", null);
		dispatcher.forward(request, response);
	}

	private void Afficher(HttpServletRequest request, HttpServletResponse response, int idChambre)
			throws ServletException, IOException, SQLException, IFT287Exception
	{
		HttpSession session = request.getSession();

		if (idChambre != -1)
		{
			// v�rifier existence du membre
			GestionEquipeSoccer aubergeInterrogation = (GestionEquipeSoccer) session.getAttribute("aubergeInterrogation");
			if (!aubergeInterrogation.getGestionTerrain().existe(idChambre))
				throw new IFT287Exception("La chambre n'existe pas : "
						+ idChambre);

			// transfert de la requ�te � la page JSP pour affichage
			session.setAttribute("chambreEnCours", String.valueOf(idChambre));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambre.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			session.setAttribute("chambreEnCours", null);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambre.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}
}
