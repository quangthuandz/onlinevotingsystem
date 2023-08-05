/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Campaign;

import Model.Account;
import Model.Campaign;
import Model.CampaignAccount;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**

 @author khanh
 */
@WebServlet(name = "JoinCampaignController", urlPatterns =
{
    "/join"
})
public class JoinCampaignController extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // Get data
        int cId = Integer.parseInt(request.getParameter("id"));

        // Get userid
        HttpSession hs = request.getSession();
        Account a = (Account) hs.getAttribute("account");
        int uId = a.getId();

        Campaign c = new Campaign(cId);
        c.getCampaignInfoById();
        if (c.isIsPublic())
        {
            CampaignAccount ca = new CampaignAccount(cId, uId, false, null);
            ca.add(ca);
            response.sendRedirect("ViewCampaignDetails?id=" + cId);
        }
        else
        {
            request.setAttribute("c", c);
            request.getRequestDispatcher("WEB-INF/JoinCampaign.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        int cId = Integer.parseInt(request.getParameter("cId"));

        // Get password
        String enteredPassword = request.getParameter("password") != null ? request.getParameter("password") : "";

        // Get userid
        HttpSession hs = request.getSession();
        Account a = (Account) hs.getAttribute("account");
        int uId = a.getId();

        Campaign c = new Campaign(cId);
        c.getCampaignInfoById();

        if (enteredPassword.equals(c.getPassword()))
        {
            CampaignAccount ca = new CampaignAccount(cId, uId, false, null);
            ca.add(ca);
            response.sendRedirect("ViewCampaignDetails?id=" + cId);
        }
        else
        {
            // Wrong password
            request.setAttribute("msg", "Wrong Password");
            request.setAttribute("c", c);
            request.setAttribute("password", enteredPassword);
            request.getRequestDispatcher("WEB-INF/JoinCampaign.jsp").forward(request, response);
        }
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
