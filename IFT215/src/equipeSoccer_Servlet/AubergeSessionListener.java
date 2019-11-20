package equipeSoccer_Servlet;

import javax.servlet.http.*;

import equipeSoccer.GestionAuberge;

import java.sql.*;

/**
 * Classe pour gestion des sessions
 */

public class AubergeSessionListener implements HttpSessionListener
{
    public void sessionCreated(HttpSessionEvent se)
    {
    }

    public void sessionDestroyed(HttpSessionEvent se)
    {
        System.out.println("ASessionListener " + se.getSession().getId());
        
        GestionAuberge aubergeInterrogation = (GestionAuberge)se.getSession().getAttribute("aubergeInterrogation");
        if (aubergeInterrogation != null)
        {
            System.out.println("connexion =" + aubergeInterrogation.getConnexion());
            try
            {
                aubergeInterrogation.fermer();
            }
            catch (SQLException e)
            {
                System.out.println("Impossible de fermer la connexion");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Auberge inaccessible.");
        }
        
        GestionAuberge aubergeUpdate = (GestionAuberge)se.getSession().getAttribute("aubergeUpdate");
        if (aubergeUpdate != null)
        {
            System.out.println("connexion = " + aubergeUpdate.getConnexion());
            try
            {
                aubergeUpdate.fermer();
            }
            catch (SQLException e)
            {
                System.out.println("Impossible de fermer la connexion");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Auberge inaccessible.");
        }
    }
}
