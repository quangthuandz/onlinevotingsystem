/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Common;

import Model.Account;
import Model.Campaign;
import Model.VotingObject;
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
import java.util.ArrayList;
import jakarta.servlet.annotation.MultipartConfig;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quang thuan
 */
@WebServlet(name = "ListChoiceController", urlPatterns
        = {
            "/choicelist"
        })
@MultipartConfig
public class ListChoiceController extends HttpServlet {

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
            out.println("<title>Servlet DetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailController at " + request.getContextPath() + "</h1>");
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
        //lay detailId 
        String detailId = request.getParameter("did");
        System.out.println("did " + detailId);
        
        //lay campaignId
        HttpSession session = request.getSession();
        String voteSecurity = (String) session.getAttribute("voteSecurity");
        Campaign c = (Campaign) session.getAttribute("campaign");
        
        VotingObject vo = new VotingObject();
        int campaignId = (int) session.getAttribute("campaignId");
        
        //lay ra 1 list cac option theo campaignId
        ArrayList<VotingObject> list = null;
        try
        {
            list = vo.getListOptionByCampaignId(campaignId);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ListChoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //lay ra voting object cua the votingId
        VotingObject detail = new VotingObject();
        if (detailId != null) {
            detail = detail.getVotingObjectByObjectId(Integer.parseInt(detailId));
        }

        request.setAttribute("detail", detail);
        session.setAttribute("votingobject", list);
        session.setAttribute("voteSecurity", voteSecurity);
        request.getRequestDispatcher("WEB-INF/Detail.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int campaignId = (int) session.getAttribute("campaignId");
        String name = request.getParameter("objectname");
        String des = request.getParameter("des");
        Part file = request.getPart("image");
        System.out.println("ten file " + file);

        String originalFileName = file.getSubmittedFileName();
        String imageName;

        if (file.getSize() == 0) {
            // Nếu không có tệp tin ảnh được tải lên, đặt ảnh mặc định
            imageName = "avatar.jpg"; // Tên tệp tin ảnh mặc định
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

        VotingObject vo = new VotingObject(name, "1", campaignId, "images/"+imageName, des);
        try
        {
            vo.addVotingObject();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ListChoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("candidateimage", imageName);
        response.sendRedirect("choicelist");
    }

}
