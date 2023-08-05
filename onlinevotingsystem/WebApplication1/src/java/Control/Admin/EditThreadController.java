/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Thread;

/**
 *
 * @author ducan
 */
public class EditThreadController extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int threadId = Integer.parseInt(request.getParameter("threadId"));
        String newContent = request.getParameter("content");
        int Cid = Integer.parseInt(request.getParameter("Cid"));
        
        Thread t = new Thread();
        t.editThread(threadId, newContent);
        response.sendRedirect("ViewCampaignDetails?id=" + Cid);
    
    }
}
