/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**

 @author ADMIN
 */
public class Vote
{

    private int id, vterid, VoId, cid, point;
    private LocalDateTime time;

    public Vote()
    {
    }

    public Vote(int id, int vterid, int VoId, int cid, int point)
    {
        this.id = id;
        this.vterid = vterid;
        this.VoId = VoId;
        this.cid = cid;
        this.point = point;
    }

    public Vote(int vterid, int VoId, int cid, int point)
    {
        this.vterid = vterid;
        this.VoId = VoId;
        this.cid = cid;
        this.point = point;
    }

    public Vote(int vterid, int VoId, int cid)
    {
        this.vterid = vterid;
        this.VoId = VoId;
        this.cid = cid;
    }

    public Vote(int vterid, int cid)
    {
        this.vterid = vterid;
        this.cid = cid;
    }

    public Vote(int id, int vterid, int VoId, int cid, int point, LocalDateTime time)
    {
        this.id = id;
        this.vterid = vterid;
        this.VoId = VoId;
        this.cid = cid;
        this.point = point;
        this.time = time;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getVid()
    {
        return vterid;
    }

    public void setVid(int vid)
    {
        this.vterid = vid;
    }

    public int getVoId()
    {
        return VoId;
    }

    public void setVoId(int VoId)
    {
        this.VoId = VoId;
    }

    public int getCid()
    {
        return cid;
    }

    public void setCid(int cid)
    {
        this.cid = cid;
    }

    public int getPoint()
    {
        return point;
    }

    public void setPoint(int point)
    {
        this.point = point;
    }

//    DatabaseConnection db = new DatabaseConnection();
//    Connection cnn = null;
    PreparedStatement ps;
    ResultSet rs;

//    private void connect()
//    {
//        try
//        {
//            cnn = db.getConnection();
//            if (cnn != null)
//            {
//                System.out.println("Connected to Vote");
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println("Campaign connect: " + e.getMessage());
//        }
//    }
    public void AddSingleVote()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String StrAdd = "Insert into onlinevotingsystem.`vote`"
                    + "(VoterId,\n"
                    + "VotingObjectId,\n"
                    + "Campaign,\n"
                    + "Point\n)"
                    + "values (?,?,?,1);";
            ps = cnn.prepareStatement(StrAdd);
            ps.setInt(1, vterid);
            ps.setInt(2, VoId);
            ps.setInt(3, cid);
            ps.execute();
        }
        catch (Exception e)
        {
            System.out.println("addVote: " + e.getMessage());
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

    public void AddRankVoting()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String StrAdd = "Insert into onlinevotingsystem.`vote`"
                    + "(VoterId,"
                    + "VotingObjectId,"
                    + "Campaign,"
                    + "Point)"
                    + "values (?,?,?,?);";
            ps = cnn.prepareStatement(StrAdd);
            ps.setInt(1, vterid);
            ps.setInt(2, VoId);
            ps.setInt(3, cid);
            ps.setInt(4, point);
            ps.execute();
        }
        catch (Exception e)
        {
            System.out.println("addVote: " + e.getMessage());
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

    public boolean CheckVoted()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String StrCheck = "Select * from vote where Campaign = ? and VoterId = ? ;";
            ps = cnn.prepareStatement(StrCheck);
            ps.setInt(1, cid);
            ps.setInt(2, vterid);
            rs = ps.executeQuery();
            while (rs.next())
            {
                return true;
            }

        }
        catch (Exception e)
        {
            System.out.println("Check Vote: " + e.getMessage());
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

    public void UpdateSingleVote()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String StrUpdate = "Update onlinevotingsystem.`vote`"
                    + "set VotingObjectId = ? "
                    + "where Campaign = ? and VoterId = ?;";
            ps = cnn.prepareStatement(StrUpdate);
            ps.setInt(3, vterid);
            ps.setInt(1, VoId);
            ps.setInt(2, cid);
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("UpdateVote: " + e.getMessage());
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

    public void UpdateRankingVote()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String StrUpdate = "Update onlinevotingsystem.`vote` set "
                    + "VotingObjectId = ? "
                    + "Where VoterId = ? "
                    + "AND Campaign = ? "
                    + "AND Point = ?;";
            ps = cnn.prepareStatement(StrUpdate);
            ps.setInt(2, vterid);
            ps.setInt(1, VoId);
            ps.setInt(3, cid);
            ps.setInt(4, point);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("UpdateVote: " + e.getMessage());
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

    public void getSingleVote()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String StrSelect = "Select * from onlinevotingsystem.`vote` where Campaign = ? and VoterId = ?;";
            ps = cnn.prepareStatement(StrSelect);
            ps.setInt(1, cid);
            ps.setInt(2, vterid);
            rs = ps.executeQuery();
            while (rs.next())
            {
                this.vterid = rs.getInt(2);
                this.VoId = rs.getInt(3);
                this.cid = rs.getInt(4);
            }
        }
        catch (Exception e)
        {
            System.out.println("Existed Vote: " + e.getMessage());
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

    public ArrayList<Vote> getRankingVote()
    {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<Vote> data = new ArrayList<>();
        try
        {

            String StrSelect = "Select * from onlinevotingsystem.`vote` "
                    + "where Campaign = ? and VoterId = ? "
                    + "order by Point Desc;";
            ps = cnn.prepareStatement(StrSelect);
            ps.setInt(1, cid);
            ps.setInt(2, vterid);
            rs = ps.executeQuery();
            while (rs.next())
            {
                int VoterId = rs.getInt(2);
                int VotingObjectId = rs.getInt(3);
                int Cid = rs.getInt(4);
                int Point = rs.getInt(5);
                Vote v = new Vote(VoterId, VotingObjectId, Cid, Point);
                data.add(v);
            }
        }
        catch (Exception e)
        {
            System.out.println("Existed RankingVote: " + e.getMessage());
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

    public int getPointByCandidateId(int candidateId)
    {
        Connection cnn = new DatabaseConnection().conn;
        int points = 0;
        try
        {

            String str = "select SUM(point) from vote where VotingObjectId = ?;";
            ps = cnn.prepareStatement(str);
            ps.setInt(1, candidateId);
            rs = ps.executeQuery();
            while (rs.next())
            {
                points = rs.getInt(1);
            }
        }
        catch (Exception e)
        {
            System.out.println("getPointByCandidateId: " + e.getMessage());
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
        return points;
    }

    public List<Map.Entry<Integer, Integer>> getNoOfVotes()
    {
        List<Map.Entry<Integer, Integer>> noOfVotes = new ArrayList<>();
        Integer total = 0, casted = 0;

        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String str = "SELECT DISTINCT ca.AccountId, v.VoterId "
                    + "FROM onlinevotingsystem.campaignaccount ca "
                    + "LEFT JOIN onlinevotingsystem.vote v ON ca.accountId = v.VoterId "
                    + "WHERE CampaignId = ? ;";
            ps = cnn.prepareStatement(str);
            ps.setInt(1, this.cid);
            rs = ps.executeQuery();
            while (rs.next())
            {
                if (rs.getInt(2) == 0)
                {
                    total++;
                }
                else
                {
                    total++;
                    casted++;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("getNoOfVotes: " + e.getMessage());
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
                    System.out.println("getNoOfVotes: " + ex.getMessage());
                }
            }
        }

        noOfVotes.add(new SimpleEntry<>(casted, total));

        return noOfVotes;
    }

    public Map<String, Integer> getCurrentVote()
    {
        Map<String, Integer> currentVote = new HashMap<>();
        VotingObject obj = new VotingObject();

        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String str = "SELECT v.VotingObjectId, v.point "
                    + "FROM onlinevotingsystem.vote v "
                    + "WHERE v.Campaign = ? AND v.VoterId = ? ;";
            ps = cnn.prepareStatement(str);
            ps.setInt(1, this.cid);
            ps.setInt(2, this.vterid);
            rs = ps.executeQuery();
            while (rs.next())
            {
                int votingObjectId = rs.getInt(1);
                int point = rs.getInt(2);

                String votingObject = obj.getVotingObjectByObjectId(votingObjectId).getVotingObjectName();

                currentVote.put(votingObject, point);
            }
        }
        catch (Exception e)
        {
            System.out.println("getCurrentVote: " + e.getMessage());
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
                    System.out.println("getCurrentVote: " + ex.getMessage());
                }
            }
        }
        return currentVote;
    }

    public Map<String, Integer> getCurrentResult()
    {
        Map<String, Integer> currentResult = new HashMap<>();

        VotingObject obj = new VotingObject();

        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String str = "SELECT VotingObjectId, SUM(Point) "
                    + "FROM onlinevotingsystem.vote "
                    + "WHERE Campaign = ? "
                    + "GROUP BY VotingObjectId; ";
            ps = cnn.prepareStatement(str);
            ps.setInt(1, this.cid);
            rs = ps.executeQuery();
            while (rs.next())
            {
                int votingObjectId = rs.getInt(1);
                int point = rs.getInt(2);

                VotingObject votingObject = obj.getVotingObjectByObjectId(votingObjectId);

                currentResult.put(votingObject.getVotingObjectName(), point);
            }
        }
        catch (Exception e)
        {
            System.out.println("getCurrentResult: " + e.getMessage());
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
                    System.out.println("getCurrentResult: " + ex.getMessage());
                }
            }
        }

        return currentResult;
    }

    public List<Map.Entry<String, Integer>> getLeadingOption()
    {
        List<Map.Entry<String, Integer>> leadingOption = new ArrayList<>();
        VotingObject obj = new VotingObject();

        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String str = "SELECT VotingObjectId, SUM(`Point`) as `point` "
                    + "FROM onlinevotingsystem.vote "
                    + "WHERE Campaign = ? "
                    + "GROUP BY VotingObjectId "
                    + "ORDER BY `point` DESC LIMIT 1;";

            ps = cnn.prepareStatement(str);
            ps.setInt(1, this.cid);
            rs = ps.executeQuery();
            while (rs.next())
            {
                int votingObjectId = rs.getInt(1);
                int point = rs.getInt(2);

                String votingObject = obj.getVotingObjectByObjectId(votingObjectId).getVotingObjectName();

                leadingOption.add(new SimpleEntry<>(votingObject, point));
            }
        }
        catch (Exception e)
        {
            System.out.println("getLeadingOption: " + e.getMessage());
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
                    System.out.println("getLeadingOption: " + ex.getMessage());
                }
            }
        }

        return leadingOption;
    }
    
    public int countVotedBallot(int id) {
        Connection cnn = new DatabaseConnection().conn;
        try {
            String strSelect = "select count(VoteId) from Vote where Campaign = ?";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("countVotedBallot: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }
}
