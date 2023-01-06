import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SmartWatchList")

public class SmartWatchList extends HttpServlet {

	/* SmartWatch Page Displays all the SmartWatchs and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        


		HashMap<String, SmartWatch> hm = new HashMap<String, SmartWatch>();
		if(CategoryName==null || CategoryName.equals("smartWatch")){
			hm.putAll(MySqlDataStoreUtilities.getSmartWatches());
			name = "";
		}
		else
		{
		   if(CategoryName.equals("Apple"))
		   {
			 for(Map.Entry<String,SmartWatch> entry : MySqlDataStoreUtilities.getSmartWatches().entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Apple"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Apple";
		   }
		   else if(CategoryName.equals("samsung"))
		    {
			for(Map.Entry<String,SmartWatch> entry : MySqlDataStoreUtilities.getSmartWatches().entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Samsung"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Samsung";
			}
			else if(CategoryName.equals("Motorola"))
			{
				for(Map.Entry<String,SmartWatch> entry : MySqlDataStoreUtilities.getSmartWatches().entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Motorola"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Motorola";
			}
		}

		
		/* Header, Left Navigation Bar are Printed.

		All the SmartWatch and SmartWatch information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" SmartWatches</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String,SmartWatch> entry : hm.entrySet())
		{
			SmartWatch smartWatch = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+smartWatch.getName()+"</h3>");
			pw.print("<strong>$"+smartWatch.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/smart_watches/"+smartWatch.getImage()+"' alt='' /></li>");
			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='smartWatch'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='smartWatches'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+smartWatch.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='smartWatches'>"+
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
