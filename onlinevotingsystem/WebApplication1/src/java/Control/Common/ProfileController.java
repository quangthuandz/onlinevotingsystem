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

/**

 @author ADMIN
 */
@WebServlet(name = "ProfileController", urlPatterns =
{
    "/profile"
})

public class ProfileController extends HttpServlet
{
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
        }else{
            Account a1 = new Account();
            a1.getProfileById(a.getId());
            request.setAttribute("data", a1);
            request.getRequestDispatcher("WEB-INF/Profile.jsp").forward(request, response);
        }
    }

    /**
     Handles the HTTP <code>POST</code> method.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException
//    {
//        HttpSession hs = request.getSession();
//        if (hs.getAttribute("account") == null)
//        {
//            response.sendRedirect("login");
//        }
//        String id = request.getParameter("id");
//        String FirstName = request.getParameter("firstname");
//        String LastName = request.getParameter("lastname");
//        String Email = request.getParameter("email");
//        String Phone = request.getParameter("phone");
//        String Address = request.getParameter("address");
//        String Gender = request.getParameter("gender");
//        String Job = request.getParameter("job");
//        String DoB = request.getParameter("dob");
//        System.out.println("dob after edit: "+DoB);
//
//        Account a = new Account(FirstName, LastName, Email, Phone, Address, Gender, Job, DoB, Integer.parseInt(id));
//        a.editProfile(Integer.parseInt(id));
//        request.setAttribute("data", a);
//        request.getRequestDispatcher("WEB-INF/Profile.jsp").forward(request, response);
//
//    }

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
