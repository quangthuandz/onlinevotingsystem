package Control.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RemoveThread extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String threadId = request.getParameter("threadId");
        Model.Thread t = new Model.Thread();
        String action = request.getParameter("action");
        if(action != null && action.equals("delete")){
            t.removeThread(threadId);
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write("{}");
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
}
