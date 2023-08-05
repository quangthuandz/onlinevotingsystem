/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import Model.Account;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;

/**
 *
 * @author quang
 */
@WebServlet(name = "ValidateVoterController", urlPatterns = {"/validatevoters"})
public class ValidateVoterController extends HttpServlet {

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
            out.println("<title>Servlet ValidateVoterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ValidateVoterController at " + request.getContextPath() + "</h1>");
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
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String json = sb.toString();

        // Chuyển đổi JSON thành mảng email
        Gson gson = new Gson();
        String[] emailList = gson.fromJson(json, String[].class);

        int invalidIndex = -1;
        Account a = new Account();

        for (int i = 0; i < emailList.length; i++) {
            if (!a.checkEmailExist(emailList[i])) {
                invalidIndex = i;
                break; // Gửi thông báo lỗi ngay khi tìm thấy email không tồn tại
            }
        }

        if (invalidIndex == -1) {
            // Tất cả email đều tồn tại trong cơ sở dữ liệu
            String result = "All emails are valid";
            response.getWriter().write(result);
            HttpSession session = request.getSession();
            session.setAttribute("emaillist", emailList);
        } else {
            // Có email không tồn tại trong cơ sở dữ liệu
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Đặt mã lỗi 400 Bad Request
            response.getWriter().write("Email not found at row: " + invalidIndex);
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
