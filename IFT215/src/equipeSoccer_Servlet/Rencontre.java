package equipeSoccer_Servlet;

import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import equipeSoccer.GestionEquipeSoccer;
import equipeSoccer.IFT215Exception;
import equipeSoccer.TupleRencontre;

/**
 * Classe traitant la requ�te provenant de la page reservation.jsp
 */

public class Rencontre extends HttpServlet
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
				String idClientParam = request.getParameter("Liste des commodites");
				String idReservationParam = request.getParameter("Liste des reservations");

				// conversion du parametre en entier
				int idChambre = -1; // inialisation requise par compilateur Java
				int idClient = -1;
				int idReservation = -1;

				if (idChambreParam != null && idClientParam != null)
				{
					try
					{
						idChambre = Integer.parseInt(idChambreParam);
						idClient = Integer.parseInt(idClientParam);
					}
					catch (NumberFormatException e)
					{
						throw new IFT215Exception("Format de no chambre / client "
								+ idChambreParam + " / " + idClientParam + " incorrect.");
					}
				}
				
				if (idReservationParam != null)
				{
					try
					{
						idReservation = Integer.parseInt(idReservationParam);
					}
					catch (NumberFormatException e)
					{
						throw new IFT215Exception("Format de no reservation "
								+ idReservationParam + " incorrect.");
					}
				}

				System.out.println("Commande recue : " + commande + "("
						+ idChambre + ", " + idClient + ", " + idReservation
						+ ")");

				if (commande == null)
				{
					Afficher(request, response);
				}
				else if (commande.equals("Canceller"))
				{
					Supprimer(request, response, idReservation);
				}
				else if (commande.equals("Reserver"))
				{
					Creer(request, response);
				}
				else
				{
					throw new IFT215Exception("Commande inconnue : "
							+ commande);
				}
			}
			catch (IFT215Exception e)
			{
				List<String> listeMessageErreur = new LinkedList<String>();
				listeMessageErreur.add(e.toString());
				request.setAttribute("listeMessageErreur", listeMessageErreur);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
				dispatcher.forward(request, response);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void Supprimer(HttpServletRequest request, HttpServletResponse response, int idReservation)
			throws ServletException, IOException, IFT215Exception,
			Exception
	{
		HttpSession session = request.getSession();
		GestionEquipeSoccer equipeSoccerUpdate = (GestionEquipeSoccer) session.getAttribute("equipeSoccerUpdate");

		if(idReservation == -1)
			throw new IFT215Exception("SVP sélectionner une réservation.");
		
		synchronized (equipeSoccerUpdate)
		{
			equipeSoccerUpdate.getGestionReservation().supprimer(idReservation);
		}

		// transfert de la requ�te � la page JSP pour affichage
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
		dispatcher.forward(request, response);
	}

	private void Creer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IFT215Exception,
			Exception
	{
		HttpSession session = request.getSession();

		// lecture des param�tres du formulaire reservation.jsp
		String idChambreParam = request.getParameter("Liste des chambres");
		String idClientParam = request.getParameter("Liste des clients");
		String dateDebutParam = request.getParameter("dateDebut");
		String dateFinParam = request.getParameter("dateFin");

		if (idChambreParam == null || idClientParam == null)
			throw new IFT215Exception("Chambre ou client manquant : "
					+ idChambreParam + " / " + idClientParam);

		int idChambre;
		int idClient;
		Timestamp date;

		try
		{
			idChambre = Integer.parseInt(idChambreParam);
			idClient = Integer.parseInt(idClientParam);
			date = Timestamp.valueOf(dateFinParam);
		}
		catch (NumberFormatException e)
		{
			throw new IFT215Exception("Chambre ou client manquant : "
					+ idChambreParam + " / " + idClientParam);
		}

		System.out.println(idChambre + " " + idClient + " " + date);

		GestionEquipeSoccer equipeSoccerUpdate = (GestionEquipeSoccer) session.getAttribute("equipeSoccerUpdate");

		synchronized (equipeSoccerUpdate)
		{
			equipeSoccerUpdate.getGestionReservation().ajouter(new TupleRencontre(idClient, date, null));
		}

		
		// On retourne a la page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
		dispatcher.forward(request, response);
	}

	private void Afficher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IFT215Exception
	{
		HttpSession session = request.getSession();

		session.setAttribute("clientEnCours", null);
		session.setAttribute("chambreEnCours", null);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
		dispatcher.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}
}