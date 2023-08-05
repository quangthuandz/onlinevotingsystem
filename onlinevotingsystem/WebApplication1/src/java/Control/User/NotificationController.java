/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.User;

import Model.Account;
import Model.Notifications;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class NotificationController extends HttpServlet {

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
            out.println("<title>Servlet NotificationController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NotificationController at " + request.getContextPath() + "</h1>");
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
//        try (PrintWriter out = response.getWriter()){
//            HttpSession hs = request.getSession();
//            Account a = (Account) hs.getAttribute("account");
//            
//            Notifications n = new Notifications();
//            response.setContentType("application/json");
//           
//
//                String notifications = n.ShowAllNotification(a.getId());
//                int newNotificationCount = n.NumberOfNewNotification(a.getId());
//
//                JsonObject data = new JsonObject();
//                data.addProperty("notifications", notifications);
//
//                Gson gson = new Gson();
//                String jsonData = gson.toJson(data);
//                out.print(jsonData);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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
        try {
            HttpSession hs = request.getSession();
            Account a = (Account) hs.getAttribute("account");
            
            Notifications n = new Notifications();
            response.setContentType("application/json");
            try ( PrintWriter out = response.getWriter()) {
                if (request.getParameter("view") != null) {  
                   System.out.println("View: "+ request.getParameter("view"));
                   n.UpdateNotificationStatus(a.getId());
                }

                String notifications = n.ShowNewNotification(a.getId());
                int newNotificationCount = n.NumberOfNewNotification(a.getId());
                String all = n.ShowAllNotification(a.getId());
                JsonObject data = new JsonObject();
                data.addProperty("all", all);
                data.addProperty("notifications", notifications);
                data.addProperty("unseen_notification", newNotificationCount);

                Gson gson = new Gson();
                String jsonData = gson.toJson(data);
                out.print(jsonData);
            }

        } catch (Exception e) {
            e.printStackTrace();
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
