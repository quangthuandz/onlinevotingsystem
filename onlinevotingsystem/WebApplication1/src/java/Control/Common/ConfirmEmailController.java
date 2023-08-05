/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Common;

import Model.Account;
import Model.OTP;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**

 @author dqt
 */
@WebServlet(name = "ConfirmEmailController", urlPatterns =
{
    "/confirmemail"
})
public class ConfirmEmailController extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("WEB-INF/ConfirmOTP.jsp").forward(request, response);
    }

    /**
     Handles the HTTP <code>POST</code> method.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // get http session email,otp in db
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");      
        String otpran = (String) session.getAttribute("otpran");
        
        // get otp input
        String otp = request.getParameter("otp");
        
        
        Date dt = new Date(System.currentTimeMillis());
        String curTIme = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt);        // Get current time

        // Initialize enteredOP
        OTP enteredOTP = new OTP(otpran, email, curTIme);

        // Check for valid OTP
        if (otp.equals(enteredOTP.getOTP()))
        {
            // If valid, proceed to reset password
            HttpSession session2 = request.getSession();
            Account a = (Account) session2.getAttribute("account");
            a.addAccount();
            request.getRequestDispatcher("WEB-INF/Success.jsp").forward(request, response);
        }
        else
        {
            // If invalid, display error -> re-enter
            String msg = "Invalid OTP. Please enter again!";
            System.out.println("Wrong OTP");
            request.setAttribute("msg", msg);
            request.setAttribute("email", email);
            request.getRequestDispatcher("WEB-INF/ConfirmOTP.jsp").forward(request, response);
        }

        request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);

    }

}
