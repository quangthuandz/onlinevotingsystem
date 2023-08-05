/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.User;

import Model.Account;
import Model.Campaign;
import Model.CampaignAccount;
import Model.CommentPost;
import Model.Like;
import Model.VotingObject;
import Model.Thread;
import Model.Vote;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 @author ducan
 */
public class ViewCampaignDetails extends HttpServlet
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
        List<CommentPost> commentList = new ArrayList<>();
        CommentPost comment = new CommentPost();
        commentList = comment.DisplayComment();
        request.setAttribute("commentList", commentList);
        int cId = Integer.parseInt(request.getParameter("id"));
        Campaign c = new Campaign(cId);
        c.getCampaignById();
        request.setAttribute("c", c);

        // Get userid
        HttpSession hs = request.getSession();
        Account a = (Account) hs.getAttribute("account");
        int uId = a.getId();

        CampaignAccount ca = new CampaignAccount(cId, uId);
        ca.findByCampaignAndAccount();

        if (ca.getId() == 0)
        {
            response.sendRedirect("join?id=" + cId);
        }
        else
        {
            //get details of voting object
            VotingObject dataOfVotingObject = new VotingObject();
            ArrayList<VotingObject> data = dataOfVotingObject.getVotingObjectById(cId);
            request.setAttribute("data", data);
            //check if current Account isHost
            boolean isHost = ca.isHost();
            //check if current Date is after the endTime
            Date d = new Date();
            boolean HasEnd = d.after(c.getEndTime());

            // Get voting object result
            Vote v = new Vote(uId, cId);
            List<Map.Entry<Integer, Integer>> noOfVotes = v.getNoOfVotes();
            request.setAttribute("vote", v);
            request.setAttribute("noOfVotes", noOfVotes);

            // Get threads by CampaignId
            Thread t = new Thread();
            ArrayList<Thread> threads = new ArrayList<>();
            // Sort Thread
            if (request.getParameter("sort_filter") != null)
            {
                String sort_filter = request.getParameter("sort_filter");
                threads = t.getListThreadByCampaignIdAndSort(cId, sort_filter);
                System.out.println(sort_filter);
                request.setAttribute("sort_filter", sort_filter);
            }
            else
            {
                threads = t.getListThreadByCampaignId(cId);
            }

            int pageNumber = request.getParameter("pageNumber") == null ? 1 : Integer.parseInt(request.getParameter("pageNumber"));
            int pageSize = request.getParameter("pageSize") == null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
            System.out.println("page: " + pageNumber + " " + pageSize + " " + Math.ceil((double) threads.size() / (double) pageSize));

            int startIdx = (pageNumber - 1) * pageSize;
            int endIdx = Math.min(startIdx + pageSize, threads.size());

            List<Thread> paginatedList = threads.subList(startIdx, endIdx);

            request.setAttribute("totalPages", Math.ceil((double) threads.size() / (double) pageSize));
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("isHost", isHost);
            request.setAttribute("dataThread", paginatedList);

            request.setAttribute("ca", ca);
            request.setAttribute("Host", ca.isHost());
            request.setAttribute("ca", ca);
            request.setAttribute("HasEnd", HasEnd);
            request.setAttribute("Cid", cId);
            request.setAttribute("a", a);

            // For like
            Like l = new Like();
            int accountId = a.getId();
            request.setAttribute("accountId", accountId);

            if (HasEnd)
            {
                request.getRequestDispatcher("WEB-INF/ViewCampaignDetailsFinished.jsp").forward(request, response);
            }
            else
            {
                request.getRequestDispatcher("WEB-INF/ViewCampaignDetails.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        int Cid = Integer.parseInt(request.getParameter("Cid"));

        //post a new thread
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        String content = request.getParameter("content");
        int Uid = a.getId();
        System.out.println("Uid: " + Uid);
        Campaign c = new Campaign();
        c.setCampaignInfoById(Cid);
        Thread t = new Thread(Uid, Cid);
        if (c.getCreatedBy() == Uid)
        {
            t.createThread(Uid, Cid, content, 1);
        }
        else
        {
            t.createThread(Uid, Cid, content, 0);
        }

        request.setAttribute("t", t);

        response.sendRedirect("ViewCampaignDetails?id=" + Cid);
    }
}
