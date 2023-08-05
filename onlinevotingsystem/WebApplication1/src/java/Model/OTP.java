package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OTP
{

    private String otp, email, timestamp;

    public String getOtp()
    {
        return otp;
    }

    public void setOtp(String otp)
    {
        this.otp = otp;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    public OTP()
    {
    }

    public OTP(String email, String timestamp)
    {
        this.email = email;
        this.timestamp = timestamp;
    }

    public OTP(String otp, String email, String timestamp)
    {
        this.otp = otp;
        this.email = email;
        this.timestamp = timestamp;
    }

    public void generate(int len)
    {
        String number = "0123456789";

        Random rd = new Random();

        char[] otpSet = new char[len];     //Initialize otp
        for (int i = 0; i < otpSet.length; i++)
        {
            otpSet[i] = number.charAt(rd.nextInt(number.length()));
        }
        this.otp = String.valueOf(otpSet);
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
//                System.out.println("Connected to OTP");
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println("OTP connect: " + e.getMessage());
//        }
//    }
    public void insert()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strInsert = "INSERT INTO `onlinevotingsystem`.`otp` (`Otp`, `Email`, `TimeStamp`) VALUES (?, ?, ?);";
            ps = cnn.prepareStatement(strInsert);
            ps.setString(1, otp);
            ps.setString(2, email);
            ps.setString(3, timestamp);
            ps.executeUpdate();
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("InsertIntoOTP: " + e.getMessage());
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

    // Check for valid OTP
    public boolean isValid() throws SQLException
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strSelect = "select * from `onlinevotingsystem`.`otp` where email = ? order by timestamp DESC LIMIT 1; ";
            ps = cnn.prepareStatement(strSelect);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next())
            {
                return (this.otp.trim().equals(rs.getString(2))) && (timestamp.compareTo(rs.getString(4)) <= 0);
            }
        }
        catch (SQLException e)
        {
            System.out.println("OTPIsValid: " + e.getMessage());
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

    // Get OTP by email
    public String getOTP()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strSelect = "select otp from onlinevotingsystem.`otp` where email = ? order by timestamp DESC LIMIT 1; ";
            ps = cnn.prepareStatement(strSelect);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next())
            {
                return rs.getString(1);
            }
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("OTPIsValid: " + e.getMessage());
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
        return null;
    }
}
