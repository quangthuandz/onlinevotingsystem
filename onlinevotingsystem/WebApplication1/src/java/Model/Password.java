package Model;

/**

 @author dqt
 */
public class Password
{

    private byte[] salt;
    private byte[] hashedPassword;

    public Password(byte[] salt, byte[] hashedPassword)
    {
        this.salt = salt;
        this.hashedPassword = hashedPassword;
    }

    public byte[] getSalt()
    {
        return salt;
    }

    public byte[] getHashedPassword()
    {
        return hashedPassword;
    }
}
