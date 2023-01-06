import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet	

		// String userAddress;
		// String customerName;
		// String creditCardNo;
		// String pincode;
		// String username = "";
		// int storeId;
		// String storeAddress;
		// String category;
		// String inStoreOrDelivery = request.getParameter("delivery");
		String userAddress = request.getParameter("address");
		String customerName = request.getParameter("name");
		String creditCardNo = request.getParameter("creditCardNo");
		String pincode = request.getParameter("pincode");
		String username = "";
		String storeAddress = request.getParameter("storeAddress");
		String inStoreOrDelivery = request.getParameter("delivery");
		ArrayList<Store> stores = MySqlDataStoreUtilities.selectStore();
		String storeId = "None";
		for (Store store : stores) {
			if(store.getAddress().trim().equals(storeAddress)) {
				storeId = store.getName();
			}
		}

		System.out.println("\n the user address is" +userAddress);
		System.out.println(creditCardNo);
		System.out.println("the user selected " + inStoreOrDelivery);
		System.out.println("zip: " + pincode);
		if(!creditCardNo.isEmpty() && !inStoreOrDelivery.isEmpty() && !pincode.isEmpty())
		{
			//Random rand = new Random(); 
			//int orderId = rand.nextInt(100);
			
			int orderId=utility.getOrderPaymentSize()+1;

			//iterate through each order

			System.out.println("About to call OrderItem Loop");

			for (OrderItem oi : utility.getCustomerOrders())
			{
				System.out.println("Calling from Payment - OrderItem loop");
				//set the parameter for each column and execute the prepared statement

				utility.storePayment(orderId,username,customerName,oi.getCategory(),utility.getCustomerOrders().size(),storeId,storeAddress,oi.getName(),oi.getPrice(),userAddress,creditCardNo, inStoreOrDelivery, pincode);
			}
			System.out.println("Finished OrderItem Loop");

			//remove the order details from cart after processing
			
			OrdersHashMap.orders.remove(utility.username());	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h2>Your Order");
			pw.print("&nbsp&nbsp");  
			pw.print("is stored ");
			pw.print("<br>Your Order No is "+(orderId));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 14);
			String dateAfter = simpleDateFormat.format(cal.getTime());
			pw.print("</h2></div><h2>You will receive your order by "+ dateAfter +" </h2></div></div>");		
			utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h4 style='color:red'>Please enter valid details</h4>");
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
