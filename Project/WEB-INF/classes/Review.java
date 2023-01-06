import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
	private String productName;
	private String productType;
	private String price;
	private String storeId; //new
	private String storeZip; //new
	private String storeCity; //new
	private String storeState; //new
	private String productOnSale; //new
	private String productMaker;
	private String manufacturerRebate; //new
	private String userName;
	private String userAge; //new
	private String userGender; //new
	private String occupation; //new
	private String reviewRating;
	private String reviewDate;
	private String reviewText;
	// private String storeZip;
	// private String retailercity;

	public Review(String productName, String storeZip, String reviewRating, String reviewText) {
       this.productName = productName;
       this.storeZip = storeZip;
       this.reviewRating = reviewRating;
       this.reviewText = reviewText;
    }
	public Review(String productName, String productType, String price, String storeId, String storeZip,
			String storeCity, String storeState, String productOnSale, String productMaker, String manufacturerRebate,
			String userName, String userAge, String userGender, String occupation, String reviewRating,
			String reviewDate, String reviewText) {
		super();
		this.productName = productName;
		this.productType = productType;
		this.price = price;
		this.storeId = storeId;
		this.storeZip = storeZip;
		this.storeCity = storeCity;
		this.storeState = storeState;
		this.productOnSale = productOnSale;
		this.productMaker = productMaker;
		this.manufacturerRebate = manufacturerRebate;
		this.userName = userName;
		this.userAge = userAge;
		this.userGender = userGender;
		this.occupation = occupation;
		this.reviewRating = reviewRating;
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreZip() {
		return storeZip;
	}
	public void setStoreZip(String storeZip) {
		this.storeZip = storeZip;
	}
	public String getStoreCity() {
		return storeCity;
	}
	public void setStoreCity(String storeCity) {
		this.storeCity = storeCity;
	}
	public String getStoreState() {
		return storeState;
	}
	public void setStoreState(String storeState) {
		this.storeState = storeState;
	}
	public String getProductOnSale() {
		return productOnSale;
	}
	public void setProductOnSale(String productOnSale) {
		this.productOnSale = productOnSale;
	}
	public String getProductMaker() {
		return productMaker;
	}
	public void setProductMaker(String productMaker) {
		this.productMaker = productMaker;
	}
	public String getManufacturerRebate() {
		return manufacturerRebate;
	}
	public void setManufacturerRebate(String manufacturerRebate) {
		this.manufacturerRebate = manufacturerRebate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getReviewRating() {
		return reviewRating;
	}
	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	
}
