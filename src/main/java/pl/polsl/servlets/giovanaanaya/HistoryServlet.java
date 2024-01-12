/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.servlets.giovanaanaya;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HistoryServlet handles requests related to displaying history records.
 * This servlet displays a table of historical records stored in the session.
 * It also keeps track of the number of times the user has visited the history page using cookies.
 * 
 * @author Giovana Anaya
 * @version 1.0
 */
@WebServlet(name = "History", urlPatterns = {"/History"})
public class HistoryServlet extends HttpServlet {

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

        String action = request.getParameter("history");

        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            if ("History".equals(action)) {

                List<Map<String, String>> history = (List<Map<String, String>>) session.getAttribute("history");
                if (history == null) {
                    history = new ArrayList<>();
                }

                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>History</title>");
                out.println("</head>");
                out.println("<body>");

                out.println("<style>table, th, td {border:1px solid black;}</style>");
                //GO HOME
                out.println(String.format("<a style='color:blue' href=%s>Home</a>", request.getContextPath() + "/"));
                out.println("<h1>History</h1>");
                //Creates the table
                out.println("<table style='width: 50%'>");
                out.println("<thead>");
                out.println("<tr>");
                //Columns of the table
                out.println("<th>Operation</th>");
                out.println("<th>Input</th>");
                out.println("<th>Result</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");

                for (Map<String, String> entry : history) {
                    out.println("<tr>");
                    out.println(String.format("<td>%s</td>", entry.get("operation")));
                    out.println(String.format("<td>%s</td>", entry.get("input")));
                    out.println(String.format("<td>%s</td>", entry.get("result")));
                    out.println("</tr>");
                }
                out.println("</tbody>");

                out.println("</table>");
                
                //Calculates how many times the history page has been visited
                Cookie[] cookies = request.getCookies();
                boolean visitedBefore = false;

                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("visitCount")) {
                            visitedBefore = true;
                            int visitCount = Integer.parseInt(cookie.getValue());
                            visitCount++;
                            cookie.setValue(String.valueOf(visitCount));
                            response.addCookie(cookie);
                            out.println("<p>You have visited this page "+visitCount+" times</p>");
                        }
                    }
                }

                if (!visitedBefore) {
                    // If user is visiting for the first time, create a new cookie
                    Cookie visitCookie = new Cookie("visitCount", "1");
                    response.addCookie(visitCookie);
                    out.println("<p>This is the first time you are visiting this page!</p>");
                }
                out.println("</body>");
                out.println("</html>");

            }
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
