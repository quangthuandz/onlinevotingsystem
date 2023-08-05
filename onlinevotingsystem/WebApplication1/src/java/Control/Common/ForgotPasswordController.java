/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Common;

import Model.Account;
import Model.EmailSender;
import Model.OTP;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**

 @author khanh
 */
@WebServlet(name = "ForgotPasswordController", urlPatterns =
{
    "/forgot_password"
})
public class ForgotPasswordController extends HttpServlet
{
    // Forward to ForgotPassword.jspge
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("WEB-INF/ForgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // Get email
        String email = request.getParameter("email");

        // Check for existing email
        Account a = new Account("", "", email);
        boolean check = a.checkMail();
        System.out.println("check: " + check);

        if (!check)
        {
            // Respond w error msg
            request.setAttribute("resp", "Invalid Email!");
            request.getRequestDispatcher("WEB-INF/ForgotPassword.jsp").forward(request, response);
        }
        else
        {
            // Generate Timestamp
            Date dt = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt);
            System.out.println(timestamp);

            // Generate OTP
            OTP otpGenerator = new OTP(email, timestamp);
            otpGenerator.generate(6);

            // Store data in database
            otpGenerator.insert();

            // Send mail
            String emailSubject = "Password Reset OTP";
            String emailBody = "Your password reset OTP is: " + otpGenerator.getOtp();
            EmailSender.sendEmail(email, emailSubject, emailBody);

            // Forward to reset password page
            request.setAttribute("email", email);
            request.getRequestDispatcher("WEB-INF/EnterOTP.jsp").forward(request, response);
        }
    }
}
