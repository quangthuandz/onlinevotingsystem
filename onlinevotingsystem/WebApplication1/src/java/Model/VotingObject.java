package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ducan
 */
public class VotingObject {

    private int votingObjectId;
    String votingObjectName;
    String votingObjectType;
    private int campaignId;
    String imgPath;
    String votingObjectDescription;
    String question;

    public VotingObject() {
    }

    public VotingObject(String question) {
        this.question = question;
    }

    public VotingObject(int votingObjectId, String votingObjectName, String votingObjectType, int campaignId, String imgPath, String votingObjectDescription, String question) {
        this.votingObjectId = votingObjectId;
        this.votingObjectName = votingObjectName;
        this.votingObjectType = votingObjectType;
        this.campaignId = campaignId;
        this.imgPath = imgPath;
        this.votingObjectDescription = votingObjectDescription;
        this.question = question;
    }

    public VotingObject(String votingObjectName, String votingObjectType, int campaignId, String imgPath, String votingObjectDescription) {
        this.votingObjectName = votingObjectName;
        this.votingObjectType = votingObjectType;
        this.campaignId = campaignId;
        this.imgPath = imgPath;
        this.votingObjectDescription = votingObjectDescription;
    }

    public VotingObject(int votingObjectId, String votingObjectName, String votingObjectType, int campaignId, String imgPath, String votingObjectDescription) {
        this.votingObjectId = votingObjectId;
        this.votingObjectName = votingObjectName;
        this.votingObjectType = votingObjectType;
        this.campaignId = campaignId;
        this.imgPath = imgPath;
        this.votingObjectDescription = votingObjectDescription;
    }

    public VotingObject(int votingObjectId, String votingObjectName, String imgPath, String votingObjectDescription, String question) {
        this.votingObjectId = votingObjectId;
        this.votingObjectName = votingObjectName;
        this.imgPath = imgPath;
        this.votingObjectDescription = votingObjectDescription;
        this.question = question;
    }

    public VotingObject(String votingObjectName, String imgPath) {
        this.votingObjectName = votingObjectName;
        this.imgPath = imgPath;
    }

    public VotingObject(int votingObjectId, int CampaignId) {
        this.votingObjectId = votingObjectId;
        this.campaignId = CampaignId;
    }

    public VotingObject(int votingObjectId, String votingObjectName, String imgPath, String votingObjectDescription) {
        this.votingObjectId = votingObjectId;
        this.votingObjectName = votingObjectName;
        this.imgPath = imgPath;
        this.votingObjectDescription = votingObjectDescription;
    }

    public int getVotingObjectId() {
        return votingObjectId;
    }

    public void setVotingObjectId(int votingObjectId) {
        this.votingObjectId = votingObjectId;
    }

    public String getVotingObjectName() {
        return votingObjectName;
    }

    public void setVotingObjectName(String votingObjectName) {
        this.votingObjectName = votingObjectName;
    }

    public String getVotingObjectType() {
        return votingObjectType;
    }

    public void setVotingObjectType(String votingObjectType) {
        this.votingObjectType = votingObjectType;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getVotingObjectDescription() {
        return votingObjectDescription;
    }

    public void setVotingObjectDescription(String votingObjectDescription) {
        this.votingObjectDescription = votingObjectDescription;
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
//                System.out.println("Connected to VotingObject");
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println("VotingObject connect: " + e.getMessage());
//        }
//    }
    public ArrayList<VotingObject> getVotingObjectById(int campaignId) {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<VotingObject> data = new ArrayList<>();
        try {

            String strSelect = "select "
                    + "VotingObjectName,"
                    + "ImgPath, "
                    + "VotingObjectId "
                    + "from "
                    + "votingobject v "
                    + "join "
                    + "campaign c "
                    + "on v.CampaignId = c.CampaignId "
                    + "where v.CampaignId = ?";

            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, campaignId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String VotingObjectName = rs.getString(1);
                String imgPath = rs.getString(2);
                int id = rs.getInt(3);
                VotingObject v = new VotingObject(VotingObjectName, imgPath);
                v.setVotingObjectId(id);
                data.add(v);
            }

        } catch (Exception e) {
            System.out.println("getVotingObjectById: " + e.getMessage());
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

    public ArrayList<VotingObject> getListOptionByCampaignId(int id) throws SQLException {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<VotingObject> list = new ArrayList<>();

        try {

            String strSelect = "SELECT VotingObjectId,Question,VotingObjectName,ImgPath, description\n"
                    + "FROM `onlinevotingsystem`.`votingobject` where CampaignId = ?";

            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                this.votingObjectId = rs.getInt(1);
                this.question = rs.getString(2);
                this.votingObjectName = rs.getString(3);
                this.imgPath = rs.getString(4);
                this.votingObjectDescription = rs.getString(5);
                list.add(new VotingObject(votingObjectId, votingObjectName, imgPath, votingObjectDescription, question));
            }
        } catch (Exception e) {
            System.out.println("getListOptionByCampaignId: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public void addVotingObject() throws SQLException {
        Connection cnn = new DatabaseConnection().conn;
        try {

            String strAdd = "INSERT INTO `onlinevotingsystem`.`votingobject`\n"
                    + "(\n"
                    + "`VotingObjectName`,\n"
                    + "`VotingObjectType`,\n"
                    + "`CampaignId`,\n"
                    + "`ImgPath`,\n"
                    + "`Question`,\n"
                    + "`Description`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?,?);";

            PreparedStatement pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, votingObjectName);
            pstm.setString(2, votingObjectType);
            pstm.setInt(3, campaignId);
            pstm.setString(4, imgPath);
            pstm.setString(5, question);
            pstm.setString(6, votingObjectDescription);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("addVotingObject: " + e.getMessage());
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

    public void deleteVotingObject(int id) {
        Connection cnn = new DatabaseConnection().conn;
        String query = "DELETE FROM votingobject WHERE VotingObjectId = ?";

        try {

            PreparedStatement statement = cnn.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deleteVOtingObject: " + e.getMessage());
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

    public VotingObject getVotingObjectByObjectId(int id) {
        Connection cnn = new DatabaseConnection().conn;
        try {

            String strSelect = "SELECT VotingObjectId,VotingObjectName,VotingObjectType,CampaignId,ImgPath,Question,(Description)\n"
                    + "FROM `onlinevotingsystem`.`votingobject` where VotingObjectId=?;";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                this.votingObjectId = rs.getInt(1);
                this.votingObjectName = rs.getString(2);
                this.votingObjectType = rs.getString(3);
                this.campaignId = rs.getInt(4);
                this.imgPath = rs.getString(5);
                this.question = rs.getString(6);
                this.votingObjectDescription = rs.getString(7);
                return new VotingObject(votingObjectId, votingObjectName, votingObjectType, campaignId, imgPath, question, votingObjectDescription);
            }
        } catch (SQLException e) {
            System.out.println("getVotingObjectById " + e.getMessage());
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

    public void updateQuestion(int id) {
        Connection cnn = new DatabaseConnection().conn;
        try {

            String StrUpdate = "UPDATE `onlinevotingsystem`.`votingobject`\n"
                    + "SET\n"
                    + "`Question` = ?\n"
                    + "WHERE `CampaignId` = ?;";

            ps = cnn.prepareStatement(StrUpdate);
            ps.setString(1, question);
            ps.setInt(2, id);
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

    public ArrayList<VotingObject> getVotedOption(int cid, int id) {
        Connection cnn = new DatabaseConnection().conn;
        ArrayList<VotingObject> data = new ArrayList<>();
        try {

            String strSelect = "select vo.VotingObjectId,vo.VotingObjectName,\n"
                    + "vo.CampaignId,vo.ImgPath,vo.Question,vo.description,\n"
                    + "vo.VotingObjectType\n"
                    + "from votingobject vo join vote v\n"
                    + "on vo.VotingObjectId = v.VotingObjectId\n"
                    + "and CampaignId = ? and VoterId = ?\n"
                    + "order by v.Point desc;";

            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, cid);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                this.votingObjectId = rs.getInt(1);
                this.votingObjectName = rs.getString(2);
                this.campaignId = rs.getInt(3);
                this.imgPath = rs.getString(4);
                this.question = rs.getString(5);
                this.votingObjectDescription = rs.getString(6);
                this.votingObjectType = rs.getString(7);
                VotingObject v = new VotingObject(votingObjectId, votingObjectName, votingObjectType, campaignId, imgPath, votingObjectDescription);
                data.add(v);
            }
        } catch (Exception e) {
            System.out.println("Candidate: " + e.getMessage());
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

    public void editCandidates() {
        Connection cnn = new DatabaseConnection().conn;
        try {

            String updateStr = "UPDATE `onlinevotingsystem`.`votingobject`\n"
                    + "SET\n"
                    + "`VotingObjectName` = ?,\n"
                    + "`ImgPath` = ?,\n"
                    + "`Description` = ?\n"
                    + "WHERE `VotingObjectId` = ?;";
            ps = cnn.prepareStatement(updateStr);
            ps.setString(1, votingObjectName);
            ps.setString(2, imgPath);
            ps.setString(3, votingObjectDescription);
            ps.setInt(4, votingObjectId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("EditCandidate: " + e.getMessage());
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

}
