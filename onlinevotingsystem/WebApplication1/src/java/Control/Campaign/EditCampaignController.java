/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Campaign;

import Model.Campaign;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

/**

 @author khanh
 */
@WebServlet(name = "EditCampaignController", urlPatterns =
{
    "/edit_campaign"
})
public class EditCampaignController extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Campaign c = new Campaign(id);
        c.getCampaignInfoById();
        
        request.setAttribute("c", c);
        request.getRequestDispatcher("WEB-INF/EditCampaign.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Date endTime = Date.valueOf(request.getParameter("endTime"));
        String desc = request.getParameter("desc");
        int ruleId = Integer.parseInt(request.getParameter("ruleId"));
        boolean isPublic = "1".equals(request.getParameter("isPublic"));
        String password = request.getParameter("password");

        Campaign c = new Campaign(id, ruleId, 0, name, password, null, endTime, null, desc, "", isPublic);
        c.editCampaignById();

        response.sendRedirect("ViewCampaignDetails?id=" + id);
//        request.getRequestDispatcher("").forward(request, response);
    }

}
