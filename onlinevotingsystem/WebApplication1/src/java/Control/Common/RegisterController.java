package Control.Common;

import Model.Account;
import Model.EmailSender;
import Model.HashPassword;
import Model.OTP;
import Model.Password;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thuan
 */
@WebServlet(name = "RegisterController", urlPatterns
        = {
            "/register"
        })
public class RegisterController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/Register.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String jobtitle = request.getParameter("job");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String repass = request.getParameter("repasss");

        //Hash pass
        HashPassword hashPass = new HashPassword();
        Password pass = hashPass.hashPassword(password);

        // Change from byte to base 64 
        String base64Pass = Base64.getEncoder().encodeToString(pass.getHashedPassword());
        String base64Salt = Base64.getEncoder().encodeToString(pass.getSalt());
        
        Account a = new Account(user, base64Pass, base64Salt, firstname, lastname, email, phone, address, gender, jobtitle, dob, false, true);
        
        //Date of birth validation
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        try {
            d = (Date) dateFormat.parse(dob);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Erro for each validation
        String msg = "",msg1="",msg2="",msg3="",msg4="",msg5="";
        
        //Validate informations
        if (a.checkUserExist(user)) {
            msg1 = "The account have existed";
        }
        if (d.after(new Date())) {
            msg2 = "Date of birth not valid";
        }
        if (!password.equals(repass)) {
            msg3 = "Password does not match";
        }
        if (a.checkEmailExist(email)) {
            msg4 = "email existed";
        }
        if (!phone.matches("^0[0-9]{9,10}$")) {
            msg5 = "phone number not valid";
        }
        
        //if register ok
        if((msg1+msg2+msg3+msg4+msg5).isEmpty()){
            
            //create timestamp for db
            Date dt = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt);

            // Generate OTP
            OTP otpGenerator = new OTP(email, timestamp);
            otpGenerator.generate(6);

            // Store data in database
            otpGenerator.insert();

            // Send mail
            String emailSubject = "Password Reset OTP";
            String emailBody = "Your password reset OTP is: " + otpGenerator.getOtp();
            EmailSender.sendEmail(email, emailSubject, emailBody);
            
            // set http attribute for a, otp and email
            HttpSession session = request.getSession();
            session.setAttribute("account", a);
            session.setAttribute("otpran", otpGenerator.getOtp());
            session.setAttribute("email", email);
            request.getRequestDispatcher("WEB-INF/ConfirmOTP.jsp").forward(request, response);
        }
        
        // send error if register fail
        request.setAttribute("a", a);
        request.setAttribute("error1", msg1);
        request.setAttribute("error2", msg2);
        request.setAttribute("error3", msg3);
        request.setAttribute("error4", msg4);
        request.setAttribute("error5", msg5);
        request.getRequestDispatcher("WEB-INF/Register.jsp").forward(request, response);
    }
        

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
