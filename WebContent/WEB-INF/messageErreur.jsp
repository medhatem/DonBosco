<%@ page import="java.util.*,java.text.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
String listeMessageErreur =(String) session.getAttribute("listeMessageErreur");
String typeErreur=(String) session.getAttribute("TypeErreur");
 if (listeMessageErreur != null)
    {
	    
	    String color="";
	    if(typeErreur=="Erreur" || typeErreur==null)
	    		color="#FF4136";
	    else if(typeErreur=="Succes")		
			color="#33cc33";
	    
%>

    <h5 style=" background-color: <%=color%>
      ;text-align: center;">

      <% String error=listeMessageErreur; %>
<%= error%>
    </h5>

<%
}
%>