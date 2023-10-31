package order;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection connection;

    public OrderDAO() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/bbs";
            String dbID = "root";
            String dbPassword = "1234";

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    // Insert an order into the database
    public void insertOrder(Order order) {
        try {
            String query = "INSERT INTO orders (userID, paymentMethod, deliveryDate, bankName, accountNumber, cardName, cardNumber) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, order.getUserID());
            preparedStatement.setString(2, order.getPaymentMethod());
            preparedStatement.setDate(3, order.getDeliveryDate());
            preparedStatement.setString(4, order.getBankName());
            preparedStatement.setString(5, order.getAccountNumber());
            preparedStatement.setString(6, order.getCardName());
            preparedStatement.setString(7, order.getCardNumber());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

    // Retrieve an order by its orderID
    public Order getOrderById(int orderID) {
        Order order = null;
        try {
            String query = "SELECT * FROM orders WHERE orderID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setInt(1, orderID);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                order = new Order();
                order.setOrderID(resultSet.getInt("orderID"));
                order.setUserID(resultSet.getString("userID"));
                order.setPaymentMethod(resultSet.getString("paymentMethod"));
                order.setDeliveryDate(resultSet.getDate("deliveryDate"));
                order.setBankName(resultSet.getString("bankName"));
                order.setAccountNumber(resultSet.getString("accountNumber"));
                order.setCardName(resultSet.getString("cardName"));
                order.setCardNumber(resultSet.getString("cardNumber"));
            }
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return order;
    }

    // Retrieve a list of all orders
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            String query = "SELECT * FROM orders";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderID(resultSet.getInt("orderID"));
                order.setUserID(resultSet.getString("userID"));
                order.setPaymentMethod(resultSet.getString("paymentMethod"));
                order.setDeliveryDate(resultSet.getDate("deliveryDate"));
                order.setBankName(resultSet.getString("bankName"));
                order.setAccountNumber(resultSet.getString("accountNumber"));
                order.setCardName(resultSet.getString("cardName"));
                order.setCardNumber(resultSet.getString("cardNumber"));
                orders.add(order);
            }
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orders;
    }
    
    // Update an order
    public void updateOrder(Order order) {
        try {
            String query = "UPDATE orders " +
                           "SET userID=?, paymentMethod=?, deliveryDate=?, bankName=?, accountNumber=?, cardName=?, cardNumber=? " +
                           "WHERE orderID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, order.getUserID());
            preparedStatement.setString(2, order.getPaymentMethod());
            preparedStatement.setDate(3, order.getDeliveryDate());
            preparedStatement.setString(4, order.getBankName());
            preparedStatement.setString(5, order.getAccountNumber());
            preparedStatement.setString(6, order.getCardName());
            preparedStatement.setString(7, order.getCardNumber());
            preparedStatement.setInt(8, order.getOrderID());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete an order by its orderID
    public void deleteOrder(int orderID) {
        try {
            String query = "DELETE FROM orders WHERE orderID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setInt(1, orderID);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Close the database connection
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
