package order;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Order {
	private int orderID;
	private String userID;
	private String paymentMethod;
	private Date deliveryDate;
	private String bankName;
	private String accountNumber;
	private String cardName;
	private String cardNumber;
	private String productImage;
	private String productName;
	private BigDecimal productPrice;

	public Order(String userID, String paymentMethod, Date deliveryDate,
            String bankName, String accountNumber,
            String cardName, String cardNumber,
            String productName,
            String productImage,
            BigDecimal productPrice) {}
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getUserID() {
		return userID;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Order() {
		// Default constructor
	}

	public Order(String userID, String paymentMethod, Date deliveryDate, String bankName, String accountNumber,
			String cardName, String cardNumber) {
		this.userID = userID;
		this.paymentMethod = paymentMethod;
		this.deliveryDate = deliveryDate;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.cardName = cardName;
		this.cardNumber = cardNumber;
	}

	// Other getters and setters...

	// Add this method to format the delivery date as a string
	public String getFormattedDeliveryDate() { 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(this.deliveryDate);
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", userID=" + userID + ", paymentMethod=" + paymentMethod
				+ ", deliveryDate=" + deliveryDate + ", bankName=" + bankName + ", accountNumber=" + accountNumber
				+ ", cardName=" + cardName + ", cardNumber=" + cardNumber + "]";
	}
}
