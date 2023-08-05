package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**

 @author dqt
 */
public class HashPassword
{

    private static final String PEPPER = "thuan2114"; //

    public Password hashPassword(String password)
    {
        try
        {
            // Khởi tạo đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Tạo salt ngẫu nhiên
            // Khi tao nó se la 1 mang byte 16 phan tu nhung khi in ra se hien theo String qua chuyen doi he co so
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Thêm salt vào mật khẩu
            // Tham so truyen vao la byte
            md.update(salt);

            // Thêm pepper vào mật khẩu
            byte[] pepperedPassword = (password + PEPPER).getBytes();
            md.update(pepperedPassword);

            // Mã hóa mật khẩu
            byte[] hashedPassword = md.digest(pepperedPassword);
            return new Password(salt, hashedPassword);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public Password hashPasswordWithSalt(String password, byte[] salt)
    {
        try
        {
            // Khởi tạo đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");


            // Thêm salt vào mật khẩu
            md.update(salt);

            // Thêm pepper vào mật khẩu
            byte[] pepperedPassword = (password + PEPPER).getBytes();
            md.update(pepperedPassword);

            // Mã hóa mật khẩu
            byte[] hashedPassword = md.digest(pepperedPassword);
            return new Password(salt, hashedPassword);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }

    }

}
