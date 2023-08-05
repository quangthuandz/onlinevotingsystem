/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Common;

import Model.Account;
import Model.Campaign;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import jakarta.servlet.annotation.MultipartConfig;
import java.io.File;
import java.net.URL;

/**
 *
 * @author quang thuan
 */
@WebServlet(name = "SetupController", urlPatterns
        = {
            "/setup"
        })
@MultipartConfig
public class SetupController extends HttpServlet {

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
            out.println("<title>Servlet SetupController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SetupController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("WEB-INF/SetupCampaign.jsp").forward(request, response);
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
        String name = request.getParameter("title");
        String description = request.getParameter("description");
        String day = request.getParameter("day");
        String hour = request.getParameter("hour");
        String minute = request.getParameter("minute");
        String second = request.getParameter("second");
        String voteType = request.getParameter("voteType");
        String voteSecurity = request.getParameter("voteSecurity");
        String password = request.getParameter("passwordInput");
        String errorMessage = null;

        try {
            int dayValue = Integer.parseInt(day);
            int hourValue = Integer.parseInt(hour);
            int minuteValue = Integer.parseInt(minute);
            int secondValue = Integer.parseInt(second);

            if (dayValue>=0 && hourValue >= 0 && hourValue<24 && minuteValue >= 0 && secondValue >= 0
                    && minuteValue <= 60 && secondValue <= 60) {
                HttpSession session = request.getSession();
                session.setAttribute("day", day);
                session.setAttribute("hour", hour);
                session.setAttribute("minute", minute);
                session.setAttribute("second", second);
            } else {
                errorMessage = "Time invalid!";
                request.setAttribute("nameText", name);
                request.setAttribute("descriptionText", description);
            }
        } catch (NumberFormatException e) {
            errorMessage = "Time must be number";
        }

        Part file = request.getPart("image");
        
        String originalFileName = file.getSubmittedFileName();
        String imageName;

        if (file.getSize() == 0) {
            // Nếu không có tệp tin ảnh được tải lên, đặt ảnh mặc định
            imageName = "vote.jpg"; // Tên tệp tin ảnh mặc định
        } else {
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            imageName = System.currentTimeMillis() + fileExtension;
            String uploadPath = "C:/Users/khanh/OneDrive/onlinevotingsystem/WebApplication1/web/images/" + imageName;
            try {
                FileOutputStream fos = new FileOutputStream(uploadPath);
                InputStream is = file.getInputStream();
                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        
        if (errorMessage == null) {
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");

            Campaign c = new Campaign(Integer.parseInt(voteType), a.getId(), name, password, description, "images/"+imageName, Integer.parseInt(voteSecurity) == 1 ? true : false);
            c.addCampaign();
            int campaignId = c.getCampaignIdByAccountId(a.getId());
            session.setAttribute("campaign", c);
            session.setAttribute("campaignId", campaignId);
            session.setAttribute("dayEnd", day);
            session.setAttribute("hour", hour);
            session.setAttribute("minute", minute);
            session.setAttribute("second", second);
            session.setAttribute("voteSecurity", voteSecurity);
            System.out.println(voteSecurity);
            System.out.println("DDDDDÐK: "+day);
            response.sendRedirect("choicelist");
        } else {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("WEB-INF/SetupCampaign.jsp").forward(request, response);
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
