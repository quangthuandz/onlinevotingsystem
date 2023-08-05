package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pin {
    private int campaignId;
    private int userId;
    private int isPinned;

    public Pin() {
    }

    public Pin(int campaignId, int userId, int isPinned) {
        this.campaignId = campaignId;
        this.userId = userId;
        this.isPinned = isPinned;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(int isPinned) {
        this.isPinned = isPinned;
    }
    
    PreparedStatement ps;
    ResultSet rs;
    
    public void updatePin(int userId, String campaignId)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            
            String strAdd = "INSERT INTO onlinevotingsystem.pinned "
                        + "(CampaignId,"
                        + "UserId,"
                        + "isPinned) "
                        + " VALUES (" + campaignId + ", " + userId + ", 1);";
            PreparedStatement pstm = cnn.prepareStatement(strAdd);
            pstm.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("Add pinned: " + e.getMessage());
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

    public void updateCancelPin(int userId, String campaignId)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            
            String strDelete = "DELETE FROM onlinevotingsystem.pinned where UserId=" + userId + " and CampaignId=" + campaignId;
            ps = cnn.prepareStatement(strDelete);
            ps.execute();
        }
        catch (Exception e)
        {
            System.out.println("Cancel pin: " + e.getMessage());
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
