/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Common;

import Model.Account;
import Model.HashPassword;
import Model.Password;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Base64;

/**

 @author khanh
 */
@WebServlet(name = "ChangePasswordController", urlPatterns =
{
    "/change_password"
})
public class ChangePasswordController extends HttpServlet
{

    // Direct to page change password
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // Access Control - not logged in
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a == null)
        {
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter())
            {
                out.print("Access Denied.");
                return;
            }
        }
        request.getRequestDispatcher("WEB-INF/ChangePassword.jsp").forward(request, response);
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
        // Get data
        String curpass = request.getParameter("curpass");
        String newpass = request.getParameter("newpass");
        String renewpass = request.getParameter("renewpass");

        // Get acc from HttpSession
        HttpSession hs = request.getSession();
        Account a = (Account) hs.getAttribute("account");

        boolean comparePass = false;

        //decode salt and pass
        byte[] byteArray = Base64.getDecoder().decode(a.getSalt());
        byte[] byteArray1 = Base64.getDecoder().decode(a.getPassword());

        //hash input password with salt 
        HashPassword hashPass = new HashPassword();
        Password pass = hashPass.hashPasswordWithSalt(curpass, byteArray);

        //compare pass from db with hash pass
        comparePass = Arrays.equals(byteArray1, pass.getHashedPassword());
        //System.out.println(comparePass);

        // Proceed
        if (!comparePass)        // Incorrect current password
        {
            String msg = "Incorrect password";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("WEB-INF/ChangePassword.jsp").forward(request, response);
        }
        else
        {
            if (!newpass.equals(renewpass))         // Unmatched repass
            {
                String msg = "Unmatched password";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("WEB-INF/ChangePassword.jsp").forward(request, response);
            }
            else        // Update new password & go to HomePage
            {
                a.updatePassword(newpass);
                response.sendRedirect("userhome");
            }
        }
    }
}
