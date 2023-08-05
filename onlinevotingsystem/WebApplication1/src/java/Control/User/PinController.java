package Control.User;

import Model.Account;
import Model.Pin;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class PinController extends HttpServlet{
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pin and unpin
        Pin p = new Pin();
        //get accountId and threadId from Thread
        // Get userid
        HttpSession hs = request.getSession();
        Account a = (Account) hs.getAttribute("account");
        String campaignId = "";
        if (request.getParameter("campaignId") != null) {
            campaignId = request.getParameter("campaignId");
        }
        int accountId = a.getId();

        String action = request.getParameter("action");

        if (action != null && (action.equals("pin"))) {
            p.updatePin(accountId, campaignId);
        } else {
            p.updateCancelPin(accountId, campaignId);
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
