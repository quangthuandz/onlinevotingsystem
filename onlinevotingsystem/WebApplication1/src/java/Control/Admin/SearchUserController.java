/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Admin;

import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**

 @author Hanh
 */
@WebServlet(name = "SearchUserController", urlPatterns
        =
        {
            "/search_user"
        })

public class SearchUserController extends HttpServlet
{

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     Handles the HTTP <code>GET</code> method.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // Access Control for Admin
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (!a.isAdmin())
        {
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter())
            {
                out.print("Access Denied.");
                return;
            }
        }
        List<Account> userList = new ArrayList<>();
        Account account = new Account(null, null, null, null, null, null, null, null, null, null, 0, false, false);
        userList = account.displayAccount();
        for (Account acc : userList)
        {
            System.out.println(acc.getUsername());
        }
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("WEB-INF/SearchUser.jsp").forward(request, response);
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
        // Access Control for Admin
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (!a.isAdmin())
        {
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter())
            {
                out.print("Access Denied.");
                return;
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String searchQuery = request.getParameter("searchQuery");
        String searchType = request.getParameter("searchType");
        List<Account> userList = new ArrayList<>();
        if (searchType.equals("Male"))
        {
            Account account = new Account(null, null, null, null, null, null, null, "male", null, null, 0, false, false);
            userList = account.searchAccountByMale();
        }
        if (searchType.equals("Female"))
        {
            Account account = new Account(null, null, null, null, null, null, null, "female", null, null, 0, false, false);
            userList = account.searchAccountByFemale();
        }
        else if (searchType.equals("Name"))
        {
            String firstName = searchQuery;
            String lastName = searchQuery;
            Account account = new Account(null, null, firstName, lastName, null, null, null, null, null, null, 0, false, false);
            userList = account.searchAccountByName();
        }
        else if (searchType.equals("Username"))
        {
            Account account = new Account(searchQuery);
            userList = (List<Account>) account.searchAccountByUserName();
        }
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("WEB-INF/SearchUser.jsp").forward(request, response);
    }

    /**
     Returns a short description of the servlet.

     @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
