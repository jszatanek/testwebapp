package com.volvoit.jboss.utils;
/*
 * Wait Servlet (to test saturation)
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

public class WaitServlet extends HttpServlet {
	
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = "wait";
	    String value = request.getParameter(name);
	    long lnWait=30000;
	    
	    

		
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Volvo IT Wait Page</title>");
		out.println("</head>");
		out.println("<body>");
		out
				.println("<p align=\"center\"><font size=\"5\" color=\"#000080\">Please Wait...</font></p>");
		out.println("<p align=\"center\"><table border=0 width='50%'><tr><td>");
		
		if (value == null) {
            // The request parameter 'param' was not present in the query string
            // e.g. http://hostname.com?a=b
            out.println("using default wait value: " + lnWait + "ms");
        } else if ("".equals(value)) {
            // The request parameter 'param' was present in the query string but has no value
            // e.g. http://hostname.com?param=&a=b
            out.println("using default wait value: " + lnWait + "ms");
        } else {
            lnWait=Long.parseLong(value);
        }
		
        try {
            Thread.sleep(lnWait);
            out.println("<br /><br />Succesfull wait time: " + lnWait+ "ms</p>");
        } catch (Exception e) {
            // handle exception
            out.println("Exception in Thread.sleep" + e.getMessage());
        }
		out.println("</td></tr></table>");
		//out.println("<br /><br /><a href='/volvoit/web'>Back to Home Page</a></p>");
		//out.println("<p align=\"center\"><a href=\"javascript:history.back()\">Go to Home</a></p>");
		
		out.println("</body>");
		out.println("</html>");
	}

}