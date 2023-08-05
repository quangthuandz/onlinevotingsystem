/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ducan
 */
public class Campaign {

    private int id, ruleId, createdBy;
    private String name, password;
    private Date startTime, endTime;
    private String des, img;
    private boolean isPublic;
    public String status;

    public Campaign() {
    }

    public Campaign(int id) {
        this.id = id;
    }

    public Campaign(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Campaign(int id, String name, Date startTime, Date endTime, String des, String img) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.des = des;
        this.img = img;
    }

    public Campaign(int id, int ruleId, int createdBy, String name, String password, Date startTime, Date endTime, Date createTime, String des, String img, boolean isPublic) {
        this.id = id;
        this.ruleId = ruleId;
        this.createdBy = createdBy;
        this.name = name;
        this.password = password;
        this.startTime = startTime;
        this.endTime = endTime;
        this.des = des;
        this.img = img;
        this.isPublic = isPublic;
    }

    public Campaign(int id, String name, String des, String img) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.img = img;
    }

    public Campaign(int ruleId, int createdBy, String name, String password, String des, String img, boolean isPublic) {
        this.ruleId = ruleId;
        this.createdBy = createdBy;
        this.name = name;
        this.password = password;
        this.des = des;
        this.img = img;
        this.isPublic = isPublic;
    }

    public Campaign(int id, String name, Date startTime, Date endTime, int createdBy, int ruleId, boolean isPublic, String status) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdBy = createdBy;
        this.ruleId = ruleId;
        this.isPublic = isPublic;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getFormattedEndTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(endTime);
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
//                System.out.println("Connected to Campaign");
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println("Campaign connect: " + e.getMessage());
//        }
//    }
    public ArrayList<Campaign> filterCampaignbyLocked() {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<Campaign> data = new ArrayList<>();
        try {
            String strSelect = "Select * from onlinevotingsystem.campaign where isPublic = 0";
            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idVote = rs.getInt(1);
                String name = rs.getString(2);
                Date sTime = rs.getDate(3);
                Date eTime = rs.getDate(4);
                String des = rs.getString(5);
                String img = rs.getString(6);
                Campaign c = new Campaign(idVote, name, sTime, eTime, des, img);
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("Filter campaign: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }

//    public ArrayList<Campaign> filterCampaignbyAvaiable() {
//        Connection cnn = new DatabaseConnection().conn;
//        ArrayList<Campaign> data = new ArrayList<>();
//        try {
//            String strSelect = "Select * from onlinevotingsystem.campaign where isPublic = 1";
//            ps = cnn.prepareStatement(strSelect);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                int idVote = rs.getInt(1);
//                String name = rs.getString(2);
//                Date sTime = rs.getDate(3);
//                Date eTime = rs.getDate(4);
//                String des = rs.getString(5);
//                String img = rs.getString(6);
//                Campaign c = new Campaign(idVote, name, sTime, eTime, des, img);
//                data.add(c);
//            }
//
//        } catch (Exception e) {
//            System.out.println("Filter campaign: " + e.getMessage());
//        } finally {
//            if (cnn != null) {
//                try {
//                    cnn.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        return data;
//    }

    public ArrayList<Campaign> getCampaignJoin(int uid) {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<Campaign> data = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM campaign JOIN campaignaccount ON campaign.campaignid = campaignaccount.campaignid WHERE campaignaccount.accountid = ? AND campaign.campaignid NOT IN (SELECT CampaignId FROM campaign WHERE CreatedBy = ? ORDER BY CampaignId DESC)";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, uid);
            ps.setInt(2,uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idVote = rs.getInt(1);
                String name = rs.getString(2);
                Date sTime = rs.getDate(3);
                Date eTime = rs.getDate(4);
                String des = rs.getString(5);
                String img = rs.getString(6);
                Campaign c = new Campaign(idVote, name, sTime, eTime, des, img);
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("getCampaignByHost: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }

    public ArrayList<Campaign> getCampaignByUnJoin(int uid) {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<Campaign> data = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM campaign WHERE campaignid NOT IN ( SELECT campaignid FROM campaignaccount WHERE accountid = ?)";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idVote = rs.getInt(1);
                String name = rs.getString(2);
                Date sTime = rs.getDate(3);
                Date eTime = rs.getDate(4);
                String des = rs.getString(5);
                String img = rs.getString(6);
                Campaign c = new Campaign(idVote, name, sTime, eTime, des, img);
                data.add(c);
                System.out.println(idVote + name);

            }

        } catch (Exception e) {
            System.out.println("getCampaignUnJoin: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }
    public ArrayList<Campaign> getCampaignByAdmin() {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<Campaign> data = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM onlinevotingsystem.campaign ";
            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idVote = rs.getInt(1);
                String name = rs.getString(2);
                Date sTime = rs.getDate(3);
                Date eTime = rs.getDate(4);
                String des = rs.getString(5);
                String img = rs.getString(6);
                Campaign c = new Campaign(idVote, name, sTime, eTime, des, img);
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("getCampaign: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }


    public ArrayList<Campaign> getCampaignByHost(int uid) {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<Campaign> data = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM onlinevotingsystem.campaign  WHERE CreatedBy = ? ORDER BY CampaignId DESC";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idVote = rs.getInt(1);
                String name = rs.getString(2);
                Date sTime = rs.getDate(3);
                Date eTime = rs.getDate(4);
                String des = rs.getString(5);
                String img = rs.getString(6);
                Campaign c = new Campaign(idVote, name, sTime, eTime, des, img);
                data.add(c);

            }

        } catch (Exception e) {
            System.out.println("getCampaignByHost: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }

    public ArrayList<Campaign> getCampaign(int accountId)
    {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<Campaign> data = new ArrayList<>();
        try {
            String strSelect = "Select * from onlinevotingsystem.campaign where isPublic = 0";
            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idVote = rs.getInt(1);
                String name = rs.getString(2);
                Date sTime = rs.getDate(3);
                Date eTime = rs.getDate(4);
                String des = rs.getString(5);
                String img = rs.getString(6);
                Campaign c = new Campaign(idVote, name, sTime, eTime, des, img);
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("Filter campaign: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }

    public ArrayList<Campaign> filterCampaignbyAvaiable() {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<Campaign> data = new ArrayList<>();
        try {
            String strSelect = "Select * from onlinevotingsystem.campaign where isPublic = 1";
            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idVote = rs.getInt(1);
                String name = rs.getString(2);
                Date sTime = rs.getDate(3);
                Date eTime = rs.getDate(4);
                String des = rs.getString(5);
                String img = rs.getString(6);
                Campaign c = new Campaign(idVote, name, sTime, eTime, des, img);
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("Filter campaign: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }

//    public ArrayList<Campaign> getCampaignJoin(int uid) {
//        Connection cnn = new DatabaseConnection().conn;
//        ArrayList<Campaign> data = new ArrayList<>();
//        try {
//            String strSelect = "SELECT DISTINCT * FROM campaign c INNER JOIN campaignaccount ca ON c.campaignid = ca.campaignid WHERE ca.accountid = 9 and c.CreatedBy != 9;";
//            ps = cnn.prepareStatement(strSelect);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                int idVote = rs.getInt(1);
//                String name = rs.getString(2);
//                Date sTime = rs.getDate(3);
//                Date eTime = rs.getDate(4);
//                String des = rs.getString(5);
//                String img = rs.getString(6);
//                Campaign c = new Campaign(idVote, name, sTime, eTime, des, img);
//                System.out.println(c.name);
//                data.add(c);
//            }
//
//        } catch (Exception e) {
//            System.out.println("getCampaignByJoin: " + e.getMessage());
//        } finally {
//            if (cnn != null) {
//                try {
//                    cnn.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        return data;
//    }

  

//    public ArrayList<Campaign> getCampaignByAdmin() {
//        Connection cnn = new DatabaseConnection().conn;
//        ArrayList<Campaign> data = new ArrayList<>();
//        try {
//            String strSelect = "SELECT * FROM onlinevotingsystem.campaign ";
//            ps = cnn.prepareStatement(strSelect);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                int idVote = rs.getInt(1);
//                String name = rs.getString(2);
//                Date sTime = rs.getDate(3);
//                Date eTime = rs.getDate(4);
//                String des = rs.getString(5);
//                String img = rs.getString(6);
//                Campaign c = new Campaign(idVote, name, sTime, eTime, des, img);
//                data.add(c);
//            }
//
//        } catch (Exception e) {
//            System.out.println("getCampaign: " + e.getMessage());
//        } finally {
//            if (cnn != null) {
//                try {
//                    cnn.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        return data;
//    }
//
//    public ArrayList<Campaign> getCampaignByHost(int uid) {
//        Connection cnn = new DatabaseConnection().conn;
//        ArrayList<Campaign> data = new ArrayList<>();
//        try {
//            String strSelect = "SELECT * FROM onlinevotingsystem.campaign  WHERE CreatedBy = ? ORDER BY CampaignId DESC";
//            ps = cnn.prepareStatement(strSelect);
//            ps.setInt(1, uid);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                int idVote = rs.getInt(1);
//                String name = rs.getString(2);
//                Date sTime = rs.getDate(3);
//                Date eTime = rs.getDate(4);
//                String des = rs.getString(5);
//                String img = rs.getString(6);
//                Campaign c = new Campaign(idVote, name, sTime, eTime, des, img);
//                data.add(c);
//            }
//        } catch (Exception e) {
//            System.out.println("getCampaignByHost: " + e.getMessage());
//        } finally {
//            if (cnn != null) {
//                try {
//                    cnn.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        return data;
//    }
//
//    public ArrayList<Campaign> getCampaign(int accountId) {
//        Connection cnn = new DatabaseConnection().conn;
//        ArrayList<Campaign> data = new ArrayList<>();
//        try {
//            String strSelect = "SELECT campaign.*\n"
//                    + "FROM campaign\n"
//                    + "JOIN pinned ON campaign.CampaignId = pinned.CampaignId\n"
//                    + "WHERE pinned.UserId = " + accountId + "\n"
//                    + "UNION\n"
//                    + "SELECT campaign.*\n"
//                    + "FROM campaign\n"
//                    + "LEFT JOIN pinned ON campaign.CampaignId = pinned.CampaignId";
//            ps = cnn.prepareStatement(strSelect);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                int idVote = rs.getInt(1);
//                String name = rs.getString(2);
//                Date sTime = rs.getDate(3);
//                Date eTime = rs.getDate(4);
//                String des = rs.getString(5);
//                String img = rs.getString(6);
//                Campaign c = new Campaign(idVote, name, sTime, eTime, des, img);
//                data.add(c);
//            }
//
//        } catch (Exception e) {
//            System.out.println("getCampaign: " + e.getMessage());
//        } finally {
//            if (cnn != null) {
//                try {
//                    cnn.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        return data;
//    }

    public Timestamp getEndTimeByid(int id) {
        Connection cnn = new DatabaseConnection().conn;
        try {
            String strSelect = "SELECT EndTime FROM onlinevotingsystem.campaign WHERE CampaignId = ? ";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getTimestamp(1);
            }

        } catch (Exception e) {
            System.out.println("getTimeCampaignById: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public Timestamp getStartTimeByid(int id) {
        Connection cnn = new DatabaseConnection().conn;
        try {
            String strSelect = "SELECT StartTime FROM onlinevotingsystem.campaign WHERE CampaignId = ? ";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getTimestamp(1);
            }

        } catch (Exception e) {
            System.out.println("getTimeCampaignById: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public void getCampaignById() {
        Connection cnn = new DatabaseConnection().conn;
        try {
            String strSelect = "SELECT * FROM onlinevotingsystem.campaign WHERE CampaignId = ? ";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                this.name = rs.getString(2);
                this.startTime = rs.getDate(3);
                this.endTime = rs.getDate(4);
                this.des = rs.getString(5);
                this.img = rs.getString(6);
                this.ruleId = rs.getInt(8);
            }

        } catch (Exception e) {
            System.out.println("getCampaignById: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void removeCampaign(String campaignId) {
        Connection cnn = new DatabaseConnection().conn;
        try {
            String strDelete = "UPDATE onlinevotingsystem.campaign SET isDeleted = 1 where CampaignId=" + campaignId;
            ps = cnn.prepareStatement(strDelete);
            ps.execute();

        } catch (Exception e) {
            System.out.println("deleteCampaign: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public ArrayList<Campaign> getCampaignBySearching(String search_keyword, String sort_filter) {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<Campaign> data = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM onlinevotingsystem.campaign where CampaignName like '%" + search_keyword + "%' order by " + sort_filter + "; ";
            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            System.out.println("before while");
            while (rs.next()) {
                int idVote = rs.getInt(1);
                String name = rs.getString(2);
                Date sTime = rs.getDate(3);
                Date eTime = rs.getDate(4);
                String des = rs.getString(5);
                String img = rs.getString(6);
                Campaign c = new Campaign(idVote, name, sTime, eTime, des, img);
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("getCampaign: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }

    public void editCampaignById() {
        Connection cnn = new DatabaseConnection().conn;
        try {

            String updateStr = "UPDATE `onlinevotingsystem`.`campaign` "
                    + "SET `CampaignName` = ?, `EndTime` = ?, `CampaignDescription` = ?, `VotingRuleId` = ?, `isPublic` = ?, `Password` = ? "
                    + "WHERE (`CampaignId` = ?); ";
            ps = cnn.prepareStatement(updateStr);
            ps.setString(1, name);
            ps.setDate(2, (java.sql.Date) endTime);
            ps.setString(3, des);
            ps.setInt(4, ruleId);
            ps.setBoolean(5, isPublic);
            ps.setString(6, password);
            ps.setInt(7, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("EditCampaignById: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void getCampaignInfoById() {
        Connection cnn = new DatabaseConnection().conn;
        try {
            String strSelect = "SELECT CampaignId, CampaignName, StartTime, EndTime, CampaignDescription, CampaignImg, CreatedBy, VotingRuleId, isPublic, `Password` "
                    + "FROM onlinevotingsystem.campaign WHERE CampaignId = ? ";

            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                this.id = rs.getInt(1);
                this.name = rs.getString(2);
                this.startTime = rs.getDate(3);
                this.endTime = rs.getDate(4);
                this.des = rs.getString(5);
                this.img = rs.getString(6);
                this.createdBy = rs.getInt(7);
                this.ruleId = rs.getInt(8);
                this.isPublic = rs.getBoolean(9);
                this.password = rs.getString(10);
            }

        } catch (Exception e) {
            System.out.println("getCampaignInfoById: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void setCampaignInfoById(int id) {
        Connection cnn = new DatabaseConnection().conn;
        try {
            String strSelect = "SELECT CampaignId, CampaignName, StartTime, EndTime, CampaignDescription, CampaignImg, CreatedBy, VotingRuleId, isPublic, `Password` "
                    + "FROM onlinevotingsystem.campaign WHERE CampaignId = ? ";

            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                this.id = rs.getInt(1);
                this.name = rs.getString(2);
                this.startTime = rs.getDate(3);
                this.endTime = rs.getDate(4);
                this.des = rs.getString(5);
                this.img = rs.getString(6);
                this.createdBy = rs.getInt(7);
                this.ruleId = rs.getInt(8);
                this.isPublic = rs.getBoolean(9);
                this.password = rs.getString(10);
            }

        } catch (Exception e) {
            System.out.println("getCampaignInfoById: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void addCampaign() {
        Connection cnn = new DatabaseConnection().conn;
        try {
            String strAdd = "INSERT INTO `onlinevotingsystem`.`campaign`\n"
                    + "(\n"
                    + "`CampaignName`,\n"
                    + "`StartTime`,\n"
                    + "`EndTime`,\n"
                    + "`CampaignDescription`,\n"
                    + "`CampaignImg`,\n"
                    + "`CreatedBy`,\n"
                    + "`VotingRuleId`,\n"
                    + "`isPublic`,\n"
                    + "`Password`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, name);
            pstm.setString(2, null);
            pstm.setString(3, null);
            pstm.setString(4, des);
            pstm.setString(5, img);
            pstm.setInt(6, createdBy);
            pstm.setInt(7, ruleId);
            pstm.setInt(8, isPublic ? 1 : 0);
            pstm.setString(9, password);
            pstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("addCampaign: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public int getCampaignIdByAccountId(int aid) {
        Connection cnn = new DatabaseConnection().conn;
        int cid = 0;
        try {

            String strSelect = "SELECT CampaignId FROM onlinevotingsystem.campaign  WHERE CreatedBy = ? ORDER BY CampaignId DESC LIMIT 1 ";

            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, aid);
            rs = ps.executeQuery();
            while (rs.next()) {
                cid = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getCampaignIdByAccountId: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return cid;
    }

    public void updateTime(int id) {
        Connection cnn = new DatabaseConnection().conn;
        try {
            String StrUpdate = "UPDATE `onlinevotingsystem`.`campaign`\n"
                    + "SET\n"
                    + "`StartTime` = ?,\n"
                    + "`EndTime` = ? \n"
                    + "where `CampaignId` = ?";

            ps = cnn.prepareStatement(StrUpdate);
            ps.setTimestamp(1, (Timestamp) startTime);
            ps.setTimestamp(2, (Timestamp) endTime);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update:" + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean isPinned(int userId, int campaignId) {
        Connection cnn = new DatabaseConnection().conn;
        try {

            String strSelect = "SELECT * from onlinevotingsystem.pinned where UserId = " + userId + " and CampaignId = " + campaignId;

            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("isPinned: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    //count total campaign of website
    public int getCampaignCount() {
        int campaignCount = 0;
        String query = "SELECT "
                + "COUNT(*) "
                + "AS "
                + "Count "
                + "FROM "
                + "CAMPAIGN ";
        try {
            Connection cnn = new DatabaseConnection().conn;
            PreparedStatement ps = cnn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                campaignCount = rs.getInt("Count");
            }

            rs.close();
            ps.close();
            cnn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return campaignCount;
    }

    public boolean getCampaignStatus() {
        LocalDate endTime = null;
        Connection cnn = new DatabaseConnection().conn;
        try {
            String str = "SELECT EndTime FROM onlinevotingsystem.campaign "
                    + "WHERE CampaignId = ? ;";
            ps = cnn.prepareStatement(str);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                endTime = rs.getDate(1).toLocalDate();
            }
        } catch (Exception e) {
            System.out.println("getCampaignStatus:" + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    System.out.println("getCampaignStatus:" + ex.getMessage());
                }
            }
        }

        return isCampaignClosed(endTime);
    }

    private boolean isCampaignClosed(LocalDate endTime) {
        LocalDate now = LocalDate.now();
        return !endTime.isAfter(now);
    }

    //get necessary infomation of campaign for dashboard
    public ArrayList<Campaign> getCampaignForDashboard() {
        ArrayList<Campaign> dataForDashboard = new ArrayList<>();
        String query = "SELECT "
                + "CampaignId, "
                + "CampaignName, "
                + "StartTime, "
                + "EndTime, "
                + "CreatedBy, "
                + "VotingRuleId, "
                + "isPublic, "
                + "Status "
                + "FROM "
                + "onlinevotingsystem.campaign;";
        try {
            Connection cnn = new DatabaseConnection().conn;
            PreparedStatement ps = cnn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idVote = rs.getInt(1);
                String name = rs.getString(2);
                Date sTime = rs.getDate(3);
                Date eTime = rs.getDate(4);
                int createBy = rs.getInt(5);
                int votingRuleId = rs.getInt(6);
                boolean isPublic = rs.getBoolean(7);
                String status = rs.getString(8);
                Campaign c = new Campaign(idVote, name, sTime, eTime, createBy, votingRuleId, isPublic, status);
                dataForDashboard.add(c);
            }
            rs.close();
            ps.close();
            cnn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataForDashboard;
    }
}
