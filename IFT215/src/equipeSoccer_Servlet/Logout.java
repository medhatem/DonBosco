package equipeSoccer_Servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Classe pour logout syst�me de gestion de l'équipe de soccer
 */

public class Logout extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // invalider la session pour lib�rer les ressources associ�es � la
        // session
        request.getSession().invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
