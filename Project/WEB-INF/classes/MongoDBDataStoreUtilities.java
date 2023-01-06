import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import java.util.*;

public class MongoDBDataStoreUtilities
{
	static DBCollection customerReviews;
	public static DBCollection getConnection()
	{
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);

		DB db = mongo.getDB("SmartPortables");
		customerReviews= db.getCollection("customerReviews");
		return customerReviews; 
	}


	public static String insertReview(String productName, String productType, String price, String storeId, String storeZip,
	String storeCity, String storeState, String productOnSale, String productMaker, String manufacturerRebate,
	String userName, String userAge, String userGender, String occupation, String reviewRating,
	String reviewDate, String reviewText)
	{
		try
		{		
			getConnection();
			BasicDBObject doc = new BasicDBObject("title", "customerReviews").
			append("userName", userName).
			append("productName", productName).
			append("productType", productType).
			append("storeId",storeId).
			append("storeZip",storeZip).
			append("storeCity",storeCity).
			append("storeState",storeState).
			append("onSale",productOnSale).
			append("productMaker", productMaker).
			append("manufacturerRebate",manufacturerRebate).
			append("userAge",userAge).
			append("userGender",userGender).
			append("occupation",occupation).
			append("reviewRating",Integer.parseInt(reviewRating)).
			append("reviewDate", reviewDate).
			append("reviewText", reviewText).
			append("price",(int) Double.parseDouble(price));
			customerReviews.insert(doc);
			return "Successfull";
		}
		catch(Exception e)
		{
			return "UnSuccessfull";
		}	
		
	}

	public static HashMap<String, ArrayList<Review>> selectReview()
	{	
		HashMap<String, ArrayList<Review>> reviews=null;

		try
		{

			getConnection();
			DBCursor cursor = customerReviews.find();
			reviews=new HashMap<String, ArrayList<Review>>();
			while (cursor.hasNext())
			{
				BasicDBObject obj = (BasicDBObject) cursor.next();				

				if(!reviews.containsKey(obj.getString("productName")))
				{	
					ArrayList<Review> arr = new ArrayList<Review>();
					reviews.put(obj.getString("productName"), arr);
				}
				ArrayList<Review> listReview = reviews.get(obj.getString("productName"));		
				Review review =new Review(obj.getString("productName"),obj.getString("productType"),obj.getString("price"),obj.getString("storeId"),obj.getString("storeZip"),obj.getString("storeCity"),obj.getString("storeState"),obj.getString("onSale"),obj.getString("productMaker"),
					obj.getString("manufacturerRebate"),obj.getString("userName"),obj.getString("userAge"),obj.getString("userGender"),obj.getString("occupation"),obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"));
			//add to review hashmap
				listReview.add(review);

			}
			return reviews;
		}
		catch(Exception e)
		{
			reviews=null;
			return reviews;	
		}	


	}
	

	public static  ArrayList <Bestrating> topProducts(){
		ArrayList <Bestrating> Bestrate = new ArrayList <Bestrating> ();
		try{

			getConnection();
			int retlimit =5;
			DBObject sort = new BasicDBObject();
			sort.put("reviewRating",-1);
			DBCursor cursor = customerReviews.find().limit(retlimit).sort(sort);
			while(cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();

				String prodcutnm = obj.get("productName").toString();
				String rating = obj.get("reviewRating").toString();
				Bestrating best = new Bestrating(prodcutnm,rating);
				Bestrate.add(best);
			}


		}
		catch (Exception e)
		{ 
			System.out.println("it is"+e.getMessage());
		}
		return Bestrate;
	}

	public static ArrayList <Mostsoldzip> mostsoldZip(){
		ArrayList <Mostsoldzip> mostsoldzip = new ArrayList <Mostsoldzip> ();
		try{

			getConnection();
			System.out.println("inside mostsoldZip in MongoDBDataStoreUtilities");
			DBObject groupProducts = new BasicDBObject("_id","$storeZip"); 
			groupProducts.put("count",new BasicDBObject("$sum",1));
			DBObject group = new BasicDBObject("$group",groupProducts);
			DBObject limit=new BasicDBObject();
			limit=new BasicDBObject("$limit",5);

			DBObject sortFields = new BasicDBObject("count",-1);
			DBObject sort = new BasicDBObject("$sort",sortFields);
			AggregationOutput output = customerReviews.aggregate(group,sort,limit);
			for (DBObject res : output.results()) {

				String zipcode =(res.get("_id")).toString();
				String count = (res.get("count")).toString();	
				Mostsoldzip mostsldzip = new Mostsoldzip(zipcode,count);
				System.out.println("inside mostsldzip in MongoUtilities is"+mostsldzip);
				mostsoldzip.add(mostsldzip);

			}

		}catch (Exception e){
			System.out.println("error is"+e);
		}
		return mostsoldzip;
	}

	public static ArrayList <Mostsold> mostsoldProducts(){
		ArrayList <Mostsold> mostsold = new ArrayList <Mostsold> ();
		try{


			getConnection();
			DBObject groupProducts = new BasicDBObject("_id","$productName"); 
			groupProducts.put("count",new BasicDBObject("$sum",1));
			DBObject group = new BasicDBObject("$group",groupProducts);
			DBObject limit=new BasicDBObject();
			limit=new BasicDBObject("$limit",5);

			DBObject sortFields = new BasicDBObject("count",-1);
			DBObject sort = new BasicDBObject("$sort",sortFields);
			AggregationOutput output = customerReviews.aggregate(group,sort,limit);

			for (DBObject res : output.results()) {



				String prodcutname =(res.get("_id")).toString();
				String count = (res.get("count")).toString();	
				Mostsold mostsld = new Mostsold(prodcutname,count);
				mostsold.add(mostsld);

			}



		}catch (Exception e){ System.out.println(e.getMessage());}
		return mostsold;
	}	

  //Get all the reviews grouped by product and zip code;
	public static ArrayList<Review> selectReviewForChart() {

		
		ArrayList<Review> reviewList = new ArrayList<Review>();
		try {

			getConnection();
			Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
			dbObjIdMap.put("storeZip", "$storeZip");
			dbObjIdMap.put("productName", "$productName");
			DBObject groupFields = new BasicDBObject("_id", new BasicDBObject(dbObjIdMap));
			groupFields.put("count", new BasicDBObject("$sum", 1));
			DBObject group = new BasicDBObject("$group", groupFields);

			DBObject projectFields = new BasicDBObject("_id", 0);
			projectFields.put("storeZip", "$_id");
			projectFields.put("productName", "$productName");
			projectFields.put("reviewCount", "$count");
			DBObject project = new BasicDBObject("$project", projectFields);

			DBObject sort = new BasicDBObject();
			sort.put("reviewCount", -1);

			DBObject orderby = new BasicDBObject();            
			orderby = new BasicDBObject("$sort",sort);


			AggregationOutput aggregate = customerReviews.aggregate(group, project, orderby);

			for (DBObject result : aggregate.results()) {

				BasicDBObject obj = (BasicDBObject) result;
				Object o = com.mongodb.util.JSON.parse(obj.getString("storeZip"));
				BasicDBObject dbObj = (BasicDBObject) o;
				Review review = new Review(dbObj.getString("productName"), dbObj.getString("storeZip"),
					obj.getString("reviewCount"), null);
				reviewList.add(review);

			}
			return reviewList;

		}

		catch (

			Exception e) {
			reviewList = null;

			return reviewList;
		}

	}


	
}	