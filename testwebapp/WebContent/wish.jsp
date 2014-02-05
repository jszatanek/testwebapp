<%@page language="java" %>
<%@page import="java.security.Principal" %>
<%@page import="java.lang.String" %>
	 
<% // Get principal from the request.
	Principal principal = request.getUserPrincipal();
	String userName = principal.getName(); %>
<html>

<head>
<title>Volvo IT Application hosting Welcome</title>
</head>

<body bgcolor="">

<p align="center"><font size="5" color="#000080">Welcome to Application Hosting<%= userName %> to<br>
Jboss Status Application</font></p>
<p align="center"><font color="green" size="3">Successfully connected using Java Security</font></p>
<p align="center"><font color="" size="3"><a href="status">Click here to</a> execute the PostgreSQL Status Servlet.</font></p>
<br/ >
<p align="center"><font color="" size="3"><a href="/WMQ_IVT" target='new'>Click here to</a> execute the IBM MQ Status page</font></p>
</body>
</html>


