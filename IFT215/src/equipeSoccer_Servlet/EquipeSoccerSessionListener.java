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

		GestionEquipeSoccer equipeSoccerInterrogation = (GestionEquipeSoccer) se.getSession().getAttribute("equipeSoccerInterrogation");
		if (equipeSoccerInterrogation != null)
		{
			// TODO Quoi ferme pour fermer
		}
		else
		{
			System.out.println("Équipe de soccer inaccessible.");
		}

		GestionEquipeSoccer equipeSoccerUpdate = (GestionEquipeSoccer) se.getSession().getAttribute("equipeSoccerUpdate");
		if (equipeSoccerUpdate != null)
		{

			// TODO Quoi ferme pour fermer

		}
		else
		{
			System.out.println("Équipe soccer inaccessible.");
		}
	}
}
