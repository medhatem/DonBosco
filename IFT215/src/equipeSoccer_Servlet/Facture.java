package equipeSoccer_Servlet;

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import equipeSoccer.GestionEquipeSoccer;
import equipeSoccer.IFT215Exception;
import equipeSoccer.TupleFacture;

/**
 * Classe traitant la requ�te provenant de la page facture.jsp
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
				// "Afficher" : Affiche la facture sélectionnée
				// "Supprimer" : Supprime la facture sélectionnée
				// "Reserver..." : Ouvre la page chambrecommodite.jsp
				String commande = request.getParameter("bouton");

				String idFactureParam = request.getParameter("Liste des factures");

				System.out.println("Commande recue : " + commande + "("
						+ idFactureParam + ")");

				int idFacture = -1;
				if (idFactureParam != null)
				{
					try
					{
						idFacture = Integer.parseInt(idFactureParam);
					}
					catch (NumberFormatException e)
					{
						throw new IFT215Exception("Format de no Facture "
								+ idFactureParam + " incorrect.");
					}
				}

				if (commande == null || commande.equals("Afficher"))
				{
					Afficher(request, response, idFacture);
				}
				else if (commande.equals("Supprimer"))
				{
					Supprimer(request, response, idFacture);
				}
				else if (commande.equals("Inclure..."))
				{
					Inclure(request, response, idFacture);
				}
				else if (commande.equals("Creer"))
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/facture.jsp");
				dispatcher.forward(request, response);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void Supprimer(HttpServletRequest request, HttpServletResponse response, int idFacture)
			throws ServletException, IOException, IFT215Exception,
			Exception
	{
		HttpSession session = request.getSession();
		GestionEquipeSoccer equipeSoccerUpdate = (GestionEquipeSoccer) session.getAttribute("equipeSoccerUpdate");
		
		if(idFacture == -1)
			throw new IFT215Exception("SVP sélectionner une facture.");
		
		synchronized (equipeSoccerUpdate)
		{
			equipeSoccerUpdate.getGestionFacture().supprimer(idFacture);
		}

		// transfert de la requ�te � la page JSP pour affichage
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/facture.jsp");
		session.setAttribute("factureEnCours", null);
		dispatcher.forward(request, response);
	}

	private void Inclure(HttpServletRequest request, HttpServletResponse response, int idFacture)
			throws ServletException, IOException,IFT215Exception
	{
		HttpSession session = request.getSession();
		session.setAttribute("factureEnCours", String.valueOf(idFacture));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambrecommodite.jsp");
		dispatcher.forward(request, response);
	}

	private void Creer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IFT215Exception,
			Exception
	{
		HttpSession session = request.getSession();

		// lecture des param�tres du formulaire facture.jsp
		String idParam = request.getParameter("id");
		String nomParam = request.getParameter("description");
		String prixParam = request.getParameter("prix");

		if (nomParam == null || nomParam.equals(""))
			throw new IFT215Exception("La description de la facture ne peut être vide.");

		int id;
		int prix;

		try
		{
			id = Integer.parseInt(idParam);
			prix = Integer.parseInt(prixParam);
		}
		catch (NumberFormatException e)
		{
			throw new IFT215Exception("Id / prix au format incorrect : "
					+ idParam + " / " + prixParam);
		}

		GestionEquipeSoccer equipeSoccerUpdate = (GestionEquipeSoccer) session.getAttribute("equipeSoccerUpdate");
		synchronized (equipeSoccerUpdate)
		{
			equipeSoccerUpdate.getGestionFacture().ajouter(new TupleFacture(id, nomParam, prix, null));;
		}

		// On retourne a la page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/facture.jsp");
		dispatcher.forward(request, response);
	}

	private void Afficher(HttpServletRequest request, HttpServletResponse response, int idFacture)
			throws ServletException, IOException, IFT215Exception
	{
		HttpSession session = request.getSession();

		if (idFacture != -1)
		{
			// transfert de la requete a la page JSP pour affichage
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/facture.jsp");
			session.setAttribute("factureEnCours", String.valueOf(idFacture));
			dispatcher.forward(request, response);
		}
		else
		{
			session.setAttribute("factureEnCours", null);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/facture.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}
}