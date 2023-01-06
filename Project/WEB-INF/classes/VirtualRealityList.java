import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VirtualRealityList")

public class VirtualRealityList extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        


		HashMap<String, VirtualReality> hm = new HashMap<String, VirtualReality>();
		if(CategoryName==null || CategoryName.equals("virtualReality")){
			hm.putAll(MySqlDataStoreUtilities.getVrMap());
			name = "";
		}
		else
		{
		   if(CategoryName.equals("HP"))
		   {
			 for(Map.Entry<String,VirtualReality> entry : MySqlDataStoreUtilities.getVrMap().entrySet())
			 {
				if(entry.getValue().getRetailer().equals("HP"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "HP";
		   }
		   else if(CategoryName.equals("samsung"))
		    {
			for(Map.Entry<String,VirtualReality> entry : MySqlDataStoreUtilities.getVrMap().entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Samsung"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Samsung";
			}
			else if(CategoryName.equals("Logitech"))
			{
				for(Map.Entry<String,VirtualReality> entry : MySqlDataStoreUtilities.getVrMap().entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Logitech"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Logitech";
			}
			else if(CategoryName.equals("Meta"))
			{
				for(Map.Entry<String,VirtualReality> entry : MySqlDataStoreUtilities.getVrMap().entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Meta"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Meta";
			}
		}

		
		

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Virtual_Reality</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, VirtualReality> entry : hm.entrySet())
		{
			VirtualReality virtualReality = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+virtualReality.getName()+"</h3>");
			pw.print("<strong>$"+virtualReality.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/virtual_reality/"+virtualReality.getImage()+"' alt='' /></li>");
			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='virtualReality'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='virtualReality'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+virtualReality.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='virtualReality'>"+
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
