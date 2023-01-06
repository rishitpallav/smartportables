import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities Utility = new Utilities(request, pw);
		storeOrders(request, response);
	}
	
	protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try
        {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
        HttpSession session=request.getSession(); 

		//get the order product details	on clicking submit the form will be passed to submitorder page	
		
	    String userName = session.getAttribute("username").toString();
        String orderTotal = request.getParameter("orderTotal");
		ArrayList<Store> stores=MySqlDataStoreUtilities.selectStore();
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='CheckOut' action='Payment' method='post'>");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
		pw.print(userName);
		pw.print("</td></tr>");
		// for each order iterate and display the order name price
		for (OrderItem oi : utility.getCustomerOrders()) 
		{
			pw.print("<tr><td> Product Purchased:</td><td>");
			pw.print(oi.getName()+"</td></tr><tr><td>");
			pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
			pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
			pw.print("Product Price:</td><td>"+ oi.getPrice());
			pw.print("</td></tr>");
		}
		pw.print("<tr><td>");
        pw.print("Total Order Cost</td><td>"+orderTotal);
		pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
		pw.print("</td></tr></table>");	
		pw.print("<table>");
     	pw.print("<tr><td>Credit/accountNo</td>");
		pw.print("<td><input type='text' name='creditCardNo'></td></tr>");
		pw.print("<tr><td>");
     	pw.print("Name</td>");
		pw.print("<td><input type='hidden' name='name' value='" + utility.username() + "'>");
		pw.print("</td></tr>");

		//pw.print("<tr><td>");
		pw.print( "<tr><td><label for=\"inStore\">inStore Pickup</label><br>\r\n</td>");
	    pw.print("<td><input type=\"radio\" id=\"inStore\" name=\"delivery\" value=\"inStore\" selected>\r\n</td></tr>");
		pw.print("<tr><td><p>Pincode: </p></td><td><input type=\"text\" name=\"pincode\"></td></tr>");
		pw.print("<tr><td><select name=\"storeAddress\" id=\"storeAddress\">\r\n");
		for (Store s : stores) {
			pw.print("<option value=");
			pw.print(s.getAddress().trim());
			pw.print(">");
			pw.print(s.getName() + ", " + s.getAddress());
			pw.print("</option>\r\n");
		}
		pw.print("</select></td></tr>"
				+ "<tr><td><label for=\"homeDelivery\">home Delivery</label></td>"
	    		+ "<td><input type=\"radio\" id=\"homeDelivery\" name=\"delivery\" value=\"homeDelivery\">\r\n</td></tr>");
        
		pw.print("<tr><td>");
	    pw.print("Customer Address</td>");
				pw.print("<td><input type=\"text\" name=\"address\"></td></tr>");
        
				pw.print("<tr><td>");
     	pw.print("Address not required if you have selected in store pickup</td>");
		pw.print("</td></tr>");
		pw.print("<tr><td colspan='2'>");
		pw.print("<input type='submit' name='submit' class='btnbuy'>");
        pw.print("</td></tr></table>");
		pw.print("</form>");
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	    }
        catch(Exception e)
		{
         System.out.println(e.getMessage());
		}  			
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    }
}
