import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PetTrackerList")

public class PetTrackerList extends HttpServlet {

	/* PetTracker Page Displays all the PetTrackers and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        

		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, PetTracker> hm = new HashMap<String, PetTracker>();
		if(CategoryName==null || CategoryName.equals("petTracker")){
			hm.putAll(MySqlDataStoreUtilities.getPetTrackers());
			name = "";
		}
		else
		{
		   if(CategoryName.equals("Apple"))
		   {
			 for(Map.Entry<String,PetTracker> entry : MySqlDataStoreUtilities.getPetTrackers().entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Apple"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Apple";
		   }
		   else if(CategoryName.equals("Cube"))
		    {
			for(Map.Entry<String,PetTracker> entry : MySqlDataStoreUtilities.getPetTrackers().entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Cube"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Cube";
			}
			else if(CategoryName.equals("Tile"))
			{
				for(Map.Entry<String,PetTracker> entry : MySqlDataStoreUtilities.getPetTrackers().entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Tile"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Tile";
			}
			else if(CategoryName.equals("Tractive"))
			{
				for(Map.Entry<String,PetTracker> entry : MySqlDataStoreUtilities.getPetTrackers().entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Tractive"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Tractive";
			}
		}

		
		/* Header, Left Navigation Bar are Printed.

		All the PetTracker and PetTracker information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" PetTrackers</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, PetTracker> entry : hm.entrySet())
		{
			PetTracker petTracker = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+petTracker.getName()+"</h3>");
			pw.print("<strong>$"+petTracker.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/pet_tracker/"+petTracker.getImage()+"' alt='' /></li>");
			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='petTracker'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='petTrackers'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+petTracker.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='petTrackers'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}
}
