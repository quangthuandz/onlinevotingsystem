package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CampaignAccount
{

    private int id;
    private int campaignId;
    private int accountId;
    private boolean host;
    private String votingResult;

    public CampaignAccount()
    {
    }

    public CampaignAccount(int campaignId, int accountId, boolean host, String votingResult)
    {
        this.campaignId = campaignId;
        this.accountId = accountId;
        this.host = host;
        this.votingResult = votingResult;
    }

    public CampaignAccount(int cId, int uId)
    {
        this.campaignId = cId;
        this.accountId = uId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCampaignId()
    {
        return campaignId;
    }

    public void setCampaignId(int campaignId)
    {
        this.campaignId = campaignId;
    }

    public int getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    public boolean isHost()
    {
        return host;
    }

    public void setHost(boolean host)
    {
        this.host = host;
    }

    public String getVotingResult()
    {
        return votingResult;
    }

    public void setVotingResult(String votingResult)
    {
        this.votingResult = votingResult;
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
//                System.out.println("Connected to CampaignAccount");
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println("CampaignAccount connect: " + e.getMessage());
//        }
//    }
    public void add(CampaignAccount ca)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strAdd = "INSERT INTO `onlinevotingsystem`.`campaignaccount` (`CampaignId`, `AccountId`, `IsHost`) VALUES (?, ?, '0');";
            ps = cnn.prepareStatement(strAdd);
            ps.setInt(1, campaignId);
            ps.setInt(2, accountId);
            ps.execute();

        }
        catch (Exception e)
        {
            System.out.println("Add CampaignAccount: " + e.getMessage());
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

    public void CheckHost()
    {
        Connection cnn = new DatabaseConnection().conn;
        
        try
        {
            
            String strSelect = "Select * From onlinevotingsystem.campaignaccount where CampaignId = ? and AccountId = ?;";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, campaignId);
            ps.setInt(2, accountId);
            rs = ps.executeQuery();
            while (rs.next())
            {
                this.host = rs.getBoolean(4);
            }
        }
        catch (Exception e)
        {
            System.out.println("Check Host: " + e.getMessage());
        }
        
    }

    public void findByCampaignAndAccount()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strSelect = "SELECT CampaignAccountId,IsHost FROM onlinevotingsystem.campaignaccount where CampaignId = ? and AccountId = ?;";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, campaignId);
            ps.setInt(2, accountId);
            rs = ps.executeQuery();
            while (rs.next())
            {
                this.id = rs.getInt(1);
                this.host = rs.getBoolean(2);
            }

        }
        catch (Exception e)
        {
            System.out.println("FindByCampaignAndAccount: " + e.getMessage());
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

    public void addHost()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strAdd = "INSERT INTO `onlinevotingsystem`.`campaignaccount` (`CampaignId`, `AccountId`, `IsHost`) VALUES (?, ?, '1');";
            ps = cnn.prepareStatement(strAdd);
            ps.setInt(1, campaignId);
            ps.setInt(2, accountId);
            ps.execute();

        }
        catch (Exception e)
        {
            System.out.println("AddHostAccount: " + e.getMessage());
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
    
    public int countVoterinCampaign(int id){
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String strSelect = "select count(AccountId) from campaignaccount where CampaignId = ?";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next())
            {
                return rs.getInt(1);
            }

        }
        catch (Exception e)
        {
            System.out.println("countVoterinCampaign: " + e.getMessage());
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
    
    public int countVotedBallot(int id){
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String strSelect = "select count(AccountId) from campaignaccount where CampaignId = ? and VotingResult != null";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next())
            {
                return rs.getInt(1);
            }

        }
        catch (Exception e)
        {
            System.out.println("countVotedBallot: " + e.getMessage());
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
