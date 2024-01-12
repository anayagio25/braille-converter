package pl.polsl.servlets.giovanaanaya;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.polsl.braillemodel.giovanaanaya.BrailleModel;
import pl.polsl.braillemodel.giovanaanaya.HistoryEntity;
import pl.polsl.braillemodel.giovanaanaya.Operations;

/**
 * BrailleServlet implementation class for the Braille Code Converter.
 *
 * @author Giovana Anaya
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/Servlet"})
public class BrailleServlet extends HttpServlet {

    /**
     * The BrailleModel instance used for convert from text to Braille and vice versa. 
     */
    BrailleModel brailleModel = new BrailleModel();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String input = request.getParameter("input");
        String conversion = "";
        String action = request.getParameter("buttonClicked");
        //GO HOME
        PrintWriter out = response.getWriter();
        out.println(String.format("<a style='color:blue' href=%s>Home</a>", request.getContextPath() + "/"));
        try {
            if (input.isEmpty()) {
                //out.println("<p>Input cannot be empty</p>");
                //errorsCount(request, response);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Input cannot be empty");
            } else {
                //This sets the info in the fields to which each correspond
                HistoryEntity history = new HistoryEntity();
                history.setOperation(action);
                history.setInput(input);

                if ("Convert To Text".equals(action)) {
                    conversion = brailleModel.convertToText(input);
                    if (conversion.isEmpty()) {
                        out.println("<p>Input has to be only numbers and a even size</p>");
                        countErrors(request, response);
                        //response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Input has to be only numbers and a even size");

                    } else {
                        response.setContentType("text/html");
                        out.println("<head><title>Conversion Result</title></head>");
                        out.println("<body>");
                        out.println("<h1>Conversion Result</h1>");
                        out.println("<p>Input: " + input + "</p>");
                        out.println("<p>Result: " + conversion + "</p>");
                        out.println("</body>");
                        out.println("</html>");
                        addToHistory(request, input, conversion, "Convert To Text");
                        history.setResult(conversion);
                    }

                }
                if ("Convert To Braille".equals(action)) {
                    conversion = brailleModel.convertTextToBraille(input);
                    if (conversion.isEmpty()) {
                        out.println("<p>Input has to be letters</p>");
                        countErrors(request, response);
                        // response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Input has to be letters");
                    } else {
                        response.setContentType("text/html");
                        out.println("<head><title>Conversion Result</title></head>");
                        out.println("<body>");
                        out.println("<h1>Conversion Result</h1>");
                        out.println("<p>Input: " + input + "</p>");
                        out.println("<p>Result: " + conversion + "</p>");
                        out.println("</body>");
                        out.println("</html>");
                        addToHistory(request, input, conversion, "Convert To Braille");
                        history.setResult(conversion);
                    }
                }
                // This will save the object history in the database
                Operations operation = new Operations();
                operation.persistObject(history);

            }

        } catch (BrailleModel.ModelException ex) {
            Logger.getLogger(BrailleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adds the current operation to the user's history.
     *
     * @param request servlet request
     * @param input input value
     * @param result conversion result
     * @param operation type of conversion operation
     */
    private void addToHistory(HttpServletRequest request, String input, String result, String operation) {
        // Retrieve the current history from the session
        List<Map<String, String>> history = (List<Map<String, String>>) request.getSession().getAttribute("history");

        // If there's no history yet, create a new list
        if (history == null) {
            history = new ArrayList<>();
        }

        // Create a new entry map for each operation
        Map<String, String> entry = new HashMap<>();
        entry.put("operation", operation);
        entry.put("input", input);
        entry.put("result", result);

        // Add the new entry to the history
        history.add(entry);

        // Update the history in the session
        request.getSession().setAttribute("history", history);

    }

    /**
     * Counts and displays the number of errors made by the user.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    private void countErrors(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        boolean errorsExist = false;
        int errorCount;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("errorCount")) {
                    errorsExist = true;
                    errorCount = Integer.parseInt(cookie.getValue());
                    errorCount++;
                    cookie.setValue(String.valueOf(errorCount));
                    response.addCookie(cookie);
                    out.println("<p>You have make " + errorCount + " errors</p>");
                }
            }
        }
        if (!errorsExist) {
            // If there are no error cookies, create a new cookie
            Cookie errorCookie = new Cookie("errorCount", "1");
            response.addCookie(errorCookie);
            out.println("<p>This is the first time you make an error!</p>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
