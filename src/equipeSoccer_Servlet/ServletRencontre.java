package equipeSoccer_Servlet;



import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import equipeSoccer.GestionEquipeSoccer;
import equipeSoccer.IFT215Exception;
import equipeSoccer.TupleJoueur;

/**
 * Classe traitant la requ�te provenant de la page client.jsp
 */

public class ServletRencontre extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Integer etat = (Integer) session.getAttribute("etat");
		session.setAttribute("listeMessageErreur", null);
		System.out.println("etat: " + etat);
				
		if (etat == null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
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

				
				Afficher(request, response);
				
				/*
				int idClient = -1;
				if (idClientParam != null)
				{
					try
					{
						idClient = Integer.parseInt(idClientParam);
					}
					catch (NumberFormatException e)
					{
						throw new IFT215Exception("Format de no Client "
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
					throw new IFT215Exception("Commande inconnue : "
							+ commande);
				}*/
			}
			catch (IFT215Exception e)
			{
				String MessageErreur = e.toString();
				request.setAttribute("listeMessageErreur", MessageErreur);
				request.setAttribute("clientEnCours", null);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void Supprimer(HttpServletRequest request, HttpServletResponse response, int idJoueur)
			throws ServletException, IOException, IFT215Exception,
			Exception
	{
		HttpSession session = request.getSession();
		GestionEquipeSoccer equipeSoccerUpdate = (GestionEquipeSoccer) session.getAttribute("equipeSoccerUpdate");
		
		if(idJoueur == -1)
			throw new IFT215Exception("SVP sélectionner un joueur.");
		
		synchronized (equipeSoccerUpdate)
		{
			equipeSoccerUpdate.getGestionJoueur().supprimer(idJoueur);
		}

		// transfert de la requ�te � la page JSP pour affichage
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ConnecteOrganisateur.jsp");
		session.setAttribute("clientEnCours", null);
		dispatcher.forward(request, response);

	}


	private void Creer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IFT215Exception,
			Exception
	{
		HttpSession session = request.getSession();
		session.setAttribute("listeMessageErreur", null);
		session.setAttribute("clientEnCours", null);

		// lecture des param�tres du formulaire Client.jsp
		String idParam = request.getParameter("id");
		String nomParam = request.getParameter("nom");
		String prenomParam = request.getParameter("prenom");
		String prixParam = request.getParameter("age");

		if (nomParam == null || nomParam.equals(""))
			throw new IFT215Exception("Le nom ne peut être vide.");

		if (prenomParam == null || prenomParam.equals(""))
			throw new IFT215Exception("Le prenom ne peut être vide.");

		int id;
		int age;

		try
		{
			id = Integer.parseInt(idParam);
			age = Integer.parseInt(prixParam);
		}
		catch (NumberFormatException e)
		{
			throw new IFT215Exception("Id / age au format incorrect : "
					+ idParam + " / " + prixParam);
		}

		GestionEquipeSoccer equipeSoccerUpdate = (GestionEquipeSoccer) session.getAttribute("equipeSoccerUpdate");

		synchronized (equipeSoccerUpdate)
		{

			equipeSoccerUpdate.getGestionJoueur().ajouter(new TupleJoueur(id, prenomParam, nomParam, null, null, null, null, 1));

		}

		// On retourne a la page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ConnecteOrganisateur.jsp");
		dispatcher.forward(request, response);
	}

	private void Afficher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IFT215Exception
	{
		HttpSession session = request.getSession();


		Integer type= (Integer)session.getAttribute("typeUtilisateur");
		
		System.out.println("Commande recue : " +  type+ " ");
		if (type == -1)
		{
			System.out.println("Commande recue : " +  "-1"+ " ");
			// transfert de la requ�te � la page JSP pour affichage
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");

			dispatcher.forward(request, response);
		}
		else if(type== 0)
		{
			System.out.println("Commande recue : " +  "0"+ " ");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
		else if(type== 1)
		{
			System.out.println("Commande recue : " +  "1"+ " ");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ConnecteJoueur.jsp");
			dispatcher.forward(request, response);
		}
		else if(type== 2)
		{
			System.out.println("Commande recue : " +  "2"+ " ");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ConnecteOrganisateur.jsp");
			dispatcher.forward(request, response);
		}
		else if(type== 3)
		{
			System.out.println("Commande recue : " +  "3"+ " ");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
		else {
			System.out.println("Commande recue : " + " rien"+ ")");
		}
	}
	
	
	
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		HttpSession session = request.getSession();
		session.setAttribute("listeMessageErreur", null);
		doGet(request, response);
	}
}