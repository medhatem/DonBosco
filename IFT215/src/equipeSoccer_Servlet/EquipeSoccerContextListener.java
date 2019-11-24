package equipeSoccer_Servlet;

import javax.servlet.*;
import java.util.*;

/**
 * Classe pour gestion des sessions
 * <P>
 * Syst�me de gestion d'équipe de soccer
 */

public class EquipeSoccerContextListener implements ServletContextListener
{
    public void contextInitialized(ServletContextEvent sce)
    {
        System.out.println("Contexte de l'�quipe de soccer WEB d�marr� : " + sce.getServletContext().getServletContextName());
        System.out.println("Voici les parametres du contexte tels que d�finis dans web.xml");
        Enumeration<String> initParams = sce.getServletContext().getInitParameterNames();
        while (initParams.hasMoreElements())
        {
            String name = (String) initParams.nextElement();
            System.out.println(name + ":" + sce.getServletContext().getInitParameter(name));
        }
    }

    public void contextDestroyed(ServletContextEvent sce)
    {
        System.out.println("Le contexte de l'application Equipe de soccer vient d'�tre d�truit.");
    }
}
