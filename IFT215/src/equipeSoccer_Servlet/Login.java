package equipeSoccer_Servlet;

import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import equipeSoccer.GestionEquipeSoccer;
import equipeSoccer.IFT215Exception;

/**
 * Classe pour login système de gestion de l'équipe de soccer
 */

public class Login extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            HttpSession session = request.getSession();
            // fermer la session si elle a d�j� �t� ouverte lors d'un appel
            // pr�c�dent
            // survient lorsque l'usager recharge la page login.jsp
            if (session.getAttribute("etat") != null)
            {
                // pour d�boggage seulement : afficher no session et information
                System.out.println("GestionEquipeSoccer: session déjà crée; id=" + session.getId());
                session.invalidate();
                session = request.getSession();
                System.out.println("GestionEquipeSoccer: session invalid�e");
            }

            // lecture des param�tres du formulaire login.jsp
            String userId = request.getParameter("userIdBD");
            String motDePasse = request.getParameter("motDePasseBD");
            String serveur = request.getParameter("serveur");
            String bd = request.getParameter("bd");

            if (serveur != null)
            {
                /*
                 * ouvrir une connexion avec la BD et cr�er les gestionnaires et
                 * stocker dans la session. On ouvre une session en lecture
                 * readcommited pour les interrogations seulement et une autre
                 * en mode serialisable, pour les transactions
                 */
                System.out.println("Login: session id=" + session.getId());
                GestionEquipeSoccer equipeSoccerInterrogation = new GestionEquipeSoccer();
                session.setAttribute("equipeSoccerInterrogation", equipeSoccerInterrogation);
                GestionEquipeSoccer equipeSoccerUpdate = new GestionEquipeSoccer();
                session.setAttribute("equipeSoccerUpdate", equipeSoccerUpdate);

                // afficher le menu membre en appelant la page
                // selectionMembre.jsp
                // tous les JSP sont dans /WEB-INF/
                // ils ne peuvent pas �tre appel�s directement par l'utilisateur
                // seulement par un autre JSP ou un servlet

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambre.jsp");
                dispatcher.forward(request, response);
                session.setAttribute("etat", new Integer(EquipeSoccerConstantes.CONNECTE));
            }
        }
        catch (IFT215Exception e)
        {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    // Dans les formulaires, on utilise la m�thode POST
    // donc, si le servlet est appel� avec la m�thode GET
    // on appelle POST
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

} // class
