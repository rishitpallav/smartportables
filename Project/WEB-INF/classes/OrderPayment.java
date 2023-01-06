import java.io.IOException;
import java.util.Date;
import java.io.*;

/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class OrderPayment implements Serializable {
	private int orderId;
	private String userName;
	private String customerName;
	private String orderName;
	private double orderPrice;
	private String userAddress;
	private String creditCardNo;
	private String inStoreOrDelivery;
	private String pincode;
	private Date dateOrdered;
	private Date shipDate;
	private String category;
	private int quantity;
	private String storeId;
	private String storeAddress;

	public OrderPayment(int orderId, String userName, String orderName, double orderPrice, String userAddress,
			String creditCardNo, String inStoreOrDelivery, String pincode, Date dateOrdered) {
		this.orderId = orderId;
		this.userName = userName;
		this.orderName = orderName;
		this.orderPrice = orderPrice;
		this.userAddress = userAddress;
		this.creditCardNo = creditCardNo;
		this.inStoreOrDelivery = inStoreOrDelivery;
		this.pincode = pincode;
		this.dateOrdered = dateOrdered;
	}

	public OrderPayment(int orderId, String userName, String customerName, String orderName, double orderPrice, String userAddress,
			String creditCardNo, String inStoreOrDelivery, String pincode, Date dateOrdered, Date shipDate, String category, int quantity, 
			String storeId, String storeAddress) {
		this.orderId = orderId;
		this.userName = userName;
		this.customerName = customerName;
		this.orderName = orderName;
		this.orderPrice = orderPrice;
		this.userAddress = userAddress;
		this.creditCardNo = creditCardNo;
		this.inStoreOrDelivery = inStoreOrDelivery;
		this.pincode = pincode;
		this.dateOrdered = dateOrdered;
		this.shipDate = shipDate;
		this.category = category;
		this.quantity = quantity;
		this.storeId = storeId;
		this.storeAddress = storeAddress;
	}


	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getInStoreOrDelivery() {
		return inStoreOrDelivery;
	}

	public void setInStoreOrDelivery(String inStoreOrDelivery) {
		this.inStoreOrDelivery = inStoreOrDelivery;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

}
