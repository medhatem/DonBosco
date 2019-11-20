package equipeSoccer_Servlet;

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import equipeSoccer.GestionAuberge;
import equipeSoccer.IFT287Exception;
import equipeSoccer.TupleChambreCommodite;

/**
 * Classe traitant la requ�te provenant de la page chambrecommodite.jsp
 */

public class ChambreCommodite extends HttpServlet
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

				// lecture des param�tres du formulaire chambrecommodite.jsp
				String idChambreParam = request.getParameter("Liste des chambres");
				String idCommoditeParam = request.getParameter("Liste des commodites");

				// conversion du parametre en entier
				int idChambre = -1; // inialisation requise par compilateur Java
				int idCommodite = -1;
				if (idChambreParam != null && idCommoditeParam != null)
				{
					try
					{
						idChambre = Integer.parseInt(idChambreParam);
						idCommodite = Integer.parseInt(idCommoditeParam);
					}
					catch (NumberFormatException e)
					{
						throw new IFT287Exception("Format de no chambre / commodite "
								+ idChambreParam + " / " + idCommoditeParam
								+ " incorrect.");
					}
				}

				System.out.println("Commande recue : " + commande + "("
						+ idChambre + ", " + idCommodite + ")");

				if (commande == null)
				{
					Afficher(request, response);
				}
				else if (commande.equals("Inclure"))
				{
					Inclure(request, response, idChambre, idCommodite);
				}
				else if (commande.equals("Retirer"))
				{
					Retirer(request, response, idChambre, idCommodite);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambrecommodite.jsp");
				dispatcher.forward(request, response);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void Retirer(HttpServletRequest request, HttpServletResponse response, int idChambre, int idCommodite)
			throws ServletException, IOException, SQLException, IFT287Exception,
			Exception
	{
		HttpSession session = request.getSession();
		GestionAuberge aubergeUpdate = (GestionAuberge) session.getAttribute("aubergeUpdate");

		if (idChambre == -1 || idCommodite == -1)
			throw new IFT287Exception("Information manquante pour supprimmer l'association.");

		synchronized (aubergeUpdate)
		{
			aubergeUpdate.getGestionChambreCommodite().supprimer(idChambre, idCommodite);
		}

		// transfert de la requ�te � la page JSP pour affichage
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambrecommodite.jsp");
		dispatcher.forward(request, response);
	}

	private void Inclure(HttpServletRequest request, HttpServletResponse response, int idChambre, int idCommodite)
			throws ServletException, IOException, SQLException, IFT287Exception,
			Exception
	{
		HttpSession session = request.getSession();

		if (idChambre == -1 || idCommodite == -1)
			throw new IFT287Exception("Information manquante pour ajouter l'association.");

		GestionAuberge aubergeUpdate = (GestionAuberge) session.getAttribute("aubergeUpdate");

		synchronized (aubergeUpdate)
		{
			aubergeUpdate.getGestionChambreCommodite().ajouter(new TupleChambreCommodite(idChambre, idCommodite));
		}

		// On retourne a la page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambrecommodite.jsp");
		dispatcher.forward(request, response);
	}

	private void Afficher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, IFT287Exception
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambrecommodite.jsp");
		dispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}
}