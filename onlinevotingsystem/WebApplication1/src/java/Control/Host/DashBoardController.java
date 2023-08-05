/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Host;

import Model.Campaign;
import Model.CampaignAccount;
import Model.CommentPost;
import Model.Vote;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author quang
 */
@WebServlet(name = "DashBoardController", urlPatterns = {"/hostdashboard"})
public class DashBoardController extends HttpServlet {

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
            out.println("<title>Servlet DashBoardController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashBoardController at " + request.getContextPath() + "</h1>");
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
        String id = (String) request.getParameter("id");
        Campaign c = new Campaign();
        Timestamp t = c.getEndTimeByid(Integer.parseInt(id));
        Timestamp startTime = c.getStartTimeByid(Integer.parseInt(id));

        CampaignAccount ca = new CampaignAccount();
        int voter = ca.countVoterinCampaign(Integer.parseInt(id));
        Vote v = new Vote();
        int votedBallot = v.countVotedBallot(Integer.parseInt(id));
        System.out.println("VVVVVVVVVVVVV " + votedBallot);

        Model.Thread td = new Model.Thread();
        int thread = td.countThreadInCampaign(Integer.parseInt(id));
        System.out.println("threads: " + thread);

        CommentPost cp = new CommentPost();
        int comment = cp.countCommentInThread(Integer.parseInt(id));
        System.out.println("comment: " + comment);

        JSONObject jsonData = new JSONObject();
        jsonData.put("comments", thread);

        HttpSession session = request.getSession();
        Map<Integer, List<Integer>> campaignDataMap = (Map<Integer, List<Integer>>) session.getAttribute("campaignDataMap");
        for (Map.Entry<Integer, List<Integer>> entry : campaignDataMap.entrySet()) {
            Integer key = entry.getKey();
            List<Integer> value = entry.getValue();
            System.out.println("KKKKKKK: " + key + ", VVVVVV: " + value);
        }
        List<Integer> data = campaignDataMap.get(Integer.parseInt(id));
        if (data == null) {
            data = new ArrayList<>(); // Hoặc giá trị mặc định khác tùy vào yêu cầu của bạn
        }
        Map<Integer, List<Integer>> campaignDataMapComment = (Map<Integer, List<Integer>>) session.getAttribute("campaignDataMapComment");
        List<Integer> dataComment = campaignDataMapComment.get(Integer.parseInt(id));
        if (dataComment == null) {
            dataComment = new ArrayList<>(); // Hoặc giá trị mặc định khác tùy vào yêu cầu của bạn
        }
        
        Map<Integer, List<Integer>> campaignDataMapVote = (Map<Integer, List<Integer>>) session.getAttribute("campaignDataMapVote");
        List<Integer> dataVote = campaignDataMapVote.get(Integer.parseInt(id));
        if (dataVote == null) {
            dataVote = new ArrayList<>(); // Hoặc giá trị mặc định khác tùy vào yêu cầu của bạn
        }

        System.out.println("IDDDDD: " + data.size());
        request.setAttribute("data", data);
        request.setAttribute("dataComment", dataComment);
        request.setAttribute("dataVote", dataVote);
        request.setAttribute("camId", id);
        request.setAttribute("endTime", t);
        request.setAttribute("startTime", startTime);
        request.setAttribute("voter", voter);
        request.setAttribute("votedballot", votedBallot);
        request.setAttribute("threads", thread);
        request.setAttribute("comments", comment);
        request.getRequestDispatcher("WEB-INF/HostDashboard.jsp").forward(request, response);
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
