package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Like
{

    private int likeId;
    private int accountId;
    private int threadId;
    private int commentId;
    private int likeType;
    private Date createdAt;

    public Like()
    {
    }

    public Like(int likeId, int accountId, int threadId, int likeType, Date createdAt)
    {
        this.likeId = likeId;
        this.accountId = accountId;
        this.threadId = threadId;
        this.likeType = likeType;
        this.createdAt = createdAt;
    }

    public Like(int likeId, int accountId, int threadId, int commentId, int likeType, Date createdAt)
    {
        this.likeId = likeId;
        this.accountId = accountId;
        this.threadId = threadId;
        this.commentId = commentId;
        this.likeType = likeType;
        this.createdAt = createdAt;
    }

    public int getLikeId()
    {
        return likeId;
    }

    public void setLikeId(int likeId)
    {
        this.likeId = likeId;
    }

    public int getLikeType()
    {
        return likeType;
    }

    public void setLikeType(int likeType)
    {
        this.likeType = likeType;
    }

    public int getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    public int getThreadId()
    {
        return threadId;
    }

    public void setThreadId(int threadId)
    {
        this.threadId = threadId;
    }

    public int getCommentId()
    {
        return commentId;
    }

    public void setCommentId(int commentId)
    {
        this.commentId = commentId;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

//    // Other methods
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
//                System.out.println("Connected to Like");
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println("Like connect: " + e.getMessage());
//        }
//    }

    public void updateLikeForThread(String action, int accountId, String threadId)
    {
        Connection cnn = new DatabaseConnection().conn;
        updateCancelLikeForThread(accountId, threadId);
        try
        {
            
            String strAdd = "";
            if (action.equals("like"))
            {
                strAdd += "INSERT INTO onlinevotingsystem.like "
                        + "(AccountId,"
                        + "ThreadId,"
                        + "LikeType) "
                        + " VALUES (" + accountId + ", " + threadId + ", 1);";
            }
            else if (action.equals("dislike"))
            {
                strAdd += "INSERT INTO onlinevotingsystem.like "
                        + "(AccountId,"
                        + "ThreadId,"
                        + "LikeType) "
                        + " VALUES (" + accountId + ", " + threadId + ", 0);";
            }
            PreparedStatement pstm = cnn.prepareStatement(strAdd);
            pstm.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("Add like: " + e.getMessage());
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

    public void updateCancelLikeForThread(int accountId, String threadId)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            
            String strDelete = "DELETE FROM onlinevotingsystem.like where CommentId is null and AccountId=" + accountId + " and ThreadId=" + threadId;
            
            ps = cnn.prepareStatement(strDelete);
            ps.execute();
        }
        catch (Exception e)
        {
            System.out.println("Cancel like: " + e.getMessage());
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
    
    public void updateLikeForComment(String action, int accountId, String commentId, String threadId)
    {
        Connection cnn = new DatabaseConnection().conn;
        updateCancelLikeForComment(accountId, commentId);
        try
        {
            
            String strAdd = "";
            if (action.equals("likeCmt"))
            {
                strAdd += "INSERT INTO onlinevotingsystem.like "
                        + "(AccountId,"
                        + "ThreadId,"
                        + "CommentId,"
                        + "LikeType) "
                        + " VALUES (" + accountId + ", " + threadId + ", " + commentId + ", 1);";
            }
            else if (action.equals("dislikeCmt"))
            {
                strAdd += "INSERT INTO onlinevotingsystem.like "
                        + "(AccountId,"
                        + "ThreadId,"
                        + "CommentId,"
                        + "LikeType) "
                        + " VALUES (" + accountId + ", " + threadId + ", "+ commentId + ", 0);";
            }
            PreparedStatement pstm = cnn.prepareStatement(strAdd);
            pstm.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("Add like: " + e.getMessage());
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

    public void updateCancelLikeForComment(int accountId, String commentId) {
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            
            String strDelete = "DELETE FROM onlinevotingsystem.like where AccountId=" + accountId + " and CommentId=" + commentId;
            
            ps = cnn.prepareStatement(strDelete);
            ps.execute();
        }
        catch (Exception e)
        {
            System.out.println("Cancel like: " + e.getMessage());
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
}
