/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Common;

import Model.Account;
import Model.CommentPost;
import Model.Notifications;
import Model.Thread;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Hanh
 */
public class CommentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Comment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Comment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommentPost comment = new CommentPost();
        Notifications n = new Notifications();
        Thread t = new Thread();
        CommentPost cp = new CommentPost();
        
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String CreatedAt = currentDateTime.format(formatter);
        int Cid = Integer.parseInt(request.getParameter("Cid"));
        String ContentComment = request.getParameter("Content");
        String EditContent = request.getParameter("EditContent");
        if (ContentComment != null) {
            int accountid = Integer.parseInt(request.getParameter("accountID"));
            int threadid = Integer.parseInt(request.getParameter("threadID"));
            t = t.getThreadById(threadid);
            String action = "comment";
            comment.AddComment(threadid, accountid, ContentComment,CreatedAt);
            cp.SelectCommentId(accountid, threadid);
            if(t.getAccountId() != accountid) n.AddCommentRelatedNotification(t.getAccountId(),action,threadid,cp.getCommentId(),accountid);
            response.sendRedirect("ViewCampaignDetails?id=" + Cid);
        }
        if(EditContent != null){
            int commentid = Integer.parseInt(request.getParameter("commentID"));
            comment = comment.CheckComment(commentid,EditContent);
            System.out.println("CommentId: " + comment.getCommentId());
            System.out.println("AccountId: " + comment.getAccountId());
            System.out.println("Content: " + comment.getContent());
            comment.updateCommentContent(commentid, EditContent);
            System.out.println("EditContent: " + comment.getContent());
            response.sendRedirect("ViewCampaignDetails?id=" + Cid);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
