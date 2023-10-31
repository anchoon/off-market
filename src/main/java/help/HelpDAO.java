package help;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HelpDAO {
    private Connection conn;

    public HelpDAO() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/BBS";
            String dbID = "root";
            String dbPassword = "1234";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addHelp(Help help) {
        String SQL = "INSERT INTO help (userID, subject, message) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, help.getUserID());
            pstmt.setString(2, help.getSubject());
            pstmt.setString(3, help.getMessage());

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Help getHelpRecord(String helpID) {
        Help help = null;
        String SQL = "SELECT * FROM help WHERE helpID=?";
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, helpID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                help = new Help();
                help.setHelpID(rs.getInt("helpID"));
                help.setUserID(rs.getString("userID"));
                help.setSubject(rs.getString("subject"));
                help.setMessage(rs.getString("message"));
                help.setTimestamp(rs.getTimestamp("timestamp"));

                help.setReplyContent(rs.getString("replyContent")); // Added to retrieve reply content
                Timestamp replyTimestamp = rs.getTimestamp("replyTimestamp");
                if (replyTimestamp != null)
                    help.setReplyTimestamp(replyTimestamp); // Added to retrieve reply timestamp
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return help;
    }

    public List<Help> getAllHelpRecords() {
        List<Help> helpList = new ArrayList<>();
        String SQL = "SELECT * FROM help";
        try (PreparedStatement pstmt = conn.prepareStatement(SQL); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Help help = new Help();
                help.setHelpID(rs.getInt("helpID"));
                help.setUserID(rs.getString("userID"));
                help.setSubject(rs.getString("subject"));
                help.setMessage(rs.getString("message"));
                help.setTimestamp(rs.getTimestamp("timestamp"));

                help.setReplyContent(rs.getString("replyContent")); // Added to retrieve reply content
                help.setReplyTimestamp(rs.getTimestamp("replyTimestamp")); // Added to retrieve reply timestamp
                helpList.add(help);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return helpList;
    }

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateHelpRecord(String helpId, String userId, String subject, String message) {
        int result;
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE help SET userID=?, subject=?, message=? WHERE helpID=?");
            ps.setString(1, userId);
            ps.setString(2, subject);
            ps.setString(3, message);
            ps.setString(4, helpId);

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating help record: " + e.getMessage());
            return false;
        }

        return result > 0;
    }

    public boolean deleteHelpRecord(String helpId) {
        int result;
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM help WHERE helpID=?");
            ps.setString(1, helpId);

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting help record: " + e.getMessage());
            return false;
        }

        return result > 0;
    }

    public boolean addReply(int helpId, String userId, String replyContent) {
        String SQL = "UPDATE help SET replyUserId = ?, replyContent = ?, replyTimestamp = NOW(), status = 'answered' WHERE helpID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, replyContent);
            pstmt.setInt(3, helpId);

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateHelpStatus(int helpId, String status) {
        String SQL = "UPDATE help SET status = ? WHERE helpID = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, status);
            pstmt.setInt(2, helpId);

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
 