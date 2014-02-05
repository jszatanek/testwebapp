package com.volvoit.jboss.utils;
/*
 * Status Servlet for Jboss connection to PostGres DB
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Status Servlet for PostGres datasource.
 * 
 */

public class Status extends HttpServlet {
	final String lsSql = "select current_database(),current_user,now()";
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Welcome Volvo IT Jboss Status  !!!</title>");
		out.println("</head>");
		out.println("<body>");
		out
				.println("<p align=\"center\"><font size=\"5\" color=\"#000080\">Welcome to AHS Java Status</font></p>");
		out.println("<p align=\"center\"><table border=0 width='50%'><tr><td>");
		long start=new GregorianCalendar().getTimeInMillis();
		long end;
		Connection loConn=connexionDB();
		if (loConn != null) {
			out.println("Connection PostGres...<br/>");
			out.println("<font size=\"3\" color=\"green\">");
			end= new GregorianCalendar().getTimeInMillis();
			long elaps=end - start;
			out.println("Successfully connected to PostGres datasource in " + elaps + "ms<br/>");
			out.println("</font>");
			out.println("Executing SQL: <br />" + lsSql + "<br />");
			start=new GregorianCalendar().getTimeInMillis();
			String lsResult=executeSQL(loConn);
			end= new GregorianCalendar().getTimeInMillis();
			elaps=end - start;
			out.println("SQL result: ( "+ elaps +"ms)<br />");
			out.println("<table cellspacing=0 border=1><tr><th width=100>Database</th><th width=50>User</th><th width=250>Date</th></tr>");
			out.println(lsResult);
			out.println("</table>");
			
		} else {
			
			out.println("Connection PostGres...<br/>");
			out.println("<font size=\"3\" color=\"red\">");
			out.println("Connection PostGres failed");
			out.println("</font>");
		}
		out.println("</td></tr></table>");
		out.println("<br /><br /><a href='/volvoit/web'>Back to Home Page</a></p>");
		//out.println("<p align=\"center\"><a href=\"javascript:history.back()\">Go to Home</a></p>");
		
		out.println("</body>");
		out.println("</html>");
	}

	public Connection connexionDB() {
		Connection conn=null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:jboss/PostgresDS");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			return conn;
		} catch (Exception loEx ) {
			loEx.printStackTrace();
		}
		return conn;
	}
	
	private String executeSQL(Connection pConn) {
		
		String ddb = "";
		String user="";
		String date="";
		String result = new String();
		try {
		
		PreparedStatement pstmt = pConn
					.prepareStatement(lsSql);
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ddb = rs.getString(1);
				user = rs.getString(2);
				date = rs.getString(3);
				
				result = "<tr><td>" + ddb + "</td><td>" + user + "</td><td>" + date + "</td></tr>";
			}

			rs.close();
			
			pConn.close();
		} catch (Exception loException) {

		}
		return result;

	}
}