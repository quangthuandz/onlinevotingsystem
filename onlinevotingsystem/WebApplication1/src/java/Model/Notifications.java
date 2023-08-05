/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class Notifications {

    private int notificationId, receiverId, PostId, commentId, senderId, campaignId;
    private String content, timestamp;
    private boolean isRead;

    PreparedStatement ps;
    ResultSet rs;

    public Notifications() {
    }

    public Notifications(int notificationId, int receiverId, int PostId, int commentId, int CampaignId, int senderId, String content, String timestamp, boolean isRead) {
        this.notificationId = notificationId;
        this.receiverId = receiverId;
        this.PostId = PostId;
        this.commentId = commentId;
        this.campaignId = campaignId;
        this.senderId = senderId;
        this.content = content;
        this.timestamp = timestamp;
        this.isRead = isRead;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getPostId() {
        return PostId;
    }

    public void setPostId(int PostId) {
        this.PostId = PostId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getType() {
        return content;
    }

    public void setType(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public void AddLikeThreadNotification(int Id, String action, int Tid, int Sid) {
        Connection cnn = new DatabaseConnection().conn;
        try {
            String content = " liked your thread ";
            String StrAdd = "insert into notification (receiverId,content,postId,senderId) "
                    + "values (?,?,?,?) ;";
            ps = cnn.prepareStatement(StrAdd);
            ps.setInt(1, Id);
            ps.setString(2, content);
            ps.setInt(3, Tid);
            ps.setInt(4, Sid);
            ps.execute();
        } catch (Exception e) {
            System.out.println("addNotification: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void AddCommentRelatedNotification(int Id, String action, int Tid, int Cid, int Sid) {
        Connection cnn = new DatabaseConnection().conn;
        try {
            String content = "";
            if (action.equals("comment")) {
                content = " comment on your thread ";
            }
            if (action.equals("reply")) {
                content = " replied to your comment ";
            }
            if (action.equals("likeCmt")) {
                content = " liked to your comment ";
            }
            String StrAdd = "insert into notification "
                    + "(receiverId, content, postId, commentId, senderId) "
                    + "values (?,?,?,?,?) ;";
            ps = cnn.prepareStatement(StrAdd);
            ps.setInt(1, Id);
            ps.setString(2, content);
            ps.setInt(3, Tid);
            ps.setInt(4, Cid);
            ps.setInt(5, Sid);
            ps.execute();
        } catch (Exception e) {
            System.out.println("addNotification: " + e.getMessage());
        } finally {

        }
    }

    public String ShowAllNotification(int Uid) {
        Connection cnn = new DatabaseConnection().conn;

        String result = "";

        try {

            String StrSelect = "select a.Username,n.content,n.timestamp\n"
                    + "from onlinevotingsystem.account a join notification n\n"
                    + "where n.receiverId = ? and a.UserId = n.senderId\n"
                    + "order by n.timestamp desc limit 100;";
            ps = cnn.prepareStatement(StrSelect);
            ps.setInt(1, Uid);
            rs = ps.executeQuery();
            StringBuilder output = new StringBuilder();

            while (rs.next()) {
                String name = rs.getString(1);
                String content = rs.getString(2);
                Timestamp timeStamp = rs.getTimestamp(3);
                String displayTime = "";

                Date d = new Date();

                long timeElapsedMillis = d.getTime() - timeStamp.getTime();
                long seconds = timeElapsedMillis / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;

                if (days > 0) {
                    displayTime = days + "d ago";
                } else if (hours > 0) {
                    displayTime = hours + "h ago";
                } else if (minutes > 0) {
                    displayTime = minutes + "m ago";
                } else {
                    displayTime = "Just now";
                }
                output.append("<li>");
                output.append("<strong>").append(name).append("</strong><br />");
                output.append("<small><em>").append(content).append("</em></small><br>");
                output.append("<small><em>").append(displayTime).append("</em></small>");
                output.append("</li>");
            }
            if (output.length() == 0) {
                output.append("<li><a href=\"#\" class=\"text-bold text-italic\">No Notification was Found</a></li>");
            }

            result = output.toString();

        } catch (Exception e) {
            System.out.println("SelectNotification: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public String ShowNewNotification(int Uid) {
        Connection cnn = new DatabaseConnection().conn;
        String result = "";
        try {

            String StrSelect = "select a.Username,n.content,n.timestamp\n"
                    + "from onlinevotingsystem.account a join notification n\n"
                    + "where n.receiverId = ? and a.UserId = n.senderId\n"
                    + "order by n.timestamp desc limit 8 ;";
            ps = cnn.prepareStatement(StrSelect);
            ps.setInt(1, Uid);
            rs = ps.executeQuery();
            StringBuilder output = new StringBuilder();
            while (rs.next()) {
                String name = rs.getString(1);
                String content = rs.getString(2);
                Timestamp timeStamp = rs.getTimestamp(3);
                String displayTime = "";

                Date d = new Date();

                long timeElapsedMillis = d.getTime() - timeStamp.getTime();
                long seconds = timeElapsedMillis / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;

                if (days > 0) {
                    displayTime = days + "d ago";
                } else if (hours > 0) {
                    displayTime = hours + "h ago";
                } else if (minutes > 0) {
                    displayTime = minutes + "m ago";
                } else {
                    displayTime = "Just now";
                }
                output.append("<li>");
                output.append("<strong>").append(name).append("</strong><br />");
                output.append("<small><em>").append(content).append("</em></small><br>");
                output.append("<small><em>").append(displayTime).append("</em></small>");
                output.append("</li>");
            }
            if (output.length() == 0) {
                output.append("<li><a href=\"#\" class=\"text-bold text-italic\">No Notification was Found</a></li>");
            }
            result = output.toString();
        } catch (Exception e) {
            System.out.println("SelectNotification: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public int NumberOfNewNotification(int Uid) {
        Connection cnn = new DatabaseConnection().conn;
        int count = 0;
        try {
            String strCount = "select count(isRead) from notification\n"
                    + "where isRead = 0 and receiverId = ?;";
            ps = cnn.prepareStatement(strCount);
            ps.setInt(1, Uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Number of New Notifications: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return count;
    }

    public void DeleteCommentRelatedNotification(int postId, int CommentId, String type) {
        Connection cnn = new DatabaseConnection().conn;

        try {
            String action = "";
            if (type.equals("comment")) {
                action = " comment on your post ";
            }
            if (type.equals("reply")) {
                action = " replied to your comment ";
            }
            if (type.equals("dislikeCmt") || type.equals("unlikeCmt")) {
                action = " liked to your comment ";
            }
            String StrDel = "Delete from notification where "
                    + "postId = ? and commentId = ? and content= ?;";
            ps = cnn.prepareStatement(StrDel);
            ps.setInt(1, postId);
            ps.setInt(2, CommentId);
            ps.setString(3, action);
            ps.execute();
        } catch (Exception e) {
            System.out.println("DeleteNotification: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void DeleteThreadLikeNotification(int postId, int Sid) {

        Connection cnn = new DatabaseConnection().conn;
        try {

            String StrDel = "Delete from notification where "
                    + "content = ' liked your post ' and postId = ? and senderId = ? ;";
            ps = cnn.prepareStatement(StrDel);
            ps.setInt(1, postId);
            ps.setInt(2, Sid);
            ps.execute();
        } catch (Exception e) {
            System.out.println("DeleteNotification: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void UpdateNotificationStatus(int Uid) {
        Connection cnn = new DatabaseConnection().conn;
        try {
            String StrUpdate = "update notification "
                    + "set isRead = 1 "
                    + "where isRead = 0 and receiverId = ?";
            ps = cnn.prepareStatement(StrUpdate);
            ps.setInt(1, Uid);
            ps.execute();
        } catch (Exception e) {
            System.out.println("DeleteNotification: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void AddVoterNotification(int receiverId, int senderId, String CampaignName){
        Connection cnn = new DatabaseConnection().conn;
        String content = "Added you to " + CampaignName;
        try {
            String StrAdd = "insert into notification(receiverId, content, senderId) "
                    + "values (?,?,?) ;";
            ps = cnn.prepareStatement(StrAdd);
            ps.setInt(1, receiverId);
            ps.setString(2, content);
            ps.setInt(3, senderId);
            ps.execute();
        } catch (Exception e) {
            System.out.println("DeleteNotification: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
