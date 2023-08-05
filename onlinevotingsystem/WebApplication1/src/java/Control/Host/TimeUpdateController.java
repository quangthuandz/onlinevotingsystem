/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Host;

import Model.Account;
import Model.Campaign;
import Model.CampaignAccount;
import Model.VotingObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Comment;

/**
 *
 * @author quang
 */
@WebServlet(name = "TimeUpdateController", urlPatterns = {"/timeupdate"})
public class TimeUpdateController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Timer timer;
    private Map<Integer, List<Integer>> campaignDataMap;
    private Map<Integer, List<Integer>> campaignDataMapComment;
    private Map<Integer, List<Integer>> campaignDataMapVote;
    private int temp;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeUpdateController() {
        super();
    }

    /**
     * @see HttpServlet#init()
     */
    public void init() throws ServletException {
        campaignDataMap = new HashMap<>();
        campaignDataMapComment = new HashMap<>();
        campaignDataMapVote = new HashMap<>();
        timer = new Timer();
        temp = 0;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateCampaignData();
            }
        }, 10000, 10000); // Cập nhật sau mỗi 5 giây
    }

    private void updateCampaignData() {
        Model.Thread td = new Model.Thread();
        Model.CommentPost c = new Model.CommentPost();
        Model.Vote v = new Model.Vote();
        int thread = td.countThreadInCampaign(temp);
        int comment = c.countCommentInThread(temp);
        int vote = v.countVotedBallot(temp);
        
        List<Integer> campaignData = campaignDataMap.get(temp);
        List<Integer> campaignDataComment = campaignDataMapComment.get(temp);
        List<Integer> campaignDataVote = campaignDataMapVote.get(temp);
        if (campaignData == null) {
            campaignData = new ArrayList<>();
            campaignDataMap.put(temp, campaignData);
        }
        
        if (campaignDataComment == null) {
            campaignDataComment = new ArrayList<>();
            campaignDataMapComment.put(temp, campaignDataComment);
        }
        
        if (campaignDataVote == null) {
            campaignDataVote = new ArrayList<>();
            campaignDataMapVote.put(temp, campaignDataVote);
        }
        
        campaignDataVote.add(vote);
        campaignDataComment.add(comment);
        campaignData.add(thread);
    }

    /**
     * @see HttpServlet#destroy()
     */
    public void destroy() {
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String hour = request.getParameter("hours");
        String minute = request.getParameter("minutes");
        String second = request.getParameter("seconds");
        
        int yearValue = Integer.parseInt(year);
        int monthValue = Integer.parseInt(month);
        int dayValue = Integer.parseInt(day);
        int hourValue = Integer.parseInt(hour);
        int minuteValue = Integer.parseInt(minute);
        int secondValue = Integer.parseInt(second);

        Calendar calendar = Calendar.getInstance();
        calendar.set(yearValue, monthValue - 1, dayValue, hourValue, minuteValue, secondValue);
        Timestamp datetime = new Timestamp(calendar.getTimeInMillis());

        HttpSession session = request.getSession();
        String dayEnd = (String) session.getAttribute("dayEnd");
        String hourEnd = (String) session.getAttribute("hour");
        String minuteEnd = (String) session.getAttribute("minute");
        String secondEnd = (String) session.getAttribute("second");
        System.out.println("HHHHH "+hourEnd);
        System.out.println("DDDDD " + dayEnd);
        System.out.println("Hello");
        int dayEndValue = Integer.parseInt(dayEnd);
        int hourEndValue = Integer.parseInt(hourEnd);
        int minuteEndValue = Integer.parseInt(minuteEnd);
        int secondEndValue = Integer.parseInt(secondEnd);

        calendar.add(Calendar.SECOND, secondEndValue);
        calendar.add(Calendar.MINUTE, minuteEndValue);
        calendar.add(Calendar.HOUR_OF_DAY, hourEndValue);
        calendar.add(Calendar.DAY_OF_MONTH, dayEndValue);

        Timestamp endTime = new Timestamp(calendar.getTimeInMillis());

        int cid = (int) session.getAttribute("campaignId");
        String question = (String) session.getAttribute("questionforcam");
        Account a = (Account) session.getAttribute("account");

        Campaign c = new Campaign(datetime, endTime);
        System.out.println("dateTIme " + datetime + " endTime " + endTime);
        VotingObject vo = new VotingObject(question);
        CampaignAccount ca = new CampaignAccount(cid, a.getId());

        vo.updateQuestion(cid);
        c.updateTime(cid);
        ca.addHost();

        temp = cid; // Gán temp sau khi đã sử dụng cid

        for (Map.Entry<Integer, List<Integer>> entry : campaignDataMap.entrySet()) {
            Integer key = entry.getKey();
            List<Integer> value = entry.getValue();
        }

        session.setAttribute("campaignDataMap", campaignDataMap);
        session.setAttribute("campaignDataMapComment", campaignDataMapComment);
        session.setAttribute("campaignDataMapVote", campaignDataMapVote);
        session.setAttribute("camId", cid);

        response.sendRedirect("ViewCampaignDetails?id=" + cid);
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
