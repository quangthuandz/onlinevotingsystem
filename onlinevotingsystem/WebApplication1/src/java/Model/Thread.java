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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**

 @author ducan
 */
public class Thread
{

    private int threadId, accountId, campaignId;
    private String title, content;
    private String timeCreate;
    private int viewCount;
    private boolean isPinned, isDeleted;
    private String imgPath;
    private final String[] bannedWord =
    {
        "fuck", "damn", "dmm", "cc", "cmm", "lồn", "cặc", "buồi", "chó", "súc vật", "cứt", "nigga",
        "đụ", "địt", "nam kỳ", "bắc kỳ", "ngu"
    };

    public Thread()
    {
    }

    public Thread(int accountId, int campaignId)
    {
        this.accountId = accountId;
        this.campaignId = campaignId;
    }

    public Thread(String content)
    {
        this.content = content;
    }

    public Thread(int threadId, int accountId, int campaignId, String title, String content, String timeCreate, int viewCount, boolean isPinned, boolean isDeleted, String imgPath)
    {
        this.threadId = threadId;
        this.accountId = accountId;
        this.campaignId = campaignId;
        this.title = title;
        this.content = content;
        this.timeCreate = timeCreate;
        this.viewCount = viewCount;
        this.isPinned = isPinned;
        this.isDeleted = isDeleted;
        this.imgPath = imgPath;
    }

    public int getThreadId()
    {
        return threadId;
    }

    public void setThreadId(int threadId)
    {
        this.threadId = threadId;
    }

    public int getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    public int getCampaignId()
    {
        return campaignId;
    }

    public void setCampaignId(int campaignId)
    {
        this.campaignId = campaignId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getTimeCreate()
    {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate)
    {
        this.timeCreate = timeCreate;
    }

    public int getViewCount()
    {
        return viewCount;
    }

    public void setViewCount(int viewCount)
    {
        this.viewCount = viewCount;
    }

    public boolean isIsPinned()
    {
        return isPinned;
    }

    public void setIsPinned(boolean isPinned)
    {
        this.isPinned = isPinned;
    }

    public boolean isIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public String getImgPath()
    {
        return imgPath;
    }

    public void setImgPath(String imgPath)
    {
        this.imgPath = imgPath;
    }

    // Other methods
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
//                System.out.println("Connected to thread");
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println("thread connect: " + e.getMessage());
//        }
//    }
    public Thread getThreadById(int threadId)
    {
        Connection cnn = new DatabaseConnection().conn;
        Thread data = new Thread();
        String sql = "SELECT "
                + "ThreadId, "
                + "AccountId, "
                + "CampaignId, "
                + "Title, "
                + "Content, "
                + "CreatedAt, "
                + "ViewCount, "
                + "IsPinned, "
                + "IsDeleted, "
                + "ImagePath"
                + " FROM onlinevotingsystem.thread "
                + "WHERE ThreadId = ?; ";
        try
        {

            ps = cnn.prepareStatement(sql);
            ps.setInt(1, threadId);
            rs = ps.executeQuery();
            while (rs.next())
            {
                threadId = rs.getInt(1);
                int accountId = rs.getInt(2);
                int campaignId = rs.getInt(3);
                String title = rs.getString(4);
                String content = rs.getString(5);
                String timeCreate = rs.getString(6);
                int viewCount = rs.getInt(7);
                boolean isPinned = rs.getBoolean(8);
                boolean isDeleted = rs.getBoolean(9);
                String imgPath = rs.getString(10);
                Thread t = new Thread(threadId, accountId, campaignId, title, content, timeCreate, viewCount, isPinned, isDeleted, imgPath);
                data = t;
            }
            return data;
        }
        catch (SQLException e)
        {
            System.out.println("getThreadById: " + e);
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

        return data;
    }

    public void createThread(int UserId, int CampaignId, String getContent, int isPinned)
    {
        Connection cnn = new DatabaseConnection().conn;
        Date date = new Date(System.currentTimeMillis());
        java.util.Date utilDate = new java.util.Date(date.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateFormat.format(utilDate);
        for (String ban : bannedWord)
        {
            if (getContent.contains(ban))
            {
                getContent = getContent.replace(ban, "****");
            }
        }
        try
        {

            String strAdd = "INSERT INTO "
                    + "onlinevotingsystem.thread"
                    + "(AccountId,"
                    + "CampaignId,"
                    + "Content,"
                    + "CreatedAt,"
                    + "Title,"
                    + "IsPinned)"
                    + "VALUES "
                    + "(?,"
                    + " ?,"
                    + " ?,"
                    + " ?,"
                    + " 'Lỗi', "
                    + isPinned + ")";
            PreparedStatement pstm = cnn.prepareStatement(strAdd);
            pstm.setInt(1, UserId);
            pstm.setInt(2, CampaignId);
            pstm.setString(3, getContent);
            pstm.setString(4, formattedDateTime);
            pstm.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("createThread: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("createThread: " + e.getMessage());
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

    public void editThread(int threadId, String getContent)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strAdd = "UPDATE "
                    + "onlinevotingsystem.thread"
                    + " SET "
                    + "Content = ? "
                    + "WHERE "
                    + "(ThreadId = ?);";
            PreparedStatement pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, getContent);
            pstm.setInt(2, threadId);
            pstm.executeUpdate();
            System.out.println("editThread success");
        }
        catch (SQLException e)
        {
            System.out.println("editThreadSQL: " + e);
        }
        catch (Exception e)
        {
            System.out.println("editThread: " + e);
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

    public ArrayList<Thread> getListThreadByCampaignId(int cId)
    {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<Thread> list = new ArrayList<>();
        try
        {

            String strSelect = "SELECT "
                    + "ThreadId,"
                    + " AccountId,"
                    + " Title,"
                    + " Content,"
                    + " CreatedAt,"
                    + " IsPinned,"
                    + " IsDeleted,"
                    + " ImagePath"
                    + " FROM onlinevotingsystem.thread "
                    + "WHERE CampaignId = ? "
                    + "ORDER BY IsPinned = 0, CreatedAt DESC;";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            while (rs.next())
            {
                int tId = rs.getInt(1);
                int aId = rs.getInt(2);
                String title = rs.getString(3);
                String content = rs.getString(4);
                String createTime = rs.getDate(5).toString();
                boolean isPinned = rs.getBoolean(6);
                boolean isDeleted = rs.getBoolean(7);
                String imgPath = rs.getString(8);

                Thread t = new Thread(tId, aId, cId, title, content, createTime, 0, isPinned, isDeleted, imgPath);

                list.add(t);
            }
        }
        catch (Exception e)
        {
            System.out.println("getListThreadByCampaignId: " + e.getMessage());
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
        return list;
    }
    
    public ArrayList<Thread> getListThreadByCampaignIdAndSort(int cId, String sort_filter) {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<Thread> list = new ArrayList<>();
        try
        {
            String strSelect = "";
            if(sort_filter.equals("time"))
                strSelect = "SELECT "
                    + "ThreadId,"
                    + " AccountId,"
                    + " Title,"
                    + " Content,"
                    + " CreatedAt,"
                    + " IsPinned,"
                    + " IsDeleted,"
                    + " ImagePath"
                    + " FROM onlinevotingsystem.thread "
                    + "WHERE CampaignId = " + cId
                    + " ORDER BY CreatedAt DESC;";
            else if(sort_filter.equals("like"))
                strSelect = "(SELECT "
                    + "thread.ThreadId,"
                    + " thread.AccountId,"
                    + " thread.Title,"
                    + " thread.Content,"
                    + " thread.CreatedAt,"
                    + " thread.IsPinned,"
                    + " thread.IsDeleted,"
                    + " thread.ImagePath,"
                    + " COUNT(*) as Likes"
                    + " FROM onlinevotingsystem.thread JOIN onlinevotingsystem.like ON `like`.ThreadId = thread.ThreadId "
                    + "WHERE like.CommentId is null and thread.CampaignId = " + cId
                    + " GROUP BY thread.ThreadID ORDER BY Likes DESC) \n"
                    + "UNION \n"
                    + "(SELECT "
                    + "ThreadId,"
                    + " AccountId,"
                    + " Title,"
                    + " Content,"
                    + " CreatedAt,"
                    + " IsPinned,"
                    + " IsDeleted,"
                    + " ImagePath,"
                    + " 0 as Likes"
                    + " FROM onlinevotingsystem.thread "
                    + "WHERE thread.ThreadId not in (select threadId from onlinevotingsystem.like) and CampaignId = " + cId
                    + " ORDER BY CreatedAt DESC)" ;
            else
                strSelect = "(SELECT "
                    + "thread.ThreadId,"
                    + " thread.AccountId,"
                    + " thread.Title,"
                    + " thread.Content,"
                    + " thread.CreatedAt,"
                    + " thread.IsPinned,"
                    + " thread.IsDeleted,"
                    + " thread.ImagePath,"
                    + " COUNT(*) as Comments"
                    + " FROM onlinevotingsystem.thread JOIN onlinevotingsystem.like ON comment.ThreadId = thread.ThreadId "
                    + "WHERE thread.CampaignId = " + cId
                    + " GROUP BY thread.ThreadID ORDER BY Comments DESC) \n"
                    + "UNION \n"
                    + "(SELECT "
                    + "ThreadId,"
                    + " AccountId,"
                    + " Title,"
                    + " Content,"
                    + " CreatedAt,"
                    + " IsPinned,"
                    + " IsDeleted,"
                    + " ImagePath,"
                    + " 0 as Comments"
                    + " FROM onlinevotingsystem.thread "
                    + "WHERE thread.ThreadId not in (select threadId from onlinevotingsystem.comment) and CampaignId = " + cId
                    + " ORDER BY CreatedAt DESC)" ;
            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next())
            {
                int tId = rs.getInt(1);
                int aId = rs.getInt(2);
                String title = rs.getString(3);
                String content = rs.getString(4);
                String createTime = rs.getDate(5).toString();
                boolean isPinned = rs.getBoolean(6);
                boolean isDeleted = rs.getBoolean(7);
                String imgPath = rs.getString(8);

                Thread t = new Thread(tId, aId, cId, title, content, createTime, 0, isPinned, isDeleted, imgPath);

                list.add(t);
            }
        }
        catch (Exception e)
        {
            System.out.println("getListThreadByCampaignId: " + e.getMessage());
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
        return list;
    }

    public boolean isLiked(int accountId, int threadId)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strSelect = "SELECT * from onlinevotingsystem.like where AccountId = " + accountId + " and ThreadId = " + threadId + " and LikeType = 1 and CommentId is null";

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

    public boolean isDisliked(int accountId, int threadId)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strSelect = "SELECT * from onlinevotingsystem.like where AccountId = " + accountId + " and ThreadId = " + threadId + " and LikeType = 0 and CommentId is null";

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

    public int getLikeByThreadId(String threadId)
    {
        Connection cnn = new DatabaseConnection().conn;
        int amountLike = 0;
        try
        {

            String strSelect = "SELECT LikeId from onlinevotingsystem.like where ThreadId = " + threadId + " and LikeType = 1 and commentId is null";

            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next())
            {
                amountLike++;
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

    public int getDislikeByThreadId(String threadId)
    {
        Connection cnn = new DatabaseConnection().conn;
        int amountDislike = 0;
        try
        {

            String strSelect = "SELECT LikeId from onlinevotingsystem.like where ThreadId = " + threadId + " and LikeType = 0 and CommentId is null";

            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next())
            {
                amountDislike++;
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

    //count total thread of website
    public int getThreadCount()
    {
        int threadCount = 0;
        String query = "SELECT "
                + "COUNT(*) "
                + "AS "
                + "Count "
                + "FROM "
                + "THREAD ";
        try
        {
            Connection cnn = new DatabaseConnection().conn;
            PreparedStatement ps = cnn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                threadCount = rs.getInt("Count");
            }

            rs.close();
            ps.close();
            cnn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return threadCount;
    }

    public void removeThread(String threadId)
    {
        try
        {
            String strDelete = "UPDATE onlinevotingsystem.thread SET isDeleted = 1 where ThreadId=" + threadId;
            Connection cnn = new DatabaseConnection().conn;
            ps = cnn.prepareStatement(strDelete);
            ps.execute();
            cnn.close();
        }
        catch (Exception e)
        {
            System.out.println("deleteThread: " + e.getMessage());
        }
    }
    
    public int countThreadInCampaign(int threadId)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String strSelect = "select count(ThreadId) from thread where CampaignId = ?";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, threadId);
            rs = ps.executeQuery();
            while (rs.next())
            {
                return rs.getInt(1);
            }

        }
        catch (Exception e)
        {
            System.out.println("countThreadInCampaign: " + e.getMessage());
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
}
