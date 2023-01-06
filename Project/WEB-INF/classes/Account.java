import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.Date;
import java.io.*;
import java.sql.*;

@WebServlet("/Account")

public class Account extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}

	/* Display Account Details of the Customer (Name and Usertype) */

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
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Account</a>");
			pw.print("</h2><div class='entry'>");
			User user=utility.getUser();
			pw.print("<table class='gridtable'>");
			pw.print("<tr>");
			pw.print("<td> User Name: </td>");
			pw.print("<td>" +user.getName()+ "</td>");
			pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td> User Type: </td>");
			pw.print("<td>" +user.getUsertype()+ "</td>");
			pw.print("</tr>");
			HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
			try
				{
					orderPayments = MySqlDataStoreUtilities.selectOrder();
					System.out.println("Orders : " + orderPayments.size());
					System.out.println(orderPayments.entrySet());
				}
			catch(Exception e)
				{
					e.printStackTrace();
				}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
				{
					for(OrderPayment od:entry.getValue())	{
						System.out.println(od.getUserName() + " AND " + user.getName());
						if(od.getUserName().equals(user.getName()))
							size= size+1;
					}
				}
				System.out.println(size + " - Size");
			if(size>0)
				{	
					
					
					for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
					{
						for(OrderPayment oi:entry.getValue())	{
							if(oi.getUserName().equals(user.getName())) 
							{
								pw.print("<form method='get' action='ViewOrder'>");
								pw.print("<tr>");			
								pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");			
								pw.print("<td>OrderID: "+oi.getOrderId()+".</td><td>Username: "+oi.getUserName()+".</td><td>Ordered Date: "+oi.getDateOrdered()+"</td><td>OrderName: "+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+ "</td></tr><tr><td></td>" + "<td>UserAddress: "+oi.getUserAddress()+"</td><td>StoreAddress: "+oi.getStoreAddress()+"</td><td>StoreId: "+oi.getStoreId()+"</td><td>StorePincode: "+oi.getPincode()+"</td>");
								pw.print("<td><input type='hidden' name='orderId' value='"+oi.getOrderId()+"'></td>");
								if(((new Date()).getTime()-oi.getDateOrdered().getTime())/(1000*60*60*24) < 10) {
									pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
								}
								pw.print("</tr><tr></tr>");
								pw.print("</form>");
							}
						}
					}
					
					pw.print("</table>");
				}
				else
				{
					pw.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
				}
			
				
				
				
				
			pw.print("</table>");		
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");	        	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
}
