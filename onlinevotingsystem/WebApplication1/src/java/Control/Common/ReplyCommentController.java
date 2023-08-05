/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Common;

import Model.Account;
import Model.Thread;
import Model.CommentPost;
import Model.Notifications;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ducan
 */
public class ReplyCommentController extends HttpServlet {

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
            out.println("<title>Servlet ReplyCommentController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReplyCommentController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        CommentPost cp = new CommentPost();
        Notifications n = new Notifications();
        CommentPost c = new CommentPost();
        CommentPost c1 = new CommentPost();
        Thread t = new Thread();
        //int AccountId, int ThreadId , String Content, String CreatedAt, int ParentCommentId
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        int accountId = a.getId();
        String action = "reply";
        String action2 = "comment";
        int threadId = Integer.parseInt(request.getParameter("threadID"));
        t = t.getThreadById(threadId);
        String content = request.getParameter("replyContent");
        int parentId = Integer.parseInt(request.getParameter("parentId").trim());
        cp.replyComment(accountId, threadId, content, parentId);
        c1.GetAccountIdByCommentId(request.getParameter("parentId").trim());
        System.out.println("C1 AccountId: "+ c1.getAccountId() );
        c.SelectReplyId(accountId, threadId, parentId);
        if(c1.getAccountId() != a.getId()) n.AddCommentRelatedNotification(c1.getAccountId(), action, threadId,c.getCommentId() , a.getId());
        if(t.getAccountId() != a.getId() && t.getAccountId() != c1.getAccountId()) n.AddCommentRelatedNotification(t.getAccountId(), action2, threadId, c.getCommentId(), a.getId());
        int Cid = Integer.parseInt(request.getParameter("Cid"));
        response.sendRedirect("ViewCampaignDetails?id=" + Cid);
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
