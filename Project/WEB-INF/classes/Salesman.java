import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;

@WebServlet("/Salesman")

public class Salesman extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}

	/* Display Order Details of the Customer (Name and Usertype) */

	protected void displayAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		try
         {  
           response.setContentType("text/html");
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login as Salesman to edit order details");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id=\"content\">\r\n"
					+ "	<div class=\"post\">\r\n"
					+ "		<h2 class=\"title\">\r\n"
					+ "			<a href=\"#\">Welcome, Manager</a>\r\n"
					+ "		</h2>\r\n"
					+ "		<div class=\"entry\">\r\n"
					+ "			<img src=\"images/site/smart_portables.jpg\"\r\n"
					+ "				style=\"width: 300px; display: block; margin-left: auto; margin-right: auto\" />\r\n"
					+ "		<table id=\"bestseller\">\r\n"
					+ "			<tr>\r\n"
					+ "				<td><h3 class=\"text-center\">Select Any of the following<h3></td>\r\n"
					+ "				<tr><td><form action='Registration' method='get'><input type='submit' class=\"btnbuy\" value='Register A Customer'></form></td></tr>\r\n"
					+ "                 <tr><td><button class=\"btnbuy\" onclick=\"location.href='ViewOrder'\">View Customer Order</td></tr>"
					+ "				</div>\r\n"
					+ "			</tr>\r\n"
					+ "		</table>\r\n"
					+ "		</div>\r\n"
					+ "	</div>\r\n"
					+ "</div>");
			utility.printHtml("Footer.html");	        	
		}
		catch(Exception e)
		{
		}		
	}
}
