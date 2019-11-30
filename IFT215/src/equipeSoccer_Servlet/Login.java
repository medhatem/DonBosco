package equipeSoccer_Servlet;

import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import equipeSoccer.GestionEquipeSoccer;
import equipeSoccer.IFT215Exception;
import equipeSoccer.TupleJoueur;

/**
 * Classe pour login système de gestion de l'équipe de soccer
 */

public class Login extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		// fermer la session si elle a d�j� �t� ouverte lors d'un appel
		// pr�c�dent
		// survient lorsque l'usager recharge la page login.jsp
		/*if (session.getAttribute("etat") != null)
		{
			// pour d�boggage seulement : afficher no session et information
			System.out.println("GestionEquipeSoccer: session déjà crée; id="
					+ session.getId());
			session.invalidate();
			session = request.getSession();
			System.out.println("GestionEquipeSoccer: session invalid�e");
		}*/

		// lecture des param�tres du formulaire login.jsp
		String nomU = request.getParameter("NomU");
		String motP = request.getParameter("MotP");

		System.out.println(nomU + " " + motP);

		if (nomU != null && motP != null)
		{
			/*
			 * ouvrir une connexion avec la BD et cr�er les gestionnaires et
			 * stocker dans la session. On ouvre une session en lecture
			 * readcommited pour les interrogations seulement et une autre
			 * en mode serialisable, pour les transactions
			 */
			System.out.println("Login: session id=" + session.getId());
			
			GestionEquipeSoccer equipeSoccerInterrogation = (GestionEquipeSoccer) session.getAttribute("equipeSoccerInterrogation");
			
			TupleJoueur j = equipeSoccerInterrogation.getGestionJoueur().login(nomU, motP);
			
			if(j == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
				dispatcher.forward(request, response);
			} else {
				session.setAttribute("etat", new Integer(EquipeSoccerConstantes.CONNECTE));
				session.setAttribute("Joueur", j);
				session.setAttribute("typeUtilisateur", j.JoueurEstDeType());
				
				if(j.JoueurEstDeType()==EquipeSoccerConstantes.SPECTATEUR) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}else if(j.JoueurEstDeType()==EquipeSoccerConstantes.JOUEUR) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ConnecteJoueur.jsp");
					dispatcher.forward(request, response);
					
				}else if(j.JoueurEstDeType()==EquipeSoccerConstantes.ORGANISATEUR) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ConnecteOrganisateur.jsp");
					dispatcher.forward(request, response);
				}else if(j.JoueurEstDeType()==EquipeSoccerConstantes.TRESORIER) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}

				System.out.println("Connecte en tant que : " + ((TupleJoueur)session.getAttribute("Joueur")).getCourriel());
				System.out.println("Connecte en tant que : " + ((TupleJoueur)session.getAttribute("Joueur")).JoueurEstDeType());
			}
		}
	}

	// Dans les formulaires, on utilise la m�thode POST
	// donc, si le servlet est appel� avec la m�thode GET
	// on appelle POST
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}

} // class
