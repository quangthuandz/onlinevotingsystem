/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Host;

import Model.Account;
import Model.Campaign;
import Model.CampaignAccount;
import Model.EmailSender;
import Model.Notifications;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author quang
 */
@WebServlet(name = "AddVoterController", urlPatterns
        = {
            "/addvoter"
        })
public class AddVoterController extends HttpServlet {

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
            out.println("<title>Servlet AddVoterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddVoterController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        Account a = new Account();
        Account a1 = new Account();
        Notifications n = new Notifications();
        HttpSession session = request.getSession();
        String[] emailList = (String[]) session.getAttribute("emaillist");
        int campaignId = (int) session.getAttribute("campaignId");
        Campaign c = new Campaign(campaignId);
        c.getCampaignById();
        a1 = (Account) session.getAttribute("account");
        String emailSubject = "Campaign Invitation";
        String emailBody = "You are invited to the campaign: http://localhost:9999/OnlineVotingSystem/ViewCampaignDetails?id=" + campaignId;
        int temp = 0;
        for (int i = 0; i < emailList.length; i++) {
            temp = a.getIdByEmail(emailList[i]);
            CampaignAccount ca = new CampaignAccount(campaignId, temp);
            ca.add(ca);
            n.AddVoterNotification(temp, a1.getId(), c.getName());
            EmailSender.sendEmail(emailList[i], emailSubject, emailBody);
        }
        request.getRequestDispatcher("WEB-INF/Detail.jsp").forward(request, response);
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
        processRequest(request, response);
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