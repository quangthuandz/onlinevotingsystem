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
 *
 * @author khanh
 */
@WebServlet(name = "LogInController", urlPatterns
        = {
            "/login"
        })
public class LogInController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LogInController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LogInController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
        request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account a = new Account(username);
        String msg = "", msg1 = "";
        if (a.checkUserExist(username)) {
            a = a.getAccountByUsername();
            boolean comparePass = false;
            
            //decode salt and pass
            byte[] byteArray = Base64.getDecoder().decode(a.getSalt());
            byte[] byteArray1 = Base64.getDecoder().decode(a.getPassword());

            //hash input password with salt 
            HashPassword hashPass = new HashPassword();
            Password pass = hashPass.hashPasswordWithSalt(password, byteArray);

            //compare pass from db with hash pass
            comparePass = Arrays.equals(byteArray1, pass.getHashedPassword());
            //System.out.println(comparePass);

            if (comparePass) {
                //System.out.println("success");
                // Login successfully
                HttpSession session = request.getSession();
                session.setAttribute("account", a);
                if (a.isAdmin()) {
                    System.out.println("admin");
                    // Admin page
                    response.sendRedirect("admin");
                } else {
                    // User page
                    response.sendRedirect("home");
                }
            } else {
                System.out.println("fail");
                msg = "Incorrect username or password. Please try again!";
                request.setAttribute("error", msg);
                // Login failed
                request.setAttribute("user", username);
                request.setAttribute("pass", password);
                request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
            }
        } else {
            msg1 = "username does not exist";
        }
        request.setAttribute("user", username);
        request.setAttribute("pass", password);
        request.setAttribute("error1", msg1);
        if (!msg1.isEmpty() || !msg.isEmpty()) {
            request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
        }
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
