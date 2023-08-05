/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.User;

import Model.Account;
import Model.Campaign;
import Model.Vote;
import Model.VotingObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EditVoteController", urlPatterns = {"/editVote"})
public class EditVoteController extends HttpServlet {

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
            out.println("<title>Servlet EditVoteController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditVoteController at " + request.getContextPath() + "</h1>");
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
        HttpSession hs = request.getSession();
        Account a = (Account) hs.getAttribute("account");
        if (a != null) {
            try {
                String id = request.getParameter("id");
                Campaign c = new Campaign(Integer.parseInt(id));
                c.getCampaignById();
                int RuleID = c.getRuleId();  
                request.setAttribute("c", c);
                
                if (RuleID == 1) {
                    VotingObject vo = new VotingObject();
                    ArrayList<VotingObject> candidates = vo.getListOptionByCampaignId(Integer.parseInt(id));
                    request.setAttribute("candidates", candidates);
                    Vote v = new Vote(a.getId(), c.getId());
                    v.getSingleVote();
                    request.setAttribute("v", v);
                    request.getRequestDispatcher("WEB-INF/EditSingleVote.jsp").forward(request, response);
                } else {
                    VotingObject vo = new VotingObject();
                    ArrayList<VotingObject> candidates = new ArrayList<>();
                    candidates = vo.getVotedOption(Integer.parseInt(id),a.getId());
                    request.setAttribute("candidates", candidates);
                    request.getRequestDispatcher("WEB-INF/EditRankingVote.jsp").forward(request, response);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditVoteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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
        HttpSession hs = request.getSession();
        Account a = (Account) hs.getAttribute("account");
        if (a != null) {
            String cid = request.getParameter("Cid");
            if (cid != null) {
                int Cid = Integer.parseInt(cid);
                System.out.println("CampaignId: " + Cid);
                Campaign c = new Campaign(Cid);
                c.getCampaignById();
                int RuleId = c.getRuleId();
                System.out.println("RuleId:" + RuleId);
                if (RuleId == 1) {
                    int choice = Integer.parseInt(request.getParameter("choice"));
                    Vote v = new Vote(a.getId(), choice, Cid);
                    v.UpdateSingleVote();
                    System.out.println("Update Successful!");
                    response.sendRedirect("ViewCampaignDetails?id=" + Cid);
                }
                if (RuleId == 2) {
                    int BallotSize = Integer.parseInt(request.getParameter("ballotSize"));

                    ArrayList<Vote> Votes = new ArrayList<>();
                    for (int i = 0; i < BallotSize; i++) {
                        int VoID = Integer.parseInt(request.getParameter("id" + i));
                        int Score = Integer.parseInt(request.getParameter("score" + i));
                        Vote v = new Vote(a.getId(), VoID, Cid, Score);
                        Votes.add(v);
                    }

                    for (Vote v : Votes) {
                        v.UpdateRankingVote();
                    }
                    System.out.println("Update Successfully!");
                    response.sendRedirect("ViewCampaignDetails?id=" + Cid);
                }

            }
        } else {
            request.getRequestDispatcher("WEB-INF/AccessDenied.jsp").forward(request, response);
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
