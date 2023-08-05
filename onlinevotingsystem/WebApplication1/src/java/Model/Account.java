package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Account
{

    private String username, password, firstName, lastName, email, phone, address, gender, job, dob, salt;
    private int id;
    private boolean admin, active;

    public Account()
    {
    }

    public Account(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public Account(int id, String username)
    {
        this.id = id;
        this.username = username;
    }

    public Account(int id)
    {
        this.id = id;
    }

    public Account(String username)
    {
        this.username = username;
    }

    public Account(String username, String password, String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public Account(String username, String password, String firstName, String lastName, String email, String phone, String address, String gender, String job, String dob, int id, boolean admin, boolean active)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
        this.id = id;
        this.admin = admin;
        this.active = active;
    }

    public Account(String firstName, String lastName, String email, String phone, String address, String gender, String job, String dob, int id)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
        this.id = id;
    }

    public Account(String username, String password, String firstName, String lastName, String email, String phone, String address, String gender, String job, String dob, boolean admin, boolean active)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
        this.admin = admin;
        this.active = active;
    }

    public Account(String firstName, String lastName, String email, String phone, String address, String gender, String job, String dob)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
    }

    public Account(String username, String password, String salt, String firstName, String lastName, String email, String phone, String address, String gender, String job, String dob, boolean admin, boolean active)
    {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
        this.admin = admin;
        this.active = active;
    }

    public Account(String username, String password, String firstName, String lastName, String email, String phone, String address, String gender, String job, String dob, String salt, int id, boolean admin, boolean active)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
        this.salt = salt;
        this.id = id;
        this.admin = admin;
        this.active = active;
    }

    public Account(int id, String username, String email, String phone, boolean admin, boolean active)
    {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.admin = admin;
        this.active = active;
    }

    /* DUCANH add properties for function: getTopUsersWithInteractions */
    private int commentCount;
    private int threadCount;

    public Account(int commentCount, int threadCount)
    {
        this.commentCount = commentCount;
        this.threadCount = threadCount;
    }

    public int getCommentCount()
    {
        return commentCount;
    }

    public void setCommentCount(int commentCount)
    {
        this.commentCount = commentCount;
    }

    public int getThreadCount()
    {
        return threadCount;
    }

    public void setThreadCount(int threadCount)
    {
        this.threadCount = threadCount;
    }

    /* DUCANH add properties for function: getTopUsersWithInteractions */
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {

    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public boolean isAdmin()
    {
        return admin;
    }

    public void setAdmin(boolean isAdmin)
    {
        this.admin = isAdmin;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean isActive)
    {
        this.active = isActive;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getJob()
    {
        return job;
    }

    public void setJob(String job)
    {
        this.job = job;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDob()
    {
        return dob;
    }

    public void setDob(String dob)
    {
        this.dob = dob;
    }

    public boolean isIsAdmin()
    {
        return admin;
    }

    public void setIsAdmin(boolean isAdmin)
    {
        this.admin = isAdmin;
    }

    public boolean isIsActive()
    {
        return active;
    }

    public void setIsActive(boolean isActive)
    {
        this.active = isActive;
    }

    public String getName()
    {
        return firstName + " " + lastName;
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
//                System.out.println("Connected to Account");
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println("Account connect: " + e.getMessage());
//        }
//    }
    public List<String> getListAccount()
    {
        Connection cnn = new DatabaseConnection().conn;
        List<String> list = new ArrayList<>();
        try
        {
            String strSelect = "select username from Account";
            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next())
            {
                list.add(rs.getString(1));
            }
        }
        catch (SQLException e)
        {
        }
        return list;
    }

    public boolean checkUser()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strSelect = "SELECT * FROM account where username = ? and password = ?;";
            ps = cnn.prepareStatement(strSelect);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next())
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            System.out.println("CheckUser: " + e.getMessage());
        }
        return false;
    }

    // Authenticate method 
    // Call inside Login method
    public boolean authenticate()
    {
        Connection cnn = new DatabaseConnection().conn;
        boolean isAdmin = false;
        try
        {

            String strSelect = "select isadmin from Account where userid = ?;";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next())
            {
                isAdmin = rs.getInt(1) == 1;
                System.out.println(isAdmin);
            }
        }
        catch (SQLException e)
        {
        }
        return isAdmin;
    }

    /*
     author: khanhnhat
     */
    // Check existing email in DB
    public boolean checkMail()
    {
        Connection cnn = new DatabaseConnection().conn;
        boolean check = false;
        try
        {

            String strSelect = "select isActive from account where email = ?;";
            ps = cnn.prepareStatement(strSelect);
            ps.setString(1, email.trim());
            rs = ps.executeQuery();
            while (rs.next())
            {
                check = true;
            }
        }
        catch (SQLException e)
        {
            System.out.println("CheckMail: " + e.getMessage());
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
        return check;
    }

    //Change password by email
    public boolean updatePassword(String pass)
    {
        Connection cnn = new DatabaseConnection().conn;
        //Hash pass
        HashPassword hashPass = new HashPassword();
        Password newPass = hashPass.hashPassword(pass);

        // Change from byte to base 64 
        String base64Pass = Base64.getEncoder().encodeToString(newPass.getHashedPassword());
        String base64Salt = Base64.getEncoder().encodeToString(newPass.getSalt());

        try
        {
            String str = "update Account set Password = ?, Salt = ? where Email = ?";
            ps = cnn.prepareStatement(str);
            ps.setString(1, base64Pass);
            ps.setString(2, base64Salt);
            ps.setString(3, this.email);
            ps.executeUpdate();
            System.out.println("Update Password successfully!");
            cnn.close();
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("UpdatePassword: " + e.getMessage());
        }
        return false;
    }

    /*
     author: dqt
     feature: login
     */
    public Account getAccountByUsername()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strSelect = "SELECT UserId,\n"
                    + "    Username,\n"
                    + "    Password,\n"
                    + "    Salt,\n"
                    + "    FirstName,\n"
                    + "    LastName,\n"
                    + "    Email,\n"
                    + "    Phone,\n"
                    + "    Address,\n"
                    + "    Dob,\n"
                    + "    Gender,\n"
                    + "    Job,\n"
                    + "    IsAdmin,\n"
                    + "    `IsActive`\n"
                    + "FROM onlinevotingsystem.`account` where username = ?;";
            ps = cnn.prepareStatement(strSelect);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next())
            {
                this.id = rs.getInt(1);
                this.password = rs.getString(3);
                this.salt = rs.getString(4);
                this.firstName = rs.getString(5);
                this.lastName = rs.getString(6);
                this.email = rs.getString(7);
                this.phone = rs.getString(8);
                this.address = rs.getString(9);
                this.dob = rs.getString(10);
                this.gender = rs.getString(11);
                this.job = rs.getString(12);
                this.admin = rs.getInt(13) == 1;
                this.active = rs.getInt(14) == 1;
                return new Account(username, password, firstName, lastName, email, phone, address, gender, job, dob, salt, id, admin, active);
            }
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("CheckAccount: " + e.getMessage());
        }
        return null;
    }

    public void addAccount()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strAdd = "INSERT INTO onlinevotingsystem.`account`\n"
                    + "(Username,\n"
                    + "Password,\n"
                    + "Salt,\n"
                    + "FirstName,\n"
                    + "LastName,\n"
                    + "Email,\n"
                    + "Phone,\n"
                    + "Address,\n"
                    + "Dob,\n"
                    + "Gender,\n"
                    + "Job,\n"
                    + "IsAdmin,\n"
                    + "IsActive)\n"
                    + "VALUES\n"
                    + "(\n"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, salt);
            pstm.setString(4, firstName);
            pstm.setString(5, lastName);
            pstm.setString(6, email);
            pstm.setString(7, phone);
            pstm.setString(8, address);
            pstm.setString(9, dob);
            pstm.setString(10, gender);
            pstm.setString(11, job);
            pstm.setBoolean(12, false);
            pstm.setBoolean(13, true);
            pstm.executeUpdate();
            cnn.close();
        }
        catch (Exception e)
        {
            System.out.println("addAccount: " + e.getMessage());
        }
    }

    public Account checkAccountId()
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strSelect = "SELECT * FROM account where userid = ?;";
            ps = cnn.prepareStatement(strSelect);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String dob = rs.getString(9);
                boolean isAdmin = rs.getInt(12) == 1;
                boolean isActive = rs.getInt(13) == 1;
                return new Account(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(10), rs.getString(11), dob, rs.getInt(1), isAdmin, isActive);
            }
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("CheckAccount: " + e.getMessage());
        }
        return null;
    }

    public void deleteAccount()
    {
        String query = "DELETE FROM account WHERE username = ?;";
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            PreparedStatement statement = cnn.prepareStatement(query);
            statement.setString(1, username);
            statement.executeUpdate();
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error deleting account: " + e.getMessage());
        }
    }

    public List<Account> searchAccountByUserName()
    {
        Connection cnn = new DatabaseConnection().conn;
        List<Account> accountList = new ArrayList<>();
        try
        {

            String strSelect = "SELECT * FROM account where username = ?;";
            ps = cnn.prepareStatement(strSelect);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String dob = rs.getString(10);
                boolean isAdmin = rs.getInt(13) == 1;
                boolean isActive = rs.getInt(14) == 1;
                Account account = new Account(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(11), rs.getString(12), dob, admin, active);
                account.setId(rs.getInt(1));
                accountList.add(account);
            }
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("CheckAccount: " + e.getMessage());
        }
        return accountList;
    }

    public boolean checkUserExist(String user)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strSelect = "SELECT username FROM account where username = ?";
            ps = cnn.prepareStatement(strSelect);
            ps.setString(1, user);
            rs = ps.executeQuery();
            if (rs.next())
            {
                return true;
            }
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("CheckUserExist: " + e.getMessage());
        }
        return false;
    }

    public boolean checkEmailExist(String email)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strSelect = "SELECT * FROM account where email = ?";
            ps = cnn.prepareStatement(strSelect);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next())
            {
                return true;
            }
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("CheckEmailExist: " + e.getMessage());
        }
        return false;
    }

    //view user profile method
    //call inside profile
    public Account getProfileInfo()
    {
        Connection cnn = new DatabaseConnection().conn;
        Account data = new Account();
        try
        {

            String strSelect = "select FirstName,LastName,Email,Phone,Address,Gender,Job,Dob from account where UserId = ? ;";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next())
            {
                firstName = rs.getString(1);
                lastName = rs.getString(2);
                email = rs.getString(3);
                phone = rs.getString(4);
                address = rs.getString(5);
                gender = rs.getString(6);
                job = rs.getString(7);
                dob = rs.getString(8);

                Account a = new Account(firstName, lastName, email, phone, address, gender, job, dob);
                data = a;
                System.out.println("data in controller: " + data);
            }
            cnn.close();
        }
        catch (Exception e)
        {
            System.out.println("getListUser: " + e.getMessage());
        }
        return data;
    }

    public void editProfile(int id)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String StrUpdate = "update account "
                    + "set FirstName= ?, "
                    + "LastName= ?, "
                    + "Email= ?, "
                    + "Phone= ?, "
                    + "Address= ?, "
                    + "Gender= ?, "
                    + "Job= ?, "
                    + "Dob= ? "
                    + "where UserId= ?";
            ps = cnn.prepareStatement(StrUpdate);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, gender);
            ps.setString(7, job);
            ps.setString(8, dob);
            ps.setInt(9, id);
            ps.executeUpdate();
            cnn.close();
        }
        catch (Exception e)
        {
            System.out.println("Update:" + e.getMessage());
        }
    }

    public Account getProfileById(int id)
    {
        Connection cnn = new DatabaseConnection().conn;
        Account data = new Account();
        try
        {

            String strSelect = "select UserId,FirstName,LastName,Email,Phone,Address,Gender,Job,Dob from account where UserId = ? ;";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next())
            {
                this.id = rs.getInt(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                email = rs.getString(4);
                phone = rs.getString(5);
                address = rs.getString(6);
                gender = rs.getString(7);
                job = rs.getString(8);
                dob = rs.getString(9);

                Account a = new Account(firstName, lastName, email, phone, address, gender, job, dob, this.id);
                data = a;
                System.out.println("data in controller: " + data);
            }
            cnn.close();
        }
        catch (Exception e)
        {
            System.out.println("getListUser: " + e.getMessage());
        }
        return data;
    }

    public List<Account> displayAccount()
    {
        Connection cnn = new DatabaseConnection().conn;
        List<Account> accountList = new ArrayList<>();
        try
        {

            String strSelect = "SELECT * FROM account";
            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String dob = rs.getString(10);
                boolean isAdmin = rs.getInt(13) == 1;
                boolean isActive = rs.getInt(14) == 1;
                Account account = new Account(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(11), rs.getString(12), dob, admin, active);
                account.setId(rs.getInt(1));
                accountList.add(account);
            }
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("CheckAccount: " + e.getMessage());
        }
        return accountList;
    }

    public List<Account> searchAccountByGender()
    {
        Connection cnn = new DatabaseConnection().conn;
        List<Account> accountList = new ArrayList<>();
        try
        {

            String strSelect = "SELECT * FROM account WHERE gender = ?;";
            ps = cnn.prepareStatement(strSelect);
            ps.setString(1, gender);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String dob = rs.getString(10);
                boolean isAdmin = rs.getInt(13) == 1;
                boolean isActive = rs.getInt(14) == 1;
                Account account = new Account(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(11), rs.getString(12), dob, admin, active);
                account.setId(rs.getInt(1));
                accountList.add(account);
            }
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("CheckAccount: " + e.getMessage());
        }
        return accountList;
    }

    public List<Account> searchAccountByName()
    {
        Connection cnn = new DatabaseConnection().conn;
        List<Account> accountList = new ArrayList<>();
        try
        {

            String strSelect = "SELECT * FROM account WHERE firstname LIKE ? OR lastname LIKE ?;";
            ps = cnn.prepareStatement(strSelect);
            ps.setString(1, "%" + firstName + "%");
            ps.setString(2, "%" + lastName + "%");
            rs = ps.executeQuery();
            while (rs.next())
            {
                String dob = rs.getString(10);
                boolean isAdmin = rs.getInt(13) == 1;
                boolean isActive = rs.getInt(14) == 1;
                Account account = new Account(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(11), rs.getString(12), dob, admin, active);
                account.setId(rs.getInt(1));
                accountList.add(account);
            }
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("CheckAccount: " + e.getMessage());
        }
        return accountList;
    }

    public List<Account> searchAccountByMale()
    {
        Connection cnn = new DatabaseConnection().conn;
        List<Account> accountList = new ArrayList<>();
        try
        {

            String strSelect = "SELECT * FROM account WHERE gender = 'male';";
            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String dob = rs.getString(10);
                boolean isAdmin = rs.getInt(13) == 1;
                boolean isActive = rs.getInt(14) == 1;
                Account account = new Account(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(11), rs.getString(12), dob, admin, active);
                account.setId(rs.getInt(1));
                accountList.add(account);
            }
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("CheckAccount: " + e.getMessage());
        }
        return accountList;
    }

    public List<Account> searchAccountByFemale()
    {
        Connection cnn = new DatabaseConnection().conn;
        List<Account> accountList = new ArrayList<>();
        try
        {

            String strSelect = "SELECT * FROM account WHERE gender = 'female';";
            ps = cnn.prepareStatement(strSelect);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String dob = rs.getString(10);
                boolean isAdmin = rs.getInt(13) == 1;
                boolean isActive = rs.getInt(14) == 1;
                Account account = new Account(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(11), rs.getString(12), dob, admin, active);
                account.setId(rs.getInt(1));
                accountList.add(account);
            }
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("CheckAccount: " + e.getMessage());
        }
        return accountList;
    }

    public void deleteAccount(int id)
    {
        String query = "DELETE FROM account WHERE userid = ?;";
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            PreparedStatement statement = cnn.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error deleting account: " + e.getMessage());
        }
    }

    public String getFormattedDob() throws ParseException
    {
        java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
        String dob1 = new SimpleDateFormat("dd-MM-yyyy").format(d);
        return dob1;
    }

    public String getNameById(int id)
    {
        Connection cnn = new DatabaseConnection().conn;
        try
        {

            String strSelect = "SELECT Username FROM account WHERE UserId = ?;";
            ps = cnn.prepareStatement(strSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next())
            {
                return rs.getString(1);
            }
            cnn.close();
        }
        catch (SQLException e)
        {
            System.out.println("CheckAccount: " + e.getMessage());
        }
        return null;
    }

    //count total user of website
    public int getUserCount()
    {
        int userCount = 0;
        String query = "SELECT "
                + "COUNT(*) "
                + "AS "
                + "Count "
                + "FROM "
                + "ACCOUNT ";
        try
        {
            Connection cnn = new DatabaseConnection().conn;
            PreparedStatement ps = cnn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                userCount = rs.getInt("Count");
            }

            rs.close();
            ps.close();
            cnn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return userCount;
    }

    //get necessary infomation of account for dashboard
    public ArrayList<Account> getAccountForDashboard()
    {
        ArrayList<Account> dataForDashboard = new ArrayList<>();
        String query = "SELECT "
                + "UserId, "
                + "Username, "
                + "Email, "
                + "Phone, "
                + "IsAdmin, "
                + "IsActive  "
                + "FROM onlinevotingsystem.account;";
        try
        {
            Connection cnn = new DatabaseConnection().conn;
            PreparedStatement ps = cnn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int userId = rs.getInt(1);
                String userName = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                boolean isAdmin = rs.getBoolean(5);
                boolean isActive = rs.getBoolean(6);
                Account a = new Account(userId, userName, email, phone, isAdmin, isActive);
                dataForDashboard.add(a);
            }
            rs.close();
            ps.close();
            cnn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return dataForDashboard;
    }

    //get top interact users
    public ArrayList<Account> getTopUsersWithInteractions(int limit)
    {
        ArrayList<Account> topUsers = new ArrayList<>();
        Connection cnn = new DatabaseConnection().conn;
        try
        {
            String query = "SELECT account.UserId, "
                    + "account.Username, "
                    + "COUNT(DISTINCT comment.CommentId) AS CommentCount, "
                    + "COUNT(DISTINCT thread.ThreadId) AS ThreadCount "
                    + "FROM account "
                    + "LEFT JOIN comment "
                    + "ON comment.AccountId = account.UserId "
                    + "LEFT JOIN thread "
                    + "ON thread.AccountId = account.UserId "
                    + "GROUP BY account.UserId "
                    + "ORDER BY (CommentCount + ThreadCount) DESC "
                    + "LIMIT ?";
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int userId = rs.getInt("UserId");
                String username = rs.getString("Username");
                int commentCount = rs.getInt("CommentCount");
                int threadCount = rs.getInt("ThreadCount");
                Account account = new Account(userId, username);
                account.setCommentCount(commentCount);
                account.setThreadCount(threadCount);
                topUsers.add(account);
            }

            rs.close();
            ps.close();
            cnn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return topUsers;
    }

    public int getIdByEmail(String email)
    {
        Connection cnn = new DatabaseConnection().conn;
        Account data = new Account();
        try
        {

            String strSelect = "select UserId from account where Email = ? ;";
            ps = cnn.prepareStatement(strSelect);
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next())
            {
                return rs.getInt(1);
            }
            cnn.close();
        }
        catch (Exception e)
        {
            System.out.println("getIdByEmail: " + e.getMessage());
        }
        return 0;
    }

}
