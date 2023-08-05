/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Admin;

import Model.Account;
import Model.Campaign;
import Model.CampaignAccount;
import Model.CommentPost;
import Model.Thread;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ducan
 */
@WebServlet(name = "AdminDashboardController", urlPatterns
        = {
            "/AdminDashboard",
            "/ExportCampaignReport",
            "/ExportUserReport"
        })
public class AdminDashboardController extends HttpServlet {

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

        Account a = new Account();
        int countUser = a.getUserCount();
        ArrayList<Account> dataAccount = a.getAccountForDashboard();
        ArrayList<Account> dataTopUser = a.getTopUsersWithInteractions(10);
        
        Campaign c = new Campaign();
        int countCampaign = c.getCampaignCount();
        ArrayList<Campaign> dataCampaign = c.getCampaignForDashboard();
        Thread t = new Thread();
        int countThread = t.getThreadCount();

        CommentPost cp = new CommentPost();
        int countComment = cp.getCommentCount();

        CampaignAccount ca = new CampaignAccount();
        //paging
        int pageNumber = request.getParameter("pageNumber") == null ? 1 : Integer.parseInt(request.getParameter("pageNumber"));

        if (pageNumber < 1) {
            pageNumber = 1;
        }

        int pageSize = request.getParameter("pageSize") == null ? 5 : Integer.parseInt(request.getParameter("pageSize"));

        if (!dataCampaign.isEmpty()) {
            int startIdx = (pageNumber - 1) * pageSize;
            int endIdx = Math.min(startIdx + pageSize, dataCampaign.size());
            List<Campaign> paginatedList = dataCampaign.subList(startIdx, endIdx);

            int totalPages = (int) Math.ceil((double) countCampaign / pageSize);

            request.setAttribute("totalPages", totalPages);

            request.setAttribute("pageNumber", pageNumber);

            request.setAttribute("pageSize", pageSize);

            request.setAttribute("paginatedList", paginatedList);
        }

        request.setAttribute("countUser", countUser);

        request.setAttribute("countCampaign", countCampaign);

        request.setAttribute("countThread", countThread);

        request.setAttribute("countComment", countComment);

        request.setAttribute("dataCampaign", dataCampaign);

        request.setAttribute("dataAccount", dataAccount);

        request.setAttribute("dataTopUser", dataTopUser);
        request.setAttribute("a", a);
        request.setAttribute("cp", cp);
        request.setAttribute("c", c);
        request.setAttribute("t", t);
        request.setAttribute("ca", ca);
        request.getRequestDispatcher("WEB-INF/AdminDashboard.jsp").forward(request, response);
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
    }

    protected void doExportCampaignReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account a = new Account();
        CommentPost cp = new CommentPost();
        Thread t = new Thread();
        Campaign c = new Campaign();
        CampaignAccount ca = new CampaignAccount();
        ArrayList<Campaign> dataCampaign = c.getCampaignForDashboard();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Campaign Report");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Campaign Id");
        headerRow.createCell(1).setCellValue("Campaign Name");
        headerRow.createCell(2).setCellValue("Host");
        headerRow.createCell(3).setCellValue("Start Date");
        headerRow.createCell(4).setCellValue("End Date");
        headerRow.createCell(5).setCellValue("Public");
        headerRow.createCell(6).setCellValue("Status");
        headerRow.createCell(7).setCellValue("Participants");
        headerRow.createCell(8).setCellValue("Threads");
        headerRow.createCell(9).setCellValue("Comments");
        int rowNum = 1;
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy/MM/dd"));

        for (Campaign campaign : dataCampaign) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(campaign.getId());
            row.createCell(1).setCellValue(campaign.getName());
            row.createCell(2).setCellValue(a.getNameById(campaign.getCreatedBy()));
            Cell startTimeCell = row.createCell(3);
            startTimeCell.setCellValue(campaign.getStartTime());
            startTimeCell.setCellStyle(dateCellStyle);

            Cell endTimeCell = row.createCell(4);
            endTimeCell.setCellValue(campaign.getEndTime());
            endTimeCell.setCellStyle(dateCellStyle);
            row.createCell(5).setCellValue(campaign.isIsPublic() ? "Yes" : "No");
            row.createCell(6).setCellValue(campaign.getStatus());
            row.createCell(7).setCellValue(ca.countVoterinCampaign(campaign.getId()));
            row.createCell(8).setCellValue(t.countThreadInCampaign(campaign.getId()));
            row.createCell(9).setCellValue(cp.countCommentInThread(campaign.getId()));
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"campaign_report.xlsx\"");

        workbook.write(response.getOutputStream());
        workbook.close();
    }
    
    protected void doExportUserReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account a = new Account();
        ArrayList<Account> dataAccount = a.getTopUsersWithInteractions(10);
        //Campaign c = new Campaign();
        //ArrayList<Campaign> dataCampaign = c.getCampaignForDashboard();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Campaign Report");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("User Id");
        headerRow.createCell(1).setCellValue("User Name");
        headerRow.createCell(2).setCellValue("Thread Count");
        headerRow.createCell(3).setCellValue("Comment Count");

        int rowNum = 1;
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy/MM/dd"));

        for (Account account : dataAccount) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(account.getId());
            row.createCell(1).setCellValue(account.getUsername());
            row.createCell(2).setCellValue(account.getThreadCount());
            row.createCell(3).setCellValue(account.getCommentCount());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"user_report.xlsx\"");

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith("/AdminDashboard")) {
            doGet(request, response);
        } else if (requestURI.endsWith("/ExportCampaignReport")) {
            doExportCampaignReport(request, response);
        } else if (requestURI.endsWith("/ExportUserReport")){
            doExportUserReport(request, response);
        }
    }
}
