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

@WebServlet("/StoreManager")

public class StoreManager extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String process = request.getParameter("product");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		double price = 0.0;
		String prod = "";
		if(!request.getParameter("price").isEmpty()) {
			price = Double.parseDouble(request.getParameter("price"));
		}
		if(!request.getParameter("prod").isEmpty()) {
			prod = request.getParameter("prod");
		}
		String manufacturer = request.getParameter("manufacturer");
		String category = request.getParameter("category");
		double discount = Double.parseDouble(request.getParameter("discount"));
		String condition = request.getParameter("condition");
		if(process.equals("addProduct")) {
			MySqlDataStoreUtilities.addproducts(category, id, name, price, image, manufacturer, condition, discount, prod);
		} else if(process.equals("updateProduct")) {
			MySqlDataStoreUtilities.updateproducts(category, id, name, price, image, manufacturer, condition, discount);
		} else {
			MySqlDataStoreUtilities.deleteproducts(id);
		}
		if(process.equals("addProduct") || process.equals("updateProduct")) {
			if(category.equals("FitnessWatch")) {
				FitnessWatch fitnessWatch = new FitnessWatch(name, price, image, manufacturer, "new", (double)0);
				fitnessWatch.setId(id);
				HashMap<String,FitnessWatch> existing = SaxParserDataStore.fitnessWatches;
				existing.put(fitnessWatch.getId(), fitnessWatch);
				SaxParserDataStore.fitnessWatches = existing;
			} else if(category.equals("SmartWatch")) {
				SmartWatch smartWatch = new SmartWatch(name, price, image, manufacturer, "new", (double)0);
				smartWatch.setId(id);
				HashMap<String,SmartWatch> existing = SaxParserDataStore.smartWatches;
				existing.put(smartWatch.getId(), smartWatch);
				SaxParserDataStore.smartWatches = existing;
			} else if(category.equals("Headphone")) {
				Headphone headphone = new Headphone(name, price, image, manufacturer, "new", (double)0);
				headphone.setId(id);
				HashMap<String,Headphone> existing = SaxParserDataStore.headphones;
				existing.put(headphone.getId(), headphone);
				SaxParserDataStore.headphones = existing;
			} else if(category.equals("VirtualReality")) {
				VirtualReality virtualReality = new VirtualReality(name, price, image, manufacturer, "new", (double)0);
				virtualReality.setId(id);
				HashMap<String,VirtualReality> existing = SaxParserDataStore.vrMap;
				existing.put(virtualReality.getId(), virtualReality);
				SaxParserDataStore.vrMap = existing;
			} else if(category.equals("PetTracker")) {
				PetTracker petTracker = new PetTracker(name, price, image, manufacturer, "new", (double)0);
				petTracker.setId(id);
				HashMap<String,PetTracker> existing = SaxParserDataStore.petTrackers;
				existing.put(petTracker.getId(), petTracker);
				SaxParserDataStore.petTrackers = existing;
			} else if(category.equals("Phone")) {
				Phone phone = new Phone(name, price, image, manufacturer, "new", (double)0);
				phone.setId(id);
				HashMap<String,Phone> existing = SaxParserDataStore.phones;
				existing.put(phone.getId(), phone);
				SaxParserDataStore.phones = existing;
			} else if(category.equals("Laptop")) {
				Laptop laptop = new Laptop(name, price, image, manufacturer, "new", (double)0);
				laptop.setId(id);
				HashMap<String,Laptop> existing = SaxParserDataStore.laptops;
				existing.put(laptop.getId(), laptop);
				SaxParserDataStore.laptops = existing;
			} else if(category.equals("VoiceAssistant")) {
				VoiceAssistant voiceAssistant = new VoiceAssistant(name, price, image, manufacturer, "new", (double)0);
				voiceAssistant.setId(id);
				HashMap<String,VoiceAssistant> existing = SaxParserDataStore.voiceAssistants;
				existing.put(voiceAssistant.getId(), voiceAssistant);
				SaxParserDataStore.voiceAssistants = existing;
			} else {
				System.out.println("Incorrect category entered");
			}
		} else {
			if(category.equals("FitnessWatch")) {
				HashMap<String,FitnessWatch> existing = SaxParserDataStore.fitnessWatches;
				existing.remove(id);
				SaxParserDataStore.fitnessWatches = existing;
			} else if(category.equals("SmartWatch")) {
				HashMap<String,SmartWatch> existing = SaxParserDataStore.smartWatches;
				existing.remove(id);
				SaxParserDataStore.smartWatches = existing;
			} else if(category.equals("Headphone")) {
				HashMap<String,Headphone> existing = SaxParserDataStore.headphones;
				existing.remove(id);
				SaxParserDataStore.headphones = existing;
			} else if(category.equals("VirtualReality")) {
				HashMap<String,VirtualReality> existing = SaxParserDataStore.vrMap;
				existing.remove(id);
				SaxParserDataStore.vrMap = existing;
			} else if(category.equals("PetTracker")) {
				HashMap<String,PetTracker> existing = SaxParserDataStore.petTrackers;
				existing.remove(id);
				SaxParserDataStore.petTrackers = existing;
			} else if(category.equals("Phone")) {
				HashMap<String,Phone> existing = SaxParserDataStore.phones;
				existing.remove(id);
				SaxParserDataStore.phones = existing;
			} else if(category.equals("Laptop")) {
				HashMap<String,Laptop> existing = SaxParserDataStore.laptops;
				existing.remove(id);
				SaxParserDataStore.laptops = existing;
			} else if(category.equals("VoiceAssistant")) {
				HashMap<String,VoiceAssistant> existing = SaxParserDataStore.voiceAssistants;
				existing.remove(id);
				SaxParserDataStore.voiceAssistants = existing;
			} else {
				System.out.println("Incorrect category entered");
			}
		}
		displayAccount(request, response);
	}

	/* Display Order Details of the StoreManager (Name and Usertype) */

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
				session.setAttribute("login_msg", "Please Login as StoreManager to edit order details");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id=\"content\">\r\n"
					+ "	<div class=\"post\">\r\n"
					+ "		<h2 class=\"title\">\r\n"
					+ "			<a href=\"#\">Welcome, Store Manager</a>\r\n"
					+ "		</h2>\r\n"
					+ "		<div class=\"entry\">\r\n"
					+ "			<img src=\"images/site/smart_portables.jpg\"\r\n"
					+ "				style=\"width: 300px; display: block; margin-left: auto; margin-right: auto\" />\r\n"
					+ " 	<form action='StoreManager' method='post'>\r\n"
					+ "		<table id=\"bestseller\">\r\n"
					+ "			<tr>\r\n"
					+ "				<td><label for=\"id\">Product ID:</label></td>\r\n"
					+ "				<td><input type=\"text\" id=\"id\" name=\"id\"></td>"
					+ "			</tr>\r\n"
					+ "			<tr>\r\n"
					+ "				<td><label for=\"name\">Name:</label></td>\r\n"
					+ "				<td><input type=\"text\" id=\"name\" name=\"name\"></td>"
					+ "			</tr>\r\n"
					+ "			<tr>\r\n"
					+ "				<td><label for=\"image\">Image Name:</label></td>\r\n"
					+ "				<td><input type=\"text\" id=\"image\" name=\"image\"></td>"
					+ "			</tr>\r\n"
					+ "			<tr>\r\n"
					+ "				<td><label for=\"price\">Price:</label></td>\r\n"
					+ "				<td><input type=\"text\" id=\"price\" name=\"price\"></td>"
					+ "			</tr>\r\n"
					+ "			<tr>\r\n"
					+ "				<td><label for=\"manufacturer\">Manufacturer:</label></td>\r\n"
					+ "				<td><input type=\"text\" id=\"manufacturer\" name=\"manufacturer\"></td>"
					+ "			</tr>\r\n"
					+ "			<tr>\r\n"
					+ "				<td><label for=\"category\">Category:</label></td>\r\n"
					+ "				<td><input type=\"text\" id=\"category\" name=\"category\"></td>"
					+ "			</tr>\r\n"
					+ "			<tr>\r\n"
					+ "				<td><label for=\"condition\">Condition:</label></td>\r\n"
					+ "				<td><input type=\"text\" id=\"condition\" name=\"condition\"></td>"
					+ "			</tr>\r\n"
					+ "			<tr>\r\n"
					+ "				<td><label for=\"discount\">Discount:</label></td>\r\n"
					+ "				<td><input type=\"text\" id=\"discount\" name=\"discount\"></td>"
					+ "			</tr>\r\n"
					+ "			<tr>\r\n"
					+ "			<td><p>If this product is an accessory, add respective product name here</p></td>"
					+ "				<td><label for=\"prod\">Product Name:</label></td>\r\n"
					+ "				<td><input type=\"text\" id=\"prod\" name=\"prod\"></td>"
					+ "			</tr>\r\n"
					+ "			<tr>\r\n"
		    		+ "  			<td><label for=\"addProduct\">Add Product</label></td>"
					+ "				<td><input type=\"radio\" id=\"addProduct\" name=\"product\" value=\"addProduct\"></td>"
					+ "			</tr>\r\n"
					+ "			<tr>\r\n"
		    		+ "  			<td><label for=\"updateProduct\">Update Product</label></td>"
					+ "				<td><input type=\"radio\" id=\"updateProduct\" name=\"product\" value=\"updateProduct\"></td>"
					+ "			</tr>\r\n"
					+ "			<tr>\r\n"
		    		+ "  			<td><label for=\"deleteProduct\">Delete Product</label></td>"
					+ "				<td><input type=\"radio\" id=\"deleteProduct\" name=\"product\" value=\"deleteProduct\"></td>"
					+ "			</tr>\r\n"
					+ "			<tr>\r\n"
					+ "				<td><input type=\"submit\" id=\"submit\" value=\"submit\"></td>"
					+ "			</tr>\r\n"
					+ "		</table>\r\n"
					+ " 	</form>\r\n"
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
