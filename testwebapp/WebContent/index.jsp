<%@page language="java" %>
<%@page import="java.security.Principal" %>
<%@page import="java.lang.String" %>
	 
<% // Get principal from the request.
	Principal principal = request.getUserPrincipal();
	String userName = principal.getName(); %>
<html>

<head>
<title>Welcome to Volvo IT Application hosting Java</title>
<meta charset="utf-8">

</head>
</head>

<body bgcolor="">

<p align="center"><font size="5" color="#000080">Volvo IT Application Hosting Java & Volvo IT Private cloud whish you<br>
</font></p>
<p align="center"><font size="3" color="#000080">an Happy New Year 2014<br>
</font></p>
<p align="center"><font color="green" size="1">Powered by:</font></p>
<p align="center">
<br/ >
<p align="center"><font color="" size="3"><a href="/WMQ_IVT" target='new'>Click here to</a> execute the IBM MQ Status page</font></p>
</body>
</html>


