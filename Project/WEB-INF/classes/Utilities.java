import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Calendar;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result + "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>";
				System.out.println(usertype().toString());
				if(usertype().toString().equals("manager")) {
					result = result + "<li><a href='Salesman'><span class='glyphicon user'>SalesmanHome</span></a></li>";
				} else if(usertype().toString().equals("retailer")) {
					result = result + "<li><a href='StoreManager'><span class='glyphicon user'>StoreManagerHome</span></a></li>"+"<li><a href='DataVisualization'><span class='glyphicon'>Orders</span></a></li>"+"<li><a href='Inventory'><span class='glyphicon'>Inventory</span></a></li>";
				}
				result = result + "<li><a href='Account'><span class='glyphicon user'>Account</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon log-out'>Logout</span></a></li>";
			}
			else
				result = result +"<li><a href='Login'><span class='glyphicon glyphicon-log-in'>Login</span></a></li>";
				result = result +"<li><a href='Cart'><span class='glyphicon cart'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} else
				pw.print(result);
	}
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		try
		{		
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
		}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		int size=0;
		try
		{
			orderPayments =MySqlDataStoreUtilities.selectOrder();
				
		}
		catch(Exception e)
		{
			
		}
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
				size=entry.getKey();
		}
			
		return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		if(type.equals("fitnessWatch")){
			FitnessWatch fitnessWatch;
			fitnessWatch = MySqlDataStoreUtilities.getFitnessWatches().get(name);
			OrderItem orderitem = new OrderItem(fitnessWatch.getName(), fitnessWatch.getPrice(), fitnessWatch.getImage(), fitnessWatch.getRetailer(), "fitnessWatch");
			orderItems.add(orderitem);
		}
		if(type.equals("smartWatch")){
			SmartWatch smartWatch;
			smartWatch = MySqlDataStoreUtilities.getSmartWatches().get(name);
			OrderItem orderitem = new OrderItem(smartWatch.getName(), smartWatch.getPrice(), smartWatch.getImage(), smartWatch.getRetailer(), "smartWatch");
			orderItems.add(orderitem);
		}
		if(type.equals("headphone")){
			Headphone headphone;
			headphone = MySqlDataStoreUtilities.getHeadphones().get(name);
			OrderItem orderitem = new OrderItem(headphone.getName(), headphone.getPrice(), headphone.getImage(), headphone.getRetailer(), "headphone");
			orderItems.add(orderitem);
		}
		if(type.equals("virtualReality")){
			VirtualReality virtualReality;
			virtualReality = MySqlDataStoreUtilities.getVrMap().get(name);
			OrderItem orderitem = new OrderItem(virtualReality.getName(), virtualReality.getPrice(), virtualReality.getImage(), virtualReality.getRetailer(), "virtualReality");
			orderItems.add(orderitem);
		}
		if(type.equals("petTracker")){
			PetTracker petTracker;
			petTracker = MySqlDataStoreUtilities.getPetTrackers().get(name);
			OrderItem orderitem = new OrderItem(petTracker.getName(), petTracker.getPrice(), petTracker.getImage(), petTracker.getRetailer(), "petTracker");
			orderItems.add(orderitem);
		}
		if(type.equals("phone")){
			Phone phone;
			phone = MySqlDataStoreUtilities.getPhones().get(name);
			OrderItem orderitem = new OrderItem(phone.getName(), phone.getPrice(), phone.getImage(), phone.getRetailer(), "phone");
			orderItems.add(orderitem);
		}
		if(type.equals("laptop")){
			Laptop laptop = null;
			laptop = MySqlDataStoreUtilities.getLaptops().get(name);
			OrderItem orderitem = new OrderItem(laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer(), "laptop");
			orderItems.add(orderitem);
		}
		if(type.equals("voiceAssistant")){
			VoiceAssistant voiceAssistant = null;
			voiceAssistant = MySqlDataStoreUtilities.getVoiceAssistants().get(name);
			OrderItem orderitem = new OrderItem(voiceAssistant.getName(), voiceAssistant.getPrice(), voiceAssistant.getImage(), voiceAssistant.getRetailer(), "voiceAssistant");
			orderItems.add(orderitem);
		}
		if(type.equals("accessories")){	
			Accessory accessory = MySqlDataStoreUtilities.getAccessories().get(name); 
			OrderItem orderitem = new OrderItem(accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getRetailer(), "accessory");
			orderItems.add(orderitem);
		}
		
	}
	
	
	public void removeItemFromCart(String item) {
		ArrayList<OrderItem> orders = OrdersHashMap.orders.get(username());
		int index=0;
		for(OrderItem orderItem : orders) {
			if(orderItem.getName().equals(item)) {
				break;
			}
			else 
				index++;
		}
		orders.remove(index);
	}
	
	// store the payment details for orders
	public void storePayment(int orderId, String username, String customerName, String category, int quantity, String storeId, String storeAddress,
		String orderName,double orderPrice,String userAddress,String creditCardNo, String inStoreOrDelivery, String pincode){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		// get the payment details file 
		try
		{
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 3);
			java.util.Date shipDate = cal.getTime();
			System.out.println("Talking from Utilities storePayment");
			MySqlDataStoreUtilities.insertOrder(orderId,username(),customerName,orderName,orderPrice,userAddress,creditCardNo,inStoreOrDelivery,pincode, new Date(), shipDate, category, quantity, storeId, storeAddress);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public String storeReview(String productName, String productType, String price, String storeId, String storeZip,
	String storeCity, String storeState, String productOnSale, String productMaker, String manufacturerRebate,
	String userName, String userAge, String userGender, String occupation, String reviewRating,
	String reviewDate, String reviewText){
		String message=MongoDBDataStoreUtilities.insertReview(productName,productType,price,storeId,storeZip,
		 storeCity,storeState,productOnSale,productMaker,manufacturerRebate,userName,userAge,userGender,
		 occupation,reviewRating,reviewDate,reviewText);
			if(!message.equals("Successfull"))
			{ return "UnSuccessfull";
			}
			else
			{
			HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
			try
			{
				reviews=MongoDBDataStoreUtilities.selectReview();
			}
			catch(Exception e)
			{
				
			}
			if(reviews==null)
			{
				reviews = new HashMap<String, ArrayList<Review>>();
			}
				// if there exist product review already add it into same list for productname or create a new record with product name
				
			if(!reviews.containsKey(productName)){	
				ArrayList<Review> arr = new ArrayList<Review>();
				reviews.put(productName, arr);
			}
			ArrayList<Review> listReview = reviews.get(productName);		
			Review review = new Review(productName,productType,price,storeId,storeZip,
			storeCity,storeState,productOnSale,productMaker,manufacturerRebate,userName,userAge,userGender,
			occupation,reviewRating,reviewDate,reviewText);
			listReview.add(review);	
				
				// add Reviews into database
			
			return "Successfull";	
			}
		}

	
	/* getFitnessWatches Functions returns the Hashmap with all fitness watches in the store.*/

	public HashMap<String, FitnessWatch> getFitnessWatches(){
			HashMap<String, FitnessWatch> hm = new HashMap<String, FitnessWatch>();
			hm.putAll(MySqlDataStoreUtilities.getFitnessWatches());
			return hm;
	}
	
	/* getSmartWatches Functions returns the Hashmap with all smart watches in the store.*/

	public HashMap<String, SmartWatch> getSmartWatches(){
			HashMap<String, SmartWatch> hm = new HashMap<String, SmartWatch>();
			hm.putAll(MySqlDataStoreUtilities.getSmartWatches());
			return hm;
	}
	
	/* getHeadphones Functions returns the Hashmap with all headphones in the store.*/

	public HashMap<String, Headphone> getHeadphones(){
			HashMap<String, Headphone> hm = new HashMap<String, Headphone>();
			hm.putAll(MySqlDataStoreUtilities.getHeadphones());
			return hm;
	}
	
	/* getVirtualReality Functions returns the Hashmap with all virtual reality in the store.*/

	public HashMap<String, VirtualReality> getVirtualReality(){
			HashMap<String, VirtualReality> hm = new HashMap<String, VirtualReality>();
			hm.putAll(MySqlDataStoreUtilities.getVrMap());
			return hm;
	}
	
	/* getPetTrackers Functions returns the Hashmap with all pet trackers in the store.*/

	public HashMap<String, PetTracker> getPetTrackers(){
			HashMap<String, PetTracker> hm = new HashMap<String, PetTracker>();
			hm.putAll(MySqlDataStoreUtilities.getPetTrackers());
			return hm;
	}
	
	/* getPhones Functions returns the Hashmap with all phones in the store.*/

	public HashMap<String, Phone> getPhones(){
			HashMap<String, Phone> hm = new HashMap<String, Phone>();
			hm.putAll(MySqlDataStoreUtilities.getPhones());
			return hm;
	}
	
	public HashMap<String, Laptop> getLaptops(){
			HashMap<String, Laptop> hm = new HashMap<String, Laptop>();
			hm.putAll(MySqlDataStoreUtilities.getLaptops());
			return hm;
	}
	
	public HashMap<String, VoiceAssistant> getVoiceAssistants(){
			HashMap<String, VoiceAssistant> hm = new HashMap<String, VoiceAssistant>();
			hm.putAll(MySqlDataStoreUtilities.getVoiceAssistants());
			return hm;
	}
	
	/* getProductsFitnessWatch Functions returns the Arraylist of Fitness Watches in the store.*/

	public ArrayList<String> getProductsFitnessWatch(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, FitnessWatch> entry : getFitnessWatches().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsSmartWatch(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SmartWatch> entry : getSmartWatches().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsHeadphone(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Headphone> entry : getHeadphones().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsVirtualReality(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, VirtualReality> entry : getVirtualReality().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsPetTracker(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, PetTracker> entry : getPetTrackers().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsPhone(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Phone> entry : getPhones().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsLaptop(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Laptop> entry : getLaptops().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsVoiceAssistant(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, VoiceAssistant> entry : getVoiceAssistants().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	

}
