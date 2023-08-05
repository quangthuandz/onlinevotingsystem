/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**

 @author Hanh
 */
public class CommentPost
{

    int CommentId, ThreadId, AccountId, ParentCommentId;
    String Content, CreatedAt;

    public CommentPost()
    {
    }

    public CommentPost(int CommentId, int ThreadId, int AccountId, String Content, String CreatedAt, int ParentCommentId)
    {
        this.CommentId = CommentId;
        this.ThreadId = ThreadId;
        this.AccountId = AccountId;
        this.Content = Content;
        this.CreatedAt = CreatedAt;
        this.ParentCommentId = ParentCommentId;
    }

    public CommentPost(int CommentId, int ThreadId)
    {
        this.CommentId = CommentId;
        this.ThreadId = ThreadId;
    }

    public CommentPost(int CommentId)
    {
        this.CommentId = CommentId;
    }

    public int getCommentId()
    {
        return CommentId;
    }

    public void setCommentId(int CommentId)
    {
        this.CommentId = CommentId;
    }

    public int getThreadId()
    {
        return ThreadId;
    }

    public void setThreadId(int ThreadId)
    {
        this.ThreadId = ThreadId;
    }

    public int getAccountId()
    {
        return AccountId;
    }

    public void setAccountId(int AccountId)
    {
        this.AccountId = AccountId;
    }

    public int getParentCommentId()
    {
        return ParentCommentId;
    }

    public void setParentCommentId(int ParentCommentId)
    {
        this.ParentCommentId = ParentCommentId;
    }

    public String getContent()
    {
        return Content;
    }

    public void setContent(String Content)
    {
        this.Content = Content;
    }

    public String getCreatedAt()
    {
        return CreatedAt;
    }

    public void setCreatedAt(String CreatedAt)
    {
        this.CreatedAt = CreatedAt;
    }

//    DatabaseConnection db = new DatabaseConnection();
//    Connection cnn;
    PreparedStatement ps;
    ResultSet rs;

//    private void connect()
//    {
//        try
//        {
//            cnn = db.getConnection();
//            if (cnn != null)
//            {
//                System.out.println("Connected to Comment");
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println("Comment connect: " + e.getMessage());
//        }
//    }
    public void updateCommentContent(int commentId, String EditContent)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String updateQuery = "UPDATE comment SET content = ? WHERE commentid = ?";
            PreparedStatement ps = cnn.prepareStatement(updateQuery);
            ps.setString(1, EditContent);
            ps.setInt(2, commentId);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Error updating comment content: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public CommentPost CheckComment(int CommentId, String EditComment)
    {
        Connection cnn = new DatabaseConnection().conn;
        CommentPost comment = new CommentPost();
        try
        {
            String strSelect = "select * from comment where commentid = ?";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, CommentId);
            rs = ps.executeQuery();
            while (rs.next())
            {
                comment = new CommentPost(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6));
            }
        }
        catch (SQLException e)
        {
            System.out.println("CheckComment: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return comment;
    }

    public void AddComment(int ThreadId, int AccountId, String content, String CreatedAt)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strSelect = "INSERT INTO Comment (threadid, accountid, content, createdat) "
                    + "VALUES (?, ?, ?, ?);";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, ThreadId);
            ps.setInt(2, AccountId);
            ps.setString(3, content);
            ps.setString(4, CreatedAt);
            ps.executeUpdate();
            cnn.close();

        }
        catch (SQLException e)
        {
            System.out.println("AddComment: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void DeleteComment()
    {
        Connection cnn = new DatabaseConnection().conn;

        try
        {
            String strSelect = "delete from comment"
                    + " where CommentId = ? and ThreadId = ?;";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, CommentId);
            ps.setInt(2, ThreadId);
            ps.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("DeleteComment: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void UpdateComment(int CommentId, int ThreadId, String Content, String CreatedAt, int ParentCommentId)
    {
        Connection cnn = new DatabaseConnection().conn;

        try
        {
            String strSelect = "";
            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();

        }
        catch (SQLException e)
        {
            System.out.println("CheckComment: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // Hàm tính thời gian chênh lệch từ thời gian đã comment đến thời gian hiện tại
    public long calculateTimeDifference(String createdAt) {
        LocalDateTime commentTime = LocalDateTime.parse(createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime currentTime = LocalDateTime.now();
        return ChronoUnit.MINUTES.between(commentTime, currentTime);
    }

    // Hàm hiển thị số thời gian đã comment dưới dạng phút, giờ hoặc ngày
    public String displayTimeSinceComment(String createdAt) {
        long timeDifferenceInMinutes = calculateTimeDifference(createdAt);

        if (timeDifferenceInMinutes < 60) {
            return timeDifferenceInMinutes + " minutes ago";
        } else if (timeDifferenceInMinutes >= 60 && timeDifferenceInMinutes < 1440) {
            long timeDifferenceInHours = timeDifferenceInMinutes / 60;
            return timeDifferenceInHours + " hours ago";
        } else {
            long timeDifferenceInDays = timeDifferenceInMinutes / 1440;
            return timeDifferenceInDays + " days ago";
        }
    }
    public List<CommentPost> DisplayComment()
    {
        Connection cnn = new DatabaseConnection().conn;

        List<CommentPost> CommentList = new ArrayList<>();
        try
        {
            String strSelect = "select CommentId, ThreadId, AccountId, content, CreatedAt , ParentCommentId from comment";
            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String createdAt = rs.getString(5);
                String timeSinceComment = displayTimeSinceComment(createdAt);
                CommentPost comment = new CommentPost(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), timeSinceComment, rs.getInt(6));
                CommentList.add(comment);
            }

        }
        catch (SQLException e)
        {
            System.out.println("CheckAccount: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return CommentList;
    }

    public void replyComment(int AccountId, int ThreadId, String Content, int ParentCommentId)
    {
        Date date = new Date(System.currentTimeMillis());
        java.util.Date utilDate = new java.util.Date(date.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateFormat.format(utilDate);
        Connection cnn = new DatabaseConnection().conn;

        try
        {
            String strSelect = "INSERT INTO "
                    + "onlinevotingsystem.comment"
                    + " (AccountId, "
                    + "ThreadId, "
                    + "Content"
                    + ", CreatedAt"
                    + ", ParentCommentId) "
                    + "VALUES "
                    + "(?,"
                    + " ?,"
                    + " ?,"
                    + " ?,"
                    + " ?);";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, AccountId);
            ps.setInt(2, ThreadId);
            ps.setString(3, Content);
            ps.setString(4, formattedDateTime);
            ps.setInt(5, ParentCommentId);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("replyComment: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean isLiked(int accountId, int commentId)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String strSelect = "SELECT * from onlinevotingsystem.like where AccountId = " + accountId + " and CommentId = " + commentId + " and LikeType = 1";
            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next())
            {
                return true;
            }
        }
        catch (Exception e)
        {
            System.out.println("getLikeByAccount: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean isDisliked(int accountId, int commentId)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strSelect = "SELECT * from onlinevotingsystem.like where AccountId = " + accountId + " and CommentId = " + commentId + " and LikeType = 0";

            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next())
            {
                return true;
            }
        }
        catch (Exception e)
        {
            System.out.println("getLikeByAccount: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public int getLikeByCommentId(String commentId)
    {
        Connection cnn = new DatabaseConnection().conn;
        int amountLike = 0;
        try
        {

            String strSelect = "SELECT COUNT(*), CommentId from onlinevotingsystem.like where CommentId = " + commentId + " and LikeType = 1 GROUP BY CommentId;";

            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next())
            {
                amountLike = rs.getInt(1);
            }

        }
        catch (Exception e)
        {
            System.out.println("getLike: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return amountLike;
    }

    public int getDislikeByCommentId(String commentId)
    {
        Connection cnn = new DatabaseConnection().conn;
        int amountDislike = 0;
        try
        {

            String strSelect = "SELECT COUNT(*), CommentId from onlinevotingsystem.like where CommentId = " + commentId + " and LikeType = 0 GROUP BY CommentId";

            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next())
            {
                amountDislike = rs.getInt(1);
            }

        }
        catch (Exception e)
        {
            System.out.println("getDislike: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return amountDislike;
    }

    //count total comment of website
    public int getCommentCount()
    {
        int commentCount = 0;
        String query = "SELECT "
                + "COUNT(*) "
                + "AS "
                + "Count "
                + "FROM "
                + "COMMENT ";
        try
        {
            Connection cnn = new DatabaseConnection().conn;
            PreparedStatement ps = cnn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                commentCount = rs.getInt("Count");
            }

            rs.close();
            ps.close();
            cnn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return commentCount;
    }

    public int countCommentInThread(int commentId)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String updateQuery = "select count(CommentId) from comment where ThreadId in (select ThreadId from Thread where CampaignId = ?)";
            PreparedStatement ps = cnn.prepareStatement(updateQuery);
            ps.setInt(1, commentId);
            rs = ps.executeQuery();
            while (rs.next())
            {
                return rs.getInt(1);
            }

        }
        catch (SQLException e)
        {
            System.out.println("countCommentInThread: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    public void SelectCommentId(int AccountId, int Tid)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String StrSelect = "select CommentId from comment\n"
                    + "where AccountId = ? and ThreadId = ? \n"
                    + "and ParentCommentId is Null\n"
                    + "Order by CreatedAt Desc limit 1";
            ps = cnn.prepareStatement(StrSelect);
            ps.setInt(1, AccountId);
            ps.setInt(2, Tid);
            rs = ps.executeQuery();
            while (rs.next())
            {
                this.CommentId = rs.getInt(1);
            }
        }
        catch (Exception e)
        {
            System.out.println("Select Comment: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(CommentPost.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void SelectReplyId(int AccountId, int Tid, int parentId)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String StrSelect = "select AccountId,CommentId from comment\n"
                    + "where AccountId = ? and ThreadId = ? \n"
                    + "and ParentCommentId = ? \n"
                    + "Order by CreatedAt Desc limit 1";
            ps = cnn.prepareStatement(StrSelect);
            ps.setInt(1, AccountId);
            ps.setInt(2, Tid);
            ps.setInt(3, parentId);
            rs = ps.executeQuery();
            while (rs.next())
            {
                this.AccountId = rs.getInt(1);
                this.CommentId = rs.getInt(2);
            }
        }
        catch (Exception e)
        {
            System.out.println("Select Comment: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(CommentPost.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void GetAccountIdByCommentId(String CommentId)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String StrSelect = "select * from comment "
                    + "where CommentId = ? ;";
            ps = cnn.prepareStatement(StrSelect);
            ps.setInt(1, Integer.parseInt(CommentId));
            rs = ps.executeQuery();
            while (rs.next())
            {
                this.AccountId = rs.getInt(2);
            }
        }
        catch (Exception e)
        {
            System.out.println("Select Comment: " + e.getMessage());
        }
        finally
        {
            if (cnn != null)
            {
                try
                {
                    cnn.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(CommentPost.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
