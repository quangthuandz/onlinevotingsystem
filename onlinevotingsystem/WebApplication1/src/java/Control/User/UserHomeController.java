/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.User;

import Model.Account;
import Model.Campaign;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**

 @author khanh
 */
@WebServlet(name = "HomeController", urlPatterns
        =
        {
            "/home"
        })
public class UserHomeController extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Access Control - not logged in
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a == null) {
            request.getRequestDispatcher("WEB-INF/AccessDenied.jsp").forward(request, response);
        } else {
            if (a.isAdmin()) // Go to admin homepage
            {
                response.sendRedirect("admin");
            } else // Go to userhome
            {
                int uId = a.getId();
                Campaign campaign = new Campaign();
                ArrayList<Campaign> c = campaign.getCampaignByHost(uId);
                request.setAttribute("host", c);
                ArrayList<Campaign> accjoin = campaign.getCampaignJoin(uId);
                request.setAttribute("join", accjoin);
                ArrayList<Campaign> unjoin = campaign.getCampaignByUnJoin(uId);
                request.setAttribute("unjoin", unjoin);
                Campaign c1 = new Campaign();
                ArrayList<Campaign> data = c1.getCampaign(a.getId());
                request.setAttribute("data", data);
                request.setAttribute("account", a);
                request.getRequestDispatcher("WEB-INF/HomePage.jsp").forward(request, response);
            }
        }

    }

}
