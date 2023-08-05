/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Campaign;

import Model.Account;
import Model.Campaign;
import Model.Vote;
import Model.VotingObject;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**

 @author khanh
 */
@WebServlet(name = "CampaignStatisticsUserController", urlPatterns =
{
    "/campaign_statistics"
})
public class CampaignStatisticsUserController extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        // Receive data
        int campaignId = Integer.parseInt(request.getParameter("campaignId"));
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");

        Vote vote = new Vote(a.getId(), campaignId);

        // Get number of votes
        List<Map.Entry<Integer, Integer>> noOfVotes = vote.getNoOfVotes();

        // Get current vote 
        Map<String, Integer> myCurrentVote = vote.getCurrentVote();
        String myCurrentVoteJson = new Gson().toJson(myCurrentVote);

        //Get Result
        Map<String, Integer> currentResult = vote.getCurrentResult();
        String currentResultJson = new Gson().toJson(currentResult);

        List<Map.Entry<String, Integer>> leadingOption = vote.getLeadingOption();

        // Current status
        Campaign campaign = new Campaign(campaignId);
        boolean isClosed = campaign.getCampaignStatus();
        
        Timestamp timestamp = campaign.getEndTimeByid(campaignId);
        
        java.util.Date d = new java.util.Date();
        boolean HasEnd= d.after(timestamp);
        
        request.setAttribute("HasEnd", HasEnd);
        request.setAttribute("endTime", timestamp);
        request.setAttribute("campaignId", campaignId);
        request.setAttribute("isClosed", isClosed);
        request.setAttribute("noOfVotes", noOfVotes);
        request.setAttribute("currentResultJson", currentResultJson);
        request.setAttribute("currentResult", currentResult);
        request.setAttribute("myCurrentVote", myCurrentVote);
        request.setAttribute("myCurrentVoteJson", myCurrentVoteJson);        
        request.setAttribute("leadingOption", leadingOption);

        request.getRequestDispatcher("WEB-INF/CampaignStatisticsUser.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
    }

}
