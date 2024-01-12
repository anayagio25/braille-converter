/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.servlets.giovanaanaya;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import pl.polsl.braillemodel.giovanaanaya.HistoryEntity;
import pl.polsl.braillemodel.giovanaanaya.Operations;

/**
 * DBHistoryServlet handles requests related to database history retrieval.
 * This servlet retrieves historical records from the database and displays them in an HTML format.
 * It uses the Operations class to interact with the database.
 * 
 * @author Giovana Anaya
 */
@WebServlet(name = "DBHistoryServlet", urlPatterns = {"/DBHistoryServlet"})
public class DBHistoryServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DBHistoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DBHistoryServlet at " + request.getContextPath() + "</h1>");
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
        Operations operation = new Operations();
        List<HistoryEntity> historyList = operation.findObjects();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(String.format("<a style='color:blue' href=%s>Home</a>", request.getContextPath() + "/"));
        out.println("<html>");
        out.println("<head><title>DataBase History</title></head>");
        out.println("<body>");
        out.println("<h1>History List</h1>");

        if (historyList != null && !historyList.isEmpty()) {
            out.println("<ul>");
            out.println("<table border='1'>");
            out.println("<tr><th>Operation</th><th>Input</th><th>Result</th></tr>");

            for (HistoryEntity history : historyList) {
                out.println("<tr>");
                out.println("<td>" + history.getOperation() + "</td>");
                out.println("<td>" + history.getInput() + "</td>");
                out.println("<td>" + history.getResult() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</ul>");
        } else {
            out.println("<p>No history available</p>");
        }

        out.println("</body>");
        out.println("</html>");
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
