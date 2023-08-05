/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Admin;

import Model.Account;
import Model.Campaign;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author khanh
 */
@WebServlet(name = "AdminHomeController", urlPatterns
        = {
            "/admin"
        })
public class AdminHomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        Campaign c = new Campaign();
        if (a == null || !a.isAdmin()) {
            request.getRequestDispatcher("WEB-INF/AccessDenied.jsp").forward(request, response);
        } else {
            ArrayList<Campaign> data = c.getCampaignByAdmin();
            request.setAttribute("data", data);
            request.setAttribute("account", a);
            request.getRequestDispatcher("WEB-INF/AdminHomePage.jsp").forward(request, response);
            // Khi xóa 1 campaign
            if (request.getParameter("mod") != null && request.getParameter("mod").equals("remove")) {
                c.removeCampaign(request.getParameter("campaignId"));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Nhận từ khóa tìm kiếm
        String search_keyword = request.getParameter("campaign_search");
        // Nhận filter sorting
        String search_filter = request.getParameter("sort_By");

        // Gọi hàm lấy dữ liệu campaign bằng từ khóa
        Campaign c = new Campaign();
        ArrayList<Campaign> data = c.getCampaignBySearching(search_keyword, search_filter);
        System.out.println(data);
        request.setAttribute("data", data);
        Campaign c1 = new Campaign();
        String filter = request.getParameter("filter");
        System.out.println("Filter: " + filter);
        if ("available".equals(filter)) {
            data = c1.filterCampaignbyAvaiable();
            request.setAttribute("data", data);
            request.getRequestDispatcher("WEB-INF/AdminHomePage.jsp").forward(request, response);
        } else if ("locked".equals(filter)) {
            data = c1.filterCampaignbyLocked();
            request.setAttribute("data", data);
            request.getRequestDispatcher("WEB-INF/AdminHomePage.jsp").forward(request, response);
        } else if ("all".equals(filter)) {
            data = c1.getCampaignByAdmin();
            request.setAttribute("data", data);
            request.getRequestDispatcher("WEB-INF/AdminHomePage.jsp").forward(request, response);
        } else {
            System.out.println("No filter");
            request.getRequestDispatcher("WEB-INF/AdminHomePage.jsp").forward(request, response);
        }
        request.getRequestDispatcher("WEB-INF/AdminHomePage.jsp").forward(request, response);
    }

}
