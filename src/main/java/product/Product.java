package product;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Product {
	private int productID;
	private String userID;
	private String productName;
	private String productDescription;
	private BigDecimal productPrice;
	private String productCategory;
	private String productImage;
	private Timestamp productDate;
	private String productSize;
	private int stockQuantity;
	private List<String> productSizes;

	// Default constructor
	public Product() {
		this.productDate = new Timestamp(new Date().getTime());
	}

	public Product(String userID, String productName, String productDescription, BigDecimal productPrice,
			String productCategory, int stockQuantity, String productImage, Timestamp productDate) {
		this.userID = userID;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.stockQuantity = stockQuantity;
		this.productImage = productImage;
		this.productDate = (productDate == null) ? new Timestamp(new Date().getTime()) : productDate;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Timestamp getProductDate() {
		return productDate;
	}

	public void setProductDate(Timestamp productDate) {
		this.productDate = productDate;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	// 상품 크기 목록을 설정하는 메서드
	public void setProductSizes(List<String> productSizes) {
		this.productSizes = productSizes;
	}

	// 상품 크기 목록을 가져오는 메서드
	public List<String> getProductSizes() {
		return productSizes;
	}

	// 새로운 생성자 추가
	public Product(String userID, String productName, String productDescription, BigDecimal productPrice,
			String productCategory, int stockQuantity, String productImage, Timestamp timestamp, String productSize) {
		this.userID = userID;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.stockQuantity = stockQuantity;
		this.productImage = productImage;
		this.productDate = (timestamp == null) ? new Timestamp(new Date().getTime()) : timestamp; 
		this.productSize = productSize;
	}
}
