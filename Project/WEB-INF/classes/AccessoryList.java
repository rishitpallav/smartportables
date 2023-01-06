import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AccessoryList")

public class AccessoryList extends HttpServlet {

	/*
	 * Accessory Page Displays all the Accessories and their Information in Game
	 * Speed
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/*
		 * Checks the Console maker whether it is microsft or sony or nintendo
		 * Add the respective product value to hashmap
		 */

		String categoryName = request.getParameter("category");

		HashMap<String, FitnessWatch> fMap = new HashMap<String, FitnessWatch>();
		HashMap<String, SmartWatch> sMap = new HashMap<String, SmartWatch>();
		HashMap<String, Headphone> hMap = new HashMap<String, Headphone>();
		HashMap<String, VirtualReality> virMap = new HashMap<String, VirtualReality>();
		HashMap<String, PetTracker> petMap = new HashMap<String, PetTracker>();
		HashMap<String, Phone> phMap = new HashMap<String, Phone>();
		HashMap<String, Laptop> lMap = new HashMap<String, Laptop>();
		HashMap<String, VoiceAssistant> voiMap = new HashMap<String, VoiceAssistant>();

		// String ConsoleName = request.getParameter("console");
		if (categoryName.equals("fitnessWatch")) {
			for (Map.Entry<String, FitnessWatch> entry : MySqlDataStoreUtilities.getFitnessWatches().entrySet()) {
				fMap.put(entry.getValue().getId(), entry.getValue());
			}
		} else if (categoryName.equals("smartWatch")) {
			for (Map.Entry<String, SmartWatch> entry : MySqlDataStoreUtilities.getSmartWatches().entrySet()) {
				sMap.put(entry.getValue().getId(), entry.getValue());
			}
		} else if (categoryName.equals("headphone")) {
			for (Map.Entry<String, Headphone> entry : MySqlDataStoreUtilities.getHeadphones().entrySet()) {
				hMap.put(entry.getValue().getId(), entry.getValue());
			}
		} else if (categoryName.equals("virtualReality")) {
			for (Map.Entry<String, VirtualReality> entry : MySqlDataStoreUtilities.getVrMap().entrySet()) {
				virMap.put(entry.getValue().getId(), entry.getValue());
			}
		} else if (categoryName.equals("petTracker")) {
			for (Map.Entry<String, PetTracker> entry : MySqlDataStoreUtilities.getPetTrackers().entrySet()) {
				petMap.put(entry.getValue().getId(), entry.getValue());
			}
		} else if (categoryName.equals("phone")) {
			for (Map.Entry<String, Phone> entry : MySqlDataStoreUtilities.getPhones().entrySet()) {
				phMap.put(entry.getValue().getId(), entry.getValue());
			}
		} else if (categoryName.equals("laptop")) {
			for (Map.Entry<String, Laptop> entry : MySqlDataStoreUtilities.getLaptops().entrySet()) {
				lMap.put(entry.getValue().getId(), entry.getValue());
			}
		} else if (categoryName.equals("voiceAssistant")) {
			for (Map.Entry<String, VoiceAssistant> entry : MySqlDataStoreUtilities.getVoiceAssistants().entrySet()) {
				voiMap.put(entry.getValue().getId(), entry.getValue());
			}
		}

		// Console console = hm.get(ConsoleName);

		/*
		 * Header, Left Navigation Bar are Printed.
		 * 
		 * All the Accessories and Accessories information are dispalyed in the Content
		 * Section
		 * 
		 * and then Footer is Printed
		 */

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + categoryName + ": Accessories</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = 2;

		if (categoryName.equals("fitnessWatch")) {

			for (Map.Entry<String, FitnessWatch> entry : fMap.entrySet()) {
				for (Map.Entry<String, String> acc : entry.getValue().getAccessories().entrySet()) {
					callMe(i, pw, acc, categoryName, entry.getValue().getName());
				}
			}
		}
		if (categoryName.equals("smartWatch")) {
			for (Map.Entry<String, SmartWatch> entry : sMap.entrySet()) {
				for (Map.Entry<String, String> acc : entry.getValue().getAccessories().entrySet()) {
					callMe(i, pw, acc, categoryName, entry.getValue().getName());
				}
			}
		}
		if (categoryName.equals("headphone")) {

			for (Map.Entry<String, Headphone> entry : hMap.entrySet()) {
				for (Map.Entry<String, String> acc : entry.getValue().getAccessories().entrySet()) {
					callMe(i, pw, acc, categoryName, entry.getValue().getName());
				}
			}
		}
		if (categoryName.equals("virtualReality")) {

			for (Map.Entry<String, VirtualReality> entry : virMap.entrySet()) {
				for (Map.Entry<String, String> acc : entry.getValue().getAccessories().entrySet()) {
					callMe(i, pw, acc, categoryName, entry.getValue().getName());
				}
			}
		}
		if (categoryName.equals("petTracker")) {

			for (Map.Entry<String, PetTracker> entry : petMap.entrySet()) {
				for (Map.Entry<String, String> acc : entry.getValue().getAccessories().entrySet()) {
					callMe(i, pw, acc, categoryName, entry.getValue().getName());
				}
			}
		}
		if (categoryName.equals("phone")) {

			for (Map.Entry<String, Phone> entry : phMap.entrySet()) {
				for (Map.Entry<String, String> acc : entry.getValue().getAccessories().entrySet()) {
					callMe(i, pw, acc, categoryName, entry.getValue().getName());
				}
			}
		}
		if (categoryName.equals("laptop")) {

			for (Map.Entry<String, Laptop> entry : lMap.entrySet()) {
				for (Map.Entry<String, String> acc : entry.getValue().getAccessories().entrySet()) {
					callMe(i, pw, acc, categoryName, entry.getValue().getName());
				}
			}
		}
		if (categoryName.equals("fitnessWatch")) {

			for (Map.Entry<String, FitnessWatch> entry : fMap.entrySet()) {
				for (Map.Entry<String, String> acc : entry.getValue().getAccessories().entrySet()) {
					callMe(i, pw, acc, categoryName, entry.getValue().getName());
				}
			}
		}
		if (categoryName.equals("fitnessWatch")) {

			for (Map.Entry<String, FitnessWatch> entry : fMap.entrySet()) {
				for (Map.Entry<String, String> acc : entry.getValue().getAccessories().entrySet()) {
					callMe(i, pw, acc, categoryName, entry.getValue().getName());
				}
			}
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}

	private void callMe(int i, PrintWriter pw, Map.Entry<String, String> acc, String categoryName, String entryGetName) {
		Accessory accessory = MySqlDataStoreUtilities.getAccessories().get(acc.getValue());
		if (i % 2 == 1)
			pw.print("<tr>");
		pw.print("<td><div id='shop_item'>");
		pw.print("<h3>" + accessory.getName() + "</h3>");
		pw.print("<strong>" + accessory.getPrice() + "$</strong><ul>");
		pw.print("<li id='item'><img src='images/accessories/" + accessory.getImage() + "' alt='' /></li>");
		pw.print("<li><form method='post' action='Cart'>" +
				"<input type='hidden' name='name' value='" + acc.getValue() + "'>" +
				"<input type='hidden' name='type' value='accessory'>" +
				"<input type='hidden' name='maker' value='" + categoryName + "'>" +
				"<input type='hidden' name='access' value='" + entryGetName + "'>" +
				"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
		pw.print(
				"<li><form method='post' action='WriteReview'>" + "<input type='hidden' name='name' value='"
						+ acc + "'>" +
						"<input type='hidden' name='type' value='accessories'>" +
						"<input type='hidden' name='maker' value='" + categoryName + "'>" +
						"<input type='hidden' name='access' value='" + entryGetName + "'>" +
						"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
		pw.print("<li><form method='post' action='ViewReview'>" + "<input type='hidden' name='name' value='"
				+ acc + "'>" +
				"<input type='hidden' name='type' value='accessories'>" +
				"<input type='hidden' name='maker' value='" + categoryName + "'>" +
				"<input type='hidden' name='access' value='" + entryGetName + "'>" +
				"<input type='submit' value='ViewReview' class='btnreview'></form></li>");

		pw.print("</ul></div></td>");
		if (i % 2 == 0 || i == 2)
			pw.print("</tr>");
		i++;

	}
}