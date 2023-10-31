package product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDAO {
	private String dbURL = "jdbc:mysql://localhost:3306/BBS";
	private String dbID = "root";
	private String dbPassword = "1234";

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public List<Product> getAllProducts() {
		List<Product> productList = new ArrayList<>();
		String sql = "SELECT * FROM product";

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Product product = createProductFromResultSet(rs);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productList;
	}

	public boolean addProduct(Product product) {
		String sql = "INSERT INTO product (userID, productName, productDescription, productPrice, productCategory, productImage, productDate, productSize, stockQuantity) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			setProductParameters(product, pstmt);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Product getProductById(int productID) {
		String sql = "SELECT * FROM product WHERE productID = ?";
		Product product = null;

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, productID);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				product = createProductFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
	}

	public Product getProductByIdWithStockQuantity(int productID) {
		// This method is essentially the same as getProductById.
		return getProductById(productID);
	}

	public boolean setProductSizes(int productID, List<String> productSizes) {
		String sql = "UPDATE product SET productSize = ? WHERE productID = ?";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			String productSizeString = String.join(",", productSizes);
			pstmt.setString(1, productSizeString);
			pstmt.setInt(2, productID);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private Product createProductFromResultSet(ResultSet rs) throws SQLException {
		Product product = new Product();
		product.setProductID(rs.getInt("productID"));
		product.setUserID(rs.getString("userID"));
		product.setProductName(rs.getString("productName"));
		product.setProductDescription(rs.getString("productDescription"));
		product.setProductPrice(rs.getBigDecimal("productPrice"));
		product.setProductCategory(rs.getString("productCategory"));
		product.setProductImage(rs.getString("productImage"));
		product.setProductDate(rs.getTimestamp("productDate"));
		product.setProductSize(rs.getString("productSize"));
		product.setStockQuantity(rs.getInt("stockQuantity"));
		return product;
	}

	private void setProductParameters(Product product, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, product.getUserID());
		pstmt.setString(2, product.getProductName());
		pstmt.setString(3, product.getProductDescription());
		pstmt.setBigDecimal(4, product.getProductPrice());
		pstmt.setString(5, product.getProductCategory());
		pstmt.setString(6, product.getProductImage());

		if (product.getProductDate() != null) {
			pstmt.setTimestamp(7, new Timestamp(product.getProductDate().getTime()));
		} else {
			pstmt.setTimestamp(7, new Timestamp(new Date().getTime()));
		}

		pstmt.setString(8, product.getProductSize());
		pstmt.setInt(9, product.getStockQuantity());
	}

	public void updateProduct(Product product) {
	}
	// You can implement the updateProduct method here.

	public boolean deleteProductById(int productId) {
		String sql = "DELETE FROM product WHERE productID = ?";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, productId);

			int affectedRows = pstmt.executeUpdate();

			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
 
}
