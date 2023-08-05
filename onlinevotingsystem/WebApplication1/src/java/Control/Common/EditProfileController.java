/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Common;

import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**

 @author ADMIN
 */
@WebServlet(name = "EditController", urlPatterns
        =
        {
            "/edit"
        })
public class EditProfileController extends HttpServlet
{

    /**
     Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     methods.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        // Access Control - not logged in
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a == null)
        {
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter())
            {
                out.print("Access Denied.");
                return;
            }
        }
        a = new Account();
        String id = request.getParameter("id");
        a.getProfileById(Integer.parseInt(id));
        System.out.println(a.getId());

        request.setAttribute("data", a);
        request.getRequestDispatcher("WEB-INF/ProfileEdit.jsp").forward(request, response);
    }

    /**
     Handles the HTTP <code>POST</code> method.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession hs = request.getSession();
        if (hs.getAttribute("account") == null)
        {
            response.sendRedirect("login");
        }
        String id = request.getParameter("id");
        System.out.println("Profile's ID: " + id);
        String FirstName = request.getParameter("firstname");
        String LastName = request.getParameter("lastname");
        String Email = request.getParameter("email");
        String Phone = request.getParameter("phone");
        String Address = request.getParameter("address");
        String Gender = request.getParameter("gender");
        String Job = request.getParameter("job");
        String DoB = request.getParameter("dob");
        System.out.println("dob after edit: " + DoB);

        Account a = new Account(FirstName, LastName, Email, Phone, Address, Gender, Job, DoB, Integer.parseInt(id));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = new java.util.Date();
        try
        {
            d = (java.util.Date) dateFormat.parse(DoB);
        }
        catch (ParseException ex)
        {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String msg = "";
        if (FirstName.isEmpty() || LastName.isEmpty()
                || Email.isEmpty() || Phone.isEmpty() || Address.isEmpty()
                || Gender.isEmpty() || DoB.isEmpty())
        {
            msg += "You must fill all the field";
        }
        else
        {
            if (d.after(new java.util.Date()))
            {
                msg += "Date of birth not valid";
            }
//            else if (!email.matches("^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$") || a.checkEmailExist(email))
//            {
//                msg += "email not valid";
//            }
            else if (!Phone.matches("^0[0-9]{9,10}$"))
            {
                msg += "phone number not valid";
            }
            else
            {
                a.editProfile(Integer.parseInt(id));
                request.setAttribute("data", a);
                request.getRequestDispatcher("WEB-INF/Profile.jsp").forward(request, response);
            }
            request.setAttribute("data", a);
            request.setAttribute("error", msg);
            request.getRequestDispatcher("WEB-INF/ProfileEdit.jsp").forward(request, response);

        }

    }

    /**
     Returns a short description of the servlet.

     @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
