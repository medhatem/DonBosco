package equipeSoccer_Servlet;

import javax.servlet.http.*;

import equipeSoccer.GestionEquipeSoccer;

import java.sql.*;

/**
 * Classe pour gestion des sessions
 */

public class EquipeSoccerSessionListener implements HttpSessionListener
{
    public void sessionCreated(HttpSessionEvent se)
    {
    }

    public void sessionDestroyed(HttpSessionEvent se)
    {
        System.out.println("ASessionListener " + se.getSession().getId());
        
        GestionEquipeSoccer aubergeInterrogation = (GestionEquipeSoccer)se.getSession().getAttribute("aubergeInterrogation");
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
        
        GestionEquipeSoccer aubergeUpdate = (GestionEquipeSoccer)se.getSession().getAttribute("aubergeUpdate");
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
