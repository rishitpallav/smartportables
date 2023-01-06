import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

public class MySqlDataStoreUtilities
{
	static Connection conn = null;
	static String message;
	public static String getConnection()
	{

		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Fetched Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportables","root","root");							
			message="Successfull";
			System.out.println("Successfully connected to MySQL");
			return message;
		}
		catch(SQLException e)
		{
			message="unsuccessful";
			System.out.println("Cannot connect to MySQL");
			return message;
		}
		catch(Exception e)
		{
			message=e.getMessage();
			System.out.println("Somehow not connected!");
			return message;
		}
	}

	public static void Insertproducts()
	{
		try{
			
			
			getConnection();
			
			
			// String truncatetableacc = "delete from Product_accessories;";
			// PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
			// pstt.executeUpdate();
			
			// String truncatetableprod = "delete from  Productdetails;";
			// PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
			// psttprod.executeUpdate();
			
			
			System.out.println("Starting inserting accessories");
			String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
			"VALUES (?,?,?,?,?,?,?,?);";
			for(Map.Entry<String,Accessory> entry : SaxParserDataStore.accessories.entrySet())
			{   
				String name = "accessories";
				Accessory acc = entry.getValue();
				System.out.println(name+ acc.getId()+ acc.getName()+ acc.getPrice()+ acc.getImage()+ acc.getRetailer()+ acc.getCondition()+ acc.getDiscount());
				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,acc.getId());
				pst.setString(3,acc.getName());
				pst.setDouble(4,acc.getPrice());
				pst.setString(5,acc.getImage());
				pst.setString(6,acc.getRetailer());
				pst.setString(7,acc.getCondition());
				pst.setDouble(8,acc.getDiscount());
				
				pst.executeUpdate();
				
				
			}
			System.out.println("Completed Accessories");
			
			for(Map.Entry<String,FitnessWatch> entry : SaxParserDataStore.fitnessWatches.entrySet())
			{   
				FitnessWatch fitnessWatches = entry.getValue();
				String name = "fitnessWatches";
				
				System.out.println(fitnessWatches.getName() + fitnessWatches.getPrice());
				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,fitnessWatches.getId());
				pst.setString(3,fitnessWatches.getName());
				pst.setDouble(4,fitnessWatches.getPrice());
				pst.setString(5,fitnessWatches.getImage());
				pst.setString(6,fitnessWatches.getRetailer());
				pst.setString(7,fitnessWatches.getCondition());
				pst.setDouble(8,fitnessWatches.getDiscount());
				
				pst.executeUpdate();
				try{
					HashMap<String,String> acc = fitnessWatches.getAccessories();
					String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					for(Map.Entry<String,String> accentry : acc.entrySet())
					{
						PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
						pstacc.setString(1,fitnessWatches.getId());
						pstacc.setString(2,accentry.getValue());
						pstacc.executeUpdate();
					}
				}catch(Exception et){
					et.printStackTrace();
				}
			}
			System.out.println("Fitness Watch completed");
			for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.smartWatches.entrySet())
			{   
				SmartWatch smartWatches = entry.getValue();
				String name = "smartWatches";
				
				
				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,smartWatches.getId());
				pst.setString(3,smartWatches.getName());
				pst.setDouble(4,smartWatches.getPrice());
				pst.setString(5,smartWatches.getImage());
				pst.setString(6,smartWatches.getRetailer());
				pst.setString(7,smartWatches.getCondition());
				pst.setDouble(8,smartWatches.getDiscount());
				
				pst.executeUpdate();
				try{
					HashMap<String,String> acc = smartWatches.getAccessories();
					String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					for(Map.Entry<String,String> accentry : acc.entrySet())
					{
						PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
						pstacc.setString(1,smartWatches.getId());
						pstacc.setString(2,accentry.getValue());
						pstacc.executeUpdate();
					}
				}catch(Exception et){
					et.printStackTrace();
				}
			}
			System.out.println("smartWatches completed");
			for(Map.Entry<String,Headphone> entry : SaxParserDataStore.headphones.entrySet())
			{   
				Headphone headphones = entry.getValue();
				String name = "headphones";
				
				
				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,headphones.getId());
				pst.setString(3,headphones.getName());
				pst.setDouble(4,headphones.getPrice());
				pst.setString(5,headphones.getImage());
				pst.setString(6,headphones.getRetailer());
				pst.setString(7,headphones.getCondition());
				pst.setDouble(8,headphones.getDiscount());
				
				pst.executeUpdate();
				try{
					HashMap<String,String> acc = headphones.getAccessories();
					String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					for(Map.Entry<String,String> accentry : acc.entrySet())
					{
						PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
						pstacc.setString(1,headphones.getId());
						pstacc.setString(2,accentry.getValue());
						pstacc.executeUpdate();
					}
				}catch(Exception et){
					et.printStackTrace();
				}
			}
			System.out.println("headphones completed");
			for(Map.Entry<String,VirtualReality> entry : SaxParserDataStore.vrMap.entrySet())
			{   
				VirtualReality vrMap = entry.getValue();
				String name = "vrMap";
				
				
				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,vrMap.getId());
				pst.setString(3,vrMap.getName());
				pst.setDouble(4,vrMap.getPrice());
				pst.setString(5,vrMap.getImage());
				pst.setString(6,vrMap.getRetailer());
				pst.setString(7,vrMap.getCondition());
				pst.setDouble(8,vrMap.getDiscount());
				
				pst.executeUpdate();
				try{
					HashMap<String,String> acc = vrMap.getAccessories();
					String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					for(Map.Entry<String,String> accentry : acc.entrySet())
					{
						PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
						pstacc.setString(1,vrMap.getId());
						pstacc.setString(2,accentry.getValue());
						pstacc.executeUpdate();
					}
				}catch(Exception et){
					et.printStackTrace();
				}
			}
			System.out.println("Virtual Reality completed");
			for(Map.Entry<String,PetTracker> entry : SaxParserDataStore.petTrackers.entrySet())
			{   
				PetTracker petTrackers = entry.getValue();
				String name = "petTrackers";
				
				
				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,petTrackers.getId());
				pst.setString(3,petTrackers.getName());
				pst.setDouble(4,petTrackers.getPrice());
				pst.setString(5,petTrackers.getImage());
				pst.setString(6,petTrackers.getRetailer());
				pst.setString(7,petTrackers.getCondition());
				pst.setDouble(8,petTrackers.getDiscount());
				
				pst.executeUpdate();
				try{
					HashMap<String,String> acc = petTrackers.getAccessories();
					String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					for(Map.Entry<String,String> accentry : acc.entrySet())
					{
						PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
						pstacc.setString(1,petTrackers.getId());
						pstacc.setString(2,accentry.getValue());
						pstacc.executeUpdate();
					}
				}catch(Exception et){
					et.printStackTrace();
				}
			}
			System.out.println("Pet tracker completed");
			for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
			{   
				Phone phones = entry.getValue();
				String name = "phones";
				
				
				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,phones.getId());
				pst.setString(3,phones.getName());
				pst.setDouble(4,phones.getPrice());
				pst.setString(5,phones.getImage());
				pst.setString(6,phones.getRetailer());
				pst.setString(7,phones.getCondition());
				pst.setDouble(8,phones.getDiscount());
				
				pst.executeUpdate();
				try{
					HashMap<String,String> acc = phones.getAccessories();
					String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					for(Map.Entry<String,String> accentry : acc.entrySet())
					{
						PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
						pstacc.setString(1,phones.getId());
						pstacc.setString(2,accentry.getValue());
						pstacc.executeUpdate();
					}
				}catch(Exception et){
					et.printStackTrace();
				}
			}
			System.out.println("Phone completed");
			for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
			{   
				Laptop laptops = entry.getValue();
				String name = "laptops";
				
				
				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,laptops.getId());
				pst.setString(3,laptops.getName());
				pst.setDouble(4,laptops.getPrice());
				pst.setString(5,laptops.getImage());
				pst.setString(6,laptops.getRetailer());
				pst.setString(7,laptops.getCondition());
				pst.setDouble(8,laptops.getDiscount());
				
				pst.executeUpdate();
				try{
					HashMap<String,String> acc = laptops.getAccessories();
					String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					for(Map.Entry<String,String> accentry : acc.entrySet())
					{
						PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
						pstacc.setString(1,laptops.getId());
						pstacc.setString(2,accentry.getValue());
						pstacc.executeUpdate();
					}
				}catch(Exception et){
					et.printStackTrace();
				}
			}
			System.out.println("Laptop completed");
			for(Map.Entry<String,VoiceAssistant> entry : SaxParserDataStore.voiceAssistants.entrySet())
			{   
				VoiceAssistant voiceAssistants = entry.getValue();
				String name = "voiceAssistants";
				
				
				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,voiceAssistants.getId());
				pst.setString(3,voiceAssistants.getName());
				pst.setDouble(4,voiceAssistants.getPrice());
				pst.setString(5,voiceAssistants.getImage());
				pst.setString(6,voiceAssistants.getRetailer());
				pst.setString(7,voiceAssistants.getCondition());
				pst.setDouble(8,voiceAssistants.getDiscount());
				
				pst.executeUpdate();
				try{
					HashMap<String,String> acc = voiceAssistants.getAccessories();
					String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					for(Map.Entry<String,String> accentry : acc.entrySet())
					{
						PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
						pstacc.setString(1,voiceAssistants.getId());
						pstacc.setString(2,accentry.getValue());
						pstacc.executeUpdate();
					}
				}catch(Exception et){
					et.printStackTrace();
				}
			}
			System.out.println("Voice assistant completed");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	} 

	public static HashMap<String,FitnessWatch> getFitnessWatches()
	{	
		HashMap<String,FitnessWatch> hm=new HashMap<String,FitnessWatch>();
		try 
		{
			getConnection();
			
			String selectFitnessWatch="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectFitnessWatch);
			pst.setString(1,"fitnessWatches");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				FitnessWatch con = new FitnessWatch(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
				
				try
				{
					String selectaccessory = "Select * from Product_accessories where productName=?";
					PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
					pstacc.setString(1,rs.getString("Id"));
					ResultSet rsacc = pstacc.executeQuery();
					
					HashMap<String,String> acchashmap = new HashMap<String,String>();
					while(rsacc.next())
					{    
						if (rsacc.getString("accessoriesName") != null){
							
							acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
							con.setAccessories(acchashmap);
						}
						
					}					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
		}
	return hm;			
	}

	public static HashMap<String,SmartWatch> getSmartWatches()
	{	
		HashMap<String,SmartWatch> hm=new HashMap<String,SmartWatch>();
		try 
		{
			getConnection();
			
			String selectSmartWatch="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectSmartWatch);
			pst.setString(1,"smartWatches");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				SmartWatch con = new SmartWatch(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
				
				try
				{
					String selectaccessory = "Select * from Product_accessories where productName=?";
					PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
					pstacc.setString(1,rs.getString("Id"));
					ResultSet rsacc = pstacc.executeQuery();
					
					HashMap<String,String> acchashmap = new HashMap<String,String>();
					while(rsacc.next())
					{    
						if (rsacc.getString("accessoriesName") != null){
							
							acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
							con.setAccessories(acchashmap);
						}
						
					}					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
		}
	return hm;			
	}

	public static HashMap<String,Headphone> getHeadphones()
	{	
		HashMap<String,Headphone> hm=new HashMap<String,Headphone>();
		try 
		{
			getConnection();
			
			String selectHeadphones="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectHeadphones);
			pst.setString(1,"headphones");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				Headphone con = new Headphone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
				
				try
				{
					String selectaccessory = "Select * from Product_accessories where productName=?";
					PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
					pstacc.setString(1,rs.getString("Id"));
					ResultSet rsacc = pstacc.executeQuery();
					
					HashMap<String,String> acchashmap = new HashMap<String,String>();
					while(rsacc.next())
					{    
						if (rsacc.getString("accessoriesName") != null){
							
							acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
							con.setAccessories(acchashmap);
						}
						
					}					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
		}
	return hm;			
	}

	public static HashMap<String,VirtualReality> getVrMap()
	{	
		HashMap<String,VirtualReality> hm=new HashMap<String,VirtualReality>();
		try 
		{
			getConnection();
			
			String selectVrMap="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectVrMap);
			pst.setString(1,"vrMap");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				VirtualReality con = new VirtualReality(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
				
				try
				{
					String selectaccessory = "Select * from Product_accessories where productName=?";
					PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
					pstacc.setString(1,rs.getString("Id"));
					ResultSet rsacc = pstacc.executeQuery();
					
					HashMap<String,String> acchashmap = new HashMap<String,String>();
					while(rsacc.next())
					{    
						if (rsacc.getString("accessoriesName") != null){
							
							acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
							con.setAccessories(acchashmap);
						}
						
					}					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
		}
	return hm;			
	}

	public static HashMap<String,PetTracker> getPetTrackers()
	{	
		HashMap<String,PetTracker> hm=new HashMap<String,PetTracker>();
		try 
		{
			getConnection();
			
			String selectPetTrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectPetTrackers);
			pst.setString(1,"petTrackers");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				PetTracker con = new PetTracker(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
				
				try
				{
					String selectaccessory = "Select * from Product_accessories where productName=?";
					PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
					pstacc.setString(1,rs.getString("Id"));
					ResultSet rsacc = pstacc.executeQuery();
					
					HashMap<String,String> acchashmap = new HashMap<String,String>();
					while(rsacc.next())
					{    
						if (rsacc.getString("accessoriesName") != null){
							
							acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
							con.setAccessories(acchashmap);
						}
						
					}					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
		}
	return hm;			
	}

	public static HashMap<String,Phone> getPhones()
	{	
		HashMap<String,Phone> hm=new HashMap<String,Phone>();
		try 
		{
			getConnection();
			
			String selectPhones="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectPhones);
			pst.setString(1,"phones");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				Phone con = new Phone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
				
				try
				{
					String selectaccessory = "Select * from Product_accessories where productName=?";
					PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
					pstacc.setString(1,rs.getString("Id"));
					ResultSet rsacc = pstacc.executeQuery();
					
					HashMap<String,String> acchashmap = new HashMap<String,String>();
					while(rsacc.next())
					{    
						if (rsacc.getString("accessoriesName") != null){
							
							acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
							con.setAccessories(acchashmap);
						}
						
					}					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
		}
	return hm;			
	}

	public static HashMap<String,Laptop> getLaptops()
	{	
		HashMap<String,Laptop> hm=new HashMap<String,Laptop>();
		try 
		{
			getConnection();
			
			String selectLaptops="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectLaptops);
			pst.setString(1,"laptops");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				Laptop con = new Laptop(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
				
				try
				{
					String selectaccessory = "Select * from Product_accessories where productName=?";
					PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
					pstacc.setString(1,rs.getString("Id"));
					ResultSet rsacc = pstacc.executeQuery();
					
					HashMap<String,String> acchashmap = new HashMap<String,String>();
					while(rsacc.next())
					{    
						if (rsacc.getString("accessoriesName") != null){
							
							acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
							con.setAccessories(acchashmap);
						}
						
					}					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
		}
	return hm;			
	}

	public static HashMap<String,VoiceAssistant> getVoiceAssistants()
	{	
		HashMap<String,VoiceAssistant> hm=new HashMap<String,VoiceAssistant>();
		try 
		{
			getConnection();
			
			String selectVoiceAssistants="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectVoiceAssistants);
			pst.setString(1,"voiceAssistants");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				VoiceAssistant con = new VoiceAssistant(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
				
				try
				{
					String selectaccessory = "Select * from Product_accessories where productName=?";
					PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
					pstacc.setString(1,rs.getString("Id"));
					ResultSet rsacc = pstacc.executeQuery();
					
					HashMap<String,String> acchashmap = new HashMap<String,String>();
					while(rsacc.next())
					{    
						if (rsacc.getString("accessoriesName") != null){
							
							acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
							con.setAccessories(acchashmap);
						}
						
					}					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
		}
	return hm;			
	}

	

	public static HashMap<String,Accessory> getAccessories()
	{	
		HashMap<String,Accessory> hm=new HashMap<String,Accessory>();
		try 
		{
			getConnection();
			
			String selectAcc="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectAcc);
			pst.setString(1,"accessories");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
				{	Accessory acc = new Accessory(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
			hm.put(rs.getString("Id"), acc);
			acc.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
	}

	public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount,String prod)
	{
		String msg = "Product is added successfully";
		try{
			
			getConnection();
			String addProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
			"VALUES (?,?,?,?,?,?,?,?);";
			
			String name = producttype;
			
			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			pst.setString(1,name);
			pst.setString(2,productId);
			pst.setString(3,productName);
			pst.setDouble(4,productPrice);
			pst.setString(5,productImage);
			pst.setString(6,productManufacturer);
			pst.setString(7,productCondition);
			pst.setDouble(8,productDiscount);
			
			pst.executeUpdate();
			try{
				if (!prod.isEmpty())
				{
					String addaprodacc =  "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
					pst1.setString(1,prod);
					pst1.setString(2,productId);
					pst1.executeUpdate();
					
				}
			}catch(Exception e)
			{
				msg = "Erro while adding the product";
				e.printStackTrace();
				
			}
			
			
			
		}
		catch(Exception e)
		{
			msg = "Erro while adding the product";
			e.printStackTrace();
			
		}
		return msg;
	}
	public static String updateproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount)
	{ 
		String msg = "Product is updated successfully";
		try{
			
			getConnection();
			String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=? where Id =?;" ;
			
			
			
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,productName);
			pst.setDouble(2,productPrice);
			pst.setString(3,productImage);
			pst.setString(4,productManufacturer);
			pst.setString(5,productCondition);
			pst.setDouble(6,productDiscount);
			pst.setString(7,productId);
			pst.executeUpdate();
			
			
			
		}
		catch(Exception e)
		{
			msg = "Product cannot be updated";
			e.printStackTrace();
			
		}
		return msg;	
	}
	public static String deleteproducts(String productId) {
		String msg = "Product is deleted successfully";
		try {
			getConnection();
			String deleteproductsQuery ="Delete from Productdetails where Id=?";
			PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
			pst.setString(1,productId);
			
			pst.executeUpdate();
		} catch(Exception e) {
			msg = "Proudct cannot be deleted";
		}
	return msg;
	}

	public static void deleteOrder(int orderId,String orderName)
	{
		try
		{
			
			getConnection();
			String deleteOrderQuery ="Delete from customerorders where OrderId=? and orderName=?";
			PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
			pst.setInt(1,orderId);
			pst.setString(2,orderName);
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			
		}
	}

	public static void insertOrder(int orderId, String userName, String customerName, String orderName, double orderPrice, String userAddress,
	String creditCardNo, String inStoreOrDelivery, String pincode, java.util.Date dateOrdered, java.util.Date shipDate, String category, int quantity, 
	String storeId, String storeAddress)
	{
		try
		{
			
			getConnection();
			
			String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,UserName,OrderName,OrderPrice,userAddress,creditCardNo, inStoreOrDelivery, pincode, dateOrdered, shipDate, category, quantity, storeId, storeAddress) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";	
			System.out.println(insertIntoCustomerOrderQuery);
			System.out.println(orderId+userName+orderName+orderPrice+userAddress+creditCardNo+ inStoreOrDelivery+ pincode+ dateOrdered+ shipDate+ category+ quantity+ storeId+ storeAddress);
			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
			//set the parameter for each column and execute the prepared statement
			pst.setInt(1,orderId);
			pst.setString(2,userName);
			pst.setString(3,orderName);
			pst.setDouble(4,orderPrice);
			pst.setString(5,userAddress);
			pst.setString(6,creditCardNo);
			pst.setString(7,inStoreOrDelivery);
			pst.setString(8,pincode);
			pst.setString(9, dateOrdered.toString());
			pst.setString(10, shipDate.toString());
			pst.setString(11,category);
			pst.setString(12, Integer.toString(quantity));
			pst.setString(13, storeId);
			pst.setString(14, storeAddress);
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}

	public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
	{	

		HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
		try
		{					

			getConnection();
			//select the table 
			String selectOrderQuery ="select * from customerorders";			
			PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
			ResultSet rs = pst.executeQuery();	
			ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
			while(rs.next())
			{
				if(!orderPayments.containsKey(rs.getInt("OrderId")))
				{	
					ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
					orderPayments.put(rs.getInt("orderId"), arr);
				}
				ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));		
				

				//add to orderpayment hashmap
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, 3);
				java.util.Date shipDate = cal.getTime();
				OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"),rs.getString("inStoreOrDelivery"),rs.getString("pincode"),new java.util.Date(),shipDate,rs.getString("category"),rs.getInt("quantity"),rs.getString("storeId"),rs.getString("storeAddress"));
				System.out.println("MyDSU SelectOrder : "+ order.getStoreAddress());
				listOrderPayment.add(order);
				
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderPayments;
	}

	public static ArrayList<OrderPayment> selectOrderForMap()
	{	

		ArrayList<OrderPayment> orderPayments=new ArrayList<OrderPayment>();
		
		try
		{					

			getConnection();
			String selectOrderQuery ="select * from customerorders";			
			PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				
				//add to orderpayment hashmap
				SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
				Date orderedDate = dateFormat.parse(rs.getString("dateOrdered"));
				Date shipDate = dateFormat.parse(rs.getString("shipDate"));

				OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"),rs.getString("inStoreOrDelivery"),rs.getString("pincode"),orderedDate,shipDate,rs.getString("category"),rs.getInt("quantity"),rs.getString("storeId"),rs.getString("storeAddress"));
				orderPayments.add(order);
				
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderPayments;
	}


	public static void insertUser(String username,String password,String repassword,String usertype, String userAddress)
	{
		try
		{	

			getConnection();
			String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype, userAddress) "
			+ "VALUES (?,?,?,?,?);";	
			
			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
			pst.setString(1,username);
			pst.setString(2,password);
			pst.setString(3,repassword);
			pst.setString(4,usertype);
			pst.setString(5, userAddress);
			pst.execute();
		}
		catch(Exception e)
		{
			
		}	
	}

	public static HashMap<String,User> selectUser()
	{	
		HashMap<String,User> hm=new HashMap<String,User>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  Registration";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
				{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
			hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
	}

	public static ArrayList<Store> selectStore()
	{	

		ArrayList<Store> stores=new ArrayList<Store>();
		
		try
		{					
			getConnection();
			//select the table 
			String selectOrderQuery ="select * from store";
			PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				Store store = new Store();
				store.setId(rs.getInt(1));
				store.setName(rs.getString(2));
				store.setAddress(rs.getString(3));
				store.setPincode(rs.getInt(4));
				stores.add(store);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return stores;
	}

	public static ArrayList<Product> selectProductsForInventory() {
		ArrayList<Product> products=new ArrayList<Product>();
		
		try
		{					
			getConnection();
			//select the table 
			String selectOrderQuery ="select * from productdetails";
			PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				Product product = new Product();
				product.setName(rs.getString("productName"));
				product.setOnSale(rs.getBoolean("onSale"));
				product.setManufacturerRebate(rs.getBoolean("manufacturerRebate"));
				product.setAvailable(rs.getInt("available"));
				product.setPrice(rs.getDouble("productPrice"));
				products.add(product);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return products;
	}


}	