/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Host;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author quang
 */
@WebServlet(name = "DownloadExcelController", urlPatterns = {"/downloadexcel"})
public class DownloadExcelController extends HttpServlet {

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
            out.println("<title>Servlet DownloadExcelController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DownloadExcelController at " + request.getContextPath() + "</h1>");
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
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"voter.xlsx\"");

        try ( OutputStream outputStream = response.getOutputStream()) {
            Workbook workbook = createExcelWorkbook();
            Sheet sheet = workbook.getSheetAt(0);
            sheet.setColumnWidth(0, 8000);
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần thiết
        }
    }

    private Workbook createExcelWorkbook() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Voters");

        // Merge ô từ C5 đến K5 và ghi văn bản vào ô
        CellStyle redTextStyle = workbook.createCellStyle();
        Font redFont = workbook.createFont();
        redFont.setColor(IndexedColors.RED.getIndex());
        redTextStyle.setFont(redFont);
        redTextStyle.setWrapText(true);

        Row row = sheet.createRow(4);
        Cell cell = row.createCell(2);
        cell.setCellValue("Please enter all the emails of the voters you want to invite into column A.");
        cell.setCellStyle(redTextStyle);
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 2, 10));

        // Merge ô từ C8 đến K8 và ghi văn bản vào ô
        row = sheet.createRow(7);
        cell = row.createCell(2);
        cell.setCellValue("Please note that you must not leave any blank rows between data and  do not enter emails in other columns.");
        cell.setCellStyle(redTextStyle);
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 2, 10));
        
        Row row11 = sheet.createRow(10);
        Cell cellC11 = row11.createCell(2);
        cellC11.setCellValue("After inputting all the emails, please import this file to the website");
        cellC11.setCellStyle(redTextStyle);
        sheet.addMergedRegion(new CellRangeAddress(10, 10, 2, 10));


        // Đặt border cho hai ô cell đã merge
        RegionUtil.setBorderTop(BorderStyle.THIN, new CellRangeAddress(4, 4, 2, 10), sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, new CellRangeAddress(4, 4, 2, 10), sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, new CellRangeAddress(4, 4, 2, 10), sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, new CellRangeAddress(4, 4, 2, 10), sheet);

        RegionUtil.setBorderTop(BorderStyle.THIN, new CellRangeAddress(7, 7, 2, 10), sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, new CellRangeAddress(7, 7, 2, 10), sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, new CellRangeAddress(7, 7, 2, 10), sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, new CellRangeAddress(7, 7, 2, 10), sheet);
        
        RegionUtil.setBorderTop(BorderStyle.THIN, new CellRangeAddress(10, 10, 2, 10), sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, new CellRangeAddress(10, 10, 2, 10), sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, new CellRangeAddress(10, 10, 2, 10), sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, new CellRangeAddress(10, 10, 2, 10), sheet);

        return workbook;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
