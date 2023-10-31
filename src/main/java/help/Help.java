package help;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Help {
    private int helpID;
    private String userID;
    private String subject;
    private String message;
    private Timestamp timestamp;
    private String replyContent;
    private Timestamp replyTimestamp;
    private String replyUserId; // New field for replyUserId

    public Help() {
    }

    public Help(String userID, String subject, String message) {
        this.userID = userID;
        this.subject = subject;
        this.message = message;
    }

    public int getHelpID() {
        return helpID;
    }

    public void setHelpID(int helpID) {
        this.helpID = helpID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Timestamp getReplyTimestamp() {
        return replyTimestamp;
    }

    public void setReplyTimestamp(Timestamp replyTimestamp) {
        this.replyTimestamp = replyTimestamp;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    @Override
    public String toString() {
        return "Help{" +
                "helpID=" + helpID +
                ", userID='" + userID + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", replyContent='" + replyContent + '\'' +
                ", replyTimestamp=" + (replyTimestamp != null ?
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(replyTimestamp) : null) +
                ", replyUserId='" + replyUserId + '\'' +
                '}';
    }
}
